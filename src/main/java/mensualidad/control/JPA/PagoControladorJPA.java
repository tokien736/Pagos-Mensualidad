package mensualidad.control.JPA;

import java.time.LocalDate;
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
import mensualidad.modelo.Pago;

public class PagoControladorJPA {
    private ObservableList<Pago> datoPago = FXCollections.observableArrayList();
    private EntityManagerFactory emf;

    public ObservableList<Pago> getDatoPago() {
        return datoPago;
    }

    public PagoControladorJPA() {
        this.emf = Persistence.createEntityManagerFactory("BaseDatos");
    }

    public ObservableList<Pago> cargarPagos() {
        ObservableList<Pago> datoPago = FXCollections.observableArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Pago> query = em.createQuery("SELECT p FROM Pago p", Pago.class);
            List<Pago> pagos = query.getResultList();

            // Agregar los pagos a la lista observable
            datoPago.addAll(pagos);

            return datoPago;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return datoPago;
    }
    
    public ObservableList<Pago> cargarPagosPorEstudiante(Estudiantes estudiante) {
        ObservableList<Pago> datoPago = FXCollections.observableArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Pago> query = em.createQuery("SELECT p FROM Pago p WHERE p.estudiante = :estudiante", Pago.class);
            query.setParameter("estudiante", estudiante);
            List<Pago> pagos = query.getResultList();

            // Agregar los pagos a la lista observable
            datoPago.addAll(pagos);

            return datoPago;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return datoPago;
    }

    public void insertarPago(Estudiantes estudiante, Matricula matricula, int deuda, LocalDate fechaVencimiento) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Pago pago = new Pago(estudiante, matricula, deuda, fechaVencimiento);
            em.persist(pago);
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

