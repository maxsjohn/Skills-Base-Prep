# Palindromic Linked List - Revision Notes

## 🎯 Problem
Check if a singly linked list is a palindrome in **O(1) space**.

**Examples:**
```
1 → 2 → 1 → 2 → 1   ✅ True
1 → 2 → 1 → 2       ❌ False
```

## 💡 Core Strategy
**Three-Step Approach:**
1. **Find middle** of linked list
2. **Reverse second half** starting from middle  
3. **Compare first half** with reversed second half

## 🔑 Key Insight
Instead of using O(n) space to store values:
- **Modify the list in-place** by reversing second half
- Only need to compare: `first_half` vs `reverse(second_half)`

## ⚡ Step-by-Step Algorithm

### **Step 1: Find Middle (Slow/Fast Pointers)**
```python
def find_middle(head):
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    return slow  # Points to middle/start of second half
```

### **Step 2: Reverse Second Half**
```python
def reverse_list(head):
    prev, curr = None, head
    while curr:
        next_temp = curr.next
        curr.next = prev
        prev = curr
        curr = next_temp
    return prev  # New head of reversed list
```

### **Step 3: Compare Both Halves**
```python
ptr1, ptr2 = head, second_head
while ptr2:  # Only iterate through second half
    if ptr1.val != ptr2.val:
        return False
    ptr1, ptr2 = ptr1.next, ptr2.next
return True
```

## 📝 Complete Implementation
```python
def isPalindrome(head):
    # Step 1: Find middle
    mid = find_middle(head)
    
    # Step 2: Reverse second half
    second_head = reverse_list(mid)
    
    # Step 3: Compare both halves
    ptr1, ptr2 = head, second_head
    result = True
    
    while ptr2:
        if ptr1.val != ptr2.val:
            result = False
            break
        ptr1, ptr2 = ptr1.next, ptr2.next
    
    return result

def find_middle(head):
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    return slow

def reverse_list(head):
    prev, curr = None, head
    while curr:
        next_temp = curr.next
        curr.next = prev
        prev = curr
        curr = next_temp
    return prev
```

## 🚨 Critical Details

### **Why Check `ptr2` in While Loop?**
```python
while ptr2:  # NOT while ptr1 and ptr2
```
- Second half is shorter/equal length to first half
- When `ptr2` reaches end, comparison is complete

### **Odd vs Even Length Handling**
```
Odd:  1→2→3→2→1    mid points to 3 (middle)
Even: 1→2→2→1      mid points to first 2 (start of second half)
```
- Algorithm works for both automatically
- Middle element in odd-length lists doesn't affect palindrome check

## 📊 Complexity
- **Time**: O(n) - Three passes through list
- **Space**: O(1) - Only using pointers

## 🔥 Interview Tips

### **Ask About Input Modification**
```
"Is it acceptable to modify the input linked list? 
My solution reverses the second half."
```

### **Alternative Approaches**
- **O(n) space**: Store values in array, check with two pointers
- **Recursive**: Use call stack (also O(n) space due to recursion)

### **Edge Cases**
```python
# Single node
1 → None          ✅ True

# Two nodes
1 → 2 → None      ❌ False
1 → 1 → None      ✅ True

# Empty list
None              ✅ True (typically)
```

## 💭 Mental Models

### **"Split and Mirror"**
```
Original: 1→2→3→2→1
Split:    [1→2→3] and [2→1]
Reverse:  [1→2→3] and [1→2]
Compare:  1==1, 2==2 ✅
```

### **"Two Pointers Walking Towards Each Other"**
```
ptr1: →→→  (forward through first half)
ptr2: →→→  (forward through reversed second half)
```

## 🎪 Quick Memory Tricks
- **"Find, Flip, Compare"** - Three-step process
- **"Slow/Fast for Middle"** - Classic two-pointer technique  
- **"Reverse In-Place"** - Space optimization key
- **"Second Half Shorter"** - Always check `ptr2` in loop condition
