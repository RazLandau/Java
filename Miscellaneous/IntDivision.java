/**
* Prints division result of 2 ints from CL
*/
public class IntDivision {
	public static void main (String[] arg){
		int numerator	= Integer.parseInt(arg[0]);
		int denominator	= Integer.parseInt(arg[1]);
		if(denominator == 0){
			System.out.println("Cannot perform zero division!");
		}
		else {
			int division = numerator / denominator;
			int remainder = numerator % denominator;
			System.out.print("The division result is: " + String.valueOf(division));
			if (remainder != 0){
				System.out.println(", remainder is: " + String.valueOf(remainder));
			}
		}
	}
}
