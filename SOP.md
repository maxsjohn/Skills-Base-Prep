# Single Problem Study Procedure
*** Detailed Ateo by step process***

##STEP 1 : Priblem reading and Analysis (3 minutes)
###What to do :
1. Read the poblem word by word (don't skim)
2. Read the example and trace them manually on paper
3. Identify constraint and edge cases
4. Write the problem and hype /category

### Physical actions
 - **Write in note book** : Problem title , difficulty  and one line summary
 - **Write in notebook** : Key constraint ( array , size , value ranges etc.)
 - ** Draw on paper: ** Trace the example 1 step by step with arrow/ numbers


### Example for Notebook entry 

```
Problem : Easy
Summary : Find two numbers that add up to target
Constraints: 2<= nums.lenght < 10^4 , each soution is unique
Pattern: Array + Hash table
```


## Editorial approach Study (4 minutes)
1.Read the editorial approach section (NOT the code yet)
2. Understand the core insight/trick
3. Identify why this approach works
4. Note the time/space complexity


### Physical actions:

 - **Write in notebook:** The key insight in your own words
 - **Draw on paper:** Visual representation of the approach (if applicable)
 - **Write in notebook:** Time: O(?), Space: O(?)

```
Key Insight: Use hashmap to store (value → index) 
           Check if (target - current_num) exists in map
Why it works: Single pass, avoid nested loops
Time: O(n), Space: O(n)
```



### STEP 3 Code analysis and typing
#### What to do :
1. Read the code line by line
2. Type the entire solution in your IDE
3. Add comments for entire major section
4. Identify the "smart lines" tht impliment the key in sight 

###Physical action
- Type in IDE : Completiom solution with your own comments
- Wite in notebook : The 2-3 most critical lines
- Circle/hight light: Variables and ther purposes

####Example with comments

```python

def twosum(nums, target):	
	#Hash map to store the index value mapping
	num_map={}

	for i, num in enumerate(nums):
		complement = target - num
	### KEY LINE :Check if complement exis
		if complement _  in num_map:
			return [num_map[complement],i]
		## Store the current index
		num_map[num]=i
	return []
```

### Example notebook entry
```
Crtica lines
	- complement = target - num 
	- if complement is in num_map"
	- num_map[num] = i
variables : num_map(value+index), complement (what wwe neeed)
```


## STEP 4: Code Walkthrough (3 minutes)

###What to do:
1. Take the first example from the problem
2. Trace through your typed code line by line
3. Write down the values of key variables at each step
4. Verify you get the correct output

### Physical actions:

- ** Draw table on paper**: Show variable states at each iteration
- **Write step numbers:** Next to each line execution


### Example walk through
```
Example: nums = [2,7,11,15], target = 9

Step 1: i=0, num=2, complement=7, num_map={}
        7 not in map, so num_map = {2: 0}

Step 2: i=1, num=7, complement=2, num_map={2: 0}
        2 IS in map! Return [0, 1] ✓
```

## STEP 5: Memory Reinforcement (2 minutes)

### What to do
1. Close your editor and notes
2. Try to write the algorithm steps from memory
3. Identify what you forgot
4. Create a memory hook/analogy


###physical actions
- Write in notebook: Algorithm steps without looking
- Check against original: Mark what you missed in red
- Write in notebook: Your personal memory trick

### Example notebook entry:


```
From memory:
1. Create empty hashmap ✓
2. Loop through array ✓
3. Calculate complement ✓
4. Check if complement exists ✓
5. Return indices or store current ✓

Memory hook: "Two Sum = Twin Search"
Like looking for your twin in a crowd - you remember faces (hashmap) 
and check if your complement is there!
```

### STEP 6: Pattern Recording (2 minutes)

#### What to do:

- Identify the broader pattern this problem represents
- Connect it to other problems you know
- Note the template/skeleton you can reuse
- Rate your confidence level

### Physical actions:

- **Write in notebook:** Pattern name and description
- **Write in notebook:** Reusable code template
- **Write in notebook:** Similar problems (if you know any)
- **Circle confidence:** 1-5 scale


###Example notebook entry:
```
Pattern: Hash Table Lookup
Template:
    hash_map = {}
    for item in array:
        if target_condition in hash_map:
            return result
        hash_map[item] = info

Similar: Three Sum, Four Sum
Confidence: 4/5 (need to practice the exact syntax)
```

## Physical Setup Requirements
###Notebook Layout:

- Left page: Problem info, insights, critical code
- Right page: Walkthrough tables, drawings, memory hooks

###Digital Setup:

- IDE/Editor: For typing and testing the solution
- Timer: Set for 20 minutes, check every 3-4 minutes
- Browser: Editorial solution open

## cTools needed:

- Notebook + pen (for writing and drawings)
- Computer with IDE
- Timer/stopwatch


Quality Checks
After each step, ask:

Step 1: "Do I understand what this problem is asking?"
Step 2: "Do I understand WHY this approach works?"
Step 3: "Can I explain what each line does?"
Step 4: "Did I get the right answer when tracing?"
Step 5: "What would I forget if I tried this tomorrow?"
Step 6: "How is this similar to other problems?"

Red flags to watch for:

Spending too long on one step
Skipping the physical writing/drawing
Not actually typing the code
Rushing through the walkthrough
Forgetting to create memory hooks


Time Management
Minutes 0-3: Reading and analysis
Minutes 3-7: Editorial study
Minutes 7-13: Code typing and analysis
Minutes 13-16: Walkthrough with example
Minutes 16-18: Memory test
Minutes 18-20: Pattern recording
If running behind: Skip detailed drawings but never skip typing the code or testing your memory.
