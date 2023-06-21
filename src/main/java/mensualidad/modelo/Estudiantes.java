
package mensualidad.modelo;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author USUARIO
 */

public class Estudiantes {
    private final StringProperty id;
    private final StringProperty nombreCompleto;
    private final StringProperty cuota;
    private final StringProperty totalPago;
    private final ObjectProperty<LocalDate> fechapago;
    public Estudiantes(){
        this(null, null);
    }
    public Estudiantes(String id, String nombreCompleto) {
       
        this.id = new SimpleStringProperty(id);
        this.nombreCompleto = new SimpleStringProperty(nombreCompleto);
        //Algunos datos ficticios iniciales, solo para pruebas convenientes.
        this.cuota = new SimpleStringProperty("700");
        this.totalPago = new SimpleStringProperty("2500");
        this.fechapago = new SimpleObjectProperty<>(LocalDate.of(2022, 1, 22));
    }
    public String getId(){
        return id.get();
    }
    
    public void setId(String id){
        this.id.set(id);
    }
    
    public StringProperty idProperty(){
        return id;
    }

    public String getNombreCompleto(){
        return nombreCompleto.get();
    }
    
    public void setNombreCompleto(String Nombre){
        this.nombreCompleto.set(Nombre);
    }
    
    public StringProperty nombreCompletoProperty(){
        return nombreCompleto;
    }
    public String getCuota(){
        return cuota.get();
    }
    
    public void setcuota(String cuota){
        this.cuota.set(cuota);
    }
    
    public StringProperty cuotaProperty(){
        return cuota;
    }
    public String getTotalPago(){
        return cuota.get();
    }
    
    public void setTotalPago(String totalPago){
        this.totalPago.set(totalPago);
    }
    
    public StringProperty totalPagoProperty(){
        return totalPago;
    }
    public LocalDate getFechaPago(){
        return fechapago.get();
    }
    
    public void setFechaPago(LocalDate fechapago){
        this.fechapago.set(fechapago);
    }
    
    public  ObjectProperty<LocalDate> fechaPagoProperty(){
        return fechapago;               
    }  
}
