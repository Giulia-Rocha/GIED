package com.dasa.gied.domain.model;
/**
 * Representa um item genérico no estoque.
 * Classe pai no modelo de herança.
 */
public class Material {

    private String id; // Simula o código lido pelo QR Code
    private String nome;
    private int quantidadeEmEstoque;
    private int pontoDeReposicao; // Quantidade mínima para notificar

    // Construtor principal
    public Material(String id, String nome, int quantidadeEmEstoque, int pontoDeReposicao) {
        this.id = id;
        this.nome = nome;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.pontoDeReposicao = pontoDeReposicao;
    }

    // Polimorfismo (Sobrecarga de Construtor)
    public Material(String id, String nome) {
        this(id, nome, 0, 10); // Assume estoque inicial 0 e reposição 10
    }

    /**
     * Registra a saída de uma quantidade de material do estoque.
     * @param quantidade A quantidade a ser removida.
     */
    public void registrarSaida(int quantidade) {
        if (quantidade > 0 && this.quantidadeEmEstoque >= quantidade) {
            this.quantidadeEmEstoque -= quantidade;
        }
    }

    /**
     * Adiciona uma quantidade de material ao estoque (entrada/reposição).
     * @param quantidade A quantidade a ser adicionada.
     */
    public void adicionarEntrada(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEmEstoque += quantidade;
        }
    }

    /**
     * Verifica se o estoque está abaixo do ponto de reposição.
     * @return true se o estoque estiver baixo, false caso contrário.
     */
    public boolean verificarEstoqueBaixo() {
        return this.quantidadeEmEstoque <= this.pontoDeReposicao;
    }

    /**
     * Retorna uma descrição formatada do material.
     * Este método será sobrescrito pela classe filha.
     * @return Uma string com os detalhes do material.
     */
    public String getDescricaoCompleta() {
        return String.format(
                "ID: %s\nNome: %s\nQtd. em Estoque: %d\nPonto de Reposição: %d",
                id, nome, quantidadeEmEstoque, pontoDeReposicao
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public int getPontoDeReposicao() {
        return pontoDeReposicao;
    }

    public void setPontoDeReposicao(int pontoDeReposicao) {
        this.pontoDeReposicao = pontoDeReposicao;
    }
}
