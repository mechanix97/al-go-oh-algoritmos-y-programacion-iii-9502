package modelo.cartas;

import modelo.CartaMonstruo;
import modelo.InvocadorDeExodia;

public class BrazoIzquierdoDeExodia extends CartaMonstruo {

	public BrazoIzquierdoDeExodia() {
		super("Brazo izquierdo de Exodia", 1000, 1000, 3);
	}

	@Override
	public void permitirInvocador(InvocadorDeExodia invocadorDeExodia) {
		invocadorDeExodia.invocarBrazoIzquierdoDeExodia();
	};	
	
}
