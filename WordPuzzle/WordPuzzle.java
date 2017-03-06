package il.ac.tau.cs.sw1.ex4;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


/*
 * This is a program simulating an interactive WordPuzzle game with the user:
 * 1. Setting Stage:
 * 		a. The program recieves a path to a vocabulary by the CLI
 * 		b. The user chooses a word from given vocabulary
 * 		c. The user creates a puzzle for the chosen word, containing blank chars
 * 2. Game Stage:
 * 		The user has k turns (k = number of blanks in puzzle + 3). Each turn:
 * 		a. The puzzle is displayed
 * 		b. The user guesses one of the blank chars in the puzzle
 * 		c. If the guess is successful - That char is revealed in the puzzle
 * 		d. If all the blank chars are revealed - The game is won!
 * 		   If all turns have passed and the puzzle isn't yet fully revealed - The game is over!
 */

public class WordPuzzle {
	
	public static final char BLANK_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;

	/*****************************************************************************************************/
	/**********************
	 * Tester
	 *************************************************************/
	/*****************************************************************************************************/	
	
	public static void main(String[] args) throws Exception {

		String[] vocabulary = initializeVocabulary(args);

		Scanner in = new Scanner(System.in);

		printSettingsMessage();
		String word = setupWord(in, vocabulary);
		char[] puzzle = setupPuzzle(in, vocabulary, word);

		printGameStageMessage();
		play(in, word, puzzle);

		in.close();
	}

	/*****************************************************************************************************/
	/**********************
	 * Functions
	 *************************************************************/
	/*****************************************************************************************************/
	
	/*
	 * @param scanner - Vocabulary resource
	 * 
	 * @return An array of the first 3000 words in given vocabulary lowercased and sorted lexicographically
	 * without repetitions, where:
	 * Word = Any non-empty String seperated by whitespaces
	 * 
	 */
	public static String[] scanVocabulary(Scanner scanner) {
		assert scanner != null;
		String[] result = new String[MAX_VOCABULARY_SIZE];
		int num_of_string = 0;
		while (scanner.hasNext() && num_of_string < MAX_VOCABULARY_SIZE) {
			String s = scanner.next().toLowerCase();
			if (!Arrays.asList(result).contains(s)) {
				result[num_of_string++] = s;
			}
		}
		result = Arrays.copyOf(result, num_of_string);
		Arrays.sort(result);
		return result;
	}

	/*
	 * @param vocabulary - Vocabulary initialized by scanVocabulary
	 * @param word
	 * 
	 * @return
	 * True - if word is in vocabulary
	 * False - otherwise
	 */
	public static boolean isInVocabulary(String[] vocabulary, String word) {
		assert vocabulary != null;
		return Arrays.asList(vocabulary).contains(word);
	}

	/*
	 * @param word
	 * @param pattern
	 * 
	 * @return
	 * True - if:
	 * 1. len(pattern) = len(word)
	 * 2. Pattern hides at least 1 char in word
	 * 3. Pattern contains only '*' and '_'
	 * 4. Pattern is consistent
	 * False - Otherwise
	 */
	public static boolean checkPattern(String word, String pattern) {
		if (word.length() != pattern.length()) {
			return false;
		}
		if (pattern.indexOf('_') == -1) {
			return false;
		}
		for (char c : pattern.toCharArray()) {
			if (c != '*' && c != '_') {
				return false;
			}
		}
		if (!consistentPattern(word, pattern)) {
			return false;
		}
		return true;
	}

	/*
	 * @param pattern
	 * 
	 * @return number of '_' chars in pattern
	 */
	public static int countBlanksInPattern(String pattern) {
		int count = 0;
		for (char c : pattern.toCharArray()) {
			if (c == '_') {
				count++;
			}
		}
		return count;
	}

	/*
	 * @param word
	 * @param pattern
	 * 
	 * @return puzzle for word that matches pattern
	 */
	public static char[] createPuzzle(String word, String pattern) {
		assert checkPattern(word, pattern);
		char[] result = new char[word.length()];
		for (int i = 0; i < word.length(); i++) {
			switch (pattern.charAt(i)) {
			case ('*'):
				result[i] = word.charAt(i);
				break;
			case ('_'):
				result[i] = '_';
				break;
			}
		}
		return result;
	}

	/*
	 * @param pattern
	 * @param puzzle
	 * @param vocabulary
	 * 
	 * @return
	 * True - if only 1 word in vocabulary matches puzzle for pattern
	 * False - otherwise
	 */
	public static boolean hasUniqueSolution(String pattern, char[] puzzle, String[] vocabulary) {
		int count = 0;
		for (String s : vocabulary) {
			if (!checkPattern(s, pattern)) {
				continue;
			}
			if (Arrays.equals(createPuzzle(s, pattern), puzzle)) {
				count++;
			}
		}
		return count == 1;
	}

	
	/*
	 * 
	 * @param guess
	 * @param word
	 * @param puzzle
	 * 
	 * @return number of new chars revealed by guess
	 * $post Applies guess to puzzle
	 */
	public static int applyGuess(char guess, String word, char[] puzzle) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess && puzzle[i] == '_') {
				puzzle[i] = guess;
				count++;
			}
		}
		return count;
	}

	/*****************************************************************************************************/
	
	/**********************
	 * Print functions
	 *************************************************************/
	
	/*****************************************************************************************************/

	public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}

	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterWordMessage() {
		System.out.println("Enter your word:");
	}

	public static void printIllegalWordMessage() {
		System.out.println("Illegal word!");
	}

	public static void printEnterYourPattern() {
		System.out.println("Enter your pattern:");
	}

	public static void printIllegalPatternMessage() {
		System.out.println("Illegal pattern!");
	}

	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}

	
	/**********************
	 * Print functions
	 *************************************************************/

	/*
	 * @return initialized vocabulary by scanVocabulary
	 */
	private static String[] initializeVocabulary(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("[ERROR] " + "Filename missing");
		}
		String file_root = args[0];
		File file = new File(file_root);
		if (!file.exists()) {
			throw new Exception("[ERROR] " + "No such file");
		}
		Scanner vocabularyScanner = new Scanner(new File(file_root));
		String[] vocabulary = WordPuzzle.scanVocabulary(vocabularyScanner);
		vocabularyScanner.close();
		printReadVocabulary(file_root, vocabulary.length);
		return vocabulary;
	}
	
	/*
	 * 
	 * @return word to guess from user
	 */

	private static String setupWord(Scanner in, String[] vocabulary) {
		String word = "";
		while (word == "") {
			printEnterWordMessage();
			word = in.nextLine();
			if (!WordPuzzle.isInVocabulary(vocabulary, word)) {
				printIllegalWordMessage();
				word = "";
			}
		}
		return word;
	}
	
	/*
	 * @return puzzle for word from user
	 */

	private static char[] setupPuzzle(Scanner in, String[] vocabulary, String word) {
		while(true){
			printEnterYourPattern();
			String pattern = in.nextLine();
			if(WordPuzzle.checkPattern(word, pattern) && WordPuzzle.hasUniqueSolution(pattern, WordPuzzle.createPuzzle(word, pattern), vocabulary)){
				return WordPuzzle.createPuzzle(word, pattern);
			}
			printIllegalPatternMessage();
		}
	}

	
	/*
	 * Interactive play with user
	 */
	private static void play(Scanner in, String word, char[] puzzle) {
		int guesses = WordPuzzle.countBlanksInPattern(Arrays.toString(puzzle)) + 3;
		while (guesses > 0 && countBlanksInPattern(Arrays.toString(puzzle)) > 0) {
			printPuzzle(puzzle);
			printEnterYourGuessMessage();
			char guess = in.nextLine().charAt(0);
			switch (WordPuzzle.applyGuess(guess, word, puzzle)) {
			case (0):
				printWrongGuess(--guesses);
				break;
			default:
				printCorrectGuess(--guesses);
			}
		}
		if (countBlanksInPattern(Arrays.toString(puzzle)) == 0) {
			printWinMessage();
		} else {
			printGameOver();
		}
	}

	
	/*
	 * @param word
	 * @param pattern
	 * 
	 * @return
	 * True - if all appearances of the same char in word are the same in pattern
	 * False - otherwise
	 */
	private static boolean consistentPattern(String word, String pattern) {
		String stars = "";
		String underscores = "";
		for (int i = 0; i < pattern.length(); i++) {
			switch (pattern.charAt(i)) {
			case ('*'):
				stars += word.charAt(i);
				break;
			case ('_'):
				underscores += word.charAt(i);
				break;
			}
		}
		for (char c : stars.toCharArray()) {
			if (underscores.indexOf(c) != -1) {
				return false;
			}
		}
		return true;
	}
}
