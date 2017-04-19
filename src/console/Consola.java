package console;

import java.io.Console;
import java.util.Arrays;

public class Consola {
	
	private static int numeroIntentos = 0;


	public static char[] obtenerPasswordPorConsola(boolean comprueba) {
		
		Console consola = System.console();
		char[]  pass	= null;
		Integer	contadorInentos = 0;
		
		if(consola == null) {
			System.err.println("Error: la consola no es valida.");
			System.exit(1);
		}
		
		String mensaje;
		String confirmMensaje;

		if(DetectOS.isMac()) {
			mensaje = ConsoleColor.BLUE + "Password:" + ConsoleColor.RESET;
			confirmMensaje = ConsoleColor.BLUE + "Confirme password:" + ConsoleColor.RESET;
		}else {	
			mensaje = "Password:";
			confirmMensaje = "Confirme password:";
		}
	
		while(contadorInentos <= numeroIntentos) {
			if(comprueba) {
				pass = consola.readPassword(mensaje);
				char[] confirmacion = consola.readPassword(confirmMensaje);
				if(Arrays.equals(pass, confirmacion)) {
					return pass;
				}else {
					System.out.println(" - Las passwords no coinciden!");
					contadorInentos++;
				}
			}else {
				pass = consola.readPassword(mensaje);
				contadorInentos++;
				return pass;
			}
		}
		return null;
	} 

	public static String[] getCommands(String prompt) {

		Console consola 	= System.console();
		String  comandos[] 	= null;

		if(consola == null) {
			System.err.println("Error: la consola no es valida.");
			System.exit(1);
		}

		comandos = consola.readLine(prompt).split("\\s+");
		return comandos;
		
	}
	
	public static void setNumeroIntentos(int numeroIntentos) {
		Consola.numeroIntentos = numeroIntentos;
	}
}
