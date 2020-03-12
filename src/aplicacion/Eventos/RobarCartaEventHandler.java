package aplicacion.Eventos;

import aplicacion.App.LadoVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.Lado;

public class RobarCartaEventHandler  implements EventHandler<ActionEvent>{
	private Lado lado;
	private LadoVista ladoVista;
	
	public RobarCartaEventHandler(Lado lado,LadoVista ladoVista) {
		this.lado = lado;
		this.ladoVista = ladoVista;
	}

	@Override
	public void handle(ActionEvent arg0) {
		
		if( this.lado.cantitadDeCartasEnMano() < 7 && this.lado.cantidadDeCartasEnMazo() > 0 && (this.ladoVista.seRoboCarta())==false) {
			this.ladoVista.roboCarta();
			this.lado.robarCartaDelMazo();
			this.ladoVista.refresh();
		}
		else {
			this.ladoVista.refresh();
		}
	}
}
