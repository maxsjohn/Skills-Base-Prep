# Largest Container - Quick Notes

## Problem
**Given**: Array of heights (vertical lines)  
**Return**: Maximum water container can hold between any two lines

```
Input: heights = [2, 7, 8, 3, 7, 6]
Output: 24  // Between indices 1 and 4: min(7,7) * (4-1) = 21
```

## Water Formula
```
water = min(heights[i], heights[j]) * (j - i)
        ↑                           ↑
    height (limited by shorter)   width
```

## Key Insights
🔑 **Height**: Limited by **shorter line** (water overflows otherwise)  
🔑 **Width**: Distance between two lines  
🔑 **Strategy**: Start with **max width**, then optimize for **height**

## Algorithm Strategy

### Inward Traversal Pattern
```
[2, 7, 8, 3, 7, 6]
 ↑              ↑
left          right
```

### Core Logic
1. **Start**: Max width (left=0, right=n-1)
2. **Calculate**: Current container area  
3. **Move**: Pointer at **shorter line** inward
4. **Repeat**: Until pointers meet

### Why Move Shorter Line?
- Moving **taller line** → width↓, height stays same → area↓
- Moving **shorter line** → width↓, but height might↑ → area might↑

## Step-by-Step Example
```
heights = [2, 7, 8, 3, 7, 6]

Step 1: left=0(2), right=5(6) → water=min(2,6)*5=10, max=10
        heights[left] < heights[right] → left++

Step 2: left=1(7), right=5(6) → water=min(7,6)*4=24, max=24  
        heights[left] > heights[right] → right--

Step 3: left=1(7), right=4(7) → water=min(7,7)*3=21, max=24
        heights[left] == heights[right] → both move

Step 4: left=2(8), right=3(3) → water=min(8,3)*1=3, max=24
        heights[left] > heights[right] → right--

Step 5: left=2, right=2 → pointers meet → return 24
```

## Decision Logic
```python
if heights[left] < heights[right]:
    left += 1      # Move shorter line
elif heights[left] > heights[right]:  
    right -= 1     # Move shorter line
else:
    left += 1      # Both equal, move both
    right -= 1     # (or just one, doesn't matter)
```

## Implementation Template
```python
def largest_container(heights):
    max_water = 0
    left, right = 0, len(heights) - 1
    
    while left < right:
        # Calculate current container
        width = right - left
        height = min(heights[left], heights[right])
        water = height * width
        max_water = max(max_water, water)
        
        # Move pointer at shorter line
        if heights[left] < heights[right]:
            left += 1
        elif heights[left] > heights[right]:
            right -= 1  
        else:
            left += 1   # Equal heights
            right -= 1
    
    return max_water
```

## Why This Works?

### Proof Sketch
- **Start**: Maximum possible width
- **Each move**: Trade width for potential height increase
- **Moving taller line**: Guaranteed to decrease area (width↓, height same)
- **Moving shorter line**: Only way to potentially increase area
- **Optimal**: We never miss the optimal solution

### Greedy Choice
Always move the pointer that **might** lead to improvement (shorter line)

## Edge Cases

| Input | Output | Notes |
|-------|--------|-------|
| `[]` | `0` | Empty array |
| `[1]` | `0` | Single element (need 2 lines) |
| `[0,1,0]` | `0` | No valid container |
| `[3,3,3,3]` | `9` | All same heights: min(3,3)*3=9 |
| `[1,2,3]` | `2` | Increasing: min(1,3)*2=2 |
| `[3,2,1]` | `2` | Decreasing: min(3,1)*2=2 |

## Complexity Analysis

| Aspect | Complexity | Explanation |
|--------|------------|-------------|
| **Time** | O(n) | Single pass with two pointers |
| **Space** | O(1) | Only constant variables |

## Brute Force vs Optimal

| Approach | Time | Space | Method |
|----------|------|-------|---------|
| **Brute Force** | O(n²) | O(1) | Check all pairs |
| **Two Pointers** | O(n) | O(1) | Inward traversal |

## Interview Tips
✅ **Start with brute force** then optimize  
✅ **Explain water formula** clearly  
✅ **Justify pointer movement** logic  
✅ **Handle equal heights** case  
✅ **Test edge cases** (empty, single element)

## Pattern Recognition
🔍 **"Maximum area/volume"** + **two elements** = Two pointers candidate  
🔍 **Trade-off optimization** (width vs height)  
🔍 **Greedy approach** with proof

## Common Mistakes
❌ Moving both pointers when heights equal (inefficient but works)  
❌ Not understanding why we move shorter line  
❌ Forgetting edge cases (empty, single element)  
❌ Incorrect water calculation formula

## Related Problems
- **Trapping Rain Water**: Different problem (cumulative water)
- **Rectangle in Histogram**: Similar optimization concept
- **Maximum Area of Island**: 2D version with different constraints
