package main.java.com.leetcode.udemy.goog;

import java.util.Arrays;

public class CountBits {


    public static void main(String[] args) {
        int[] res = {2,3,2,3};
        System.out.println(Arrays.toString(countBits(5)));
    }

    public static  int[] countBits(int n) {

        int[] results = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            results[i] = getBits(i);
        }
        return results;

    }

    private static  int getBits(int number) {

        int count = 0;
        for (int i = 0; i < 32; i++) {

            if ((number & (1 << i)) != 0) {
                count++;
            }


        }
        return count;


    }
}
