# Set Matrix Zeroes - Complete Revision Guide

## 🎯 Problem Statement

Given an m x n matrix, if an element is 0, set its entire row and column to 0. Do it **in-place**.

**Example:**
```
Input:                    Output:
[1,  1,  1]              [1,  0,  1]
[1,  0,  1]       →      [0,  0,  0]  
[1,  1,  1]              [1,  0,  1]
```

## 💡 Solution 1: Hash Sets (O(m+n) Space)

```python
def setZeroes(matrix: List[List[int]]) -> None:
    if not matrix or not matrix[0]:
        return
    
    m, n = len(matrix), len(matrix[0])
    zero_rows, zero_cols = set(), set()
    
    # Pass 1: Find all zeros and record their positions
    for r in range(m):
        for c in range(n):
            if matrix[r][c] == 0:
                zero_rows.add(r)
                zero_cols.add(c)
    
    # Pass 2: Set rows and columns to zero
    for r in range(m):
        for c in range(n):
            if r in zero_rows or c in zero_cols:
                matrix[r][c] = 0
```

## 🚀 Solution 2: In-Place (O(1) Space) ⭐

```python
def setZeroes(matrix: List[List[int]]) -> None:
    if not matrix or not matrix[0]:
        return
    
    m, n = len(matrix), len(matrix[0])
    
    # Step 1: Check if first row/column originally have zeros
    first_row_has_zero = any(matrix[0][c] == 0 for c in range(n))
    first_col_has_zero = any(matrix[r][0] == 0 for r in range(m))
    
    # Step 2: Use first row/column as markers for zeros in submatrix
    for r in range(1, m):
        for c in range(1, n):
            if matrix[r][c] == 0:
                matrix[0][c] = 0    # Mark column
                matrix[r][0] = 0    # Mark row
    
    # Step 3: Set zeros in submatrix based on markers
    for r in range(1, m):
        for c in range(1, n):
            if matrix[0][c] == 0 or matrix[r][0] == 0:
                matrix[r][c] = 0
    
    # Step 4: Handle first row if it originally had zeros
    if first_row_has_zero:
        for c in range(n):
            matrix[0][c] = 0
    
    # Step 5: Handle first column if it originally had zeros  
    if first_col_has_zero:
        for r in range(m):
            matrix[r][0] = 0
```

## 🧠 Core Intuition

**Key Insight:** Use the first row and first column as "marker arrays" to track which rows/columns should be zeroed.

## 📊 Visual Breakdown

### **Step-by-Step In-Place Process:**

**Original Matrix:**
```
[1, 1, 1, 1]
[1, 0, 1, 1]    ← zero at (1,1)
[1, 1, 1, 1]
[1, 1, 0, 1]    ← zero at (3,2)
```

**Step 1: Check first row/column for original zeros**
```
first_row_has_zero = False
first_col_has_zero = False
```

**Step 2: Mark zeros using first row/column**
```
[1, 0, 0, 1]    ← markers: col 1, col 2 have zeros
[0, 0, 1, 1]    ← marker: row 1 has zero
[1, 1, 1, 1]
[0, 1, 0, 1]    ← marker: row 3 has zero
 ↑
marker: rows 1,3 have zeros
```

**Step 3: Apply zeros based on markers**
```
[1, 0, 0, 1]
[0, 0, 0, 0]    ← entire row 1 = 0
[1, 0, 0, 1]    ← cols 1,2 = 0
[0, 0, 0, 0]    ← entire row 3 = 0
```

## 🔑 Algorithm Steps (In-Place)

1. **Preserve Original State:** Check if first row/column originally contain zeros
2. **Mark Phase:** Use first row/column to mark which rows/columns need zeroing
3. **Apply Phase:** Zero out submatrix based on markers
4. **Clean Up:** Handle first row/column based on original state

## ⚡ Key Decision Logic

```python
# For any cell (r,c) in submatrix:
if matrix[r][c] == 0:
    matrix[0][c] = 0    # Mark this column
    matrix[r][0] = 0    # Mark this row

# Later, to check if cell should be zero:
if matrix[0][c] == 0 or matrix[r][0] == 0:
    matrix[r][c] = 0
```

## 🎯 Why This Works

**Clever Observation:** Since we're going to zero out entire rows/columns anyway, we can safely use the first row and column as storage for our markers!

```
Before marking:        After marking:
[a, b, c]             [a, 0, c]  ← marked col 1
[d, 0, f]      →      [0, 0, f]  ← marked row 1  
[g, h, i]             [g, h, i]
```

## 🚨 Critical Edge Cases

1. **First row/column have original zeros**
   ```python
   # Must check BEFORE using as markers!
   first_row_has_zero = any(matrix[0][c] == 0 for c in range(n))
   ```

2. **Empty matrix**
   ```python
   if not matrix or not matrix[0]:
       return
   ```

3. **Single row/column matrices**
   ```python
   # Algorithm works but be careful with indices
   for r in range(1, m):  # Start from 1, not 0
   ```

## 💭 Mental Models

### **Two-Phase Thinking:**
```
Phase 1: "Record which rows/cols need zeroing"
Phase 2: "Apply the zeroing"
```

### **Marker Strategy:**
```
matrix[0][c] = 0  means "zero out column c"
matrix[r][0] = 0  means "zero out row r"
```

### **Clean-up Pattern:**
```
1. Handle submatrix first
2. Handle first row if needed  
3. Handle first column if needed
```

## 📈 Complexity Analysis

### **Hash Sets Approach:**
- **Time:** O(m × n) - Two passes through matrix
- **Space:** O(m + n) - Store row and column indices

### **In-Place Approach:**
- **Time:** O(m × n) - Multiple passes but still linear
- **Space:** O(1) - Only using existing matrix space

## 🔥 Key Insights for Revision

1. **"First Row/Column as Storage"** - The genius of using them as marker arrays
2. **"Preserve Before Use"** - Check original state before overwriting with markers
3. **"Submatrix First"** - Always handle inner matrix before first row/column
4. **"Order Matters"** - Handle first row before first column (or vice versa consistently)

## 🎪 Quick Memory Tricks

- **"Mark and Sweep"** - Mark zeros, then sweep to apply
- **"Corner Protection"** - Protect first row/column original state
- **"Inside Out"** - Process inside matrix first, then borders
- **"Zero Propagation"** - Zeros "infect" their entire row and column

## 🔄 Common Mistakes to Avoid

❌ **Modifying first row/column before checking original state**
❌ **Processing first row/column in wrong order**  
❌ **Forgetting to handle edge case where first row/column originally have zeros**
❌ **Not starting submatrix iteration from index 1**

## 🏆 Interview Tips

- **Start with hash set approach** if asked for any solution first
- **Mention in-place optimization** to show advanced thinking
- **Explain the "marker array" insight** - this is the key breakthrough
- **Walk through edge cases** to show thoroughness
