package modelo;

public class FaseDePreparacion extends Fase {

	public FaseDePreparacion(Yugioh yugioh) {
		this.yugioh = yugioh;
	}

	@Override
	public void siguiente() {
		this.yugioh.asignarFase(new FaseDeAtaque(this.yugioh));	
	}

}
