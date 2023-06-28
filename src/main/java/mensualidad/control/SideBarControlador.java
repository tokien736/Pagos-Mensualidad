
package mensualidad.control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mensualidad.modelo.Estudiantes;

/**
 *
 * @author PC-Jose
 */
public class SideBarControlador implements Initializable{
        
    @FXML
    private BorderPane bp;

    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        showEstudiantes();
    }

    

    @FXML
    private void estudiante(MouseEvent event) {
        try {
            // cargar persona overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/mensualidad/vista/estudiantes.fxml"));
            AnchorPane Estudiantes = (AnchorPane) loader.load();
            
            //Colocar persona overview dentro en el centro de root layout
            bp.setCenter(Estudiantes);
            
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @FXML
    private void pagos(MouseEvent event) {
        cargarPaginas("pagos");
    }

    @FXML
    private void reportes(MouseEvent event) {
        cargarPaginas("reportes");
    }

    @FXML
    private void configuraciones(MouseEvent event) {
        cargarPaginas("configuraciones");        
    }

    @FXML
    private void cerrarSesion(MouseEvent event) {
    try {
        // Cargar el archivo FXML de la ventana de inicio de sesión
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mensualidad/vista/login.fxml"));
        Parent root = loader.load();

        // Crear una nueva escena
        Scene scene = new Scene(root);

        // Obtener la ventana actual
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Crear una nueva ventana
        Stage loginStage = new Stage();
        loginStage.setScene(scene);
        // Mostrar la nueva ventana y cerrar la actual
        loginStage.show();
        currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
    private void cargarPaginas(String page){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/mensualidad/vista/" + page + ".fxml"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        bp.setCenter(root);
    }
    private void showEstudiantes() {
        try {
            // cargar persona overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/mensualidad/vista/estudiantes.fxml"));
            AnchorPane Estudiantes = (AnchorPane) loader.load();
            
            //Colocar persona overview dentro en el centro de root layout
            bp.setCenter(Estudiantes);
            
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }    


}
