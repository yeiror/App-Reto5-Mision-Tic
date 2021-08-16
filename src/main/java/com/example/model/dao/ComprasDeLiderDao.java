package com.example.model.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.vo.ComprasDeLiderVo;
import com.example.util.JDBCUtilities;

public class ComprasDeLiderDao {
   public List<ComprasDeLiderVo> listarLideresCompras() throws SQLException
   {
      List<ComprasDeLiderVo> respuesta = new ArrayList<>();
      var conn = JDBCUtilities.getConnection();
      PreparedStatement stmt = null;
      ResultSet rset= null;

      try 
      { 
         var query= "SELECT (l.Nombre||' '||l.Primer_Apellido|| ' '|| l.Segundo_Apellido) as LIDER,"+
         " sum(mc.Precio_Unidad*c.Cantidad) as VALOR"+
         " FROM Lider l inner join Proyecto p on l.ID_Lider =p.ID_Lider"+
         " INNER JOIN Compra c on c.ID_Proyecto = p.ID_Proyecto"+
         " INNER JOIN MaterialConstruccion mc  on mc.ID_MaterialConstruccion =c.ID_MaterialConstruccion"+
         " GROUP BY Lider"+
         " ORDER BY VALOR ASC"+
         " LIMIT 10";
            stmt=conn.prepareStatement(query);
            rset = stmt.executeQuery();
            while(rset.next())
            {
                var vo = new ComprasDeLiderVo();
                vo.setLider(rset.getString("LIDER"));
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
   