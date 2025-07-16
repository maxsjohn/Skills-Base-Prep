# Remove Kth Last Node - Quick Revision Notes

## 🎯 Problem Statement
**Return head of linked list after removing kth node from the end**

Example:
```
Input:  1 → 2 → 4 → 7 → 3,  k = 2
Output: 1 → 2 → 4 → 3  (removed node 7)
```

---

## 💡 Intuition

### Two Objectives
1. **Find position of kth last node**
2. **Remove this node**

### Node Removal Requirement
**To remove node b**: Need access to **preceding node a**
```
a → b → c
```
**Algorithm**: `prev.next = prev.next.next`
**Result**: `a → c` (node b removed)

**Key Point**: Need node **directly before** kth last node

---

## 🔄 Naive Solution
1. **First pass**: Traverse to get length (n)
2. **Second pass**: Navigate `n - k - 1` steps to reach node before kth last
3. **Problem**: Uses two for-loops

---

## 🎯 Two Pointer Approach

### Challenge
- **Single traversal**: Hard to tell distance from final node
- **Only know when reaching final node** (next = null)

### Solution: Leader-Trailer Strategy
- **Leader pointer**: Advances first
- **Trailer pointer**: Follows behind
- **Goal**: When leader reaches end, trailer at node before kth last

### Key Insight
**Leader should be k nodes ahead of trailer** when leader reaches last node

---

## ⚠️ Edge Case: Removing Head
**Problem**: No node before head to redirect pointer

**Solution**: Create **dummy node**
```
dummy → head → rest of list
```
Start traversal from dummy node

---

## 🚀 Algorithm Steps

### Step 1: Setup
```python
dummy = ListNode(-1)
dummy.next = head
trailer = leader = dummy
```

### Step 2: Advance Leader k Steps
```python
for _ in range(k):
    leader = leader.next
    if not leader:  # k larger than list length
        return head
```

### Step 3: Move Both Until Leader Reaches End
```python
while leader.next:
    leader = leader.next
    trailer = trailer.next
```

### Step 4: Remove Node
```python
trailer.next = trailer.next.next
return dummy.next
```

---

## 💻 Complete Implementation
```python
def remove_kth_last_node(head, k):
    # Dummy node for edge case handling
    dummy = ListNode(-1)
    dummy.next = head
    trailer = leader = dummy
    
    # Advance leader k steps ahead
    for _ in range(k):
        leader = leader.next
        if not leader:
            return head
    
    # Move both until leader reaches end
    while leader.next:
        leader = leader.next
        trailer = trailer.next
    
    # Remove kth node from end
    trailer.next = trailer.next.next
    return dummy.next
```

---

## 📊 Complexity Analysis
- **Time**: O(n) 
  - First traverses at most n nodes
  - Two pointers traverse at most once each
- **Space**: O(1) - Only uses constant extra space

---

## 🧠 Key Points

### Why Dummy Node?
- **Handles edge case** of removing head node
- **Provides node before head** for pointer manipulation

### Leader-Trailer Distance
- **Leader exactly k nodes ahead** of trailer
- **When leader at end**: trailer at perfect position for removal

### Single Pass Solution
- **Avoids two traversals** of naive approach
- **Elegant use of two pointers** to maintain relative positioning
