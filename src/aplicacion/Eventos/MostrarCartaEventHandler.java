package aplicacion.Eventos;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import aplicacion.App.CartaVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.StageStyle;
import modelo.Carta;

public class MostrarCartaEventHandler implements EventHandler<ActionEvent>{
	private CartaVista cartaVista;
	private Carta carta;
	
	public MostrarCartaEventHandler(Carta carta) {
		this.cartaVista = new CartaVista(carta);
		this.carta = carta;
	}

	@Override
	public void handle(ActionEvent arg0) {
		Image img = this.cartaVista.obtenerImagen(270,300);
		Stage ventanaCarta = new Stage();
		Button btn = new Button();
		
		VBox contenedor = new VBox();
		Label lbl = new Label();
		btn.setGraphic(new ImageView(img));
		
		lbl.setText(this.carta.obtenerNombre());
		
		
		contenedor.getChildren().addAll(btn,lbl);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setSpacing(10);
		contenedor.setPadding(new Insets(0,0,10,0));
		Scene escena = new Scene(contenedor);
		
		BotonCancelarEventHandler botonCancelarEventHandler = new BotonCancelarEventHandler(ventanaCarta);
		btn.setOnAction(botonCancelarEventHandler);
		
		escena.getStylesheets().add("aplicacion/css/card-window.css");
		ventanaCarta.setScene(escena);		
		ventanaCarta.initStyle(StageStyle.UNDECORATED);
		ventanaCarta.show();
		ventanaCarta.setAlwaysOnTop(true);
	}
}
