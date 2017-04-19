package log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class File {
	
	private java.io.File 	file;
	private FileWriter 		fileWriter;
	private BufferedWriter	bufferWriter;
	
	public File(String path) {
		file = new java.io.File(path);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			fileWriter = new FileWriter(file.getName(), true);
			bufferWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufferWriter = new BufferedWriter(fileWriter);
	
	}
	
	public void write(String msg) {
		try {
			bufferWriter.write(msg);
			bufferWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeln(String msg) {
		try {
			bufferWriter.write(msg+"\n");
			bufferWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			bufferWriter.flush();
			bufferWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
