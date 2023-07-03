package mensualidad.control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import mensualidad.control.JPA.Estudiantes_Controlador;
import mensualidad.control.JPA.MatriculaControladorJPA;
import mensualidad.modelo.Estudiantes;
import mensualidad.modelo.Matricula;

/**
 * FXML Controller class
 *
 * @author PC-Jose
 */
public class PagosController implements Initializable {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField gradoField;
    @FXML
    private TableView<Matricula> tablePagar;
    @FXML
    private TableColumn<Matricula, String> idColumna;
    @FXML
    private TableColumn<Matricula, Integer> pensionColumna;
    @FXML
    private TableColumn<Matricula, Integer> mesesColumna;
    @FXML
    private TableColumn<Matricula, Integer> totalColumna;
    @FXML
    private TableColumn<Matricula, Boolean> pagadoColumna;

    private Estudiantes_Controlador estudianteControlador;
    private MatriculaControladorJPA matriculaControlador;
    private Estudiantes estudianteActual; // Variable para almacenar el estudiante actual seleccionado 

    /**
     * Initializes the controller class.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estudianteControlador = new Estudiantes_Controlador();
        matriculaControlador = new MatriculaControladorJPA();

        
    }


    @FXML
    private void btnBuscar(ActionEvent event) {
        String nombre = nombreField.getText();
        String grado = gradoField.getText();

        estudianteActual = estudianteControlador.buscarEstudiantePorNombreYGrado(nombre, grado);
        if (estudianteActual != null) {
            cargarMatriculas(estudianteActual);
        } else {
            mostrarAlerta("No se encontró ningún estudiante con los datos proporcionados.", Alert.AlertType.INFORMATION);
        }
    }
    
    @FXML
    private void btnPagar(ActionEvent event) {
        Matricula matriculaSeleccionada = tablePagar.getSelectionModel().getSelectedItem();
        if (matriculaSeleccionada != null) {
            if (matriculaSeleccionada.getPagado()) {
                mostrarAlerta("El estudiante ya ha pagado su cuota.", Alert.AlertType.WARNING);
            } else {
                matriculaSeleccionada.setPagado(true);
                EntityManagerFactory emf = matriculaControlador.getEntityManagerFactory();
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = null;
                try {
                    tx = em.getTransaction();
                    tx.begin();
                    em.merge(matriculaSeleccionada);
                    tx.commit();
                    mostrarAlerta("Pago Realizado", Alert.AlertType.INFORMATION);
                    // Actualizar la lista de matrículas y refrescar la tabla
                    cargarMatriculas(estudianteActual);
                    tablePagar.refresh(); // Actualizar la visualización de la tabla
                } catch (Exception e) {
                    if (tx != null && tx.isActive()) {
                        tx.rollback();
                    }
                    mostrarAlerta("Error al realizar el pago", Alert.AlertType.ERROR);
                    e.printStackTrace();
                } finally {
                    em.close();
                }
            }
        } else {
            mostrarAlerta("No se ha seleccionado ninguna matrícula.", Alert.AlertType.WARNING);
        }
    }



    private void cargarMatriculas(Estudiantes estudiante) {
        ObservableList<Matricula> matriculas = matriculaControlador.cargarMatriculas();
        tablePagar.setItems(matriculas);

        idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        pensionColumna.setCellValueFactory(new PropertyValueFactory<>("pension"));
        mesesColumna.setCellValueFactory(new PropertyValueFactory<>("mesesDeuda"));
        pagadoColumna.setCellValueFactory(new PropertyValueFactory<>("pagado"));
        totalColumna.setCellValueFactory(cellData -> {
            int pension = cellData.getValue().getPension();
            int mesesDeuda = cellData.getValue().getMesesDeuda();
            int total = pension * mesesDeuda;
            return new SimpleIntegerProperty(total).asObject();
        });
    }

    
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Mensaje");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
