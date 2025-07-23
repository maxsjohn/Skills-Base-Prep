# First and Last Occurrences of a Number

## Problem

Given a sorted array of integers in non-decreasing order, find the first and last indexes of a given target number. If the target is not found, return `[-1, -1]`.

## Intuition

A brute-force approach would be to linearly scan the array to find the first and last occurrences of the target. However, since the array is sorted, we can use binary search to find the occurrences more efficiently.

## Approach

The problem can be broken down into two parts:

1.  Find the lower bound of the target (the first occurrence).
2.  Find the upper bound of the target (the last occurrence).

### Lower-Bound Binary Search

To find the first occurrence of the target, we can use a modified binary search. When the midpoint value is equal to the target, we continue searching on the left side of the midpoint to find an even earlier occurrence.

### Upper-Bound Binary Search

To find the last occurrence of the target, we can use another modified binary search. When the midpoint value is equal to the target, we continue searching on the right side of the midpoint to find a later occurrence. To avoid an infinite loop in this case, we bias the midpoint calculation to the right.

## Complexity Analysis

*   **Time Complexity:** O(log n), where n is the number of elements in the array. This is because we are using binary search.
*   **Space Complexity:** O(1), as we are not using any extra space that scales with the input size.
