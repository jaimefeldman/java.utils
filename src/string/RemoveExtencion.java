package string;

public class RemoveExtencion {
	
	public static String fromFileName(String strFilename) {
		
		if(strFilename.contains(".")) {
			int pointIndex = strFilename.lastIndexOf('.');
			String newName = strFilename.substring(0, pointIndex);
			return newName;
		}
		return strFilename;
	}
	
}
