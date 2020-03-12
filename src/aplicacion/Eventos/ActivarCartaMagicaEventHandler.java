package aplicacion.Eventos;

import aplicacion.App.LadoVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import modelo.Carta;

public class ActivarCartaMagicaEventHandler implements EventHandler<ActionEvent>{

	private Carta carta;
	private LadoVista lado;
	private Stage ventana;
	
	public ActivarCartaMagicaEventHandler(Carta unaCarta,LadoVista unLado,Stage unaVentana){
		this.carta = unaCarta;
		this.lado = unLado;
		this.ventana = unaVentana;
	}
	
	@Override
	public void handle(ActionEvent event) {
		this.carta.voltear();
		this.ventana.close();
		this.lado.refresh();
	}

}
