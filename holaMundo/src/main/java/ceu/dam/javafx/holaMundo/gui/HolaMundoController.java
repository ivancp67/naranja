package ceu.dam.javafx.holaMundo.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class HolaMundoController {

	private String user;
	private String password;
	
	public void sesion(ActionEvent event) {
		if (!(user.isEmpty()) && password.length() >= 8) {
			System.out.println("Se ha iniciado sesión correctamente");
		}
	}
	
	public void mostrarError() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
	    alert.setTitle("Error");
		if (user.isEmpty()) {
			alert.setContentText("El nombre de usuario no puede estar vacío");
			alert.showAndWait();
		}
		else if (password.length() < 8) {
			alert.setContentText("La contraseña debe de tener más de 8 caracteres");
			alert.showAndWait();
		}
	}
	
}
