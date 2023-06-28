package mensualidad.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Usuarios.seleccionaTodos", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.seleccionaPorId", query = "SELECT u FROM Usuarios u WHERE u.usuarioId = :usuarioId")
})
public class Usuarios implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_Id", nullable = false)
    private Integer usuarioId;   
    @Column(name = "nombreUsuario", length = 40)    
    private String nombreUsuario;
    @Column(name = "contrasena", length = 40)
    private String contrasena;

    public Usuarios() {
        this(null,null);
    }

    public Usuarios(String nombreUsuario, String contrasena) {        
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String user) {
        this.nombreUsuario = user;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String password) {
        this.contrasena = password;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }
    @Override
    public String toString() {
        return "mensualidad.modelo.usuarios[ usuarioId=" + usuarioId + " ]";
    }         
}
