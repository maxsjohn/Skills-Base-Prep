# Find the Insertion Index

## Problem Description

You are given a sorted array of unique integers and a target integer.

- If the `target` is in the array, return its index.
- If the `target` is not in the array, return the index where it would be inserted to maintain the sorted order.

**Examples:**

- `nums = [1, 2, 4, 5, 7, 8, 9]`, `target = 4` -> `Output: 2`
- `nums = [1, 2, 4, 5, 7, 8, 9]`, `target = 6` -> `Output: 4`

## Key Idea

The core of the problem is to find the **lower bound**: the first element in the array that is greater than or equal to the `target`.

- If the `target` exists, this will be the `target` itself.
- If the `target` does not exist, this will be the first element greater than the `target`, which is the insertion point.

Since the array is sorted, **binary search** is the most efficient approach.

## Algorithm (Binary Search)

1.  **Initialize Pointers:**
    - `left`: `0`
    - `right`: `len(nums)` (one past the end to handle insertion at the end)

2.  **Loop:** Continue as long as `left < right`.

3.  **Find Midpoint:** `mid = (left + right) // 2`

4.  **Compare:**
    - If `nums[mid] >= target`: The target is at `mid` or to its left. Move the `right` pointer to `mid`.
      - `right = mid`
    - If `nums[mid] < target`: The target must be to the right of `mid`. Move the `left` pointer past `mid`.
      - `left = mid + 1`

5.  **Return:** When the loop terminates (`left == right`), `left` will be the insertion index.

## Complexity Analysis

-   **Time Complexity:** `O(log n)` - due to the binary search.
-   **Space Complexity:** `O(1)` - as we only use a few variables to keep track of pointers.
