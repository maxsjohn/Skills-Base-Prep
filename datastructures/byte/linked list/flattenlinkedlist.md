# Flatten Multi-Level Linked List - Revision Notes

## 🎯 Problem
Flatten a multi-level linked list where nodes have `next` and `child` pointers into a single-level list.

**Structure:**
```
Node: {val, next, child}
- next: points to next node in same level
- child: points to head of child linked list (or null)
```

## 💡 Core Strategy
**Two-Pointer Approach:**
- **curr**: Traverse current level, find nodes with children
- **tail**: Always points to end of flattened list so far

**Key Insight:** Append each child list to the tail, then continue processing.

## ⚡ Algorithm Steps

1. **Initialize Pointers:**
   ```python
   tail = head
   while tail.next:    # Find initial tail
       tail = tail.next
   curr = head
   ```

2. **Process Each Node:**
   ```python
   while curr:
       if curr.child:
           # Append child list to tail
           tail.next = curr.child
           curr.child = None        # ⚠️ Important: nullify child
           
           # Advance tail to end of appended list
           while tail.next:
               tail = tail.next
       
       curr = curr.next
   ```

## 📝 Complete Implementation
```python
def flatten(head):
    if not head:
        return None
    
    # Find initial tail
    tail = head
    while tail.next:
        tail = tail.next
    
    # Process each node
    curr = head
    while curr:
        if curr.child:
            # Append child list to tail
            tail.next = curr.child
            curr.child = None  # Nullify child pointer
            
            # Move tail to end of newly appended list
            while tail.next:
                tail = tail.next
        
        curr = curr.next
    
    return head

class MultiLevelListNode:
    def __init__(self, val=0, next=None, child=None):
        self.val = val
        self.next = next
        self.child = child
```

## 🔑 Key Operations

### **Append Child to Tail:**
```python
# Connect tail to child list
tail.next = curr.child
curr.child = None  # Must nullify!

# Advance tail to end of appended list
while tail.next:
    tail = tail.next
```

### **Why Nullify Child Pointer?**
- Ensures list is fully flattened
- Prevents confusion in final structure
- Required for correct flattened list

## 🚨 Critical Details

### **Tail Management:**
- **Always keep tail at end** of current flattened list
- **Advance tail** after appending each child list
- Tail moves but curr continues sequential traversal

### **Processing Order:**
```
Level 1: 1→2→3→4
Level 2:   ↓   ↓
          5→6 7→8
Level 3:    ↓
           9→10

Result: 1→2→3→4→5→6→9→10→7→8
```

## 📊 Complexity
- **Time**: O(n) - Each node visited at most twice (once by curr, once while advancing tail)
- **Space**: O(1) - Only using two pointers

## 🔥 Visual Process

### **Step-by-Step Example:**
```
Initial: 1→2→3(child:5→6)→4
tail points to 4, curr starts at 1

curr=1: No child, curr=2
curr=2: No child, curr=3  
curr=3: Has child (5→6)
  - tail.next = 5→6  
  - Result: 1→2→3→4→5→6
  - tail advances to 6
curr=4: Continue...
```

## 💭 Mental Models

### **"Append and Advance" Pattern:**
```
1. Find child list
2. Append to tail  
3. Advance tail to new end
4. Continue traversal
```

### **"Level-by-Level Flattening":**
```
Think: "Take each child list and stick it at the end"
- Preserves order within each level
- Processes levels left-to-right, top-to-bottom
```

## 🎪 Quick Memory Tricks
- **"Two-Pointer Dance"** - curr finds, tail appends
- **"Nullify After Append"** - Always set child to None
- **"Tail Chasing"** - Keep tail at the very end
- **"Sequential Processing"** - curr moves steadily through original level

## 🏆 Interview Tips

### **Start with Simple Example:**
```
1→2(child:3→4)
Result: 1→2→3→4
```

### **Explain the Two-Pointer Strategy:**
- "curr traverses the original level sequentially"
- "tail always points to the end of our flattened result"
- "When curr finds a child, we append it to tail"

### **Handle Edge Cases:**
```python
# Empty list
if not head: return None

# No children anywhere - list already flat
# Algorithm still works correctly
```

## ⚠️ Common Mistakes
❌ **Forgetting to nullify child pointers**  
❌ **Not advancing tail after appending**  
❌ **Trying to use recursion** (leads to O(n) space)  
❌ **Confusing curr and tail roles**
