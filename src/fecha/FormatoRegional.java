package fecha;

import java.util.HashMap;
import java.util.Locale;


/*
 *  FormatoRegional agrupa regiones para dar formato a la fecha.
 *  La clase cumple una funcion de claridad y ordern ya que la zona horaria 
 *  pordira asignarse simplemente usando Locale.("es", "CL")
 */

public class FormatoRegional {

	public static enum zona {CHILE, ARGENTINA, USA, ALEMANIA, FRANCIA, JAPON, CHINA, AUSTRALIA, UK, BRAZIL, CANADA, ITALIA, RUSIA, ISRAEL};
	private static HashMap<zona, String> zonaMap = new HashMap<>();
	
	private static void createZonaMap() {
		zonaMap.put(zona.CHILE,			"es CL");
		zonaMap.put(zona.ARGENTINA,		"es AR");
		zonaMap.put(zona.USA,			"en US");
		zonaMap.put(zona.ALEMANIA,		"de DE");
		zonaMap.put(zona.FRANCIA,		"fr FR");
		zonaMap.put(zona.JAPON,			"ja	JP");
		zonaMap.put(zona.CHINA,			"zh CN");
		zonaMap.put(zona.AUSTRALIA,		"en AU");
		zonaMap.put(zona.UK,			"uk UA");
		zonaMap.put(zona.BRAZIL,		"pt BR");
		zonaMap.put(zona.CANADA,		"en CA");
		zonaMap.put(zona.ITALIA,		"it IT");
		zonaMap.put(zona.RUSIA,			"ru RU");
		zonaMap.put(zona.ISRAEL,		"iw IL");
	}
	
	
	public static Locale getZona(zona zona) {
		createZonaMap();
		String[] zonaPart = zonaMap.get(zona).split("\\s+");
		Locale local = new Locale(zonaPart[0], zonaPart[1]);
		return local;
	}
	
	
}
