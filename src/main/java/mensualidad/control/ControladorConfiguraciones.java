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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mensualidad.modelo.Usuarios;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class ControladorConfiguraciones implements Initializable {

    @FXML
    private TextField antCont;
    @FXML
    private TextField nueCont;
    @FXML
    private TextField conCont;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardar(ActionEvent event) {
        String antiguaContrasena = antCont.getText();
        String nuevaContrasena = nueCont.getText();
        String confirmarContrasena = conCont.getText();

        Usuarios usuarioAutenticado = UsuarioAutenticado.getInstance().getUsuario();
        
        if (usuarioAutenticado != null) {
            // Verificar que la contraseña antigua coincida con la almacenada
            if (usuarioAutenticado.getContrasena().equals(antiguaContrasena)) {
                // Verificar que la nueva contraseña y la confirmación coincidan
                if (nuevaContrasena.equals(confirmarContrasena)) {
                    // Actualizar la contraseña del usuario
                    usuarioAutenticado.setContrasena(nuevaContrasena);
                    
                    // Guardar los cambios en la base de datos (puedes adaptarlo según tu implementación)
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos");
                    EntityManager em = emf.createEntityManager();
                    try {
                        em.getTransaction().begin();
                        em.merge(usuarioAutenticado);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                        em.getTransaction().rollback();
                        e.printStackTrace();
                    } finally {
                        em.close();
                        emf.close();
                    }
                    
                    // Mostrar un mensaje de éxito o realizar otras acciones
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Cambio de Contraseña");
                    alert.setHeaderText(null);
                    alert.setContentText("¡La contraseña se ha cambiado exitosamente!");
                    alert.showAndWait();
                    
                    // Limpiar los campos de texto
                    antCont.clear();
                    nueCont.clear();
                    conCont.clear();
                } else {
                    // Mostrar un mensaje de error si la nueva contraseña y la confirmación no coinciden
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error de Contraseña");
                    alert.setHeaderText(null);
                    alert.setContentText("La nueva contraseña y la confirmación no coinciden");
                    alert.showAndWait();
                }
            } else {
                // Mostrar un mensaje de error si la contraseña antigua no coincide
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error de Contraseña");
                alert.setHeaderText(null);
                alert.setContentText("La contraseña antigua es incorrecta");
                alert.showAndWait();
            }
        }
    }
}
