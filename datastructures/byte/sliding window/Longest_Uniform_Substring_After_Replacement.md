# Longest Uniform Substring After Replacements - Revision Notes

## 🎯 Problem
Find length of longest uniform substring (all characters identical) that can be formed by replacing up to `k` characters.

**Example:**
```
s = "aabcdcca", k = 2
Output: 5
Explanation: Replace 'b' and 'd' with 'c' to get "ccccc"
```

## 💡 Intuition

### **Uniform Substring Strategy:**
- **Uniform substring** = all characters are identical
- **Key insight**: To make substring uniform with minimum replacements, replace all characters **except the most frequent one**

### **Replacement Calculation:**
```
num_chars_to_replace = len(substring) - highest_freq
```
- **Valid window**: `num_chars_to_replace ≤ k`
- **Invalid window**: `num_chars_to_replace > k`

### **Example Analysis:**
```
Substring: "abaabac"
Frequencies: a=4, b=2, c=1
Most frequent: 'a' (freq=4)
Replacements needed: 7 - 4 = 3
If k ≥ 3: Can make uniform, otherwise cannot
```

### **Dynamic Sliding Window:**
- **Condition**: Find longest substring where `num_chars_to_replace ≤ k`
- **Expand**: When window is valid (condition met)
- **Slide**: When window is invalid (condition violated)

### **Critical Optimization:**
- **Don't shrink window** when invalid - just **slide it**
- **Reasoning**: Looking for longest substring, so shorter windows won't give better results
- **Slide vs Shrink**: Maintain window length when sliding

### **Algorithm Components:**
- **freqs**: Hash map to track character frequencies in current window
- **highest_freq**: Track highest frequency seen so far
- **left/right**: Window boundaries

## ⚡ Implementation

```python
def longest_uniform_substring_after_replacements(s: str, k: int) -> int:
    freqs = {}
    highest_freq = max_len = 0
    left = right = 0
    while right < len(s):
        # Update the frequency of the character at the right pointer and the highest
        # frequency for the current window.
        freqs[s[right]] = freqs.get(s[right], 0) + 1
        highest_freq = max(highest_freq, freqs[s[right]])
        # Calculate replacements needed for the current window.
        num_chars_to_replace = (right - left + 1) - highest_freq
        # Slide the window if the number of replacements needed exceeds 'k'.
        # The right pointer always gets advanced, so we just need to advance 'left'.
        if num_chars_to_replace > k:
            # Remove the character at the left pointer from the hash map before
            # advancing the left pointer.
            freqs[s[left]] -= 1
            left += 1
        # Since the length of the current window increases or stays the same, assign
        # the length of the current window to 'max_len'.
        max_len = right - left + 1
        # Expand the window.
        right += 1
    return max_len
```

## 🔑 Key Implementation Details

### **Frequency Tracking:**
```python
# Add character to window
freqs[s[right]] = freqs.get(s[right], 0) + 1
highest_freq = max(highest_freq, freqs[s[right]])

# Remove character from window (when sliding)
freqs[s[left]] -= 1
```

### **Window Validation:**
```python
num_chars_to_replace = (right - left + 1) - highest_freq
if num_chars_to_replace > k:
    # Slide window (don't shrink)
    left += 1
```

### **Length Tracking:**
```python
# Window length either increases or stays same
max_len = right - left + 1
```

## 🚨 Critical Details

### **Slide vs Shrink Logic:**
- **Traditional approach**: Shrink window until valid again
- **Optimized approach**: Slide window to maintain length
- **Why it works**: We want longest substring, so maintaining max length seen is sufficient

### **highest_freq Behavior:**
- **Only increases or stays same** - never decreases during algorithm
- **This is intentional** - we're tracking the best frequency seen, not current window's actual max frequency
- **Works because** we slide (not shrink) when window becomes invalid

### **Window States:**
1. **Valid window** (`num_chars_to_replace ≤ k`): Expand right pointer
2. **Invalid window** (`num_chars_to_replace > k`): Slide both pointers

## 📊 Complexity Analysis

**Time Complexity: O(n)**
- Single pass through string with two pointers
- Each character added and removed at most once

**Space Complexity: O(m)**
- Hash map stores at most m unique characters
- m ≤ 26 for lowercase letters (effectively O(1))

## 💭 Mental Models

### **"Keep the Majority, Replace the Rest":**
```
Window: "aabaa" 
Majority: 'a' (freq=4)
Replace: 'b' (count=1)
Result: "aaaaa" with 1 replacement
```

### **"Sliding Window with Memory":**
```
Track best frequency seen (highest_freq)
When window invalid: slide to maintain best length found
Never shrink below best length discovered
```

## 🎪 Quick Memory Tricks
- **"Replace Minority Characters"** - Keep most frequent, replace others
- **"Slide Don't Shrink"** - Maintain window length when invalid
- **"Frequency Memory"** - highest_freq never decreases
- **"Length Monotonic"** - max_len only increases or stays same

## 🏆 Interview Tips

### **Explain the Core Insight:**
```
"To make a substring uniform with minimum replacements,
keep the most frequent character and replace all others."
```

### **Highlight the Optimization:**
```
"Instead of shrinking when window becomes invalid,
I slide it to maintain the maximum length found so far."
```

### **Walk Through Example:**
```
s = "aabccc", k = 2

Window grows: "a" → "aa" → "aab" → "aabc" 
At "aabc": need 3 replacements > k=2, so slide
Continue: "abcc" → "abccc" 
Final: length 5 with "ccccc" (replace 'a','b')
```

## ⚠️ Common Mistakes
❌ **Shrinking window when invalid** → Should slide to maintain max length  
❌ **Decreasing highest_freq** → Should only increase or stay same  
❌ **Wrong replacement calculation** → Use `window_length - highest_freq`  
❌ **Not updating freqs when sliding** → Must remove left character from hash map
