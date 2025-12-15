package ceu.dam.javafx.ejercicio11.gui;

import java.io.IOException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ceu.dam.javafx.ejercicio11.app.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController {
	
	public static Stage primaryStage;
	
	public AppController() {
	}
	
	public AppController(Stage stage) {
		primaryStage = stage;
		primaryStage.setUserData(new HashMap<String, Object>());
	}
	
	public AppController cambiarVista(String fxml) {
		try {
			URL url = App.class.getResource(fxml);
			FXMLLoader loader = new FXMLLoader(url);
			Scene scene = new Scene(loader.load());
			primaryStage.setScene(scene);
			return loader.getController();
		} catch (IOException e) {
			throw new RuntimeException("No se ha podido cargar fxml con ruta " + fxml, e);
		}
	}
	
	public Parent cargarVista(String fxml) {
		try {
			URL url = App.class.getResource(fxml);
			FXMLLoader loader = new FXMLLoader(url);
			return loader.load();
		} catch (IOException e) {
			throw new RuntimeException("No se ha podido cargar fxml con ruta "+ fxml, e);
		}
		
	}
	
	public Object getUserDataObject(String key) {
		@SuppressWarnings("unchecked")
		Map<String, Object> userData = (Map<String, Object>) primaryStage.getUserData();
		return userData.get(key);
	}
	
	public void putUserDataObject(String key, Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> userData = (Map<String, Object>) primaryStage.getUserData();
		userData.put(key, data);
	}
}
