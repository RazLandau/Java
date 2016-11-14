import java.util.Arrays;





public class Assignment02Q05 {
	public static void main(String[] arg) {
		int[] primes = new int[20];
		primes[0] = 2;
		int num = 3;
		int status = 1;
		int index = 1;
		for (int i = 2; i <= 20; i++) {
			for (int j = 2; j <= Math.sqrt(num); j++) {
				if (num % j == 0) {
					status = 0;
					i--;
					break;
				}
			}
			if (status != 0) {
				primes[index] = num;
				index++;
			}
			status = 1;
			num++;
		}
		System.out.println(Arrays.toString(primes));
	}
}
