package modelo;

public class FaseInicial extends Fase {
	
	public FaseInicial(Yugioh yugioh) {
		this.yugioh = yugioh;
	}

	@Override
	public void siguiente() {
		this.yugioh.asignarFase(new FaseDePreparacion(this.yugioh));
	}
}
