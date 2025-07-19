# Linked List Midpoint - Revision Notes

## 🎯 Problem
Find the middle node of a singly linked list using **fast and slow pointers**.

**Examples:**
```
Odd length:  1→2→4→7→3    return node 4 (middle)
Even length: 1→2→4→7      return node 4 (second middle)
```

## 💡 Core Strategy
**Fast and Slow Pointer Technique:**
- **Slow pointer**: Moves 1 step per iteration
- **Fast pointer**: Moves 2 steps per iteration
- **When fast reaches end**: Slow is at middle

## ⚡ Why This Works
```
Fast moves 2x speed of slow
When fast travels full distance, slow travels half distance
Half distance = middle position!
```

## 📝 Standard Implementation (Second Middle for Even)
```python
def findMiddle(head):
    slow = fast = head
    
    # Stop when fast reaches end
    while fast and fast.next:
        slow = slow.next        # Move 1 step
        fast = fast.next.next   # Move 2 steps
    
    return slow  # Points to middle node

# Example walkthrough:
# List: 1→2→3→4→5
# Step 0: slow=1, fast=1
# Step 1: slow=2, fast=3  
# Step 2: slow=3, fast=5
# Step 3: slow=3, fast=null (stop)
# Result: slow points to node 3 (middle)
```

## 🔄 Variation: First Middle for Even Length
```python
def findFirstMiddle(head):
    slow = fast = head
    
    # Stop when fast.next.next would be null
    while fast.next and fast.next.next:
        slow = slow.next
        fast = fast.next.next
    
    return slow  # Points to first middle node

# Example walkthrough:
# List: 1→2→3→4
# Step 0: slow=1, fast=1
# Step 1: slow=2, fast=3
# Step 2: fast.next.next=null (stop)
# Result: slow points to node 2 (first middle)
```

## 🔑 Key Differences

| List Length | Standard (Second Middle) | First Middle Variation |
|-------------|-------------------------|----------------------|
| **Odd (5 nodes)** | Returns node 3 | Returns node 3 |
| **Even (4 nodes)** | Returns node 3 | Returns node 2 |

### **Loop Conditions:**
```python
# Second middle (standard):
while fast and fast.next:

# First middle:
while fast.next and fast.next.next:
```

## 🚨 Critical Implementation Details

### **Null Pointer Safety:**
```python
# Standard version:
while fast and fast.next:  # Prevents fast.next.next error

# First middle version:  
while fast.next and fast.next.next:  # Prevents null access
```

### **Initialization:**
```python
slow = fast = head  # Both start at head
```

## 📊 Step-by-Step Examples

### **Odd Length (5 nodes): 1→2→3→4→5**
```
Standard approach:
Step 0: slow=1, fast=1
Step 1: slow=2, fast=3
Step 2: slow=3, fast=5  
Step 3: fast.next=null → stop
Result: slow=3 ✅
```

### **Even Length (4 nodes): 1→2→3→4**
```
Standard approach (second middle):
Step 0: slow=1, fast=1
Step 1: slow=2, fast=3
Step 2: slow=3, fast=null → stop  
Result: slow=3 ✅

First middle approach:
Step 0: slow=1, fast=1
Step 1: slow=2, fast=3
Step 2: fast.next.next=null → stop
Result: slow=2 ✅
```

## 📈 Complexity Analysis
- **Time**: O(n) - Single pass through list
- **Space**: O(1) - Only using two pointers

## 🔥 Alternative Approach (Less Optimal)
```python
def findMiddleTwoPass(head):
    # Pass 1: Count nodes
    length = 0
    curr = head
    while curr:
        length += 1
        curr = curr.next
    
    # Pass 2: Go to middle
    curr = head
    for i in range(length // 2):
        curr = curr.next
    
    return curr
# Time: O(n), but requires two passes
```

## 💭 Mental Models

### **"Half Speed, Half Distance":**
```
Think of slow pointer as going half the speed
When fast completes the journey, slow is halfway
```

### **"Race Track" Analogy:**
```
Two runners: one runs 2x faster than the other
When fast runner finishes, slow runner is at midpoint
```

## 🎪 Quick Memory Tricks
- **"Fast and Slow for Middle"** - Classic fast/slow application
- **"Fast to End, Slow to Middle"** - Core relationship
- **"Even Length Choice"** - Second vs first middle node
- **"One Pass Wonder"** - More efficient than two-pass counting

## 🏆 Interview Tips

### **Clarify Requirements:**
```
"For even length lists, should I return the first or second middle node?"
Common: Second middle (most implementations default to this)
```

### **Explain the Technique:**
```
"I'll use fast and slow pointers. Fast moves twice as fast as slow.
When fast reaches the end, slow will be at the middle."
```

### **Handle Edge Cases:**
```python
# Single node
1 → null: Returns node 1 ✅

# Two nodes  
1 → 2 → null: Returns node 2 (second middle) ✅
```

### **Show Both Versions:**
Start with standard, then mention the variation if asked.

## ⚠️ Common Mistakes
❌ **Wrong loop condition** → Null pointer exceptions  
❌ **Not clarifying even-length behavior** → Ambiguous requirements  
❌ **Using two passes** → Missing the optimization opportunity  
❌ **Incorrect initialization** → Starting pointers at wrong positions

## 🔧 Template Code
```python
def findMiddle(head):
    # Handle edge case
    if not head:
        return None
    
    # Initialize both pointers
    slow = fast = head
    
    # Choose appropriate condition:
    # For second middle: while fast and fast.next
    # For first middle: while fast.next and fast.next.next
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    
    return slow
```
