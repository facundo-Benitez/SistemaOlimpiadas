package ar.edu.unlam.pb2.olimpiadas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Evento {

	private LocalDate dia;
	private LocalTime horaDeInicio;
	private LocalTime horaDeFin;
	private Integer nroParticipantes;
	private Set<Comisario>comisarios;
	private Integer cantidadDeComisariosJueces;
	private Integer cantidadDeComisariosObservadores;

	public Evento(LocalDate dia, LocalTime horaDeInicio, LocalTime horaDeFin, Integer nroParticipantes) {
		this.dia=dia;
		this.horaDeInicio=horaDeInicio;
		this.horaDeFin=horaDeFin;
		this.nroParticipantes=nroParticipantes;
		this.comisarios=new HashSet<>();
		this.cantidadDeComisariosJueces=0;
		this.cantidadDeComisariosObservadores=0;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHoraDeInicio() {
		return horaDeInicio;
	}

	public void setHoraDeInicio(LocalTime horaDeInicio) {
		this.horaDeInicio = horaDeInicio;
	}

	public LocalTime getHoraDeFin() {
		return horaDeFin;
	}

	public void setHoraDeFin(LocalTime horaDeFin) {
		this.horaDeFin = horaDeFin;
	}

	public Integer getNroParticipantes() {
		return nroParticipantes;
	}

	public void setNroParticipantes(Integer nroParticipantes) {
		this.nroParticipantes = nroParticipantes;
	}

	public Set<Comisario> getComisarios() {
		return comisarios;
	}

	
	public Integer getCantidadDeComisariosObservadores() {
		return cantidadDeComisariosObservadores;
	}

	public Integer getCantidadDeComisariosJueces() {
		return cantidadDeComisariosJueces;
	}

	public Boolean agregarComisario(Comisario nuevoComisario) {
		if(nuevoComisario instanceof ComisarioJuez) {
			cantidadDeComisariosJueces++;
		}
		if(nuevoComisario instanceof ComisarioObservador) {			
			cantidadDeComisariosObservadores++;
		}
		return this.comisarios.add(nuevoComisario);		
	}
	

}
