# LRU Cache - Revision Notes

## 🎯 Problem
Design LRU cache with `get(key)` and `put(key, value)` operations in **O(1) time**.

## 💡 Core Strategy
**Doubly Linked List + HashMap**
- **HashMap**: O(1) key lookup → node reference
- **Doubly Linked List**: O(1) add/remove from both ends

## 🔑 Key Design Decisions

### **Node Structure**
```python
class DoublyLinkedListNode:
    def __init__(self, key, val):
        self.key = key      # Store key for deletion from hashmap
        self.val = val
        self.next = self.prev = None
```

### **Cache Layout**
```
head ↔ [LRU] ↔ [...] ↔ [MRU] ↔ tail
 ↑                               ↑
least recently used    most recently used
```

## ⚡ Core Operations

### **get(key) Logic**
1. Check if key in hashmap → return -1 if not found
2. **Move node to tail** (make it MRU)
3. Return node value

### **put(key, value) Logic**
1. If key exists → remove old node
2. Create new node, add to hashmap
3. **Check capacity** → evict LRU if needed
4. Add new node to tail

## 🛠 Helper Functions

### **add_to_tail(node)**
```python
def add_to_tail(self, node):
    prev_node = self.tail.prev
    node.prev = prev_node
    node.next = self.tail
    prev_node.next = node
    self.tail.prev = node
```

### **remove_node(node)**
```python
def remove_node(self, node):
    node.prev.next = node.next
    node.next.prev = node.prev
```

## 📝 Complete Implementation
```python
class LRUCache:
    def __init__(self, capacity):
        self.capacity = capacity
        self.hashmap = {}
        # Dummy head and tail
        self.head = DoublyLinkedListNode(-1, -1)
        self.tail = DoublyLinkedListNode(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def get(self, key):
        if key not in self.hashmap:
            return -1
        # Move to tail (make MRU)
        self.remove_node(self.hashmap[key])
        self.add_to_tail(self.hashmap[key])
        return self.hashmap[key].val
    
    def put(self, key, value):
        # Remove existing if present
        if key in self.hashmap:
            self.remove_node(self.hashmap[key])
        
        node = DoublyLinkedListNode(key, value)
        self.hashmap[key] = node
        
        # Evict LRU if over capacity
        if len(self.hashmap) > self.capacity:
            del self.hashmap[self.head.next.key]
            self.remove_node(self.head.next)
        
        self.add_to_tail(node)
```

## 🚨 Critical Details

### **Dummy Nodes**
- **head**: Points to LRU (first real node)
- **tail**: Points to MRU (last real node)
- Simplifies edge cases (empty list, single node)

### **Eviction Logic**
```python
# Check AFTER adding to hashmap but BEFORE adding to list
if len(self.hashmap) > self.capacity:
    del self.hashmap[self.head.next.key]  # Remove from hashmap first
    self.remove_node(self.head.next)      # Then remove from list
```

### **Why Store Key in Node?**
Need key to delete from hashmap during eviction:
```python
del self.hashmap[self.head.next.key]  # Need the key!
```

## 📊 Complexity
- **Time**: O(1) for both get() and put()
- **Space**: O(capacity) for hashmap + linked list

## 🔥 Interview Tips

### **Start with Requirements**
- get(key): Return value or -1
- put(key, value): Add/update, evict if needed
- Both operations in O(1) time

### **Explain Data Structure Choice**
- "Need O(1) lookup → HashMap"
- "Need O(1) add/remove from ends → Doubly Linked List"
- "Combine both for O(1) cache operations"

### **Walk Through Example**
```
capacity = 2
put(1, 100) → [1:100]
put(2, 200) → [1:100, 2:200]
get(1)      → [2:200, 1:100], return 100
put(3, 300) → [1:100, 3:300] (evict 2)
```

## 💭 Mental Model
Think of it as:
- **"Moving to front"** → make most recently used
- **"Remove from back"** → evict least recently used
- **"HashMap for instant access"** → O(1) node lookup
