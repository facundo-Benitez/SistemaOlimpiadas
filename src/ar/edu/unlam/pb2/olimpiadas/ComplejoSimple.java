package ar.edu.unlam.pb2.olimpiadas;

public class ComplejoSimple extends ComplejoDeportivo {
    
	public ComplejoSimple(String nombre, Double areaTotal) {
        super(nombre, areaTotal);
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
