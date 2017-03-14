/*
* Print counts for each char in string/s given by the user by CL by order of appearance
*/
public class CountCharsAppearances {
	public static void main(String[] arg) {
		
		String str = uniqChars(arg);
		int[] arr = countChars(str, arg);
		print(str, arr);
	}
	
	private static String uniqChars(String[] arg){
		String str = "";
		for (String s: arg){
			for (char c : s.toCharArray()){
				String char_str = Character.toString(c);
				if (str.indexOf(char_str) == -1){
					str = str + char_str;
				}
			}
		}
		return str;
	}
	
	private static int[] countChars(String str, String[] arg){
		int[] arr = new int[26];
		int i = 0;
		for (char c: str.toCharArray()){
			for (String s : arg){
				if (s.indexOf(c) != -1){
					arr[i]++;
				}
			}
			i++;
		}	
		return arr;
	}
	
	private static void print(String str, int[] arr){
		Boolean first = true;
		for (int j = 0; j< str.length(); j++){
			if (!first){
			System.out.print('\t');					
		}
			System.out.print(str.charAt(j) + ":" + arr[j]);
			if (first) {
				first = false;
			}			
		}		
	}
}
