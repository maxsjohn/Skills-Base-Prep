# DSA Problem Notes Template

## Problem: Serialize and Deserialize Binary Tree #297
**Difficulty:** Hard | **Pattern:** Tree Traversal, String Processing

---

### 🎯 **Core Insight**
*Use preorder traversal for serialization (root first) so deserialization can reconstruct tree in same order without needing complex parsing*

---

### 📋 **Problem Breakdown**
- **Input:** Binary tree root (any structure)
- **Output:** String representation that can be converted back to identical tree
- **Constraints:** Must handle all tree shapes including empty, single node, skewed trees

---

### 🔄 **Solution Evolution**

#### Approach 1: Level Order (BFS) Serialization
- **Time:** O(n) | **Space:** O(n)
- **Key Idea:** Serialize level by level, include nulls for missing nodes
- **Why suboptimal:** Lots of trailing nulls for unbalanced trees

#### Approach 2: Preorder (DFS) Serialization
- **Time:** O(n) | **Space:** O(n)
- **Key Optimization:** Root-first traversal enables simple recursive reconstruction

---

### 🧠 **Algorithm Pseudocode (Simplified)**
```
Serialize (Preorder DFS):
1. If node is null → add "None" to result
2. If node exists → add node.val to result
3. Recursively serialize left subtree
4. Recursively serialize right subtree
5. Join all values with delimiter (comma)

Deserialize (Preorder reconstruction):
1. Split string by delimiter to get list
2. Use list as queue (pop from front)
3. If next value is "None" → return None
4. If next value is number → create node with that value
5. Recursively build left subtree (pop from queue)
6. Recursively build right subtree (pop from queue)
7. Return constructed node
```

---

### 🔑 **Key Components**
- **Base Case:** null node → "None" string, "None" string → null node  
- **State/Variables:** 
  - `result[]` (build serialized string)
  - `nodes[]` (queue for deserialization)
- **Traversal Order:** Preorder (root → left → right) for both operations
- **Edge Cases:** Empty tree, single node, deeply unbalanced tree

---

### 💡 **Gotchas & Tips**
- **❌ CRITICAL:** Always return consistent types (string from serialize, even for empty tree)
- **Delimiter choice:** Avoid characters that appear in node values
- **Queue management:** Use `pop(0)` to maintain preorder sequence during deserialization
- **Round-trip testing:** Always test serialize(tree) → deserialize(result) → matches original

---

### 🔗 **Pattern Recognition**
- **Preorder traversal:** Root processing before children enables reconstruction
- **String parsing:** Split-and-process pattern for structured data
- **Recursive reconstruction:** Use recursion to rebuild nested structures
- **Similar problems:** File system serialization, expression tree parsing

---

### 🔗 **Related Problems**
- #449 Serialize and Deserialize BST (can optimize using BST properties)
- #105 Construct Binary Tree from Preorder and Inorder Traversal
- #428 Serialize and Deserialize N-ary Tree

---

**Date:** [06/01/2025] | **Review:** [ ] Week 1 [ ] Month 1 [ ] Month 3