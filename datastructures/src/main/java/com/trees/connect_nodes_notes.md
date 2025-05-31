**Populating Next Right Pointers in Each Node**

## **Problem Pattern**
Connect each node to its next right node at same level using `next` pointer

## **Approach 1: Level Order (BFS)**
```python
def connect(self, root):
    if not root: return root
    q = deque([root])
    
    while q:
        count = len(q)
        while count:
            node = q.popleft()
            if count - 1:  # Not last node in level
                node.next = q[0]
            
            if node.left: q.append(node.left)
            if node.right: q.append(node.right)
            count -= 1
    return root
```

**Mistakes Made**:
- ❌ Forgot `count -= 1` → Infinite loop
- ❌ Assigned next to last node → Should skip when `count-1 == 0`

## **Approach 2: O(1) Space (Perfect Binary Tree)**
```python
def connect(self, root):
    leftmost = root
    while leftmost:
        head = leftmost
        while head and head.left:  # Check both conditions!
            head.left.next = head.right
            if head.next:
                head.right.next = head.next.left
            head = head.next
        leftmost = leftmost.left
    return root
```

## **Key Insights**

### **Perfect Binary Tree Property**
- Always filled left-to-right
- If node has child, both left & right exist
- Can traverse level-by-level using existing connections

### **Critical Mistakes**
1. **Loop Condition**: `while head and head.left` 
   - ❌ Only `while head` → Fails at leaf nodes
   - ✅ Must check `head.left` exists

2. **Connection Pattern**:
   ```
   Level n:     1 ←→ 2 ←→ 3 ←→ 4
               ↙ ↘   ↙ ↘   ↙ ↘   ↙ ↘
   Level n+1: 1→2  3→4  5→6  7→8
   ```

### **Two Connections Per Node**
1. `head.left.next = head.right` (siblings)
2. `head.right.next = head.next.left` (cousins, if exists)

## **Memory Tricks**
- **BFS**: Standard level-order, skip last node's next
- **O(1)**: Use current level's connections to build next level
- **Perfect Tree**: Leftmost path goes straight down (`leftmost = leftmost.left`)

## **Time/Space**
- **BFS**: O(n) time, O(n) space (queue)
- **Optimal**: O(n) time, O(1) space (use existing structure)