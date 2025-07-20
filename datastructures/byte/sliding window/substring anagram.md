# Substring Anagrams - Revision Notes

## 🎯 Problem
Count substrings in `s` that are anagrams of `t`.

**Example:**
```
s = "caabab", t = "aba"  
Output: 2
Explanation: Anagram at index 1 ("aab") and index 2 ("aba")
```

## 💡 Intuition

### **Reframe Anagram Definition:**
- Anagram = substring with **exactly same characters** as `t` in **any order**
- Substring must have **same length** as `t` (len_t)
- Only need to examine substrings of **fixed length** len_t

### **Why Fixed Sliding Window:**
- Window of len_t **guaranteed to slide through every substring** of this length
- Saves from examining every possible substring

### **Frequency Comparison:**
- In anagram, **order doesn't matter** - only **frequency of each letter**
- Compare character frequencies instead of rearranging letters
- Use **array of size 26** for lowercase letters ('a' = index 0, 'b' = index 1, etc.)

### **Algorithm Components:**
- **expected_freqs**: Store frequencies of characters in string `t`
- **window_freqs**: Track frequencies of characters in current window  
- **left/right pointers**: Define window boundaries
- **count**: Number of anagrams detected

### **Two-Phase Process:**
1. **Expand window** to fixed length len_t by advancing right pointer
2. **Slide window** across string, checking each position

## ⚡ Implementation

```python
def substring_anagrams(s: str, t: str) -> int:
    len_s, len_t = len(s), len(t)
    if len_t > len_s:
        return 0
    count = 0
    expected_freqs, window_freqs = [0] * 26, [0] * 26
    # Populate 'expected_freqs' with the characters in string 't'.
    for c in t:
        expected_freqs[ord(c) - ord('a')] += 1
    left = right = 0
    while right < len_s:
        # Add the character at the right pointer to 'window_freqs' before sliding the
        # window.
        window_freqs[ord(s[right]) - ord('a')] += 1
        # If the window has reached the expected fixed length, we advance the left
        # pointer as well as the right pointer to slide the window.
        if right - left + 1 == len_t:
            if window_freqs == expected_freqs:
                count += 1
            # Remove the character at the left pointer from 'window_freqs' before
            # advancing the left pointer.
            window_freqs[ord(s[left]) - ord('a')] -= 1
            left += 1
        right += 1
    return count
```

## 🔑 Key Implementation Details

### **Character to Index Mapping:**
```python
ord(character) - ord('a')  # Maps 'a'→0, 'b'→1, ..., 'z'→25
```

### **Window Process:**
1. **Add** character at right pointer to `window_freqs`
2. **Check** if window reached fixed length len_t
3. **Compare** frequency arrays (O(1) - only 26 comparisons)
4. **Remove** character at left pointer before advancing left

### **Sliding Logic:**
- Advance **right pointer** every iteration
- Advance **left pointer** only when window = len_t
- Keep `window_freqs` updated by increment/decrement

## 📊 Complexity Analysis

**Time Complexity: O(n)** where n = length of s
- Populating `expected_freqs`: O(m) where m = length of t
- Traversing string s: O(n) with two pointers  
- Array comparison: O(1) per iteration (26 elements only)
- Since m ≤ n, overall is O(n)

**Space Complexity: O(1)**
- Two frequency arrays of size 26 (constant space)

## 🚨 Key Optimizations

### **Early Return:**
```python
if len_t > len_s:
    return 0  # Impossible to form anagram
```

### **Constant Time Comparison:**
- Frequency array comparison takes O(1) (26 comparisons)
- Much better than generating all permutations of t
