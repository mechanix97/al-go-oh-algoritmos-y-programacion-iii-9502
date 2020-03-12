package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import excepciones.CantidadDeSacrificiosInvalidaException;
import excepciones.NoHayEspecioEnElCampoException;
import excepciones.PosicionOcupadaException;
import modelo.CartaMonstruo;
import modelo.Jugador;
import modelo.Lado;
import modelo.Yugioh;
import modelo.cartas.AgujeroOscuro;
import modelo.cartas.Jinzo;
import modelo.cartas.Reinforcements;

class LadoTest {

	@Test
	void testLadoInicialmenteNoContieneCartasEnElCementerio() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		assertEquals(0, lado.cantidadDeCartasEnCementerio());
	}

	@Test
	void testSeDestruyeUnaCartaYEstaApareceEnElCementerio() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		Jinzo jinzo = new Jinzo();
		lado.colocar(jinzo);
		jinzo.destruir();
		
		assertEquals(1, lado.cantidadDeCartasEnCementerio());
	}
	
	@Test
	void testSeAgregaMonstruoAlCampoYApareceAhi() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		Jinzo jinzo = new Jinzo();
		lado.colocar(jinzo);
		
		assertTrue(lado.estaEnCampoMonstruo(jinzo));
	}
	
	@Test
	void testSeBuscaUnaCartaQueNoExisteEnElCampo() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		Jinzo jinzo = new Jinzo();
		Jinzo otroJinzo = new Jinzo();
		
		lado.colocar(jinzo, 0);

		boolean atrapada = false;
		try {
			lado.colocar(otroJinzo, 0);
		} catch (PosicionOcupadaException e) {
			atrapada = true;
		}
		assertTrue(atrapada);
	}
	
	@Test
	void testSeColocaUnaCartaEnUnaPosicionOcupadaYLanzaError() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		Jinzo jinzo = new Jinzo();
	}
	
	@Test
	void testInicialmenteNoSeCuentaConSacrificios() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		assertEquals(0, lado.obteneCantidadDeSacrificios());
	}
	
	@Test
	void testSeSacrificanDosCartasYLaCantidadDeSacrificiosEsDos() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		Jinzo jinzo = new Jinzo();
		Jinzo otroJinzo = new Jinzo();
		
		lado.colocar(jinzo);
		lado.colocar(otroJinzo);
		
		lado.sacrificar(jinzo);
		lado.sacrificar(otroJinzo);
		
		assertEquals(2, lado.obteneCantidadDeSacrificios());
		
	}
	
	@Test
	void testSeColocaUnaCartaMagicaEnUnaPosicionOcupadaYLanzaError() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		lado.colocar(new AgujeroOscuro(), 0);
		boolean atrapada = false;
		try {
			lado.colocar(new AgujeroOscuro(), 0);
		} catch (PosicionOcupadaException e) {
			atrapada = true;
		}
		
		assertTrue(atrapada);
	}
	
	@Test
	void testSeColocaUnaCartaTrampaEnUnaPosicionOcupadaYLanzaError() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		lado.colocar(new Reinforcements(), 0);
		boolean atrapada = false;
		try {
			lado.colocar(new Reinforcements(), 0);
		} catch (PosicionOcupadaException e) {
			atrapada = true;
		}
		
		assertTrue(atrapada);
		
	}
	
	@Test
	void testSeColocaUnMonstruoEstaEnElCampo() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		Jinzo jinzo = new Jinzo();
		lado.colocar(jinzo);
		assertTrue(lado.estaMonstruo(jinzo, 0));
	}
	
	@Test
	void testSeColocanMuchasCartasMagicasEnElCampoYLanzaExcepcion() {
		
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		lado.colocar(new AgujeroOscuro());
		lado.colocar(new AgujeroOscuro());
		lado.colocar(new AgujeroOscuro());
		lado.colocar(new AgujeroOscuro());
		lado.colocar(new AgujeroOscuro());
		boolean atrapada = false;
		try {
			lado.colocar(new AgujeroOscuro());
		} catch (NoHayEspecioEnElCampoException e) {
			atrapada = true;
		}
		assertTrue(atrapada);
	}
	
	@Test
	void testSeColocanMuchasCartasMonstruoEnElCampoYLanzaError() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		lado.colocar(new Jinzo());
		lado.colocar(new Jinzo());
		lado.colocar(new Jinzo());
		lado.colocar(new Jinzo());
		lado.colocar(new Jinzo());
		boolean atrapada = false;
		try {
			lado.colocar(new Jinzo());
		} catch (NoHayEspecioEnElCampoException e) {
			atrapada = true;
		}
		assertTrue(atrapada);
		
	}
	
	@Test
	void testSeColocanMuchasCartasTrampasEnElCampoYDaError() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		boolean atrapada = false;
		try {
			for (int i = 0; i < 10; i ++) {
				lado.colocar(new Reinforcements());
			}
		} catch (NoHayEspecioEnElCampoException e) {
			atrapada = true;
		}
		assertTrue(atrapada);
	}
	
	@Test
	void testLaCantidadInicialDeCartasEnManoEsCinco() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		assertEquals(5, lado.cantitadDeCartasEnMano());
	}
	
	@Test
	void testLaCantidadInicialDeCartasEnCementerioEsCero() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		assertEquals(0, lado.cantidadDeCartasEnCementerio());
	}
	
	@Test
	void testLaCantidadInicialDeCartasEnMazoEs35() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		assertEquals(35, lado.cantidadDeCartasEnMazo());
	}
	
	@Test
	void testSeBajaUnaCartaNivelMedioSinSacrificiosNecesariosYDaError() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
				
		boolean atrapada = false;
		try {
			lado.colocar(new CartaMonstruo("Invalida", 500, 400, 6));
		} catch (CantidadDeSacrificiosInvalidaException e) {
			atrapada = true;
		}
		assertTrue(atrapada);
	}
	
	@Test
	void testSeBajaUnaCartaNivelSuperiorSinSacrificiosNecesariosYDaError() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
				
		boolean atrapada = false;
		try {
			lado.colocar(new CartaMonstruo("Invalida", 500, 400, 12));
		} catch (CantidadDeSacrificiosInvalidaException e) {
			atrapada = true;
		}
		assertTrue(atrapada);
	}
	
	
	
}
