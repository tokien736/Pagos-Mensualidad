/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mensualidad.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mensualidad.control.JPA.Estudiantes_Controlador;
import mensualidad.modelo.Estudiantes;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */

public class AgregarEstudianteControl implements Initializable {
    private Stage dialogStage;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField gradoField;
    
    private Estudiantes estudiantes;
    public void nuevoEstudainte(){
        String nombre = null;
        String apellido = null;
        String nombreCompleto; 
        String grado = null;
        if(validadCampos()){
            nombre = nombreField.getText();
            apellido = apellidoField.getText();            
            grado = gradoField.getText();
            nombreCompleto = nombre + " " + apellido;
            grado = grado;

            Estudiantes_Controlador controlador = new Estudiantes_Controlador();
            Estudiantes estudiante = new Estudiantes(nombreCompleto, grado);
            estudiante.setGradoEstudios(grado);
            controlador.insertarEstudiante(estudiante);                  
            System.out.println("Estudiante insertado correctamente.");
        } 
        nombreField.setText("");
        apellidoField.setText("");
        gradoField.setText("");
        
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
    private boolean validadCampos(){
        String errorMensaje = "";
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMensaje += "Sin nombre válido!\n"; 
        }
        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMensaje += "Sin apellido válido!\n"; 
        }
        if (gradoField.getText() == null || gradoField.getText().length() == 0) {
            errorMensaje += "Sin grado válido!\n"; 
        }  
        
        if (errorMensaje.length() == 0) {
            return true;
        } else {
            // muestra el error del mensaje
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campos inválidos");
            alert.setHeaderText("Corrija los campos inválidos");
            alert.setContentText(errorMensaje);

            alert.showAndWait();

            return false;
        }
    }    

    
}

