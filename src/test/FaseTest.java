package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Jugador;
import modelo.Yugioh;

class FaseTest {

	@Test
	void testSeJueganLasCincoFasesYPasaAlSiguienteJugador() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador primerJugador = yugioh.obtenerJugadorDeTurno();
		
		// inicial a preparacion
		yugioh.siguienteFase();
		// preparacion a ataque
		yugioh.siguienteFase();
		// ataque a final
		yugioh.siguienteFase();
		// final al siguiente turno
		yugioh.siguienteFase();
		
		Jugador segundoJugador = yugioh.obtenerJugadorDeTurno();
		assertNotEquals(primerJugador, segundoJugador);
	}

}
