package br.com.trabalhofinal.trabalhofinal.entity;

public enum StatusEntrega {

    C("Coletado"), E("Entrega");

    private String status;

    StatusEntrega(String status) {
        this.status = status;

    }

    public String getStatus() {
        return this.status;
    }


}
