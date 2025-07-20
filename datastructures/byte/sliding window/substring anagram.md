# Substring Anagrams - Revision Notes

## 🎯 Problem
Count the number of substrings in string `s` that are anagrams of string `t`.

**Example:**
```
s = "caabab", t = "aba"
Output: 2

Anagrams found:
- Index 1: "aab" (anagram of "aba") 
- Index 2: "aba" (anagram of "aba")
```

## 💡 Core Strategy
**Fixed Sliding Window + Frequency Counting:**
- **Window size**: Fixed at `len(t)` 
- **Frequency arrays**: Compare character counts instead of actual strings
- **Anagram condition**: Same character frequencies, order doesn't matter

## ⚡ Algorithm Steps

1. **Setup frequency array for target:**
   ```python
   expected_freqs = [0] * 26  # For 'a' to 'z'
   for c in t:
       expected_freqs[ord(c) - ord('a')] += 1
   ```

2. **Slide fixed-size window:**
   ```python
   window_freqs = [0] * 26
   left = right = 0
   
   while right < len(s):
       # Add character at right
       window_freqs[ord(s[right]) - ord('a')] += 1
       
       # Check if window reached target size
       if right - left + 1 == len(t):
           if window_freqs == expected_freqs:
               count += 1
           
           # Remove character at left before sliding
           window_freqs[ord(s[left]) - ord('a')] -= 1
           left += 1
       
       right += 1
   ```

## 📝 Complete Implementation
```python
def findAnagrams(s, t):
    len_s, len_t = len(s), len(t)
    
    # Early exit optimization
    if len_t > len_s:
        return 0
    
    count = 0
    expected_freqs = [0] * 26  # Target string frequencies
    window_freqs = [0] * 26    # Current window frequencies
    
    # Build frequency array for target string
    for c in t:
        expected_freqs[ord(c) - ord('a')] += 1
    
    left = right = 0
    
    while right < len_s:
        # Add current character to window
        window_freqs[ord(s[right]) - ord('a')] += 1
        
        # If window reaches target size
        if right - left + 1 == len_t:
            # Check if current window is anagram
            if window_freqs == expected_freqs:
                count += 1
            
            # Slide window: remove left character
            window_freqs[ord(s[left]) - ord('a')] -= 1
            left += 1
        
        right += 1
    
    return count
```

## 🔑 Key Implementation Details

### **Character to Index Conversion:**
```python
index = ord(c) - ord('a')  # Maps 'a'→0, 'b'→1, ..., 'z'→25
```

### **Fixed Window Logic:**
```python
# Window size check
if right - left + 1 == len_t:
    # Process window
    # Then slide by removing left and advancing left pointer
```

### **Frequency Array Comparison:**
```python
# Python allows direct array comparison
if window_freqs == expected_freqs:  # O(26) = O(1)
    count += 1
```

## 🚨 Critical Details

### **Window Management:**
- **Add character**: Always add `s[right]` to window first
- **Check size**: Only process when window reaches `len_t`
- **Remove character**: Remove `s[left]` before advancing left pointer

### **Edge Case:**
```python
if len_t > len_s:
    return 0  # Impossible to form anagram
```

## 📊 Complexity Analysis
- **Time**: O(n) where n = len(s)
  - Single pass through string s
  - Array comparison is O(26) = O(1) per iteration
- **Space**: O(1) - Two fixed arrays of size 26

## 🔥 Why This Works

### **Anagram Property:**
```
Two strings are anagrams ⟺ Same character frequencies
"abc" and "bca" both have: a=1, b=1, c=1
```

### **Fixed Window Insight:**
```
All anagrams of string t must have exactly len(t) characters
So we only need to check substrings of length len(t)
```

## 🚨 **Critical Optimization Insight**

### **❌ Naive Approach (What You Might Think Initially):**
```python
# For each window position:
for i in range(len(s) - len(t) + 1):
    substring = s[i:i+len(t)]
    
    # Recalculate frequency map for entire substring
    current_freqs = [0] * 26
    for c in substring:
        current_freqs[ord(c) - ord('a')] += 1
    
    if current_freqs == expected_freqs:
        count += 1
# Time: O(n * m) where n=len(s), m=len(t)
```

### **✅ Sliding Window Optimization:**
```python
# Maintain running frequency map as window slides
window_freqs = [0] * 26

while right < len(s):
    # Add new character entering window
    window_freqs[ord(s[right]) - ord('a')] += 1
    
    if right - left + 1 == len(t):
        # Check frequency map (already maintained)
        if window_freqs == expected_freqs:
            count += 1
        
        # Remove character leaving window  
        window_freqs[ord(s[left]) - ord('a')] -= 1
        left += 1
# Time: O(n) - each character added/removed exactly once
```

### **🔑 Key Realization:**
```
Don't recalculate frequency map for each window!
Instead: Maintain running count by adding/subtracting as window slides

Window: "abc" → "bcd"
Instead of: Count all chars in "bcd" 
Do: Remove 'a', Add 'd' to existing counts
```

## 💭 Mental Models

### **"Frequency Fingerprint":**
```
Each string has a unique frequency "fingerprint"
Compare fingerprints instead of rearranging characters
```

### **"Sliding Scanner":**
```
Window acts like a scanner moving across string
Scanner maintains running count of characters it sees
```

## 🎪 Quick Memory Tricks
- **"Fixed Window Size"** - Always len(t), never varies
- **"Frequency Match"** - Arrays must be identical  
- **"Add Right, Remove Left"** - Window sliding pattern
- **"26 Letters Max"** - Constant space for frequency arrays

## 🏆 Interview Tips

### **Explain the Insight:**
```
"Instead of generating all permutations of t and searching for them,
I'll use the fact that anagrams have identical character frequencies."
```

### **Walk Through Example:**
```
s = "abab", t = "ab"
expected_freqs: a=1, b=1

Window "ab": a=1, b=1 ✅ Match!
Window "ba": a=1, b=1 ✅ Match!
Result: 2 anagrams
```

### **Mention Optimization:**
- "I use arrays instead of hash maps since we only have 26 lowercase letters"
- "Fixed window size makes this more efficient than checking all substrings"

## ⚠️ Common Mistakes
❌ **Variable window size** → Should be fixed at len(t)  
❌ **Checking all substrings** → Only need substrings of length len(t)  
❌ **Wrong sliding logic** → Must remove left before advancing left pointer  
❌ **Using hash maps** → Arrays are more efficient for 26 letters  
❌ **Forgetting edge case** → When len(t) > len(s)

## 🔧 Template Pattern
```python
def slidingWindowAnagram(s, pattern):
    if len(pattern) > len(s):
        return 0
    
    # Setup frequency arrays
    expected = [0] * 26
    window = [0] * 26
    
    # Build expected frequencies
    for c in pattern:
        expected[ord(c) - ord('a')] += 1
    
    # Slide window
    left = right = 0
    count = 0
    
    while right < len(s):
        # Expand window
        window[ord(s[right]) - ord('a')] += 1
        
        # Check if window is correct size
        if right - left + 1 == len(pattern):
            if window == expected:
                count += 1
            
            # Contract window
            window[ord(s[left]) - ord('a')] -= 1
            left += 1
        
        right += 1
    
    return count
```
