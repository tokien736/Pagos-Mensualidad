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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC-Jose
 */
public class ReportesController implements Initializable {

    @FXML
    private TextField txtfieldEstudiante;
    @FXML
    private TextField txtfieldGrado;
    @FXML
    private TextField txtfieldGrado2;
    @FXML
    private TableView<?> tablaResultados;
    @FXML
    private TableColumn<?, ?> idColumna;
    @FXML
    private TableColumn<?, ?> nombreCompletoColumna;
    @FXML
    private TableColumn<?, ?> gradoColumna;
    @FXML
    private TableColumn<?, ?> fechaPagoColumna;
    @FXML
    private TableColumn<?, ?> totalPagoColumna;
    @FXML
    private TableView<?> tablaResultados2;
    @FXML
    private TableColumn<?, ?> idColumna2;
    @FXML
    private TableColumn<?, ?> nombreCompletoColumna2;
    @FXML
    private TableColumn<?, ?> gradoColumna2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBuscar2ButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleBuscarButtonAction(ActionEvent event) {
    }
    
}
