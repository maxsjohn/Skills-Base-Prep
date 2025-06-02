# Tree Problems - Recursion Notes

## Key Concept
**Trees are naturally recursive** → Most tree problems can be solved recursively

## Two Main Approaches

### 1. TOP-DOWN (Preorder-like)
- **Process current node FIRST**
- **Pass values DOWN to children**

**Template:**
```
top_down(root, params):
1. Handle null case
2. Process current node (update answer)
3. Recurse left: top_down(root.left, new_params)
4. Recurse right: top_down(root.right, new_params)
5. Return answer
```

**Example - Max Depth:**
- Pass depth as parameter
- Each node knows its depth
- Leaf nodes update global answer

### 2. BOTTOM-UP (Postorder-like)
- **Process children FIRST**
- **Build answer UP from children**

**Template:**
```
bottom_up(root):
1. Handle null case
2. left_ans = bottom_up(root.left)
3. right_ans = bottom_up(root.right)
4. return answer built from left_ans, right_ans, root.val
```

**Example - Max Depth:**
- Get max depth of left & right subtrees
- Return max(left, right) + 1

## Decision Framework

**Use TOP-DOWN when:**
- Can you determine parameters to help node know its answer?
- Can you pass these parameters to children?

**Use BOTTOM-UP when:**
- Can you calculate node's answer from children's answers?

## Quick Tips
- Top-down = "What do I need to tell my children?"
- Bottom-up = "What do my children tell me?"
- Practice is key to mastering tree recursion!