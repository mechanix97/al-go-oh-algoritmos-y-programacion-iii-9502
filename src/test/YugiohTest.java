package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import excepciones.CantidadDeSacrificiosInvalidaException;
import modelo.CartaDeCampo;
import modelo.CartaMagica;
import modelo.CartaMonstruo;
import modelo.CartaTrampa;
import modelo.cartas.Wasteland;
import modelo.Jugador;
import modelo.Lado;
import modelo.Mano;
import modelo.Mazo;
import modelo.Tablero;
import modelo.Yugioh;
import modelo.cartas.AgujeroOscuro;
import modelo.cartas.BrazoDerechoDeExodia;
import modelo.cartas.BrazoIzquierdoDeExodia;
import modelo.cartas.CabezaDeExodia;
import modelo.cartas.CilindroMagico;
import modelo.cartas.DragonBlancoDeOjosAzules;
import modelo.cartas.DragonDefinitivoDeOjosAzules;
import modelo.cartas.Fisura;
import modelo.cartas.InsectoComeHombres;
import modelo.cartas.Jinzo;
import modelo.cartas.OllaDeLaCodicia;
import modelo.cartas.PiernaDerechaDeExodia;
import modelo.cartas.PiernaIzquierdaDeExodia;
import modelo.cartas.Reinforcements;
import modelo.cartas.Sogen;

class YugiohTest {

	@Test
	void testColorCartaMonstruoEnPosicionDeAtaque() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = tablero.obtenerLadoDe(jugador);
		
		CartaMonstruo monstruo = new CartaMonstruo("Huevo Monstruoso", 600, 900, 3);
		lado.colocar(monstruo, 0);
		
		// Por defecto coloca una carta monstruo en posicion de ataque
		assertTrue(monstruo.estaEnPosicionDeAtaque());
	}
	
	@Test
	void testColorCartaMonstruoEnPosicionDeDefensa() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = tablero.obtenerLadoDe(jugador);
		
		CartaMonstruo monstruo = new CartaMonstruo("Huevo Monstruoso", 600, 900, 3);
		lado.colocar(monstruo, 0);
		lado.cambiarAPosicionDeDefensaMonstruo(0);
		
		assertTrue(monstruo.estaEnPosicionDeDefensa());
	}	
	
	@Test
	void testColocarUnaCartaMagicaEnElCampoBocaAbajo() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = tablero.obtenerLadoDe(jugador);

		CartaMagica magica = new AgujeroOscuro();
		lado.colocar(magica, 0);

		// Por defecto se agregan las cartas magicas boca abajo
		assertTrue(magica.estaBocaAbajo());
	}
	
	@Test
	void testColocarUnaCartaTrampaEnElCampoBocaAbajo() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = tablero.obtenerLadoDe(jugador);

		CartaTrampa trampa = new CilindroMagico();
		lado.colocar(trampa, 0);

		// Por defecto se agregan las cartas trampa boca abajo 
		assertTrue(trampa.estaBocaAbajo());
	}
	
	@Test
	void testMandarUnaCartaAlCementerioYVerificarQueEsteAhi() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = tablero.obtenerLadoDe(jugador);

		CartaMonstruo monstruo = new CartaMonstruo("Huevo Monstruoso", 600, 900, 3);
		lado.colocar(monstruo, 0);
		lado.destruirCartaMonstruo(0);
		
		assertTrue(lado.cementerioContiene(monstruo));	
	}
	
	@Test
	void testAmbosMonstruosEnAtaquePeroMiMonstruoTieneMenosAtaqueQueElOponente() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		// yo soy el jugador de turno
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);

		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoDeTurno.colocar(amazon, 0);

		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoOponente.colocar(beautiful, 0);

		amazon.atacarA(beautiful);
		
		// Verifico que se destruyo amazon
		assertTrue(ladoDeTurno.cementerioContiene(amazon));
		
		// diferencia de ataque = 300
		// Se restan de jugadorDeTurno y queda 7700.
		assertEquals(7700, jugadorDeTurno.obtenerVida());
	}
	
	@Test
	void testAmbosMonstruosEnAtaquePeroMiMonstruoTieneMayorAtaqueQueElOponente() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);

		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoDeTurno.colocar(beautiful, 0);
		
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoOponente.colocar(amazon, 0);

		ladoDeTurno.atacarConMonstruoEnPosicionAMonstruoEnPosicion(0,0);
		
		// Verifico que se destruyo amazon
		assertTrue(ladoOponente.cementerioContiene(amazon));
		
		// diferencia de ataque = 300
		// Se restan de jugadorOponente y queda 6700.
		assertEquals(7700, jugadorOponente.obtenerVida());
	}
	
	@Test
	void testAmbosMonstruosEnAtaqueYConMismoAtaque() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);

		CartaMonstruo beautiful1 = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoDeTurno.colocar(beautiful1, 0);
		
		CartaMonstruo beautiful2 = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoOponente.colocar(beautiful2, 0);
		
		ladoDeTurno.atacarConMonstruoEnPosicionAMonstruoEnPosicion(0,0);
		
		// Verifico que se destruyeron ambas
		assertTrue(ladoDeTurno.cementerioContiene(beautiful1));
		assertTrue(ladoOponente.cementerioContiene(beautiful2));
		
		// diferencia de ataque = 0
		// Ninguno de los jugadores recibio danio
		assertEquals(8000, jugadorDeTurno.obtenerVida());
		assertEquals(8000, jugadorOponente.obtenerVida());
	}
	
	@Test
	void testMiMonstruoEnAtaqueYElOtroEnDefensaPeroMiAtaqueEsMayorQueSuDefensa() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		// yo soy el jugador uno
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);

		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoDeTurno.colocar(amazon, 0);

		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoOponente.colocar(beautiful, 0);
		ladoOponente.cambiarAPosicionDeDefensaMonstruo(0);

		ladoDeTurno.atacarConMonstruoEnPosicionAMonstruoEnPosicion(0,0);
		
		// Verifico que se destruyo beautiful
		assertTrue(ladoOponente.cementerioContiene(beautiful));
		
		// diferencia de puntos = 1300 - 800 = 500.
		// Pero jugadorOponente no recibe danio
		assertEquals(8000, jugadorOponente.obtenerVida());
	}
	
	@Test
	void testMiMonstruoEnAtaqueYElOtroEnDefensaPeroMiAtaqueEsMenorQueSuDefensa() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		// yo soy el jugador uno
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);

		CartaMonstruo huevo = new CartaMonstruo("Huevo Monstruoso", 1600, 800, 4);
		ladoDeTurno.colocar(huevo, 0);
		
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoOponente.colocar(amazon, 0);
		ladoOponente.cambiarAPosicionDeDefensaMonstruo(0);

		ladoDeTurno.atacarConMonstruoEnPosicionAMonstruoEnPosicion(0,0);
		
		// Verifico que no se destruyo huevo
		assertFalse(ladoDeTurno.cementerioContiene(huevo));
		
		// Pero jugadorDeTurno no recibe danio
		assertEquals(8000, jugadorDeTurno.obtenerVida());
	}
	
	@Test
	void testTodosSeDestruyenTrasActivarseAgujeroOscuro() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		// yo soy el jugador uno
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoDeTurno.colocar(beautiful, 0);
		
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoOponente.colocar(amazon, 0);
		
		CartaMagica agujeroOscuro = new AgujeroOscuro();
		ladoDeTurno.colocar(agujeroOscuro, 0);
		
		// No estoy seguro, en este modelo  primero se coloca una carta en 
		// la zona y luego se da las opciones de dar vuelta o no. Tambien podria ser posible
		// activar la carta desde la misma mano. Esta bien asi o es necesario un metodo 
		// colocarCartaYDarVuelta(carta) ? 
		ladoDeTurno.voltearCartaMagicaEnPosicion(0);
		
		// Verifico que los monstruos esten en el cementerio.
		assertTrue(ladoDeTurno.cementerioContiene(beautiful));
		assertTrue(ladoOponente.cementerioContiene(amazon));
		
		// Verifico que nadie recibio danio
		assertEquals(8000, jugadorDeTurno.obtenerVida());
		assertEquals(8000, jugadorOponente.obtenerVida());
	}

	/*
	@Test
	public void testSeBajaUnMonstruoDe5EstrellasAlCampoYTomaUnSacrificio() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoDeTurno.colocar(beautiful, 0);
		
		CartaMonstruo darkWitch = new CartaMonstruo("Dark Witch", 1800, 1700, 5);
		darkWitch.agregarSacrificio(beautiful);
		ladoDeTurno.colocar(darkWitch, 0);
		
		// verifico que esta darkWitch en la posicion 0
		assertTrue(ladoDeTurno.estaMonstruo(darkWitch, 0));
		
		// verifico que beautiful fue sacrificada
		assertTrue(ladoDeTurno.cementerioContiene(beautiful));
	}
	*/
	
	
	@Test
	public void testSeBajaUnMonstruoDe5EstrellasAlCampoYTomaUnSacrificio() {
		Yugioh yugioh = new Yugioh("Jugador 1", "Jugador 2");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		CartaMonstruo carta = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);

		lado.colocar(carta);
		lado.sacrificar(carta);
		
		boolean colocada = true;
		try { 
			lado.colocar(new CartaMonstruo("Dark Witch", 1800, 1700, 5));
		} catch (CantidadDeSacrificiosInvalidaException e) {
			colocada = false;
		}
		assertTrue(colocada);
	}
	

	@Test
	public void testSeBajaUnMonstruoDe7EstrellasAlCampoYTomaDosSacrificio() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoDeTurno.colocar(beautiful);
		
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoDeTurno.colocar(amazon);
		
		CartaMonstruo darkMagician = new CartaMonstruo("Dark Magician", 2500, 2100, 7);
		
		ladoDeTurno.sacrificar(beautiful);
		ladoDeTurno.sacrificar(amazon);
		
		ladoDeTurno.colocar(darkMagician, 2);
		
		// verifico que esta darkWitch en la posicion 0
		assertTrue(ladoDeTurno.estaMonstruo(darkMagician, 2));
		
		// verifico que beautiful fue sacrificada
		assertTrue(ladoDeTurno.cementerioContiene(beautiful));
		assertTrue(ladoDeTurno.cementerioContiene(amazon));
	}

	
	@Test
	void testAgregoyActivoCartaDeCampoWasteland(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoDeTurno.colocar(beautiful, 0);
		CartaMonstruo time = new CartaMonstruo("Time Wizard", 500, 400, 2);
		ladoOponente.colocar(time, 0);
		
		CartaDeCampo wasteland = new Wasteland();
		ladoDeTurno.colocarCartaDeCampo(wasteland);
		
		assertEquals(1800,beautiful.extraerPuntosAtaque());
		assertEquals(700,time.extraerPuntosDefensa());
	}
	
	@Test
	void testAgregoWastelandYLuegoCartaEnAmbosLados(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaDeCampo wasteland = new Wasteland();
		ladoDeTurno.colocarCartaDeCampo(wasteland);
		CartaMonstruo time = new CartaMonstruo("Time Wizard", 500, 400, 2);
		ladoDeTurno.colocar(time, 0);
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoOponente.colocar(beautiful, 0);
		
		assertEquals(1100,beautiful.extraerPuntosDefensa());
		assertEquals(700,time.extraerPuntosAtaque());
	}
	
	@Test
	void testAgregoyActivoCartaDeCampoSogen(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoDeTurno.colocar(beautiful, 0);
		CartaMonstruo time = new CartaMonstruo("Time Wizard", 500, 400, 2);
		ladoOponente.colocar(time, 0);
		
		CartaDeCampo sogen = new Sogen();
		ladoDeTurno.colocarCartaDeCampo(sogen);
		
		assertEquals(1300,beautiful.extraerPuntosDefensa());
		assertEquals(700,time.extraerPuntosAtaque());
	}
	
	@Test
	void testAgregoSogenYLuegoCartaEnAmbosLados(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaDeCampo sogen = new Sogen();
		ladoDeTurno.colocarCartaDeCampo(sogen);
		CartaMonstruo time = new CartaMonstruo("Time Wizard", 500, 400, 2);
		ladoDeTurno.colocar(time, 0);
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoOponente.colocar(beautiful, 0);
		
		assertEquals(1800,beautiful.extraerPuntosAtaque());
		assertEquals(900,time.extraerPuntosDefensa());
	}
	
	@Test
	void testAgregoJinzoYAtacoDirectamenteAlJugador(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);

		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoOponente.colocar(amazon, 0);
		CartaMonstruo jinzo = new Jinzo();
		ladoDeTurno.colocar(jinzo,0);
		
		ladoDeTurno.voltearCartaMostruoEnPosicion(0);
		
		//Jinzo #7 ataca directamente al jugador enemigo
		assertEquals(7500,jugadorOponente.obtenerVida());
	}
	
	@Test
	void testAgregoYActivoCartaMagicaFisura(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);

		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoOponente.colocar(amazon, 0);
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoOponente.colocar(beautiful, 1);
		CartaMagica fisura = new Fisura();
		ladoDeTurno.colocar(fisura,0);
		
		ladoDeTurno.voltearCartaMagicaEnPosicion(0);
		
		//Destruye la carta enemiga de menor ataque
		assertTrue(ladoOponente.cementerioContiene(amazon));
	}
	
	@Test
	void testActivarOllaDeLaCodiciaRobaDosCartasDelMazo() {
		
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		Mano mano = jugador.obtenerMano();
		Mazo mazo = lado.obtenerMazo();
		
		CartaMagica ollaDeLaCodicia = new OllaDeLaCodicia();
		lado.colocar(ollaDeLaCodicia, 0);
		lado.voltearCartaMagicaEnPosicion(0);
		
		// El mazo inicialmente tiene 40 cartas, 5 van a la mano y se roban 2.
		assertEquals(33, mazo.obtenerTamanio());
		// Se agregan 2 cartas a una mano de 5
		assertEquals(7, mano.obtenerTamanio());
	}
	
	@Test
	void testAtacoInsectoComeHombresYMeDestruyo(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaMonstruo insecto = new InsectoComeHombres();
		ladoDeTurno.colocar(insecto,0);
		ladoDeTurno.cambiarAPosicionDeDefensaMonstruo(0);
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoOponente.colocar(amazon,0);
		
		ladoOponente.atacarConMonstruoEnPosicionAMonstruoEnPosicion(0,0);
		
		//Al atacar al Insecto, ese mostruo se destruye
		assertTrue(ladoOponente.cementerioContiene(amazon));
		//Verifico que el Insecto queda en mi Zona
		assertTrue(ladoDeTurno.estaMonstruo(insecto,0));
	}
	
	@Test
	void testAtacoInsectoComeHombresDespuesDeActivarEfectoYSeDestruye(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaMonstruo insecto = new InsectoComeHombres();
		ladoDeTurno.colocar(insecto,0);
		ladoDeTurno.cambiarAPosicionDeDefensaMonstruo(0);
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoOponente.colocar(amazon,0);
		CartaMonstruo huevo = new CartaMonstruo("Huevo Monstruoso", 600, 900, 3);
		ladoOponente.colocar(huevo, 1);
		
		ladoOponente.atacarConMonstruoEnPosicionAMonstruoEnPosicion(0,0);
		ladoOponente.atacarConMonstruoEnPosicionAMonstruoEnPosicion(1,0);
		
		//Al atacar al Insecto, ese mostruo se destruye
		assertTrue(ladoOponente.cementerioContiene(amazon));
		//Verifico que el Insecto se destruyó luego del segundo ataque
		assertTrue(ladoDeTurno.cementerioContiene(insecto));
		//Verifico que el huevo sigue en el campo contrario
		assertTrue(ladoOponente.estaMonstruo(huevo,1));
	}
	
	
	@Test
	void testSeSacrificarTresDragonesBlancosParaBajarADragonDefinitivo() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();

		DragonDefinitivoDeOjosAzules dragonDefinitivo = new DragonDefinitivoDeOjosAzules();
		
		for (int i = 0; i < 3; i++) {
			DragonBlancoDeOjosAzules dragon = new DragonBlancoDeOjosAzules();
			CartaMonstruo harpie1 = new CartaMonstruo("Harpie Girl", 500, 500, 2);
			CartaMonstruo harpie2 = new CartaMonstruo("Harpie Girl", 500, 500, 2);
			lado.colocar(harpie1);
			lado.colocar(harpie2);
			lado.sacrificar(harpie1);
			lado.sacrificar(harpie2);
			lado.colocar(dragon);
			lado.sacrificar(dragon);
		}
		lado.colocar(dragonDefinitivo, 4);
		
		//Verifico que se haya podido bajar.
		assertTrue(lado.estaMonstruo(dragonDefinitivo, 4));
	}
	
	
	@Test
	void testColocoCilindroMagicoYAlAtacarActivoTrampa(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaMonstruo amazon = new CartaMonstruo("Amazon of the Seas", 1300, 1400, 4);
		ladoDeTurno.colocar(amazon, 0);
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoOponente.colocar(beautiful, 0);
		CartaTrampa cilindro = new CilindroMagico();
		ladoDeTurno.colocar(cilindro,0);
		
		ladoOponente.atacarConMonstruoEnPosicionAMonstruoEnPosicion(0,0);
		
		//Cilindro Magico niega el ataque y le disminuye la vida al jugador
		assertEquals(6400,jugadorOponente.obtenerVida());
		//Verifico que el Monstruo atacado sigue en el campo
		assertTrue(ladoDeTurno.estaMonstruo(amazon,0));
		//Verifico que el Monstruo que ataca siguen en el campo
		assertTrue(ladoOponente.estaMonstruo(beautiful,0));
	}
	
	@Test
	void testColocarReinforcementsYAlAtacarActivoTrampa(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaMonstruo agresorOscuro = new CartaMonstruo("Agresor Oscuro",1200,1200,2);
		ladoDeTurno.colocar(agresorOscuro,0);
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoOponente.colocar(beautiful, 0);
		CartaTrampa reinforcements = new Reinforcements();
		ladoDeTurno.colocar(reinforcements,0);
		
		ladoOponente.atacarConMonstruoEnPosicionAMonstruoEnPosicion(0,0);
		
		//Aumenta los puntos de ataque en 500
		assertEquals(1700,agresorOscuro.extraerPuntosAtaque());
		//Con el efecto de la trampa, al aumentar, se le descuenta vida
		assertEquals(7900,jugadorOponente.obtenerVida());
		//El monstruo que ataco, quedo destruido
		assertTrue(ladoOponente.cementerioContiene(beautiful));
	}
	
	@Test
	void testUsoReinforcementsLoDestruyoYDisminuyeLosPuntosDeAtaque(){
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Tablero tablero = yugioh.obtenerTablero();
		Jugador jugadorDeTurno = yugioh.obtenerJugadorDeTurno();
		Jugador jugadorOponente = yugioh.obtenerJugadorOponente();
		Lado ladoDeTurno = tablero.obtenerLadoDe(jugadorDeTurno);
		Lado ladoOponente = tablero.obtenerLadoDe(jugadorOponente);
		
		CartaMonstruo agresorOscuro = new CartaMonstruo("Agresor Oscuro",1200,1200,2);
		ladoDeTurno.colocar(agresorOscuro,0);
		CartaMonstruo beautiful = new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
		ladoOponente.colocar(beautiful, 0);
		CartaTrampa reinforcements = new Reinforcements();
		ladoDeTurno.colocar(reinforcements,0);
		
		ladoOponente.atacarConMonstruoEnPosicionAMonstruoEnPosicion(0,0);
		
		//Aumenta los puntos de ataque en 500
		assertEquals(1700,agresorOscuro.extraerPuntosAtaque());
		
		reinforcements.destruir();
		
		//Observo que la carta que tenía aumento, ahora no lo tiene
		assertEquals(1200,agresorOscuro.extraerPuntosAtaque());
		//Veo que reinforcements se encuentre en el cementeio
		assertTrue(ladoDeTurno.cementerioContiene(reinforcements));
	}
	
	@Test 
	void testJugadorExtraeTodasSusCartasPierdeYTerminaElJuego() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Lado lado = jugador.obtenerLado();
		Mazo mazo = lado.obtenerMazo();
		
		while (!mazo.estaVacio()) {
			mazo.robar();
		}
		
		assertTrue(jugador.esPerdedor());
		assertTrue(yugioh.estaTerminado());	
	}
	
	@Test
	void testJugadorTieneLasCincoPartesDeExodiaYGanaLaPartida() {
		Yugioh yugioh = new Yugioh("JugadorUno","JugadorDos");
		Jugador jugador = yugioh.obtenerJugadorDeTurno();
		Mano mano = jugador.obtenerMano();
		
		mano.agregar(new CabezaDeExodia());
		mano.agregar(new BrazoDerechoDeExodia());
		mano.agregar(new BrazoIzquierdoDeExodia());
		mano.agregar(new PiernaDerechaDeExodia());
		mano.agregar(new PiernaIzquierdaDeExodia());
		
		assertTrue(jugador.esGanador());
		assertTrue(yugioh.estaTerminado());
	}
}

