package ar.edu.unlam.pb2.olimpiadas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SedeOlimpica {
	
	private String nombreSede;
	private List<ComplejoDeportivo> complejos;
	private Set<Evento>eventos;
	private Set<Comisario>comisarios;
	
	public SedeOlimpica(String nombreSede) {
		this.nombreSede=nombreSede;
		this.complejos=new ArrayList<>();
		this.eventos=new HashSet<>();
		this.comisarios=new HashSet<>();
	}

	public String getNombreSede() {
		return nombreSede;
	}

	public Boolean agregarComplejos(ComplejoDeportivo nuevoComplejo) {
		return this.complejos.add(nuevoComplejo);
	}


	public ComplejoSimple crearComplejoSimple(String nombreComplejo, Double areaTotalOcupada) {
		ComplejoSimple simple=new ComplejoSimple(nombreComplejo, areaTotalOcupada);
		return simple;
	}

	public ComplejoPolideportivo crearComplejoPolideportivo(String nombreComplejo, Double areaTotalOcupada) {
		ComplejoPolideportivo polideportivo=new ComplejoPolideportivo(nombreComplejo, areaTotalOcupada);
		return polideportivo;
	}
	
	public Evento crearEvento(LocalDate dia, LocalTime horaDeInicio,LocalTime horaDeFin,Integer nroParticipantes) {
		Evento nuevoEvento=new Evento(dia, horaDeInicio, horaDeFin,nroParticipantes);
		return nuevoEvento;
	}
	
	public Boolean agregarEvento(Evento nuevoEvento) {
		return this.eventos.add(nuevoEvento);
	}
	
	public Boolean registarComisarios(Comisario nuevoComisario) {
		return this.comisarios.add(nuevoComisario);
	}
	
	public Evento buscarEvento(Evento evento) {
		for (Evento eventoActual : eventos) {
			if(eventoActual.equals(evento)) {
				return eventoActual;
			}
		}
		return null;
	}
	
	public ComplejoDeportivo buscarComplejo(ComplejoDeportivo complejo) {
		for (ComplejoDeportivo complejoActual : this.complejos) {
			if(complejoActual.equals(complejo)) {
				return complejoActual;
			}
		}
		return null;
	}
	
	public Boolean asignarUnEventoAUnComplejoDeportivo(ComplejoDeportivo complejo , Evento evento) {
		Boolean seAsigno=false;
		ComplejoDeportivo buscado=this.buscarComplejo(complejo);
		if(buscado!=null) {
			buscado.agregarEvento(evento);
			return seAsigno=true;
		}

		return seAsigno;
	}
	public Comisario buscarComisario(Comisario comisario) throws ComisarioException {
		for (Comisario comisarioActual : comisarios) {
			if(comisarioActual.equals(comisario)) {
				return comisarioActual;
			}
		}
		throw new ComisarioException("Comisario Inexistente");
	}
	
	public Boolean asignarComisarioAUnEvento(Evento evento,Comisario comisario) throws ComisarioException {
		Boolean seAsigno=false;
		Evento buscado=this.buscarEvento(evento);
		Comisario comisarioBuscado=this.buscarComisario(comisario);
		if(comisarioBuscado!=null) {
			buscado.agregarComisario(comisario);
			return seAsigno=true;
		}
		throw new ComisarioException("Comisario Inexistente");
	}
	
	public Double calcularPromedioDeEdadDeLosComisariosObservadores(Evento evento) {
		Double promedio=0.0;
		for (Evento eventoActual : eventos) {
			if(eventoActual.equals(evento)) {
			   for (Comisario comisario : eventoActual.getComisarios()) {
				    if(comisario instanceof ComisarioObservador ) {
				    	promedio+= ((ComisarioObservador)comisario).getEdad()/eventoActual.getCantidadDeComisariosObservadores();
				    }
			    }
			}			
		}
		return promedio;
	}


}
