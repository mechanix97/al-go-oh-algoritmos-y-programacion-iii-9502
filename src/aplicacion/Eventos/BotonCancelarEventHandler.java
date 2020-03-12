package aplicacion.Eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class BotonCancelarEventHandler implements EventHandler<ActionEvent>{
	
	private Stage ventana;
	
	public BotonCancelarEventHandler(Stage ventanaSalir){
		this.ventana = ventanaSalir;
	}

	@Override
	public void handle(ActionEvent arg0) {
		ventana.close();
	}

}
