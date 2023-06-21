/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mensualidad.control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mensualidad.modelo.Estudiantes;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class AgregarEstudianteControl implements Initializable {


    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField gradoField;
    
    private Estudiantes estudiantes;
    public void nuevoEstudainte(){
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String nombreCompleto = nombre + " " + apellido;
        String grado = gradoField.getText();

        Estudiantes_Controlador controlador = new Estudiantes_Controlador();

        Estudiantes estudiante = new Estudiantes(nombreCompleto, grado);
        estudiante.setCuota("700");
        estudiante.setTotalPago("2500");
        estudiante.setFechaPago(LocalDate.of(2022, 1, 22));
        estudiante.setGradoEstudios(grado);
        controlador.insertarEstudiante(estudiante);

        System.out.println("Estudiante insertado correctamente.");
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    private void btAgregar(ActionEvent event) {
        nuevoEstudainte();
    }

    
}
