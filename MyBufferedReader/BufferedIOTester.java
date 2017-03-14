package il.ac.tau.cs.sw1.ex8.bufferedIO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedIOTester {
	public static final String INPUT_FOLDER = "resources/";

	public static void main(String[] args) throws IOException{

		String inputFileName = INPUT_FOLDER + "rocky1.txt";
		FileReader fReader = new FileReader(new File(inputFileName));
		IBufferedReader bR = new MyBufferReader(fReader, 1000);
		if (!bR.getNextLine().equals("Now somewhere in the Black mining Hills of Dakota")){
			System.out.println("Reader Error: wrong firstLine");
		}
		fReader.close();
		bR.close();
		
		MyFileReader myFReader = new MyFileReader(new File(inputFileName));
		IBufferedReader bR2 = new MyBufferReader(myFReader, 7);
		assert bR2.getNextLine().equals("Now somewhere in the Black mining Hills of Dakota");
		assert myFReader.getReadsCount() == 8;
		myFReader.close();
		bR2.close();
		
	}
}
