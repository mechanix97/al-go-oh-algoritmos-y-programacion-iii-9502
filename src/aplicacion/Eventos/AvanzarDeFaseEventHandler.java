package aplicacion.Eventos;

import aplicacion.App.TableroVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AvanzarDeFaseEventHandler implements EventHandler<ActionEvent> {
	private TableroVista tv;
	public AvanzarDeFaseEventHandler(TableroVista tv) {
		this.tv = tv;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		this.tv.avanzarFase();
		this.tv.refresh();
	}

}
