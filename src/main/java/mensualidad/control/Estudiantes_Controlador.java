package mensualidad.control;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import mensualidad.modelo.Estudiantes;

public class Estudiantes_Controlador {
    private ObservableList<Estudiantes> datoEstudiante = FXCollections.observableArrayList();
    public ObservableList<Estudiantes> getDatoEstudiantes() {
        return datoEstudiante;
    }
private EntityManagerFactory emf;
public Estudiantes_Controlador() {
    this.emf = Persistence.createEntityManagerFactory("BaseDatos");
}
    public ObservableList<Estudiantes> cargarEstudiantes() {
        ObservableList<Estudiantes> datoEstudiante = FXCollections.observableArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Estudiantes> query = em.createQuery("SELECT e FROM Estudiantes e", Estudiantes.class);
            List<Estudiantes> estudiantes = query.getResultList();

            // Agregar los estudiantes a la lista observable
            datoEstudiante.addAll(estudiantes);

            return datoEstudiante;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return datoEstudiante;
    }
    public void insertarEstudiante(Estudiantes estudiante) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(estudiante);
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
    public Estudiantes buscarEstudiante(String nombre, String grado) {
        EntityManager em = emf.createEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM Estudiantes e WHERE e.nombreCompleto = :nombre AND e.gradoEstudios = :grado");
            consulta.setParameter("nombre", nombre);
            consulta.setParameter("grado", grado);
            List<Estudiantes> resultados = consulta.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0);
            }
        } finally {
            em.close();
        }
        return null; // Si no se encuentra ningún estudiante con los datos proporcionados
    }
    
}
