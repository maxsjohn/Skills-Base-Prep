# Fast and Slow Pointers - Revision Notes

## 🎯 Core Concept
**Two pointers moving at different speeds** through a data structure to solve problems efficiently.

**Standard Pattern:**
- **Slow pointer**: Moves 1 step per iteration
- **Fast pointer**: Moves 2 steps per iteration

## ⚡ Basic Mechanics

```python
# Standard fast/slow pointer setup
slow = fast = head

while fast and fast.next:
    slow = slow.next        # Move 1 step
    fast = fast.next.next   # Move 2 steps
```

## 🔑 Key Applications

### **1. Cycle Detection**
```python
# Floyd's Cycle Detection (Tortoise and Hare)
def hasCycle(head):
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow == fast:
            return True  # Cycle detected
    return False
```

### **2. Finding Middle of Linked List**
```python
def findMiddle(head):
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    return slow  # Points to middle when fast reaches end
```

### **3. Finding Nth from End**
```python
def nthFromEnd(head, n):
    slow = fast = head
    
    # Move fast n steps ahead
    for _ in range(n):
        fast = fast.next
    
    # Move both until fast reaches end
    while fast:
        slow = slow.next
        fast = fast.next
    
    return slow  # Points to nth from end
```

## 🧠 Why This Works

### **Cycle Detection Logic:**
- If there's a cycle, fast pointer will eventually "lap" the slow pointer
- Think of it as two runners on a circular track - faster runner catches up

### **Middle Finding Logic:**
- When fast pointer reaches end, slow pointer is at middle
- Fast moves 2x speed → covers 2x distance → slow is at halfway point

### **Distance Relationship:**
```
When fast has moved 2n steps, slow has moved n steps
This creates predictable relative positioning
```

## 🔥 Common Use Cases

| Problem Type | Fast Speed | Slow Speed | Key Insight |
|--------------|------------|------------|-------------|
| **Cycle Detection** | 2 steps | 1 step | Fast catches slow if cycle exists |
| **Find Middle** | 2 steps | 1 step | Slow at middle when fast at end |
| **Nth from End** | 1 step | 1 step | Start fast N steps ahead |
| **Palindrome Check** | 2 steps | 1 step | Find middle, then reverse second half |

## 💡 Problem Recognition Patterns

**Use Fast/Slow Pointers When:**
- ✅ Need to find middle of linked list
- ✅ Detect cycles in linked list  
- ✅ Find nth node from end
- ✅ Check if linked list is palindrome
- ✅ Find intersection point of two linked lists

**Don't Use When:**
- ❌ You have array with indices (use regular two pointers)
- ❌ Need to compare values far apart
- ❌ Problem requires backtracking

## 🚨 Critical Implementation Details

### **Null Pointer Safety:**
```python
# Always check both fast and fast.next
while fast and fast.next:
    # Safe to do fast.next.next
```

### **Edge Cases:**
```python
# Empty list
if not head: return None

# Single node
if not head.next: return head

# Two nodes - be careful with fast.next.next
```

## 🎪 Memory Tricks

### **"Tortoise and Hare"**
- **Slow = Tortoise** (steady, 1 step)
- **Fast = Hare** (quick, 2 steps)
- If hare catches tortoise → there's a cycle!

### **"Meeting Point Math"**
- Fast moves 2x speed of slow
- They meet when: `2 × slow_distance = fast_distance`

### **"Half Speed, Half Distance"**
- When fast reaches end, slow is at middle
- Fast: full journey, Slow: half journey

## 🏆 Interview Tips

### **Start with Simple Example:**
```
List: 1→2→3→4→5→null

Step 0: slow=1, fast=1
Step 1: slow=2, fast=3  
Step 2: slow=3, fast=5
Step 3: slow=4, fast=null (stop)

Result: slow points to node 3 (middle)
```

### **Explain the Speed Difference:**
- "Fast pointer acts as a scout, moving ahead faster"
- "The relative positioning gives us the information we need"
- "It's like having two runners with different speeds"

### **Common Variations:**
- Fast moves 3 steps, slow moves 1 (for different problems)
- Both start at different positions (for specific algorithms)
- Multiple fast/slow pairs (for complex problems)

## 🔄 Template Pattern

```python
def fastSlowPattern(head):
    # Handle edge cases
    if not head or not head.next:
        return head
    
    # Initialize pointers
    slow = fast = head
    
    # Main traversal
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        
        # Problem-specific logic here
        if problem_condition(slow, fast):
            return solution
    
    # Post-processing if needed
    return process_result(
