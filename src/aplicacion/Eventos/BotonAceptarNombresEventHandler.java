package aplicacion.Eventos;

import aplicacion.App.Aplicacion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class BotonAceptarNombresEventHandler implements EventHandler<ActionEvent> {

    private TextField textField1;
    private TextField textField2;
    private Label label;
    private Aplicacion app;

    public BotonAceptarNombresEventHandler(TextField textField1,TextField textField2, Label label,Aplicacion app) {
        this.textField1 = textField1;
        this.textField2 = textField2;
        this.label = label;
        this.app = app;
        
    }
    
    @Override
    public void handle(ActionEvent actionEvent) {

        if (this.textField1.getText().trim().equals("") 
        		|| this.textField2.getText().trim().equals("")  ) {
            this.label.setText("Debe ingresar un texto");
        } 
        else if (this.textField2.getText().equals( this.textField1.getText())){

            this.label.setText("Los nombres no pueden coincidir");
        }        
        else {
        	this.app.iniciarJuego(this.textField1.getText(),this.textField2.getText());
        }
    }
}
