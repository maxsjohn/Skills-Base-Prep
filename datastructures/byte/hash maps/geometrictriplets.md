# Count Triplets in Geometric Progression - Complete Revision Guide

## 🎯 Problem Statement

Given an array and a common ratio `r`, find all triplets of indices `(i, j, k)` where `i < j < k` and the values form a geometric sequence: `nums[i] * r = nums[j]` and `nums[j] * r = nums[k]`.

**Example:**
```
Input: nums = [2, 1, 2, 4, 8, 8], r = 2
Output: 5

Triplets found:
- [2, 4, 8] at indices (0, 3, 4), (0, 3, 5), (2, 3, 4), (2, 3, 5)
- [1, 2, 4] at indices (1, 2, 3)
```

## 💡 Optimal Solution - O(n) Time

```python
from collections import defaultdict  # ⚠️ Import from collections!

def countTriplets(nums: List[int], r: int) -> int:
    # ⚠️ Use defaultdict(int), NOT defaultdict("int")!
    left_map = defaultdict(int)   # Frequency of elements to the left
    right_map = defaultdict(int)  # Frequency of elements to the right
    count = 0
    
    # Step 1: Populate right_map with all elements initially
    for x in nums:
        right_map[x] += 1
    
    # Step 2: Process each element as potential middle of triplet
    for x in nums:
        # Remove current element from right (it's not to the right of itself)
        right_map[x] -= 1
        
        # Check if x can be middle of geometric triplet
        if x % r == 0:  # x/r must be integer
            # ⚠️ MULTIPLY frequencies, don't sum them!
            count += left_map[x // r] * right_map[x * r]
        
        # Add current element to left (for future iterations)
        left_map[x] += 1
    
    return count
```

## 🧠 Core Intuition

**Key Insight:** For any element `x` to be the middle of a geometric triplet:
- We need `x/r` to the **left** of `x`
- We need `x*r` to the **right** of `x`
- **Number of triplets = frequency(x/r) × frequency(x*r)**

## 📊 Visual Breakdown

### **Triplet Representation:**
```
Geometric triplet: (a, a*r, a*r²)
Alternative view:   (x/r, x, x*r)  ← We use this!

Why? Because it ensures proper ordering:
- x/r is always to the LEFT of x
- x*r is always to the RIGHT of x
```

### **Step-by-Step Example:**
```
Array: [2, 1, 2, 4, 8, 8], r = 2

Initial state:
left_map = {}
right_map = {2: 2, 1: 1, 4: 1, 8: 2}

Processing x = 2 (index 0):
right_map[2] -= 1  → {2: 1, 1: 1, 4: 1, 8: 2}
x % r == 0? Yes (2 % 2 == 0)
count += left_map[2//2] * right_map[2*2] = left_map[1] * right_map[4] = 0 * 1 = 0
left_map[2] += 1  → {2: 1}

Processing x = 1 (index 1):
right_map[1] -= 1  → {2: 1, 1: 0, 4: 1, 8: 2}
x % r == 0? No (1 % 2 != 0) → Skip
left_map[1] += 1  → {2: 1, 1: 1}

Processing x = 2 (index 2):
right_map[2] -= 1  → {2: 0, 1: 0, 4: 1, 8: 2}
x % r == 0? Yes
count += left_map[1] * right_map[4] = 1 * 1 = 1 ✅
left_map[2] += 1  → {2: 2, 1: 1}

... and so on
```

## 🔑 Algorithm Steps

1. **Initialize Maps:**
   - `left_map`: Empty (no elements processed yet)
   - `right_map`: All elements with their frequencies

2. **For Each Element `x`:**
   - Remove `x` from `right_map` (it's no longer "to the right")
   - If `x % r == 0`, calculate triplets: `left_map[x//r] * right_map[x*r]`
   - Add `x` to `left_map` (it's now "to the left" for future elements)

3. **Return Total Count**

## ⚡ Critical Implementation Details

### **🚨 Common Mistake #1: Import Statement**
```python
# ❌ WRONG:
from collections import defaultdict("int")  # Wrong syntax!

# ✅ CORRECT:
from collections import defaultdict
left_map = defaultdict(int)  # int is the factory function
```

### **🚨 Common Mistake #2: Sum vs Multiply**
```python
# ❌ WRONG - Adding frequencies:
count += left_map[x // r] + right_map[x * r]

# ✅ CORRECT - Multiplying frequencies:
count += left_map[x // r] * right_map[x * r]
```

**Why multiply?** If there are 2 instances of `x/r` on the left and 3 instances of `x*r` on the right, we can form 2 × 3 = 6 different triplets!

### **🚨 Common Mistake #3: Division Check**
```python
# ❌ WRONG - Not checking divisibility:
count += left_map[x / r] * right_map[x * r]  # x/r might not be integer!

# ✅ CORRECT - Check divisibility first:
if x % r == 0:
    count += left_map[x // r] * right_map[x * r]
```

## 🎯 Why This Algorithm Works

**Dynamic Window Approach:**
- As we process element at index `i`, `left_map` contains frequencies of elements at indices `0` to `i-1`
- `right_map` contains frequencies of elements at indices `i+1` to `n-1`
- This ensures proper ordering: left < middle < right

**Frequency Multiplication Logic:**
```
If we have:
- 2 occurrences of value 'a' on the left
- 3 occurrences of value 'c' on the right
- Current middle value is 'b'

Then we can form 2 × 3 = 6 triplets: (a₁,b,c₁), (a₁,b,c₂), (a₁,b,c₃), (a₂,b,c₁), (a₂,b,c₂), (a₂,b,c₃)
```

## 📈 Complexity Analysis

- **Time:** O(n) - Single pass through array with O(1) hash map operations
- **Space:** O(n) - Hash maps store at most n unique elements

## 🚨 Edge Cases & Gotchas

1. **r = 1 (Special Case):**
   ```python
   # When r = 1, all three elements must be equal
   # x/r = x = x*r, so we need count of same element
   # Formula becomes: freq(x) * (freq(x) - 1) * (freq(x) - 2) / 6
   ```

2. **r = 0:**
   ```python
   # Invalid case - geometric sequence can't have ratio 0
   ```

3. **Empty Array:**
   ```python
   # Return 0 (no triplets possible)
   ```

4. **Array with < 3 Elements:**
   ```python
   # Return 0 (need at least 3 elements for triplet)
   ```

## 💭 Mental Models

### **"Sliding Window of Possibilities":**
```
As we move through array:
[processed] [current] [remaining]
    ↑          ↑         ↑
left_map   middle   right_map
```

### **"Frequency Multiplication Pattern":**
```
Think: "How many ways can I pick one from left AND one from right?"
Answer: left_count × right_count (multiplication principle)
```

## 🔥 Key Insights for Revision

1. **"Two-Map Strategy"** - Use left and right frequency maps
2. **"Middle-First Thinking"** - Process each element as potential middle
3. **"Multiply Don't Add"** - Frequencies multiply for combination counting
4. **"Divisibility Check"** - Always verify `x % r == 0` before processing
5. **"Dynamic Update"** - Move elements from right to left as you process

## 🎪 Quick Memory Tricks

- **"Left-Middle-Right"** - Think of each element as middle, look left and right
- **"Frequency Factory"** - `defaultdict(int)` creates integer factory
- **"Multiply Magic"** - Combinations multiply, not add
- **"Division Detective"** - Always check if division results in integer

## 🏆 Interview Tips

- **Start with brute force** (O(n³)) to show understanding
- **Explain the optimization** - "What if we fix the middle element?"
- **Draw the frequency maps** - Visual representation helps
- **Mention edge cases** - Show comprehensive thinking
- **Code step by step** - Don't rush the implementation

## ⚠️ Common Coding Mistakes

❌ **Wrong import:** `defaultdict("int")` instead of `defaultdict(int)`  
❌ **Adding frequencies:** `left + right` instead of `left * right`  
❌ **Skipping divisibility check:** Not verifying `x % r == 0`  
❌ **Wrong update order:** Moving to left_map before checking triplets  
❌ **Float division:** Using `/` instead of `//` for integer division
