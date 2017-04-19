package fecha;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import fecha.FormatoRegional.zona;
import string.StringFormat;
import string.stringToDate;



/*
 *  Fecha Inmutalbe (crea una instancia inmutable de una fecha *
 *  Proporciona manejo mas eficiente y claro de las fechas.
 */


public class Fecha {

	private   Locale 			local;
	protected LocalDateTime 	fecha;
	public enum 		cantidad {DIAS, SEMANAS, MESES, AÑOS};
	

	//////// CONSTRUCTORES INSTANCIA ////////
	
	public Fecha() {  												//* Inicializador por defecto pone la fecha de hoy.
		
		setLocal();
		this.fecha = LocalDateTime.now();
	}
	
	public Fecha(String strFecha) {									//* Constructor por String.
		
		setLocal();
		//Inicializador con año.
		if(strFecha.length() == 8) {
			
			initWithString(strFecha);
			
			//Inicializador con mesDia
		} else if(strFecha.length() == 4){
			
			initWithDiaMes(strFecha);
		}
		
	}
	
	public Fecha(Fecha fecha) {										//* Constructor por Fecha.
		
		setLocal();
		initWithFecha(fecha);
	}
	
	public Fecha(LocalDateTime localdate) {								//* Inicializador por un Localdate. (crea el objeto fecha a partir de un local date.
		
		setLocal();
		this.fecha = localdate;
	}
	
	public Fecha(Timestamp fechaTimeStamp) {						//* Inicializador por un Timestamp (crea unn objeto fecha a partir de un timeStamp).
		
		setLocal();
		this.fecha = fechaTimeStamp.toLocalDateTime();
	}
	
	public Fecha(Integer numeroDiaDelAño) {							//* Incializador por un numero de dia del año actual.
		
		setLocal();
		initWithDayOfYear(LocalDate.now().getYear(), numeroDiaDelAño);
	}
	
	public Fecha(Integer año, Integer numeroDiaDelAño) {			//* Inicializador por un numero de dia y un año especifico.
		
		setLocal();
		initWithDayOfYear(año, numeroDiaDelAño);
	}

	// Constructor con un formato date()
	public Fecha(Date date) {
		setLocal();
		setDateType(date);
	}

	////////////////////////////////////////
	
	/////// CONSTRUCTORES ESTATICOS ///////

	public static Fecha Hoy() {										//* Crea una fecha de forma estatica.
	
		return new Fecha();
	}

	public static Fecha Mañana() {									//* Crea uan fecha de forma estatica con la fecha de mañana.
		
		return new Fecha(LocalDateTime.now().plusDays(1));
	}
	
	public static Fecha PasadoMañana() {							//* crea una fecha de forma estatica con la fecha de pasado mañana.
		
		return new Fecha(LocalDateTime.now().plusDays(2));
	}
	
	public static Fecha MasUnaSemana() {							//* crea una fecha de forma estatica con la fecha de una semana mas.
		
		return new Fecha(LocalDateTime.now().plusWeeks(1));
	}
	
	public static Fecha MasUnMes() {								//* crea una fecha de forma estatica con la fecha de un mes mas.
		
		return new Fecha(LocalDateTime.now().plusMonths(1));
	}
	
	public static Fecha MasUnAño() {								//* crea una fecha de forma estatica con la fecha de un año mas.
		
		return new Fecha(LocalDateTime.now().plusYears(1));
	}
	
	public static Fecha fromString(String fecha) { 					//* Crea una fecha con un string de forma estatica.
		
		return new Fecha(fecha);
	}

	public static Fecha fromISOFormat(String strFecha) {			//* Creea un fecha a partir de un ISOFormat.
		
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate  fecha = LocalDate.parse(strFecha, formatoFecha);
		LocalTime	hora = LocalTime.now();

		LocalDateTime localdate = LocalDateTime.of(fecha, hora);
		
		return new Fecha(localdate);
		
	}

	public static Fecha fromTimeStamp(Timestamp fechaTimeStamp) {	// * Crea una fecha a partir de un TimeStamp.
		
		return new Fecha(fechaTimeStamp);
	}
	
	public static Fecha fromFecha(Fecha fechaObject) {				//* Crea una fecha a partir de otra Instancia Fecha.
		
		return new Fecha(fechaObject);
	}

	public static Fecha fromMutableFecha(FechaMutable fechaMutable) { // Crea una fecha a partir de una fecha Mutable.
		
		return new FechaMutable(fechaMutable);
		
	}
	
	public static Fecha fromLocalDate(LocalDateTime localdate) { 		//* Crea una fecha a partir de un localdate.
		
		return new Fecha(localdate);
	}
	
	public static Fecha fromYearDay(Integer dia) {					//* Crea una fecha a partir de un dia del año.
		
		return new Fecha(dia);
	}

	public static Fecha fromYearDay(Integer año, Integer dia) {		//* Crea una fecha a partir de un año especifico y un dia de ese año.
		
		return new Fecha(año, dia);
	}
	
	public static Fecha suma(cantidad cantidad, Integer numero) {	//* crea una fecha a partir de la actual sumandole dias semanas meses o años.
	
		switch (cantidad) {
		case DIAS:
			return new Fecha(LocalDateTime.now().plusDays(numero.longValue()));
		
		case SEMANAS:
			return new Fecha(LocalDateTime.now().plusWeeks(numero.longValue()));

		case MESES:
			return new Fecha(LocalDateTime.now().plusMonths(numero.longValue()));

		case AÑOS:
			return new Fecha(LocalDateTime.now().plusYears(numero.longValue()));

		default:
			return null;
		}
			
	}

	public static Fecha resta(cantidad cantidad, Integer numero) { 	//* crea una fecha a partir de la actual dias semanas meses o años.
		
		switch (cantidad) {
		case DIAS:
			return new Fecha(LocalDateTime.now().minusDays(numero.longValue()));
		
		case SEMANAS:
			return new Fecha(LocalDateTime.now().minusWeeks(numero.longValue()));

		case MESES:
			return new Fecha(LocalDateTime.now().minusMonths(numero.longValue()));

		case AÑOS:
			return new Fecha(LocalDateTime.now().minusYears(numero.longValue()));

		default:
			return null;
		}
	}
	
	///////////////////////////////////////////
	
		
	////////////// PUBLICAS ////////////////

	public Date getDate() {
		return Date.from(this.fecha.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public LocalDateTime	getLocalDateTime() {							//* Devuelve el localDate, si por algun motivo fue null deveulve la fecha actual y lanza un mensaje de error por la consola.

		if(this.fecha == null) {
			System.err.println("Fecha fue null, fecha ajustada al día de hoy");
			return LocalDateTime.now();
		
		} else{
			return this.fecha;
		}
	}
	
	public Timestamp 	getTimeStamp() {							//* Devuelve la fecha en formato TimeStamp, si es null devuelve la fecha de hoy.
		
		if(this.fecha == null) {
			System.err.println("Fecha fue null, fecha ajustada al día de hoy");
			return Timestamp.valueOf(LocalDate.now().atStartOfDay());
			
		} else {

			return Timestamp.valueOf(this.fecha);
		}
	}
	
	public String 		getDiaMesStirng() {							//* Devulve un stirng del tipo dia mes ej. "3009" que corresponde al dia y el mes en string, agregando ceros cuando son numeros simles: esto sirve para buscar en la tabla hash de feriados.
		
		if(this.fecha!=null) {

			String  strDia;
			String 	strMes;
			
			strDia	= Integer.toString(this.fecha.getDayOfMonth());
			strMes	= Integer.toString(this.fecha.getMonthValue());
			
			if(strDia.length() < 2) {
				strDia = "0"+strDia;
			}
	
			if(strMes.length() < 2) {
				strMes = "0" + strMes;
			}
			
			return strDia.concat(strMes);
		} else{
			System.err.println("Error Imposible obtener diaMes la instancia fecha es null, valor por defecto al dia de hoy.");
			this.fecha = LocalDateTime.now();
			getDiaMesStirng();
		}
		return null;
	}
	
	public Integer		getDiaClendario() {							//* Devulve el numero del dia del calendario.
		
		if(this.fecha!= null) {

			return this.fecha.getDayOfMonth();
		} else{

			System.err.println("la fecha tiene un valor de NULL, la fecha fue ajustada al dia de hoy.");
			return LocalDate.now().getDayOfMonth();
		}
	}
	
	public String 		getNombreMes() {							//* Deveulve el nombre del Mes.
		
		if(this.fecha!= null) {
			
			return StringFormat.upperFirst(Month.from(this.fecha).getDisplayName(TextStyle.FULL, this.local));
		} else{
			
			System.err.println("la fecha tiene un valor NULL, al fecha fue ajustada al dia de hoy.");
			return StringFormat.upperFirst(Month.from(LocalDate.now()).getDisplayName(TextStyle.FULL, this.local));
		}
	}
	
	public Integer 		getNumeroDiaSemana() {						//* Deveulve el numero de dia de la semana.
		
		if(this.fecha!= null) {
			
			return DayOfWeek.from(this.fecha).getValue();
		} else{
			
			System.err.println("la fecha tiene un valor NULL, al fecha fue ajustada al dia de hoy.");
			return DayOfWeek.from(LocalDate.now()).getValue();
		}
		
	}
	
	public String 		getNombreDiaSemana() {						//* Deveulve el nombre del dia de la semana.
		
		if(this.fecha!= null) {
			
			return StringFormat.upperFirst(DayOfWeek.from(this.fecha).getDisplayName(TextStyle.FULL, this.local));
		} else{

			System.err.println("la fecha tiene un valor NULL, al fecha fue ajustada al dia de hoy.");
			return StringFormat.upperFirst(DayOfWeek.from(this.fecha).getDisplayName(TextStyle.FULL, this.local));
		}
 	}
	
	public Integer 		getAño() {									//* Devulve el el año de la fecha.
		
		if(this.fecha!= null) {
			
			return this.fecha.getYear();
		}else {

			System.err.println("la fecha tiene un valor NULL, al fecha fue ajustada al dia de hoy.");
			return LocalDate.now().getYear();
		}
	}
	
	public Integer 		getNumeroDiaDelAño() {						//* Devulve el numero del dia del año.
		
		if(this.fecha!= null) {
			
			return this.fecha.getDayOfYear();
		} else{
			
			System.err.println("la fecha tiene un valor NULL, al fecha fue ajustada al dia de hoy.");
			return LocalDate.now().getDayOfYear();
		}
	}
	
	
	public String 		getLongPrettyFormatWihtTime() {							//* Deveulve la fecha en un formato bonito.
		
		if(this.fecha == null) 
			this.fecha = LocalDateTime.now();

		String nombreDiaSemana;
		String mes;
	    int dia;
        int ano;
	                        
        nombreDiaSemana 	= this.getNombreDiaSemana();
	    dia					= this.getDiaClendario();
	    mes 				= this.getNombreMes();
	    ano 				= this.getAño();
	    String hora 		= this.fecha.format(DateTimeFormatter.ofPattern("'a las' HH:mm 'Hrs.'"));
	   
	    // Determina que si la fecha es igual al día de hoy, escribe Hoy, si no no escribe nada.
		 if(this.getISOFormat().equals(Hoy().getISOFormat())) {

 	    	return String.format("Hoy %s, %d de %s del %d %s", nombreDiaSemana, dia, mes, ano, hora);
 	    }else {

 	    	return String.format("%s, %d de %s del %d %s", nombreDiaSemana, dia, mes, ano, hora);
 	    }
	
	}
	
	public String 		getLongPrettyFormat() {							//* Deveulve la fecha en un formato bonito.
		
		if(this.fecha == null) 
			this.fecha = LocalDateTime.now();

		String nombreDiaSemana;
		String mes;
	    int dia;
        int ano;
	                        
        nombreDiaSemana 	= this.getNombreDiaSemana();
	    dia					= this.getDiaClendario();
	    mes 				= this.getNombreMes();
	    ano 				= this.getAño();
	   
	    // Determina que si la fecha es igual al día de hoy, escribe Hoy, si no no escribe nada.
	    
    		
		 if(this.getISOFormat().equals(Hoy().getISOFormat())) {
 	    	return String.format("Hoy %s, %d de %s del %d", nombreDiaSemana, dia, mes, ano);
 	    }else {

 	    	return String.format("%s, %d de %s del %d", nombreDiaSemana, dia, mes, ano);
 	    }
	
	}
	
	
	public String 		getLongPrettyFormatWithSpace() {				//* Deveulve la fecha en un formato bonito pero espaciada.

		if(this.fecha == null) 
			this.fecha = LocalDateTime.now();
		
			String nombreDiaSemana;
		    String mes;
		    int dia;
		    int ano;
		                     
		    nombreDiaSemana 	= this.getNombreDiaSemana();
		    dia					= this.getDiaClendario();
		    mes 				= this.getNombreMes();
		    ano 				= this.getAño();
		   
		    if(this.getISOFormat().equals(Hoy().getISOFormat())) {
		    	return String.format("Hoy %-8s %-2d de %-10s del %d", nombreDiaSemana, dia, mes, ano);
		    }else{
		    	return String.format("%-8s %-2d de %-10s del %d", nombreDiaSemana, dia, mes, ano);
		    }                   
		    
		}
	
	
	
	
	public String 		getZona() {									//* Devuelve la Zona en la que esta la instancia.
		
		return StringFormat.upperFirst(this.local.getDisplayLanguage()) + " : " + local.getDisplayCountry(local);
	}

	public String		getISOFormat() {							//* Devuelve la Fecha en formato ISO añomesdia. 
		
		return this.fecha.format(DateTimeFormatter.BASIC_ISO_DATE);
	}
	
	public String		getBasicFormat() {							//* Devuelve la Fecha en formato dia-mesNombre-año.
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		return this.fecha.toLocalDate().format(format);
	}

	public boolean 		esAnterior(Fecha fecha) {					//* Verifica que la fecha sea anterior a la fecha de su parametro.

		if(this.fecha!= null) {
			if(this.fecha.isBefore(fecha.getLocalDateTime())) {
				return true;
			} else{
				return false;
			}
		}else {
			this.fecha = LocalDateTime.now();
			System.err.println("ERROR : La fecha tienen un valor NULL, la fecha fue ajustada al dia de hoy.");
			return false;
		}
	}
	
	public boolean 		esPosterior(Fecha fecha) {					//* Verifica que la fecha se posterior a la fecha de su parametro.

		if(this.fecha!= null) {
			if(this.fecha.isAfter(fecha.getLocalDateTime())) {
				return true;
			}else{
				return false;
			}
		}else {
			this.fecha = LocalDateTime.now();
			System.err.println("ERROR : La fecha tienen un valor NULL, la fecha fue ajustada al dia de hoy.");
			return false;
		}

	}
	
	public boolean 		esIgual(Fecha fecha) {
		
		if(this.fecha.isEqual(fecha.fecha)) {
			return true;
		} else {
			return false;
		}
			
	}

	public boolean 		estaEntreLasFechas(Fecha fechaInical, Fecha fechaFinal) {
	
		// Chequa que las fechas no sean NULL.
		if(fechaInical!= null && fechaFinal!=null) {

			// Chequea que la fecha inical sea menor al la fecha final.
			if(fechaInical.esAnterior(fechaFinal)) {

				// Revisa que la fecha este dentro del rango
				Boolean esFechaInical = (this.fecha.isEqual(fechaInical.getLocalDateTime()) || this.fecha.isAfter(fechaInical.getLocalDateTime()));
				Boolean esFechaFinal  = (this.fecha.isEqual(fechaFinal.getLocalDateTime())  || this.fecha.isBefore(fechaFinal.getLocalDateTime()));
				
				if(esFechaInical && esFechaFinal) {
					return true;
				}
				
			} else{
				System.err.println("Fecha inicial debe ser menor a fecha final y tampoco iguales.");
				return false;
			}

		} else {
			System.err.println("Alguna fecha es NULL");
			return false;
		}
		return false;	
	}

	public boolean  	esAñoBisiesto() {							// Devuelve verdadero si el año es bisiesto y falso si no lo es.
		
		LocalDate localdate = this.fecha.toLocalDate();
		return localdate.isLeapYear();
	}
	
	
	public void 		setZona(Locale local) {						//* Establece la zona para el formato de la fecha.
		
		this.local = local;
	}

	public String 		getShortPrettyFormat() {					//* Devuelve la fecha con un formato ej : "Martes, 3 Enero".
		
		//String	nombreDia = this.getNombreDiaSemana();
		String	nombreMes =	this.getNombreMes();
		Integer	dia		  = this.getDiaClendario(); 
		
		return String.format("%d de %s", dia, nombreMes);
		
	}
	
	public String		getStringFormat() {

		
		String  diaMes 	= this.getDiaMesStirng();
		Integer año		= this.getAño();
		
		String fechaString = diaMes + año.toString();
		
		return String.format("%s", fechaString);
		
	}
	
	public String 		getDateTime() {
		
		if(this.fecha!=null) {
			return this.fecha.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy H:mm:ss"));

		}else {
			return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy H:mm:ss"));
		}
		
	}
	
	
	
	public String		getTime() {
		
		if(this.fecha != null) {
			String hora = this.fecha.format(DateTimeFormatter.ofPattern("H:mm:ss"));
			return hora;
		}else {
			String hora = LocalDate.now().format(DateTimeFormatter.ofPattern("H:mm:ss"));
			return hora;
		}
		
		
	}
	
	///////////////////////////////////////////
	
	

	//////////////// PRIVADAS ////////////////
	
	protected void setDateType(Date date) {

		this.fecha = LocalDateTime.parse(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date));
	}
	
	private	void initWithString(String strFecha) {					//* Inicializador por string. crea una fecha apartir de un string con formato ddmmyyyy.
		
		if((this.fecha = stringToDate.convertString(strFecha))==null) {

			System.err.println("Error en el formato de la fecha.");
		}
	}
	
	private void initWithFecha(Fecha fecha) {						//* Inicializador por otro objeto Fecha. (crea un nuevo objeto a partir de un objeto fecha.
		
		if((fecha.getLocalDateTime())!=null) {
			setLocalDateTime(fecha.getLocalDateTime());
		}else {
			System.err.println("initWithFecha a fallado, el parametro fecha es NULL.");
		}
	}

	protected void setLocalDateTime(LocalDateTime localdate) {				//* Establece una nueva fecha a partir de un localDate.
		
		this.fecha = localdate;
	}

	private void setLocal() {										//* Establece la zona Regional.

			this.local = FormatoRegional.getZona(zona.CHILE);
	}

	private void initWithDayOfYear(Integer año, Integer dia) {		//* Inicializador con un numero de dia del año 1 y 366. calcula si es bisiesto y le resta 1.
		

		// Chequea los rangos de dias y años sean correctos.
		if (dia >= 1 && dia <= 366 && año >=1) {
			
			// Chequea si el año es bisiesto o sea que tenga 366 en vez de 365 dias.
			// si no esta el parametro de año, el año por defecto es el actual.
		
			String chequandoAño = "0101" + año.toString();
			
			// Crea una fecha temporal para comprobar si el año es bisisto. y le resta 1 al maximo de dias si no fuera bisiesto.
			Fecha fechaTemp = new Fecha(chequandoAño);

			if(!fechaTemp.getLocalDateTime().toLocalDate().isLeapYear())
				if(dia == 366)
					dia = 365;
			
			LocalDate fechaAnoDia = LocalDate.ofYearDay(año, dia);
			LocalTime hora		  = LocalTime.now();
			
			this.fecha = LocalDateTime.of(fechaAnoDia, hora);
			
		}

	}
	
	private void initWithDiaMes(String mesDia) {
		
		//* Chequea la longitud de la fecha, simepre debe ser de cuatro caracters.
		if(mesDia.length() == 4) {
			
			//* Divide los primeros 2 para el dia y los ultimos 2 para el mes.
			Integer dia = Integer.valueOf(mesDia.substring(0, 2));
			Integer mes = Integer.valueOf(mesDia.substring(2, 4));
			
			//* Chequa que los dias se encutren en el rango corrcto y los meses en su rango correcto.
			if((dia >= 1 && dia <= 31) && (mes >= 1 && mes <= 12)) {

				//* Crea una fecha a partir de esos meses con el año actual.
				LocalDate fecha = LocalDate.of(LocalDate.now().getYear(), mes, dia); 
				LocalTime hora 	= LocalTime.now();
				this.fecha = LocalDateTime.of(fecha, hora);
				
			} else{
				System.err.println("Error initWithDiaMes : los rangos de los dias o meses son incorrectos.");
			}
			
		} else{ 
			System.err.println("Error intitWithMesDia : la longitud debe ser de 4 caracteres.");
		}
	}
	
	/////////////////////////////////////////
}
