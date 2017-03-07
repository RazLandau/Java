package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class WordPuzzleTester {

	public static void main(String[] args) throws Exception {
		scanVocabularyTest();
		isInVocabularyTest();
		checkPatternTest();
		countBlanksInPatternTest();
		createPuzzleTest();
		hasUniqueSolutionTest();
		applyGuessTest();
		defaultTest();
	}

	public static void scanVocabularyTest() throws Exception {

		final String ONE_LINE_FILE = "resources" + File.separator + "hw4" + File.separator + "vocabulary.txt";
		final String MULTIPLE_LINE_FILE = "resources" + File.separator + "hw4" + File.separator + "blackbird.txt";
		final String OVER_3000_WORDS_FILE = "resources" + File.separator + "hw4" + File.separator + "dorian_gray.txt";

		
		Scanner scanner0 = new Scanner(new File(ONE_LINE_FILE));
		String[] result0 = WordPuzzle.scanVocabulary(scanner0);
		assert result0.length == 15;
		assert result0[0].equals("and") && result0[14].equals("while");
		
		Scanner scanner1 = new Scanner(new File(MULTIPLE_LINE_FILE));
		String[] result1 = WordPuzzle.scanVocabulary(scanner1);
		assert result1.length == 36;
		assert result1[0].equals("all") && result1[35].equals("your");
		
		Scanner scanner2 = new Scanner(new File(OVER_3000_WORDS_FILE));
		String[] result2 = WordPuzzle.scanVocabulary(scanner2);
		assert (result2.length == 3000);
	}
	
	public static void checkPatternTest(){
		
		final String string0 = "playbuzz";
		final String PATTERN_TOO_SHORT = "";
		final String PATTERN_TOO_LONG = "_*_*_*_*_*_*";
		final String NO_UNDERSCORES = "********";
		final String NOT_ALL_STARS_OR_UNDERSCORES = "*_*_*_*p";
		final String INCONSISTENT_MULTIPLE_CHAR = "*_*_*_*_";
		final String GOOD_PATTERN = "*_*_*_**";
		
		assert !WordPuzzle.checkPattern(string0, PATTERN_TOO_SHORT);
		assert !WordPuzzle.checkPattern(string0, PATTERN_TOO_LONG);
		assert !WordPuzzle.checkPattern(string0, NO_UNDERSCORES);
		assert !WordPuzzle.checkPattern(string0, NOT_ALL_STARS_OR_UNDERSCORES);
		assert !WordPuzzle.checkPattern(string0, INCONSISTENT_MULTIPLE_CHAR);
		assert WordPuzzle.checkPattern(string0, GOOD_PATTERN);
	}
	
	public static void isInVocabularyTest(){
		
		String[] vocabulary = WordPuzzle.scanVocabulary(new Scanner("play"));	
		
		assert (WordPuzzle.isInVocabulary(vocabulary, "play"));
		assert (!WordPuzzle.isInVocabulary(vocabulary, "buzz"));
	}
	
	public static void countBlanksInPatternTest(){
		
		final String ALL_STARS = "**";
		final String ALL_UNDERSCORES = "__";
		final String EMPTY_STRING = "";
		final String START_AND_UNDERSCORES = "_*_*";
		
		assert WordPuzzle.countBlanksInPattern(ALL_STARS) == 0;
		assert WordPuzzle.countBlanksInPattern(ALL_UNDERSCORES) == 2;
		assert WordPuzzle.countBlanksInPattern(EMPTY_STRING) == 0;
		assert WordPuzzle.countBlanksInPattern(START_AND_UNDERSCORES) == 2;		
	}
	
	public static void createPuzzleTest(){
		
		final String STRING0 = "playbuzz";
		final String ALL_UNDERSCORES = "________";
		final char[] ALL_UNDERSCORES_RESULT = {'_','_','_','_','_','_','_','_'};
		final String STARS_AND_UNDERSCORES = "____****";
		final char[] STARS_AND_UNDERSCORES_RESULT = {'_','_','_','_','b','u','z','z'};
		
		assert Arrays.equals(WordPuzzle.createPuzzle(STRING0, ALL_UNDERSCORES), ALL_UNDERSCORES_RESULT);	
		assert Arrays.equals(WordPuzzle.createPuzzle(STRING0, STARS_AND_UNDERSCORES), STARS_AND_UNDERSCORES_RESULT);	

	}
	
	public static void hasUniqueSolutionTest(){
		
		final String[] VOCABULARY = WordPuzzle.scanVocabulary(new Scanner("at am"));
		
		final String BAD_PATTERN = "*_";
		final char[] BAD_PUZZLE = {'a','_'};
		final String GOOD_PATTERN = "_*";
		final char[] GOOD_PUZZLE = {'_','m'};
		
		
		assert !WordPuzzle.hasUniqueSolution(BAD_PATTERN, BAD_PUZZLE, VOCABULARY);
		assert WordPuzzle.hasUniqueSolution(GOOD_PATTERN, GOOD_PUZZLE, VOCABULARY);
	}
	
	public static void applyGuessTest(){
		
		final String WORD0 = "buzz";
		final char[] PUZZLE0 = {'_','_','_','_'};
		final char BAD_GUESS = 'x';
		final char GOOD_GUESS = 'b';
		final char MULTIPLE_CHAR_GUESS = 'z';
		
		assert WordPuzzle.applyGuess(BAD_GUESS, WORD0, PUZZLE0) == 0;
		assert Arrays.equals(PUZZLE0, PUZZLE0);
		assert WordPuzzle.applyGuess(GOOD_GUESS, WORD0, PUZZLE0) == 1;
		assert Arrays.equals(PUZZLE0, new char[] {'b','_','_','_'});
		assert WordPuzzle.applyGuess(GOOD_GUESS, WORD0, PUZZLE0) == 0;
		assert Arrays.equals(PUZZLE0, new char[] {'b','_','_','_'});
		assert WordPuzzle.applyGuess(MULTIPLE_CHAR_GUESS, WORD0, PUZZLE0) == 2;
		assert Arrays.equals(PUZZLE0, new char[] {'b','_','z','z'});
	}

	public static void defaultTest() {
		
		String vocabularyText = "I look at the floor and I see it needs sweeping while my guitar gently wheeps";
		Scanner vocabularyScanner = new Scanner(vocabularyText);
		String[] vocabulary = WordPuzzle.scanVocabulary(vocabularyScanner);
		String pattern1 = "**_*_";
		String pattern2 = "__*__";
		if (!WordPuzzle.isInVocabulary(vocabulary, "while")) {
			System.out.println("Error 1");
		}
		if (!WordPuzzle.checkPattern("while", pattern1)) {
			System.out.println("Error 2");
		}
		if (WordPuzzle.checkPattern("guiltar", pattern2)) {
			System.out.println("Error 3");
		}

		if (WordPuzzle.countBlanksInPattern(pattern2) != 4) {
			System.out.println("Error 4");
		}
		String pattern3 = "__*";
		char[] puzzle1 = WordPuzzle.createPuzzle("the", pattern3);
		if (!Arrays.equals(puzzle1, new char[] { '_', '_', 'e' })) {
			System.out.println("Error 5");
		}
		if (!WordPuzzle.hasUniqueSolution(pattern3, puzzle1, vocabulary)) {
			System.out.println("Error 6");
		}
		char[] puzzle2 = new char[] { 'w', '_', '_', 'l', 'e' };
		int numOfChangedLetters = WordPuzzle.applyGuess('i', "while", puzzle2);
		if (numOfChangedLetters != 1) {
			System.out.println("Error 7");
		}
		if (!Arrays.equals(puzzle2, new char[] { 'w', '_', 'i', 'l', 'e' })) {
			System.out.println("Error 8");
		}
		System.out.println("done!");
	}
}
