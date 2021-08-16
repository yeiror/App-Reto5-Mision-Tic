package com.example.model.vo;

public class ComprasDeLiderVo {
        private String lider;
        private float valor;
        public String getLider() {
                return lider;
        }
        public void setLider(String lider) {
                this.lider = lider;
        }
        public float getValor() {
                return valor;
        }
        public void setValor(float valor) {
                this.valor = valor;
        }
        @Override
        public String toString() {
                return "ComprasDeLiderVo [lider=" + lider + ", valor=" + valor + "]";
        }

   }
   