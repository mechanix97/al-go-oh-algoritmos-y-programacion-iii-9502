package modelo.cartas;

import modelo.CartaMagica;
import modelo.CartaMonstruo;
import modelo.Lado;
import modelo.Tablero;

public class Fisura extends CartaMagica{

	public Fisura(){
		super("Fisura");
	}
	
	public CartaMonstruo devolverMenor(CartaMonstruo[] zonaMonstruoEnemiga){
		CartaMonstruo cartaMenorAtaque = zonaMonstruoEnemiga[0];
		int menorAtaque = zonaMonstruoEnemiga[0].extraerPuntosAtaque();
		int ataqueActual;
		for (int i = 1; i < 5; i++) {
			if(zonaMonstruoEnemiga[i] != null){
				ataqueActual = zonaMonstruoEnemiga[i].extraerPuntosAtaque();
				if(ataqueActual < menorAtaque){
					menorAtaque = ataqueActual;
					cartaMenorAtaque = zonaMonstruoEnemiga[i];
				}
			}
		}
		return cartaMenorAtaque;
	}
	
	@Override
	public void activar(){
		CartaMonstruo cartaADestruir;
		Lado ladoDuenio;
		Lado ladoEnemigo;
		Tablero tablero;
		CartaMonstruo[] zonaMonstruoEnemiga;
		ladoDuenio = this.duenio.obtenerLado();
		tablero = ladoDuenio.obtenerTablero();
		ladoEnemigo = tablero.obtenerLadoEnemigo(ladoDuenio);
		zonaMonstruoEnemiga = ladoEnemigo.obtenerZonaMonstruo();
		cartaADestruir = this.devolverMenor(zonaMonstruoEnemiga);
		ladoEnemigo.destruir(cartaADestruir);
		this.destruir();
	}
	

}
