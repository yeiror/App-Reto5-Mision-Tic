package com.example.model.dao;
import com.example.model.vo.PagadoPorProyectoVo;
import com.example.util.JDBCUtilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagadoPorProyectoDao {
    public List<PagadoPorProyectoVo> listarTotalPagado(Double limite_inferior) throws SQLException
    {
        List<PagadoPorProyectoVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt=null;
        ResultSet rset=null;

        try
        {
            var query="SELECT  p.ID_Proyecto as ID , sum(c.Cantidad*mc.Precio_Unidad)as VALOR"+
            " FROM Proyecto p INNER JOIN Compra c on c.ID_Proyecto = p.ID_Proyecto"+
            " INNER JOIN MaterialConstruccion mc on mc.ID_MaterialConstruccion =c.ID_MaterialConstruccion"+
            " WHERE c.Pagado ='Si' GROUP BY p.ID_Proyecto"+
            " HAVING SUM(c.Cantidad*mc.Precio_Unidad) > ?"+
            " ORDER BY VALOR DESC";
            stmt=conn.prepareStatement(query);
            stmt.setDouble(1, limite_inferior);
            rset= stmt.executeQuery();
            while(rset.next()){
                var vo= new PagadoPorProyectoVo();
                vo.setId(rset.getInt("ID"));
                vo.setValor(rset.getFloat("VALOR"));

                respuesta.add(vo);
            }
        }finally
        {
            if(rset!=null){
                rset.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }

        return respuesta;
    }
   }