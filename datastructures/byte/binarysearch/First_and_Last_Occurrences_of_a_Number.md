# :dart: Finding the First and Last Position of an Element in a Sorted Array

## :triangular_flag_on_post: The Challenge

Given a sorted array of integers `nums` and a target integer `target`, your task is to find the **starting and ending position** of the `target`.

If the `target` is not found in the array, you should return `[-1, -1]`.

**The catch?** You must write an algorithm with `O(log n)` runtime complexity. This immediately points to **Binary Search**!

---

## :brain: The Core Idea: Two Binary Searches

A standard binary search will find *an* occurrence of the target, but it won't necessarily be the first or the last one. The key insight is to run **two modified binary searches**:

1.  One to find the **lower bound** (the very first occurrence of the target).
2.  Another to find the **upper bound** (the very last occurrence of the target).

---

### :arrow_left: Finding the Lower Bound (First Occurrence)

To find the leftmost occurrence, we perform a binary search. When we find an element equal to the `target`, we don't stop. We think, "Great, but is there another one even earlier?" So, we continue our search in the **left half** of the array.

*   If `nums[mid] < target`, we search the right half (`left = mid + 1`).
*   If `nums[mid] >= target`, we know the first occurrence could be `mid` or to its left, so we search the left half (`right = mid`).

### :arrow_right: Finding the Upper Bound (Last Occurrence)

To find the rightmost occurrence, we do the opposite. When we find an element equal to the `target`, we think, "Nice, but maybe there's another one after this?" So, we continue our search in the **right half**.

*   If `nums[mid] > target`, we search the left half (`right = mid - 1`).
*   If `nums[mid] <= target`, we know the last occurrence could be `mid` or to its right, so we search the right half (`left = mid`).

**A crucial trick:** For the upper bound search, to avoid getting stuck in an infinite loop when `left` and `right` are adjacent, we must bias our midpoint calculation to the right: `mid = (left + right) // 2 + 1`.

---

## :snake: Python Solution

Here is the complete Python implementation based on the logic from the PDF:

```python
from typing import List

def lower_bound_binary_search(nums: List[int], target: int) -> int:
    left, right = 0, len(nums) - 1
    while left < right:
        mid = (left + right) // 2
        if nums[mid] >= target:
            right = mid
        else:
            left = mid + 1
    # After the loop, `left` is the lower bound.
    # Check if it's a valid index and if the value matches the target.
    if not nums or nums[left] != target:
        return -1
    return left

def upper_bound_binary_search(nums: List[int], target: int) -> int:
    left, right = 0, len(nums) - 1
    while left < right:
        # Bias the midpoint to the right to avoid infinite loops
        mid = (left + right) // 2 + 1
        if nums[mid] > target:
            right = mid - 1
        else:
            left = mid
    # After the loop, `left` is the upper bound.
    # Check if it's a valid index and if the value matches the target.
    if not nums or nums[left] != target:
        return -1
    return left

def find_first_and_last(nums: List[int], target: int) -> List[int]:
    """
    Finds the first and last occurrences of a target in a sorted array.
    """
    if not nums:
        return [-1, -1]
    lower_bound = lower_bound_binary_search(nums, target)
    # If lower_bound is -1, target doesn't exist, so no need to search for upper_bound.
    if lower_bound == -1:
        return [-1, -1]
    upper_bound = upper_bound_binary_search(nums, target)
    return [lower_bound, upper_bound]

```

---

## :bar_chart: Complexity Analysis

*   **Time Complexity**: `O(log n)`
    *   We run two binary searches, each taking `O(log n)` time.
*   **Space Complexity**: `O(1)`
    *   We only use a constant amount of extra memory.