# :evergreen_tree: The Cutting Wood Problem: A Binary Search Puzzle

## :axe: The Challenge

You're given an array of tree heights and an integer `k`, which is the total amount of wood you need to collect. You have a special woodcutting machine that you can set to a specific height, `H`.

*   The machine chops the top off every tree that is **taller** than `H`.
*   The amount of wood collected from a tree is the difference between its height and `H`.
*   Trees shorter than or equal to `H` are left untouched.

Your goal is to find the **highest possible integer height `H`** you can set the machine to while still cutting **at least `k`** meters of wood.

---

### :bulb: Example

*   `heights = [2, 6, 3, 8]`
*   `k = 7`
*   **Output: `3`**

**Explanation:** If we set the machine to `H=3`, we cut wood from the trees of height 6 and 8. The wood collected is `(6 - 3) + (8 - 3) = 3 + 5 = 8`. This is `>= k`. If we set `H=4`, we only cut from trees of height 6 and 8, collecting `(6-4) + (8-4) = 2 + 4 = 6`, which is less than `k`. So, `3` is the highest we can go.

---

## :brain: The Core Idea: Binary Search on the *Answer*

This problem is a fantastic example of a common pattern: using binary search on the *search space of the potential answers*, not on the input array itself.

The input array `heights` isn't sorted, but we don't need it to be. Our search space is the range of possible heights `H` we can set the cutter to. What's the range?

*   **Minimum `H`**: `0` (cutting every tree down to the ground).
*   **Maximum `H`**: The height of the tallest tree.

As we increase `H`, the amount of wood we get decreases. This creates a monotonic (consistently decreasing) function, which is the perfect scenario for a binary search!

We can define a condition: `cuts_enough_wood(H)`. This function will return `true` if setting the cutter to `H` yields at least `k` wood, and `false` otherwise. If we check this for all possible `H` values, we get a sorted-like boolean sequence:

`[True, True, True, ..., False, False, False]`

Our goal is to find the **last `True`** in this sequence. This is an **upper-bound** binary search problem.

---

## :computer: The Algorithm: Upper-Bound Binary Search

1.  **Define the Search Space**:
    *   `left = 0`
    *   `right = max(heights)`

2.  **Create a Helper Function `cuts_enough_wood(H, k, heights)`**:
    *   This function iterates through the `heights` array.
    *   For each tree, if `height > H`, it adds `height - H` to a `wood_collected` total.
    *   It returns `wood_collected >= k`.

3.  **Perform the Binary Search**:
    *   Loop while `left < right`.
    *   Calculate the midpoint. **Crucially**, since we are looking for the *upper bound* (the rightmost `True`), we must bias our midpoint to the right to avoid an infinite loop when `left` and `right` are adjacent. `mid = (left + right) // 2 + 1`.
    *   **`if cuts_enough_wood(mid, k, heights):`**
        *   It works! This `mid` is a potential answer. But maybe we can go even higher? We continue searching in the right half, *including* `mid`.
        *   `left = mid`
    *   **`else:`**
        *   It doesn't work. `mid` is too high. We must search in the left half, *excluding* `mid`.
        *   `right = mid - 1`

4.  **The Result**: When the loop terminates (`left == right`), `right` (or `left`) will hold the highest possible height `H`.

---

## :snake: Python Solution

Here is the complete Python implementation from the document:

```python
from typing import List

def cuts_enough_wood(H: int, k: int, heights: List[int]) -> bool:
    """Determine if the current value of 'H' cuts at least 'k' meters of wood."""
    wood_collected = 0
    for height in heights:
        if height > H:
            wood_collected += (height - H)
    return wood_collected >= k

def cutting_wood(heights: List[int], k: int) -> int:
    """Finds the highest possible height setting to cut at least k wood."""
    left, right = 0, max(heights)

    while left < right:
        # Bias the midpoint to the right for the upper-bound binary search.
        mid = (left + right) // 2 + 1

        if cuts_enough_wood(mid, k, heights):
            # This height works, try for a higher one.
            left = mid
        else:
            # This height is too high, try a lower one.
            right = mid - 1

    return right # or left, they are the same

```

---

## :bar_chart: Complexity Analysis

*   **Time Complexity**: `O(n * log(m))`
    *   `m` is the maximum height of a tree (the size of our search space).
    *   The binary search takes `O(log m)` steps.
    *   In each step, we call `cuts_enough_wood`, which iterates through all `n` trees, taking `O(n)` time.
*   **Space Complexity**: `O(1)`
    *   We only use a few variables to store pointers and the total, requiring constant extra space.