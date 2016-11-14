
public class Assignment02Q01Sec03 {
	public static void main(String[] arg) {
		int count = 0;
		for (String s : arg) {
			int i = Integer.parseInt(s);
			if (i % 6 % 2 == 0) {
				count++;
			}
		}
		System.out.println(count);
	}

}
