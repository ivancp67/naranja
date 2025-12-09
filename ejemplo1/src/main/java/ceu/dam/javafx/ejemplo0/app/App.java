package ceu.dam.javafx.ejemplo0.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/ceu/dam/javafx/ejemplo0/gui/holaMundo.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 640, 480);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
