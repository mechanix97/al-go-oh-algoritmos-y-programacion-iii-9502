package modelo.cartas;

import modelo.CartaMagica;
import modelo.Mano;
import modelo.Mazo;
import modelo.Lado;

public class OllaDeLaCodicia extends CartaMagica {

	public OllaDeLaCodicia() {
		super("Olla de la codicia");
	}

	@Override
	public void activar() {
		Lado lado = this.duenio.obtenerLado();
		Mazo mazo = lado.obtenerMazo();
		Mano mano = lado.obtenerJugador().obtenerMano();
		
		mano.agregar(mazo.robar());
		mano.agregar(mazo.robar());
		this.destruir();
	}
}
