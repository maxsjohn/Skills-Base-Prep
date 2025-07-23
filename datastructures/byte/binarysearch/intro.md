# Binary Search - Quick Revision Notes

## Core Concept
- **Dictionary analogy**: Instead of page-by-page search, open middle page and narrow down based on alphabetical position
- **Principle**: Divide search space in half each iteration

## Implementation Steps
1. **Define search space** - Set `left` and `right` boundaries
2. **Narrow search space** - Move pointers based on midpoint comparison
3. **Choose exit condition** - `left < right` vs `left ≤ right`
4. **Return correct value** - Final position after loop ends

## Key Decisions

### Midpoint Calculation
```python
mid = (left + right) // 2
# Or safer: mid = left + (right - left) // 2
```

### Pointer Updates
- **Exclude midpoint**: `left = mid + 1` or `right = mid - 1`
- **Include midpoint**: `left = mid` or `right = mid`

### Exit Conditions
- `left < right` → terminates when pointers meet
- `left ≤ right` → terminates when left surpasses right

## Time Complexity
- **O(log n)** - Halves search space each iteration

## Applications
- **Sorted Arrays**: First/last occurrence, insertion index
- **Partially Sorted**: Rotated arrays
- **Non-intuitive**: Cutting wood, local maxima, weighted random
- **Multiple Arrays**: Median of two sorted arrays
- **Matrix**: 2D search

## Key Insight
Final answer is where `left` and `right` converge after loop termination.

## Common Gotchas & Tricky Details

### The Devil's in the Detail
- **Boundary initialization**: How to set initial `left` and `right`?
- **Loop condition**: `left < right` vs `left ≤ right`?
- **Pointer updates**: When to use `+1` or `-1`?

### Critical Questions to Ask
- **Should midpoint be excluded?** → Use `left = mid + 1` or `right = mid - 1`
- **Should midpoint be included?** → Use `left = mid` or `right = mid`
- **What if target could be at midpoint?** → Include it in search space

### Edge Cases
- **Empty array**: Handle `left > right` scenarios
- **Single element**: Ensure algorithm works with one element
- **Target not found**: Return appropriate value (often insertion point)
- **Duplicate elements**: Consider first/last occurrence requirements

### Implementation Pitfalls
- **Integer overflow**: Use `mid = left + (right - left) // 2` in other languages
- **Infinite loops**: Wrong pointer update logic can cause endless loops
- **Off-by-one errors**: Careful with `+1/-1` in boundary updates

## Quick Checklist for Implementation
- [ ] Define search space boundaries correctly
- [ ] Calculate midpoint safely (avoid overflow)
- [ ] Decide include/exclude logic for pointer updates
- [ ] Choose appropriate exit condition
- [ ] Handle edge cases (empty, single element)
- [ ] Test with duplicates and target not found
- [ ] Return final convergence point
