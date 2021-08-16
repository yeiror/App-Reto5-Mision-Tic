package com.example.controller;

import java.sql.SQLException;
import java.util.List;

import com.example.model.dao.ProyectosConsultaDao;
import com.example.model.vo.ProyectosConsultaVo;

public class ConsultasController {
    private ProyectosConsultaDao proyectosconsulta;

    public ConsultasController(){
        proyectosconsulta = new ProyectosConsultaDao();
        //El controlador se comunica con el DAO
    }

    public List<ProyectosConsultaVo> listadoProyectosDeClasificaciones(String opc1, String opc2) throws SQLException{  
        return proyectosconsulta.listarProyectosPorClasificaciones(opc1, opc2);
    }
}
