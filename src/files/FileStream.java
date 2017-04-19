package files;

/*
 * FileStream: Lee un arvhivo y lo almacena termporalmente en memoria hasta que se le solicita que lo escriba en disco.
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import fecha.Fecha;

public class FileStream {

	private File inputFile;
	private File outputFile;
	private FileInputStream fis;
	private ByteArrayOutputStream baos;
	private byte[] buffer;
	
	public FileStream(String inputFileName, String outputFileName) {

		this.inputFile 	= new File(inputFileName);
		this.outputFile	= new File(outputFileName);
		this.buffer 	= null;

	}

	public FileStream() {

		this.inputFile 	= null;
		this.outputFile	= null;
		this.buffer		= null;
	}

	public File getInputFile() {
		return inputFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
	
	public byte[] getFile(String filename) {
		
		this.inputFile = new File(filename);
		if(this.inputFile.exists() && !this.inputFile.isDirectory()) {
			try {
				this.fis  = new FileInputStream(this.inputFile);
				this.baos = new ByteArrayOutputStream();
				int nRead =0;
				this.buffer = new byte[(int)this.inputFile.length()];
				while((nRead = this.fis.read(buffer, 0, buffer.length)) != -1) {
					this.baos.write(buffer, 0, nRead);
					this.baos.flush();
					
					return baos.toByteArray();
				}
				
			} catch (IOException e) {
				System.err.println("Error: con la operacion de lectura y almacenaje del arvhivo.");
			}
		}
		
		return null;
	}
	
	public byte[] getBuffer() {

		if(this.buffer == null) {

			return new byte[0];
		} else {
			
			return this.buffer;
		}
	}
	
	public boolean saveToDisk() {
		
		String newFilename, filename = this.inputFile.getName();
		String extencion 	= "";
		
		int pointIndex = filename.indexOf('.');
		if(pointIndex > 0) {

			extencion 	= filename.substring(pointIndex);
			newFilename = filename.substring(0, pointIndex);
			
		}else {

			extencion 	= "";
			newFilename = filename;
		}		
		
		String backupFileName  = newFilename + "-" + Fecha.Hoy().getISOFormat().toString()+extencion;
		
		try {
			FileOutputStream fos = new FileOutputStream(backupFileName);
			fos.write(this.buffer);
			fos.flush();
			fos.close();
			return true;

		} catch (IOException e) {

			System.err.println("No fue posible escribir el arvhivo de respaldo en el disco.");
			return false;
		}
		
	}
}
