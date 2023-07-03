/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensualidad.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author PC-Jose
 */

@Entity
@Table(catalog = "javafx", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Pago.seleccionaTodos", query = "SELECT p FROM Pago p"),
    @NamedQuery(name = "Pago.seleccionaPorId", query = "SELECT p FROM Pago p WHERE p.idPago = :idPago")
})
public class Pago implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPago")
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "idEstudiante")
    private Estudiantes estudiante;

    @ManyToOne
    @JoinColumn(name = "idMatricula")
    private Matricula matricula;

    @Column(name = "deuda")
    private int deuda;

    @Column(name = "fechaVencimiento")
    private LocalDate fechaVencimiento;

    public Pago() {
    }

    public Pago(Estudiantes estudiante, Matricula matricula, int deuda, LocalDate fechaVencimiento) {
        this.estudiante = estudiante;
        this.matricula = matricula;
        this.deuda = deuda;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Estudiantes getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiantes estudiante) {
        this.estudiante = estudiante;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public int getDeuda() {
        return deuda;
    }

    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    // Propiedades de JavaFX para su uso en JavaFX UI
    public ObjectProperty<Estudiantes> estudianteProperty() {
        return new SimpleObjectProperty<>(estudiante);
    }

    public ObjectProperty<Matricula> matriculaProperty() {
        return new SimpleObjectProperty<>(matricula);
    }

    public IntegerProperty deudaProperty() {
        return new SimpleIntegerProperty(deuda);
    }

    public ObjectProperty<LocalDate> fechaVencimientoProperty() {
        return new SimpleObjectProperty<>(fechaVencimiento);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mensualidad.modelo.Pago[ idPago=" + idPago + " ]";
    }
    
}
