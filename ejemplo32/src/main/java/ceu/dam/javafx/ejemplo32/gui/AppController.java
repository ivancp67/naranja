package ceu.dam.javafx.ejemplo32.gui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ceu.dam.javafx.ejemplo32.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AppController {
	private static Stage primaryStage;
	


	public AppController() {
	}

	public AppController(Stage stage) {
		primaryStage = stage;
		stage.setUserData(new HashMap<String, Object>());
	}

	public AppController cambiarVista(String fxml) {
		try {
			URL url = App.class.getResource(fxml);
			FXMLLoader loader = new FXMLLoader(url);
			Scene scene = new Scene((Parent) loader.load());
			primaryStage.setScene(scene);
			return (AppController) loader.getController();
		} catch (IOException e) {
			throw new RuntimeException("No se ha podido cargar fxml con ruta " + fxml, e);
		}
	}

	public Parent cargarVista(String fxml) {
		try {
			URL url = App.class.getResource(fxml);
			FXMLLoader loader = new FXMLLoader(url);
			return (Parent) loader.load();
		} catch (IOException e) {
			throw new RuntimeException("No se ha podido cargar fxml con ruta " + fxml, e);
		}

	}

	public Object getUserData(String key) {
		return ((Map<String, Object>)primaryStage.getUserData()).get(key);
	}
	
	public Object putUserData(String key, Object value) {
		return ((Map<String, Object>)primaryStage.getUserData()).put(key, value);
	}
	
	public void mostrarOk(String mensaje) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Ok");
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
	public void mostrarError(String mensaje) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}