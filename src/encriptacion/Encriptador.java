package encriptacion;

import java.util.ArrayList;

/*
 * Clase: Encripador Encripta un Strings en AES/ECB/PKCS5Padding de forma simetrica.
 * by (cl.jimix) 20.06.2016
 */

import javax.crypto.Cipher;
import java.security.MessageDigest;
import javax.crypto.BadPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


public class Encriptador {

	private String tipoCifrado;
	private ArrayList<String> log = new ArrayList<>();

	public ArrayList<String> getLogArray() {
		return this.log;
	}
	
	public Encriptador() {
		this.tipoCifrado = "SHA";
	}
	
	public Encriptador(String tipoCifrado) {
		this.tipoCifrado = tipoCifrado;
	}
	
	public byte[] enctyptarEnBytes(char[] Key, byte[] bytes) {
		
		if(Key!= null) {
		
			try {

			final Cipher aes 	= obtieneCipher(Key, true);
			final byte[] cifrado = aes.doFinal(bytes);
			return cifrado;
				
			} catch (Exception e) {
				log.add("ERROR: " + e.getMessage());
				return null;
			}
		
		} else {
			log.add("se debe especificar una llave, use setPassword()");
			return null;
	
		}

	}

	public byte[] encriptarEnBytes(char[] Key, String sinCifrar) {

		if(Key!= null) {
			
			byte[] bytes;
			try {

				bytes = sinCifrar.getBytes("UTF-8");
				final Cipher aes 		= obtieneCipher(Key, true);
				final byte[] cifrado	= aes.doFinal(bytes);
				return cifrado;
			
			} catch (Exception e ) {
				log.add("ERROR : " + e.getMessage());
			}
		}

		log.add("se debe especificar una llave, use setPassword()");
		return null;
	}
	
	public String encriptarEnHex(char[] Key, String sinCifrar) {
		
		return DatatypeConverter.printHexBinary(this.encriptarEnBytes(Key, sinCifrar));
	}
	
	public String desencripta(char[] Key, byte[] cifrado) {
		
		if(Key!= null) {
			try {
				final Cipher aes = obtieneCipher(Key, false);
				final byte[] bytes = aes.doFinal(cifrado);
				final String sinCifrar = new String(bytes, "UTF-8");
				return sinCifrar;

			} catch (BadPaddingException e) {

				log.add("ERROR: imposible desencriptar, contraseña incorrecta");
				return null;
			} catch (Exception e) {
				
				log.add("ERROR: " + e.getMessage());
				return null;
			}
					}
		log.add("se debe especificar una llave, use setPassword()");
		return null;
	}
	
	public String desencripta(char[] Key, String cifrado) {
		
		try {
			
			return this.desencripta(Key, DatatypeConverter.parseHexBinary(cifrado));

		} catch (IllegalArgumentException e) {
			log.add("ERROR: Intentando desencriptar un texto no encriptado.");
			return null;
		}
		
	}

	public byte[] desencriptaToBytes(char[] Key, byte[] cifrado) {

		if(Key!= null && Key.length > 0) {

			try {

				final Cipher aes = obtieneCipher(Key, false);
				try {

					final byte[] bytes = aes.doFinal(cifrado);
					return bytes;
				
				} catch (BadPaddingException e) {
					log.add("Password Incorrecta, imposible desifrar el archivo.");
					return null;

				} catch (IllegalArgumentException e) {
					log.add("ERROR: Intentando desencriptar un texto no encriptado.");
					return null;
				}

			} catch (Exception e) {
				log.add("ERROR desconocido: " + e.getMessage());
				return null;
			}
		
		}else {
			log.add("Error: password no valida.!");
			return null;
		}
	}

	private Cipher obtieneCipher(char[] Key, boolean paraCifrar) throws Exception {

		final String frase = String.valueOf(Key);
		
		final MessageDigest digest = MessageDigest.getInstance(this.tipoCifrado);
			digest.update(frase.getBytes("UTF-8"));
		final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
		final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
		if (paraCifrar) {
				aes.init(Cipher.ENCRYPT_MODE, key);
			} else {
				aes.init(Cipher.DECRYPT_MODE, key);
			}
		
		return aes;
	}
	
	
}
