package com.khlopotov.ai;

/**
 * https://www.codewars.com/kata/maximum-contiguous-sum/train/java
 */
public class MaxContinuous {
    public static int maxContiguousSum(final int[] arr) {
        int maxSum = 0;
        int tempSum = 0;

        for (int i : arr) {
            tempSum += i;
            if (tempSum > maxSum) {
                maxSum = tempSum;
            }
            if (tempSum < 0) {
                tempSum = 0;
            }
        }
        return maxSum;
    }
}