package com.example.atividadecrud_09_19;

public class Produto {
    private int id;
    private String texto;
    private String cor;

    public Produto(String texto, String cor) {
        this.texto = texto;
        this.cor = cor;
    }

    public Produto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
