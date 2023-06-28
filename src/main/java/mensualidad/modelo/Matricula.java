package mensualidad.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
 * @author joo
 */
@Entity
@Table(catalog = "javafx", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Matricula.seleccionaTodos", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.seleccionaPorId", query = "SELECT m FROM Matricula m WHERE m.matriculaId = :matriculaId")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matriculaid", nullable = false)
    private Integer matriculaId;
    @Column(name = "fechaPago")
    private LocalDate fechaPago;
    @Column(name = "pension")
    private Integer pension;
    @Column(name = "mesesDeuda")
    private Integer mesesDeuda;
    @Column(name = "estado")
    private Boolean pagado;

    @JoinColumn(name = "estudianteId", referencedColumnName = "estudiante_id")
    @ManyToOne
    private Estudiantes estudiante;

    public Matricula() {
        this(null,0,0,false,null);
    }

    public Matricula(LocalDate fechaPago, Integer pension, Integer mesesDeuda, Boolean pagado, Estudiantes estudiante) {
        this.fechaPago = fechaPago;
        this.pension = pension;
        this.mesesDeuda = mesesDeuda;
        this.pagado = pagado;
        this.estudiante = estudiante;
    }

    

    
    public Integer getMatriculaID() {
        return matriculaId;
    }

    public void setMatriculaID(Integer matriculaID) {
        this.matriculaId = matriculaID;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getPension() {
        return pension;
    }

    public void setPension(Integer pension) {
        this.pension = pension;
    }

    public Integer getMesesDeuda() {
        return mesesDeuda;
    }

    public void setMesesDeuda(Integer mesesDeuda) {
        this.mesesDeuda = mesesDeuda;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public Estudiantes getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiantes estudiante) {
        this.estudiante = estudiante;
    }

    // Propiedades de JavaFX para su uso en JavaFX UI
    public IntegerProperty idProperty() {
        if (matriculaId != null) {
            return new SimpleIntegerProperty(matriculaId.intValue());
        } else {
            return new SimpleIntegerProperty(0); // Valor por defecto en caso de que estudiantesId sea nulo
        }
    }

    public ObjectProperty<LocalDate> fechaPagoProperty() {
        return new SimpleObjectProperty<>(fechaPago);
    }

    public IntegerProperty PensionProperty() {
        return new SimpleIntegerProperty(pension);
    }

    public IntegerProperty MesesDeudaProperty() {
        return new SimpleIntegerProperty(mesesDeuda);
    }

    public BooleanProperty isActiveProperty() {
        return new SimpleBooleanProperty(pagado);
    }

    public ObjectProperty<Estudiantes> estudianteProperty() {
        return new SimpleObjectProperty<>(estudiante);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculaId != null ? matriculaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.matriculaId == null && other.matriculaId != null) || (this.matriculaId != null && !this.matriculaId.equals(other.matriculaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mensualidad.modelo.Matricula[ id=" + matriculaId + " ]";
    }

}
