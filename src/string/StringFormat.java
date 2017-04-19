package string;

//Levanta la primera letra de una palabra.

public class StringFormat {

	// Metodo estatico para levantar la primera letra de una palabra.
	public static String upperFirst(String str) {
		String tempString = Character.toUpperCase(str.charAt(0)) + str.substring(1);
		return tempString;
	}
	
	
}
