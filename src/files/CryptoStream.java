package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import encriptacion.Encriptador;
import files.FileSize;

/*
 * Lee un arvhivo y devuelve un stream encriptado del mismo.
 */


public class CryptoStream {

	private static ArrayList<String> log = new ArrayList<>();

	
	/* Enriptador de streams designado */
	public static byte[] EncryptStreams(char[] key, File file, byte[] byteArray)  {
		Encriptador encriptador 	= new Encriptador();
		byte[] inputBytes;		

		try {
			// Obteniendo el los datos del archivo.
			if(file == null) {
				inputBytes = byteArray;
				log.add("Encriptando directamente desde un byte array, tamaño: " + FileSize.getHumanFormat((long)inputBytes.length));
			}else {
				FileInputStream	inputStream = new FileInputStream(file);
				inputBytes = new byte[(int) file.length()];
				inputStream.read(inputBytes);
				inputStream.close();
				log.add("tamaño de [" + file.getName() +"] " + FileSize.getHumanFormat(file.length()));
			}

			
			byte[] outputBytes = encriptador.enctyptarEnBytes(key, inputBytes);
			return outputBytes;
			
		}catch(IOException e) {
			log.add("Error en stream encriptador");
			log.add("motivo del error : [" + e.getMessage() + "]");
		}
		return null;
	}
	
	public static byte[] getCryptoStreamFromFile(char[] key, File file) {
		return EncryptStreams(key, file, null);
	}
	
	public static byte[] getCryptoStreamFromByteArray(char[] key, byte[] byteArray) {
		return EncryptStreams(key, null, byteArray);
	}
	
	public static byte[] getUnencryptedByteArrayFromEncryptedStream(char[] key, byte[] bytesArray) throws NullPointerException {

		Encriptador encriptador 	= new Encriptador();
		byte[] outputBytes = encriptador.desencriptaToBytes(key, bytesArray);
		return outputBytes;
	}

}
