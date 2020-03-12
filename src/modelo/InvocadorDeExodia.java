package modelo;

import java.util.Collection;

public class InvocadorDeExodia {
	
	private boolean estaCabezaDeExodia;
	private boolean estaBrazoDerechoDeExodia;
	private boolean estaBrazoIzquierdoDeExodia;
	private boolean estaPiernaDerechaDeExodia;
	private boolean estaPiernaIzquierdaDeExodia;
	
	public InvocadorDeExodia() {
		this.estaCabezaDeExodia = false;
		this.estaBrazoDerechoDeExodia = false;
		this.estaBrazoIzquierdoDeExodia = false;
		this.estaPiernaDerechaDeExodia = false;
		this.estaPiernaIzquierdaDeExodia = false;
	}
		
	public boolean exodiaEstaInvocado(Collection<Carta> coleccion) {
		
		for (Carta carta: coleccion) {
			carta.permitirInvocador(this);
		}
		
		return this.seEncuentraExodia();
	}
	
	public void invocarCabezaDeExodia() {
		this.estaCabezaDeExodia = true;
	}
	
	public void invocarBrazoDerechoDeExodia() {
		this.estaBrazoDerechoDeExodia = true;
	}
	
	public void invocarBrazoIzquierdoDeExodia() {
		this.estaBrazoIzquierdoDeExodia = true;
	}
	
	public void invocarPiernaDerechaDeExodia() {
		this.estaPiernaDerechaDeExodia = true; 
	}
	
	public void invocarPiernaIzquierdaDeExodia() {
		this.estaPiernaIzquierdaDeExodia = true;
	}
	
	private boolean seEncuentraExodia() {
		return this.estaCabezaDeExodia
			   && this.estaBrazoDerechoDeExodia
			   && this.estaBrazoIzquierdoDeExodia
			   && this.estaPiernaDerechaDeExodia
			   && this.estaPiernaIzquierdaDeExodia;
	}
	
	
	
}
