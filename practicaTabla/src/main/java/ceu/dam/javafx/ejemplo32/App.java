package ceu.dam.javafx.ejemplo32;

import javafx.application.Application;

import ceu.dam.javafx.ejemplo32.gui.FXMLPaths;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{

	//TODO: Método que carga la escena del scene builder en la ejecución
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPaths.HOLA_MUNDO));
        Scene scene = new Scene(loader.load(), 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	public static void main(String[] args) {
		launch(new String[0]);
	}

}
