package ar.edu.unlam.pb2.olimpiadas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

public class OlimpiadasTest {
	
	private static final String NOMBRE_SEDE="tokio Japon 2024";
	private SedeOlimpica sedeOlimpica;
	
	@Before
	public void init() {	
		this.sedeOlimpica= new SedeOlimpica(NOMBRE_SEDE);	
	}

	@Test
	public void queSePuedaCrearUnComplejoSimpleEnUnaSedeOlimpica() {
		String nombreComplejo="Complejo Simple";
		Double areaTotalOcupada=150.00;
		
		ComplejoSimple simple= this.sedeOlimpica.crearComplejoSimple(nombreComplejo,areaTotalOcupada);
		Boolean seAgrego=this.sedeOlimpica.agregarComplejos(simple);
		
		assertEquals(nombreComplejo,simple.getNombreComplejo());
		assertEquals(areaTotalOcupada,simple.getAreaTotalOcupada());
		assertTrue(seAgrego);
	}

	@Test
	public void queSePuedaCrearUnComplejoPolideportivoConUnAreaEnUnaSedeOlimpica() throws IndicadorAreaException {
		String nombreComplejo="Complejo Polideportivo";
		Double areaTotalOcupada=150.00;
		String ubicacion="Centro";
		String nombreDeporte="Futbol";
		
		
		ComplejoPolideportivo polideportivo= this.sedeOlimpica.crearComplejoPolideportivo(nombreComplejo,areaTotalOcupada);
		polideportivo.agregarArea(ubicacion, nombreDeporte);
		Boolean seAgrego=this.sedeOlimpica.agregarComplejos(polideportivo);	

		
		assertEquals(nombreComplejo,polideportivo.getNombreComplejo());
		assertEquals(areaTotalOcupada,polideportivo.getAreaTotalOcupada());
		assertTrue(seAgrego);

	}
	
	
	@Test
	public void queSePuedaCrearUnComplejoPolideportivoConUnAreaYUnEventoEnUnaSedeOlimpica() throws IndicadorAreaException, ComisarioException {
		String nombreComplejo="Complejo Polideportivo";
		Double areaTotalOcupada=150.00;
		String ubicacion="Centro";
		String nombreDeporte="Futbol";
		LocalDate dia = LocalDate.of(2024, 05, 15);
		LocalTime horaDeInicio = LocalTime.of(21, 0);
		LocalTime horaDeFin = LocalTime.of(23, 0);
		Integer nroParticipantes=48;
		Integer dniComisario1=12345678;
		String nombreComisario="Juan Perez";
		Integer edad=58;

		
		ComplejoPolideportivo polideportivo= this.sedeOlimpica.crearComplejoPolideportivo(nombreComplejo,areaTotalOcupada);	
		polideportivo.agregarArea(ubicacion, nombreDeporte);
		this.sedeOlimpica.agregarComplejos(polideportivo);
		
		Evento evento1 =this.sedeOlimpica.crearEvento(dia, horaDeInicio, horaDeFin, nroParticipantes);
		this.sedeOlimpica.agregarEvento(evento1);
		
		Comisario arbitro =new ComisarioJuez(dniComisario1,nombreComisario,edad);
		Comisario juezDeLinea1 =new ComisarioObservador(23456478,"Alan Brito",45);
		Comisario juezDeLinea2 =new ComisarioObservador(32456856,"Pedro Jose",50);
		this.sedeOlimpica.registarComisarios(arbitro);
		this.sedeOlimpica.registarComisarios(juezDeLinea1);
		this.sedeOlimpica.registarComisarios(juezDeLinea2);
		
		this.sedeOlimpica.asignarUnEventoAUnComplejoDeportivo(polideportivo, evento1);
		
		Boolean seAsigno1=this.sedeOlimpica.asignarComisarioAUnEvento(evento1, arbitro);
		Boolean seAsigno2=this.sedeOlimpica.asignarComisarioAUnEvento(evento1, juezDeLinea1);
		Boolean seAsigno3=this.sedeOlimpica.asignarComisarioAUnEvento(evento1, juezDeLinea2);
	   
	    assertTrue(seAsigno1);
	    assertTrue(seAsigno2);
	    assertTrue(seAsigno3);
	    assertTrue(polideportivo.getMapaDeAreas().containsKey(ubicacion));
	    
	}
	
	
	@Test (expected=IndicadorAreaException.class)
	public void queAlAgregarUnAreaAUnPolideportivoConIndicadorYaExistenteLanceUnaExcepcionIndicadorAreaException() throws IndicadorAreaException {
		String nombreComplejo="Complejo Polideportivo";
		Double areaTotalOcupada=150.00;
		String ubicacion="Centro";
		String nombreDeporte="Futbol";
		
		
		ComplejoPolideportivo polideportivo= this.sedeOlimpica.crearComplejoPolideportivo(nombreComplejo,areaTotalOcupada);
		polideportivo.agregarArea(ubicacion, nombreDeporte);
		polideportivo.agregarArea(ubicacion, nombreDeporte);
	
	}
	
	
	@Test
	public void queSePuedaAgregarUnComisarioJuezAUnEvento() throws ComisarioException, IndicadorAreaException {
		String nombreComplejo="Complejo Polideportivo";
		Double areaTotalOcupada=150.00;
		String ubicacion="Centro";
		String nombreDeporte="Futbol";
		LocalDate dia = LocalDate.of(2024, 05, 15);
		LocalTime horaDeInicio = LocalTime.of(21, 0);
		LocalTime horaDeFin = LocalTime.of(23, 0);
		Integer nroParticipantes=48;
		Integer dniComisario1=12345678;
		String nombreComisario="Juan Perez";
		Integer edad=58;
		
		ComplejoPolideportivo polideportivo= this.sedeOlimpica.crearComplejoPolideportivo(nombreComplejo,areaTotalOcupada);
		polideportivo.agregarArea(ubicacion, nombreDeporte);
		this.sedeOlimpica.agregarComplejos(polideportivo);
		
		Evento evento1 =this.sedeOlimpica.crearEvento(dia, horaDeInicio, horaDeFin, nroParticipantes);
		this.sedeOlimpica.agregarEvento(evento1);
		
		Comisario arbitro =new ComisarioJuez(dniComisario1,nombreComisario,edad);
		this.sedeOlimpica.registarComisarios(arbitro);
		
		this.sedeOlimpica.asignarUnEventoAUnComplejoDeportivo(polideportivo, evento1);
		
		Boolean seAsigno=this.sedeOlimpica.asignarComisarioAUnEvento(evento1, arbitro);
		
        assertTrue(seAsigno);

	}
	
	@Test(expected=ComisarioException.class)
	public void queAlAgregarUnComisarioJuezInexistenteLanceUnaExcepcionComisarioException() throws ComisarioException, IndicadorAreaException {
		String nombreComplejo="Complejo Polideportivo";
		Double areaTotalOcupada=150.00;
		String ubicacion="Centro";
		String nombreDeporte="Futbol";
		LocalDate dia = LocalDate.of(2024, 05, 15);
		LocalTime horaDeInicio = LocalTime.of(21, 0);
		LocalTime horaDeFin = LocalTime.of(23, 0);
		Integer nroParticipantes=48;
		Integer dniComisario1=12345678;
		String nombreComisario="Juan Perez";
		Integer edad=58;
		
		ComplejoPolideportivo polideportivo= this.sedeOlimpica.crearComplejoPolideportivo(nombreComplejo,areaTotalOcupada);
		polideportivo.agregarArea(ubicacion, nombreDeporte);
		this.sedeOlimpica.agregarComplejos(polideportivo);
		
		Evento evento1 =this.sedeOlimpica.crearEvento(dia, horaDeInicio, horaDeFin, nroParticipantes);
		this.sedeOlimpica.agregarEvento(evento1);
		
		Comisario arbitro1 =new ComisarioJuez(dniComisario1,nombreComisario,edad);
		this.sedeOlimpica.registarComisarios(arbitro1);
		
		this.sedeOlimpica.asignarUnEventoAUnComplejoDeportivo(polideportivo, evento1);
		
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, arbitro1);
		
		Comisario arbitro2 =new ComisarioJuez(23456654,"Pedro Jose",50);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, arbitro2);
        
        
	}
	

	@Test
	public void queSePuedaCalcularElTotalDeParticipantesDeLosEventosDeUnComplejoSimple() throws ComisarioException {
		String nombreComplejo="Complejo Simple";
		Double areaTotalOcupada=150.00;
		LocalDate dia = LocalDate.of(2024, 05, 15);
		LocalTime horaDeInicio = LocalTime.of(21, 0);
		LocalTime horaDeFin = LocalTime.of(23, 0);
		Integer nroParticipantes=48;
		Integer dniComisario1=12345678;
		String nombreComisario="Juan Perez";
		Integer edad=58;
		
		ComplejoSimple complejo =this.sedeOlimpica.crearComplejoSimple(nombreComplejo,areaTotalOcupada);
		this.sedeOlimpica.agregarComplejos(complejo);
		Evento evento1 =this.sedeOlimpica.crearEvento(dia, horaDeInicio, horaDeFin, nroParticipantes);
		this.sedeOlimpica.agregarEvento(evento1);		
		Evento evento2 =this.sedeOlimpica.crearEvento(dia, LocalTime.of(20, 0), LocalTime.of(21, 0), 30);
		this.sedeOlimpica.agregarEvento(evento2);

		Comisario arbitro =new ComisarioJuez(dniComisario1,nombreComisario,edad);
		Comisario juezDeLinea1 =new ComisarioObservador(23456478,"Alan Brito",48);
		Comisario juezDeLinea2 =new ComisarioObservador(32456856,"Pedro Jose",50);
		this.sedeOlimpica.registarComisarios(arbitro);
		this.sedeOlimpica.registarComisarios(juezDeLinea1);
		this.sedeOlimpica.registarComisarios(juezDeLinea2);

		this.sedeOlimpica.asignarUnEventoAUnComplejoDeportivo(complejo, evento2);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento2, arbitro);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento2, juezDeLinea1);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento2, juezDeLinea2);
		this.sedeOlimpica.asignarUnEventoAUnComplejoDeportivo(complejo, evento1);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, arbitro);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, juezDeLinea1);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, juezDeLinea2);
        
		Integer cantidadEsperada=78;
		Integer cantidadObtenida=complejo.getTotalParticipantes();
		assertEquals(cantidadEsperada,cantidadObtenida);
        
	}
	
	
	@Test
	public void queSePuedaCalcularElPromedioDeEdadDeLosComisariosObservadoresDeUnEventoEspecifico() throws IndicadorAreaException, ComisarioException {
		String nombreComplejo="Complejo Simple";
		Double areaTotalOcupada=150.00;
		String ubicacion="Centro";
		String nombreDeporte="Futbol";
		LocalDate dia = LocalDate.of(2024, 05, 15);
		LocalTime horaDeInicio = LocalTime.of(21, 0);
		LocalTime horaDeFin = LocalTime.of(23, 0);
		Integer nroParticipantes=48;
		Integer dniComisario1=12345678;
		String nombreComisario="Juan Perez";
		Integer edad=58;
				
		ComplejoPolideportivo polideportivo= this.sedeOlimpica.crearComplejoPolideportivo(nombreComplejo,areaTotalOcupada);
		polideportivo.agregarArea(ubicacion, nombreDeporte);
		this.sedeOlimpica.agregarComplejos(polideportivo);
		
		Evento evento1 =this.sedeOlimpica.crearEvento(dia, horaDeInicio, horaDeFin, nroParticipantes);
		this.sedeOlimpica.agregarEvento(evento1);
		
		Comisario arbitro =new ComisarioJuez(dniComisario1,nombreComisario,edad);
		Comisario juezDeLinea1 =new ComisarioObservador(23456478,"Alan Brito",48);
		Comisario juezDeLinea2 =new ComisarioObservador(32456856,"Pedro Jose",50);
		this.sedeOlimpica.registarComisarios(arbitro);
		this.sedeOlimpica.registarComisarios(juezDeLinea1);
		this.sedeOlimpica.registarComisarios(juezDeLinea2);
		
		this.sedeOlimpica.asignarUnEventoAUnComplejoDeportivo(polideportivo, evento1);
		
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, arbitro);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, juezDeLinea1);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, juezDeLinea2);
        
        Double promedioEsperado=49.00;
        Double promedioObtenido= this.sedeOlimpica.calcularPromedioDeEdadDeLosComisariosObservadores(evento1);

        assertEquals(promedioEsperado,promedioObtenido);
        
	}
	
	
	@Test
	public void queSePuedaObtenerUnaListaDeComisariosJuecesDeUnEventoEspecificoSinRepeticiones() throws IndicadorAreaException, ComisarioException {
		String nombreComplejo="Complejo Polideportivo";
		Double areaTotalOcupada=150.00;
		String ubicacion="Centro";
		String nombreDeporte="Futbol";
		LocalDate dia = LocalDate.of(2024, 05, 15);
		LocalTime horaDeInicio = LocalTime.of(21, 0);
		LocalTime horaDeFin = LocalTime.of(23, 0);
		Integer nroParticipantes=48;
		Integer dniComisario1=12345678;
		String nombreComisario="Juan Perez";
		Integer edad=58;
		
		ComplejoPolideportivo polideportivo= this.sedeOlimpica.crearComplejoPolideportivo(nombreComplejo,areaTotalOcupada);
		polideportivo.agregarArea(ubicacion, nombreDeporte);
		this.sedeOlimpica.agregarComplejos(polideportivo);
		
		Evento evento1 =this.sedeOlimpica.crearEvento(dia, horaDeInicio, horaDeFin, nroParticipantes);
		this.sedeOlimpica.agregarEvento(evento1);
		
		Comisario arbitro =new ComisarioJuez(dniComisario1,nombreComisario,edad);
		Comisario juezDeLinea1 =new ComisarioObservador(23456478,"Alan Brito",48);
		Comisario juezDeLinea2 =new ComisarioObservador(32456856,"Pedro Jose",50);
		Comisario juezDeLinea3 =new ComisarioObservador(32456856,"Pedro Jose",50);
		
		this.sedeOlimpica.registarComisarios(arbitro);
		this.sedeOlimpica.registarComisarios(juezDeLinea1);
		this.sedeOlimpica.registarComisarios(juezDeLinea2);
		
		this.sedeOlimpica.asignarUnEventoAUnComplejoDeportivo(polideportivo, evento1);
		
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, arbitro);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, juezDeLinea1);
		this.sedeOlimpica.asignarComisarioAUnEvento(evento1, juezDeLinea2);
	}
	
	
	@Test
	public void queSePuedaObtenerUnMapaDeUnComplejoPolideportivoConNombreDeComplejoYUbicacion() throws IndicadorAreaException, ComisarioException {
		String nombreComplejo="Complejo Polideportivo";
		Double areaTotalOcupada=150.00;
		String ubicacion="Centro";
		String nombreDeporte="Futbol";

		
        ComplejoPolideportivo complejo =this.sedeOlimpica.crearComplejoPolideportivo(nombreComplejo,areaTotalOcupada);
        complejo.agregarArea(ubicacion, nombreDeporte);
        complejo.agregarArea("EsquinaNE", "Hockey");
        this.sedeOlimpica.agregarComplejos(complejo);
       
        assertEquals(2, complejo.getMapaDeAreas().size());
        assertTrue(complejo.getMapaDeAreas().containsKey(ubicacion));
        assertTrue(complejo.getMapaDeAreas().containsKey("EsquinaNE"));
		
	}
	
}
