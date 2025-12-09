package ceu.dam.javafx.ejercicio11.gui;

import java.util.Optional;

import ceu.dam.javafx.ejercicio11.app.FXMLPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AdiosMundoController extends AppController{

	public static final String SESION_USER = "usuario";
	
	@FXML
	public void logOut(ActionEvent envent) {
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setHeaderText(null);
			a.setContentText("¿Mario es maricon?");
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
}
