package modelo.cartas;

import modelo.CartaDeCampo;
import modelo.CartaMonstruo;
import modelo.Lado;

public class Sogen extends CartaDeCampo{
	
	public Sogen(){
		super("Sogen");
	}

	@Override
	public void efectoDuenio(Lado unLado) {
		unLado.aumentarPuntosDefensa(500);
	}

	@Override
	public void efectoEnemigo(Lado unLado) {
		unLado.aumentarPuntosAtaque(200);
	}

	@Override
	public void aplicarEfectoCartaIndividualDuenio(CartaMonstruo monstruo) {
		monstruo.aumentarDefensa(500);
	}

	@Override
	public void aplicarEfectoCartaIndividualEnemigo(CartaMonstruo monstruo) {
		monstruo.aumentarAtaque(200);
	}

	@Override
	public void posicionarEn(Lado lado) {
		// TODO Auto-generated method stub
		
	}

}
