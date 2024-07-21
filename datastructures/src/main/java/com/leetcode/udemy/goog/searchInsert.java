package main.java.com.leetcode.udemy.goog;

public class searchInsert {

    public int searchInsert(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // At this point, start is the index where the target should be inserted.
        return start;
    }
}