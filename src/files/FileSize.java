package files;

/*
 * Clase FileSize: devuelve al tamaño de una archivo para lectura humana.
 * by (cl.jimix) 20.06.2016
 */


public class FileSize {

	
	public static String getHumanFormat(Long bytes) {

	  	int unit = 1024;
	    if (bytes < unit) return bytes + "b";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre =  "KMGTPE".charAt(exp-1)+"";
	    return String.format("%.1f %sb", bytes / Math.pow(unit, exp), pre);	
	    
	    
	
	}
	


}
