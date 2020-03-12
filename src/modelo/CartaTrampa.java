package modelo;

public class CartaTrampa extends Carta {

	
	public CartaTrampa(String nombre) {
		super(nombre);
	}
	
	public boolean verificarCarta(){
		if((this.nombre == "Cilindro Magico") | (this.nombre == "Reinforcements")){
			return true;
		}
		return false;
	}
	
	public void destruir() {
		Lado lado = this.duenio.obtenerLado();
		lado.destruir(this);
	}
	
	public void activar(CartaMonstruo cartaAtacada, CartaMonstruo cartaParaAtacar) {}

	@Override
	public void posicionarEn(Lado lado) {
		lado.colocar(this);		
	}
}
