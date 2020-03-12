package modelo;

public class FaseFinal extends Fase {

	public FaseFinal(Yugioh yugioh) {
		this.yugioh = yugioh;
	}
	
	@Override
	public void siguiente() {
		this.yugioh.intercambiarJugadores();
		this.yugioh.asignarFase(new FaseInicial(this.yugioh));
	}
}
