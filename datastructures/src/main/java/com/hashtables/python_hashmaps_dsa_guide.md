# Python Dictionaries/Hashmaps - DSA Interview Guide

## Core Properties
- **Key-Value pairs** with **unique keys**
- **O(1)** average time for access, insertion, deletion
- **Insertion-ordered** (Python 3.7+)
- **Mutable** and **dynamic sizing**
- Keys must be **hashable** (immutable types)

## Critical Operations

### Creation & Basic Ops:
```python
# Creation
d = {}
d = dict()
d = {'a': 1, 'b': 2}
d = dict([('a', 1), ('b', 2)])

# Access
d['key']           # O(1) - KeyError if not exists
d.get('key')       # O(1) - returns None if not exists
d.get('key', 0)    # O(1) - returns default if not exists

# Insertion/Update
d['key'] = value   # O(1)
d.update({'c': 3}) # O(k) where k is items being updated

# Deletion
del d['key']       # O(1) - KeyError if not exists
d.pop('key')       # O(1) - returns value, KeyError if not exists
d.pop('key', None) # O(1) - returns default if not exists
d.popitem()        # O(1) - removes and returns arbitrary (key, value)
d.clear()          # O(n) - removes all items
```

## Essential Methods

### Navigation & Inspection:
```python
d.keys()           # dict_keys object - all keys
d.values()         # dict_values object - all values  
d.items()          # dict_items object - (key, value) tuples
len(d)             # O(1) - number of key-value pairs
'key' in d         # O(1) - membership test
```

### Advanced Methods:
```python
# setdefault - get or set default
d.setdefault('key', []).append(1)  # Creates list if key doesn't exist

# get with computation
from collections import defaultdict
dd = defaultdict(list)  # Auto-creates lists for new keys
dd = defaultdict(int)   # Auto-creates 0 for new keys
```

## Common DSA Patterns

### 1. Frequency Counter:
```python
def count_frequency(arr):
    freq = {}
    for item in arr:
        freq[item] = freq.get(item, 0) + 1
    return freq

# Or using defaultdict
from collections import defaultdict
def count_frequency(arr):
    freq = defaultdict(int)
    for item in arr:
        freq[item] += 1
    return freq
```

### 2. Two Sum Problem:
```python
def two_sum(nums, target):
    seen = {}  # value -> index
    for i, num in enumerate(nums):
        complement = target - num
        if complement in seen:
            return [seen[complement], i]
        seen[num] = i
    return []
```

### 3. Group Anagrams:
```python
def group_anagrams(strs):
    groups = {}
    for s in strs:
        key = ''.join(sorted(s))  # Create signature
        if key not in groups:
            groups[key] = []
        groups[key].append(s)
    return list(groups.values())
```

### 4. LRU Cache Pattern:
```python
from collections import OrderedDict

class LRUCache:
    def __init__(self, capacity):
        self.cache = OrderedDict()
        self.capacity = capacity
    
    def get(self, key):
        if key in self.cache:
            self.cache.move_to_end(key)  # Mark as recently used
            return self.cache[key]
        return -1
    
    def put(self, key, value):
        if key in self.cache:
            self.cache.move_to_end(key)
        elif len(self.cache) >= self.capacity:
            self.cache.popitem(last=False)  # Remove oldest
        self.cache[key] = value
```

### 5. Sliding Window with HashMap:
```python
def longest_substring_without_repeating(s):
    char_index = {}
    left = max_len = 0
    
    for right, char in enumerate(s):
        if char in char_index and char_index[char] >= left:
            left = char_index[char] + 1
        char_index[char] = right
        max_len = max(max_len, right - left + 1)
    
    return max_len
```

## Dictionary Comprehensions
```python
# Basic
squares = {x: x**2 for x in range(5)}

# With condition  
even_squares = {x: x**2 for x in range(10) if x % 2 == 0}

# From two lists
keys = ['a', 'b', 'c']
values = [1, 2, 3]
d = {k: v for k, v in zip(keys, values)}

# Invert dictionary
original = {'a': 1, 'b': 2}
inverted = {v: k for k, v in original.items()}
```

## Interview Tricks

### Safe Key Access:
```python
# Instead of multiple if-checks
if 'key' in d:
    d['key'] += 1
else:
    d['key'] = 1

# Use setdefault or get
d['key'] = d.get('key', 0) + 1
d.setdefault('key', 0)
d['key'] += 1
```

### Merging Dictionaries:
```python
# Python 3.9+
d1 = {'a': 1, 'b': 2}
d2 = {'c': 3, 'd': 4}
merged = d1 | d2

# Python 3.5+
merged = {**d1, **d2}

# Traditional
merged = d1.copy()
merged.update(d2)
```

### Default Values Pattern:
```python
# Group items by some property
from collections import defaultdict

def group_by_length(words):
    groups = defaultdict(list)
    for word in words:
        groups[len(word)].append(word)
    return dict(groups)
```

## Key Interview Points
- **When to use**: Need fast key-based lookup, counting, grouping, caching
- **Time complexity**: O(1) average for get/set/delete operations
- **Space complexity**: O(n) where n is number of key-value pairs
- **Hash collisions**: Rare but can degrade to O(n) in worst case
- **Keys must be hashable**: strings, numbers, tuples (with hashable elements)

## Common Mistakes to Avoid
```python
# Wrong - unhashable key types
d[[1, 2]] = "value"     # TypeError: unhashable type: 'list'
d[{1, 2}] = "value"     # TypeError: unhashable type: 'set'

# Right - hashable keys
d[(1, 2)] = "value"     # Tuples are hashable
d["1,2"] = "value"      # Strings are hashable
d[frozenset([1, 2])] = "value"  # Frozensets are hashable

# Wrong - direct access without checking
value = d['key']  # KeyError if key doesn't exist

# Right - safe access
value = d.get('key', default_value)
if 'key' in d:
    value = d['key']
```

## Performance Comparison
| Operation | List/Array | Dictionary |
|-----------|------------|------------|
| Access by key/index | O(1) | O(1) |
| Search for value | O(n) | O(1) for key, O(n) for value |
| Insert | O(1) at end, O(n) elsewhere | O(1) |
| Delete | O(n) | O(1) |
| Memory usage | Lower | Higher (overhead) |

## Advanced Collections
```python
from collections import defaultdict, Counter, OrderedDict

# Counter - specialized dict for counting
counter = Counter(['a', 'b', 'a', 'c', 'b', 'a'])
# Counter({'a': 3, 'b': 2, 'c': 1})
counter.most_common(2)  # [('a', 3), ('b', 2)]

# OrderedDict - maintains insertion order (less needed in Python 3.7+)
od = OrderedDict([('a', 1), ('b', 2)])
od.move_to_end('a')  # Move key to end

# defaultdict - auto-creates missing values
dd = defaultdict(list)
dd['key'].append(1)  # Automatically creates empty list
```

## Memory Tricks
- **Dictionary = Fast Lookup Table**: Use when you need to map keys to values
- **"Get before Set"**: Always check/get before modifying to avoid KeyErrors
- **"Count with get(key, 0)"**: Standard pattern for frequency counting
- **"Group with defaultdict"**: Use defaultdict(list) for grouping patterns

---

**Hashmaps are the Swiss Army knife of DSA - master these patterns and you'll solve 60% of interview problems!**