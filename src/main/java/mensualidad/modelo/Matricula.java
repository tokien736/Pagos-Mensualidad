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
import javax.persistence.Basic;
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

@Entity
@Table(catalog = "javafx", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Matricula.seleccionaTodos", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.seleccionaPorId", query = "SELECT m FROM Matricula m WHERE m.matriculaId = :matriculaId")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "matricula_id", nullable = false)
    private Integer matriculaId;

    @JoinColumn(name = "estudiante_id", referencedColumnName = "estudiante_id", nullable = false)
    @ManyToOne
    private Estudiantes estudiante;

    @Column(name = "cuota", length = 30)
    private String cuota;

    @Column(name = "meses_deuda")
    private int mesesDeuda;

    @Column(name = "pagado")
    private boolean pagado;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    public Matricula() {
        this(null, null, 0, false, null);
    }

    public Matricula(Estudiantes estudiante, String cuota, int mesesDeuda, boolean pagado, LocalDate fechaPago) {
        this.estudiante = estudiante;
        this.cuota = cuota;
        this.mesesDeuda = mesesDeuda;
        this.pagado = pagado;
        this.fechaPago = fechaPago;
    }

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Estudiantes getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiantes estudiante) {
        this.estudiante = estudiante;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public int getMesesDeuda() {
        return mesesDeuda;
    }

    public void setMesesDeuda(int mesesDeuda) {
        this.mesesDeuda = mesesDeuda;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    // Propiedades de JavaFX para su uso en JavaFX UI
    public IntegerProperty matriculaIdProperty() {
        return new SimpleIntegerProperty(matriculaId);
    }

    public ObjectProperty<Estudiantes> estudianteProperty() {
        return new SimpleObjectProperty<>(estudiante);
    }

    public StringProperty cuotaProperty() {
        return new SimpleStringProperty(cuota);
    }

    public IntegerProperty mesesDeudaProperty() {
        return new SimpleIntegerProperty(mesesDeuda);
    }

    public BooleanProperty pagadoProperty() {
        return new SimpleBooleanProperty(pagado);
    }

    public ObjectProperty<LocalDate> fechaPagoProperty() {
        return new SimpleObjectProperty<>(fechaPago);
    }

    public IntegerProperty idProperty() {
        if (matriculaId != null) {
            return new SimpleIntegerProperty(matriculaId);
        } else {
            return new SimpleIntegerProperty(0); // Valor por defecto en caso de que matriculaId sea nulo
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculaId != null ? matriculaId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "mensualidad.modelo.Matricula[ matriculaId=" + matriculaId + " ]";
    }
}


