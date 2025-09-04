package com.dasa.gied.domain.model;

import java.util.Date;

/**
 * Representa um item genérico no estoque.
 * Classe pai no modelo de herança.
 */
public class Item {

    private Long id;
    private String nome;
    private String descricao;
    private Integer nivelMinEstoque;

    public Item(Long id, String nome, String descricao, Integer nivelMinEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.nivelMinEstoque = nivelMinEstoque;
    }

    public void verificarEstoque(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNivelMinEstoque() {
        return nivelMinEstoque;
    }

    public void setNivelMinEstoque(Integer nivelMinEstoque) {
        this.nivelMinEstoque = nivelMinEstoque;}
    }

