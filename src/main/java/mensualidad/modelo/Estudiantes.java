package mensualidad.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(catalog = "javafx", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Estudiantes.seleccionaTodos", query = "SELECT e FROM Estudiantes e"),
    @NamedQuery(name = "Estudiantes.seleccionaPorId", query = "SELECT e FROM Estudiantes e WHERE e.estudiantesId = :EstudiantesId")})
public class Estudiantes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "estudiante_id", nullable = false)
    private Integer estudiantesId;
    
    @Column(name = "nombre_completo", length = 30)
    private String nombreCompleto;
    
    @Column(name = "cuota", length = 30)
    private String cuota;
    
    @Column(name = "total_pago", length = 30)
    private String totalPago;
    
    @Column(name = "fecha_pago")
    private LocalDate fechapago;
    
    @Column(name = "grado_estudios", length = 30)
    private String gradoEstudios;

    public Estudiantes() {
        this(null,null);
    }

    public Estudiantes(String nombreCompleto, String gradoEstudios) {
        this.nombreCompleto = nombreCompleto;
        this.gradoEstudios = gradoEstudios;
        // Algunos datos ficticios iniciales, solo para pruebas convenientes.
        this.cuota = "700";
        this.totalPago = "2500";
        this.fechapago = LocalDate.of(2022, 1, 22);
    }

    public Integer getId() {
        return estudiantesId;
    }

    public void setId(Integer id) {
        this.estudiantesId = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public String getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(String totalPago) {
        this.totalPago = totalPago;
    }

    public LocalDate getFechaPago() {
        return fechapago;
    }

    public void setFechaPago(LocalDate fechapago) {
        this.fechapago = fechapago;
    }

    public String getGradoEstudios() {
        return gradoEstudios;
    }

    public void setGradoEstudios(String gradoEstudios) {
        this.gradoEstudios = gradoEstudios;
    }

    // Propiedades de JavaFX para su uso en JavaFX UI
    public IntegerProperty idProperty() {
    if (estudiantesId != null) {
        return new SimpleIntegerProperty(estudiantesId.intValue());
    } else {
        return new SimpleIntegerProperty(0); // Valor por defecto en caso de que estudiantesId sea nulo
    }
    }

    public StringProperty nombreCompletoProperty() {
        return new SimpleStringProperty(nombreCompleto);
    }

    public StringProperty cuotaProperty() {
        return new SimpleStringProperty(cuota);
    }

    public StringProperty totalPagoProperty() {
        return new SimpleStringProperty(totalPago);
    }
    public StringProperty gradoEstudiosProperty() {
        return new SimpleStringProperty(gradoEstudios);
    }    

    public ObjectProperty<LocalDate> fechaPagoProperty() {
        return new SimpleObjectProperty<>(fechapago);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudiantesId != null ? estudiantesId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "mensualidad.modelo.estudiantes[ estudiantesId=" + estudiantesId + " ]";
    }    
}
