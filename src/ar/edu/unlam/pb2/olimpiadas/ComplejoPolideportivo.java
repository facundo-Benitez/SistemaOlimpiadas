package ar.edu.unlam.pb2.olimpiadas;

import java.util.HashMap;
import java.util.Map;

public class ComplejoPolideportivo extends ComplejoDeportivo {
    private Map<String, String> areas;

    public ComplejoPolideportivo(String nombre, Double areaTotal) {
        super(nombre, areaTotal);
        this.areas = new HashMap<>();
    }

    public void agregarArea(String ubicacion, String deporte) throws IndicadorAreaException {
        if (areas.containsKey(ubicacion)) {
            throw new IndicadorAreaException("El indicador de area ya existe");
        }
        areas.put(ubicacion, deporte);
    }

    public Map<String, String> getMapaDeAreas() {
        return areas;
    }

    @Override
    public Integer getTotalParticipantes() {
    	Integer cantidad=0;
    	for (Evento evento : eventos) {
    		cantidad+=evento.getNroParticipantes();
		}
        return cantidad;
    }
    
}
