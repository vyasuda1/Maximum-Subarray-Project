package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] A = new int[]{-9, 10, -8, 10, 5, -4, -2, 5};
        int n = A.length;
        System.out.print("Brute force (Algorithm A): ");
        int[] resultA = MaxSubarrayAlgorithms.findMaxSubarrayA(A, n);
        System.out.println(resultA[0] + " " + resultA[1] + " " + resultA[2]);
        System.out.print("Divide and Conquer (Algorithm B): ");
        int[] resultB = MaxSubarrayAlgorithms.findMaxSubarrayB(A, 0, n - 1);
        System.out.println(resultB[0] + " " + resultB[1] + " " + resultB[2]);
        System.out.print("Kadane's Algorithm (Algorithm C): ");
        int[] resultC = MaxSubarrayAlgorithms.findMaxSubarrayC(A, n);
        System.out.println(resultC[0] + " " + resultC[1] + " " + resultC[2]);
        System.out.println();

        A = new int[]{-2, -5, 6, -2, -3, 1, 5, -6};
        n = A.length;
        System.out.print("Brute force (Algorithm A): ");
        resultA = MaxSubarrayAlgorithms.findMaxSubarrayA(A, n);
        System.out.println(resultA[0] + " " + resultA[1] + " " + resultA[2]);
        System.out.print("Divide and Conquer (Algorithm B): ");
        resultB = MaxSubarrayAlgorithms.findMaxSubarrayB(A, 0, n - 1);
        System.out.println(resultB[0] + " " + resultB[1] + " " + resultB[2]);
        System.out.print("Kadane's Algorithm (Algorithm C): ");
        resultC = MaxSubarrayAlgorithms.findMaxSubarrayC(A, n);
        System.out.println(resultC[0] + " " + resultC[1] + " " + resultC[2]);
        System.out.println();

        A = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        n = A.length;
        System.out.print("Brute force (Algorithm A): ");
        resultA = MaxSubarrayAlgorithms.findMaxSubarrayA(A, n);
        System.out.println(resultA[0] + " " + resultA[1] + " " + resultA[2]);
        System.out.print("Divide and Conquer (Algorithm B): ");
        resultB = MaxSubarrayAlgorithms.findMaxSubarrayB(A, 0, n - 1);
        System.out.println(resultB[0] + " " + resultB[1] + " " + resultB[2]);
        System.out.print("Kadane's Algorithm (Algorithm C): ");
        resultC = MaxSubarrayAlgorithms.findMaxSubarrayC(A, n);
        System.out.println(resultC[0] + " " + resultC[1] + " " + resultC[2]);
        System.out.println();

        A = new int[]{-3, -4, -5, -6, -7, -8, -9, -10};
        n = A.length;
        System.out.print("Brute force (Algorithm A): ");
        resultA = MaxSubarrayAlgorithms.findMaxSubarrayA(A, n);
        System.out.println(resultA[0] + " " + resultA[1] + " " + resultA[2]);
        System.out.print("Divide and Conquer (Algorithm B): ");
        resultB = MaxSubarrayAlgorithms.findMaxSubarrayB(A, 0, n - 1);
        System.out.println(resultB[0] + " " + resultB[1] + " " + resultB[2]);
        System.out.print("Kadane's Algorithm (Algorithm C): ");
        resultC = MaxSubarrayAlgorithms.findMaxSubarrayC(A, n);
        System.out.println(resultC[0] + " " + resultC[1] + " " + resultC[2]);
        System.out.println();
    }
}
