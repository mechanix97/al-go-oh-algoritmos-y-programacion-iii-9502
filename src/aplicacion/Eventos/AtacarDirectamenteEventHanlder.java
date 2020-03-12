package aplicacion.Eventos;

import java.io.File;
import aplicacion.App.LadoVista;
import excepciones.NoSePuedeAtacarDirectamenteException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import modelo.CartaMonstruo;
import modelo.Lado;

public class AtacarDirectamenteEventHanlder implements EventHandler<ActionEvent>{
	
	private Lado lado;
	private CartaMonstruo carta;
	private Stage ventana;
	private LadoVista ladoVista;
	private int miPosicion;

	public AtacarDirectamenteEventHanlder(Lado unLado,CartaMonstruo unaCarta,Stage ventana,LadoVista unLadoVista,int unaPosicion){
		this.lado = unLado;
		this.carta = unaCarta;
		this.ventana = ventana;
		this.ladoVista = unLadoVista;
		this.miPosicion = unaPosicion;
	}

	@Override
	public void handle(ActionEvent arg0) {
		try{
			this.lado.atacarConMonstruoEnPosicionAJugadorEnemigo(this.miPosicion);
			this.carta.ataco();
			this.ladoVista.refresh();
			this.ventana.close();
		}
		catch(NoSePuedeAtacarDirectamenteException e){
			File song = new File( "src/aplicacion/Sonidos/error.mp3");
			Media media = new Media(song.toURI().toString());
		    MediaPlayer player = new MediaPlayer(media);
		    player.setAutoPlay(true);
			this.ladoVista.refresh();
			this.ventana.close();
		}
	}
	
	
}
