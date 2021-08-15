package utp.misiontic2022.c2.p21.reto4.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utp.misiontic2022.c2.p21.reto4.modelo.vo.Requerimiento_2;
import utp.misiontic2022.c2.p21.reto4.util.JDBCUtilities;

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