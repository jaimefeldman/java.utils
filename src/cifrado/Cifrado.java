package cifrado;

//* Cifrado: Clase Utilitaria para cifrado de contraseñas.
//* by. (cl.jimix) 20.06.2016.
//* modos aceptados.

//*	MD2, MD5, SHA-1, SHA-256, SHA-348, SHA-512


import java.security.MessageDigest;

public class Cifrado {
	
	public enum tipo {SHA224, SHA256, SHA384, SHA512};
	private tipo hashtype;
	private String hashSelected;

	/* Contructor por defecto */
	public Cifrado() {
		
		/* cifrado por defecto sha-256 */
		hashtype = tipo.SHA256;
		SelectHash();
	}

	/* Contructor designado */
	public Cifrado(tipo cifrado) {
		
		hashtype = cifrado;
		SelectHash();
	}
	
	/* Cifrado. */
	public String getCipher(char[] passwd) {
		
		try {
			MessageDigest md = MessageDigest.getInstance(this.hashSelected);
			md.update(new String(passwd).getBytes("UTF-8"));
			byte[] byteDate = md.digest();
			
			/* Convertir los bytes generados a formato hexadecimal. */
			StringBuffer sb = new StringBuffer();
			for (byte b : byteDate) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	private void SelectHash() {
		
		switch (hashtype) {
		case SHA224:
			this.hashSelected = "SHA-224";
			break;
		case SHA256:
			this.hashSelected = "SHA-256";
			break;
		case SHA384:
			this.hashSelected = "SHA-384";
			break;
		case SHA512:
			this.hashSelected = "SHA-512";
			break;
		default:
			this.hashSelected = "SHA-256";
			break;
		}
	}
	
	public void setHashType(tipo cifrado) {
		this.hashtype = cifrado;
		SelectHash();
	}

	public String getHashType() {
		return this.hashSelected;
	}
	
}
