# Longest Substring With Unique Characters - Revision Notes

## 🎯 Problem
Find length of longest substring with all unique characters.

**Example:**
```
s = "abcba"
Output: 3
Explanation: "abc" is longest substring with unique characters
```

## 💡 Intuition

### **Brute Force Analysis:**
- Check all possible substrings: O(n²) substrings
- Check uniqueness for each: O(n) using hash set
- **Total: O(n³)** - too slow!

### **Sliding Window Insight:**
- Looking for **longest substring** satisfying condition (unique characters)
- Use **dynamic sliding window** approach
- Two window states:
  1. **Valid**: Contains only unique characters → **expand window**
  2. **Invalid**: Contains duplicates → **shrink window**

### **Window Management:**
- **Expand**: Advance right pointer when no duplicates
- **Shrink**: Advance left pointer when duplicate found
- Use **hash set** to track characters in current window

### **Algorithm Flow:**
1. Initialize left=0, right=0, hash_set=empty
2. Advance right pointer, add character to hash_set
3. If duplicate found, shrink from left until valid
4. Track maximum window size seen

## ⚡ Basic Implementation

```python
def longest_substring_with_unique_chars(s: str) -> int:
    max_len = 0
    hash_set = set()
    left = right = 0
    while right < len(s):
        # If we encounter a duplicate character in the window, shrink the window until
        # it's no longer a duplicate.
        while s[right] in hash_set:
            hash_set.remove(s[left])
            left += 1
        # Once there are no more duplicates in the window, update 'max_len' if the
        # current window is larger.
        max_len = max(max_len, right - left + 1)
        hash_set.add(s[right])
        # Expand the window.
        right += 1
    return max_len
```

## 🚀 Optimized Implementation

```python
def longest_substring_with_unique_chars_optimized(s: str) -> int:
    max_len = 0
    prev_indexes = {}
    left = right = 0
    while right < len(s):
        # If a previous index of the current character is present in the current
        # window, it's a duplicate character in the window.
        if s[right] in prev_indexes and prev_indexes[s[right]] >= left:
            # Shrink the window to exclude the previous occurrence of this character.
            left = prev_indexes[s[right]] + 1
        # Update 'max_len' if the current window is larger.
        max_len = max(max_len, right - left + 1)
        prev_indexes[s[right]] = right
        # Expand the window.
        right += 1
    return max_len
```

## 🔑 Key Implementation Details

### **Basic Approach (Hash Set):**
- **Expand**: Always advance right pointer
- **Shrink**: When duplicate found, keep advancing left until window valid
- **Track**: Use set to store characters in current window

### **Optimized Approach (Hash Map):**
- **Smart Shrinking**: Jump left pointer directly past previous occurrence
- **Index Tracking**: Store previous index of each character
- **Window Check**: Ensure previous index is within current window

### **Window Validation:**
```python
# Basic: Remove characters one by one from left
while s[right] in hash_set:
    hash_set.remove(s[left])
    left += 1

# Optimized: Jump directly to correct position
if s[right] in prev_indexes and prev_indexes[s[right]] >= left:
    left = prev_indexes[s[right]] + 1
```

## 📊 Complexity Analysis

**Both Approaches:**
- **Time**: O(n) - Each character visited at most twice
- **Space**: O(m) where m = number of unique characters

**Space Breakdown:**
- **Basic**: Hash set stores characters in current window
- **Optimized**: Hash map stores index of each character

## 🚨 Critical Details

### **Window State Management:**
1. **Expand Phase**: Add character at right, advance right pointer
2. **Check Phase**: Detect if current window has duplicates
3. **Shrink Phase**: Remove duplicates by advancing left pointer
4. **Update Phase**: Track maximum window size

### **Optimization Key Insight:**
```
Instead of: Advance left one by one until duplicate removed
Do: Jump left directly past the previous occurrence

Example: "abcba" 
When right hits 'b' at index 3:
- Basic: left goes 0→1→2 (removing 'a', 'b')  
- Optimized: left jumps directly to 2 (past previous 'b')
```

### **Edge Cases:**
- Empty string: Return 0
- Single character: Return 1  
- All unique characters: Return string length
- All same characters: Return 1

## 💭 Mental Models

### **"Expanding and Contracting Window":**
```
Valid window: [a|b|c] → Expand
Invalid window: [a|b|c|b] → Contract from left until valid
```

### **"Previous Index Optimization":**
```
When duplicate found at right:
- Find where it appeared before
- Jump left pointer past that position
- No need to check characters in between
```

## 🎪 Quick Memory Tricks
- **"Unique Window Sliding"** - Core sliding window application
- **"Set for Current, Map for Optimization"** - Two data structure approaches
- **"Jump vs Step"** - Optimization jumps directly vs stepping one by one
- **"Track Max Always"** - Update maximum length at each valid window

## 🏆 Interview Tips

### **Start with Basic Approach:**
```
"I'll use sliding window with a hash set to track characters.
When I find a duplicate, I'll shrink from the left until valid."
```

### **Explain the Optimization:**
```
"Instead of shrinking one character at a time, I can jump directly
to the position after the previous occurrence of the duplicate character."
```

### **Walk Through Example:**
```
s = "abcba"
Basic approach: Window grows [a] → [ab] → [abc] → [bc] → [cb] → [cba]
Optimized: Same result but fewer left pointer movements
```

## ⚠️ Common Mistakes
❌ **Not updating max_len correctly** → Update after ensuring window is valid  
❌ **Wrong optimization condition** → Check `prev_indexes[s[right]] >= left`  
❌ **Forgetting to update hash map** → Always store current index  
❌ **Off-by-one errors** → Use `prev_indexes[s[right]] + 1` for left pointer
