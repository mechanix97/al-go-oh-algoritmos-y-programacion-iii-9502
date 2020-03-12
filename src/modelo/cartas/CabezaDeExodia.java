package modelo.cartas;

import modelo.CartaMonstruo;
import modelo.InvocadorDeExodia;

public class CabezaDeExodia extends CartaMonstruo {

	public CabezaDeExodia() {
		super("Cabeza de Exodia", 1000, 1000, 3);	
	}
	
	@Override
	public void permitirInvocador(InvocadorDeExodia invocadorDeExodia) {
		invocadorDeExodia.invocarCabezaDeExodia();
	};

}
