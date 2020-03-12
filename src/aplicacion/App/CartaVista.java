package aplicacion.App;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import modelo.Carta;

public class CartaVista {


	protected Carta carta;
	protected Image imagenCarta;
	protected String imagePath;
	
	public CartaVista(Carta unaCarta){
		this.carta = unaCarta;
		this.imagePath ="aplicacion/Imagenes/cartas/"+String.valueOf(this.carta.obtenerId())+".png";
	}
	
	public Button obtenerBoton(int ancho, int largo){
		Button btn = new Button();
		btn.setGraphic(new ImageView(new Image(this.imagePath,ancho, largo, false, false)));
		return btn;
	}
	
	public Image obtenerImagen(int ancho, int largo){
		return new Image(this.imagePath,ancho, largo, false, false);
	}
	
	public Carta obtenerCarta(){
		return this.carta;
	}
}
