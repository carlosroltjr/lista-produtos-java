package com.example.lista_produtos.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Produto implements Serializable {

    private int id;
    private String nome;
    private Float valor;
    private Categoria categoria;

    public Produto(int id, String nome, Float valor, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " - " + nome + " - " + valor + " - " + categoria;
    }
}
