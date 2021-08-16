package com.example.model.vo;

public class PagadoPorProyectoVo {
    private Integer id;
    private Float valor;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Float getValor() {
        return valor;
    }
    public void setValor(Float valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return "PagadoPorProyectoVo [id=" + id + ", valor=" + valor + "]";
    }
    
   }
   