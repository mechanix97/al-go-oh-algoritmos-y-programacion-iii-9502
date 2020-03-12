package aplicacion.App;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import aplicacion.App.Turno;
import aplicacion.Eventos.AvanzarDeFaseEventHandler;
import aplicacion.App.Fase;
import modelo.CartaMonstruo;
import modelo.Jugador;
import modelo.Yugioh;

public class TableroVista {
	private Aplicacion app;
	private Jugador jugador1;
	private Jugador jugador2;
	private LadoArribaVista ladoArriba;
	private LadoAbajoVista ladoAbajo;
	private Scene escena;
	private Label info; 
	private Button botonAvanzar;

	private int turnos = 1;
	private Turno turno;
	private Fase fase;
	private CartaMonstruo carta;
	
	public TableroVista(Yugioh yugioh,Aplicacion app) {
		this.app = app;
		this.carta = null;
		this.jugador1 = yugioh.obtenerJugadorDeTurno();
		this.jugador2 = yugioh.obtenerJugadorOponente();
		this.turno = Turno.TURNOABAJO;
		this.fase = Fase.INICIAL;
		this.ladoArriba = new LadoArribaVista (yugioh.obtenerLadoDos(),this);
		this.ladoAbajo = new LadoAbajoVista (yugioh.obtenerLadoUno(),this);
		this.info = new Label("                                  ");
		this.info.setMaxWidth(150);
		this.info.setId("INFO");
		this.botonAvanzar = new Button("Pasar a \nFase De Preparacion");
		this.botonAvanzar.requestFocus(); 
		this.botonAvanzar.setId("AVANZAR");
				
		AvanzarDeFaseEventHandler eh = new  AvanzarDeFaseEventHandler(this);
		this.botonAvanzar.setOnAction(eh);
		
		obtenerInformacion();
	}

	public void refresh() {
		verificarGanador();
		obtenerInformacion();
		this.app.refresh();
	}
	
	public void avanzarFase() {	
		switch(this.fase) {
			case INICIAL:
				this.fase = Fase.PREPARACION;
				this.botonAvanzar.setText("Pasar a \nFase De Ataque");
				break;
			case PREPARACION:
				this.fase = Fase.ATAQUE;
				this.botonAvanzar.setText("Pasar a \nFase Final");
				break;
			case ATAQUE:
				this.fase = Fase.FINAL;
				this.botonAvanzar.setText("Finalizar Turno");
				break;
			case FINAL:
				this.fase = Fase.INICIAL;
				this.botonAvanzar.setText("Pasar a \nFase De Preparacion");
				avanzarTurno();
				break;
			default:
				break;					
			}
	
	}
	
	public void avanzarTurno() {
		switch(this.turno) {
			case TURNOABAJO:
				this.turno = Turno.TURNOARRIBA;
				break;
			case TURNOARRIBA:
				this.turno = Turno.TURNOABAJO;				
				this.turnos++;
				break;
			default:
				break;		
		}
	}
	
	public Scene juego(){
		switch(this.turno) {
			case TURNOABAJO:
				this.ladoArriba.dibujarSinTurno();
				switch(this.fase) {
					case INICIAL:	
						this.ladoAbajo.faseInicial();
						break;
					case PREPARACION:
						this.ladoAbajo.fasePreparacion();
						break;
					case ATAQUE:
						this.ladoAbajo.faseAtaque();
						break;
					case FINAL:
						this.ladoAbajo.faseFinal();
						break;
					default:
						break;					
				}
				break;
			case TURNOARRIBA: 
				this.ladoAbajo.dibujarSinTurno();
				switch(this.fase) {
					case INICIAL:	
						this.ladoArriba.faseInicial();
						break;
					case PREPARACION:
						this.ladoArriba.fasePreparacion();
						break;
					case ATAQUE:
						this.ladoArriba.faseAtaque();
						break;
					case FINAL:
						this.ladoArriba.faseFinal();
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
		cargarFase();
		return this.escena;
	}
	
	public void cargarFase() {
		VBox pane1 = this.ladoArriba.getGrid();
		VBox pane2 = this.ladoAbajo.getGrid();
		VBox contenedor = new VBox(pane1,pane2);
	    
		HBox hb = new HBox(this.info, contenedor, this.botonAvanzar);
		hb.setAlignment(Pos.CENTER);
		contenedor.setSpacing(20);
		
		hb.setSpacing(20);
	    hb.setStyle("-fx-padding: 0 10 0 10;");
	    contenedor.setAlignment( Pos.CENTER);
	    this.info.setAlignment( Pos.CENTER);
	    this.botonAvanzar.setAlignment( Pos.CENTER);
	    this.botonAvanzar.requestFocus();
	    HBox.setHgrow(contenedor, Priority.ALWAYS);
		this.escena = new Scene(hb);
	}

	public void cartaMonstruoColocada(CartaMonstruo carta) {
		this.carta = carta;
	}
	
	public void reiniciarCarta() {
		this.carta = null;
	}
	
	public CartaMonstruo obtenerCarta() {
		return this.carta;
	}
	
	public boolean hayCarta() {
		return this.carta!=null;
	}

	public void verificarGanador(){
		if(this.jugador1.esPerdedor() | this.jugador2.esGanador()){
			this.app.juegoTerminadoConGanador(this.jugador2);
		}
		else if(this.jugador2.esPerdedor() | this.jugador1.esGanador()){
			this.app.juegoTerminadoConGanador(this.jugador1);
		}
	}
	
	public void obtenerInformacion() {
		String info ="TURNO: "+String.valueOf(this.turnos)+"\n\n"
				+this.jugador2.obtenerNombre() + "\n"
				+"Vida:"+String.valueOf(this.jugador2.obtenerVida())+ "\n "
				+"Sacrificios:"+String.valueOf(this.jugador2.obtenerSacrificios())+"\n"
				+"                             \n"
				+this.jugador1.obtenerNombre() +"\n"
				+"Vida:" +String.valueOf(this.jugador1.obtenerVida())+ "\n"
				+"Sacrificios:"+String.valueOf(this.jugador1.obtenerSacrificios())+"\n"
				+"\n\n\n";
		this.info.setText(info);
				
	}	
}