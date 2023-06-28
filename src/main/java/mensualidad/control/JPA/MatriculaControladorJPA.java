/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensualidad.control.JPA;

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

/**
 *
 * @author joo
 */
public class MatriculaControladorJPA {

    private ObservableList<Matricula> datoMatricula = FXCollections.observableArrayList();
    private EntityManagerFactory emf;

    public ObservableList<Matricula> getDatoMatricula() {
        return datoMatricula;
    }

    public MatriculaControladorJPA() {
        this.emf = Persistence.createEntityManagerFactory("BaseDatos");
    }

    public ObservableList<Matricula> cargarMatriculas() {
        ObservableList<Matricula> datoMatricula = FXCollections.observableArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Matricula> query = em.createQuery("SELECT m FROM Matricula m", Matricula.class);
            List<Matricula> Matricula = query.getResultList();

            // Agregar los estudiantes a la lista observable
            datoMatricula.addAll(Matricula);

            return datoMatricula;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return datoMatricula;
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
