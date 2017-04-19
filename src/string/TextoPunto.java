package string;

public class TextoPunto {

	public static String print(String text1,String text2, int lenght) {
		
		int text1Lenght = text1.length();
	
		int rellenoDiferencia =  (lenght - text1Lenght);
		
		String strRelleno = new String(new char[rellenoDiferencia]).replace('\0', '.');
		return String.format("%-"+lenght+"s%s", text1.concat(strRelleno), text2);
	}
	
}
