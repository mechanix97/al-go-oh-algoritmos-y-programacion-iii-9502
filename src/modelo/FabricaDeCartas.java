package modelo;

import java.util.Random;

import excepciones.CodigoDeCartaInvalidoException;
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
import modelo.cartas.Wasteland;

public abstract class FabricaDeCartas {

	public static Carta crearCarta(int codigo) {
	
		switch (codigo) {
			case 0: return new AgujeroOscuro(); 
			case 1: return new BrazoDerechoDeExodia(); 
			case 2: return new BrazoIzquierdoDeExodia();
			case 3: return new CabezaDeExodia();
			case 4: return new CilindroMagico();
			case 5: return new DragonBlancoDeOjosAzules();
			case 6: return new DragonDefinitivoDeOjosAzules();
			case 7: return new Fisura();
			case 8: return new InsectoComeHombres();
			case 9: return new Jinzo();
			case 10: return new OllaDeLaCodicia();
			case 11: return new PiernaDerechaDeExodia();
			case 12: return new PiernaIzquierdaDeExodia();
			case 13: return new Reinforcements();
			case 14: return new Sogen();
			case 15: return new Wasteland();
			case 16: return new CartaMonstruo("Beautiful Headhuntress", 1600, 800, 4);
			case 17: return new CartaMonstruo("Amazon of the seas", 1300, 1400, 4);
			case 18: return new CartaMonstruo("Crowned by the world chalice", 0, 2100, 2);
			case 19: return new CartaMonstruo("Harpie girl", 500, 500, 2);
			case 20: return new CartaMonstruo("Maiden of the moonlight", 1500, 1300, 4);
		}
		throw new CodigoDeCartaInvalidoException();
	}
	
	public static Carta crearCartaAleatoria() {
		Random rand = new Random();
		return crearCarta(rand.nextInt(21));
	}

}
