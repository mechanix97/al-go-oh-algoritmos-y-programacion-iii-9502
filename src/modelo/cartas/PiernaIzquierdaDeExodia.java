package modelo.cartas;

import modelo.CartaMonstruo;
import modelo.InvocadorDeExodia;

public class PiernaIzquierdaDeExodia extends CartaMonstruo {

	public PiernaIzquierdaDeExodia() {
		super("Pierna izquierda de Exodia", 1000, 1000, 3);
	}

	@Override
	public void permitirInvocador(InvocadorDeExodia invocadorDeExodia) {
		invocadorDeExodia.invocarPiernaIzquierdaDeExodia();
	};

	
}
