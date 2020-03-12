package modelo.cartas;

import modelo.CartaMonstruo;
import modelo.CartaTrampa;
import modelo.Jugador;
import modelo.Lado;
import modelo.Tablero;

public class CilindroMagico extends CartaTrampa {

	public CilindroMagico() {
		super("Cilindro Magico");
	}

	public void activar(CartaMonstruo cartaAtacada,CartaMonstruo cartaParaAtacar){
		Lado ladoDuenio;
		Tablero tablero;
		Jugador enemigo;
		ladoDuenio = this.duenio.obtenerLado();
		tablero = ladoDuenio.obtenerTablero();
		enemigo = tablero.obtenerJugadorEnemigo(this.duenio);
		enemigo.disminuirVidaEn(cartaParaAtacar.extraerPuntosAtaque());
		this.destruir();
	}
	
}
