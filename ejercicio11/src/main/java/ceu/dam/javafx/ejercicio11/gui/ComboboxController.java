package ceu.dam.javafx.ejercicio11.gui;


import ceu.dam.javafx.ejercicio11.app.FXMLPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ComboboxController extends AppController{
	
	@FXML
	private TextField textfield;
	private AdiosMundoController menuController;

	@FXML
	public void limpiar(ActionEvent event) {
		this.textfield.clear();
		
	}
	
	

}
