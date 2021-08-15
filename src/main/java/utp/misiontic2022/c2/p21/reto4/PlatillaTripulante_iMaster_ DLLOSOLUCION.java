/*
 * Pueda usar esta plantilla para la carga del reto a iMaster
 * Copie las clases de los paquetes Modelo, Vista, Controlador y Util
 * No incluya los import de los archivos .java solo las clases
 */
// Importaciones necesarias en iMaster
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Util (No modificar)
class JDBCUtilities {
    private static final String DATABASE_LOCATION = "ProyectosConstruccion.db";

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:"+DATABASE_LOCATION;

        return DriverManager.getConnection(url);
    }
}

// Remplace en adelante por las clases de sus archivos .java

// Vista
public class VistaRequerimientos {

    public static final ControladorRequerimientos controlador = new ControladorRequerimientos();

    public static void requerimiento1(){
        try {
            var requerimiento_1s = controlador.consultarRequerimiento1();

            for (Requerimiento_1 requerimiento_1 : requerimiento_1s) {
                
                System.out.printf("%s %d %n", requerimiento_1.getNombre_material(), requerimiento_1.getPrecio());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void requerimiento2(){
        try {
            
            var requerimiento_2s = controlador.consultarRequerimiento2();

            for (Requerimiento_2 requerimiento_2 : requerimiento_2s) {
                
                System.out.printf("%s %s %n", requerimiento_2.getConstructora(), requerimiento_2.getCiudad());
            }
            

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void requerimiento3(){
        try {
            var requerimiento_3s = controlador.consultarRequerimiento3();

            for (Requerimiento_3 requerimiento_3 : requerimiento_3s) {
                
                System.out.printf("%s %s %s %d %d %n", requerimiento_3.getProveedor(), requerimiento_3.getNombre_material(), requerimiento_3.getImportado(), requerimiento_3.getPrecio_unidad(), requerimiento_3.getCantidad());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}


// Controlador
public class ControladorRequerimientos {
    private Requerimiento_1Dao requerimiento_1Dao;
    private Requerimiento_2Dao requerimiento_2Dao;
    private Requerimiento_3Dao requerimiento_3Dao;


    public ControladorRequerimientos(){
        this.requerimiento_1Dao = new Requerimiento_1Dao();
        this.requerimiento_2Dao = new Requerimiento_2Dao();
        this.requerimiento_3Dao = new Requerimiento_3Dao();
    }

    
    public ArrayList<Requerimiento_1> consultarRequerimiento1() throws SQLException {
        return requerimiento_1Dao.requerimiento1();
    }

    public ArrayList<Requerimiento_2> consultarRequerimiento2() throws SQLException {
        return requerimiento_2Dao.requerimiento2();
    }

    public ArrayList<Requerimiento_3> consultarRequerimiento3() throws SQLException {
        return requerimiento_3Dao.requerimiento3();
    }
}

//                          Modelo
// VO

//******    Requerimiento 1

public class Requerimiento_1 {
    private String  nombre_material;
    private Integer precio;

    
    public String getNombre_material() {
        return nombre_material;
    }
    public void setNombre_material(String nombre_material) {
        this.nombre_material = nombre_material;
    }
    public Integer getPrecio() {
        return precio;
    }
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    
    
}

//******    Requerimiento 2

public class Requerimiento_2 {
    private String constructora;
    private String ciudad;

    
    public String getConstructora() {
        return constructora;
    }
    public void setConstructora(String constructora) {
        this.constructora = constructora;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
        
 
}


//******    Requerimiento 3

public class Requerimiento_3 {
    private String proveedor;
    private String nombre_material;
    private String importado;
    private Integer precio_unidad;
    private Integer cantidad;
    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    public String getNombre_material() {
        return nombre_material;
    }
    public void setNombre_material(String nombre_material) {
        this.nombre_material = nombre_material;
    }
    public String getImportado() {
        return importado;
    }
    public void setImportado(String importado) {
        this.importado = importado;
    }
    public Integer getPrecio_unidad() {
        return precio_unidad;
    }
    public void setPrecio_unidad(Integer precio_unidad) {
        this.precio_unidad = precio_unidad;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}


// DAO

/////// Requerimiento_1Dao

public class Requerimiento_1Dao {

    public ArrayList<Requerimiento_1> requerimiento1() throws SQLException {
        
        var response = new ArrayList<Requerimiento_1>();
  
        try(var connection = JDBCUtilities.getConnection();
            var statement = connection.prepareStatement("SELECT mc.Nombre_Material, mc.Precio_Unidad FROM MaterialConstruccion mc WHERE Importado = 'Si' ORDER BY Precio_Unidad  DESC;");
            var rset = statement.executeQuery();){

            while(rset.next()){

                var requerimiento1_VO = new Requerimiento_1();
                requerimiento1_VO.setNombre_material(rset.getString("Nombre_Material"));
                requerimiento1_VO.setPrecio(rset.getInt("Precio_Unidad"));

                response.add(requerimiento1_VO);
            }
        }

        return response;

}

}


/////// Requerimiento_2Dao

public class Requerimiento_2Dao {

    public ArrayList<Requerimiento_2> requerimiento2() throws SQLException {
        
        var response = new ArrayList<Requerimiento_2>();
        
        try(var connection = JDBCUtilities.getConnection();
            
            var statement = connection.prepareStatement("SELECT DISTINCT p.Constructora , p.Ciudad FROM Proyecto p WHERE p.Ciudad LIKE 'B%' ORDER BY p.Ciudad;");

            var rset = statement.executeQuery();)  {

                while(rset.next()){

                var requerimiento2_VO = new Requerimiento_2();

                requerimiento2_VO.setConstructora(rset.getString("Constructora"));

                requerimiento2_VO.setCiudad(rset.getString("Ciudad"));

                response.add(requerimiento2_VO);

                }

        }
        return response;
    }
}

/////// Requerimiento_3Dao

public class Requerimiento_3Dao {

    
    public ArrayList<Requerimiento_3> requerimiento3() throws SQLException {
        

        var response = new ArrayList<Requerimiento_3>();

        String consultaSQL = 
        
        "SELECT c.Proveedor , mc.Nombre_Material , mc.Importado , mc.Precio_Unidad, SUM(c.Cantidad) AS Cantidad"
        
        + " FROM Compra c " 

        + " JOIN MaterialConstruccion mc ON mc.ID_MaterialConstruccion = c.ID_MaterialConstruccion" 
        
        + " WHERE mc.Importado = 'Si'"
        
        + " AND c.Proveedor  = 'Homecenter'"
        
        + " GROUP BY mc.Nombre_Material , c.Proveedor"
        
        + " HAVING SUM(c.Cantidad) > 100" 
        
        + " ORDER BY mc.Nombre_Material , c.Proveedor";

        try(var connection = JDBCUtilities.getConnection();
            var statement = connection.prepareStatement(consultaSQL);
            var rset  = statement.executeQuery();){

            while (rset.next()){

                var requerimiento3_VO = new Requerimiento_3();
                requerimiento3_VO.setProveedor(rset.getString("Proveedor"));
                requerimiento3_VO.setNombre_material(rset.getString("Nombre_Material"));
                requerimiento3_VO.setImportado(rset.getString("Importado"));
                requerimiento3_VO.setPrecio_unidad(rset.getInt("Precio_Unidad"));
                requerimiento3_VO.setCantidad(rset.getInt("Cantidad"));

                response.add(requerimiento3_VO);
            }

        }

        return response;


    }
}