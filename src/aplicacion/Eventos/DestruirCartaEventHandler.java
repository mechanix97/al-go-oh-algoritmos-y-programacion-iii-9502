package aplicacion.Eventos;

import aplicacion.App.CartaVista;
import aplicacion.App.LadoVista;
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
import modelo.Lado;

public class DestruirCartaEventHandler implements EventHandler<ActionEvent>{

	private Lado lado;
	private Carta carta;
	private LadoVista ladoVista;
	private CartaVista cartaVista;
	
	public DestruirCartaEventHandler(Carta unaCarta,LadoVista ladoVista,Lado lado) {
		this.lado = lado;
		this.cartaVista = new CartaVista(unaCarta);
		this.carta = unaCarta;
		this.ladoVista = ladoVista;
	}
	
	@Override
	public void handle(ActionEvent event) {
		Image img = this.cartaVista.obtenerImagen(270,300);
		Stage ventanaCarta = new Stage();
		Button btn = new Button();
		
		VBox contenedor = new VBox();
		Button sacrificar = new Button("SACRIFICAR");
		btn.setGraphic(new ImageView(img));
		
		contenedor.getChildren().addAll(btn,sacrificar);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setSpacing(10);
		contenedor.setPadding(new Insets(0,0,10,0));
		Scene escena = new Scene(contenedor);
		
		BotonCancelarEventHandler botonCancelarEventHandler = new BotonCancelarEventHandler(ventanaCarta);
		btn.setOnAction(botonCancelarEventHandler);
		
		SacrificarEventHandler eh = new SacrificarEventHandler(this.lado,this.carta,ventanaCarta,this.ladoVista);
		sacrificar.setOnAction(eh);
		
		sacrificar.setId("UnBoton");
		escena.getStylesheets().add("aplicacion/css/card-window.css");
		ventanaCarta.setScene(escena);		
		ventanaCarta.initStyle(StageStyle.UNDECORATED);
		ventanaCarta.show();
		ventanaCarta.setAlwaysOnTop(true);
	}

}
