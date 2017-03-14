package il.ac.tau.cs.sw1.ex8.bufferedIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Helper class used to test MyBufferReader
 * @author Raz
 *
 */
public class MyFileReader extends FileReader{

	private int readsCounter = 0;
	public MyFileReader(File arg0) throws FileNotFoundException {
		super(arg0);
	}

	@Override
	public int read(char[] arg0, int arg1, int arg2) throws IOException {
		readsCounter++;
		return super.read(arg0, arg1, arg2);
	}
	
	public int getReadsCount(){
		return readsCounter;
	}
	

}
