package cronometro;

public class Cronometro {
	
	private Long inicio, fin;
	
	public void start() {

		this.inicio = System.currentTimeMillis();
	}
	
	public void stop() {
		
		this.fin = System.currentTimeMillis();
	}
	
	public String getTime() {

		Long resultado = (fin - inicio);
		
		if(resultado < 1000.0) {
			return String.valueOf(getMilliseconds()) + " ms.";
		}else if((resultado > 1000 && resultado < 60000)) {
			return String.format("%.2f %s", (float)(resultado / 1000.0), "sec.");
		}else if ((resultado > 60000.0 && resultado < 3600000.0)) {
			return String.format("%.2f %s", (float)(resultado / 60000.0), "min.");
		}else if ((resultado > 3600000.0)) {
			return String.format("%.2f %s", (float)(resultado / 3600000.0), "hrs.");
		}
		
		return "";
	}
	
	public long getMilliseconds() {
		
		return fin - inicio;
	}
	
	public double getSeconds() {
		
		return (fin - inicio) / 1000.0;
	}
	
	public double getMinutes() {
		
		return (fin - inicio) / 60000.0;
	}
	
	public double getHours() {
		
		return (fin - inicio) / 3600000.0;
	}
	
	
}
