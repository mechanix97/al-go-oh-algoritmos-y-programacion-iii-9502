package modelo;

import java.util.ArrayList;


public class Mano {
	
	private ArrayList<Carta> coleccionDeCartas;
	
	public Mano() {
		this.coleccionDeCartas = new ArrayList<Carta>();
	}
	
	public void agregar(Carta unaCarta) {
		this.coleccionDeCartas.add(unaCarta);
	}
	
	public void sacarCarta(Carta carta) {
		for(int i =0; i < this.coleccionDeCartas.size();i++) {
			if( this.coleccionDeCartas.get(i) == carta) {
				this.coleccionDeCartas.remove(i);
			}
		}
	}

	public int obtenerTamanio() {
		return this.coleccionDeCartas.size();
	}

	public boolean contieneExodia() {
		InvocadorDeExodia invocador = new InvocadorDeExodia();
		return invocador.exodiaEstaInvocado(this.coleccionDeCartas);
			  
	}
	
	public Carta obtenerCarta(int i) {		
		return this.coleccionDeCartas.remove(i);
	}
	
	public ArrayList<Carta> obtenerCartas() {
		return this.coleccionDeCartas;
	}

}
