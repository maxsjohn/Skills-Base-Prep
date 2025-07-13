# Longest Consecutive Sequence - Complete Revision Guide

## 🎯 Problem Statement

Find the longest chain of consecutive numbers in an unsorted array. Numbers are consecutive if they differ by 1.

**Example:**
```
Input: nums = [1, 6, 2, 5, 8, 7, 10, 3]
Output: 4
Explanation: Longest consecutive sequence is [5, 6, 7, 8]
```

## 💡 Optimal Solution - O(n) Time

```python
def longestConsecutive(nums: List[int]) -> int:
    if not nums:
        return 0
    
    num_set = set(nums)  # O(n) space for O(1) lookups
    longest_chain = 0
    
    # ⚠️ CRITICAL: Iterate over num_set, NOT nums!
    for num in num_set:
        # Only start searching if this is the beginning of a sequence
        if num - 1 not in num_set:
            current_num = num
            current_chain = 1
            
            # Find the rest of the sequence
            while current_num + 1 in num_set:
                current_num += 1
                current_chain += 1
                
            longest_chain = max(longest_chain, current_chain)
    
    return longest_chain
```

## 🧠 Core Intuition

**Key Insight:** Only search for sequences starting from their **smallest number**. This ensures each number is visited exactly once.

## 📊 Visual Breakdown

### **Identifying Sequence Starts:**
```
Array: [1, 6, 2, 5, 8, 7, 10, 3]
Set:   {1, 2, 3, 5, 6, 7, 8, 10}

For each number, check if it's a sequence start:
- 1: Is (1-1=0) in set? NO ✅ → Start of sequence [1, 2, 3]
- 2: Is (2-1=1) in set? YES ❌ → Skip (part of sequence starting at 1)  
- 3: Is (3-1=2) in set? YES ❌ → Skip
- 5: Is (5-1=4) in set? NO ✅ → Start of sequence [5, 6, 7, 8]
- 6: Is (6-1=5) in set? YES ❌ → Skip
- 7: Is (7-1=6) in set? YES ❌ → Skip  
- 8: Is (8-1=7) in set? YES ❌ → Skip
- 10: Is (10-1=9) in set? NO ✅ → Start of sequence [10]

Result: Max length = 4 from sequence [5, 6, 7, 8]
```

## 🔑 Algorithm Steps

1. **Convert to Set:** `num_set = set(nums)` for O(1) lookups
2. **Find Sequence Starts:** For each number, check if `num - 1` exists
3. **Expand Sequence:** If it's a start, keep adding `num + 1` while it exists
4. **Track Maximum:** Keep track of the longest sequence found

## ⚡ Critical Performance Insight

### **🚨 MAJOR PITFALL - Iterate over SET, not original array!**

```python
# ❌ WRONG - Can cause TLE with duplicates:
for num in nums:  # Processes duplicates multiple times!
    if num - 1 not in num_set:
        # ... rest of logic

# ✅ CORRECT - Each unique number processed once:
for num in num_set:  # Each unique number processed exactly once
    if num - 1 not in num_set:
        # ... rest of logic
```

### **Why This Matters:**
```python
# Example with many duplicates:
nums = [1, 1, 1, 1, 1, 2, 3, 4] * 1000  # 8000 elements, but only 4 unique

# Wrong approach: Processes sequence [1,2,3,4] 1000 times!
# Correct approach: Processes sequence [1,2,3,4] only 1 time!
```

## 🎯 Alternative Approaches

### **Approach 1: Sorting - O(n log n)**
```python
def longestConsecutive(nums):
    if not nums:
        return 0
    
    nums.sort()
    longest = current = 1
    
    for i in range(1, len(nums)):
        if nums[i] != nums[i-1]:  # Skip duplicates
            if nums[i] == nums[i-1] + 1:
                current += 1
            else:
                longest = max(longest, current)
                current = 1
    
    return max(longest, current)
```

### **Approach 2: Brute Force - O(n³)**
```python
def longestConsecutive(nums):
    longest = 0
    for num in nums:
        current_num = num
        current_length = 1
        
        while current_num + 1 in nums:  # O(n) lookup each time
            current_num += 1
            current_length += 1
            
        longest = max(longest, current_length)
    
    return longest
```

## 📈 Complexity Analysis

| Approach | Time | Space | Notes |
|----------|------|-------|-------|
| **Optimal (Hash Set)** | **O(n)** | **O(n)** | Each number visited once |
| Sorting | O(n log n) | O(1) | Good if space is limited |
| Brute Force | O(n³) | O(1) | Too slow for large inputs |

## 🚨 Edge Cases & Gotchas

1. **Empty Array:** `nums = []` → return `0`
2. **Single Element:** `nums = [1]` → return `1`  
3. **No Consecutive:** `nums = [1, 3, 5, 7]` → return `1`
4. **All Consecutive:** `nums = [1, 2, 3, 4]` → return `4`
5. **Duplicates:** `nums = [1, 2, 0, 1]` → return `3` (sequence: [0,1,2])
6. **Negative Numbers:** `nums = [-1, 0, 1]` → return `3`

## 💭 Mental Models

### **"Start Only" Strategy:**
```
Think: "I only care about the BEGINNING of each sequence"
- If I see 5, and 4 exists, then 5 is NOT a beginning
- If I see 5, and 4 doesn't exist, then 5 IS a beginning
```

### **"Union of Sequences" Model:**
```
Array: [1, 6, 2, 5, 8, 7, 10, 3]

Sequences found:
[1] → [1, 2] → [1, 2, 3]     (length 3)
[5] → [5, 6] → [5, 6, 7] → [5, 6, 7, 8]  (length 4) ✅
[10]  (length 1)

Max = 4
```

## 🔥 Key Insights for Revision

1. **"Hash Set for Speed"** - Convert array to set for O(1) lookups
2. **"Start Detection"** - Only begin search when `num - 1` doesn't exist
3. **"Iterate on Set"** - Use `num_set` not `nums` to avoid duplicate processing
4. **"Each Number Once"** - Algorithm ensures each number visited exactly once
5. **"Negative Numbers OK"** - Algorithm works with any integers

## 🎪 Quick Memory Tricks

- **"Set and Forget"** - Convert to set, then forget the original array
- **"Start Smart"** - Only start from sequence beginnings  
- **"One Pass Wonder"** - Each number gets processed exactly once
- **"Duplicate Trap"** - Remember to iterate over set, not original array

## 🏆 Interview Tips

- **Start with brute force** to show you understand the problem
- **Mention O(n log n) sorting** as an intermediate solution  
- **Explain the O(n) optimization** - this is the key insight
- **Emphasize the "start detection" logic** - this shows deep understanding
- **Mention the duplicate processing pitfall** - shows practical coding experience

## ⚠️ Common Mistakes

❌ **Iterating over original array instead of set** (causes TLE with duplicates)  
❌ **Not checking if number is sequence start** (leads to O(n²) complexity)  
❌ **Forgetting to handle empty array edge case**  
❌ **Using list instead of set for lookups** (O(n) vs O(1) per lookup)
