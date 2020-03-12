package modelo;

import excepciones.CantidadDeSacrificiosInvalidaException;

public class NivelSuperior extends Nivel {

	@Override
	public void sacrificar(int sacrificios) {
		if (sacrificios < 2) {
			throw new CantidadDeSacrificiosInvalidaException();
		}
	}

}
