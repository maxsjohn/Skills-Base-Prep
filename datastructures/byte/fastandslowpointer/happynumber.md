# Happy Number - Revision Notes

## 🎯 Problem
Determine if a number is "happy" by repeatedly summing squares of its digits until reaching 1 or detecting a cycle.

**Example:**
```
n = 23
23 → 2² + 3² = 13
13 → 1² + 3² = 10  
10 → 1² + 0² = 1 ✅ (Happy!)

n = 4  
4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 ❌ (Cycle!)
```

## 💡 Core Insight
**Transform into Cycle Detection Problem:**
- **Happy number**: Process leads to 1 (no cycle)
- **Unhappy number**: Process gets stuck in infinite loop (cycle)
- **Solution**: Use Floyd's Cycle Detection algorithm!

## ⚡ Key Components

### **1. Get Next Number Function:**
```python
def get_next_num(x):
    next_num = 0
    while x > 0:
        digit = x % 10      # Extract last digit
        x //= 10            # Remove last digit  
        next_num += digit ** 2  # Add square to sum
    return next_num

# Example: get_next_num(123)
# digit=3, x=12, next_num=9
# digit=2, x=1, next_num=13  
# digit=1, x=0, next_num=14
# Return 14
```

### **2. Fast and Slow Pointer Logic:**
```python
def isHappy(n):
    slow = fast = n
    
    while True:
        slow = get_next_num(slow)                    # Move 1 step
        fast = get_next_num(get_next_num(fast))      # Move 2 steps
        
        if fast == 1:        # Reached 1 = Happy!
            return True
        elif fast == slow:   # Cycle detected = Unhappy!
            return False
```

## 📝 Complete Implementation
```python
def isHappy(n):
    def get_next_num(x):
        next_num = 0
        while x > 0:
            digit = x % 10
            x //= 10
            next_num += digit ** 2
        return next_num
    
    slow = fast = n
    
    while True:
        slow = get_next_num(slow)
        fast = get_next_num(get_next_num(fast))
        
        if fast == 1:
            return True
        elif fast == slow:
            return False
```

## 🔑 Why Floyd's Algorithm Works

### **Two Possible Outcomes:**
1. **Terminates at 1**: Happy number
2. **Enters cycle**: Unhappy number (fast catches slow)

### **"Virtual Linked List" Concept:**
```
Think of each number as a node:
23 → 13 → 10 → 1 (Happy path)
4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 (Cycle)
```

### **Digit Processing Pattern:**
```python
x = 123
digit = x % 10    # Gets 3
x = x // 10       # Becomes 12

digit = x % 10    # Gets 2  
x = x // 10       # Becomes 1

digit = x % 10    # Gets 1
x = x // 10       # Becomes 0 (stop)
```

## 📊 Complexity Analysis
- **Time**: O(log n) - Number of digits in n determines iterations
- **Space**: O(1) - Only using constant variables

## 🚨 Edge Cases

### **Single Digit Numbers:**
```python
n = 1: Already happy ✅
n = 7: 7 → 49 → 97 → 130 → 10 → 1 ✅  
n = 4: 4 → 16 → 37 → ... → cycle ❌
```

### **Large Numbers:**
- Algorithm quickly reduces to smaller numbers
- Cycle detection happens in bounded space

## 🔥 Alternative Approach (Less Optimal)
```python
def isHappySet(n):
    seen = set()
    
    while n != 1 and n not in seen:
        seen.add(n)
        n = get_next_num(n)
    
    return n == 1
# Time: O(log n), Space: O(log n) - uses extra memory
```

## 💭 Mental Models

### **"Mathematical Linked List":**
```
Each number "points to" its next number
Following this chain either:
- Leads to 1 (terminates)  
- Forms a cycle (infinite loop)
```

### **"Runner Analogy":**
```
Two runners on a track of numbers:
- If track leads to finish line (1): Happy
- If track is circular: Unhappy (runners meet)
```

## 🎪 Quick Memory Tricks
- **"Square and Sum"** - Core digit processing
- **"1 or Cycle"** - Only two possible outcomes
- **"Floyd for Cycles"** - Classic cycle detection
- **"Math meets Pointers"** - Creative algorithm application

## 🏆 Interview Tips

### **Explain the Transformation:**
```
"This looks like a linked list cycle problem in disguise.
Each number 'points to' the next number in the sequence."
```

### **Walk Through Example:**
```
n = 23
slow: 23 → 13 → 10 → 1 ✅
fast: 23 → 10 → 1 ✅
Result: Happy number!
```

### **Mention Alternative:**
"I could use a hash set to track seen numbers, but Floyd's algorithm is more space-efficient."

### **Key Insight:**
"The brilliant part is recognizing this as a cycle detection problem, not a pure math problem."

## ⚠️ Common Mistakes
❌ **Using hash set first** → Missing the space optimization  
❌ **Not handling single digits** → Edge case oversight  
❌ **Overcomplicating math** → Missing the pattern recognition  
❌ **Wrong termination condition** → Not checking both 1 and cycle cases

## 🔧 Helper Function Template
```python
def get_next_num(x):
    result = 0
    while x > 0:
        digit = x % 10      # Extract rightmost digit
        x //= 10            # Remove rightmost digit
        result += digit * digit  # Add square
    return result
```
