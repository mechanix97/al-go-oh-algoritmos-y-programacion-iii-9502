package modelo.cartas;

import modelo.CartaMonstruo;
import modelo.InvocadorDeExodia;

public class PiernaDerechaDeExodia extends CartaMonstruo {

	public PiernaDerechaDeExodia() {
		super("Pierna derecha de Exodia", 1000, 1000, 3);
	}

	@Override
	public void permitirInvocador(InvocadorDeExodia invocadorDeExodia) {
		invocadorDeExodia.invocarPiernaDerechaDeExodia();
	};	
}
