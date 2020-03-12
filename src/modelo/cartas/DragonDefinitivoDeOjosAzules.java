package modelo.cartas;

import excepciones.CantidadDeSacrificiosDeDragonesBlancosDeOjosAzulesInvalidaException;
import modelo.CartaMonstruo;

public class DragonDefinitivoDeOjosAzules extends CartaMonstruo {

	public DragonDefinitivoDeOjosAzules() {
		super("Dragon definitivo de ojos azules", 4500, 3800, 12);
	}

	@Override
	public void darSacrificios(int sacrificios) {
		if (sacrificios < 3) {
			throw new CantidadDeSacrificiosDeDragonesBlancosDeOjosAzulesInvalidaException();
		}
	}
}
