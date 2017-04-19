package files;

/*
 *  Clase: CryptoFile encripta y desecnripta Archivos completos.
 *  by. (cl.jimix) 20.06.2016.
 *  
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import cronometro.Cronometro;
import encriptacion.Encriptador;
import files.FileSize;

public class CryptoFile {

	private static ArrayList<String> log = new ArrayList<>();

	public static void enctipta(char[] key, String originalFile) {
		
		File archivoOriginal = new File(originalFile);
		Encriptador encriptador = new Encriptador();
		Cronometro crono = new Cronometro();
		
		try {
			// Obteniendo el los datos del archivo.
			FileInputStream		inputStream = new FileInputStream(archivoOriginal);
			byte[] inputBytes = new byte[(int) archivoOriginal.length()];

			log.add("tamaño de [" + archivoOriginal.getName() +"] " + FileSize.getHumanFormat(archivoOriginal.length()));

			inputStream.read(inputBytes);
			inputStream.close();
			
			byte[] outputBytes = encriptador.enctyptarEnBytes(key, inputBytes);

			if(outputBytes.length > 0) {
				
				crono.start();
				log.add("tamaño del archivo encriptado ["+ archivoOriginal.getName() +"] " + FileSize.getHumanFormat((long)outputBytes.length));
				// Una vez leido el contenido del archivo con exito, se eleimina el arvhivo.
				// y se deja un menaje en log.

				FileOutputStream outputStream = new FileOutputStream(archivoOriginal);
				outputStream.write('\0');
				archivoOriginal.delete();
				outputStream.close();
				
				outputStream  = new FileOutputStream(originalFile);
				outputStream.write(outputBytes);
				outputStream.close();

				crono.stop();
				log.add("Encryptacion del archivo [" + archivoOriginal.getName() + "] exitosa.");
				log.add("Tiempo de encriptado: " + crono.getTime());

			}else {
				log.add("No fue posible encriptar el archivo [" + archivoOriginal.getName() + "]. El encripatador no devolvio nada, el archivo no se a modificado.");
			}

		} catch (FileNotFoundException e) {
			log.add("El archivo [" + archivoOriginal.getPath() + "] no se encutra.");
		} catch (Exception e) {
			log.add("ERROR: " + e.getMessage());
		}
	
	}
	
	public static void desencripta(char[] key, String archivoCifrado) {
	
		File	cifrado = new File(archivoCifrado);
		//File	decifrado = new File(archivoCifrado);
		Encriptador encripatador = new Encriptador();
		Cronometro crono = new  Cronometro();
		
		try {
			// Obteniendo el los datos del archivo.
			FileInputStream		inputStream = new FileInputStream(cifrado);
			byte[] inputBytes = new byte[(int) cifrado.length()];

			log.add("tamaño de [" + archivoCifrado +"] " + FileSize.getHumanFormat(cifrado.length()));


			inputStream.read(inputBytes);
			inputStream.close();
			

			byte[] outputBytes = encripatador.desencriptaToBytes(key, inputBytes);

			if(outputBytes.length > 0)  {

				crono.start();

				log.add("tamaño del archivo desencriptado ["+ cifrado.getName() +"] " + FileSize.getHumanFormat((long)outputBytes.length));
				cifrado.delete();
			
				FileOutputStream	outputStream  = new FileOutputStream(cifrado);
				outputStream.write(outputBytes);
				outputStream.close();

				crono.stop();
				log.add("Decifrado del archivo [" + cifrado.getName() + "] exitosa.");
				log.add("Tiempo de desencriptado: " + crono.getTime());
				
			}else {
				log.add("Error : No se ha desencriptado el archivo [" + cifrado.getName() +"]");
			}

		} catch (FileNotFoundException e) {
			log.add("El archivo [" + cifrado.getPath() + "] no se encutra.");
		} catch (Exception e) {
			log.add("ERROR: " + e.getMessage());
		}
		
		
	}
	
	public static ArrayList<String> getLogArray() {
		return log;
	}


}