package log;

public class Saludador {
	
	Integer edad;
	
	Log log = new ConsoleLog();


	public Saludador() {
		// TODO Auto-generated constructor stub
	}
	
	public void Saluda(String msg) {
		System.out.println(" hola " + msg);
		log.INFO("Informacion de la clase saludador!");
		
	}

}
