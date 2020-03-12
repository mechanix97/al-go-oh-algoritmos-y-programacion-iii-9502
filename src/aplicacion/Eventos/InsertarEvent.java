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
import modelo.CartaMonstruo;
import modelo.Lado;

public class InsertarEvent implements EventHandler<ActionEvent>{
	
	private Carta carta;
	private Lado lado;
	private LadoVista ladoVista;
	private CartaVista cartaVista;
	

	public InsertarEvent(Carta unaCarta,Lado unLado,LadoVista ladoVista){
		this.carta = unaCarta;
		this.cartaVista = new CartaVista(unaCarta);
		this.lado = unLado;
		this.ladoVista = ladoVista;

	}

	@Override
	public void handle(ActionEvent event) {
		Image img = this.cartaVista.obtenerImagen(270,300);
		Stage ventanaCarta = new Stage();
		Button btn = new Button();
		
		VBox contenedor = new VBox();
		btn.setGraphic(new ImageView(img));
		
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setSpacing(10);
		
		Scene escena = new Scene(contenedor);
		
		BotonCancelarEventHandler botonCancelarEventHandler = new BotonCancelarEventHandler(ventanaCarta);
		btn.setOnAction(botonCancelarEventHandler);
		
		if((this.lado.seInsertoMonstruo() ) && (this.carta instanceof CartaMonstruo)){
			contenedor.getChildren().addAll(btn);
			
		}
		else{
			Button colocar= new Button("COLOCAR");
			colocar.setId("UnBoton");
			contenedor.getChildren().addAll(btn,colocar);
			
			ColocarCartaEventHandler eh = new ColocarCartaEventHandler(this.lado,this.carta,ventanaCarta,this.ladoVista);
			colocar.setOnAction(eh);
			contenedor.setPadding(new Insets(0,0,10,0));			
		}
		
		escena.getStylesheets().add("aplicacion/css/card-window.css");
		ventanaCarta.setScene(escena);		
		ventanaCarta.initStyle(StageStyle.UNDECORATED);
		ventanaCarta.show();	
		ventanaCarta.setAlwaysOnTop(true);
	}
}