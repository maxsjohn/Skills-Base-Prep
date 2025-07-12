# Valid Palindrome - Quick Notes

## Problem
**Given**: String with mixed characters  
**Return**: True if palindrome after removing non-alphanumeric chars

```
Input: s = 'a dog! a panic in a pagoda.'
Output: True  // 'adogapanicina pagoda' → palindrome

Input: s = 'abc123'  
Output: False
```

## Key Insights
🔑 **Palindrome**: Reads same forward/backward  
🔑 **Ignore**: Non-alphanumeric chars (spaces, punctuation)  
🔑 **Compare**: First ↔ Last, Second ↔ Second-last, etc.

## Visual Pattern
```
Original: "a dog! a panic in a pagoda"
Clean:    "adogapanicina pagoda"
Compare:   ↑              ↑
          a              a  ✓
           ↑            ↑
          d            d  ✓
           ↑          ↑  
          o          o  ✓
          ... continue inward
```

## Algorithm Strategy

### Inward Traversal Pattern
```
[ a + 2 c ! 2 a ]
  ↑           ↑
 left       right
```

### Core Logic
1. **Skip non-alphanumeric** from both ends
2. **Compare** alphanumeric chars at left/right
3. **Move inward** if match, **return false** if mismatch
4. **Continue** until pointers meet/cross

## Implementation Pattern
```python
def is_palindrome_valid(s):
    left, right = 0, len(s) - 1
    
    while left < right:
        # Skip non-alphanumeric from left
        while left < right and not s[left].isalnum():
            left += 1
            
        # Skip non-alphanumeric from right  
        while left < right and not s[right].isalnum():
            right -= 1
            
        # Compare alphanumeric chars
        if s[left].lower() != s[right].lower():
            return False
            
        left += 1
        right -= 1
    
    return True
```

## Step-by-Step Example
```
s = "a + 2 c ! 2 a"

Step 1: left=0(a), right=12(a) → match → move inward
Step 2: left=1(+), skip non-alphanumeric → left=2(2)  
        right=11(2) → match → move inward
Step 3: left=3( ), skip → left=4(c)
        right=10( ), skip → right=8(2)
Step 4: left=4(c), right=8(2) → mismatch → return False
```

## Exit Conditions

### Odd Length (has middle char)
```
"racecar" → left meets right at 'e'
```

### Even Length (no middle char)  
```
"abba" → left crosses right
```

**Solution**: `while left < right` handles both cases

## Edge Cases

| Input | Output | Notes |
|-------|--------|-------|
| `""` | `True` | Empty string |
| `"a"` | `True` | Single char |
| `"aa"` | `True` | Two same chars |
| `"ab"` | `False` | Two different chars |
| `"!, (?)"` | `True` | Only non-alphanumeric |
| `"12.02.2021"` | `True` | Numbers + punctuation |
| `"A man, a plan, a canal: Panama"` | `True` | Classic example |

## Character Handling

### Alphanumeric Check
```python
char.isalnum()  # Returns True for a-z, A-Z, 0-9
```

### Case Insensitive
```python
s[left].lower() == s[right].lower()
```

### Skip Logic
```python
# Skip from left
while left < right and not s[left].isalnum():
    left += 1

# Skip from right  
while left < right and not s[right].isalnum():
    right -= 1
```

## Complexity Analysis

| Aspect | Complexity | Explanation |
|--------|------------|-------------|
| **Time** | O(n) | Single pass, each char visited once |
| **Space** | O(1) | Only two pointer variables |

## Interview Tips

### Clarification Questions
✅ **Case sensitive?** Usually no  
✅ **What counts as alphanumeric?** Letters + digits  
✅ **Empty string palindrome?** Usually yes  
✅ **Use built-in functions?** Ask interviewer

### Implementation Variations
1. **Two-pass**: Clean string first, then check
2. **One-pass**: Skip non-alphanumeric on-the-fly (better)
3. **Recursive**: Less efficient but demonstrates recursion

### Built-in Functions to Confirm
- `str.isalnum()` - check alphanumeric
- `str.lower()` - case conversion
- Alternative: implement manually if requested

## Pattern Recognition
🔍 **"Check palindrome"** → Two pointers inward traversal  
🔍 **"Ignore certain chars"** → Skip logic while moving  
🔍 **String comparison** → Case handling important

## Common Mistakes
❌ Not handling case sensitivity  
❌ Forgetting to skip non-alphanumeric  
❌ Wrong exit condition (should be `left < right`)  
❌ Not checking bounds in skip loops

## Extensions
- **Palindrome Permutation**: Can rearrange to form palindrome?
- **Valid Palindrome II**: Remove at most one character
- **Longest Palindromic Substring**: Find longest palindrome within string
