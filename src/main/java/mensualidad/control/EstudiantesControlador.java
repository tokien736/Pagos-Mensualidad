
package mensualidad.control;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import mensualidad.control.JPA.Estudiantes_Controlador;
import mensualidad.control.JPA.MatriculaControladorJPA;
import mensualidad.control.reporte.Reporte;
import mensualidad.modelo.Estudiantes;
import mensualidad.modelo.Matricula;


public class EstudiantesControlador implements Initializable {

    @FXML
    private TableView<Estudiantes> tablaEstudiante;
    @FXML
    private TableColumn<Estudiantes, String> idColumna;
    @FXML
    private TableColumn<Estudiantes, String> nombreCompletoColumna;
    @FXML
    private TableColumn<Estudiantes, String> gradoColumna;
      
    
    @FXML
    private TableView<Matricula> tablaMatricula;
    @FXML
    private TableColumn<Matricula, Integer> idColumna2;
    @FXML
    private TableColumn<Matricula, Integer> idEstudianteColumna;
    @FXML
    private TableColumn<Matricula, LocalDate> fechaPagoColumna;
    @FXML
    private TableColumn<Matricula, Integer> pensionColumna;
    @FXML
    private TableColumn<Matricula, Integer> mesesColumna;
    @FXML
    private TableColumn<Matricula, Boolean> estadoColumna;
    
    private Estudiantes_Controlador controlador;
    private MatriculaControladorJPA controlador1;
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
        controlador1 = new MatriculaControladorJPA();

        // Configurar las propiedades de las columnas
        idColumna.setCellValueFactory(new PropertyValueFactory<>("estudiantesId"));
        nombreCompletoColumna.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        gradoColumna.setCellValueFactory(new PropertyValueFactory<>("gradoEstudios"));
        
        // Configurar las propiedades de las columnas de matricula
        idColumna2.setCellValueFactory(new PropertyValueFactory<>("matriculaId"));
        idEstudianteColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstudiante().getEstudiantesId()).asObject());
        fechaPagoColumna.setCellValueFactory(new PropertyValueFactory<>("fechaPago"));
        pensionColumna.setCellValueFactory(new PropertyValueFactory<>("pension"));  
        mesesColumna.setCellValueFactory(new PropertyValueFactory<>("mesesDeuda"));
        estadoColumna.setCellValueFactory(new PropertyValueFactory<>("pagado"));
       
        // Obtener los datos de los estudiantes desde el controlador
        ObservableList<Estudiantes> estudiantes = controlador.cargarEstudiantes();
        tablaEstudiante.setItems(estudiantes);
        // Obtener los datos de los estudiantes desde el controlador
        ObservableList<Matricula> matricula = controlador1.cargarMatriculas();
        tablaMatricula.setItems(matricula);
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
        recargaDatosService.setPeriod(Duration.seconds(5)); // Configurar el intervalo de tiempo en segundos
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
    public void agregarMatricula(ActionEvent event) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mensualidad/vista/agregarMatricula.fxml"));
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
