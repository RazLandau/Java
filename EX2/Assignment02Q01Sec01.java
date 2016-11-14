
public class Assignment02Q01Sec01 {
	public static void main (String[] arg){
		for (String c : arg){
			int ascii = (int)c.charAt(0);
			if (ascii%2 == 0){
				System.out.println(c);	
			}
		}
	}

}
