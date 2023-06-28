package mensualidad.control;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import mensualidad.modelo.Estudiantes;
import mensualidad.modelo.Matricula;

public class Matricula_Controlador {
    private ObservableList<Matricula> datosMatricula = FXCollections.observableArrayList();
    public ObservableList<Matricula> getDatosMatricula() {
        return datosMatricula;
    }

    private EntityManagerFactory emf;

    public Matricula_Controlador() {
        this.emf = Persistence.createEntityManagerFactory("BaseDatos");
    }

    public ObservableList<Matricula> cargarMatriculas() {
        ObservableList<Matricula> datosMatricula = FXCollections.observableArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Matricula> query = em.createQuery("SELECT m FROM Matricula m", Matricula.class);
            List<Matricula> matriculas = query.getResultList();

            // Agregar las matriculas a la lista observable
            datosMatricula.addAll(matriculas);

            return datosMatricula;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return datosMatricula;
    }
    
    public void insertarMatricula(Matricula matricula) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(matricula);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

