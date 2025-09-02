package com.dasa.gied.domain.model;
/**
 * Representa um item genérico no estoque.
 * Classe pai no modelo de herança.
 */
public class Item {

    private Long id;
    private String name;
    private String description;
    private Integer nivelMinEstoque;

    public Item(Long id, String name, String description, Integer nivelMinEstoque) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNivelMinEstoque() {
        return nivelMinEstoque;
    }

    public void setNivelMinEstoque(Integer nivelMinEstoque) {
        this.nivelMinEstoque = nivelMinEstoque;
    }
}
