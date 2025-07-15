# Linked Lists - Quick Revision Notes

## 🏗️ Basic Structure

### Node Definition
```python
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val      # Data storage
        self.next = next    # Pointer to next node
```

### Key Components
- **val**: Stores the data
- **next**: Reference/pointer to next node
- **head**: Starting node (entry point)
- **tail**: Last node (points to null)

---

## 📋 Singly Linked List

### Structure
```
head → [1] → [2] → [3] → [4] → [5] → null
```

### Characteristics
- **Unidirectional**: Can only traverse forward
- **Head Access**: Only immediate access to first node
- **Traversal**: Must start from head to reach other nodes

### Traversal Pattern
```python
ptr = head
while ptr:
    # Process ptr.val
    ptr = ptr.next
```

---

## 🔗 Doubly Linked List

### Node Definition
```python
class DoublyListNode:
    def __init__(self, val=0, next=None, prev=None):
        self.val = val
        self.next = next    # Forward pointer
        self.prev = prev    # Backward pointer
```

### Structure
```
head ⇄ [1] ⇄ [2] ⇄ [3] ⇄ [4] ⇄ [5] ⇄ tail
```

### Characteristics
- **Bidirectional**: Can traverse forward and backward
- **Two Access Points**: Both head and tail available
- **Extra Memory**: Requires additional prev pointer

---

## ⚖️ Advantages vs Disadvantages

### ✅ Advantages
- **Dynamic Size**: Grow/shrink during runtime
- **Efficient Insertion/Deletion**: O(1) at known position
- **Memory Efficient**: Only allocate what's needed
- **No Memory Waste**: Unlike fixed arrays

### ❌ Disadvantages
- **No Random Access**: Can't access by index O(1)
- **Sequential Access**: Must traverse from head O(n)
- **Extra Memory**: Pointers require additional space
- **Cache Performance**: Nodes may not be contiguous in memory

---

## 🔄 Common Operations

### Insertion
```python
# Insert new_node between prev_node and next_node
new_node.next = prev_node.next
prev_node.next = new_node
```

### Deletion
```python
# Remove node after prev_node
prev_node.next = prev_node.next.next
```

### Search
```python
def search(head, target):
    current = head
    while current:
        if current.val == target:
            return current
        current = current.next
    return None
```

---

## 🎯 Pointer Manipulation Tips

### Visualization Strategy
- **Think of pointers as arrows** pointing between nodes
- **Draw the changes** before coding
- **Update pointers step by step** to avoid losing references

### Common Pattern
```python
# Save references before changing
temp = current.next
current.next = new_node
new_node.next = temp
```

### Key Rules
1. **Never lose the reference** to the rest of the list
2. **Update pointers in correct order**
3. **Handle edge cases** (empty list, single node)

---

## 🛠️ Interview Problem Patterns

### 1. Traversal Problems
- **Two Pointers**: Fast/slow, left/right
- **Cycle Detection**: Floyd's algorithm
- **Intersection**: Finding common nodes
- **Palindrome Check**: Compare forward/backward

### 2. Restructuring Problems
- **Reversal**: Change direction of pointers
- **Removal**: Delete specific nodes
- **Merging**: Combine multiple lists
- **Reordering**: Change node positions

### 3. Doubly Linked List Problems
- **LRU Cache**: Most recently used tracking
- **Browser History**: Forward/back navigation
- **Undo/Redo**: Bidirectional operations

---

## 🚀 Key Interview Techniques

### Two Pointer Technique
```python
slow = fast = head
while fast and fast.next:
    slow = slow.next
    fast = fast.next.next
# slow is at middle when fast reaches end
```

### Dummy Node Pattern
```python
dummy = ListNode(0)
dummy.next = head
# Helps handle edge cases with head manipulation
```

### Cycle Detection (Floyd's)
```python
slow = fast = head
while fast and fast.next:
    slow = slow.next
    fast = fast.next.next
    if slow == fast:
        return True  # Cycle found
return False
```

---

## 🎵 Real-World Applications

### Music Playlist
- **Singly Linked**: Simple forward playlist
- **Doubly Linked**: Previous/next song navigation
- **Circular**: Repeat playlist functionality

### Browser History
- **Doubly Linked**: Back/forward buttons
- **Dynamic**: Add new pages, clear history

### Undo/Redo Systems
- **Doubly Linked**: Navigate between states
- **Memory Efficient**: Only store differences

---

## ⚠️ Common Interview Pitfalls

### Memory Issues
- **Losing References**: Always save next before changing
- **Memory Leaks**: Properly handle deleted nodes
- **Null Pointer**: Check for null before accessing

### Edge Cases to Remember
- **Empty List**: head = null
- **Single Node**: head.next = null  
- **Two Nodes**: Special handling needed
- **Circular Lists**: Infinite loop prevention

### Testing Strategy
1. **Empty list**
2. **Single element**
3. **Two elements** 
4. **Multiple elements**
5. **Circular list** (if applicable)

---

## 📚 Quick Reference

| Operation | Singly | Doubly | Array |
|-----------|---------|---------|-------|
| **Access** | O(n) | O(n) | O(1) |
| **Search** | O(n) | O(n) | O(n) |
| **Insert** | O(1)* | O(1)* | O(n) |
| **Delete** | O(1)* | O(1)* | O(n) |

*At known position. O(n) if position unknown.

### Space Complexity
- **Singly**: O(n) - one pointer per node
- **Doubly**: O(n) - two pointers per node  
- **Array**: O(n) - continuous memory

### When to Use
- **Linked List**: Dynamic size, frequent insertions/deletions
- **Array**: Random access needed, cache performance important
