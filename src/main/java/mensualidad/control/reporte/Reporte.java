package mensualidad.control.reporte;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class Reporte {
    private String reporte;
    
    public Reporte(String reporte){
        this.reporte = reporte;
    }
    
    public void generarReporte(){
        try {
            // Obtiene el EntityManager a través de la unidad de persistencia "BaseDatos"
            EntityManager entityManager = Persistence.createEntityManagerFactory("BaseDatos").createEntityManager();
            
            // Obtiene la conexión a partir del EntityManager
            Connection conn = entityManager.unwrap(Connection.class);
            
            // Código para generar el informe utilizando JasperReports
            String rutaJRXML = "src/main/java/mensualidad/control/reporte/Cherry.jrxml";
            File archivoJRXML = new File(rutaJRXML);
            InputStream dir = new FileInputStream(archivoJRXML);
            JasperReport jr = JasperCompileManager.compileReport(dir);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            
            // Muestra el informe en un JFrame
            JRViewer viewer = new JRViewer(jp);
            JFrame frame = new JFrame("Reporte");
            frame.getContentPane().add(viewer);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.pack();
            frame.setVisible(true);
            
            // Cierra el EntityManager
            entityManager.close();
        } catch (Exception ex) {
            System.out.println("Error al generar el reporte " + this.reporte + ": " + ex);
        }
    }
}
