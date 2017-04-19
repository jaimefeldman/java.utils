package string;

public class CheckString {
	
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
