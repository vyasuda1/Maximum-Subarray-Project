package cs146F21.Yasuda.project2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the algorithms defined in the MaxSubarrayAlgorithms class.
 * @author Viola Yasuda
 * @version 1.0 10/16/2021
 */
class MaxSubarrayAlgorithmsTest {
	// for test cases requirement 1
	int[][] maxSumTestCases; //holds the 10 test cases of arrays with 100 elements from maxSumtest.txt
	int[][] maxSumTestAnswers; //holds the expected values from running the test cases from maxSumtest.txt
	// for test cases requirement 2
	int[] emptyArray; //an empty array
	int[] onePositiveElement; //an array of size 1 whose element is positive
	int[] oneNegativeElement; //an array of size 1 whose element is negative
	int[] allPositiveElements; //an array of size 2 whose elements are all positive
	int[] allNegativeElements; //an array of size 2 whose elements are all negative
	int[] alternatingSigns; //an array whose elements are alternating in sign (-,+,-...)
	int[] normalArray1; //a normal array to test the algorithms
	int[] normalArray2; //a second normal array to test the algorithms
	int[] normalArray3; //a third normal array to test the algorithms
	
	/**
	 * Initializes the arrays that will be used to test the MaxSubarrayAlgorithms methods.
	 */
	@BeforeEach
	void initializeArrays() {
		// for test cases requirement 1
		maxSumTestCases = new int[10][100];
		maxSumTestAnswers = new int[10][3];
		File file = new File("maxSumtest.txt");
		try {
			Scanner in = new Scanner(file);
			int i = 0;
			while (in.hasNextLine()) {
				for (int j = 0; j < 100; j++) {
					maxSumTestCases[i][j] = in.nextInt();
				}
				for (int j = 0; j < 3; j++) {
					maxSumTestAnswers[i][j] = in.nextInt();
				}
				in.nextLine();
				i++;
			}
			in.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//for test cases requirement 2
		emptyArray = new int[]{};
		onePositiveElement = new int[]{1};
		oneNegativeElement = new int[]{-1};
		allPositiveElements = new int[]{1, 2};
		allNegativeElements = new int[]{-1, -2};
		alternatingSigns = new int[]{-1, 2, -3, 4, -5};
		normalArray1 = new int[]{-9, 10, -8, 10, 5, -4, -2, 5};
		normalArray2 = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
		normalArray3 = new int[]{-3, -4, -5, -6, -7, -8, -9, -10};
	}
	
	/**
	 * Tests the brute-force algorithm (findMaxSubarrayA) for finding the maximum subarray of an array.
	 */
	@Test
	void testFindMaxSubarrayA() {
		//testing test cases from maxSumtest.txt
		for (int i = 0; i < 10; i++) {
			int[] result = MaxSubarrayAlgorithms.findMaxSubarrayA(maxSumTestCases[i], 100);
			for (int j = 0; j < 3; j++) {
				assertEquals(maxSumTestAnswers[i][j], result[j]);
			}
		}
		//testing for various small and difference cases
		//testing for when input array is empty
		int[] emptyResult = MaxSubarrayAlgorithms.findMaxSubarrayA(emptyArray, emptyArray.length); //expected result: 0 0 -1
		assertEquals(emptyResult[0], 0);
		assertEquals(emptyResult[1], 0);
		assertEquals(emptyResult[2], -1);
		//testing for when input array only has one positive element
		int[] onePosResult = MaxSubarrayAlgorithms.findMaxSubarrayA(onePositiveElement, onePositiveElement.length); //expected result: 1 0 0
		assertEquals(onePosResult[0], 1);
		assertEquals(onePosResult[1], 0);
		assertEquals(onePosResult[2], 0);
		//testing for when input array only has one negative element
		int[] oneNegResult = MaxSubarrayAlgorithms.findMaxSubarrayA(oneNegativeElement, oneNegativeElement.length); //expected result: 0 0 -1
		assertEquals(oneNegResult[0], 0);
		assertEquals(oneNegResult[1], 0);
		assertEquals(oneNegResult[2], -1);
		//testing for when input array has all positive elements
		int[] allPosResult = MaxSubarrayAlgorithms.findMaxSubarrayA(allPositiveElements, allPositiveElements.length); //expected result: 3 0 1
		assertEquals(allPosResult[0], 3);
		assertEquals(allPosResult[1], 0);
		assertEquals(allPosResult[2], 1);
		//testing for when input array has all negative elements
		int[] allNegResult = MaxSubarrayAlgorithms.findMaxSubarrayA(allNegativeElements, allNegativeElements.length); //expected result: 0 0 -1
		assertEquals(allNegResult[0], 0);
		assertEquals(allNegResult[1], 0);
		assertEquals(allNegResult[2], -1);
		//testing for when input array has elements alternating in signs
		int[] alternResult = MaxSubarrayAlgorithms.findMaxSubarrayA(alternatingSigns, alternatingSigns.length); //expected result: 4 3 3
		assertEquals(alternResult[0], 4);
		assertEquals(alternResult[1], 3);
		assertEquals(alternResult[2], 3);
		//testing array for normal cases
		int[] normResult1 = MaxSubarrayAlgorithms.findMaxSubarrayA(normalArray1, normalArray1.length); //expected result: 17 1 4
		assertEquals(normResult1[0], 17);
		assertEquals(normResult1[1], 1);
		assertEquals(normResult1[2], 4);
		int[] normResult2 = MaxSubarrayAlgorithms.findMaxSubarrayA(normalArray2, normalArray2.length); //expected result: 7 2 6
		assertEquals(normResult2[0], 7);
		assertEquals(normResult2[1], 2);
		assertEquals(normResult2[2], 6);
		int[] normResult3 = MaxSubarrayAlgorithms.findMaxSubarrayA(normalArray3, normalArray3.length); //expected result: 0 0 -1
		assertEquals(normResult3[0], 0);
		assertEquals(normResult3[1], 0);
		assertEquals(normResult3[2], -1);
	}
	
	/**
	 * Tests the divide-and-conquer algorithm (findMaxSubarrayB) for finding the maximum subarray of an array.
	 */
	@Test
	void testFindMaxSubarrayB() {
		//testing test cases from maxSumtest.txt
		for (int i = 0; i < 10; i++) {
			int[] result = MaxSubarrayAlgorithms.findMaxSubarrayB(maxSumTestCases[i], 0, 99);
			for (int j = 0; j < 3; j++) {
				assertEquals(maxSumTestAnswers[i][j], result[j]);
			}
		}
		//testing for various small and difference cases
		//testing for when input array is empty
		int[] emptyResult = MaxSubarrayAlgorithms.findMaxSubarrayB(emptyArray, 0, emptyArray.length - 1); //expected result: 0 0 -1
		assertEquals(emptyResult[0], 0);
		assertEquals(emptyResult[1], 0);
		assertEquals(emptyResult[2], -1);
		//testing for when input array only has one positive element
		int[] onePosResult = MaxSubarrayAlgorithms.findMaxSubarrayB(onePositiveElement, 0, onePositiveElement.length - 1); //expected result: 1 0 0
		assertEquals(onePosResult[0], 1);
		assertEquals(onePosResult[1], 0);
		assertEquals(onePosResult[2], 0);
		//testing for when input array only has one negative element
		int[] oneNegResult = MaxSubarrayAlgorithms.findMaxSubarrayB(oneNegativeElement, 0, oneNegativeElement.length - 1); //expected result: 0 0 -1
		assertEquals(oneNegResult[0], 0);
		assertEquals(oneNegResult[1], 0);
		assertEquals(oneNegResult[2], -1);
		//testing for when input array has all positive elements
		int[] allPosResult = MaxSubarrayAlgorithms.findMaxSubarrayB(allPositiveElements, 0, allPositiveElements.length - 1); //expected result: 3 0 1
		assertEquals(allPosResult[0], 3);
		assertEquals(allPosResult[1], 0);
		assertEquals(allPosResult[2], 1);
		//testing for when input array has all negative elements
		int[] allNegResult = MaxSubarrayAlgorithms.findMaxSubarrayB(allNegativeElements, 0, allNegativeElements.length - 1); //expected result: 0 0 -1
		assertEquals(allNegResult[0], 0);
		assertEquals(allNegResult[1], 0);
		assertEquals(allNegResult[2], -1);
		//testing for when input array has elements alternating in signs
		int[] alternResult = MaxSubarrayAlgorithms.findMaxSubarrayB(alternatingSigns, 0, alternatingSigns.length - 1); //expected result: 4 3 3
		assertEquals(alternResult[0], 4);
		assertEquals(alternResult[1], 3);
		assertEquals(alternResult[2], 3);
		//testing array for normal cases
		int[] normResult1 = MaxSubarrayAlgorithms.findMaxSubarrayB(normalArray1, 0, normalArray1.length - 1); //expected result: 17 1 4
		assertEquals(normResult1[0], 17);
		assertEquals(normResult1[1], 1);
		assertEquals(normResult1[2], 4);
		int[] normResult2 = MaxSubarrayAlgorithms.findMaxSubarrayB(normalArray2, 0, normalArray2.length - 1); //expected result: 7 2 6
		assertEquals(normResult2[0], 7);
		assertEquals(normResult2[1], 2);
		assertEquals(normResult2[2], 6);
		int[] normResult3 = MaxSubarrayAlgorithms.findMaxSubarrayB(normalArray3, 0, normalArray3.length - 1); //expected result: 0 0 -1
		assertEquals(normResult3[0], 0);
		assertEquals(normResult3[1], 0);
		assertEquals(normResult3[2], -1);
	}
	
	/**
	 * Tests Kadane's algorithm (findMaxSubarrayC) for finding the maximum subarray of an array.
	 */
	@Test
	void testFindMaxSubarrayC() {
		//testing test cases from maxSumtest.txt
		for (int i = 0; i < 10; i++) {
			int[] result = MaxSubarrayAlgorithms.findMaxSubarrayC(maxSumTestCases[i], 100);
			for (int j = 0; j < 3; j++) {
				assertEquals(maxSumTestAnswers[i][j], result[j]);
			}
		}
		//testing for various small and difference cases
		//testing for when input array is empty
		int[] emptyResult = MaxSubarrayAlgorithms.findMaxSubarrayC(emptyArray, emptyArray.length); //expected result: 0 0 -1
		assertEquals(emptyResult[0], 0);
		assertEquals(emptyResult[1], 0);
		assertEquals(emptyResult[2], -1);
		//testing for when input array only has one positive element
		int[] onePosResult = MaxSubarrayAlgorithms.findMaxSubarrayC(onePositiveElement, onePositiveElement.length); //expected result: 1 0 0
		assertEquals(onePosResult[0], 1);
		assertEquals(onePosResult[1], 0);
		assertEquals(onePosResult[2], 0);
		//testing for when input array only has one negative element
		int[] oneNegResult = MaxSubarrayAlgorithms.findMaxSubarrayC(oneNegativeElement, oneNegativeElement.length); //expected result: 0 0 -1
		assertEquals(oneNegResult[0], 0);
		assertEquals(oneNegResult[1], 0);
		assertEquals(oneNegResult[2], -1);
		//testing for when input array has all positive elements
		int[] allPosResult = MaxSubarrayAlgorithms.findMaxSubarrayC(allPositiveElements, allPositiveElements.length); //expected result: 3 0 1
		assertEquals(allPosResult[0], 3);
		assertEquals(allPosResult[1], 0);
		assertEquals(allPosResult[2], 1);
		//testing for when input array has all negative elements
		int[] allNegResult = MaxSubarrayAlgorithms.findMaxSubarrayC(allNegativeElements, allNegativeElements.length); //expected result: 0 0 -1
		assertEquals(allNegResult[0], 0);
		assertEquals(allNegResult[1], 0);
		assertEquals(allNegResult[2], -1);
		//testing for when input array has elements alternating in signs
		int[] alternResult = MaxSubarrayAlgorithms.findMaxSubarrayC(alternatingSigns, alternatingSigns.length); //expected result: 4 3 3
		assertEquals(alternResult[0], 4);
		assertEquals(alternResult[1], 3);
		assertEquals(alternResult[2], 3);
		//testing array for normal cases
		int[] normResult1 = MaxSubarrayAlgorithms.findMaxSubarrayC(normalArray1, normalArray1.length); //expected result: 17 1 4
		assertEquals(normResult1[0], 17);
		assertEquals(normResult1[1], 1);
		assertEquals(normResult1[2], 4);
		int[] normResult2 = MaxSubarrayAlgorithms.findMaxSubarrayC(normalArray2, normalArray2.length); //expected result: 7 2 6
		assertEquals(normResult2[0], 7);
		assertEquals(normResult2[1], 2);
		assertEquals(normResult2[2], 6);
		int[] normResult3 = MaxSubarrayAlgorithms.findMaxSubarrayC(normalArray3, normalArray3.length); //expected result: 0 0 -1
		assertEquals(normResult3[0], 0);
		assertEquals(normResult3[1], 0);
		assertEquals(normResult3[2], -1);
	}
	
	/**
	 * Gets the average running time in milliseconds of the brute-force algorithm (findMaxSubarrayA) on an array of size n.
	 * @param n the size of the input array
	 * @return the average running time in milliseconds of the brute-force algorithm
	 */
	double getAverageTimeA(int n) {
		long startTime, endTime;
		long elapsedTime = 0L;
		int[] a = new int[n];
		Random random = new Random();
		random.setSeed(20);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < n; j++) {
				a[j] = random.nextInt();
			}
			startTime = System.nanoTime();
			MaxSubarrayAlgorithms.findMaxSubarrayA(a, n);
			endTime = System.nanoTime();
			elapsedTime += endTime - startTime;
		}
		return (double) elapsedTime / 10000000;
	}
	
	/**
	 * Prints the average running times of the brute-force algorithm (findMaxSubarrayA) on arrays of different sizes.
	 */
	@Test
	void testRunningTimeA() {
		System.out.println("Experimental analysis of the running time for brute force algorithm:");
		System.out.println("Average elapsed time when n = 100: " + getAverageTimeA(100) + " ms");
		System.out.println("Average elapsed time when n = 200: " + getAverageTimeA(200) + " ms");
		System.out.println("Average elapsed time when n = 500: " + getAverageTimeA(500) + " ms");
		System.out.println("Average elapsed time when n = 1000: " + getAverageTimeA(1000) + " ms");
		System.out.println("Average elapsed time when n = 2000: " + getAverageTimeA(2000) + " ms");
		System.out.println("Average elapsed time when n = 5000: " + getAverageTimeA(5000) + " ms");
		System.out.println("Average elapsed time when n = 10000: " + getAverageTimeA(10000) + " ms\n");
	}
	
	/**
	 * Gets the average running time in milliseconds of the divide-and-conquer algorithm (findMaxSubarrayB) on an array of size n.
	 * @param n the size of the input array
	 * @return the average running time in milliseconds of the divide-and-conquer algorithm
	 */
	double getAverageTimeB(int n) {
		long startTime, endTime;
		long elapsedTime = 0L;
		int[] a = new int[n];
		Random random = new Random();
		random.setSeed(20);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < n; j++) {
				a[j] = random.nextInt();
			}
			startTime = System.nanoTime();
			MaxSubarrayAlgorithms.findMaxSubarrayB(a, 0, n - 1);
			endTime = System.nanoTime();
			elapsedTime += endTime - startTime;
		}
		return (double) elapsedTime / 10000000;
	}
	
	/**
	 * Prints the average running times of the divide-and-conquer algorithm (findMaxSubarrayB) on arrays of different sizes.
	 */
	@Test
	void testRunningTimeB() {
		System.out.println("Experimental analysis of the running time for divide-and-conquer algorithm:");
		System.out.println("Average elapsed time when n = 100: " + getAverageTimeB(100) + " ms");
		System.out.println("Average elapsed time when n = 200: " + getAverageTimeB(200) + " ms");
		System.out.println("Average elapsed time when n = 500: " + getAverageTimeB(500) + " ms");
		System.out.println("Average elapsed time when n = 1000: " + getAverageTimeB(1000) + " ms");
		System.out.println("Average elapsed time when n = 2000: " + getAverageTimeB(2000) + " ms");
		System.out.println("Average elapsed time when n = 5000: " + getAverageTimeB(5000) + " ms");
		System.out.println("Average elapsed time when n = 10000: " + getAverageTimeB(10000) + " ms\n");
	}
	
	/**
	 * Gets the average running time in milliseconds of Kadane's algorithm (findMaxSubarrayC) on an array of size n.
	 * @param n the size of the input array
	 * @return the average running time in milliseconds of Kadane's algorithm
	 */
	double getAverageTimeC(int n) {
		long startTime, endTime;
		long elapsedTime = 0L;
		int[] a = new int[n];
		Random random = new Random();
		random.setSeed(20);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < n; j++) {
				a[j] = random.nextInt();
			}
			startTime = System.nanoTime();
			MaxSubarrayAlgorithms.findMaxSubarrayC(a, n);
			endTime = System.nanoTime();
			elapsedTime += endTime - startTime;
		}
		return (double) elapsedTime / 10000000;
	}
	
	/**
	 * Prints the average running times of Kadane's algorithm (findMaxSubarrayC) on arrays of different sizes.
	 */
	@Test
	void testRunningTimeC() {
		System.out.println("Experimental analysis of the running time for Kadane's algorithm:");
		System.out.println("Average elapsed time when n = 100: " + getAverageTimeC(100) + " ms");
		System.out.println("Average elapsed time when n = 200: " + getAverageTimeC(200) + " ms");
		System.out.println("Average elapsed time when n = 500: " + getAverageTimeC(500) + " ms");
		System.out.println("Average elapsed time when n = 1000: " + getAverageTimeC(1000) + " ms");
		System.out.println("Average elapsed time when n = 2000: " + getAverageTimeC(2000) + " ms");
		System.out.println("Average elapsed time when n = 5000: " + getAverageTimeC(5000) + " ms");
		System.out.println("Average elapsed time when n = 10000: " + getAverageTimeC(10000) + " ms");
	}
}
