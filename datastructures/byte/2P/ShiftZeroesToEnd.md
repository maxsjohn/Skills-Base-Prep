# Shift Zeros to End - Quick Notes

## Problem
**Given**: Array of integers  
**Modify**: In-place to move all zeros to end  
**Maintain**: Relative order of non-zero elements

```
Input: nums = [0, 1, 0, 3, 2]
Output: [1, 3, 2, 0, 0]
```

## Requirements
✅ **Move zeros** to end  
✅ **Preserve order** of non-zeros  
✅ **In-place** modification (O(1) space)

## Key Insight
🔑 **Focus on non-zeros** instead of zeros  
🔑 **Move non-zeros left** → zeros automatically go right  
🔑 **Two pointers**: One tracks position, other finds elements

## Strategy Shift
```
Instead of: "Move zeros to right"
Think:     "Move non-zeros to left"

[0, 1, 0, 3, 2] → [1, 3, 2, _, _]
                    ↑ non-zeros  ↑ zeros
```

## Two Pointers Pattern

### Unidirectional Traversal
```
[0, 1, 0, 3, 2]
 ↑     ↑
left  right
```

### Pointer Roles
- **Left**: Tracks **where** next non-zero should go
- **Right**: **Finds** non-zero elements (scans entire array)

## Algorithm Steps

### Core Logic
```python
for right in range(len(nums)):
    if nums[right] != 0:
        swap(nums[left], nums[right])
        left += 1
```

### Step-by-Step Example
```
nums = [0, 1, 0, 3, 2]

Step 1: left=0, right=0 → nums[0]=0 → skip
Step 2: left=0, right=1 → nums[1]=1≠0 → swap(0,1) → [1,0,0,3,2], left=1
Step 3: left=1, right=2 → nums[2]=0 → skip  
Step 4: left=1, right=3 → nums[3]=3≠0 → swap(0,3) → [1,3,0,0,2], left=2
Step 5: left=2, right=4 → nums[4]=2≠0 → swap(0,2) → [1,3,2,0,0], left=3

Result: [1, 3, 2, 0, 0]
```

## Implementation Template
```python
def shift_zeros_to_end(nums):
    left = 0  # Position for next non-zero
    
    for right in range(len(nums)):
        if nums[right] != 0:
            # Swap non-zero to correct position
            nums[left], nums[right] = nums[right], nums[left]
            left += 1  # Move to next position
    
    # No return needed (in-place modification)
```

## Why This Works

### Invariant Maintenance
- **nums[0:left]**: All non-zeros in original order
- **nums[left:]**: Mix of zeros and unprocessed elements
- **After completion**: All non-zeros left, all zeros right

### Swap Logic
- **When right finds non-zero**: Place it at position `left`
- **Increment left**: Ready for next non-zero
- **Zeros naturally accumulate**: In positions after `left`

## Edge Cases

| Input | Output | Notes |
|-------|--------|-------|
| `[]` | `[]` | Empty array |
| `[0]` | `[0]` | Single zero |
| `[1]` | `[1]` | Single non-zero |
| `[0,0,0]` | `[0,0,0]` | All zeros |
| `[1,3,2]` | `[1,3,2]` | No zeros |
| `[1,1,1,0,0]` | `[1,1,1,0,0]` | Already correct |
| `[0,0,1,1,1]` | `[1,1,1,0,0]` | Zeros at start |

## Complexity Analysis

| Aspect | Complexity | Explanation |
|--------|------------|-------------|
| **Time** | O(n) | Single pass through array |
| **Space** | O(1) | In-place swapping only |

## Alternative Approaches

### Naive (Extra Space)
```python
# Create new array, add non-zeros first
temp = []
for num in nums:
    if num != 0:
        temp.append(num)
while len(temp) < len(nums):
    temp.append(0)
# Copy back to nums
```
❌ **Space**: O(n) - violates in-place requirement

### Two-Pass
```python
# Pass 1: Count non-zeros, move them left
# Pass 2: Fill remaining with zeros
```
✅ **Works** but less elegant than one-pass solution

## Interview Tips
✅ **Clarify in-place requirement** upfront  
✅ **Start with simple examples** to verify logic  
✅ **Explain pointer roles** clearly  
✅ **Test edge cases** (all zeros, no zeros)  
✅ **Mention swap optimization** (avoid when left==right)

## Pattern Recognition
🔍 **"Move elements to end"** → Focus on opposite direction  
🔍 **"Maintain relative order"** → Stable partitioning  
🔍 **Unidirectional two pointers** → One tracks, one scans

## Optimization Note
```python
# Can optimize to avoid unnecessary swaps
if left != right:
    nums[left], nums[right] = nums[right], nums[left]
```
But the simple version is cleaner and equally efficient.

## Related Problems
- **Remove Element**: Remove specific value in-place
- **Partition Array**: Separate by condition
- **Dutch National Flag**: Three-way partitioning
