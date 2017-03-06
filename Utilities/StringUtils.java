import java.util.Arrays;

/*
 * Various utilities for Strings
 */

public class StringUtils {

	final static String STRING = "string";
	final static String EMPTY_STRING = "";
	final static String ONLY_SPACES = "  ";
	final static String MULTIPLE_SPACES = "  str  ing  ";

	/*****************************************************************************************************/

	/**********************
	 * Testers
	 *************************************************************/
	/*****************************************************************************************************/	
	
	/*
	 * Main Tester
	 */
	public static void main(String[] arg) {
		
		sortStringWordsTest();
		 mergeStringsTest();
		isAnagramTest();
	}

	/*
	 * Tester for sortStringWords
	 */
	public static void sortStringWordsTest() {

		assert sortStringWords("to be or not to be").equals("be be not or to to");

		assert sortStringWords(EMPTY_STRING).equals(EMPTY_STRING);
		assert sortStringWords(ONLY_SPACES).equals(EMPTY_STRING);
		assert sortStringWords(MULTIPLE_SPACES).equals("ing str"); 
	}

	/*
	 * Tester for mergeStrings
	 */
	public static void mergeStringsTest() {

		assert mergeStrings("boy", "girl").equals("boygirl");
		assert mergeStrings("catdog", "boygirl").equals("catdbyirl");
		assert mergeStrings("abcdefg", "bcgfhi").equals("adehi");

		assert mergeStrings(EMPTY_STRING, STRING).equals(STRING);
		assert mergeStrings(STRING, EMPTY_STRING).equals(STRING);
		assert mergeStrings(EMPTY_STRING, EMPTY_STRING).equals(EMPTY_STRING);
		assert mergeStrings(STRING, STRING).equals(EMPTY_STRING);
	}

	/*
	 * Tester for isAnagram
	 */
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

	/*****************************************************************************************************/

	/**********************
	 * Functions
	 *************************************************************/
	/*****************************************************************************************************/	
	
	/*
	 * @param str - String to sort
	 * 
	 * @return sorted(str)
	 */
	public static String sortStringWords(String str) {
		
		str=str.trim();
		String[] result = str.split("\\s+");
		Arrays.sort(result);
		return Arrays.toString(result).replaceAll(",|\\[|\\]", "");
	}

	/*
	 * @param a - 1st String
	 * @param b - 2nd String
	 * 
	 * @return xor(a,b), where:
	 * xor(a,b) = all unique chars in a&b by order of appearance
	 */
	public static String mergeStrings(String a, String b) {
		
		String temp = a + b;
		String result = "";
		for (char c : temp.toCharArray()) {
			if (a.indexOf(c) < 0 || b.indexOf(c) < 0) {
				result += c;
			}
		}
		return result;
	}

	/*
	 * @param a - 1st String
	 * @param b - 2nd String
	 * 
	 * @return
	 * True - if a&b are anagrams
	 * False - Otherwise
	 */
	public static boolean isAnagram(String a, String b) {
		
		a = a.replaceAll("\\s+", "");
		b = b.replaceAll("\\s+", "");
		return mergeStrings(a, b) == "";
	}
}
