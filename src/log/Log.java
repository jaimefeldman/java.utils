package log;

import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

public abstract class Log {
	
	
	public void INFO(String message) {
		String 	infoMsg 		= "[ INFO ] : " + message;
		String 	nombreMetodo 	= 	Thread.currentThread().getStackTrace()[2].getMethodName();
		String 	nombreClase 	=	Thread.currentThread().getStackTrace()[2].getClassName();
		Integer	numeroLinea 	=	Thread.currentThread().getStackTrace()[2].getLineNumber();
		String  info = String.format("[%s -> %s : %s]", nombreClase,nombreMetodo,numeroLinea.toString());
		print(getFechaHora() + " " + info +"\n" + infoMsg + "\n");
	}
	
	public void DEBUG(String message) {
		String 	infoMsg 		= "[ DEBUG ] : " + message;
		String 	nombreMetodo 	= 	Thread.currentThread().getStackTrace()[2].getMethodName();
		String 	nombreClase 	=	Thread.currentThread().getStackTrace()[2].getClassName();
		Integer	numeroLinea 	=	Thread.currentThread().getStackTrace()[2].getLineNumber();
		String  info = String.format("[%s -> %s : %s]", nombreClase,nombreMetodo,numeroLinea.toString());
		print(getFechaHora() + " " + info +"\n" + infoMsg + "\n");

	}

	public void WARNING(String message) {
		String 	infoMsg 		= "[ WARNING ] : " + message;
		String 	nombreMetodo 	= 	Thread.currentThread().getStackTrace()[2].getMethodName();
		String 	nombreClase 	=	Thread.currentThread().getStackTrace()[2].getClassName();
		Integer	numeroLinea 	=	Thread.currentThread().getStackTrace()[2].getLineNumber();
		String  info = String.format("[%s -> %s : %s]", nombreClase,nombreMetodo,numeroLinea.toString());
		print(getFechaHora() + " " + info +"\n" + infoMsg + "\n");

	}
	
	public void ERROR(String message) {
		String 	infoMsg 		= "[ ERROR ] : " + message;
		String 	nombreMetodo 	= 	Thread.currentThread().getStackTrace()[2].getMethodName();
		String 	nombreClase 	=	Thread.currentThread().getStackTrace()[2].getClassName();
		Integer	numeroLinea 	=	Thread.currentThread().getStackTrace()[2].getLineNumber();
		String  info = String.format("[%s -> %s : %s]", nombreClase,nombreMetodo,numeroLinea.toString());
		print(getFechaHora() + " " + info +"\n" + infoMsg + "\n");

	}
	
	public void FATAL(String message) {
		String 	infoMsg 		= "[ FATAL ] : " + message;
		String 	nombreMetodo 	= 	Thread.currentThread().getStackTrace()[2].getMethodName();
		String 	nombreClase 	=	Thread.currentThread().getStackTrace()[2].getClassName();
		Integer	numeroLinea 	=	Thread.currentThread().getStackTrace()[2].getLineNumber();
		String  info = String.format("[%s -> %s : %s]", nombreClase,nombreMetodo,numeroLinea.toString());
		print(getFechaHora() + " " + info +"\n" + infoMsg + "\n");

	}
	
	private String getFechaHora() {
		Locale 		local = new Locale("es","CL");
		LocalDate 	fecha = LocalDate.now();
		LocalTime 	time  = LocalTime.now();
		
		String nombreDiaSemana = DayOfWeek.from(fecha).getDisplayName(TextStyle.FULL, local);
		String nombreMes	   = Month.from(fecha).getDisplayName(TextStyle.FULL, local);
		
		String hora 	   = String.format("%s:%s:%s", time.getHour(), time.getMinute(), time.getSecond());
		String fechaString = String.format("%s %s de %s del %s",  nombreDiaSemana, fecha.getDayOfMonth(), nombreMes, fecha.getYear());
		
		String strFecha		= fechaString + " a las " + hora + " hrs.";
		return strFecha;
	}
	
	abstract void print(String mensaje);
		
}
