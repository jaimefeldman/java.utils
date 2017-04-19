package files;

import java.io.Serializable;

import fecha.Fecha;

public class DataFile implements Serializable {

	private static final long serialVersionUID = 5202801372407049624L;
	
	private String nombre;
	private String fechaCreacion;
	private String fechaModificacion;
	private String tipoArchivo;
	private String checksum;
	private byte[] data;
	private String fileSize;

	
	public DataFile() {
		this.nombre 			= null;
		this.fechaCreacion 		= null;
		this.fechaModificacion 	= null;
		this.tipoArchivo 		= null;
		this.checksum 			= null;
		this.data				= null;
		this.fileSize 			= null;
	}

	public String getNombre() {
		return nombre;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public String getChecksum() {
		return checksum;
	}

	public byte[] getData() {
		return data;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	
	public void setData(byte[] datos) {

		if(this.fechaCreacion == null) {
			this.fechaCreacion = Fecha.Hoy().getLocalDateTime().toString();
			this.fechaModificacion = Fecha.Hoy().getLocalDateTime().toString();
		}else {
			this.fechaModificacion = Fecha.Hoy().getLocalDateTime().toString();
		}

		if(datos.length > 0) {
			this.fileSize = String.valueOf(datos.length);
			this.checksum = MD5Cheksum.getMD5CheksumFromByteArray(datos);
			this.fileSize = FileSize.getHumanFormat((long)datos.length);
			this.data = datos;
		}
	}

	public String getFileSize() {
		return fileSize;
	}

}
