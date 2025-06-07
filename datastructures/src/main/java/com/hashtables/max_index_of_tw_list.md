### **Problem: Minimum Index Sum of Two Lists**
**Goal:** Find common strings between `list1` and `list2` with the smallest index sum (`i + j`).

---

### **Key Insights**
1. **Brute Force is O(m*n)** → Compare all pairs (slow for large lists).
2. **Hashmap Optimization** → Store indices of one list for O(1) lookups.
3. **Single Pass** → Traverse `list2` once, check against `list1`'s hashmap.

---

### **Optimal Approach**
1. **Step 1:** Create `index_map = {string: index}` for `list1`.
2. **Step 2:** Traverse `list2`:
   - If `s` in `index_map`, calculate `current_sum = i (from list1) + j (from list2)`.
   - Update `min_sum` and `result` dynamically:
     - If `current_sum < min_sum` → Reset `result = [s]`.
     - If `current_sum == min_sum` → Append `s` to `result`.

---

### **Your Learnings**
1. **Test with Samples First** → Avoid jumping to code; validate logic manually.
2. **Avoid Redundant Loops** → Original solution traversed hashmap twice (inefficient).
3. **Space Efficiency** → `defaultdict` was overkill; simple dict sufficed.
4. **Edge Cases** → Empty lists? All strings common? Duplicates?

---

### **Code (Optimized)**
\```python
def findRestaurant(self, list1: List[str], list2: List[str]) -> List[str]:
    index_map = {s: i for i, s in enumerate(list1)}
    min_sum, result = float('inf'), []
    
    for j, s in enumerate(list2):
        if s in index_map:
            current_sum = index_map[s] + j
            if current_sum < min_sum:
                min_sum = current_sum
                result = [s]
            elif current_sum == min_sum:
                result.append(s)
    return result
\```

---

### **Time & Space**
- **Time:** O(m + n) → One pass each.
- **Space:** O(m) → Storing `list1`'s indices.

---

### **Common Pitfalls**
1. **Ignoring Duplicates** → If a string repeats, which index to use? (Problem says "first occurrence").
2. **Overcomplicating** → Unnecessary data structures (`defaultdict`).
3. **Premature Optimization** → Start brute force, then optimize.

---

### **Revision Tips**
- Draw sample inputs (e.g., `list1 = ["A", "B"]`, `list2 = ["B", "A"]`).
- Always check constraints (e.g., `1 <= list1.length, list2.length <= 1000`).
- Use `enumerate` for clean index tracking.
