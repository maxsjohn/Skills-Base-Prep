# Linked List Cycle Detection - Revision Notes

## 🎯 Problem
Detect if a linked list contains a cycle (loop) using **O(1) space**.

**Example:**
```
0 → 1 → 2 → 3 → 4
        ↑       ↓
        ← ← ← ← ←  (cycle exists)
```

## 💡 Core Strategy
**Floyd's Cycle Detection Algorithm (Tortoise and Hare)**
- **Slow pointer**: Moves 1 step per iteration
- **Fast pointer**: Moves 2 steps per iteration
- **If cycle exists**: Fast will eventually catch up to slow
- **If no cycle**: Fast will reach null

## ⚡ Algorithm Logic

### **Why Fast Catches Slow:**
- Fast gains 1 step on slow each iteration
- Distance between them decreases by 1 each time
- Maximum steps to meet = cycle length

### **Three Cases:**
1. **Perfect Cycle**: Entire list is a cycle → pointers meet
2. **Delayed Cycle**: Linear part + cycle → pointers meet in cycle
3. **No Cycle**: Fast reaches null → no cycle detected

## 📝 Complete Implementation
```python
def hasCycle(head):
    slow = fast = head
    
    # Check both fast and fast.next to avoid null pointer errors
    while fast and fast.next:
        slow = slow.next        # Move 1 step
        fast = fast.next.next   # Move 2 steps
        
        if fast == slow:        # Pointers met = cycle found
            return True
    
    return False  # Fast reached end = no cycle
```

## 🔑 Key Implementation Details

### **Null Pointer Safety:**
```python
while fast and fast.next:  # Must check BOTH
    # Safe to do fast.next.next now
```

### **Why Check Both Conditions?**
- `fast`: Ensures fast is not null
- `fast.next`: Ensures fast.next.next won't cause error

### **Initialization:**
```python
slow = fast = head  # Both start at same position
```

## 🚨 Critical Edge Cases

### **Empty List:**
```python
head = None → fast = None → while loop doesn't execute → return False ✅
```

### **Single Node (No Cycle):**
```python
1 → null → fast.next = None → loop exits → return False ✅
```

### **Single Node (With Cycle):**
```python
1 → 1 (points to self) → fast == slow after 1 iteration → return True ✅
```

### **Two Nodes:**
```python
1 → 2 → null: No cycle
1 ⇄ 2: Cycle (both work correctly)
```

## 📊 Complexity Analysis
- **Time**: O(n) - Fast pointer will meet slow in at most n steps
- **Space**: O(1) - Only using two pointers

## 🔥 Alternative Approach (Less Optimal)
```python
def hasCycleSet(head):
    visited = set()
    curr = head
    
    while curr:
        if curr in visited:
            return True
        visited.add(curr)
        curr = curr.next
    
    return False
# Time: O(n), Space: O(n) - uses extra memory
```

## 💭 Mental Models

### **"Runners on Track" Analogy:**
```
- Slow runner: 1 lap per minute
- Fast runner: 2 laps per minute  
- On circular track: Fast will lap slow
- On straight track: Fast reaches finish first
```

### **"Distance Closing" Concept:**
```
Each iteration: distance between pointers decreases by 1
Eventually: distance becomes 0 → pointers meet
```

## 🎪 Quick Memory Tricks
- **"Tortoise and Hare"** - Classic algorithm nickname
- **"Fast catches slow in cycle"** - Core insight
- **"Null check both"** - `fast and fast.next`
- **"O(1) space winner"** - Better than hash set approach

## 🏆 Interview Tips

### **Explain the Intuition:**
```
"Imagine two runners on a track. If the track is circular, 
the faster runner will eventually lap the slower one."
```

### **Walk Through Example:**
```
List: 1→2→3→2 (cycle: 2→3→2)

Step 0: slow=1, fast=1
Step 1: slow=2, fast=3  
Step 2: slow=3, fast=2
Step 3: slow=2, fast=3
Step 4: slow=3, fast=2
...
Eventually: slow==fast → cycle detected!
```

### **Mention Edge Cases:**
- Empty list
- Single node with/without self-loop
- Linear list (no cycle)

## ⚠️ Common Mistakes
❌ **Forgetting to check `fast.next`** → Null pointer exception  
❌ **Wrong initialization** → Starting pointers at different positions  
❌ **Using hash set first** → Mention O(1) space optimization  
❌ **Not explaining why it works** → Must explain the "gaining 1 step" logic
