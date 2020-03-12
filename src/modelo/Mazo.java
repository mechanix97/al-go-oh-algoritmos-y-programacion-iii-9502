package modelo;

import java.util.Collections;
import java.util.Stack;

public class Mazo {

	private Stack<Carta> pila;
	
	public Mazo() {
		this.pila = new Stack<Carta>();
		

		
		for (int i = 0; i < 40; i++) {
			this.pila.push(FabricaDeCartas.crearCartaAleatoria());
		}
				
		Collections.shuffle(this.pila);
	}
	
	public int obtenerTamanio() {
		return this.pila.size();
	}

	public Carta robar() {
		return this.pila.pop();
	}

	public boolean estaVacio() {
		return this.pila.isEmpty();
	}
}
