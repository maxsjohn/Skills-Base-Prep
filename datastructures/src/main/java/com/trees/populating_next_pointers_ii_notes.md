# DSA Problem Notes Template

## Problem: Populating Next Right Pointers in Each Node II #117
**Difficulty:** Medium | **Pattern:** Tree Traversal, Level Order | **⭐ Follow-up to #116**

---

### 🎯 **Core Insight**
*Same "zipper" concept as #116, but use helper function + separate tracking of prev/leftmost to handle irregular tree structure*

---

### 📋 **Problem Breakdown**
- **Input:** Any binary tree (not necessarily perfect)
- **Output:** Same tree with next pointers populated
- **Constraints:** Tree can have missing nodes at any level

---

### 🔄 **Solution Evolution**

#### ❌ **Direct Copy from #116 fails because:**
- Can't assume leftmost child exists for next level
- Can't assume every node has both children  
- Need dynamic leftmost tracking instead of simple `.left` navigation

#### Approach 1: Level Order Traversal (Queue)
- **Time:** O(n) | **Space:** O(n)
- **Key Idea:** Same BFS approach works for any tree
- **Why suboptimal:** Still O(n) space

#### Approach 2: Helper Function + Pointer Tracking  
- **Time:** O(n) | **Space:** O(1)
- **Key Optimization:** processChild() handles linking logic + dynamic leftmost detection

---

### 🧠 **Algorithm Pseudocode (Simplified)**
```
Think of building next level like a chain:
[prev] -> [new child] -> [next child] -> ...

For each level:
1. Start at leftmost node of current level
2. Go through each node in current level (using next pointers)
3. For each node, look at its children:
   
   When we find a child:
   - If it's the FIRST child we've seen → Remember it (new leftmost)
   - If it's NOT the first → Link previous child to this child
   - Update: this child becomes the "previous" for next child

4. Move to next level (use the leftmost we remembered)

Detailed:
leftmost = root
while leftmost exists:
  prev = None (no children linked yet)
  curr = leftmost (start traversing current level)
  leftmost = None (will find new leftmost for next level)
  
  while curr exists:
    if curr.left exists:
      if prev: prev.next = curr.left  # Link to chain
      else: leftmost = curr.left      # First child = new leftmost
      prev = curr.left                # Update prev
    
    if curr.right exists:
      if prev: prev.next = curr.right
      else: leftmost = curr.right
      prev = curr.right
    
    curr = curr.next  # Move to next node in current level
```

---

### 🔑 **Key Components**
- **Base Case:** No more levels (leftmost becomes None)
- **State/Variables:** 
  - `leftmost` → "Where does next level start?" (changes each level)
  - `curr` → "Which node am I looking at now?" (moves within level)
  - `prev` → "What's the last child I connected?" (builds the chain)
- **The Chain Building Logic:**
  - First child found → becomes `leftmost` of next level
  - Subsequent children → get linked to `prev` to form chain
  - Each child becomes new `prev` for next connection
- **Edge Cases:** Nodes with no children (skip), only left child, only right child

---

### 💡 **Building on #116 + Learning from Mistakes**
- **What stayed same:** Use next pointers to traverse each level horizontally
- **What changed:** Can't assume children exist, so need careful checking
- **New challenge:** Dynamically build next level chain while traversing current level
- **Key insight:** Track TWO things separately:
  - `leftmost` = "Where does next level start?" 
  - `prev` = "What's the last child I linked?"
- **Mental Model:** Like threading beads on a string - each child becomes next bead

---

### 🔗 **Pattern Recognition**
- **From #116:** Next pointer traversal technique
- **New technique:** Helper function to encapsulate conditional linking
- **Pointer management:** Separate tracking of `prev` (linking) vs `leftmost` (level navigation)
- **Similar pattern:** Two-pointer technique with conditional updates

---

### 🔗 **Related Problems**
- #116 Populating Next Right Pointers I (foundation)
- #102 Binary Tree Level Order Traversal  
- #114 Flatten Binary Tree to Linked List (similar pointer manipulation)

---

**Date:** [06/01/2025] | **Review:** [ ] Week 1 [ ] Month 1 [ ] Month 3