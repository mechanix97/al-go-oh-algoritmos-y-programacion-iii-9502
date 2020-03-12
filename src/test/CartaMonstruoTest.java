package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import excepciones.ImposibleAtacarEnEstadoDeDefensaException;
import excepciones.NivelInvalidoException;
import modelo.CartaMonstruo;
import modelo.Jugador;
import modelo.Lado;
import modelo.Yugioh;

class CartaMonstruoTest {

	@Test
	void testUnMonstruoAtacaEnEstadoDeDefensaYLanzaError() {
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		
		amazon.cambiarAPosicionDeDefensa();
		
		boolean atrapada = false;
		try {
			amazon.atacarA(beautiful);
		} catch(ImposibleAtacarEnEstadoDeDefensaException e) {
			atrapada = true;
		}
		assertTrue(atrapada);
	}
	
	@Test void testAumentarAtaqueEn100AumentaElAtaqueEn100(){
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		amazon.aumentarAtaque(100);
		assertEquals(1400, amazon.extraerPuntosAtaque());
	}
	
	@Test void testAumentarDefensaEn100AumentaElDefensaEn100(){
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		amazon.aumentarDefensa(100);
		assertEquals(1500, amazon.extraerPuntosDefensa());
	}
	
	@Test 
	void testMonstruoRecibeFuerteAtaqueASusPuntosDeAtaqueYDestruye() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		lado.colocar(amazon);
		
		amazon.recibirDanioAPuntosDeAtaque(2000);
		assertTrue(lado.cementerioContiene(amazon));
	}
	
	@Test 
	void testMonstruoRecibeFuerteAtaqueASusPuntosDeDefensaYDestruye() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		lado.colocar(amazon);
		
		amazon.recibirDanioAPuntosDeDefensa(2000);
		assertTrue(lado.cementerioContiene(amazon));
	}
	
	@Test
	void testSeCreaUnaCartaMonstruoDeNivelCeroYLanzaException() {
		boolean atrapada = false;
		try {
			CartaMonstruo invalido = new CartaMonstruo("Invalido", 400, 300, 0);
		} catch (NivelInvalidoException e) {
			atrapada = true;
		}
		assertTrue(atrapada);
	}
}
