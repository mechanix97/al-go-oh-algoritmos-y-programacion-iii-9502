package modelo.cartas;

import modelo.CartaMonstruo;

public class InsectoComeHombres extends CartaMonstruo{

	public InsectoComeHombres() {
		super("InsectoComeHombres",450,600,2);
	}

	public void activar(CartaMonstruo monstruoAtacante){
		monstruoAtacante.destruir();
	}
	
	@Override 
	public void enfrentarA(CartaMonstruo monstruoAtacante){
		if(!this.bocaArriba){
			this.bocaArriba = true;
			this.activar(monstruoAtacante);
		}
		else{
			this.estado.enfrentarA(monstruoAtacante, this);
		}
	}
}
