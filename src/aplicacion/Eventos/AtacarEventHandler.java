package aplicacion.Eventos;

import aplicacion.App.CartaVista;
import aplicacion.App.LadoVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.CartaMonstruo;
import modelo.Lado;

public class AtacarEventHandler implements EventHandler<ActionEvent>{
	
	private Lado lado;
	private CartaMonstruo carta;
	private CartaVista cartaVista;
	private Stage ventana;
	private LadoVista ladoVista;
	private int miPosicion;

	public AtacarEventHandler(Lado unLado,CartaMonstruo unaCarta,Stage ventana,LadoVista unLadoVista,int unaPosicion){
		this.lado = unLado;
		this.cartaVista = new CartaVista(unaCarta);
		this.carta = unaCarta;
		this.ventana = ventana;
		this.ladoVista = unLadoVista;
		this.miPosicion = unaPosicion;
	}
	
	@Override
	public void handle(ActionEvent event) {
		this.carta.ataco();
		this.ventana.close();
		Image img = this.cartaVista.obtenerImagen(270,300);
		Stage ventanaCarta = new Stage();
		Button btn = new Button();
		Label mensaje = new Label("Seleccione la Posicion de \n       la Carta a Atacar");
		VBox contenedor = new VBox();
		Button primera = new Button("PRIMERA");
		Button segunda = new Button("SEGUNDA");
		Button tercera = new Button("TERCERA");
		Button cuarta = new Button("CUARTA");
		Button quinta = new Button("QUINTA");
		btn.setGraphic(new ImageView(img));
		
		contenedor.getChildren().addAll(btn,mensaje,primera,segunda,tercera,cuarta,quinta);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setSpacing(10);
		contenedor.setPadding(new Insets(0,0,10,0));
		Scene escena = new Scene(contenedor);
		
		BotonCancelarEventHandler botonCancelarEventHandler = new BotonCancelarEventHandler(ventanaCarta);
		btn.setOnAction(botonCancelarEventHandler);
		
		AtacarContrarioEventHandler atacarPrimera = new AtacarContrarioEventHandler(this.lado,this.carta,ventanaCarta,this.ladoVista,this.miPosicion,0);
		primera.setOnAction(atacarPrimera);
		AtacarContrarioEventHandler atacarSegunda = new AtacarContrarioEventHandler(this.lado,this.carta,ventanaCarta,this.ladoVista,this.miPosicion,1);
		segunda.setOnAction(atacarSegunda);
		AtacarContrarioEventHandler atacarTercera = new AtacarContrarioEventHandler(this.lado,this.carta,ventanaCarta,this.ladoVista,this.miPosicion,2);
		tercera.setOnAction(atacarTercera);
		AtacarContrarioEventHandler atacarCuarta = new AtacarContrarioEventHandler(this.lado,this.carta,ventanaCarta,this.ladoVista,this.miPosicion,3);
		cuarta.setOnAction(atacarCuarta);
		AtacarContrarioEventHandler atacarQuinta = new AtacarContrarioEventHandler(this.lado,this.carta,ventanaCarta,this.ladoVista,this.miPosicion,4);
		quinta.setOnAction(atacarQuinta);
		
		primera.setId("UnBoton");
		segunda.setId("UnBoton");
		tercera.setId("UnBoton");
		cuarta.setId("UnBoton");
		quinta.setId("UnBoton");
		escena.getStylesheets().add("aplicacion/css/card-window.css");
		ventanaCarta.setScene(escena);		
		ventanaCarta.initStyle(StageStyle.UNDECORATED);
		ventanaCarta.show();
		ventanaCarta.setAlwaysOnTop(true);
	}

}
