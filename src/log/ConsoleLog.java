package log;

public class ConsoleLog extends Log {

	@Override
	void print(String message) {
		// Sacando los mensajes por consola.
		System.out.println(message);
		
	}

}
