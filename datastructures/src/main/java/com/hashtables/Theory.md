# Python Sets - DSA Interview Guide

## Core Properties
- **Unordered** and **unique elements only**
- **O(1)** average time for add, remove, lookup
- **Mutable** (unlike frozenset)

## Critical Operations

### Creation & Basic Ops:
```python
s = set([1, 2, 2, 3])  # {1, 2, 3} - duplicates removed
s.add(4)              # O(1)
s.remove(2)           # O(1) - KeyError if not exists
s.discard(2)          # O(1) - no error if not exists
len(s)                # O(1)
x in s                # O(1) - this is the magic!
```

## Set Operations (Interview Gold)

```python
a = {1, 2, 3}
b = {3, 4, 5}

# Union
a | b           # {1, 2, 3, 4, 5}
a.union(b)

# Intersection  
a & b           # {3}
a.intersection(b)

# Difference
a - b           # {1, 2} (in a but not b)
a.difference(b)

# Symmetric Difference
a ^ b           # {1, 2, 4, 5} (in either but not both)
```

## Common DSA Patterns

### 1. Remove Duplicates:
```python
nums = [1, 2, 2, 3, 1]
unique = list(set(nums))  # [1, 2, 3]
```

### 2. Fast Lookup/Membership:
```python
# Instead of: if x in list  # O(n)
# Use: if x in set_version  # O(1)
```

### 3. Two Sum Pattern:
```python
def two_sum(nums, target):
    seen = set()
    for num in nums:
        if target - num in seen:  # O(1) lookup!
            return True
        seen.add(num)
```

### 4. Find Missing Numbers:
```python
def find_missing(nums, n):
    expected = set(range(1, n+1))
    actual = set(nums)
    return expected - actual  # Missing numbers
```

## Interview Tricks

### Convert for O(1) lookups:
```python
# Slow
if x in list_of_items:  # O(n)

# Fast  
item_set = set(list_of_items)
if x in item_set:  # O(1)
```

### Check for duplicates:
```python
def has_duplicates(arr):
    return len(arr) != len(set(arr))
```

### Set Comprehension:
```python
squares = {x**2 for x in range(10)}
```

## Key Interview Points
- **When to use**: Need fast membership testing, remove duplicates, set operations
- **Time complexity**: O(1) for add/remove/lookup (average case)
- **Space**: O(n) where n is number of elements
- **Gotcha**: Elements must be **hashable** (no lists/dicts inside sets)

## Common Mistakes to Avoid
```python
# Wrong - can't add unhashable types
s.add([1, 2])     # TypeError!
s.add({1: 2})     # TypeError!

# Right
s.add((1, 2))     # Tuples are hashable
s.add("string")   # Strings are hashable
```

## Memory Trick
Think "set = fast lookup table" - whenever you need to check "is this element present?" multiple times, convert to set first!

## Additional Set Methods
```python
s1.issubset(s2)        # s1 <= s2
s1.issuperset(s2)      # s1 >= s2
s1.isdisjoint(s2)      # No common elements
s.copy()               # Shallow copy
s.clear()              # Remove all elements
s.pop()                # Remove and return arbitrary element
```

## Performance Comparison
| Operation | List | Set |
|-----------|------|-----|
| Membership (`x in container`) | O(n) | O(1) |
| Add element | O(1) | O(1) |
| Remove element | O(n) | O(1) |
| Union of two containers | O(n²) | O(n) |

---

**This covers 90% of set usage in coding interviews. The O(1) lookup is your superpower!**
