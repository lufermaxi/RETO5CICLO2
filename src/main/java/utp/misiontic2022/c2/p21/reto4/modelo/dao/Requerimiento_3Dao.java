package utp.misiontic2022.c2.p21.reto4.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utp.misiontic2022.c2.p21.reto4.modelo.vo.Requerimiento_2;
import utp.misiontic2022.c2.p21.reto4.modelo.vo.Requerimiento_3;
import utp.misiontic2022.c2.p21.reto4.util.JDBCUtilities;

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