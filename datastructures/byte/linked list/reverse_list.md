# Linked List Reversal - Quick Revision Notes

## 🎯 Problem Statement
**Goal**: Reverse a singly linked list in-place
```
Input:  1 → 2 → 4 → 7 → 3 → null
Output: 3 → 7 → 4 → 2 → 1 → null
```

---

## 🔄 Approach 1: Iterative (Recommended)

### Key Insight
**Flip pointer direction** at each node: `node.next = previous_node`

### Three Pointers Strategy
```python
prev_node = None      # Previous node (starts null)
curr_node = head      # Current node being processed
next_node = None      # Temporary storage for next node
```

### Algorithm Steps
**At each iteration:**
1. **Save reference**: `next_node = curr_node.next`
2. **Reverse pointer**: `curr_node.next = prev_node`  
3. **Move forward**: `prev_node = curr_node`, `curr_node = next_node`

### Visual Flow
```
Initial:  null ← prev   curr → next → ...
Step 1:   null ← prev ← curr   next → ...
Step 2:         prev ← curr ← next → ...
```

### Implementation
```python
def reverse_list(head):
    prev_node, curr_node = None, head
    
    while curr_node:
        next_node = curr_node.next    # 1. Save next
        curr_node.next = prev_node    # 2. Reverse pointer
        prev_node = curr_node         # 3. Move prev forward
        curr_node = next_node         # 4. Move curr forward
    
    return prev_node  # New head of reversed list
```

### Complexity
- **Time**: O(n) - Visit each node once
- **Space**: O(1) - Only three pointers

---

## 🔁 Approach 2: Recursive

### Key Insight
**Divide & Conquer**: Reverse sublist, then connect current node

### Base Cases
```python
if not head or not head.next:
    return head  # Empty list or single node
```

### Recursive Logic
1. **Recursively reverse** sublist starting from `head.next`
2. **Connect current node** to end of reversed sublist
3. **Break original connection** from current node

### Step-by-Step
```python
# Given: 1 → 2 → 3 → 4 → null

# 1. Recursively reverse 2→3→4
new_head = reverse_list(head.next)  # Returns 4→3→2→null

# 2. Connect: Make 2 point back to 1
head.next.next = head  # 2.next = 1

# 3. Break: Remove 1's connection to 2  
head.next = None       # 1.next = null

# Result: 4→3→2→1→null
```

### Implementation
```python
def reverse_list_recursive(head):
    # Base cases
    if not head or not head.next:
        return head
    
    # Reverse sublist and get new head
    new_head = reverse_list_recursive(head.next)
    
    # Connect current node to reversed sublist
    head.next.next = head
    head.next = None
    
    return new_head
```

### Complexity
- **Time**: O(n) - Visit each node once
- **Space**: O(n) - Recursive call stack

---

## 📊 Approach Comparison

| Aspect | Iterative | Recursive |
|--------|-----------|-----------|
| **Space** | O(1) | O(n) |
| **Readability** | Medium | High |
| **Stack Overflow Risk** | No | Yes (large lists) |
| **Interview Preference** | ⭐⭐⭐ | ⭐⭐ |

---

## 🧠 Key Insights & Tips

### Pointer Manipulation
- **Visualize arrows**: Draw pointers as arrows between nodes
- **Save references**: Always store `next` before breaking connections
- **Work step-by-step**: Don't try to reverse multiple pointers at once

### Common Mistakes
- ❌ **Losing references**: Forgetting to save `next_node`
- ❌ **Wrong order**: Updating pointers in incorrect sequence  
- ❌ **Edge cases**: Not handling empty list or single node

### Memory Aid
**"Save, Reverse, Move"**
1. **Save** the next node
2. **Reverse** current pointer  
3. **Move** both pointers forward

---

## 🔍 Edge Cases to Test

### Essential Test Cases
```python
# Empty list
head = None → return None

# Single node  
head = [1] → return [1]

# Two nodes
head = [1,2] → return [2,1]

# Multiple nodes
head = [1,2,3,4,5] → return [5,4,3,2,1]
```

---

## 🎯 Interview Strategy

### What to Say
1. **"I'll use three pointers to reverse connections in-place"**
2. **"Let me trace through a small example first"**
3. **"I need to save the next reference before breaking the link"**

### Follow-up Questions
- **"Can you do it recursively?"** → Show recursive approach
- **"What if it's a doubly linked list?"** → Swap next/prev pointers
- **"Reverse only part of the list?"** → Modify start/end conditions

### Time Management
- **2-3 minutes**: Explain approach and draw example
- **5-7 minutes**: Code iterative solution
- **2-3 minutes**: Test with edge cases
- **Optional**: Show recursive if time permits

---

## 💡 Related Problems

### Extensions
- **Reverse Nodes in k-Groups**: Reverse every k consecutive nodes
- **Reverse Between Positions**: Reverse sublist from position m to n
- **Palindrome Check**: Use reversal to compare first/second half

### Pattern Recognition
**When you see**: "Reverse", "Backward traversal", "Flip direction"
**Think**: Linked list reversal technique
