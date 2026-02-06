package ceu.dam.javafx.ejemplo32.gui;

import java.util.List;

import ceu.dam.javafx.ejemplo32.modelo.Equipo;
import ceu.dam.javafx.ejemplo32.services.EquipoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EquiposController extends AppController{

	@FXML
	TableView <Equipo> tablaEquipos;
	
	@FXML
	TableColumn<Equipo, String> columnaNombre;
	
	@FXML
	TableColumn<Equipo, String> columnaCiudad;
	
	@FXML
	TableColumn<Equipo, Integer> columnaAnyo;
	
	@FXML
	TextField txtFieldConsulta;
	
	@FXML
	CheckBox chkBoxTodos;
	
	@FXML
	ProgressBar progressBarEquipos;
	
	@FXML
	ComboBox<String> comboBoxCiudades;
	
	@FXML
	Label labelLogin;
	
	private ObservableList<Equipo> datos;
	
	private ObservableList<String> ciudades;
	
	private EquipoService service = new EquipoService();;
	
	@FXML
	public void initialize() {
		columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		columnaAnyo.setCellValueFactory(new PropertyValueFactory("creacion"));
		columnaCiudad.setCellValueFactory(new PropertyValueFactory("ciudad"));
		
		datos = FXCollections.observableArrayList();
		tablaEquipos.setItems(datos);
		
		ciudades = FXCollections.observableArrayList();
		ciudades.add("Sevilla");
		ciudades.add("Cádiz");
		ciudades.add("Huelva");
		ciudades.add("Córdoba");

		comboBoxCiudades.setItems(ciudades);
		
		labelLogin.setText("Usuario logado: "+getUserData("usuario"));
	}
	
	@FXML
	public void limpiarTextField() {
		boolean checkBox = chkBoxTodos.isSelected();
		if(checkBox) {
			txtFieldConsulta.setText("");
		}
	}
	
	@FXML
	public void anyadir() {
		cambiarVista(FXMLPaths.PANTALLA_ANYADIR);
	}
	
	@FXML
	public void consultarEquipos() {
		
		String filtroConsulta = txtFieldConsulta.getText();
		
		String ciudadSeleccionada = comboBoxCiudades.getValue();
		
		boolean checkBox = chkBoxTodos.isSelected();
		if(checkBox) {
			datos.setAll(service.consultarEquipos());
		} else {
			
			datos.setAll(service.consultarEquiposFiltrado(filtroConsulta, ciudadSeleccionada));
		}
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	public void filtrarCiudad() throws InterruptedException {
		
		Task<List<Equipo>> task = new Task<List<Equipo>>() {

			@Override
			protected List<Equipo> call() throws Exception {
				
				updateProgress(33,100);
				String ciudadSeleccionada = comboBoxCiudades.getValue();
				Thread.sleep(1000);
				updateProgress(66,100);
				return service.consultarEquiposFiltradoCiudad(ciudadSeleccionada);
			}
			
			@Override
			protected void succeeded() {
				super.succeeded();
				datos.setAll(getValue());
				updateProgress(100,100);
			}
			
			@Override
			protected void failed() {
				super.failed();
				mostrarError("Error al consultar los equipos");
				updateProgress(100,100);
			}
		};

		new Thread(task).start();
		progressBarEquipos.progressProperty().bind(task.progressProperty());
	}
	
	
}
