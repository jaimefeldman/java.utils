package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;

public class MD5Cheksum {
	
	/* */
	private static String createMD5Cheksum(String filename, byte[] byteArray) {
		
		MessageDigest 	md;
		byte[] 			mdbytes;
		StringBuffer 	sb;
		
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(byteArray);
			mdbytes = md.digest();
			//convert the byte to hex format method 1
			sb = new StringBuffer();
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return "";
				
				
		
		
	}
	
	public static String getMD5Cheksum(String filename) {

	
		File file = new File(filename);
		if(file.exists() && !file.isDirectory()) {
		
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				FileInputStream fis = new FileInputStream(filename);
			
			byte[] dataBytes = new byte[(int)file.length()];
			
			 int nread = 0; 
				while ((nread = fis.read(dataBytes)) != -1) {
				  md.update(dataBytes, 0, nread);
				};
				byte[] mdbytes = md.digest();
		
				//convert the byte to hex format method 1
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < mdbytes.length; i++) {
				  sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
				}

				fis.close();
				return  sb.toString();
				
			
			} catch (NoSuchAlgorithmException e) {
				System.err.println("Algoritmo MD5 no encotrado.");
			} catch (FileNotFoundException e) {
				System.err.println("Archivo [" + filename + "] no encotrado.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		} else {	

			System.err.println("El archivo [" + filename + "] no se encutra.");
		}
		
    	return "";
	} 

	public static boolean compareMD5Checksum(String md5checksum, String filename) {
	
		String fileMd5Checksum = getMD5Cheksum(filename);
		
		if(md5checksum.equals(fileMd5Checksum)) 
			return true;
		else 
			return false;
		
	}
	
	public static String getMD5CheksumFromByteArray(byte[] byteArray) {
		return createMD5Cheksum(null, byteArray);
	}
}
