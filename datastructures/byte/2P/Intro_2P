# Two Pointers - Quick Revision Notes

## Core Concept
- **2 vars** tracking **positions/indices** in data structure
- **Compare elements** at both positions → make decisions
- **O(n) time** vs O(n²) nested loops
- **Leverages predictable dynamics** (sorted arrays, palindromes)

## When to Use?
✅ **Linear data structure** (array, linked list)  
✅ **Predictable dynamics** (sorted, symmetric)  
✅ **Finding pairs** or results from 2 values  
✅ **Comparison-based** problems

## 3 Main Strategies

### 1. Inward Traversal
```
[●●●●●●●●]
 ↑     ↑
left  right
```
- **Start**: Opposite ends
- **Move**: Toward center until meet/cross
- **Use**: Compare elements from different ends

**Problems:**
- Pair Sum (sorted array)
- Triplet Sum
- Container with Most Water
- Valid Palindrome

### 2. Unidirectional Traversal  
```
[●●●●●●●●]
 ↑   ↑
left right
```
- **Start**: Same end (usually beginning)
- **Move**: Same direction, different speeds
- **Use**: One finds info, other tracks info

**Problems:**
- Remove Duplicates
- Move Zeros to End
- Partition Array

### 3. Staged Traversal
```
Stage 1: [●●●●●●●●]
          ↑
        first

Stage 2: [●●●●●●●●]
          ↑ ↑
       first second
```
- **Step 1**: First pointer finds condition
- **Step 2**: Second pointer processes from first
- **Use**: Find + process pattern

**Problems:**
- Next Lexicographical Sequence
- Substring problems
- Pattern matching

## Common Patterns

### Sorted Array Problems
- **Two Sum**: `left + right` vs target
- **Three Sum**: Fix one, two-pointer on rest
- **Container**: `min(height[l], height[r]) * width`

### String/Array Manipulation
- **Palindrome**: Compare `s[left]` vs `s[right]`
- **Remove Duplicates**: Keep unique at `left`, scan with `right`
- **Partition**: Separate elements by condition

### Time Complexity Benefits
- **Single pass**: O(n) instead of O(n²)
- **No extra space**: In-place operations
- **Early termination**: Stop when condition met

## Implementation Tips
- **Initialization**: Correct start positions
- **Movement logic**: When to move which pointer
- **Termination**: Clear stop condition
- **Edge cases**: Empty arrays, single elements

## Real-World Applications
- **Memory compaction** (garbage collection)
- **Merge operations** (sorted arrays)
- **String processing** (palindromes, anagrams)
- **Array partitioning** (quicksort pivot)

## Quick Reference

| Strategy | Start Pos | Movement | Use Case |
|----------|-----------|----------|----------|
| **Inward** | Opposite ends | → ← | Compare ends |
| **Unidirectional** | Same end | → → | Track + find |
| **Staged** | Condition-based | Sequential | Find + process |

## Key Advantages
- ⚡ **O(n) time** complexity
- 💾 **O(1) space** complexity  
- 🎯 **Intuitive** for linear structures
- 🔄 **Versatile** across problem types
