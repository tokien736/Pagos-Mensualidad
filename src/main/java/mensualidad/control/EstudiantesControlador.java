/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mensualidad.control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import mensualidad.modelo.Estudiantes;

/**
 * FXML Controller class
 *
 * @author PC-Jose
 */
public class EstudiantesControlador implements Initializable {

private ObservableList<Estudiantes> datoEstudiante = FXCollections.observableArrayList();
    
    public EstudiantesControlador(){
        //Agregando algunos datos de muestra        
        datoEstudiante.add(new Estudiantes("1","Fernando Garcia"));
        datoEstudiante.add(new Estudiantes("2","Daniel Stip"));
        datoEstudiante.add(new Estudiantes("3","Jose Vilca"));
        datoEstudiante.add(new Estudiantes("4","Jose Enrique"));
    }
    /**
     * Devuelve los datos como una lista observable de Personas.
     * @return
     */
    public ObservableList<Estudiantes> getDatoEstudiantes(){
        return datoEstudiante;
    }
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

    /**
     * Initializes the controller class.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar la tabla de estudiantes con las columnas correspondientes
        idColumna.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nombreCompletoColumna.setCellValueFactory(cellData -> cellData.getValue().nombreCompletoProperty());
        cuotaColumna.setCellValueFactory(cellData -> cellData.getValue().cuotaProperty());
        totalPagoColumna.setCellValueFactory(cellData -> cellData.getValue().totalPagoProperty());
        fechaPagoColumna.setCellValueFactory(cellData -> cellData.getValue().fechaPagoProperty());
        // Configurar la lista de estudiantes en la tabla
        tablaEstudiante.setItems(getDatoEstudiantes());  
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
    
}
