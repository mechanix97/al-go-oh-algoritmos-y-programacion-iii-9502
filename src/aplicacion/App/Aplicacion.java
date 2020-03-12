package aplicacion.App;

import aplicacion.Eventos.BotonSalirEventHandler;
import aplicacion.Eventos.PantallaNombresBotonAtrasEventHandler;
import aplicacion.Eventos.PantallaNombresEnterEventHandler;

import java.io.File;
import java.util.Random;

import aplicacion.Eventos.BotonAceptarEventHandler;
import aplicacion.Eventos.BotonAceptarNombresEventHandler;
import aplicacion.Eventos.BotonComenzarEventHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import modelo.Jugador;
import modelo.Yugioh;
import javafx.stage.StageStyle;

public class Aplicacion extends Application{

	private int resolucionAlto = 700;
	private int resolucionAncho = 1280;
	private Stage stage;
	private Yugioh yugioh;
	private TableroVista tablero;
	private boolean firstRun = true;
	
	public static void main(String []args){
		launch(args);
	}
	
	@Override
	public void start(Stage stage){	
		this.stage = stage;
		
		stage.setTitle("Al-Go-Oh!- FIUBA");
		StackPane layout = new StackPane();
		Label titulo = new Label();
		Label integrantes = new Label();
				
		Button comenzar = new Button("Comenzar");
		Button salir = new Button("Salir");
		VBox contenedorVertical = new VBox();
		
		comenzar.defaultButtonProperty().bind(comenzar.focusedProperty());
		salir.defaultButtonProperty().bind(salir.focusedProperty());
		

		titulo.setText("Al-Go-Oh!\n" + "Trabajo Practico Nro. 2 \n" +  "Algoritmos y Programacion III - FIUBA");
		integrantes.setText("Integrantes: \n" + "\t- Bravo Arroyo, Victor Manuel\n" + "\t- Botter Brun, Juan Bautista\n" + "\t- Calvani, Sergio Alejandro\n" + "\t- Rack, Lucas Alexis");
		
		StackPane.setAlignment(titulo,Pos.TOP_LEFT);
		StackPane.setAlignment(integrantes,Pos.BOTTOM_LEFT);
		

		BotonSalirEventHandler botonSalirEventHandler = new BotonSalirEventHandler();
		salir.setOnAction(botonSalirEventHandler);
		
		BotonComenzarEventHandler botonComenzarEventHandler = new BotonComenzarEventHandler(this);
		comenzar.setOnAction(botonComenzarEventHandler);
		
		contenedorVertical.getChildren().addAll(comenzar,salir);
		contenedorVertical.setSpacing(20);
		contenedorVertical.setPadding(new Insets(240,0,0,50));
		
		layout.setPrefWidth(this.resolucionAncho);
		layout.setPrefHeight(this.resolucionAlto);		
		layout.getChildren().addAll(titulo,integrantes,contenedorVertical);
		layout.setPadding(new Insets(10));
		
		
		Scene escena = new Scene(layout);
		this.stage.setFullScreen(false);
		escena.getStylesheets().add("aplicacion/css/start-screen.css");
		this.stage.setScene(escena);
		
		if(this.firstRun == true) {
			this.stage.initStyle(StageStyle.UTILITY);
			this.stage.resizableProperty().setValue(Boolean.FALSE);
			this.firstRun = false;
			Random rand = new Random();
			int i = rand.nextInt(2);
			File song; 
			if(i==0) {
				song= new File( "src/aplicacion/Sonidos/main_song1.mp3");
			}
			else {
				song = new File( "src/aplicacion/Sonidos/main_song2.mp3");
			}
	        Media media = new Media(song.toURI().toString());
	        MediaPlayer player = new MediaPlayer(media);
	        player.setAutoPlay(true);
		}
		
		
		this.stage.show();
		
		comenzar.requestFocus();
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
	public void ingresarNombre() {
		StackPane layout = new StackPane();
		Button botonAceptar = new Button();
		Label etiquetaIngreseNombres = new Label();
		Label etiquetaJugador1 = new Label();
		TextField textoJugador1 = new TextField();
		Label etiquetaJugador2 = new Label();
		TextField textoJugador2 = new TextField();
		Label etiquetaError= new Label();
		Button botonAtras = new Button("Atras");
		
		//LABEL INGRESE NOMBRES
        etiquetaIngreseNombres.setText("Ingrese Nombres de los Jugadores");
        etiquetaIngreseNombres.setId("ingrese-label");

        //jugador 1        
        etiquetaJugador1.setText("Jugador 1:");
        etiquetaJugador1.setId("nombre-label");      
                
        textoJugador1.setPromptText("Jugador 1");           
        
        //jugador 2       
        etiquetaJugador2.setText("Jugador 2:");
        etiquetaJugador2.setId("nombre-label");
                
        textoJugador2.setPromptText("Jugador 2");
        
        //label error
        etiquetaError.setText("");
        etiquetaError.setId("error-label");  
        
        //boton
        botonAceptar.setText("Aceptar");
        
        //VBOX1
        VBox contenedorJugador1 = new VBox(etiquetaJugador1,textoJugador1);
        contenedorJugador1.setSpacing(5);        
        
        //VBOX 2
        VBox contenedorJugador2 = new VBox(etiquetaJugador2,textoJugador2);
        contenedorJugador2.setSpacing(5);
        
        //HBOX
    	Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        
        HBox contenedorBoton = new HBox(etiquetaError, region1, botonAceptar);
        contenedorBoton.setSpacing(0);
        
        //VBOX PRINCIPAL
        VBox contenedorPrincipal = new VBox(etiquetaIngreseNombres,contenedorJugador1, contenedorJugador2,contenedorBoton);
        contenedorPrincipal.setSpacing(15);
        contenedorPrincipal.setPadding(new Insets(20));
        contenedorPrincipal.setMaxHeight(200);
        contenedorPrincipal.setMaxWidth(350);
        contenedorPrincipal.setId("hbox");
        
        layout.getChildren().addAll(contenedorPrincipal,botonAtras);
        layout.setPadding(new Insets(10));
        
        //eventos
        BotonAceptarNombresEventHandler botonAceptarEventHandler = new BotonAceptarNombresEventHandler(textoJugador1,textoJugador2,etiquetaError,this);
        botonAceptar.setOnAction(botonAceptarEventHandler);       
        botonAceptar.defaultButtonProperty().bind(botonAceptar.focusedProperty());
        PantallaNombresBotonAtrasEventHandler eh = new PantallaNombresBotonAtrasEventHandler(this);
        botonAtras.setOnAction(eh);       
        botonAtras.defaultButtonProperty().bind(botonAtras.focusedProperty());
        PantallaNombresEnterEventHandler textoEventHandler = new PantallaNombresEnterEventHandler(botonAceptar);
        textoJugador1.setOnKeyPressed(textoEventHandler);
        textoJugador2.setOnKeyPressed(textoEventHandler);
        
		StackPane.setAlignment(layout, Pos.CENTER );
		StackPane.setAlignment(botonAtras,Pos.BOTTOM_LEFT);
		
		layout.setPrefWidth(this.resolucionAncho);
		layout.setPrefHeight(this.resolucionAlto);	
		
		Scene escena = new Scene(layout);
		escena.getStylesheets().add("aplicacion/css/name-screen.css");
		this.stage.setScene(escena);
	}
                                                            
	public void iniciarJuego(String nombre1,String nombre2) {
		this.yugioh = new Yugioh(nombre1,nombre2);
		this.tablero = new TableroVista(this.yugioh,this);		
		this.stage.setTitle("AlgoOH");
		refresh();
	}
	
	public void refresh() {
		Scene escena = this.tablero.juego();
		
		escena.getStylesheets().add("aplicacion/css/game-screen.css");
		this.stage.setScene(escena);
		this.stage.show();
	}
	
	public void juegoTerminadoConGanador(Jugador ganador){
		Stage ultimoEscenario = new Stage();
		File song;
		Button aceptar = new Button("Aceptar");
		Label mensaje = new Label();
		mensaje.setText("¡Felicitaciones " + ganador.obtenerNombre() + " sos el ganador!");
		
		BotonAceptarEventHandler salir = new BotonAceptarEventHandler();
		aceptar.setOnAction(salir);
		
		
		mensaje.setAlignment(Pos.CENTER);
		VBox contenedor = new VBox(mensaje,aceptar);
		contenedor.setAlignment(Pos.BOTTOM_CENTER);
		contenedor.setSpacing(50);
		contenedor.setPadding(new Insets(0,0,60,0));
		song= new File( "src/aplicacion/Sonidos/ganador.mp3");
		Media media = new Media(song.toURI().toString());
	    MediaPlayer player = new MediaPlayer(media);
	    player.setAutoPlay(true);
		Scene escena = new Scene(contenedor,300,350);
		escena.getStylesheets().add("aplicacion/css/winner-window.css");
		ultimoEscenario.initStyle(StageStyle.UNDECORATED);
		ultimoEscenario.setScene(escena);
		ultimoEscenario.show();
		aceptar.requestFocus();
		ultimoEscenario.setAlwaysOnTop(true);
		
	}
}