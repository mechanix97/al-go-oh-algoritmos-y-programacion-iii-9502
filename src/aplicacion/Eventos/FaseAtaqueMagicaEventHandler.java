package aplicacion.Eventos;

import aplicacion.App.CartaVista;
import aplicacion.App.LadoVista;
import aplicacion.Eventos.ActivarCartaMagicaEventHandler;
import aplicacion.Eventos.BotonCancelarEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Carta;

public class FaseAtaqueMagicaEventHandler implements EventHandler<ActionEvent>{
	
	private Carta carta;
	private CartaVista cartaVista;
	private LadoVista lado;
	
	public FaseAtaqueMagicaEventHandler(Carta unaCarta,LadoVista unLado){
		this.carta = unaCarta;
		this.cartaVista = new CartaVista(unaCarta);
		this.lado = unLado;
	}

	@Override
	public void handle(ActionEvent event) {
		Image img = this.cartaVista.obtenerImagen(270,300);
		Stage ventanaCarta = new Stage();
		Button btn = new Button();
		
		VBox contenedor = new VBox();
		Button activar = new Button("ACTIVAR");
		btn.setGraphic(new ImageView(img));
		
		contenedor.getChildren().addAll(btn,activar);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setSpacing(10);
		contenedor.setPadding(new Insets(0,0,10,0));
		Scene escena = new Scene(contenedor);
		
		BotonCancelarEventHandler botonCancelarEventHandler = new BotonCancelarEventHandler(ventanaCarta);
		btn.setOnAction(botonCancelarEventHandler);
		
		ActivarCartaMagicaEventHandler activarEvento = new ActivarCartaMagicaEventHandler(this.carta,this.lado,ventanaCarta);
		activar.setOnAction(activarEvento);
		
		activar.setId("UnBoton");
		escena.getStylesheets().add("aplicacion/css/card-window.css");
		ventanaCarta.setScene(escena);		
		ventanaCarta.initStyle(StageStyle.UNDECORATED);
		ventanaCarta.show();
		ventanaCarta.setAlwaysOnTop(true);
	}
	
}
