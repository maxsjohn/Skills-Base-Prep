# Next Lexicographical Sequence - Quick Notes

## Problem
Given string, find **next immediate** lexicographical permutation using same characters.
If already last → return first (smallest) permutation.

## Examples
- `"abcd"` → `"abdc"`
- `"dcba"` → `"abcd"` (last → first)

## Key Insight
**Smallest increase** = rearrange **rightmost** characters possible.
**Last permutation** = always **non-increasing** order.

## Algorithm (4 Steps)

### 1. Find Pivot
- Traverse **right to left**
- Pivot = first char that breaks **non-increasing** sequence
- If no pivot → string is last permutation → **reverse entire string**

### 2. Find Rightmost Successor
- In suffix after pivot, find **rightmost** char > pivot
- (Since suffix is non-increasing, first from right that's larger)

### 3. Swap
- Swap **pivot** with **rightmost successor**

### 4. Reverse Suffix
- Reverse everything **after pivot position**
- Makes suffix smallest possible permutation

## Example Walkthrough
`"abcedda"`:
1. **Pivot**: `c` (breaks non-increasing `edda`)
2. **Rightmost successor**: `d` (rightmost char > `c`)
3. **Swap**: `"abcedda"` → `"abdecca"`
4. **Reverse suffix**: `"abdecca"` → `"abdaced"`

## Code Pattern
```python
# 1. Find pivot (right to left)
pivot = n-2
while pivot >= 0 and s[pivot] >= s[pivot+1]:
    pivot -= 1

# 2. Handle last permutation
if pivot == -1:
    return reverse(s)

# 3. Find rightmost successor
right_succ = n-1
while s[right_succ] <= s[pivot]:
    right_succ -= 1

# 4. Swap and reverse suffix
swap(s[pivot], s[right_succ])
reverse(s[pivot+1:])
```

## Complexity
- **Time**: O(n) - single pass to find pivot + successor + reverse
- **Space**: O(n) - for mutable array (strings immutable in Python)

## Key Points
- Use "**non-increasing**" not "decreasing" (allows equal adjacent chars)
- Rightmost focus for **minimal increase**
- Last permutation always in **descending order**
