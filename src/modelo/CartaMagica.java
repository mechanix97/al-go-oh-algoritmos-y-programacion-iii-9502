package modelo;

public abstract class CartaMagica extends Carta {
	
	public CartaMagica(String nombre) {
		super(nombre);
	}
	
	public void destruir() {
		Lado lado = this.duenio.obtenerLado();
		lado.destruir(this);
	}
	
	@Override
	public abstract void activar();
	
	@Override
	public void posicionarEn(Lado lado) {
		lado.colocar(this);		
	}
}
