package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Carta;
import modelo.Mazo;

class MazoTest {

	@Test
	void testMazoTieneCuarentaCartas() {
		Mazo mazo = new Mazo();
		assertEquals(40, mazo.obtenerTamanio());
	}
	
	@Test
	void testRobarDosCartasDelMazoDisminuyeSuTamanioEnDos() {
		Mazo mazo = new Mazo();
		Carta carta = mazo.robar();
		Carta otraCarta = mazo.robar();
		assertEquals(38, mazo.obtenerTamanio());
	}

}
