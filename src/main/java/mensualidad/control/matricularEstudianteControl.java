/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensualidad.control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static com.ctc.wstx.util.DataUtil.Integer;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mensualidad.control.JPA.Estudiantes_Controlador;
import mensualidad.control.JPA.MatriculaControladorJPA;
import mensualidad.modelo.Estudiantes;
import mensualidad.modelo.Matricula;

public class matricularEstudianteControl {
    private Stage dialogStage;
    //private MatriculaControladorJPA control;
    @FXML
    private TextField estudianteIdField;
    @FXML
    private TextField pensionField;
    @FXML
    private TextField cuotaField;

    @FXML
    private void atras(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
    @FXML
    private void btMatricular(ActionEvent event) {
        if (validadCampos()) {
            nuevoMatricula();

            // Mostrar mensaje de estudiante matriculado
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Estudiante Matriculado");
            alert.setHeaderText(null);
            alert.setContentText("El estudiante ha sido matriculado correctamente.");

            alert.showAndWait();

            // Borrar los campos
            estudianteIdField.clear();
            pensionField.clear();
            cuotaField.clear();
        }
    }

    private boolean validadCampos(){
        String errorMensaje = "";
        if (estudianteIdField.getText() == null || estudianteIdField.getText().length() == 0) {
            errorMensaje += "Sin IdValido!\n"; 
        }
        if (pensionField.getText() == null || pensionField.getText().length() == 0) {
            errorMensaje += "Sin pension válido!\n"; 
        }
        if (cuotaField.getText() == null || cuotaField.getText().length() == 0) {
            errorMensaje += "Sin couta válido!\n"; 
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

    public void nuevoMatricula() {
        if(validadCampos()){
            int estudianteId = Integer.parseInt(estudianteIdField.getText());
            int pension = Integer.parseInt(pensionField.getText());
            int cuotasMeses = Integer.parseInt(cuotaField.getText());

            // Crear una fábrica de EntityManager
            EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("BaseDatos");

            // Obtener una instancia de EntityManager
            EntityManager entityManager = emFactory.createEntityManager();

            // Buscar la entidad Estudiantes por su identificador        
            Estudiantes estudiante = entityManager.find(Estudiantes.class, estudianteId);
            // Cerrar el EntityManager y la fábrica de EntityManager al finalizar
            entityManager.close();
            emFactory.close();



            MatriculaControladorJPA controlador = new MatriculaControladorJPA();

            Boolean pagado = false;
            LocalDate fechaPago = LocalDate.of(2022, 1, 22);

            Matricula matricula = new Matricula(fechaPago, pension, cuotasMeses, pagado, estudiante);
            controlador.insertarMatricula(matricula);

            System.out.println("Matricula insertada correctamente.");
            
        }
    }    

}
