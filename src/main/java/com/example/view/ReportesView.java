package com.example.view;

import java.sql.SQLException;

import com.example.controller.ReportesController;
import com.example.model.dao.PagadoPorProyectoDao;
import com.example.model.vo.ComprasDeLiderVo;
import com.example.model.vo.PagadoPorProyectoVo;
import com.example.model.vo.ProyectoBancoVo;

public class ReportesView {
    private ReportesController reportesController;

    public ReportesView()
    {
        reportesController= new ReportesController();
    }

    private String repitaCaracter(Character caracter, Integer veces) {
        var respuesta = "";
        for (int i = 0; i < veces; i++) {
        respuesta += caracter;
    }
    return respuesta;
}

//-----------------------SEGUNDA CONSULTA--------------------
    public void proyectosFinanciadosPorBanco(String banco) {

        try{
        System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO "
        + repitaCaracter('=', 37));

        if (banco != null && !banco.isBlank()) {
            
            System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s",
            "ID", "CONSTRUCTORA", "CIUDAD", "CLASIFICACION", "ESTRATO", "LIDER"));
            System.out.println(repitaCaracter('-', 105));
    // Imprimir en pantalla la información del proyecto
            var lista = reportesController.listadoProyectosBancos(banco);
            for (ProyectoBancoVo proyectoBancoVo : lista) {
                System.out.println(String.format("%3d %-25s %-20s %-15s %7d %-30s",
                proyectoBancoVo.getId(), proyectoBancoVo.getConstructora(), proyectoBancoVo.getCiudad(), 
                proyectoBancoVo.getClasificacion(), proyectoBancoVo.getEstrato(), proyectoBancoVo.getLider()));
            }
    }
    }catch(SQLException e){
        e.printStackTrace();
    }
}

//-----------------CUARTA CONSULTA --------------------
    public void totalPagadoPorProyectosSuperioresALimite(Double limiteInferior) {
        System.out.println(repitaCaracter('=', 1) + " TOTAL PAGADO POR PROYECTO "
        + repitaCaracter('=', 1));
        try{
        if (limiteInferior != null) {
            System.out.println(String.format("%3s %13s", "ID", "VALOR "));
            System.out.println(repitaCaracter('-', 29));

            // TODO Imprimir en pantalla la información del total pagado
            var lista = reportesController.listarTotalPagado(limiteInferior);
            for (PagadoPorProyectoVo pagadoPorProyectoVo : lista) {
                System.out.println(String.format("%3d %,15.1f", pagadoPorProyectoVo.getId(), pagadoPorProyectoVo.getValor()));
            }
    }
    }catch(SQLException e){
        e.printStackTrace();
    }
        }

//-----------------QUINTA CONSULTA --------------------
    public void lideresQueMenosGastan() {
        System.out.println(repitaCaracter('=', 5) + " 10 LIDERES MENOS COMPRADORES "
        + repitaCaracter('=', 6));
        try{
        System.out.println(String.format("%-25s %13s", "LIDER", "VALOR "));
        System.out.println(repitaCaracter('-', 41));
        // TODO Imprimir en pantalla la información de los líderes
            var lista = reportesController.listarComprasDeLider();
            for (ComprasDeLiderVo comprasDeLider : lista) {
                System.out.println(String.format("%-25s %,15.1f", 
                comprasDeLider.getLider(), comprasDeLider.getValor()));
            }

        }catch(SQLException e)
        {
           e.printStackTrace(); 
        } 
}
}