package com.example.model.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.vo.ProyectoBancoVo;
import com.example.util.JDBCUtilities;

public class ProyectoBancoDao{
    
    public List<ProyectoBancoVo> listarProyectosporBanco(String bancoVinculado) throws SQLException{
        List<ProyectoBancoVo> respuesta = new ArrayList<>();

        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset= null;

        try {
            var query= "SELECT p.ID_Proyecto AS ID, Constructora, Ciudad, p.Clasificacion, t.Estrato,"+ 
            " (l.Nombre||' '||l.Primer_Apellido|| ' '|| l.Segundo_Apellido) as LIDER"+ 
            " FROM Proyecto p"+
            " INNER JOIN Tipo t ON p.ID_Tipo = t.ID_Tipo"+
            " INNER JOIN Lider l on p.ID_Lider = l.ID_Lider"+
            " WHERE Banco_Vinculado =?"+ 
            " ORDER BY p.Fecha_Inicio DESC, p.Ciudad asc, p.Constructora";

            stmt=conn.prepareStatement(query);
            stmt.setString(1, bancoVinculado);
            rset = stmt.executeQuery();
            while(rset.next())
            {
                var vo = new ProyectoBancoVo();
                vo.setId(rset.getInt("ID"));
                vo.setConstructora(rset.getString("CONSTRUCTORA"));
                vo.setCiudad(rset.getString("CIUDAD"));
                vo.setClasificacion(rset.getString("CLASIFICACION"));
                vo.setEstrato(rset.getInt("ESTRATO"));
                vo.setLider(rset.getString("LIDER"));

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