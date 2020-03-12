package modelo;

public class Jugador {

	private int puntosDeVida;
	private Lado lado;
	private Mano mano;
	boolean perdedor;
	private String nombre;
	
	public Jugador(String nombre) {
		this.puntosDeVida = 8000; 
		this.mano = new Mano();
		this.perdedor = false;
		this.nombre = nombre;
	}
	
	public void sacarCartaDeMano(Carta carta) {
		this.mano.sacarCarta(carta);
	}
	
	public int cantidadDeCartasEnMano() {
		return this.mano.obtenerTamanio();
	}
	
	public void disminuirVidaEn(int danio) {
		this.puntosDeVida -= danio;
		if(this.puntosDeVida < 0) {
			this.puntosDeVida = 0;
		}
	}

	public void asignarLado(Lado lado) {
		this.lado = lado;
	}
	
	public String obtenerNombre() {
		return this.nombre;
	}
	
	public int obtenerSacrificios() {
		return this.lado.obteneCantidadDeSacrificios();
	}
	
	public Lado obtenerLado() {
		return this.lado;
	}
	
	public int obtenerVida() {
		return this.puntosDeVida;
	}

	public Mano obtenerMano() {
		return this.mano;
	}

	public boolean esPerdedor() {
		return this.puntosDeVida <= 0 || this.lado.obtenerMazo().estaVacio();
	}

	public boolean esGanador() {
		Jugador oponente = this.lado.obtenerTablero().obtenerJugadorEnemigo(this);
		return this.mano.contieneExodia() || oponente.esPerdedor();
	}
	
	public void agregarCartaAMano(Carta carta) {
		this.mano.agregar(carta);
	}
}
