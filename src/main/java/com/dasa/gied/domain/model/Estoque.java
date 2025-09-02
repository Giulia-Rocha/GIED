package com.dasa.gied.domain.model;

public class Estoque {

    private Long idItem;
    private Integer quantidadeAtual;

    public Estoque(Long idItem, Integer quantidadeAtual) {
        this.idItem = idItem;
        this.quantidadeAtual = quantidadeAtual;
    }
    public Estoque() {}

    public void adicionarQuantidade(Integer quantidade){
        this.quantidadeAtual += quantidade;
    }
    public void removerQuantidade(Integer quantidade){
        if(this.quantidadeAtual - quantidade >= quantidade){
            this.quantidadeAtual -= quantidade;
        }else{
            throw new IllegalStateException("Estoque insuficiente para o item" + this.idItem);
        }
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(Integer quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }
}
