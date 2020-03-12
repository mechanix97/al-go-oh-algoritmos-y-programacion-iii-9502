package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.CartaMagica;
import modelo.CartaMonstruo;
import modelo.cartas.AgujeroOscuro;


public class CartaTest {
	@Test
	void testCodigoDeCartas() {
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		CartaMagica ao = new AgujeroOscuro();
		assertTrue(beautiful.obtenerId() == 1);
		assertTrue(ao.obtenerId() == 21);
	}	
}
