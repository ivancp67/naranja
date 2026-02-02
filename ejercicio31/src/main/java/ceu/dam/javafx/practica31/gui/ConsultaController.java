package ceu.dam.javafx.practica31.gui;

import java.util.List;

import ceu.dam.javafx.practica31.modelo.Usuario;
import ceu.dam.javafx.practica31.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;

public class ConsultaController extends AppController {

	@FXML
	private TableView<Usuario> tabla;
	
	@FXML
	private TableColumn<Usuario, Integer> columnaEdad;

	@FXML
	private TableColumn<Usuario, String> columnaNombre;

	@FXML
	private TableColumn<Usuario, String> columnaApellido;



	@FXML
	private TextField filtro;

	@FXML
	private ProgressBar barraProgreso;
	
	@FXML
	private ProgressIndicator indicatorProgreso;
	
	private ObservableList<Usuario> datos;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void initialize() {
		PropertyValueFactory factoryValueEdad = new PropertyValueFactory("edad");
		PropertyValueFactory factoryValueNombre = new PropertyValueFactory("nombre");
		PropertyValueFactory factoryValueApellido = new PropertyValueFactory("apellido");

		this.columnaEdad.setCellValueFactory(factoryValueEdad);
		this.columnaNombre.setCellValueFactory(factoryValueNombre);
		this.columnaApellido.setCellValueFactory(factoryValueApellido);

		this.datos = FXCollections.observableArrayList();
		this.tabla.setItems(this.datos);
	}

	@FXML
	public void consultar(ActionEvent event) {
		consultar(this.filtro.getText());
	}

	public void consultar(String textoFiltro) {
		this.filtro.setText(textoFiltro);
		StudentService service = new StudentService();

		this.tabla.setEffect(new BoxBlur());

		Task<List<Usuario>> task = new Task<List<Usuario>>() {

			@Override
			protected List<Usuario> call() throws Exception {
				// Llamada al servicio en segundo plano
				updateProgress(0.5, 1);
				return service.consultarUsuarios(textoFiltro);
			}

			@Override
			protected void succeeded() {
				super.succeeded();
				tabla.setEffect(null);
				datos.setAll(getValue()); // getValue() = resultado de call()
				barraProgreso.progressProperty().unbind();
				barraProgreso.setProgress(1.0);
				updateProgress(1, 1);
			}

			@Override
			protected void failed() {
				super.failed();
				tabla.setEffect(null);
				datos.clear();
				mostrarError("No hay registros en la bbdd con ese filtro");
				barraProgreso.progressProperty().unbind();
				barraProgreso.setProgress(1.0);
				updateProgress(1, 1);
			}
		};

		barraProgreso.progressProperty().bind(task.progressProperty());
		indicatorProgreso.progressProperty().bind(task.progressProperty());
		new Thread(task).start();
	}
}