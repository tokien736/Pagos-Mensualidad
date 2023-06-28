
package mensualidad.control.JPA;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import mensualidad.modelo.Estudiantes;

/**
 * FXML Controller class
 *
 * @author joo
 */
public class ReportesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtfieldEstudiante;
    @FXML
    private TextField txtfieldGrado;    
    @FXML
    private TableView<Estudiantes> tablaResultados;
    @FXML
    private TableColumn<Estudiantes, String> idColumna;
    @FXML
    private TableColumn<Estudiantes, String> nombreCompletoColumna;
    @FXML
    private TableColumn<Estudiantes, String> gradoColumna;
    @FXML
    private TableColumn<Estudiantes, LocalDate> fechaPagoColumna;
    @FXML
    private TableColumn<Estudiantes, String> totalPagoColumna;
    
    //Busqueda para los estudiantes matriculados
    @FXML
    private TextField txtfieldGrado2;
    @FXML
    private TableView<Estudiantes> tablaResultados2;
    @FXML
    private TableColumn<Estudiantes, String> idColumna2;
    @FXML
    private TableColumn<Estudiantes, String> nombreCompletoColumna2;
    @FXML
    private TableColumn<Estudiantes, String> gradoColumna2;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreCompletoColumna.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        fechaPagoColumna.setCellValueFactory(new PropertyValueFactory<>("fechaPago"));
        totalPagoColumna.setCellValueFactory(new PropertyValueFactory<>("totalPago"));
        gradoColumna.setCellValueFactory(new PropertyValueFactory<>("gradoEstudios"));
        
        idColumna2.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreCompletoColumna2.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        gradoColumna2.setCellValueFactory(new PropertyValueFactory<>("gradoEstudios"));
        
    }

    @FXML
    private void handleBuscarButtonAction(ActionEvent event) {
        String parametroNom = txtfieldEstudiante.getText();
        String parametroGrado = txtfieldGrado.getText();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos"); 
        EntityManager em = emf.createEntityManager();

        TypedQuery<Estudiantes> query = em.createQuery("SELECT e FROM Estudiantes e WHERE e.nombreCompleto = :parametroNom OR e.gradoEstudios = :parametroGrado", Estudiantes.class); 
        
        query.setParameter("parametroNom", parametroNom);
        query.setParameter("parametroGrado", parametroGrado);
        List<Estudiantes> resultados = query.getResultList();
       
        // Convertir la lista de resultados en un ObservableList
        ObservableList<Estudiantes> data = FXCollections.observableArrayList(resultados);

        // Asignar el ObservableList a la TableView
        tablaResultados.setItems(data);

    }
    @FXML
    private void handleBuscar2ButtonAction(ActionEvent event) {
        String parametro = txtfieldGrado2.getText();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos"); 
        EntityManager em = emf.createEntityManager();

        TypedQuery<Estudiantes> query = em.createQuery("SELECT e FROM Estudiantes e WHERE e.gradoEstudios = :parametroGrado2", Estudiantes.class); 
        
        query.setParameter("parametroGrado2", parametro);
        List<Estudiantes> resultados = query.getResultList();
       
        // Convertir la lista de resultados en un ObservableList
        ObservableList<Estudiantes> data = FXCollections.observableArrayList(resultados);

        // Asignar el ObservableList a la TableView
        tablaResultados2.setItems(data);

    }
}
    
