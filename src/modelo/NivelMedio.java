package modelo;


import excepciones.CantidadDeSacrificiosInvalidaException;

public class NivelMedio extends Nivel {

	@Override
	public void sacrificar(int sacrificios) {
		if (sacrificios < 1) {
			throw new CantidadDeSacrificiosInvalidaException();
		}
	}

}
