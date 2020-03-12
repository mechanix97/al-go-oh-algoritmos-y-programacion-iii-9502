package modelo.cartas;

import modelo.CartaMonstruo;
import modelo.CartaTrampa;

public class Reinforcements extends CartaTrampa{
	
	CartaMonstruo cartaAyudada;

	public Reinforcements() {
		super("Reinforcements");
	}
	
	public void destruir(){
		this.cartaAyudada.aumentarAtaque(-500);
		super.destruir();
	}
	
	public void activar(CartaMonstruo cartaAtacada,CartaMonstruo cartaParaAtacar){
		this.bocaArriba = true;
		this.cartaAyudada = cartaAtacada;
		cartaAtacada.aumentarAtaque(500);
		cartaAtacada.atacarA(cartaParaAtacar);
	}
}
