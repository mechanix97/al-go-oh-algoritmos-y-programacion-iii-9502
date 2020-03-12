package aplicacion.Eventos;

import java.io.File;

import aplicacion.App.LadoVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import modelo.CartaMonstruo;

public class ActivarCartaMonstruoEventHandler implements EventHandler<ActionEvent>{
	
	private CartaMonstruo carta;
	private LadoVista lado;
	private Stage ventana;
	
	public ActivarCartaMonstruoEventHandler(CartaMonstruo unaCarta,LadoVista unLado,Stage unaVentana){
		this.carta = unaCarta;
		this.lado = unLado;
		this.ventana = unaVentana;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		if(this.carta.estaBocaAbajo()){
			this.carta.voltear();
			this.ventana.close();
			this.lado.refresh();
		}
		else{
			File song = new File( "src/aplicacion/Sonidos/error.mp3");
			Media media = new Media(song.toURI().toString());
		    MediaPlayer player = new MediaPlayer(media);
		    player.setAutoPlay(true);
			this.lado.refresh();
			this.ventana.close();
		}
	}

}
