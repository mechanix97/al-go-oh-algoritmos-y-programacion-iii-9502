package aplicacion.Eventos;

import aplicacion.App.Aplicacion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BotonComenzarEventHandler implements EventHandler<ActionEvent> {
	private Aplicacion app;
	
	public BotonComenzarEventHandler(Aplicacion app) {
		this.app = app;
	}
	
	
	@Override
	public void handle(ActionEvent arg0) {
		this.app.ingresarNombre();
	}
}
