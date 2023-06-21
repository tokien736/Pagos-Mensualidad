/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mensualidad.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class LoginControlador implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void ingresar(ActionEvent event) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mensualidad/vista/SideBar.fxml"));
            Parent root = loader.load();

            // Crear una nueva escena
            Scene scene = new Scene(root);

            // Obtener la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Crear una nueva ventana
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Mostrar la nueva ventana y cerrar la actual si es necesario
            newStage.show();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void salir(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
    
}
