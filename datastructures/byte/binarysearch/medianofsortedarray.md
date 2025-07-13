# Find Median of Two Sorted Arrays - Complete Revision Guide

## 🎯 Problem Statement

Find the median of two sorted arrays in **O(log(min(m,n)))** time without merging them.

**Examples:**
```
Input: nums1 = [0,2,5,6,8], nums2 = [1,3,7]
Output: 4.0
Merged: [0,1,2,3,5,6,7,8] → median = (3+5)/2 = 4.0

Input: nums1 = [0,2,5,6,8], nums2 = [1,3,7,9]  
Output: 5.0
Merged: [0,1,2,3,5,6,7,8,9] → median = 5
```

## 💡 Complete Solution

```python
class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        # Always binary search on smaller array
        if len(nums2) < len(nums1):
            nums1, nums2 = nums2, nums1
        
        m, n = len(nums1), len(nums2)
        total = m + n
        half = total // 2
        
        left, right = 0, m - 1
        
        while True:
            # Partition indices
            i = (left + right) // 2
            j = half - i - 2
            
            # Boundary values (use infinity for out-of-bounds)
            nums1_left = nums1[i] if i >= 0 else float('-inf')
            nums1_right = nums1[i + 1] if i + 1 < m else float('inf')
            nums2_left = nums2[j] if j >= 0 else float('-inf')
            nums2_right = nums2[j + 1] if j + 1 < n else float('inf')
            
            # Check if valid partition
            if nums1_left <= nums2_right and nums2_left <= nums1_right:
                # Found correct partition
                if total % 2:
                    return min(nums1_right, nums2_right)
                else:
                    return (max(nums1_left, nums2_left) + min(nums1_right, nums2_right)) / 2
            elif nums1_left > nums2_right:
                # nums1 partition too far right
                right = i - 1
            else:
                # nums1 partition too far left
                left = i + 1
```

## 🧠 Core Intuition

**Goal:** Partition both arrays so that:
1. Left partitions have exactly `half` total elements
2. `max(left_elements) ≤ min(right_elements)`

## 📊 Visual Breakdown

### **Correct Partition Example:**
```
nums1: [0, 2, | 5, 6, 8]    ← partition after index 1
nums2: [1, 3, | 7]          ← partition after index 1

Left side:  [0, 2, 1, 3]  = 4 elements
Right side: [5, 6, 8, 7]  = 4 elements

max(left) = max(2, 3) = 3
min(right) = min(5, 7) = 5
✅ Valid: 3 ≤ 5
```

### **Invalid Partition Example:**
```
nums1: [0, 2, 5, | 6, 8]    ← partition after index 2
nums2: [1, | 3, 7]          ← partition after index 0

Left side:  [0, 2, 5, 1]  = 4 elements  
Right side: [6, 8, 3, 7]  = 4 elements

max(left) = max(5, 1) = 5
min(right) = min(6, 3) = 3
❌ Invalid: 5 > 3
```

## 🔑 Algorithm Steps

1. **Optimization:** Always search on smaller array (reduces search space)
2. **Binary Search:** Try different partition positions in smaller array
3. **Calculate:** Other array's partition = `half_total - current_partition - 2`
4. **Validate:** Check cross-conditions: `L1 ≤ R2` and `L2 ≤ R1`
5. **Adjust:** Move partition based on which condition fails

## ⚡ Binary Search Decision Logic

```python
# Get boundary values
L1 = nums1[i]     if i >= 0 else -∞
R1 = nums1[i+1]   if i+1 < m else +∞  
L2 = nums2[j]     if j >= 0 else -∞
R2 = nums2[j+1]   if j+1 < n else +∞

# Decision tree
if L1 > R2:
    right = i - 1    # Move partition LEFT in nums1
elif L2 > R1:  
    left = i + 1     # Move partition RIGHT in nums1
else:
    # Found valid partition!
```

## 🎯 Return Logic

**Even total length:**
```python
return (max(L1, L2) + min(R1, R2)) / 2
```

**Odd total length:**
```python
return min(R1, R2)  # Right side has extra element
```

## 🚨 Critical Edge Cases

1. **Empty partitions:** Use `-∞` and `+∞` for out-of-bounds
2. **One array exhausted:** Partition can be entirely on one side
3. **Single element arrays:** Handle boundary checks carefully
4. **Very different sizes:** Always search on smaller array

## 💭 Mental Models

### **Sliding Divider Concept:**
```
Wrong:   [1,2,3,4] | [5,6]     ← divider too far right
Correct: [1,2] | [3,4,5,6]     ← perfect balance
Wrong:   [1] | [2,3,4,5,6]     ← divider too far left
```

### **Cross-Check Pattern:**
```
Array1: [..., L1, | R1, ...]
Array2: [..., L2, | R2, ...]

Must satisfy: L1 ≤ R2  AND  L2 ≤ R1
```

## 📈 Complexity Analysis

- **Time:** O(log(min(m,n))) - Binary search on smaller array
- **Space:** O(1) - Only using constant extra space

## 🔥 Key Insights for Revision

1. **Never merge arrays** - defeats the purpose of O(log) requirement
2. **Binary search on SMALLER array** - crucial optimization  
3. **Partition formula:** `j = half - i - 2` (remember the -2!)
4. **Cross-validation:** Both `L1 ≤ R2` AND `L2 ≤ R1` must be true
5. **Infinity handling:** Essential for edge cases where partition is at boundaries
6. **Odd vs Even:** Odd returns single element, even returns average of two

## 🎪 Quick Memory Tricks

- **"Half and Cross"** - Split in half, check cross-conditions
- **"Small Search"** - Always binary search the smaller array
- **"Infinity Guards"** - Use ±∞ for boundary protection
- **"Left Max, Right Min"** - These determine the median
