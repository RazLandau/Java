import java.util.Arrays;

public class TestUnit {

	public static void main(String[] arg) {
		System.out.print("ArrayUtilsTest: ");
		ArrayUtilsTest.Run();
		System.out.println("Passed");
		System.out.print("StringUtilsTest: ");
		StringUtilsTest.Run();
		System.out.println("Passed");
	}

	public static class ArrayUtilsTest extends ArrayUtils {

		final static int[] EMPTY_ARRAY = new int[0];
		final static int[] SINGLETON = { 1 };

		public static void Run() {
			incArrayTest();
			sumArrayTest();
			altenateSumTest();
			matrixMultiplicationTest();
		}

		public static void incArrayTest() {

			final int[] EXAMPLE0 = new int[] { 0, 0, 0, 0, 0 };
			final int[] RESULT0 = new int[] { 0, 1, 2, 3, 4 };
			final int[] EXAMPLE1 = new int[] { 1, 2, 3, 4, 5 };
			final int[] RESULT1 = new int[] { 1, 3, 5, 7, 9 };

			assert Arrays.equals(incArray(EXAMPLE0), RESULT0);
			assert Arrays.equals(incArray(EXAMPLE1), RESULT1);

			assert Arrays.equals(incArray(EMPTY_ARRAY), EMPTY_ARRAY);
			assert Arrays.equals(incArray(SINGLETON), new int[] { SINGLETON[0] });

		}

		public static void sumArrayTest() {

			final int[] EXAMPLE0 = new int[] { 1, 2, 3, 4 };
			final int[] RESULT0 = new int[] { 3, 7 };
			final int[] EXAMPLE1 = new int[] { 1, 2, 3, 4, 5 };
			final int[] RESULT1 = new int[] { 3, 7, 5 };

			assert Arrays.equals(sumArray(EXAMPLE0), RESULT0);
			assert Arrays.equals(sumArray(EXAMPLE1), RESULT1);

			assert Arrays.equals(incArray(EMPTY_ARRAY), EMPTY_ARRAY);
			assert Arrays.equals(incArray(SINGLETON), SINGLETON);

		}

		public static void altenateSumTest() {

			final int[] EXAMPLE0 = new int[] { 1, -2, 3, 4, 5 };
			final int RESULT0 = -6;
			final int[] EXAMPLE1 = new int[] { 1, 2, -3, 4, 5 };
			final int RESULT1 = -8;

			assert alternateSum(EXAMPLE0) == RESULT0;
			assert alternateSum(EXAMPLE1) == RESULT1;

			assert alternateSum(SINGLETON) == SINGLETON[0];
			assert alternateSum(EMPTY_ARRAY) == 0;
			;
		}

		public static void matrixMultiplicationTest() {

			final int[][] EMPTY_MATRIX = new int[0][0];
			final int[][] ALL_ZEROS = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
			final int[][] SINGLETON_MATRIX = { { 1 } };
			final int[][] COL_VECTOR = { { 1 }, { 1 }, { 1 } };
			final int[][] ROW_VECTOR = { { 1, 1, 1 } };
			final int[][] ROWxCOL_RESULT = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };

			final int[][] EXAMPLE0_M = new int[][] { { 1, 2, 3, }, { 4, 5, 6 }, { 7, 8, 9 } };
			final int[][] EXAMPLE0_N = new int[][] { { 1, 0, 0, }, { 0, 1, 0 }, { 0, 0, 1 } };
			final int[][] RESULT0 = new int[][] { { 1, 2, 3, }, { 4, 5, 6 }, { 7, 8, 9 } };
			final int[][] EXAMPLE1_M = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
			final int[][] EXAMPLE1_N = new int[][] { { 1, 1 }, { 2, 2 } };
			final int[][] RESULT1 = new int[][] { { 5, 5 }, { 11, 11 }, { 17, 17 } };

			assert Arrays.deepEquals(matrixMultiplication(EXAMPLE0_M, EXAMPLE0_N), RESULT0);
			assert Arrays.deepEquals(matrixMultiplication(EXAMPLE1_M, EXAMPLE1_N), RESULT1);

			assert Arrays.deepEquals(matrixMultiplication(EMPTY_MATRIX, EMPTY_MATRIX), EMPTY_MATRIX);
			assert Arrays.deepEquals(matrixMultiplication(EXAMPLE0_M, ALL_ZEROS), ALL_ZEROS);
			assert Arrays.deepEquals(matrixMultiplication(ALL_ZEROS, EXAMPLE0_N), ALL_ZEROS);
			assert Arrays.deepEquals(matrixMultiplication(SINGLETON_MATRIX, SINGLETON_MATRIX), SINGLETON_MATRIX);
			assert Arrays.deepEquals(matrixMultiplication(COL_VECTOR, ROW_VECTOR), ROWxCOL_RESULT);
		}
	}

	public static class StringUtilsTest extends StringUtils {

		final static String STRING = "string";
		final static String EMPTY_STRING = "";
		final static String ONLY_SPACES = "  ";
		final static String MULTIPLE_SPACES = "  str  ing  ";

		public static void Run() {
			sortStringWordsTest();
			mergeStringsTest();
			isAnagramTest();
		}

		public static void sortStringWordsTest() {

			assert sortStringWords("to be or not to be").equals("be be not or to to");

			assert sortStringWords(EMPTY_STRING).equals(EMPTY_STRING);
			assert sortStringWords(ONLY_SPACES).equals(EMPTY_STRING);
			assert sortStringWords(MULTIPLE_SPACES).equals("ing str");
		}

		public static void mergeStringsTest() {

			assert mergeStrings("boy", "girl").equals("boygirl");
			assert mergeStrings("catdog", "boygirl").equals("catdbyirl");
			assert mergeStrings("abcdefg", "bcgfhi").equals("adehi");

			assert mergeStrings(EMPTY_STRING, STRING).equals(STRING);
			assert mergeStrings(STRING, EMPTY_STRING).equals(STRING);
			assert mergeStrings(EMPTY_STRING, EMPTY_STRING).equals(EMPTY_STRING);
			assert mergeStrings(STRING, STRING).equals(EMPTY_STRING);
		}

		public static void isAnagramTest() {

			assert isAnagram("mother in law", "hitler woman") == true;
			assert isAnagram("software", "jeans") == false;

			assert isAnagram(EMPTY_STRING, STRING) == false;
			assert isAnagram(STRING, EMPTY_STRING) == false;
			assert isAnagram(EMPTY_STRING, EMPTY_STRING) == true;
			assert isAnagram(STRING, STRING) == true;
			assert isAnagram(STRING, MULTIPLE_SPACES) == true;
			assert isAnagram(MULTIPLE_SPACES, STRING) == true;
		}
	}
}
