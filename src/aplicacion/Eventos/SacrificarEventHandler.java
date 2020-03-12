package aplicacion.Eventos;

import aplicacion.App.LadoVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import modelo.Carta;
import modelo.CartaMonstruo;
import modelo.Lado;

public class SacrificarEventHandler implements EventHandler<ActionEvent>{
	
	private Lado lado;
	private Carta carta;
	private Stage ventanaCarta;
	private LadoVista ladoVista;

	public SacrificarEventHandler(Lado unLado, Carta unaCarta, Stage unaVentanaCarta, LadoVista unLadoVista) {
		this.lado = unLado;
		this.carta = unaCarta;
		this.ventanaCarta = unaVentanaCarta;
		this.ladoVista = unLadoVista;
	}

	@Override
	public void handle(ActionEvent event) {
		this.lado.sacrificar((CartaMonstruo) this.carta);
		this.ladoVista.refresh();
		this.ventanaCarta.close();
	}
}
