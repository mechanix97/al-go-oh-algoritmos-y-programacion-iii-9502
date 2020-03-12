package aplicacion.Eventos;

import java.io.File;
import aplicacion.App.LadoVista;
import excepciones.AtacarMonstruoVacioException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import modelo.Carta;
import modelo.Lado;

public class AtacarContrarioEventHandler implements EventHandler<ActionEvent>{
	
	private Lado lado;
	private Stage ventana;
	private LadoVista ladoVista;
	private int miPosicion;
	private int posicionContrario;

	public AtacarContrarioEventHandler(Lado unLado,Carta unaCarta,Stage ventana,LadoVista unLadoVista,int unaPosicion,int otraPosicion){
		this.lado = unLado;
		this.ventana = ventana;
		this.ladoVista = unLadoVista;
		this.miPosicion = unaPosicion;
		this.posicionContrario = otraPosicion;
	}

	@Override
	public void handle(ActionEvent event) {
		try{
			this.lado.atacarConMonstruoEnPosicionAMonstruoEnPosicion(this.miPosicion,this.posicionContrario);
			this.ladoVista.refresh();
			this.ventana.close();
		}catch(AtacarMonstruoVacioException e){
			File song = new File( "src/aplicacion/Sonidos/error.mp3");
			Media media = new Media(song.toURI().toString());
		    MediaPlayer player = new MediaPlayer(media);
		    player.setAutoPlay(true);
			this.ladoVista.refresh();
			this.ventana.close();
		}
	}
	
}
