package com.example.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.example.model.vo.ProyectosConsultaVo;
import com.example.util.JDBCUtilities;

public class ProyectosConsultaDao {
    public List<ProyectosConsultaVo> listarProyectosPorClasificaciones(String clasi1, String clasi2) throws SQLException {
        List<ProyectosConsultaVo> respuesta = new ArrayList<>();
        
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt=null;
        ResultSet rset=null;

        try{
        var query= "SELECT CIUDAD, CLASIFICACION, COUNT(*) as TOTAL,"
            +" min(FECHA_INICIO) as VIEJO, max(FECHA_INICIO) as RECIENTE" 
            +" from PROYECTO p WHERE Clasificacion in (?,?)"
            +" GROUP BY CIUDAD, CLASIFICACION"
            +" ORDER BY CIUDAD, CLASIFICACION";

        stmt = conn.prepareStatement(query);
        stmt.setString(1, clasi1);
        stmt.setString(2, clasi2);
        rset = stmt.executeQuery();
        while(rset.next())
        {
            var vo= new ProyectosConsultaVo();
            vo.setCiudad(rset.getString("CIUDAD"));
            vo.setClasificacion(rset.getString("CLASIFICACION"));
            vo.setTotal(rset.getInt("TOTAL"));
            vo.setViejo(rset.getString("VIEJO"));
            vo.setReciente(rset.getString("RECIENTE"));
            respuesta.add(vo);
        }
    }   finally{
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
