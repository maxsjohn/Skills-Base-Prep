# Cutting Wood

## Problem

You are given an array of tree heights and an integer `k` representing the total length of wood to be cut. A woodcutting machine is set to a certain height `H`. The machine cuts the top part of all trees taller than `H`. Trees shorter than `H` are untouched. Determine the highest possible setting of the woodcutter (`H`) that cuts at least `k` meters of wood.

## Intuition

This problem can be solved using binary search, but not on the input array itself. Instead, we perform a binary search on the possible height settings of the woodcutter. The search space for `H` is from 0 to the height of the tallest tree.

As we increase the height setting `H`, the amount of wood cut decreases. This monotonic property allows us to use binary search to find the optimal height.

## Approach

1.  **Define a helper function `cuts_enough_wood(H, k, heights)`:** This function calculates the total wood collected for a given height `H` and returns `true` if the amount is greater than or equal to `k`, and `false` otherwise.

2.  **Binary Search on the Height:**
    *   Initialize `left = 0` and `right = max(heights)`.
    *   While `left < right`:
        *   Calculate `mid = (left + right) // 2 + 1` (bias to the right for upper-bound search).
        *   If `cuts_enough_wood(mid, k, heights)` is `true`, it means we might be able to go even higher, so we set `left = mid`.
        *   If `false`, the height is too high, so we set `right = mid - 1`.
    *   The loop terminates when `left == right`, and this value is the highest possible height setting.

## Complexity Analysis

*   **Time Complexity:** O(n log m), where n is the number of trees and m is the maximum height of the trees. The binary search takes O(log m) iterations, and in each iteration, we call `cuts_enough_wood`, which takes O(n) time.
*   **Space Complexity:** O(1).
