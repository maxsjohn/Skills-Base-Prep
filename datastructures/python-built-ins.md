# Essential Python Built-ins for DSA (Raw Format)

## 1. Character/Number Tools  
```python  
ord('a')  # char → ASCII (97)  
chr(97)   # ASCII → char ('a')  
int('5')  # string → int  
str(5)    # int → string  
```  

## 2. Math Shortcuts  
```python  
abs(-5)       # 5  
pow(2, 3)     # 8 (2**3)  
divmod(10, 3) # (3, 1) → (quotient, remainder)  
```  

## 3. Data Structures  
```python  
nums = [1,2,3]  
nums[::-1]     # Reverse list  
d.get('key', 0) # Safe dict access  

s = {1,2,3}  
s.difference({2}) # {1,3}  
```  

## 4. Iteration Helpers  
```python  
for i, val in enumerate(['a','b']): pass # (0,'a'), (1,'b')  
for a, b in zip([1,2], ['x','y']): pass # (1,'x'), (2,'y')  
```  

## 5. String Checks  
```python  
'abc'.isalpha()  # Letters only?  
'123'.isdigit()   # Numbers only?  
' '.join(['a','b']) # "a b"  
```  

## 6. Heap/Queue  
```python  
import heapq  
heapq.heappush(h, 2)  # Min-heap  

from collections import deque  
q = deque([1,2,3])  
q.popleft()  # O(1) queue  
```  

## 7. Bit Operations  
```python  
bin(5)       # '0b101'  
5 & 3        # Bitwise AND (1)  
1 << 3       # Bit shift left (8)  
```  

# Practice Tip: Use these in Leetcode Two Sum/Valid Palindrome to build memory.
