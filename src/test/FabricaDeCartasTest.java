package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import excepciones.CantidadDeSacrificiosInvalidaException;
import excepciones.CodigoDeCartaInvalidoException;
import modelo.FabricaDeCartas;

class FabricaDeCartasTest {

	@Test
	void testSolicitaCrearUnaCartaConCodigoInvalidoYLanzaError() {
		boolean atrapada = false;
		try {
			FabricaDeCartas.crearCarta(-1);
		} catch (CodigoDeCartaInvalidoException e) {
			atrapada = true;
		}
		assertTrue(atrapada);
	}
}
