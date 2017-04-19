package log;

public class FileLog extends Log {
	
	log.File file;
	
	public FileLog(String fileName) {
		file = new File(fileName);
	}
	

	@Override
	void print(String mensaje) {
		file.writeln(mensaje);
	}
	
	
}
