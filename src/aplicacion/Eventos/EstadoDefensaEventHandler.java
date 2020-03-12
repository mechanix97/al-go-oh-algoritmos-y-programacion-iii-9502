package aplicacion.Eventos;

import aplicacion.App.LadoVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import modelo.Carta;
import modelo.CartaMonstruo;

public class EstadoDefensaEventHandler implements EventHandler<ActionEvent>{

	private Carta carta;
	private Stage ventana;
	private LadoVista ladoVista;
	
	public EstadoDefensaEventHandler(Carta unaCarta,LadoVista unLadoVista,Stage unaVentana){
		this.carta = unaCarta;
		this.ventana = unaVentana;
		this.ladoVista = unLadoVista;
	}
	
	@Override
	public void handle(ActionEvent event) {
		((CartaMonstruo) this.carta).cambiarAPosicionDeDefensa();
		this.ladoVista.refresh();
		this.ventana.close();
	}

}
