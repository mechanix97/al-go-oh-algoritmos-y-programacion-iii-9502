package aplicacion.Eventos;

import aplicacion.App.LadoVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import modelo.Carta;
import modelo.CartaDeCampo;
import modelo.CartaMagica;
import modelo.CartaMonstruo;
import modelo.CartaTrampa;
import modelo.Lado;

public class CambiarEventMons implements EventHandler<ActionEvent>{
	
	private Carta carta;
	private LadoVista ladoVista;
	private Lado lado;
	private Stage escenario;
	private int posicion;
	
	public CambiarEventMons(Carta unaCarta,Stage otroEscenario,Lado unLado,LadoVista unLadoVista,int pos){
		this.carta = unaCarta;
		this.lado = unLado;
		this.ladoVista = unLadoVista;
		this.posicion = pos;
		this.escenario = otroEscenario;
	}

	@Override
	public void handle(ActionEvent event) {
		this.escenario.close();
		try{
			this.lado.colocar((CartaMonstruo)this.carta,this.posicion-1);
			this.ladoVista.refresh();
		}
		catch(ClassCastException excepcionMonstruo){
			try{
				this.lado.colocar((CartaMagica)this.carta,this.posicion-1);
				this.ladoVista.refresh();
			}
			catch(ClassCastException excepcionMagica){
				try {
					this.lado.colocar((CartaTrampa)this.carta,this.posicion-1);
					this.ladoVista.refresh();
				}
				catch(ClassCastException excepcionTrampa){
					this.lado.colocarCartaDeCampo((CartaDeCampo)this.carta);
					this.ladoVista.refresh();
				}
			}
		}
	}
}
