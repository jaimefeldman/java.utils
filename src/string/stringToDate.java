package string;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Convierte una fecha en formato string ddMMyyyy en una fecha valida Localdate.
// si no es valida devuelve null.

public class stringToDate {
	
	// Devuelve Null si la fecha es invalida.
	public static LocalDateTime convertString(String strFecha) {
		if(checkStingDate(strFecha)) {
			DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("ddMMyyyy");
			return LocalDateTime.parse(strFecha, formatoFecha);
		}else{
			return null;
		}
	}

	private static boolean checkStingDate(String strFecha) {
		
		// Primero Verifica que la fecha tenga la logitud adecuada 6 caracteres. dos para el dia dos para el mes dos para el año.
		if(strFecha.length() == 8) {
			
			// Verificar que los dias meses y años esten dentro de un rango aceptable.
			String dia 	= strFecha.substring(0, 2);
			String mes	= strFecha.substring(2, 4);
			String ano	= strFecha.substring(4, 8);
		
			if((Integer.parseInt(dia) >= 1 && Integer.parseInt(dia) <= 31) && (Integer.parseInt(mes) >=1 && Integer.parseInt(mes) <=12) && (Integer.parseInt(ano) >= 1000 && Integer.parseInt(ano) <= 9900)) {
				return true;
			
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}
}
