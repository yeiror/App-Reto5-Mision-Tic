package com.example.controller;

import java.sql.SQLException;
import java.util.List;

import com.example.model.dao.ComprasDeLiderDao;
import com.example.model.dao.PagadoPorProyectoDao;
import com.example.model.dao.ProyectoBancoDao;
import com.example.model.vo.ComprasDeLiderVo;
import com.example.model.vo.PagadoPorProyectoVo;
import com.example.model.vo.ProyectoBancoVo;

public class ReportesController {
    
    private ProyectoBancoDao proyectosbanco;
    private PagadoPorProyectoDao pagadoPorProyecto;
    private ComprasDeLiderDao comprasDeLider;

    public ReportesController(){
        proyectosbanco = new ProyectoBancoDao();
        pagadoPorProyecto= new PagadoPorProyectoDao();
        comprasDeLider= new ComprasDeLiderDao();
    }

    public List<ProyectoBancoVo> listadoProyectosBancos(String banco) throws SQLException
    {
        return proyectosbanco.listarProyectosporBanco(banco);
    }

    public List<PagadoPorProyectoVo> listarTotalPagado(Double limite_inferior) throws SQLException
    {
        return pagadoPorProyecto.listarTotalPagado(limite_inferior);
    }
    public List<ComprasDeLiderVo> listarComprasDeLider() throws SQLException
    {
        return comprasDeLider.listarLideresCompras();
    }
}