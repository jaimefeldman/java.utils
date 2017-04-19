package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 *  Clase: ArchivoBinario, Guarda y lee Objetos serializados. osea los objetos que use la clase debe implementar
 *  la interface serializable.
 *  by (cl.jimix) 23.06.2016.
 */


public class ArchivoObjeto extends Archivo {

	
	public boolean escribe(Object obj) {
	
		File file = getFile();
		if(file != null) {
			try {
				FileOutputStream 	fos 	= new FileOutputStream(getFile());
				ObjectOutputStream 	oos 	= new ObjectOutputStream(fos);
				
				oos.writeObject(obj);
				oos.close();
				return true;

			} catch (IOException e) {
				
				Log("Error al escribir el objeto binario.");
				Log("Descripcion del error [" + e.getMessage() + "]");
			}
		}
		return false;
	}
	
	public Object leer() {
		
		File file = getFile();
		if(file != null) {
			try {
				FileInputStream		fis		= new FileInputStream(file);
				ObjectInputStream	ois		= new ObjectInputStream(fis);
				
				Object obj = ois.readObject();
				ois.close();
				return obj;
			
			
			} catch (IOException e) {
				Log("Error al escribir el objeto binario.");
				Log("Descripcion del error [" + e.getMessage() + "]");
			} catch (IllegalArgumentException e) {
				Log("Error el archivo no es un archivo Objeto valido.");
				Log("Descripcion [" + e.getMessage() +"]");
			} catch (ClassNotFoundException e) {
				Log("Error Clase no encontrada.");
				Log("Descripcion [" + e.getMessage() +"]");
				
			}
		}
		return null;
	}

}
