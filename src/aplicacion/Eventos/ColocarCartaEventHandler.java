package aplicacion.Eventos;

import java.io.File;

import aplicacion.App.LadoVista;
import excepciones.CantidadDeSacrificiosInvalidaException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import modelo.CartaDeCampo;
import modelo.Carta;
import modelo.Lado;

public class ColocarCartaEventHandler implements EventHandler<ActionEvent>{
	private Lado lado;
	private Carta carta;
	private Stage ventana;
	private LadoVista ladoVista;
	
	public ColocarCartaEventHandler(Lado lado,Carta carta,Stage ventana,LadoVista ladoVista) {
		this.lado = lado;
		this.carta = carta;
		this.ventana = ventana;
		this.ladoVista = ladoVista;
	}

	@Override
	public void handle(ActionEvent event) {
		try{
			this.lado.colocarCartaDeCampo((CartaDeCampo) this.carta);
			this.ladoVista.refresh();
			this.ventana.close();
		}
		catch(ClassCastException excepcionDeCarta){
			try{
				this.lado.colocar(this.carta);
				this.ladoVista.refresh();
				this.ventana.close();
			}
			catch(CantidadDeSacrificiosInvalidaException e){
				File song = new File( "src/aplicacion/Sonidos/error.mp3");
				Media media = new Media(song.toURI().toString());
			    MediaPlayer player = new MediaPlayer(media);
			    player.setAutoPlay(true);
				this.ladoVista.refresh();
				this.ventana.close();
			}
		}
	}
}
