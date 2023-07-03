package mensualidad.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import mensualidad.modelo.Usuarios;

public class App extends Application {

    private Stage primaryStage;
    private AnchorPane loginLayout;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Pagos De Mensualidad App");      
        initlogin(); 
        crearUsuarioBase();          
    }
        

    private void initlogin() {
        try {
            // Cargar el root layout el archivo file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/mensualidad/vista/login.fxml"));
            loginLayout = (AnchorPane) loader.load();

            //Visualizar la escena que contiene el root layout 
            Scene scene = new Scene(loginLayout);
            primaryStage.setScene(scene);
            primaryStage.show();            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void crearUsuarioBase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            String nombreUsuario = "admin";

            // Buscar un usuario con el mismo nombre de usuario
            TypedQuery<Usuarios> query = em.createQuery("SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario", Usuarios.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            List<Usuarios> usuariosExistente = query.getResultList();

            if (!usuariosExistente.isEmpty()) {
                // Eliminar el usuario existente si se encuentra
                Usuarios usuarioExistente = usuariosExistente.get(0);
                em.remove(usuarioExistente);
            }

            // Crear el nuevo usuario
            Usuarios nuevoUsuario = new Usuarios("admin", "americo");
            em.persist(nuevoUsuario);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }


    }
    
}



