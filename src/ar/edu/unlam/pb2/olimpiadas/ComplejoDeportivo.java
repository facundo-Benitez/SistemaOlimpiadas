package ar.edu.unlam.pb2.olimpiadas;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplejoDeportivo {

	private String nombreComplejo;
	private Double areaTotalOcupada;
	protected List<Evento>eventos;
	
	public ComplejoDeportivo(String nombreComplejo, Double areaTotalOcupada) {
		this.nombreComplejo=nombreComplejo;
		this.areaTotalOcupada=areaTotalOcupada;
		this.eventos=new ArrayList<>();
	}

	public String getNombreComplejo() {
		return nombreComplejo;
	}

	public Double getAreaTotalOcupada() {
		return areaTotalOcupada;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public Boolean agregarEvento(Evento nuevoEvento) {
		return this.eventos.add(nuevoEvento);
		
	}
	
	public abstract Integer getTotalParticipantes();
}
