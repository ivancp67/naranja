package ceu.dam.javafx.ejemplo32;


import ceu.dam.javafx.ejemplo32.gui.AppController;

import ceu.dam.javafx.ejemplo32.gui.FXMLPaths;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	public void start(Stage primaryStage) throws Exception {
		AppController controller = new AppController(primaryStage);
		controller.cambiarVista(FXMLPaths.PANTALLA_LOGIN);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(new String[0]);
	}
}