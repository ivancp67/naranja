package ceu.dam.javafx.ejercicio11.gui;

import java.util.Optional;

import ceu.dam.javafx.ejercicio11.app.FXMLPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

public class AdiosMundoController extends AppController{

	@FXML
	private BorderPane borderPane;
	
	@FXML
	public void logOut(ActionEvent envent) {
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setHeaderText(null);
			a.setContentText("¿Seguro que quieres salir?");
			a.setTitle("confirmacion");
			Optional<ButtonType> result = a.showAndWait();
			if (result.get() == ButtonType.YES) {
				System.out.println("Se ha pulsado Aceptar");
				cambiarVista(FXMLPaths.HOLA_MUNDO);
			}
			else if(result.get() == ButtonType.NO) {
				System.out.println("Se ha pulsado Cancelar");
			}
	}
	
	@FXML
	public void salir(ActionEvent event) {
		System.out.println("Se ha salido perfectamente");
		System.exit(0);
	}
	
	@FXML
	public void combobox(ActionEvent event) {
		System.out.println("Se ha seleccionado combobox");
		Parent vista = cargarVista(FXMLPaths.COMBOBOX);
		borderPane.setCenter(vista);
	}
	
	@FXML
	public void colorPicker(ActionEvent event) {
		System.out.println("Se ha seleccionado colorPicker");
		cambiarVista(FXMLPaths.COLORPICKER);
	}
	
	@FXML
	public void datePicker(ActionEvent event) {
		System.out.println("Se ha seleccionado datePicker");
		cambiarVista(FXMLPaths.DATEPICKER);
	}
	
	@FXML
	public void slider(ActionEvent event) {
		System.out.println("Se ha seleccionado slider");
		cambiarVista(FXMLPaths.SLIDER);
	}
}
