package ceu.dam.javafx.ejercicio11.app;

import ceu.dam.javafx.ejercicio11.gui.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		AppController controller = new AppController(primaryStage);
		controller.cambiarVista(FXMLPaths.HOLA_MUNDO);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
