package ar.edu.unlam.pb2.olimpiadas;

import java.util.Objects;

public class Comisario {

	private Integer dniComisario;
	private String nombreComisario;
	private Integer edad;

	public Comisario(Integer dniComisario, String nombreComisario, Integer edad) {
		this.dniComisario=dniComisario;
		this.nombreComisario=nombreComisario;
		this.edad=edad;
	}

	public Integer getDniComisario() {
		return dniComisario;
	}

	public String getNombreComisario() {
		return nombreComisario;
	}

	public Integer getEdad() {
		return edad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dniComisario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comisario other = (Comisario) obj;
		return Objects.equals(dniComisario, other.dniComisario);
	}
	
}
