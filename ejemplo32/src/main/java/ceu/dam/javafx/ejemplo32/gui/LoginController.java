package ceu.dam.javafx.ejemplo32.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends AppController {

	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField passUsuario;
	
	@FXML
	public void login(ActionEvent event) {
		if("User".equals(txtUsuario.getText()) && ("Pass".equals(passUsuario.getText()))) {
			putUserData("usuario", txtUsuario.getText());
			cambiarVista("/ceu/dam/javafx/ejemplo32/gui/tabla.fxml");
			return;
		}
		mostrarError("Login incorrecto");
	}
}
