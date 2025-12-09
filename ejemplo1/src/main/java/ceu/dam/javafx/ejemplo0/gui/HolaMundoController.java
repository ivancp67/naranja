package ceu.dam.javafx.ejemplo0.gui;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HolaMundoController {
	
	@FXML
	private TextField user;
	@FXML
	private PasswordField password;
	@FXML
	private Label confirmar;
	
	@FXML
	public void entrar(ActionEvent event) {
		confirmar.setText("Acceso confirmado\n"+user.getText());
	}
	
	@FXML
	public void salir(ActionEvent event) {
		System.out.println("Se ha salido perfectamente");
		System.exit(0);
	}
}
