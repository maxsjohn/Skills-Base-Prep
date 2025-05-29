### **Construct Binary Tree from Inorder & Postorder Traversal**

**Problem**: Build binary tree from `inorder` (LNR) and `postorder` (LRN) traversals.

**Core Insight**:

- **Postorder root**: Last element = current root

- **Inorder split**: Root divides array into left/right subtrees

---

### **Solution Evolution**

#### **1. Base Approach (Naive)**

def buildTree(inorder, postorder):

if not inorder:

return None

root_val = postorder.pop()

root = TreeNode(root_val)

split = inorder.index(root_val)  # O(n) scan per node!

root.right = buildTree(inorder[split+1:], postorder)  # Right first!

root.left = buildTree(inorder[:split], postorder)

return root

**Issues**:

- ❌ **O(n²) time**: Repeated `inorder.index()` scans

- ❌ **Slicing overhead**: Creates new lists per recursive call

- ❌ **Fragile**: Duplicate values break `.index()`

---

#### **2. Optimized Approach (Hash Map)**

def buildTree(inorder, postorder):

idx_map = {val: i for i, val in enumerate(inorder)}  # O(n) one-time map

def build(left, right):

if left > right: return None

root_val = postorder.pop()

root = TreeNode(root_val)

split = idx_map[root_val]  # O(1) lookup

root.right = build(split+1, right)  # Critical: right first

root.left = build(left, split-1)

return root

return build(0, len(inorder)-1)

**Optimizations**:

- ✅ **O(1) lookups**: Hash map replaces slow `.index()` scans

- ✅ **No slicing**: Uses index pointers only

- ✅ **Handles duplicates**: Map stores last occurrence (adjust if needed)

---

### **Key Mistakes & Fixes**

1. **Subtree Order**:

- ❌ Built left subtree first → **Wrong root assignment**

- ✅ **Always build right subtree first** (postorder pop order)

2. **Index Boundaries**:

- ❌ `[left, split]` → Includes root → **Infinite recursion**

- ✅ **Exclude root**:

- Left: `[left, split-1]`

- Right: `[split+1, right]`

3. **Base Approach Trap**:

- ❌ Using `.index()` for every root → **O(n²) time**

- ✅ **Preprocess map** once → O(n) total time

---

### **Why Optimized > Base**

| **Metric**       | **Base Approach**       | **Optimized Approach** |

|-------------------|-------------------------|------------------------|

| **Time**          | O(n²)                   | **O(n)**               |

| **Space**         | O(n²) (call stack + slicing) | O(n) (map + stack) |

| **Lookup Method** | `inorder.index()` (O(n)) | **Hash map (O(1))**   |

> 💡 **Map Advantage**: Preprocessing map reduces per-node O(n) scans → O(1) lookups

---

### **Key Takeaways**

1. **Order Matters**:

- Postorder = [Left, Right, **Root**] → Build **right before left**

- Preorder = [**Root**, Left, Right] → Build left before right

2. **Pattern**:

root = postorder.pop()

right_subtree = build(split_index+1, right)  # First!

left_subtree = build(left, split_index-1)    # Second!

3. **Optimize Immediately**:

- **Always use hash map** for O(1) lookups in tree construction problems

- Avoid list slicing - use index pointers instead

> ⚠️ **Critical Insight**: Building left subtree first with postorder uses **left subtree's root** for right subtree → **Incorrect tree**

Note: The above markdown uses 4-space indentation for code blocks. However, note that the table might have issues in some viewers? But that's standard markdown.
