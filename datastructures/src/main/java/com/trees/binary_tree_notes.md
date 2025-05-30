**Construct Binary Tree from Preorder and Inorder Traversal**
**Similar to Inorder+Postorder but with key differences**:
1. **Root extraction**: `preorder.pop(0)` (first element) instead of last
2. **Build order**: Left subtree before right (reverse of postorder case)
**Mistakes made**:
* ❌ Used `popleft()` (doesn't exist for lists) → **Fix**: `pop(0)`
* ❌ Passed wrong bounds: `node.left = helper(left, split)` → **Fix**: `split-1`
* ❌ Visualization gap: Forgot inorder root splits array into exact halves
**Key Insight**:
```python
node = TreeNode(preorder.pop(0))  # First element = root
node.left = helper(left, split-1) # Exclude root from left
node.right = helper(split+1, right) # Exclude root from right
```

**Pattern**:
1. Preorder = [**Root**, Left, Right] → Build left before right
2. Always verify split points on paper before coding