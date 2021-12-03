package com.company;

/**
 * This class contains three methods that implement different algorithms for finding the maximum subarray of an array.
 * @author Viola Yasuda
 * @version 1.0 10/13/2021
 */
public class MaxSubarrayAlgorithms {
    /**
     * Calculates the start index, end index, and sum of the elements of the maximum subarray of an array using a
     * brute force (O(n^2)) algorithm.
     * @param A the array to find the maximum subarray from
     * @param n the number of elements in the array
     * @return an int array containing the maximum subarray sum (at index 0), the starting index (arrival date) of the
     * maximum subarray (at index 1), and the ending index (departure date) of the maximum subarray (at index 2)
     */
    public static int[] findMaxSubarrayA(int[] A, int n) {
        int max = 0; //the largest sum of consecutive elements in the array
        int arrive = 0; //the arrival date (starting index of the max subarray)
        int depart = -1; //the departure date (ending index of the max subarray)
        int val; //the current sum of consecutive elements starting at index i and ending at index j

        /* The outer loop picks the beginning element, the inner loop finds the maximum possible sum with the first
           element picked by outer loop and compares this maximum with the overall maximum. */
        for (int i = 0; i < n; i++) {
            val = 0;
            for (int j = i; j < n; j++) {
                val = val + A[j];
                if (val > max) {
                    max = val;
                    arrive = i;
                    depart = j;
                }
            }
        }
        return new int[]{max, arrive, depart};
    }

    /**
     * Calculates the start index, end index, and sum of the elements of the maximum subarray of an array using a
     * divide-and-conquer (O(nlgn)) algorithm.
     * @param A the array to find the maximum subarray from
     * @param low the lowest index to search from in the array
     * @param high the highest index to search to in the array
     * @return an int array containing the maximum subarray sum (at index 0), the starting index (arrival date) of the
     * maximum subarray (at index 1), and the ending index (departure date) of the maximum subarray (at index 2)
     */
    public static int[] findMaxSubarrayB(int[] A, int low, int high) {
        if (high == -1) {
            return new int[]{0, 0, -1}; //in the case that the array is empty
        }
        if (high == low) {
            if (A[low] < 0) {
                return new int[]{0, 0, -1}; //in the case that the array has one negative element
            }
            return new int[]{A[low], low, high}; // base case: only one element
        }
        else {
            int mid = (low + high) / 2; //the midpoint of the array (to be used in findMaxCrossSubarray)

            //Case 1: T(n/2)
            int[] left = findMaxSubarrayB(A, low, mid); //holds the sum, low, and high of the left max subarray
            int leftSum = left[0]; //the maximum subarray sum in the left half of the array
            int leftLow = left[1]; //the starting index of the max subarray in the left half of the array
            int leftHigh = left[2]; //the ending index of the max subarray in the left half of the array
            //Case 2: T(n/2)
            int[] right = findMaxSubarrayB(A, mid + 1, high); //holds the sum, low, and high of the right max subarray
            int rightSum = right[0]; //the maximum subarray sum in the right half of the array
            int rightLow = right[1]; //the starting index of the max subarray in the right half of the array
            int rightHigh = right[2]; //the ending index of the max subarray in the right half of the array
            //Case 3: O(n)
            int[] cross = findMaxCrossSubarray(A, low, mid, high); //holds the sum, low, and high of the subarray over the midpoint
            int crossSum = cross[0]; //the maximum subarray sum such that the subarray crosses the midpoint
            int crossLow = cross[1]; //the starting index of the max subarray that crosses the midpoint
            int crossHigh = cross[2]; //the ending index of the max subarray that crosses the midpoint

            /* Return the values of the maximum case among the left half, right half, and cross subarrays. */
            if (leftSum >= rightSum && leftSum >= crossSum && leftSum >= 0) {
                return new int[]{leftSum, leftLow, leftHigh}; // of Case 1
            }
            else if (rightSum >= leftSum && rightSum >= crossSum && rightSum >= 0) {
                return new int[]{rightSum, rightLow, rightHigh}; // of Case 2
            }
            else if (crossSum >= 0) {
                return new int[]{crossSum, crossLow, crossHigh}; // of Case 3
            }
            else {
                return new int[]{0, 0, -1}; //in the case that all elements of the array are negative
            }
        }
    }

    /**
     * Finds the maximum subarray of an array that crosses a specified midpoint.
     * @param A the array to find the maximum cross subarray from
     * @param low the lowest index to search from in the array
     * @param mid the midpoint between low and high
     * @param high the highest index to search to in the array
     * @return an int array containing the maximum sum (at index 0), the starting index (at index 1), and the ending
     * index (at index 2) of the max subarray that crosses the midpoint
     */
    private static int[] findMaxCrossSubarray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE; //the max sum of consecutive elements on the left side of mid
        int maxLeft = -1; //the starting index of the max subarray that crosses the array's midpoint
        int sum = 0; //the temporary sum of consecutive elements on the left/right side of the midpoint
        for (int i = mid; i >= low; i--) { //finds the mac sum of the left side of mid
            sum = sum + A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = Integer.MIN_VALUE; //the max sum of consecutive elements on the right side of mid
        int maxRight = -1; //the ending index of the max subarray that crosses the array's midpoint
        sum = 0;
        for (int j = mid + 1; j <= high; j++) { //finds the max sum of the right side of mid
            sum = sum + A[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
        return new int[]{leftSum + rightSum, maxLeft, maxRight};
    }

    /**
     * Calculates the start index, end index, and sum of the elements of the maximum subarray of an array using
     * Kadane's Algorithm (O(n)).
     * @param A the array to find the maximum subarray from
     * @param n the number of elements in the array
     * @return an int array containing the maximum subarray sum (at index 0), the starting index (arrival date) of the
     * maximum subarray (at index 1), and the ending index (departure date) of the maximum subarray (at index 2)
     */
    public static int[] findMaxSubarrayC(int[] A, int n) {
        //Initialize
        int maxSum = 0; //represents the largest sum of consecutive elements in the array
        int maxTemp = 0; //represents the temporary sum of consecutive elements in the array
        int arrive = -1; //represents the arrival date (starting index of the max subarray)
        int depart = -1; //represents the departure date (ending index of the max subarray)
        int tempArrive = 0; //represents the temporary starting index of the max subarray
        /* The positive contiguous summations computed by adding one  element each time are kept in maxTemp. At the end,
            the largest sum is saved in maxSum. */
        for (int i = 0; i < n; i++) {
            maxTemp = maxTemp + A[i];
            if (maxTemp < 0) {
                maxTemp = 0;
                arrive = i + 1; //next day is the arrival since i-th not included
            }
            if (maxSum < maxTemp) {
                maxSum = maxTemp;
                depart = i;
                tempArrive = arrive;
            }
        }
        arrive = tempArrive;
        return new int[]{maxSum, arrive, depart};
    }
}
