package modelo;

public class FaseDeAtaque extends Fase {

	public FaseDeAtaque(Yugioh yugioh) {
		this.yugioh = yugioh;
	}
	
	@Override
	public void siguiente() {
		this.yugioh.asignarFase(new FaseFinal(this.yugioh));
	}

}
