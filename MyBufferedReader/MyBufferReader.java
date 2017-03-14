package il.ac.tau.cs.sw1.ex8.bufferedIO;

import java.io.FileReader;
import java.io.IOException;

/**
 * Extension to Java's BufferedReader with parameterized buffer size
 * @author Raz
 *
 */
public class MyBufferReader implements IBufferedReader {

	private FileReader fReader;
	private char[] buffer;

	public MyBufferReader(FileReader fReader, int bufferSize) {
		this.fReader = fReader;
		buffer = new char[bufferSize];
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public String getNextLine() throws IOException {
		String result = "";
		int numRead;
		while ((numRead = fReader.read(buffer)) != -1) {
			String string = new String(buffer, 0, numRead);
			int index = string.indexOf('\n');
			if(index >= 0){
				result += string.substring(0, index);
				return result;
			}
			result += string;
		}
		return result;
	}

}
