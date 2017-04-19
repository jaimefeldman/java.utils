package files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Archivo {
	
	private File file = null;
	private ArrayList<String> log = new ArrayList<>();

	// Abre un archivo.
	public boolean open(String filename) {

		file = new File(filename);
		if(file.exists() && !file.isDirectory()) {
			return true;
		}else {
			return false;
		}
	}
	
	// Crea un archivo.
	public boolean create(String filename) {
		
		file = new File(filename);
			try {

				file.createNewFile();
				return true;

			} catch (IOException e) {
				log.add("create file Error : no fue posible crear el archivo.");
				log.add("detalles del problema : [" + e.getMessage() + "]");
				return false;
			}
			
	} 

	// Obtiene el file de la variable interna.
	public File getFile() {
		return this.file;
	}
	
	// Obtiene el tamaño.
	public String getSize() {

		if(this.file !=null) {
			return FileSize.getHumanFormat(Long.valueOf(this.file.length()));
		}
		return "";
	}

	// Obtiene el MD5Checksum.
	public String getMD5Checksum() {

		if(this.file != null) {
			return MD5Cheksum.getMD5Cheksum(this.file.getName());
		}
		return "";
	}
	
	// Obtiene el nombre del archivo.
	public String getName() {

		if(this.file !=null) {
			return this.file.getName();
		}
		return "";
	}
	
	// Obitiene el absolute path del archivo.
	public String getPath() {
		
		if(this.file !=null) {
			return this.file.getAbsolutePath();
		}
		return "";
	}
	
	// Agrega mensajes al log.
	public void Log(String messaje) {
		this.log.add(messaje);
	}
	
	// Devuelve el log.
	public ArrayList<String> getLog() {
		return this.log;
	}
	
}
