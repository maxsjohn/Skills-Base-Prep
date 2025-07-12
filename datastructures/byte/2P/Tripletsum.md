# Triplet Sum - Quick Notes

## Problem
**Given**: Array of integers  
**Return**: All unique triplets [a,b,c] where a+b+c = 0

```
Input: nums = [0, -1, 2, -3, 1]
Output: [[-3, 1, 2], [-1, 0, 1]]
```

## Key Strategy
🔑 **Fix one element** → reduce to **Two Sum** problem

## Algorithm Breakdown

### Step 1: Sort Array
```
[-1, 2, -2, 1, -1, 2] → [-2, -1, -1, 1, 2, 2]
```
**Why?** Enables duplicate handling + two pointers

### Step 2: Fix First Element (a)
```
For each nums[i] as 'a':
  Find pairs [b,c] where b + c = -a
```

### Step 3: Use Two Pointers for Remaining
```
a + b + c = 0  →  b + c = -a
```
Apply **Pair Sum** on `nums[i+1:]` with target = `-nums[i]`

## Visual Example
```
nums = [-2, -1, -1, 1, 2, 2]

i=0: a=-2, find pairs summing to 2 in [-1,-1,1,2,2]
     No valid pairs found

i=1: a=-1, find pairs summing to 1 in [-1,1,2,2]  
     Found: [-1,2] → triplet [-1,-1,2]
```

## Duplicate Handling

### Case 1: Duplicate 'a' values
```python
if i > 0 and nums[i] == nums[i-1]:
    continue  # Skip same 'a' value
```

### Case 2: Duplicate 'b' values
```python
# In pair_sum function:
left += 1
while left < right and nums[left] == nums[left-1]:
    left += 1  # Skip same 'b' value
```

**Note**: No need to handle duplicate 'c' - automatically unique when [a,b] unique

## Implementation Pattern
```python
def triplet_sum(nums):
    triplets = []
    nums.sort()
    
    for i in range(len(nums)):
        # Optimization: stop at positive numbers
        if nums[i] > 0:
            break
            
        # Skip duplicate 'a'
        if i > 0 and nums[i] == nums[i-1]:
            continue
            
        # Find all pairs for current 'a'
        pairs = pair_sum_all(nums, i+1, -nums[i])
        for pair in pairs:
            triplets.append([nums[i]] + pair)
    
    return triplets

def pair_sum_all(nums, start, target):
    pairs = []
    left, right = start, len(nums) - 1
    
    while left < right:
        sum_val = nums[left] + nums[right]
        
        if sum_val == target:
            pairs.append([nums[left], nums[right]])
            left += 1
            # Skip duplicate 'b'
            while left < right and nums[left] == nums[left-1]:
                left += 1
        elif sum_val < target:
            left += 1
        else:
            right -= 1
    
    return pairs
```

## Optimizations

### Early Termination
```python
if nums[i] > 0:
    break  # All remaining triplets will be positive
```
**Why?** If a > 0, then b,c must be > 0 → sum > 0

### Skip Processing
- Skip duplicate 'a' values before processing
- Skip duplicate 'b' values during pair finding
- Sorted array enables easy duplicate detection

## Complexity Analysis

| Aspect | Complexity | Explanation |
|--------|------------|-------------|
| **Time** | O(n²) | Sort: O(n log n) + Loop: O(n) × PairSum: O(n) |
| **Space** | O(n) | Sorting space (excluding output) |
| **Output Space** | O(n²) | Worst case: all pairs valid |

## Edge Cases

| Input | Output | Notes |
|-------|--------|-------|
| `[]` | `[]` | Empty array |
| `[0]` | `[]` | Single element |
| `[1,-1]` | `[]` | Two elements |
| `[0,0,0]` | `[[0,0,0]]` | All same |
| `[1,0,1]` | `[]` | No valid triplets |
| `[0,0,1,-1,1,-1]` | `[[-1,0,1]]` | Duplicate handling |

## Interview Tips
✅ **Start with brute force** (O(n³)) then optimize  
✅ **Emphasize sorting necessity** for duplicate handling  
✅ **Explain two-level duplicate prevention**  
✅ **Mention early termination optimization**  
✅ **Discuss space complexity** with/without output

## Pattern Recognition
🔍 **"Find triplets/3-sum"** → Fix one, two pointers for rest  
🔍 **Combination of**: sorting + two pointers + duplicate handling  
🔍 **Extension of**: Two Sum problem

## Common Variations
- **3Sum Closest**: Find triplet closest to target
- **4Sum**: Extend to 4 elements
- **3Sum Smaller**: Count triplets < target
