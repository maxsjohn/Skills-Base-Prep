# Hashmap Key Design Cheatsheet

## Core Concept
```python
# Rule 1: Same group → Same key
# Rule 2: Different groups → Different keys
```

## Key Design Patterns

1. **Anagrams Problem** (Group Anagrams)
   - Key: Sorted string
   - ```python
     key = ''.join(sorted(s))
     ```
   - Correlates: 49. Group Anagrams

2. **Frequency Map Problems**
   - Key: Tuple of character counts
   - ```python
     key = tuple(sorted(Counter(s).items()))
     ```
   - Correlates: 438. Find All Anagrams in String

3. **Coordinate/Point Problems**
   - Key: Stringified slope/intercept
   - ```python
     # For line grouping
     key = f"{slope},{intercept}"
     ```
   - Correlates: 149. Max Points on a Line

4. **Array/Sequence Problems**
   - Key: Relative difference string
   - ```python
     # For isomorphic sequences
     key = ' '.join([str(ord(s[i])-ord(s[i-1])) for i in range(1,len(s))])
     ```
   - Correlates: 205. Isomorphic Strings

## Quick Solutions Bank

### 49. Group Anagrams
```python
def groupAnagrams(strs):
    d = defaultdict(list)
    for s in strs:
        key = ''.join(sorted(s))
        d[key].append(s)
    return list(d.values())
```

### 350. Intersection of Two Arrays II
```python
def intersect(nums1, nums2):
    if len(nums1) > len(nums2):
        return intersect(nums2, nums1)
    freq = Counter(nums1)
    return [x for num in nums2 if freq[num] > 0 and not freq.subtract([num])]
```

### 205. Isomorphic Strings
```python
def isIsomorphic(s, t):
    return len(set(zip(s,t))) == len(set(s))) == len(set(t)))
```

## Pro Tips
1. For counting problems → `Counter` is your friend
2. For order-insensitive → `sorted()` as key
3. For sequence patterns → store relative differences
4. Always verify both key design rules with test cases

## Correlation Matrix
| Problem Type          | Key Strategy          | Example Problems |
|-----------------------|-----------------------|------------------|
| Anagrams              | Sorted string         | 49, 242          |
| Frequency Matching    | Count tuples          | 350, 383         |
| Sequence Patterns     | Relative differences  | 205, 290         |
| Geometric Grouping    | Stringified equations | 149, 939         |
