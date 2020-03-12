package aplicacion.App;

import java.util.ArrayList;

import aplicacion.Eventos.DestruirCartaEventHandler;
import aplicacion.Eventos.FaseAtaqueMagicaEventHandler;
import aplicacion.Eventos.FaseAtaqueMonstruoEventHandler;
import aplicacion.Eventos.InsertarEvent;
import aplicacion.Eventos.MostrarCartaEventHandler;
import aplicacion.Eventos.RobarCartaEventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.RowConstraints;
import modelo.Carta;
import modelo.CartaDeCampo;
import modelo.CartaMagica;
import modelo.CartaMonstruo;
import modelo.Jugador;
import modelo.Lado;
import modelo.Mano;


public class LadoAbajoVista extends LadoVista {
	private GridPane campo;
	private HBox mano;
	private Lado lado;
	private Jugador jugador;
	private TableroVista tableroVista;
	private boolean seRoboCartaFlag; 
	
	public LadoAbajoVista(Lado lado,TableroVista tableroVista) {
		//dibuja el tablero inicial
		this.tableroVista = tableroVista;
		this.lado = lado;
		this.seRoboCartaFlag = false;
		this.jugador = lado.obtenerJugador();
		this.mano = new HBox();
		this.mano.setSpacing(10);
		
		dibujarSinTurno();
	}
	public boolean seRoboCarta() {
		return this.seRoboCartaFlag;
	}
	
	public void roboCarta() {
		this.seRoboCartaFlag = true;
	}

	public void faseInicial() {
		this.lado.verificarReinforcements();
		reiniciarGrid();
        dibujarCartasMonstruoSoloVista();
        dibujarCartasTrampaSoloVista();
        dibujarCartaCampo();
        dibujarCementerio(); 
		dibujarMazo();
		dibujarManoSoloVista();	
	}

	public void fasePreparacion() {
		reiniciarGrid();
        dibujarCartasMonstruoSoloSacrificio();
        dibujarCartasTrampaSoloVista();
        dibujarCartaCampo();
        dibujarCementerio(); 
		dibujarMazoSoloVista();
		dibujarMano();	
	}
	
	public void faseAtaque() {
		reiniciarGrid();
        dibujarCartasMonstruo();
        dibujarCartasTrampaSoloVista();
        dibujarCartaCampo();
        dibujarCementerio(); 
		dibujarMazoSoloVista();
		dibujarManoSoloVista();	
	}
	
	public void faseFinal() {
		reiniciarGrid();
        dibujarCartasMonstruoSoloVista();
        dibujarCartasTrampa();
        dibujarCartaCampo();
        dibujarCementerio(); 
		dibujarMazoSoloVista();
		dibujarManoSoloVista();	
	}
	
	public void dibujarSinTurno() {
		reiniciarGrid();
		dibujarCartaCampo();
		dibujarCementerio();
		dibujarManoOculta();
		dibujarCartasTrampaOcultas();
		dibujarCartasMonstruoSoloVista();
		dibujarMazoSoloVista();
		this.seRoboCartaFlag = false;
		this.lado.apagarFlags();
	}
			
	public VBox getGrid() {
		VBox vb = new VBox(this.campo,this.mano);
		this.campo.setAlignment(Pos.TOP_CENTER );
	    this.mano.setAlignment( Pos.TOP_CENTER );
	    vb.setSpacing(10);
		return vb;
	}
	
	@Override
	public void refresh() {
		this.tableroVista.refresh();
	}
	
	public void dibujarCementerio() {
        Button cementerio = new Button();      
        
		if(this.lado.cantidadDeCartasEnCementerio() == 0) {
			cementerio.setId("cartaCementerioVacia");
		}
		else {
			cementerio.setId("cartaCementerio");
		}
		this.campo.add(cementerio,6,0);
	}
	
	public void dibujarCartaCampo() {       
		CartaDeCampo carta = this.lado.obtenerCartaCampo();
		if(carta != null) {
			CartaVista cv = new CartaVista(carta);
			Button button =cv.obtenerBoton(80,100);
			button.setMinSize(80,100);
			button.setMaxSize(80,100);
			
			MostrarCartaEventHandler eh = new MostrarCartaEventHandler(carta);
			button.setOnAction(eh);
			this.campo.add(button,0,0);
		}
		else {
	        Button campo = new Button();
	        campo.setId("cartaCampoVacia");
	        this.campo.add(campo,0,0);
		}
		
	}
	
	public void dibujarManoSoloVista() {
		this.mano.getChildren().clear();
		Mano mano = this.lado.obtenerJugador().obtenerMano();
		ArrayList<Carta> coleccionDeCartas = mano.obtenerCartas();
		int size = mano.obtenerTamanio();
		for (int i = 0; i <size; i++) {
			Carta carta = coleccionDeCartas.get(i);
			CartaVista cv = new CartaVista(carta);
			Button button =cv.obtenerBoton(80,100);
			button.setMinSize(80,100);
			button.setMaxSize(80,100);
			
			MostrarCartaEventHandler eh = new MostrarCartaEventHandler(carta);
			button.setOnAction(eh);
			
			this.mano.getChildren().add(button);
		}
	}
	
	public void dibujarMano() {
		this.mano.getChildren().clear();
		Mano mano = this.lado.obtenerJugador().obtenerMano();
		ArrayList<Carta> coleccionDeCartas = mano.obtenerCartas();
		int size = mano.obtenerTamanio();
		for (int i = 0; i <size; i++) {
			Carta carta = coleccionDeCartas.get(i);
			CartaVista cv = new CartaVista(carta);
			Button button =cv.obtenerBoton(80,100);
			button.setMinSize(80,100);
			button.setMaxSize(80,100);
			

			InsertarEvent eh = new InsertarEvent(carta,this.lado,this);
			button.setOnAction(eh);
			
			this.mano.getChildren().add(button);
		}
	}

	public void dibujarManoOculta() {
		//dibuja dorso de cartas
		this.mano.getChildren().clear();
		Button[] cartasMano = new Button[jugador.cantidadDeCartasEnMano()];
	    for(int i = 0; i < cartasMano.length; i++) {
	    	cartasMano[i] = new Button();
	    	cartasMano[i].setId("cartaMonstruo");
	    	this.mano.getChildren().add(cartasMano[i]);
	   	}
    }

	public void dibujarCartasTrampaOcultas() {
		Carta[] cartas = lado.obtenerCartasMagicas();
		Button[] cartasTrampa = new Button[5];
     
		for(int i = 0; i < 5 ; i++) {
			cartasTrampa[i] = new Button();
			if(cartas[i] == null) {
				cartasTrampa[i].setId("cartaMagicaTrampaVacia");
			}
			else {
				cartasTrampa[i].setId("cartaMagicaTrampa");
			}
			this.campo.add(cartasTrampa[i],i+1,1);
		}
	}
	
	public void dibujarCartasTrampaSoloVista() {
		Carta[] cartas = lado.obtenerCartasMagicas();
		Button[] cartasTrampa = new Button[5];
     
		for(int i = 0; i < 5 ; i++) {
			cartasTrampa[i] = new Button();
			if(cartas[i] == null) {
				cartasTrampa[i].setId("cartaMagicaTrampaVacia");
				this.campo.add(cartasTrampa[i],i+1,1);
			}
			else {
				CartaVista cv = new CartaVista(cartas[i]);
				Button button =cv.obtenerBoton(80,100);
				button.setMinSize(80,100);
				button.setMaxSize(80,100);
				MostrarCartaEventHandler eh = new MostrarCartaEventHandler(cartas[i]);
				button.setOnAction(eh);
				this.campo.add(button, i+1,1);
			}
			
		}
	}
	
	public void dibujarCartasTrampa() {
		Carta[] cartas = lado.obtenerCartasMagicas();
		Button[] cartasTrampa = new Button[5];

     
		for(int i = 0; i < 5 ; i++) {
			cartasTrampa[i] = new Button();
			if(cartas[i] == null) {
				cartasTrampa[i].setId("cartaMagicaTrampaVacia");
				this.campo.add(cartasTrampa[i],i+1,1);
			}
			else {
				CartaVista cv = new CartaVista(cartas[i]);
				Button button =cv.obtenerBoton(80,100);
				button.setMinSize(80,100);
				button.setMaxSize(80,100);
				
				if(cartas[i].getClass().getSuperclass() == CartaMagica.class){
					FaseAtaqueMagicaEventHandler eh = new FaseAtaqueMagicaEventHandler(cartas[i],this);
					button.setOnAction(eh);
				}
				else{
					MostrarCartaEventHandler eh = new MostrarCartaEventHandler(cartas[i]);
					button.setOnAction(eh);
				}
				this.campo.add(button, i+1,1);
			}
			
		}
	}

	public void dibujarCartasMonstruoSoloVista() {
		CartaMonstruo[] cartas = lado.obtenerCartasMonstruo();
		Button[] button = new Button[5];
		for(int i = 0; i < 5 ; i++) {
			button[i] = new Button();
			if(cartas[i] == null) {
				button[i].setId("cartaMonstruoVacia");
				this.campo.add(button[i],i+1,0);
			}
			else {
				//VER EL TEMA DE LA POSICION Y EL ESTADO!!!!!!!
				CartaVista cv = new CartaVista(cartas[i]);
				button[i] =cv.obtenerBoton(80,100);
				button[i].setMinSize(80,100);
				button[i].setMaxSize(80,100);
				if(!cartas[i].estaEnPosicionDeAtaque()){
					button[i].setRotate(270);
				} 
				MostrarCartaEventHandler eh = new MostrarCartaEventHandler(cartas[i]);
				button[i].setOnAction(eh);
				this.campo.add(button[i], i+1,0);
			}
		}
	}
	
	public void dibujarCartasMonstruoSoloSacrificio() {
		CartaMonstruo[] cartas = lado.obtenerCartasMonstruo();
		Button[] button = new Button[5];
		for(int i = 0; i < 5 ; i++) {
			button[i] = new Button();
			if(cartas[i] == null) {
				button[i].setId("cartaMonstruoVacia");
				this.campo.add(button[i],i+1,0);
			}
			else {
				//VER EL TEMA DE LA POSICION Y EL ESTADO!!!!!!!
				CartaVista cv = new CartaVista(cartas[i]);
				button[i] =cv.obtenerBoton(80,100);
				button[i].setMinSize(80,100);
				button[i].setMaxSize(80,100);
				if(!cartas[i].estaEnPosicionDeAtaque()){
					button[i].setRotate(270);
				} 
				DestruirCartaEventHandler eh = new DestruirCartaEventHandler(cartas[i],this,this.lado);
				button[i].setOnAction(eh);
				this.campo.add(button[i], i+1,0);
			}
		}
	}
	
	public void dibujarCartasMonstruo() {
		CartaMonstruo[] cartas = lado.obtenerCartasMonstruo();
		Button[] button = new Button[5];
		for(int i = 0; i < 5 ; i++) {
			button[i] = new Button();
			if(cartas[i] == null) {
				button[i].setId("cartaMonstruoVacia");
				this.campo.add(button[i],i+1,0);
			}
			else {
				//VER EL TEMA DE LA POSICION Y EL ESTADO, CAMBIAR EVENTHANDLER!!!!!!!
				CartaVista cv = new CartaVista(cartas[i]);
				button[i] =cv.obtenerBoton(80,100);
				button[i].setMinSize(80,100);
				button[i].setMaxSize(80,100);
				if(!cartas[i].estaEnPosicionDeAtaque()){
					button[i].setRotate(270);
				} 
				FaseAtaqueMonstruoEventHandler eh = new FaseAtaqueMonstruoEventHandler(cartas[i],this.lado,this,i);
				button[i].setOnAction(eh);
				this.campo.add(button[i], i+1,0);
			}
		}
	}

	public void dibujarMazoSoloVista() {
        Button mazo = new Button();
        if(this.lado.cantidadDeCartasEnMazo()==0 ) {
        	mazo.setId("cartaMazoVacia");
        }
        else {
        	mazo.setId("cartaMazo");
        }
        this.campo.add(mazo, 6,1);
	}
	
	public void dibujarMazo() {
        Button mazo = new Button();
        if(this.lado.cantidadDeCartasEnMazo()==0 ) {
        	mazo.setId("cartaMazoVacia");
            this.campo.add(mazo, 6,1);
        }
        else {
        	mazo.setId("cartaMazo");
        	mazo.setText("ROBAR");
            RobarCartaEventHandler robarCartaEH = new RobarCartaEventHandler(this.lado,this);
            mazo.setOnAction(robarCartaEH);
            this.campo.add(mazo, 6,1);
        }
	}

	public void reiniciarGrid() {
		this.campo = new GridPane();
		this.campo.setHgap(20); 
		this.campo.setVgap(20);
		
		for(int i= 0;i<7;i++ ) {
		      this.campo.getColumnConstraints().add(new ColumnConstraints(80));
		    }
		for(int i = 0;i<2;i++) {
	    	this.campo.getRowConstraints().add(new RowConstraints(100));
	    }
	}
}