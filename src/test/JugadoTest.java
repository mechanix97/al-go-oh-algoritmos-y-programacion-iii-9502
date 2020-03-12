package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Jugador;
import modelo.Yugioh;
import modelo.cartas.Jinzo;

class JugadoTest {

	@Test
	void testInicialmenteNoTieneSacrificios() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		assertEquals(0, jugador.obtenerSacrificios());
	}
	
	@Test
	void testInicialmenteTiene8000DeVida() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		assertEquals(8000, jugador.obtenerVida());
	}

	@Test
	void testInicialmenteNoEsPerdedor() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		assertFalse(jugador.esPerdedor());
	}
	
	@Test
	void testInicialmenteNoEsGanador() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		assertFalse(jugador.esGanador());
	}
	
	@Test
	void testSeAgregaUnaCartaASuManoYAhoraTiene6() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		jugador.agregarCartaAMano(new Jinzo());
		assertEquals(6, jugador.obtenerMano().obtenerTamanio());
		
	}
	
	@Test
	void testJugadorTieneMenosDeCeroPuntosDeVidaYPierdeElJuego() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		jugador.disminuirVidaEn(9000);
		assertTrue(jugador.esPerdedor());
	}
	
	@Test
	void testJugadorDeTurnoTieneMenosDeCeroPuntosDeVidaYElOponenteGanaElJuego() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Jugador oponente = yugioh.obtenerJugadorOponente();
		jugador.disminuirVidaEn(9000);
		assertTrue(oponente.esGanador());
	}
	
}
