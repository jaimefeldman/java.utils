package app;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import console.ConsoleColor;
import fecha.Fecha;
import files.ArchivoObjeto;
import files.DataFile;

public class appUtil {

	public static void main(String[] args) {

	/*	DataFile dataObj = new DataFile();
	
		File file = new File("sombrero.jpg");
		if(file.exists() && !file.isDirectory()) {
			
			dataObj.setNombre(file.getName());
			dataObj.setTipoArchivo("jpg");
			dataObj.setTipoArchivo("imagen");
			dataObj.setFechaCreacion(Fecha.Hoy().getISOFormat());
			dataObj.setFechaModificacion(Fecha.Hoy().getISOFormat());
			dataObj.setFileSize(FileSize.getHumanFormat(file.length()));
			try {
				
				dataObj.setData(Files.readAllBytes(Paths.get(file.getName())));
			} catch (IOException e) {
				System.err.println("no fue posible leer los datos.");
			}
			
			dataObj.setChecksum(MD5Cheksum.getMD5Cheksum(file.getName()));
			
		}else {
			System.err.println("arrchivo no existe.");
		}
		
		
		System.out.println(dataObj.getNombre());
		System.out.println(dataObj.getFileSize());
		System.out.println(dataObj.getChecksum());
		System.out.println(dataObj.getData().toString());
		
		ArchivoObjeto objFile = new ArchivoObjeto();
		objFile.create("file.obj");
		objFile.escribe(dataObj);
		*/


	
		// restaurando el archivo objeto.

		DataFile dataObj = new DataFile();
		
		ArchivoObjeto objFile = new ArchivoObjeto();
		objFile.open("file.obj");
		dataObj = (DataFile)objFile.leer();
		
		System.out.println("=== Obteneindo informacion del archivo ===");
		
		try {
			System.out.println("tipo de archivo : " + Files.probeContentType(Paths.get("file.obj")));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("nombre : " + dataObj.getNombre());
		System.out.println("tamaño : " + dataObj.getFileSize());
		System.out.println("checksum : " + dataObj.getChecksum());
		System.out.println("fecha cracion : " + Fecha.fromISOFormat(dataObj.getFechaCreacion()).getLongPrettyFormat());
		try {
			FileOutputStream fos = new FileOutputStream(new File(dataObj.getNombre()));
			fos.write(dataObj.getData());
			fos.close();
			
			
		} catch (IOException e) {
			System.err.println("no fue posible crar el archivo.");
		}
	
		
		Fecha x = Fecha.Hoy();
		System.out.println(x.getISOFormat());
		
		
		System.out.println("prueba de color gris " + ConsoleColor.GRAY + " COLOR GRIS " + ConsoleColor.RESET);
		
		/*PropertiesFile prop = new PropertiesFile();
		prop.open("users.pwd");
		
		// Creando un mapa con propiedades.
		
		TreeMap<String, String> propertieMap = new TreeMap<>();
		Cifrado cifrado = new Cifrado("SHA-512");
		
		
		propertieMap.put("jaime", cifrado.getCiphier("feldman".toCharArray()));
		propertieMap.put("ricardo", cifrado.getCiphier("allan".toCharArray()));
		
		prop.setPropertiesMap(propertieMap);
		
		propertieMap = prop.getPropertieMap();
		System.out.println("Leiendo el mapa de propiedades.");
		for (Entry<String, String> p : propertieMap.entrySet()) {
			System.out.println(p.getKey() + " : " + p.getValue());
		}
		
		 crando un archivo de texto y escribieno en el 
		FileWriter fw;
		try {
			
			fw = new FileWriter("prueba.txt", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter arcvhivoTexto = new PrintWriter(bw);
			arcvhivoTexto.println("Hola mundo, esto es una prueba");
			arcvhivoTexto.println("Otra linea");
			arcvhivoTexto.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 leiendo 
		try(BufferedReader br = new BufferedReader(new FileReader("prueba.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    System.out.println(everything);
		}catch (IOException e) {
			e.printStackTrace();
	
		}
		
		String texto = "un texot de algo";
		
		String[] str = texto.split("\\s+");
		
		System.out.println("len : " + str[0]);
			
			
			if (DetectOS.isWindows()) {
				System.out.println("This is Windows");
			} else if (DetectOS.isMac()) {
				System.out.println("This is Mac");
			} else if (DetectOS.isUnix()) {
				System.out.println("This is Unix or Linux");
			} else if (DetectOS.isSolaris()) {
				System.out.println("This is Solaris");
			} else {
				System.out.println("Your OS is not support!!");
			}
			
*/		
		System.out.println("Fecha con hora :" + Fecha.Hoy().getTime());
//Fecha.Hoy().getDateTime());
				
		
	}
}
