package fecha;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class FechaMutable extends Fecha {

	//////////// CONSTRUCTORES /////////////
	public FechaMutable() {

		super();
	}

	public FechaMutable(String fecha) {

		super(fecha);
	}
	
	public FechaMutable(Fecha fecha) {

		super(fecha);
	}
	
	public FechaMutable(FechaMutable fechaMutable) {
		
		initWithFechaMutable(fechaMutable);
	}
	
	public FechaMutable(Timestamp fechaTimeStamp) {
		
		super(fechaTimeStamp);
	}
	
	public FechaMutable(LocalDateTime fechaLocaldate) {
		
		super(fechaLocaldate);
	}
	
	public FechaMutable(Integer diaDelAño) {

		super(diaDelAño);
	}
	
	public FechaMutable(Integer año, Integer diaDelAño) {

		
		super(año, diaDelAño);
	}

	///////////////////////////////////////

	public FechaMutable(Date date) {
		super(date);
	}

	
	
	/////// CONSTRUCTORES ESTATICOS ///////

	public static FechaMutable Hoy() {										//* Crea una fecha de forma estatica.
	
		return new FechaMutable();
	}

	public static FechaMutable Mañana() {									//* Crea uan fecha de forma estatica con la fecha de mañana.
		
		return new FechaMutable(LocalDateTime.now().plusDays(1));
	}
	
	public static FechaMutable PasadoMañana() {								//* crea una fecha de forma estatica con la fecha de pasado mañana.
		
		return new FechaMutable(LocalDateTime.now().plusDays(2));
	}
	
	public static FechaMutable MasUnaSemana() {								//* crea una fecha de forma estatica con la fecha de una semana mas.
		
		return new FechaMutable(LocalDateTime.now().plusWeeks(1));
	}
	
	public static FechaMutable MasUnMes() {									//* crea una fecha de forma estatica con la fecha de un mes mas.
		
		return new FechaMutable(LocalDateTime.now().plusMonths(1));
	}
	
	public static FechaMutable MasUnAño() {									//* crea una fecha de forma estatica con la fecha de un año mas.
		
		return new FechaMutable(LocalDateTime.now().plusYears(1));
	}
	
	public static FechaMutable fromString(String fecha) { 					//* Crea una fecha con un string de forma estatica.
		
		return new FechaMutable(fecha);
	}

	public static FechaMutable fromTimeStamp(Timestamp fechaTimeStamp) {	// * Crea una fecha a partir de un TimeStamp.
		
		return new FechaMutable(fechaTimeStamp);
	}
	
	public static FechaMutable fromFechaMutable(FechaMutable fechaObject) {	//* Crea una fecha a partir de otra Instancia FechaMutable. return new FechaMutable(fechaObject); } public static FechaMutable fromFecha(Fecha fecha) {

		return new FechaMutable(fechaObject);
	}

	public static FechaMutable fromFecha(Fecha fecha) {						//* Crea una fehaMutable a partir de otra Fecha Inmutable.
		
		return new FechaMutable(fecha);
	}
	
	public static FechaMutable fromLocalDate(LocalDateTime localdate) { 		//* Crea una fecha a partir de un localdate.
		
		return new FechaMutable(localdate);
	}
	
	public static FechaMutable fromYearDay(Integer dia) {					//* Crea una fecha a partir de un dia del año.
		
		return new FechaMutable(dia);
	}

	public static FechaMutable fromYearDay(Integer año, Integer dia) {		//* Crea una fecha a partir de un año especifico y un dia de ese año.
		
		return new FechaMutable(año, dia);
	}
	
	public static FechaMutable suma(cantidad cantidad, Integer numero) {	//* crea una fecha a partir de la actual sumandole dias semanas meses o años.
	
		FechaMutable fecha = new FechaMutable();
		fecha.sumar(cantidad, numero);
		return fecha;
	}

	public static FechaMutable resta(cantidad cantidad, Integer numero) { 	//* crea una fecha a partir de la actual dias semanas meses o años.
		
		FechaMutable fecha = new FechaMutable();
		fecha.restar(cantidad, numero);
		return fecha;
	}
	
	///////////////////////////////////////////
	
	
	
	////////////// MUTADORES //////////////
	
	public void sumar(cantidad cantidad, Integer numero) {

		switch (cantidad) {
			case DIAS:
				
				this.fecha = this.fecha.plusDays(numero.longValue());
				break;

			case SEMANAS:

				this.fecha = this.fecha.plusWeeks(numero.longValue());
				break;

			case MESES:

				this.fecha = this.fecha.plusMonths(numero.longValue());
				break;

			case AÑOS:

				this.fecha = this.fecha.plusYears(numero.longValue());
				break;
				
			default:
				this.fecha = null;
			}
	}

	public void restar(cantidad cantidad, Integer numero) {

		switch (cantidad) {
			case DIAS:
				
				this.fecha = this.fecha.minusDays(numero.longValue());
				break;

			case SEMANAS:

				this.fecha = this.fecha.minusWeeks(numero.longValue());
				break;

			case MESES:

				this.fecha = this.fecha.minusMonths(numero.longValue());
				break;

			case AÑOS:

				this.fecha = this.fecha.minusYears(numero.longValue());
				break;
				
			default:
				this.fecha = null;
			}

	}
	
	public void setHoy() {										//* Crea una fecha de forma estatica.
	
		this.fecha = LocalDateTime.now();
	}

	public void setMañana() {									//* Crea uan fecha de forma estatica con la fecha de mañana.
		
		 this.fecha = LocalDateTime.now().plusDays(1);
	}
	
	public void setPasadoMañana() {								//* crea una fecha de forma estatica con la fecha de pasado mañana.
		
		this.fecha = LocalDateTime.now().plusDays(2);
	}
	
	public void setMasUnaSemana() {								//* crea una fecha de forma estatica con la fecha de una semana mas.
		
		this.fecha = LocalDateTime.now().plusWeeks(1);
	}
	
	public void setMasUnMes() {									//* crea una fecha de forma estatica con la fecha de un mes mas.
		
		this.fecha = LocalDateTime.now().plusMonths(1);
	}
	
	public void setMasUnAño() {									//* crea una fecha de forma estatica con la fecha de un año mas.
		
		this.fecha = LocalDateTime.now().plusYears(1);
	}
	
	public void setFromString(String fecha) { 					//* Crea una fecha con un string de forma estatica.
		
		this.fecha = FechaMutable.fromString(fecha).getLocalDateTime();
	}

	public void setFromTimeStamp(Timestamp fechaTimeStamp) {	// * Crea una fecha a partir de un TimeStamp.
		
		this.fecha =  FechaMutable.fromTimeStamp(fechaTimeStamp).getLocalDateTime();
	}
	
	public void setFromFechaMutable(FechaMutable fechaObject) {	//* Crea una fecha a partir de otra Instancia FechaMutable.
		
		this.fecha = FechaMutable.fromFechaMutable(fechaObject).getLocalDateTime();
	}

	public void setFromFecha(Fecha fecha) {

		this.fecha = FechaMutable.fromFecha(fecha).getLocalDateTime();
	}

	public void setFormDate(Date date) {
		setDateType(date);
	}
	
	public void setFromLocalDateTime(LocalDateTime localdatetime) { 		//* Crea una fecha a partir de un localdate.
		
		this.fecha = FechaMutable.fromLocalDate(localdatetime).getLocalDateTime();
	}
	
	public void setFromYearDay(Integer dia) {					//* Crea una fecha a partir de un dia del año.
		
		this.fecha = FechaMutable.fromYearDay(dia).getLocalDateTime();
	}

	public void setFromYearDay(Integer año, Integer dia) {		//* Crea una fecha a partir de un año especifico y un dia de ese año.
	
		this.fecha = FechaMutable.fromYearDay(año, dia).getLocalDateTime();
	}
	
	
	
	////////////////////////////////////
	
	//////////// PRIVADAS ///////////////
	
	private void initWithFechaMutable(FechaMutable fechaMutable) {						//* Inicializador por otro objeto FechaMutable. (crea un nuevo objeto a partir de un objeto fechaMutalbe.
		
		if((fechaMutable.getLocalDateTime()!= null)) {

			setLocalDateTime(fechaMutable.getLocalDateTime());

		}else {

			System.err.println("initWithFecha a fallado, el parametro fecha es NULL.");
		}
	}
	
	////////////////////////////////////
}
