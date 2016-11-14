
public class Assignment02Q02 {
	public static void main(String[] arg) {
		for (String s : arg){
			double fahrenheit = Double.parseDouble(s);
			double celsuius = ((fahrenheit - 32)*5)/9;
			celsuius = Math.round((celsuius) * 10) / 10.0;
			System.out.println("Fahrenheit: " + fahrenheit + " Celsius: " + celsuius);
		}
	}
}
