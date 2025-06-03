# DSA Quick Revision Template

Use this template as a prompt: "Create quick revision notes for [ALGORITHM/CONCEPT NAME] using this format:"

## 🎯 **[ALGORITHM NAME]**

### **Core Idea (1 line)**
What does it do in simple terms?

### **When to Use**
- Problem pattern/scenario (2-3 bullet points)
- Key indicators in problem statement

### **Time & Space**
- **Time:** O(?)
- **Space:** O(?)

### **Key Steps**
1. Step 1 (action verb)
2. Step 2 (action verb) 
3. Step 3 (action verb)

### **Code Pattern**
```
// 3-5 lines of pseudocode or key structure
// Focus on the core logic, not full implementation
```

### **Gotchas/Edge Cases**
- Common mistake 1
- Edge case to remember

### **Similar Problems**
- LeetCode #123: Problem Name
- Pattern: [Two Pointers/Sliding Window/etc.]

---

## Example Usage:
"Create quick revision notes for Binary Search using this format:"

## Example Output:

### 🎯 **Binary Search**

**Core Idea:** Eliminate half the search space each iteration by comparing with middle element

**When to Use:**
- Sorted array/list search problems
- "Find target" or "Find first/last occurrence"
- Search space can be divided into valid/invalid parts

**Time & Space:**
- **Time:** O(log n)
- **Space:** O(1)

**Key Steps:**
1. Set left=0, right=n-1
2. Find mid = (left+right)/2  
3. Compare arr[mid] with target, adjust boundaries
4. Repeat until found or left > right

**Code Pattern:**
```
while left <= right:
    mid = (left + right) // 2
    if arr[mid] == target: return mid
    elif arr[mid] < target: left = mid + 1
    else: right = mid - 1
```

**Gotchas/Edge Cases:**
- Integer overflow: use left + (right-left)//2
- <= vs < in while condition
- When to use mid+1 vs mid-1

**Similar Problems:**
- LeetCode #704: Binary Search
- Pattern: Divide and Conquer