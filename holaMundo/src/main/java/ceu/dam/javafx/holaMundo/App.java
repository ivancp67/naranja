package ceu.dam.javafx.holaMundo;

import ceu.dam.javafx.holaMundo.gui.FXMLPaths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(App.class.getResource(FXMLPaths.HOLA_MUNDO));
		Scene scene = new Scene(loader.load(), 640, 480);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(new String[0]);
	}
	
}
