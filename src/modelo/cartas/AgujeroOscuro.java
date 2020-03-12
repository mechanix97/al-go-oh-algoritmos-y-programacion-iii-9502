package modelo.cartas;

import modelo.CartaMagica;
import modelo.Tablero;
import modelo.Lado;

public class AgujeroOscuro extends CartaMagica{
	
	
	public AgujeroOscuro() {
		super("Agujero Oscuro");
	}

	@Override
	public void activar() {
		Lado lado = this.duenio.obtenerLado();
		Tablero tablero = lado.obtenerTablero();
		tablero.destruirTodosLosMonstruos();
		this.destruir();
	}
}
