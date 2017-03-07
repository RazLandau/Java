package il.ac.tau.cs.sw1.ex5;

import java.io.IOException;

public class BigramModelTester {
	public static final String ALL_YOU_NEED_FILENAME = "resources/hw5/all_you_need.txt";
	public static final String EMMA_FILENAME = "resources/hw5/emma.txt";
	public static final String VOCABULARY_FILENAME = "resources/hw5/vocabulary.txt";
	public static final String BLACKBIRD_FILENAME = "resources/hw5/blackbird.txt";
	public static final String ALL_YOU_NEED_COUNTS_DIR = "resources/hw5/all_you_need_counts.txt";

	public static void main(String[] args) throws IOException{
		buildModelFromFileTest();
		getBigramCountTest();
		getWordIndexTest();
		defaultTest();
	}
	
	public static void buildModelFromFileTest() throws IOException{
		BigramModel sG = new BigramModel();
		assert sG.buildModelFromFile(ALL_YOU_NEED_FILENAME) == 5;
		assert sG.buildModelFromFile(VOCABULARY_FILENAME) == 15;
		assert sG.buildModelFromFile(BLACKBIRD_FILENAME) == 36;
		assert sG.buildModelFromFile(EMMA_FILENAME) == 7544;
	}
	
	public static void getBigramCountTest() throws IOException{
		
		BigramModel sG = new BigramModel();
		sG.buildModelFromFile(VOCABULARY_FILENAME);
		assert sG.getBigramCount("i", "look") == 1;
		assert sG.getBigramCount("i", "see") == 1;
		assert sG.getBigramCount("look", "at") == 1;
		assert sG.getBigramCount("at", "the") == 1;
		assert sG.getBigramCount("the", "floor") == 1;
		assert sG.getBigramCount("floor", "and") == 1;
		assert sG.getBigramCount("and", "i") == 1;
		assert sG.getBigramCount("i", "see") == 1;
		assert sG.getBigramCount("see", "it") == 1;
		assert sG.getBigramCount("it", "needs") == 1;
		assert sG.getBigramCount("needs", "sweeping") == 1;
		assert sG.getBigramCount("sweeping", "while") == 1;
		assert sG.getBigramCount("while", "my") == 1;
		assert sG.getBigramCount("my", "guitar") == 1;
		assert sG.getBigramCount("guitar", "gently") == 1;
		assert sG.getBigramCount("gently", "wheeps") == 1;


	}
	
	public static void getWordIndexTest() throws IOException{
		
		BigramModel sG = new BigramModel();
		sG.buildModelFromFile(VOCABULARY_FILENAME);
		
		assert sG.getWordIndex("i") == 0;
		assert sG.getWordIndex("look") == 1;
		assert sG.getWordIndex("at") == 2;
		assert sG.getWordIndex("the") == 3;
		assert sG.getWordIndex("floor") == 4;
		assert sG.getWordIndex("and") == 5;
		assert sG.getWordIndex("see") == 6;
		assert sG.getWordIndex("it") == 7;
		assert sG.getWordIndex("needs") == 8;
		assert sG.getWordIndex("sweeping") == 9;
		assert sG.getWordIndex("while") == 10;
		assert sG.getWordIndex("my") == 11;
		assert sG.getWordIndex("guitar") == 12;
		assert sG.getWordIndex("gently") == 13;
		assert sG.getWordIndex("wheeps") == 14;
	}
	
	public static void defaultTest() throws IOException{
		BigramModel sG = new BigramModel();
		int numOfWords = sG.buildModelFromFile(ALL_YOU_NEED_FILENAME);
		if (numOfWords != 5){
			System.out.println("Error 1");
		}
		if (sG.getBigramCount("is", "love") != 3){
			System.out.println("Error 2:");
		}
		if (sG.getBigramCount("all", "love") != 0){
			System.out.println("Error 3");
		}
		
		if (sG.getWordIndex("you") != 1){
			System.out.println("Error 4");
		}
		
		sG.saveModelToFile(ALL_YOU_NEED_COUNTS_DIR);
		sG.loadModelFromFile(ALL_YOU_NEED_COUNTS_DIR);
		if (sG.getBigramCount("is", "love") != 3){
			System.out.println("Error 4");
		}
		
		if (!sG.getMostFrequentProceeding("is").equals("love")){
			System.out.println("Error 5");
		}
		if (!sG.getMostFrequentProceeding("love").equals("all")){
			System.out.println("Error 5");
		}
		if (!sG.buildSentence("all", 3).equals("all you need")){
			System.out.println("Error 6");
		}
		if (!sG.buildSentence("all", 6).equals("all you need is love all")){
			// <love all> and <love love> have the same number of counts, but since we select
			//the word with the lowest index, we pick all over love
			System.out.println("Error 7");
		}
		if (BigramModel.calcCosineSim(new int[] {1,2,0,4, 2}, new int[] {5, 0, 3, 1, 1}) != 11./30){
			System.out.println("Error 8");
		}		
		sG.buildModelFromFile(EMMA_FILENAME);
		if (!sG.getClosestWord("scheme").equals("trick")){
			System.out.println("Error 9");
		}
		if (!sG.getClosestWord("good").equals("great")){
			System.out.println("Error 10");
		}		
		System.out.println("done!");

	}
}
