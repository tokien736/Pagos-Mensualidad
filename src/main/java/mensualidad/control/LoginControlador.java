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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mensualidad.modelo.Usuarios;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class LoginControlador implements Initializable {

    @PersistenceContext
    private EntityManager entityManager;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField password;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
    }    

    @FXML
    public void ingresar(ActionEvent event) {
        String nombreUsuario = usuario.getText();
        String contrasenaUsuario = password.getText();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos");
        EntityManager em = emf.createEntityManager();

        try {
            // Consultar el usuario por nombre de usuario
            TypedQuery<Usuarios> query = em.createQuery("SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario", Usuarios.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            Usuarios usuario = query.getSingleResult();

            // Verificar la contraseña
            if (usuario != null && usuario.getContrasena().equals(contrasenaUsuario)) {
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
            } else {
                // Mostrar un mensaje de alerta si la contraseña es incorrecta
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error de autenticación");
                alert.setHeaderText(null);
                alert.setContentText("El nombre de usuario o la contraseña son incorrectos");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }


    @FXML
    public void salir(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
     
}
