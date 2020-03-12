package modelo;

public abstract class CartaDeCampo extends Carta{
	
	protected Tablero tablero;
	protected Jugador duenio;
	
	public CartaDeCampo(String unNombre){
		super(unNombre);
		this.bocaArriba = true;
	}
	
	public void asignarTableroYDuenio(Tablero unTablero,Jugador unDuenio) {
		this.tablero = unTablero;
		this.duenio = unDuenio;
	}
	
	public void activar() {
		this.tablero.aplicarEfectoDeCampo(this.duenio, this);
	}
	
	public abstract void aplicarEfectoCartaIndividualDuenio(CartaMonstruo monstruo);
	
	public abstract void aplicarEfectoCartaIndividualEnemigo(CartaMonstruo monstruo);
	
	public abstract void efectoDuenio(Lado unLado);
	
	public abstract void efectoEnemigo(Lado unLado);
}
