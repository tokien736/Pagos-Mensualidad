
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mensualidad.control;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mensualidad.control.reporte.Reporte;
import mensualidad.modelo.Estudiantes;

/**
 * FXML Controller class
 *
 * @author PC-Jose
 */
public class EstudiantesControlador implements Initializable {

    @FXML
    private TableView<Estudiantes> tablaEstudiante;
    @FXML
    private TableColumn<Estudiantes, String> idColumna;
    @FXML
    private TableColumn<Estudiantes, String> nombreCompletoColumna;
    @FXML
    private TableColumn<Estudiantes, LocalDate> fechaPagoColumna;
    @FXML
    private TableColumn<Estudiantes, String> cuotaColumna;
    @FXML
    private TableColumn<Estudiantes, String> totalPagoColumna;
    @FXML
    private TableColumn<Estudiantes, String> gradoColumna;

    private Estudiantes_Controlador controlador;
    private ScheduledService<Void> recargaDatosService;   

       /**
        * Initializes the controller class.
        */
    private void cargarDatosEstudiantes() {
            tablaEstudiante.setItems(controlador.cargarEstudiantes());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlador = new Estudiantes_Controlador();

        // Configurar las propiedades de las columnas
        idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreCompletoColumna.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        fechaPagoColumna.setCellValueFactory(new PropertyValueFactory<>("fechaPago"));
        cuotaColumna.setCellValueFactory(new PropertyValueFactory<>("cuota"));
        totalPagoColumna.setCellValueFactory(new PropertyValueFactory<>("totalPago"));
        gradoColumna.setCellValueFactory(new PropertyValueFactory<>("gradoEstudios"));

        // Obtener los datos de los estudiantes desde el controlador
        ObservableList<Estudiantes> estudiantes = controlador.cargarEstudiantes();
        tablaEstudiante.setItems(estudiantes);
        
        // Crear el ScheduledService para recargar los datos cada cierto tiempo
        recargaDatosService = new ScheduledService<Void>() {
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        cargarDatosEstudiantes();
                        return null;
                    }
                };
            }
        };
        recargaDatosService.setPeriod(Duration.seconds(10)); // Configurar el intervalo de tiempo en segundos
        recargaDatosService.start();        
    }

    @FXML
    public void agregarestudiante(ActionEvent event) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mensualidad/vista/agregar_estudiante.fxml"));
            Parent root = loader.load();

            // Crear una nueva escena
            Scene scene = new Scene(root);

            // Crear una nueva ventana
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Mostrar la nueva ventana
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnReporte(ActionEvent event) {
        Reporte reporte = new Reporte("Cherry");
        reporte.generarReporte();
    }
    
}
