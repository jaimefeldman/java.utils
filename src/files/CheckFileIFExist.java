package files;

import java.io.File;
import string.CheckString;

public class CheckFileIFExist {

	public static File getNewOne(File file) {
	
		// Si exisistiera el archivo modifiquelo.
		if(file.exists() && !file.isDirectory()) {
			String	filename 	= file.getName();
			int		endIndex 	= filename.indexOf(".");
			int 	startIndex  = 0;
			if(filename.contains("-")) {
				startIndex = filename.indexOf("-");
				String numeroEncontrado = filename.substring(startIndex+1, endIndex);

				if(CheckString.isNumeric(numeroEncontrado)) {
					int nuevoNumero = Integer.valueOf(numeroEncontrado) + 1;
					String newFileName = filename.replace(numeroEncontrado, String.valueOf(nuevoNumero));
					return new File(newFileName);
				}else {

					startIndex = filename.lastIndexOf('-');
					endIndex   = filename.lastIndexOf('.');
					
					numeroEncontrado = filename.substring(startIndex+1, endIndex);
					if(CheckString.isNumeric(numeroEncontrado)) {
						int nuevoNumero = Integer.parseInt(numeroEncontrado) +1;
						String newFileName = filename.replace(numeroEncontrado, String.valueOf(nuevoNumero));
						return new File(newFileName);
					}else {
						String newFileName = filename.replace(".", "-1.");
						return new File(newFileName);
					}
					
				}
			}else {
				String newFileName = filename.replace(".", "-1.");
				return new File(newFileName);
			}
			
		}
		return file;
	}
	
	
}
