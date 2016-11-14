


public class Assignment02Q01Sec02 {
	public static void main (String[] arg){
		if (arg.length == 0){
			return;
		}
		if (arg.length == 1){
			System.out.println(Double.parseDouble(arg[0]));
			return;
		}
		double max = -Double.MAX_VALUE, second_max = -Double.MAX_VALUE;
		for (String s : arg){
			double d = Double.parseDouble(s);
			if (d > max){
				second_max = max;
				max = d;
			} else {
				if (d > second_max){
					second_max = d;
				}
			}
		}
		System.out.println(max);
		System.out.println(second_max);
	}
		
}
