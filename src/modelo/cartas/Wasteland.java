package modelo.cartas;

import modelo.CartaDeCampo;
import modelo.CartaMonstruo;
import modelo.Lado;

public class Wasteland extends CartaDeCampo{
	
	public Wasteland(){
		super("Wasteland");
	}

	@Override
	public void efectoDuenio(Lado unLado) {
		unLado.aumentarPuntosAtaque(200);
	}

	@Override
	public void efectoEnemigo(Lado unLado) {
		unLado.aumentarPuntosDefensa(300);
	}

	@Override
	public void aplicarEfectoCartaIndividualDuenio(CartaMonstruo monstruo) {
		monstruo.aumentarAtaque(200);
	}

	@Override
	public void aplicarEfectoCartaIndividualEnemigo(CartaMonstruo monstruo) {
		monstruo.aumentarDefensa(300);
	}

	@Override
	public void posicionarEn(Lado lado) {
		// TODO Auto-generated method stub
		
	}
}
