package modelo;


import excepciones.NivelInvalidoException;

public abstract class Nivel {

	public static Nivel crearNivel(int unNivel) {
		
		if (unNivel >= 1 && unNivel <= 4) {
			return new NivelBajo();
		} else if (unNivel >= 5 && unNivel <= 6) {
			return new NivelMedio();
		} else if (unNivel > 6){
			return new NivelSuperior();
		} else {
			throw new NivelInvalidoException();
		}
		
	}

	public abstract void sacrificar(int sacrificios);
}