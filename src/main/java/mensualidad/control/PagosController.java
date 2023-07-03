package mensualidad.control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mensualidad.control.JPA.Estudiantes_Controlador;
import mensualidad.control.JPA.MatriculaControladorJPA;
import mensualidad.control.JPA.PagoControladorJPA;
import mensualidad.modelo.Estudiantes;
import mensualidad.modelo.Matricula;
import mensualidad.modelo.Pago;

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
    private TableView<Pago> tablePagar;
    @FXML
    private TableColumn<Pago, String> idColumna;
    @FXML
    private TableColumn<Pago, Integer> pensionColumna;
    @FXML
    private TableColumn<Pago, Integer> mesesColumna;
    @FXML
    private TableColumn<Pago, Integer> totalColumna;
    @FXML
    private TableColumn<Pago, Integer> pagadoColumna;

    private Estudiantes_Controlador estudianteControlador;
    private MatriculaControladorJPA matriculaControlador;
    private PagoControladorJPA pagoControlador;

    /**
     * Initializes the controller class.
     */
    @FXML    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estudianteControlador = new Estudiantes_Controlador();
        matriculaControlador = new MatriculaControladorJPA();
        pagoControlador = new PagoControladorJPA();

        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("estudianteNombreCompleto"));
        deudaColumna.setCellValueFactory(new PropertyValueFactory<>("deuda"));
        fechaVencimientoColumna.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));

        cargarEstudiantes();
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        String nombre = nombreField.getText();
        String grado = gradoField.getText();

        if (!nombre.isEmpty() && !grado.isEmpty()) {
            Estudiantes estudiante = estudianteControlador.buscarEstudiantePorNombreYGrado(nombre, grado);
            if (estudiante != null) {
                //asd
            } else {
                mostrarAlerta("Estudiante no encontrado.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Ingresa el nombre y el grado del estudiante.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnPagar(ActionEvent event) {
        Pago pagoSeleccionado = tablePagar.getSelectionModel().getSelectedItem();
        if (pagoSeleccionado != null) {
            // Aquí puedes implementar la lógica para realizar el pago
            // Por ejemplo, actualizar la deuda del pago a cero y guardar los cambios en la base de datos
            // También puedes mostrar un mensaje de éxito si se realizó el pago correctamente
            // O mostrar un mensaje de error si no se pudo realizar el pago
        } else {
            mostrarAlerta("Selecciona un pago de la tabla.", Alert.AlertType.WARNING);
        }
    }

    private void cargarEstudiantes() {
        tablePagar.getItems().clear();
        tablePagar.setItems(pagoControlador.cargarPagos());
    }

    private void cargarMatriculas(Estudiantes estudiante) {
        tablePagar.getItems().clear();
        tablePagar.setItems(pagoControlador.cargarPagosPorEstudiante(estudiante));
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Mensaje");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
