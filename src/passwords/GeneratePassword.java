package passwords;

import java.security.SecureRandom;

public class GeneratePassword {

	public enum TiposPasswords {NUMEROS, LETRAS, ALFANUMERICO, ALFANUMERICOSIMBILICO};
	private static final String VALID_SPECIAL_CHARACTERS = "!@#$%&*()_-+=[]{}\\|:/?.,><"; 

	private static int 			PASSWORD_SIZE 			 = 8;
	private static Integer 		BLOCK_SIZE 				 = 0;

	private static TiposPasswords tipoPasswordEnUso = TiposPasswords.ALFANUMERICO;

	
	/* Establece un nuevo tipo para generar passwords. */
	public static void setTipo(TiposPasswords tipo) {

		tipoPasswordEnUso = tipo;
	}
	
	/* Establece la longitud de la password a generar */
	public static void setLenght(Integer size) {

		if(size > 0 && size != null) {
			PASSWORD_SIZE = size;

		}else {
			
			PASSWORD_SIZE = 8;
		}
	}

	/* Establece el blocksize */
	public static void setBlockSize(Integer size) {
		
		if(size > 0 && size != null) {
			BLOCK_SIZE = size;

		}else {

			BLOCK_SIZE = 0;
		}
	
		
	}
	

	/* Genera y devuelve una password */
	public static String getPassword() {
		
		return generatePassword();
		
	}

	
	
	///////////////////////////////////////////////////////////////// INTERNAS PRIVADAS ///////////////////////////////////////////////////////////////////////////////////// 
	
	private static String generatePassword() {
		
		//TiposPasswords tipoPassword = tipo;
	    
		SecureRandom random 		= new SecureRandom();
	    StringBuilder mstrPassword 	= new StringBuilder();

	    while (mstrPassword.length() < PASSWORD_SIZE) {
	    	
	        char character = (char) random.nextInt(Character.MAX_VALUE);
	        
	        switch (tipoPasswordEnUso) {
			case NUMEROS: 
				if ((character >= '0' && character <= '9')) 
					mstrPassword.append(character);
				break;

			case LETRAS:
				 if ((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z'))
					 mstrPassword.append(character);
				break;

			case ALFANUMERICO:
				if ((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z') || (character >= '0' && character <= '9')) {
					mstrPassword.append(character);
				}
				break;

			case ALFANUMERICOSIMBILICO:
				if ((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z') || (character >= '0' && character <= '9') || VALID_SPECIAL_CHARACTERS.contains(String.valueOf(character))) {
					mstrPassword.append(character);
				}
				break;
				
				
			default:
				break;
			}
	    }
	    
	    if(BLOCK_SIZE > 0) {

	    	return formatPasswordWithBlocks(mstrPassword.toString());
	    }else {

	    	return mstrPassword.toString();
	    }

		
	}
	
	private static String formatPasswordWithBlocks(String strPassword) {
	
		char[] passwordCharArray = strPassword.toCharArray();
		
		StringBuffer mutableStringBuffer = new StringBuffer();
		
		int blockCount = 0;

		for (int i = 0; i < passwordCharArray.length; i++) {
			blockCount++;

			if(blockCount == BLOCK_SIZE + 1) {
				mutableStringBuffer.append('-');
				blockCount=0;
				i--;
				continue;
			}
			mutableStringBuffer.append(passwordCharArray[i]);
		}
		return mutableStringBuffer.toString();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////// GETTERS PUBLICOS ///////////////////////////////////////////////////////////////////////////////////// 
	
 	public static Integer getPasswordSize() {

		return PASSWORD_SIZE;
	}
	
	public static Integer getBlockSize() {

		return BLOCK_SIZE;
	}
	
	public static TiposPasswords getTipoPasswordEnUso() {

		return tipoPasswordEnUso;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
