# Pair Sum - Sorted Array - Quick Notes

## Problem
**Given**: Sorted array + target sum  
**Return**: Indices of pair that sum to target (or empty array)

```
Input: nums = [-5, -2, 3, 4, 6], target = 7
Output: [2, 3]  // nums[2] + nums[3] = 3 + 4 = 7
```

## Key Insight
🔑 **Array is SORTED** → use this fact for O(n) solution

## Brute Force vs Optimal

| Approach | Time | Space | Method |
|----------|------|-------|---------|
| **Brute Force** | O(n²) | O(1) | Nested loops |
| **Two Pointers** | O(n) | O(1) | Inward traversal |

## Two Pointers Algorithm

### Setup
```
[-5, -2, 3, 4, 6]  target = 7
 ↑            ↑
left        right
```

### Decision Logic
```python
sum = nums[left] + nums[right]

if sum < target:    left += 1   # Need bigger sum
elif sum > target:  right -= 1  # Need smaller sum
else:               return [left, right]  # Found!
```

### Why This Works?
- **Sorted array** → predictable value changes
- `left++` → always **increases** sum
- `right--` → always **decreases** sum
- **Converging** → covers all possibilities

## Step-by-Step Example
```
nums = [-5, -2, 3, 4, 6], target = 7

Step 1: l=0, r=4 → -5+6=1 < 7 → l++
Step 2: l=1, r=4 → -2+6=4 < 7 → l++  
Step 3: l=2, r=4 → 3+6=9 > 7 → r--
Step 4: l=2, r=3 → 3+4=7 = 7 → return [2,3]
```

## Implementation Template
```python
def pair_sum_sorted(nums, target):
    left, right = 0, len(nums) - 1
    
    while left < right:
        current_sum = nums[left] + nums[right]
        
        if current_sum < target:
            left += 1      # Need bigger sum
        elif current_sum > target:
            right -= 1     # Need smaller sum
        else:
            return [left, right]  # Found pair
    
    return []  # No pair found
```

## Edge Cases

| Input | Output | Notes |
|-------|--------|-------|
| `[]` | `[]` | Empty array |
| `[1]` | `[]` | Single element |
| `[2,3], target=5` | `[0,1]` | Perfect pair |
| `[2,4], target=5` | `[]` | No valid pair |
| `[2,2,3], target=5` | `[0,2]` or `[1,2]` | Duplicates |
| `[-3,-2,-1], target=-5` | `[0,1]` | All negatives |

## Complexity
- ⏰ **Time**: O(n) - single pass
- 💾 **Space**: O(1) - only 2 pointers
- 🎯 **Optimal** for sorted input

## Interview Tips
✅ **Always check if input is sorted** - key optimization hint  
✅ **Start with brute force** then optimize  
✅ **Test edge cases** (empty, single, duplicates)  
✅ **Explain pointer movement logic** clearly

## When NOT to Use
❌ **Unsorted array** → use HashMap instead  
❌ **Find all pairs** → different approach needed  
❌ **More than 2 elements** → extend to 3Sum/4Sum

## Pattern Recognition
🔍 **"Sorted array + find pair"** = Two pointers candidate  
🔍 **Inward traversal** pattern  
🔍 **Comparison-driven** pointer movement
