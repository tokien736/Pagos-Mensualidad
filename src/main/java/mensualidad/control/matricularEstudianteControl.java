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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mensualidad.control.JPA.Estudiantes_Controlador;
import mensualidad.control.JPA.MatriculaControladorJPA;
import mensualidad.modelo.Estudiantes;
import mensualidad.modelo.Matricula;

public class matricularEstudianteControl {

    //private MatriculaControladorJPA control;
    @FXML
    private TextField estudianteIdField;
    @FXML
    private TextField pensionField;
    @FXML
    private TextField cuotaField;

    public void nuevoMatricula() {
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
    @FXML
    private void atras(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
    @FXML
    private void btMatricular(ActionEvent event) {
        nuevoMatricula();
    }

}
