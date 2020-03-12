package modelo;

import java.util.ArrayList;

import excepciones.AtacarMonstruoVacioException;
import excepciones.NoHayEspecioEnElCampoException;
import excepciones.NoSePuedeAtacarDirectamenteException;
import excepciones.PosicionOcupadaException;
import modelo.cartas.DragonBlancoDeOjosAzules;
import modelo.cartas.DragonDefinitivoDeOjosAzules;

public class Lado {

	private int tamanio;
	private boolean seColocoMonstruo;
	private CartaDeCampo cartaCampo;
	private CartaMonstruo[] zonaDeMonstruos;
	private Carta[] zonaMagica;
	private ArrayList<Carta> cementerio;
	private Jugador duenio;
	private Tablero tablero;
	private int sacrificios;
	private int sacrificiosDragones;
	private Mazo mazo;
	
	public Lado(Jugador jugador, Tablero tablero) {
		this.seColocoMonstruo = false;
		this.tamanio = 5;
		this.zonaDeMonstruos = new CartaMonstruo[tamanio];
		this.zonaMagica = new Carta[tamanio];
		this.cementerio = new ArrayList<Carta>();
		this.cartaCampo = null;
		this.duenio = jugador;
		this.duenio.asignarLado(this);
		this.tablero = tablero;
		this.sacrificios = 0;
		this.sacrificiosDragones = 0;
		this.mazo = new Mazo();
	}
	
	public void apagarFlags() {
		this.seColocoMonstruo = false;
		for(int i=0;i<5;i++) {
			if(this.zonaDeMonstruos[i]!=null) {
				this.zonaDeMonstruos[i].apagarFlag();
				this.zonaDeMonstruos[i].reiniciarAtaque();
			}
		}
	}
	
	public boolean seInsertoMonstruo(){
		return this.seColocoMonstruo;
	}
	
	public void verificarReinforcements(){
		for (int i = 0; i < this.tamanio; i++) {
			if (this.zonaMagica[i] != null) {
				if((this.zonaMagica[i].esReinforcements()) & (!this.zonaMagica[i].estaBocaAbajo())){
					((CartaTrampa)this.zonaMagica[i]).destruir();
				}

			}
		}
	}
	
	public int cantidadDeCartasEnCementerio() {
		return this.cementerio.size();
	
	}

	public int cantitadDeCartasEnMano() {
		return this.duenio.cantidadDeCartasEnMano();
	}
	
	public int cantidadDeCartasEnMazo() {
		return this.mazo.obtenerTamanio();
	}
	
	public void robarCartaDelMazo() {
		this.duenio.agregarCartaAMano(this.mazo.robar());
	}
	
	public Mano obtenerMano() {
		return this.duenio.obtenerMano();
	}
	
	public CartaMonstruo[] obtenerCartasMonstruo() {
		return this.zonaDeMonstruos;
	}
	
	public Carta[] obtenerCartasMagicas() {
		return this.zonaMagica;
	}
	
	public boolean esDuenio(Jugador jugador) {
		return this.duenio == jugador;
	}
	
	public CartaMonstruo[] obtenerZonaMonstruo(){
		return zonaDeMonstruos;
	}
	
	public void colocarCartaDeCampo(CartaDeCampo campo){
		this.cartaCampo = campo;
		sacarCartaDeMano(campo);
		this.tablero.seAgregoCartaDeCampo();
		campo.asignarTableroYDuenio(this.tablero,this.duenio);
		campo.activar();		
	}
	
	public CartaDeCampo obtenerCartaCampo() {
		return this.cartaCampo;
	}

	public void sacarCartaDeMano(Carta carta) {
		this.duenio.sacarCartaDeMano(carta);
	}
	
	public boolean estaEnCampoMonstruo(CartaMonstruo monstruo) {
		for(int i=0;i < this.tamanio; i++) {
			if (this.zonaDeMonstruos[i] == monstruo) {
				return true;
			}
		}
		return false;
	}
	
	public void colocar(CartaMonstruo monstruo, int i) {
		
		monstruo.darSacrificios(this.sacrificios);
		this.sacrificios = 0;
		colocarEn(monstruo, i);
	}
	
	public void colocar(DragonDefinitivoDeOjosAzules dragon, int i) {
		dragon.darSacrificios(sacrificiosDragones);
		this.sacrificiosDragones = 0;
		colocarEn(dragon, i);
	}
	
	private void colocarEn(CartaMonstruo monstruo, int i) {
		if(this.cartaCampo != null){
			this.cartaCampo.aplicarEfectoCartaIndividualDuenio(monstruo);
		}
		else{
			this.tablero.verificarCartaDeCampoLadoContrario(this,monstruo);
		}
		if(this.zonaDeMonstruos[i] != null){
			throw new PosicionOcupadaException();
		}
		this.seColocoMonstruo = true;
		this.zonaDeMonstruos[i] = monstruo;
		monstruo.cambiarAPosicionDeAtaque();
		monstruo.asignarDuenio(this.duenio);
	}
	
	public int obteneCantidadDeSacrificios() {
		return this.sacrificios;
	}

	public void cambiarAPosicionDeDefensaMonstruo(int i) {
		CartaMonstruo carta = (CartaMonstruo) this.zonaDeMonstruos[i];
		carta.cambiarAPosicionDeDefensa();
	}

	public void colocar(CartaMagica magica, int i) {
		if(this.zonaMagica[i] != null){
			throw new PosicionOcupadaException();
		}
		this.zonaMagica[i] = magica;
		magica.asignarDuenio(this.duenio);
	}

	public void colocar(CartaTrampa trampa, int i) {
		if(this.zonaMagica[i] != null){
			throw new PosicionOcupadaException();
		}
		this.zonaMagica[i] = trampa;
		trampa.asignarDuenio(this.duenio);
	}

	public void destruirCartaMonstruo(int i) {
		Carta carta = this.zonaDeMonstruos[i];
		this.zonaDeMonstruos[i] = null;
		this.cementerio.add(carta);
	}

	public boolean cementerioContiene(Carta unaCarta) {
		return this.cementerio.contains(unaCarta);
	}
	
	public void destruir(CartaMonstruo cartaMonstruo) {
		destruirEn(zonaDeMonstruos, cartaMonstruo);
	}
	
	public void destruir(CartaMagica cartaMagica) {
		destruirEn(zonaMagica, cartaMagica);
	}
	
	public void destruir(CartaTrampa cartaTrampa) {
		destruirEn(zonaMagica, cartaTrampa);
	}
	
	private void destruirEn(Carta[] zona, Carta unaCarta) {
		for (int i = 0; i < this.tamanio; i++) {
			if (zona[i] == unaCarta) {
				zona[i] = null;
				this.cementerio.add(unaCarta);
				return;
			}
		}
	}

	public void voltearCartaMagicaEnPosicion(int i) {
		CartaMagica magica = (CartaMagica) this.zonaMagica[i];
		magica.voltear();
	}
	
	public void voltearCartaMostruoEnPosicion(int i) {
		CartaMonstruo monstruo = (CartaMonstruo) this.zonaDeMonstruos[i];
		monstruo.voltear();
	}
	
	public void sacrificar(CartaMonstruo cartaMonstruo) {
		this.destruir(cartaMonstruo);
		(this.sacrificios)++;
	}
	
	public void sacrificar(DragonBlancoDeOjosAzules cartaMonstruo) {
		this.destruir(cartaMonstruo);
		(this.sacrificiosDragones)++;
		this.sacrificios++;
	}
	
	public Tablero obtenerTablero() {
		return this.tablero;
	}
	
	public Jugador obtenerJugador(){
		return this.duenio;
	}

	public void destruirTodosLosMonstruos() {
		
		for (CartaMonstruo monstruo: this.zonaDeMonstruos) {
			if (monstruo != null) {
				monstruo.destruir();
			}
		}
	}
	
	public void aumentarPuntosAtaque(int aumento){
		for (int i = 0; i < this.tamanio; i++) {
			if(zonaDeMonstruos[i] != null){
				this.zonaDeMonstruos[i].aumentarAtaque(aumento);
			}
		}
	}
	
	public void aumentarPuntosDefensa(int aumento){
		for (int i = 0; i < this.tamanio; i++) {
			if(zonaDeMonstruos[i] != null){
				this.zonaDeMonstruos[i].aumentarDefensa(aumento);
			}
		}
	}

	public Mazo obtenerMazo() {
		return this.mazo;
	}
	
	public void atacarConMonstruoEnPosicionAJugadorEnemigo(int posicionDeMiZona){
		CartaMonstruo cartaParaAtacar;
		cartaParaAtacar = this.zonaDeMonstruos[posicionDeMiZona];
		this.tablero.atacarJugadorEnemigo(this,cartaParaAtacar);
	}
	
	public void atacarConMonstruoEnPosicionAMonstruoEnPosicion(int posicionDeMiZona,int posicionZonaContrario){
		CartaMonstruo cartaParaAtacar;
		cartaParaAtacar = this.zonaDeMonstruos[posicionDeMiZona];
		this.tablero.atacarAEnemigoCon(this,cartaParaAtacar,posicionZonaContrario);
	}
	
	public void atacarMonstruoEnPosicionCon(int posicionMonstruoAtacado,CartaMonstruo cartaParaAtacar){
		boolean activoTrampa = false;
		CartaTrampa cartaMagica;
		CartaMonstruo cartaAtacada;
		if(this.zonaDeMonstruos[posicionMonstruoAtacado] == null){
			throw new AtacarMonstruoVacioException();
		}
		cartaAtacada = this.zonaDeMonstruos[posicionMonstruoAtacado];
		for(int i = 0; i < this.tamanio; i++){
			if(this.zonaMagica[i] != null){
				try{
					cartaMagica = (CartaTrampa) this.zonaMagica[i];
					if(cartaMagica.verificarCarta()){
						cartaMagica.activar(cartaAtacada,cartaParaAtacar);
						activoTrampa = true;
					} 	
				}catch(ClassCastException e) {}
			}
		}
		if(!activoTrampa){
			cartaParaAtacar.atacarA(cartaAtacada);
		}
	}
	
	public boolean estaMonstruo(CartaMonstruo monstruoBuscado, int i) {
		CartaMonstruo monstruo = this.zonaDeMonstruos[i];
		return monstruoBuscado == monstruo;
	}
	
	public void aplicarEfectoCampoIndividualEnemigo(CartaMonstruo monstruo){
		this.cartaCampo.aplicarEfectoCartaIndividualEnemigo(monstruo);
	}
	
	public void colocar(Carta unaCarta) {
		unaCarta.posicionarEn(this);
	}
	
	public void colocar(CartaMonstruo monstruo) {
		monstruo.encenderFlag();
		int pos = this.posicionDisponible(this.zonaDeMonstruos);
		this.colocar(monstruo, pos);
		sacarCartaDeMano(monstruo);
	}
	
	public void colocar(CartaMagica magica) {
		int pos = this.posicionDisponible(this.zonaMagica);
		this.colocar(magica, pos);
		sacarCartaDeMano(magica);
	}
	
	public void colocar(CartaTrampa trampa) {
		int pos = this.posicionDisponible(this.zonaMagica);
		this.colocar(trampa, pos);
		sacarCartaDeMano(trampa);
	}
	
	private int posicionDisponible(Carta[] zona) {
		for (int i = 0; i < this.tamanio; i++) {
			if (zona[i] == null) {
				return i;
			}
		}
		throw new NoHayEspecioEnElCampoException();
	}
	
	public void atacarJugador(CartaMonstruo cartaParaAtacar){
		boolean hayMonstruos = false;
		for (int i = 0; i < this.tamanio; i++) {
			if (this.zonaDeMonstruos[i] != null){
				hayMonstruos = true;
			}
		}
		if(!hayMonstruos){
			this.duenio.disminuirVidaEn(cartaParaAtacar.extraerPuntosAtaque());
		}
		else{
			throw new NoSePuedeAtacarDirectamenteException();
		}
	}
}
