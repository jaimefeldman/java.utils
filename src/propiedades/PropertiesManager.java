/**
 * 
 */
package propiedades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map.Entry;


import java.util.Properties;
import java.util.TreeMap;

import encriptacion.Encriptador;
import files.ArchivoObjeto;
import files.CryptoStream;
import files.DataFile;
import propiedades.CompareMaps;

/**
 * @author "Jaime A. Feldman B."
 * 
 */
public class PropertiesManager {
	
	private TreeMap<String, String> propertiesMap;
	private TreeMap<String, String> originalPropertiesMap;
	private ArrayList<String>		log;
	private String					extension;
	private boolean					useExtension;
	private boolean					enableComparation;
	
	private char[] 					fenc;
	private char[] 					denc;
	private String 					strFilename;
	public enum operationType 		{Encrypt, Dencrypt};


	/* Inicializador designado */
	private void loadProperties(TreeMap<String, String> propertiesMap) {

		
		if(propertiesMap != null) {
			this.propertiesMap 		   =  new TreeMap<>(propertiesMap);
			this.originalPropertiesMap =  new TreeMap<>(propertiesMap);
		} else {
			this.propertiesMap = new TreeMap<>();
			this.originalPropertiesMap = null;
		}
		
		this.fenc = null;
		this.denc = null;
		this.extension 		= "properties";
		this.log	   		= new ArrayList<>();
		this.useExtension 	= true;
		this.loadPropertiesConfigFile("properties.conf");
		this.enableComparation	= true;
	}
	
	public PropertiesManager() {
		loadProperties(null);
	}
	
	public PropertiesManager(TreeMap<String, String> propertiesMap) {
		loadProperties(propertiesMap);
	}

	public ArrayList<String> getLog() {
		return this.log;
	}

	public char[] getFenc() {
		return this.fenc;
	}

	public char[] getDenc() {
		return this.denc;
	}
	
	public void setFenc(char[] fenc) {
		this.fenc = fenc;
	}

	public void setDenc(char[] denc) {
		this.denc = denc;
		
	}
	
	/* Devuelve el mapa de propiedades */
	public TreeMap<String, String> getMap() {
		return propertiesMap;
	}
	
	/* Asigna un nuevo mapa al objeto */
	public void setMap(TreeMap<String, String> treeMap) {

		this.propertiesMap.clear();
		this.propertiesMap = treeMap;
	}

	/* Intentar cargar el archivo de configuracion */	
	private void loadPropertiesConfigFile(String strPropertiesConfigname) {
		
		if(strPropertiesConfigname != null) {
			if(strPropertiesConfigname.length() > 0) {
				File file = new File(strPropertiesConfigname);
				if(file.exists() && !file.isDirectory()) {
						Properties jprop = new Properties();
						TreeMap<String, String> configMap = new TreeMap<>();
						try {
							BufferedReader inputFile = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
							jprop.load(inputFile);
							Enumeration<?> enumeration = jprop.propertyNames();
							while(enumeration.hasMoreElements()) {
								String key = (String) enumeration.nextElement();
								configMap.put(key, jprop.getProperty(key));
							}
							
							if(configMap.containsKey("ext")) 
								this.extension = configMap.get("ext");
							else
								this.extension = "properties";
							
							if(configMap.containsKey("useExt"))
								this.useExtension = Boolean.valueOf(configMap.get("useExt"));
							else
								this.useExtension = true;
							
							inputFile.close();
						} catch (FileNotFoundException e) {
							
						} catch (UnsupportedEncodingException e) {
						
						} catch (IOException e) {
							// TODO: handle exception
						}
				}else {
					
					TreeMap<String, String> configMap = new TreeMap<>();
					configMap.put("ext", "properties");
					configMap.put("useExt", "true");
					Properties jprop = new Properties();
					try {
						BufferedWriter outputFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF8"));
						
						for (Entry<String, String> entry : configMap.entrySet()) {
							jprop.setProperty(entry.getKey(), entry.getValue());
						}
						
						jprop.store(outputFile, null);
						outputFile.close();
						try {
							outputFile.close();
						} catch (IOException e) {
							e.printStackTrace();
						}

					} catch (FileNotFoundException e) {
						e.printStackTrace();

					} catch (IOException e) {
					}
				}
				
			}	
		}
	}

	/* Encrypta y desencripta los datos de las propiedades */
	public boolean dataEncriptation(operationType operation) {

		
		if(!this.propertiesMap.isEmpty() && this.denc != null) {
			
			TreeMap<String, String> newPropertiesMap = new TreeMap<>();
			Encriptador encriptador = new Encriptador(); 
			
			for (Entry<String, String> entry : this.propertiesMap.entrySet()) {
				String data;
				if(operation == operationType.Dencrypt) 
					data = encriptador.desencripta(this.denc, entry.getValue());
				else
					data = encriptador.encriptarEnHex(this.denc, entry.getValue());
					
				if(data !=null) 
					newPropertiesMap.put(entry.getKey(), data);
				else
					return false;
			}
			setMap(newPropertiesMap);
			return true;
		}
		return false;
	}

	/* Elimina todos los elementos de la lista */
	public boolean removeProperties(String llave) {
		if(this.propertiesMap.containsKey(llave)) {
			if(this.propertiesMap.remove(llave, this.propertiesMap.get(llave)))
				return true;
		}
		return false;
	}

	/* Elimina todos los elementos de la lista */
	public void clearList() {

		if(!this.propertiesMap.isEmpty())
			this.propertiesMap.clear();
	}

	/* Agrega datos a las propiedades (llave, valor) */
	public boolean addProperties(String llave, String valor) {
		
		if(llave.length() > 0 && valor.length() > 0) {
			llave = llave.trim();
			this.propertiesMap.put(llave, valor);
			log.add("Elementos agregados a la lista: [" + llave + "] valor: [" + valor +"]");
			return true;
		}else {
			return false;
		}
	}
	
	/* Limpia las variables de password cuando la clase finaliza */
	public void finalize() throws Throwable {
		Arrays.fill(this.fenc, '\0');
		Arrays.fill(this.denc, '\0');
		this.fenc = null;
		this.denc = null;
		super.finalize();
	}

	/* carga los datos dese un array de bytes */
	public boolean loadFromStream(byte[] byteArray) {
			try {

				Properties     prop 		  = new Properties();
				BufferedReader inputFile  	  = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArray)));

				prop.load(inputFile);
				Enumeration<?> e = prop.propertyNames();

				while(e.hasMoreElements()) {

					String key = (String) e.nextElement();
						
					this.propertiesMap.put(key, prop.getProperty(key));
					
				}
				this.originalPropertiesMap = new TreeMap<>(this.propertiesMap);
				return true; 
			}catch(IOException e) {
				log.add("Error loadFromFile.");
				log.add("Motivo : [" + e.getMessage() + "]");
			}catch(IllegalArgumentException e) {
				log.add("Error loadFromFile: el archivo no corresponde a un archivo de properties.");
				log.add("Motivo: [" + e.getMessage() +"]");
			}
		
		return false;
	}

	/* Carga una lista de propiedades desde un archivo */
	public boolean loadFromFile(String filename) {
		
		File file = new File(filename);
		if(file.exists() && !file.isDirectory()) {
			try {
				Properties     prop 		  = new Properties();
				BufferedReader inputFile  	  = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));

				prop.load(inputFile);
				Enumeration<?> e = prop.propertyNames();

				while(e.hasMoreElements()) {

					String key = (String) e.nextElement();

						this.propertiesMap.put(key, prop.getProperty(key));
				}

				this.originalPropertiesMap = new TreeMap<>(this.propertiesMap);
				this.strFilename = filename;
				detectExtension(filename);
				return true; 

			}catch(IOException e) {
				log.add("Error loadFromFile.");
				log.add("Motivo : [" + e.getMessage() + "]");
			}catch(IllegalArgumentException e) {
				log.add("Error loadFromFile: el archivo no corresponde a un archivo de properties.");
				log.add("Motivo: [" + e.getMessage() +"]");
			}
		}
		return false;
	}

	/* Devuelve un buffer en bytes con la informacion de propiedades */
	public byte[] getByteBuffer(TreeMap<String, String> propertiesMap) {
		
		//BufferedWriter outputBuffer;
		//outputBuffer = new BufferedWriter(new OutputStreamWriter(new ByteArrayOutputStream()));
		ByteArrayOutputStream bitBuffer = new ByteArrayOutputStream(1024);
		Properties	prop = new Properties();
		
		for (Entry<String, String> mapEntry : propertiesMap.entrySet()) {

				prop.setProperty(mapEntry.getKey(), mapEntry.getValue());
		}

		try {
			
			prop.store(bitBuffer, null);
			byte[] b = new byte[1024];
			b = bitBuffer.toByteArray();
			bitBuffer.reset();
			//return bitBuffer;
			return b;
			
		} catch (IOException e) {
			log.add("Problema para obtener el buffer de datos : getBuffer Failure!");
		}
	return null;	
		
	}	

	/* Guarda el mapa en un archivo de propiedades  (debe ser capaz de guardar en plano o encriptado) */
	/* Dependinedo si esta settiada la variable fenc, si fenc tienen algun valor lo encriptara con ese valor */
	public boolean saveToFile(String filename) {
		
		if(filename == null) {
			if(this.getFileName() == null) {
				filename = "noNameFile";
			}else {
				filename = this.getFileName();
			}
		}
		
		if(this.propertiesMap.isEmpty()) {
			log.add("Nada que Guardar la lista esta vacia.");
			return false;
		}
	
		if(this.enableComparation) {
			if(this.originalPropertiesMap !=null) {
				if(CompareMaps.sonIguales(this.propertiesMap, this.originalPropertiesMap)) {
					log.add("Nada que guardar no se a modificado la lista.");
					return false;
				} 

			}
		}
		

		if(this.fenc !=null) {
			
			ArchivoObjeto objFile 	= new ArchivoObjeto();
			DataFile	  dataFile 	= new DataFile();
			
			dataFile.setNombre(filename);
			dataFile.setTipoArchivo("encrypted");
			dataFile.setData(CryptoStream.getCryptoStreamFromByteArray(this.fenc, getByteBuffer(this.propertiesMap)));
			
			objFile.create(filename);
			objFile.escribe(dataFile);
			
			this.enableComparation = true;
			return true;
			
		}else {	

			BufferedWriter outputFile;

			try {

				if(this.useExtension) 
					outputFile 	= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filename+"."+this.extension)),"UTF8"));
				else
					outputFile 	= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filename)),"UTF8"));


				Properties	prop = new Properties();
				for (Entry<String, String> mapEntry : propertiesMap.entrySet()) {

						prop.setProperty(mapEntry.getKey(), mapEntry.getValue());
				}
				
				prop.store(outputFile, null);
				this.enableComparation = true;

				return true;

				} catch (IOException e) {
					log.add("Error saveToFile: No fue posible guardar el archivo");
					log.add("Motivo: ["+ e.getMessage() +"]");
				}
			
			}
			return false;
	}
	
	/* Borra la lista de password */
	public void clearPasswords() {

		Arrays.fill(this.fenc, '\0');
		Arrays.fill(this.denc, '\0');
		this.denc = null;
		this.fenc = null;
	}

	/* Obtiene el numeor de elementos de la lista */
	public Integer getSize() {
		return this.propertiesMap.size();
	}

	/* Devuelve el nombre del archivo */
	public String getFileName() {
		
		return this.strFilename;
	}

	/* Establece el nombre del archivo */
	public void setFileName(String strFilename)  {
		this.strFilename = strFilename;
		detectExtension(strFilename);
	}
	
	/* Establece el uso de la extencion */
	public void useExtension(boolean use) {

		this.useExtension = use;
	}

	/* Analiza la extencion del nombre del archivo */
	private void detectExtension(String filename) {
		
		String extencion = "";
		
		if(filename.contains(".")) {
			int beginIndex = filename.indexOf('.');
			extencion = filename.substring(beginIndex+1);
			filename = filename.substring(0, beginIndex);
			if(extencion.contains(".")) {
				extencion = extencion.substring(0, extencion.indexOf('.'));
			}
			this.extension = extencion;

		}else {
			useExtension(false);
			this.extension = "";
		}
	}

	public String getExtencion() {
		return this.extension;
	}
	
	public boolean isUsedExtencion() {
		return this.useExtension;
	}

	/* Crea un archivo de llaves plano o encriptado. */
	public void createKeyFile(char[] passwd) {

		
		
		TreeMap<String, String> keyMap = new TreeMap<>();

		if(this.fenc != null) 
			keyMap.put("fenc", new String(this.fenc));
		
		if(this.denc != null) 
			keyMap.put("denc", new String(this.denc));
		
		if(keyMap.size() > 0) {

			if(passwd != null) {
			
				ArchivoObjeto objFile 	= new ArchivoObjeto();
				DataFile	  dataFile 	= new DataFile();
				
				dataFile.setNombre("keyfile-"+this.strFilename);
				dataFile.setTipoArchivo("encrypted");
				dataFile.setData(CryptoStream.getCryptoStreamFromByteArray(passwd, getByteBuffer(keyMap)));
				
				objFile.create(strFilename+".pkey");
				objFile.escribe(dataFile);
			
			} else{
				
				Properties jprop = new Properties();
				try {
					BufferedWriter outputFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.strFilename+"-keyfile.txt"),"UTF8"));
					
					for (Entry<String, String> entry : keyMap.entrySet()) {
						jprop.setProperty(entry.getKey(), entry.getValue());
					}
					
					jprop.store(outputFile, null);
					outputFile.close();
					try {
						outputFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();

				} catch (IOException e) {
				}
				
				
			}
		}
	}

	public TreeMap<String, String> getOriginalMap() {

		return this.originalPropertiesMap;
	}
	
	public void setEnableComparation(boolean enable) {
		this.enableComparation = enable;
	}

}
