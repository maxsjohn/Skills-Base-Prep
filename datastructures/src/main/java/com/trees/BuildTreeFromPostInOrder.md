**Construct Binary Tree from Inorder & Postorder Traversal**

**Key Insight**: 
- Postorder = [Left, Right, **Root**] → Last element = root
- Inorder splits at root → Left/Right subtrees

**Critical Pattern**:
```python
def buildTree(inorder, postorder):
    idx_map = {val: i for i, val in enumerate(inorder)}
    
    def build(left, right):
        if left > right: return None
        root_val = postorder.pop()  # Last = root
        root = TreeNode(root_val)
        split = idx_map[root_val]   # O(1) lookup
        
        root.right = build(split+1, right)  # RIGHT FIRST!
        root.left = build(left, split-1)    # Then left
        return root
    
    return build(0, len(inorder)-1)
```

**Common Mistakes**:
- ❌ Built left first → Uses wrong root for right subtree
- ❌ Used `inorder.index()` → O(n²) time complexity  
- ❌ Wrong bounds: `[left, split]` → **Fix**: `split-1`

**Why Right First?**
Postorder pops from end: [Left, Right, **Root**]
- Pop root → Remaining: [Left, Right]  
- Next pop gets Right's root (correct)
- If left first: Next pop gets Left's root for right subtree (wrong!)

**Memory**:
- **Hash map**: O(1) root lookups vs O(n) scanning
- **Build order**: RIGHT → LEFT (opposite of preorder)
- **Boundaries**: Always exclude root index from subtrees
