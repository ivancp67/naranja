package ceu.dam.javafx.ejemplo32.gui;

import ceu.dam.javafx.ejemplo32.services.EquipoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AnyadirEquipoController extends AppController{

	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtAnyoCreacion;
	@FXML
	private ComboBox<String> comboBoxCiudades;
	
	@FXML
	private Label labelOk;
	
	private ObservableList<String> observableListCiudades;
	
	@FXML
	public void initialize() {
		observableListCiudades = FXCollections.observableArrayList();
		observableListCiudades.add("Sevilla");
		observableListCiudades.add("Cádiz");
		observableListCiudades.add("Huelva");
		observableListCiudades.add("Córdoba");

		comboBoxCiudades.setItems(observableListCiudades);
	}
	
	@FXML
	public void anyadirEquipo(ActionEvent event) {
		
		String nombreEquipo = txtNombre.getText();
		if(nombreEquipo.isEmpty()) {
			mostrarError("El nombre del equipo es obligatorio");
			return;
		}

		String anyo = txtAnyoCreacion.getText();
		Integer anyoCreacion = null;
		try {
			anyoCreacion = Integer.valueOf(anyo);
		} catch (Exception e) {
			mostrarError("El año tiene que ser numérico");
			return;
		}
		
		String ciudad = comboBoxCiudades.getValue();
		if( ciudad == null || ciudad.isEmpty()) {
			mostrarError("La ciudado es obligatorio");
			return;
		}
		
		EquipoService service = new EquipoService();
		service.anyadirEquipo(nombreEquipo, anyoCreacion, ciudad);
		//mostrarOk("El equpo: "+nombreEquipo+" se ha agregado correctamente");
		labelOk.setText("El equpo: "+nombreEquipo+" se ha agregado correctamente");
	}
	
	@FXML
	public void volverAtras() {
		cambiarVista("/ceu/dam/javafx/ejemplo32/gui/tabla.fxml");
	}
}
