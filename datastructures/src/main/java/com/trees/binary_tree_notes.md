# Binary Trees - DSA Interview Guide

## Core Concepts

### Tree Node Structure:
```python
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
```

### Key Properties:
- **Each node has at most 2 children** (left and right)
- **Root**: Top node (no parent)
- **Leaf**: Node with no children
- **Height**: Longest path from root to leaf
- **Depth**: Distance from root to a node
- **Level**: All nodes at same depth

## Types of Binary Trees

### 1. Complete Binary Tree:
- All levels filled except possibly the last
- Last level filled from left to right
- Used in **heaps**

### 2. Full Binary Tree:
- Every node has either 0 or 2 children
- No node has exactly 1 child

### 3. Perfect Binary Tree:
- All internal nodes have 2 children
- All leaves at same level
- Has exactly 2^h - 1 nodes (h = height)

### 4. Binary Search Tree (BST):
- Left subtree values < root value
- Right subtree values > root value
- Enables O(log n) search in balanced trees

### 5. Balanced Binary Tree:
- Height difference between left and right subtrees ≤ 1
- Examples: AVL trees, Red-Black trees

## Essential Traversal Patterns

### 1. Depth-First Search (DFS):

#### Inorder (Left → Root → Right):
```python
def inorder(root):
    if not root:
        return []
    return inorder(root.left) + [root.val] + inorder(root.right)

# Iterative version
def inorder_iterative(root):
    result, stack = [], []
    current = root
    
    while stack or current:
        while current:
            stack.append(current)
            current = current.left
        current = stack.pop()
        result.append(current.val)
        current = current.right
    
    return result
```

#### Preorder (Root → Left → Right):
```python
def preorder(root):
    if not root:
        return []
    return [root.val] + preorder(root.left) + preorder(root.right)

# Iterative version
def preorder_iterative(root):
    if not root:
        return []
    
    result, stack = [], [root]
    while stack:
        node = stack.pop()
        result.append(node.val)
        if node.right:  # Push right first
            stack.append(node.right)
        if node.left:
            stack.append(node.left)
    
    return result
```

#### Postorder (Left → Right → Root):
```python
def postorder(root):
    if not root:
        return []
    return postorder(root.left) + postorder(root.right) + [root.val]

# Iterative version (trickier)
def postorder_iterative(root):
    if not root:
        return []
    
    result, stack = [], [root]
    while stack:
        node = stack.pop()
        result.append(node.val)
        if node.left:
            stack.append(node.left)
        if node.right:
            stack.append(node.right)
    
    return result[::-1]  # Reverse the result
```

### 2. Breadth-First Search (BFS) - Level Order:
```python
from collections import deque

def level_order(root):
    if not root:
        return []
    
    result, queue = [], deque([root])
    while queue:
        node = queue.popleft()
        result.append(node.val)
        
        if node.left:
            queue.append(node.left)
        if node.right:
            queue.append(node.right)
    
    return result

# Level by level
def level_order_levels(root):
    if not root:
        return []
    
    result, queue = [], deque([root])
    while queue:
        level_size = len(queue)
        level = []
        
        for _ in range(level_size):
            node = queue.popleft()
            level.append(node.val)
            
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        
        result.append(level)
    
    return result
```

## Common Interview Patterns

### 1. Tree Properties & Validation:

#### Maximum Depth:
```python
def max_depth(root):
    if not root:
        return 0
    return 1 + max(max_depth(root.left), max_depth(root.right))
```

#### Check if Balanced:
```python
def is_balanced(root):
    def height(node):
        if not node:
            return 0
        
        left_height = height(node.left)
        if left_height == -1:
            return -1
        
        right_height = height(node.right)
        if right_height == -1:
            return -1
        
        if abs(left_height - right_height) > 1:
            return -1
        
        return 1 + max(left_height, right_height)
    
    return height(root) != -1
```

#### Validate BST:
```python
def is_valid_bst(root):
    def validate(node, min_val, max_val):
        if not node:
            return True
        
        if node.val <= min_val or node.val >= max_val:
            return False
        
        return (validate(node.left, min_val, node.val) and 
                validate(node.right, node.val, max_val))
    
    return validate(root, float('-inf'), float('inf'))
```

### 2. Path & Sum Problems:

#### Path Sum:
```python
def has_path_sum(root, target_sum):
    if not root:
        return False
    
    if not root.left and not root.right:  # Leaf node
        return root.val == target_sum
    
    remaining = target_sum - root.val
    return (has_path_sum(root.left, remaining) or 
            has_path_sum(root.right, remaining))
```

#### All Root-to-Leaf Paths:
```python
def binary_tree_paths(root):
    if not root:
        return []
    
    paths = []
    
    def dfs(node, path):
        if not node.left and not node.right:  # Leaf
            paths.append(path + str(node.val))
            return
        
        if node.left:
            dfs(node.left, path + str(node.val) + "->")
        if node.right:
            dfs(node.right, path + str(node.val) + "->")
    
    dfs(root, "")
    return paths
```

#### Maximum Path Sum:
```python
def max_path_sum(root):
    max_sum = float('-inf')
    
    def max_gain(node):
        nonlocal max_sum
        if not node:
            return 0
        
        # Max gain from left and right subtrees
        left_gain = max(max_gain(node.left), 0)
        right_gain = max(max_gain(node.right), 0)
        
        # Current path sum including this node
        current_path_sum = node.val + left_gain + right_gain
        max_sum = max(max_sum, current_path_sum)
        
        # Return max gain from this node
        return node.val + max(left_gain, right_gain)
    
    max_gain(root)
    return max_sum
```

### 3. Tree Construction:

#### Build Tree from Preorder & Inorder:
```python
def build_tree_pre_in(preorder, inorder):
    if not preorder or not inorder:
        return None
    
    root = TreeNode(preorder[0])
    root_idx = inorder.index(preorder[0])
    
    root.left = build_tree_pre_in(preorder[1:root_idx+1], 
                                  inorder[:root_idx])
    root.right = build_tree_pre_in(preorder[root_idx+1:], 
                                   inorder[root_idx+1:])
    
    return root
```

#### Build Tree from Postorder & Inorder:
```python
def build_tree_post_in(inorder, postorder):
    if not inorder or not postorder:
        return None
    
    root = TreeNode(postorder[-1])
    root_idx = inorder.index(postorder[-1])
    
    root.left = build_tree_post_in(inorder[:root_idx], 
                                   postorder[:root_idx])
    root.right = build_tree_post_in(inorder[root_idx+1:], 
                                    postorder[root_idx:-1])
    
    return root
```

### 4. Tree Modifications:

#### Invert Binary Tree:
```python
def invert_tree(root):
    if not root:
        return None
    
    root.left, root.right = root.right, root.left
    invert_tree(root.left)
    invert_tree(root.right)
    
    return root
```

#### Flatten to Linked List:
```python
def flatten(root):
    if not root:
        return
    
    flatten(root.left)
    flatten(root.right)
    
    # Store right subtree
    right_subtree = root.right
    
    # Move left subtree to right
    root.right = root.left
    root.left = None
    
    # Find the rightmost node and attach original right subtree
    current = root
    while current.right:
        current = current.right
    current.right = right_subtree
```

## Advanced Patterns

### 1. Lowest Common Ancestor:
```python
def lowest_common_ancestor(root, p, q):
    if not root or root == p or root == q:
        return root
    
    left = lowest_common_ancestor(root.left, p, q)
    right = lowest_common_ancestor(root.right, p, q)
    
    if left and right:
        return root
    return left or right
```

### 2. Serialize & Deserialize:
```python
def serialize(root):
    def preorder(node):
        if not node:
            return "null,"
        return str(node.val) + "," + preorder(node.left) + preorder(node.right)
    
    return preorder(root)

def deserialize(data):
    def build_tree():
        val = next(vals)
        if val == "null":
            return None
        
        node = TreeNode(int(val))
        node.left = build_tree()
        node.right = build_tree()
        return node
    
    vals = iter(data.split(","))
    return build_tree()
```

### 3. Morris Traversal (O(1) Space):
```python
def morris_inorder(root):
    result = []
    current = root
    
    while current:
        if not current.left:
            result.append(current.val)
            current = current.right
        else:
            # Find inorder predecessor
            predecessor = current.left
            while predecessor.right and predecessor.right != current:
                predecessor = predecessor.right
            
            if not predecessor.right:
                # Make current the right child of its predecessor
                predecessor.right = current
                current = current.left
            else:
                # Revert the changes
                predecessor.right = None
                result.append(current.val)
                current = current.right
    
    return result
```

## BST Specific Operations

### Search:
```python
def search_bst(root, val):
    if not root or root.val == val:
        return root
    
    if val < root.val:
        return search_bst(root.left, val)
    else:
        return search_bst(root.right, val)
```

### Insert:
```python
def insert_into_bst(root, val):
    if not root:
        return TreeNode(val)
    
    if val < root.val:
        root.left = insert_into_bst(root.left, val)
    else:
        root.right = insert_into_bst(root.right, val)
    
    return root
```

### Delete:
```python
def delete_node(root, key):
    if not root:
        return root
    
    if key < root.val:
        root.left = delete_node(root.left, key)
    elif key > root.val:
        root.right = delete_node(root.right, key)
    else:
        # Node to be deleted found
        if not root.left:
            return root.right
        elif not root.right:
            return root.left
        
        # Node has two children - find inorder successor
        min_node = root.right
        while min_node.left:
            min_node = min_node.left
        
        root.val = min_node.val
        root.right = delete_node(root.right, min_node.val)
    
    return root
```

## Time & Space Complexities

| Operation | Average Case | Worst Case | Space |
|-----------|-------------|------------|-------|
| **Traversal** | O(n) | O(n) | O(h) recursive stack |
| **Search (BST)** | O(log n) | O(n) | O(h) |
| **Insert (BST)** | O(log n) | O(n) | O(h) |
| **Delete (BST)** | O(log n) | O(n) | O(h) |
| **Height Calculation** | O(n) | O(n) | O(h) |

*Where h = height of tree, n = number of nodes*

## Interview Tips & Tricks

### 1. Recursive Pattern Template:
```python
def solve_tree_problem(root):
    # Base case
    if not root:
        return base_value
    
    # Recursive calls
    left_result = solve_tree_problem(root.left)
    right_result = solve_tree_problem(root.right)
    
    # Combine results
    return combine(root.val, left_result, right_result)
```

### 2. Level Order with Queue:
```python
from collections import deque

def level_order_template(root):
    if not root:
        return []
    
    result = []
    queue = deque([root])
    
    while queue:
        level_size = len(queue)
        current_level = []
        
        for _ in range(level_size):
            node = queue.popleft()
            current_level.append(node.val)
            
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        
        result.append(current_level)
    
    return result
```

### 3. Path Tracking Pattern:
```python
def path_problems(root, target):
    paths = []
    
    def dfs(node, current_path, current_sum):
        if not node:
            return
        
        current_path.append(node.val)
        current_sum += node.val
        
        # Check if leaf and meets condition
        if not node.left and not node.right and current_sum == target:
            paths.append(current_path[:])  # Make a copy
        
        # Continue DFS
        dfs(node.left, current_path, current_sum)
        dfs(node.right, current_path, current_sum)
        
        # Backtrack
        current_path.pop()
    
    dfs(root, [], 0)
    return paths
```

## Common Gotchas & Edge Cases
- **Empty tree**: Always check `if not root`
- **Single node tree**: Root with no children
- **Unbalanced trees**: Can degrade to O(n) operations
- **Integer overflow**: Be careful with sum calculations
- **Reference vs value**: When modifying tree structure
- **Backtracking**: Remember to undo changes in path problems

## Memory Tricks
- **"In-order gives sorted sequence"** for BSTs
- **"Pre-order for tree construction"** (root first)
- **"Post-order for tree deletion"** (children first)
- **"Level-order uses queue"** (BFS)
- **"DFS uses stack"** (recursion or explicit stack)
- **"BST property: left < root < right"**

---

**Master these patterns and you'll handle 90% of binary tree interview questions!**
