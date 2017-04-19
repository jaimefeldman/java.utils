package propiedades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompareMaps {
	
	public static boolean sonIguales(Map<String, String> map1, Map<String, String> map2) {
	
		// Determina el tamaño.
		if(map1.size() != map2.size()) return false;
		
		List<String> key1 = new ArrayList<>(map1.keySet());
		List<String> key2 = new ArrayList<>(map2.keySet());
		
		if(key1.equals(key2)) {
			List<String> value1 = new ArrayList<>(map1.values());
			List<String> value2 = new ArrayList<>(map2.values());
			if(value1.equals(value2))
			return true;
		}
		
		return false;
	}
	
	
}
