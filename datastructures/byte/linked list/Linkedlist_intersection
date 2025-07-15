# Linked List Intersection - Quick Revision Notes

## 🎯 Problem Statement
**Return the node where two singly linked lists intersect. If no intersection, return null.**

Example:
```
A: 1 → 3 → 4 → 8 → 7 → 2
B: 6 → 4 → 8 → 7 → 2
Output: Node 8
```

---

## 💡 Intuition

### What is Intersection?
- **Intersection occurs when two linked lists converge at a shared node**
- **From that point onwards, they share ALL subsequent nodes**
- **Note**: Has nothing to do with node VALUES

### Naive Approach: Hash Set
- Traverse first list → store each node in hash set
- Traverse second list → find first node that exists in hash set
- **Problem**: Uses extra space. Can we do O(1) space?

### Key Insight: Equal Length Problem
- **Problem is easier if both lists have equal length**
- **Why?** Intersection node at same position from both heads
- **When iterating equal-length lists**: reach intersection simultaneously

### Brilliant Observation
**While list A and list B may have different lengths:**
- **`list A → list B` has SAME length as `list B → list A`**
- **These combined lists also share same tail nodes**

---

## 🔄 Algorithm Logic

### Setup Equal-Length Scenario
```
Original lists (different lengths):
A: [nodes] → [shared tail]
B: [nodes] → [shared tail]

Combined (same lengths):
A→B: [A nodes] → [shared] → [B nodes] → [shared]
B→A: [B nodes] → [shared] → [A nodes] → [shared]
```

### Two Pointer Traversal
- **Traverse both combined lists with two pointers**
- **Stop when pointers point to same node** = intersection
- **If no intersection**: both pointers reach null simultaneously

---

## 🚀 Traversing Combined Lists

### Key Technique
**Don't actually connect the lists!**

Instead:
1. **Traverse list A** normally
2. **Upon reaching end of A** → continue from head of B
3. **Same for list B** → continue from head of A

```
Traverse A → B:
1. traverse list A: head_A → ... → end
2. traverse list B: head_B → ... → end

Traverse B → A:
1. traverse list B: head_B → ... → end  
2. traverse list A: head_A → ... → end
```

---

## 💻 Implementation
```python
def linked_list_intersection(head_A, head_B):
    ptr_A, ptr_B = head_A, head_B
    
    # Traverse until they meet
    while ptr_A != ptr_B:
        # Traverse A → B: A first, then B
        ptr_A = ptr_A.next if ptr_A else head_B
        
        # Traverse B → A: B first, then A  
        ptr_B = ptr_B.next if ptr_B else head_A
    
    # Either intersection node or both null
    return ptr_A
```

---

## 📊 Complexity Analysis
- **Time**: O(n + m) where n, m = lengths of lists A and B
  - Pointers linearly traverse both lists sequentially
- **Space**: O(1) constant space
