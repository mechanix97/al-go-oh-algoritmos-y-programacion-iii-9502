package modelo.cartas;

import modelo.CartaMonstruo;
import modelo.Jugador;
import modelo.Lado;
import modelo.Tablero;

public class Jinzo extends CartaMonstruo{

	public Jinzo() {
		super("Jinzo #7", 500, 400, 2);
	}
	
	public void activar(){
		Tablero tablero;
		Jugador enemigo;
		Lado ladoDuenio;
		ladoDuenio = this.duenio.obtenerLado();
		tablero = ladoDuenio.obtenerTablero();
		enemigo = tablero.obtenerJugadorEnemigo(this.duenio);
		enemigo.disminuirVidaEn(this.ataque);
	}
}
