/**
* Prints the number of ints given by the user by CL that are divisible by 6
*/
public class Count6Divisibles {
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
