package modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Carta {

	protected String nombre;
	private int id;
	protected Jugador duenio;
	protected boolean bocaArriba;
	
	public Carta(String nombre) {
		this.nombre = nombre;
		this.bocaArriba = false;
		this.id=obtenerId(this.nombre);
	}
	
	public int obtenerId(String nombre) {
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream("id.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;

		
		try {
			while ((strLine = br.readLine()) != null)   {
				
				String[] parts = strLine.split("-");
				
				if(nombre.equals(parts[0])){					
					br.close();
					return Integer.parseInt(parts[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return -1;	
	}
	
	public int obtenerId() {
		return this.id;
	}
	
	public String extraerNombre(){
		return this.nombre;
	}	
	
	public boolean estaBocaAbajo() {
		return !this.bocaArriba;
	}
	
	public void voltear() {
		if (!bocaArriba) {
			bocaArriba = true;
		}
		this.activar();
	}
	
	public void asignarDuenio(Jugador duenio) {
		this.duenio = duenio;
	}
	
	public void activar() {}

	public void permitirInvocador(InvocadorDeExodia invocadorDeExodia) {}
	
	public String obtenerNombre() {
		return this.nombre;
	}
	
	public boolean esReinforcements(){
		if(this.nombre == "Reinforcements"){
			return true;
		}
		return false;
	}

	public abstract void posicionarEn(Lado lado);
}
