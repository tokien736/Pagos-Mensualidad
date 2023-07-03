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
import mensualidad.control.reporte.Reporte;
import mensualidad.control.reporte.ReporteMatricula;

/**
 * FXML Controller class
 *
 * @author PC-Jose
 */
public class ReportesController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnReporteEstudiante(ActionEvent event) {
        Reporte reporte = new Reporte("Cherry");
        reporte.generarReporte();
    }
    @FXML
    private void btnReporteMatricula(ActionEvent event) {
        ReporteMatricula reporte1 = new ReporteMatricula("Coffee");
        reporte1.generarReporte();
    }
    
}
