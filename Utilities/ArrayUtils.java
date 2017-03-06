import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * Various utilities for Arrays
 */

public class ArrayUtils {
	
	/*****************************************************************************************************/

	/**********************
	 * Testers
	 *************************************************************/
	/*****************************************************************************************************/	
	
	/*
	 * Main Tester
	 */
	public static void main(String[] arg) {
		incArrayTest();
		sumArrayTest();
		alternateSumTest();
		matrixMultiplicationTest();
	}

	/*
	 * Tester for incArray
	 */
	public static void incArrayTest() {

		final int[] EXAMPLE0 = new int[] { 0, 0, 0, 0, 0 };
		final int[] RESULT0 = new int[] { 0, 1, 2, 3, 4 };
		final int[] EXAMPLE1 = new int[] { 1, 2, 3, 4, 5 };
		final int[] RESULT1 = new int[] { 1, 3, 5, 7, 9 };

		assert Arrays.equals(incArray(EXAMPLE0), RESULT0);
		assert Arrays.equals(incArray(EXAMPLE1), RESULT1);
	}

	/*
	 * Tester for sumArray
	 */
	public static void sumArrayTest() {

		final int[] EXAMPLE0 = new int[] { 1, 2, 3, 4 };
		final int[] RESULT0 = new int[] { 3, 7 };
		final int[] EXAMPLE1 = new int[] { 1, 2, 3, 4, 5 };
		final int[] RESULT1 = new int[] { 3, 7, 5 };

		assert Arrays.equals(sumArray(EXAMPLE0), RESULT0);
		assert Arrays.equals(sumArray(EXAMPLE1), RESULT1);
	}

	/*
	 * Tester for alternateSum
	 */
	public static void alternateSumTest() {

		final int[] EXAMPLE0 = new int[] { 1, -2, 3, 4, 5 };
		final int RESULT0 = -6;
		final int[] EXAMPLE1 = new int[] { 1, 2, -3, 4, 5 };
		final int RESULT1 = -8;
		
		assert alternateSum(EXAMPLE0) == RESULT0;
		assert alternateSum(EXAMPLE1) == RESULT1;
	}

	/*
	 * Tester for matrixMultiplication
	 */
	public static void matrixMultiplicationTest() {

		final int[][] EXAMPLE0_M = new int[][] { { 1, 2, 3, }, { 4, 5, 6 }, { 7, 8, 9 } };
		final int[][] EXAMPLE0_N = new int[][] { { 1, 0, 0, }, { 0, 1, 0 }, { 0, 0, 1 } };
		final int[][] RESULT0 = new int[][] { { 1, 2, 3, }, { 4, 5, 6 }, { 7, 8, 9 } };
		final int[][] EXAMPLE1_M = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		final int[][] EXAMPLE1_N = new int[][] { { 1, 1 }, { 2, 2 } };
		final int[][] RESULT1 = new int[][] { { 5, 5 }, { 11, 11 }, { 17, 17 } };

		assert Arrays.deepEquals(matrixMultiplication(EXAMPLE0_M, EXAMPLE0_N), RESULT0);
		assert Arrays.deepEquals(matrixMultiplication(EXAMPLE1_M, EXAMPLE1_N), RESULT1);
	}

	/*****************************************************************************************************/

	/**********************
	 * Function
	 *************************************************************/
	/*****************************************************************************************************/		
	
	/*
	 * @param array - Array of ints
	 * 
	 * @return New array where new_array[i] = old_array[i+1]
	 */
	public static int[] incArray(int[] array) {
		int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i] + i;
		}
		return result;
	}

	/*
	 * @param array - Array of ints
	 * 
	 * @return New array of given array tuples' sums
	 * Example: sumArray([1,2,3,4]) = [3,7]
	 */
	public static int[] sumArray(int[] array) {
		if (array.length % 2 == 0) {
			int[] result = new int[array.length / 2];
			for (int i = 0; i < array.length / 2; i++) {
				result[i] = array[i * 2] + array[i * 2 + 1];
			}
			return result;
		} else {
			int[] result = new int[array.length / 2 + 1];
			for (int i = 0; i < array.length / 2; i++) {
				result[i] = array[i * 2] + array[i * 2 + 1];
			}
			result[result.length - 1] = array[array.length - 1];
			return result;
		}
	}

	/*
	 * @param array - Array of ints
	 * 
	 * @return Min alternateSum in array, where:
	 * alternateSum(array, i, j) = array[i]-array[i+1]+array[i+2]-...array[j]
	 */
	public static int alternateSum(int[] array) {
		if(array.length == 0){
			return 0;
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				int[] temp = Arrays.copyOfRange(array, i, j + 1);
				for (int k = 1; k < temp.length; k += 2) {
					temp[k] *= -1;
				}
				int temp_sum = IntStream.of(temp).sum();
				if (temp_sum < result) {
					result = IntStream.of(temp).sum();
				}

			}
		}
		return result;
	}

	/*
	 * @param m - 1st Matrix of ints
	 * @param n - 2nd Matrix of ints
	 * 
	 * @return m*n
	 */
	public static int[][] matrixMultiplication(int[][] m, int[][] n) {
		if (m.length == 0 || n.length == 0 ){
			return new int[0][0];
		}
		int[][] result = new int[m.length][n[0].length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < n[0].length; j++) {
				for (int k = 0; k < m[0].length; k++) {
					result[i][j] += m[i][k] * n[k][j];
				}
			}
		}
		return result;
	}
}
