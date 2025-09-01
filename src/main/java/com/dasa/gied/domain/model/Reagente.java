package com.dasa.gied.domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representa um Reagente, que é um tipo específico de Material.
 * Classe filha que demonstra Herança e Sobrescrita.
 */

public class Reagente extends Material {

    private LocalDate dataValidade;

    // Construtor
    public Reagente(String id, String nome, int quantidadeEmEstoque, int pontoDeReposicao, LocalDate dataValidade) {
        super(id, nome, quantidadeEmEstoque, pontoDeReposicao); // Chama o construtor da classe pai (Material)
        this.dataValidade = dataValidade;
    }

    /**
     * Verifica se o reagente está vencido.
     * @return true se a data de validade já passou, false caso contrário.
     */
    public boolean verificarValidade() {
        return LocalDate.now().isAfter(dataValidade);
    }



    /**
     * Polimorfismo (Sobrescrita de método).
     * Retorna a descrição completa, incluindo a data de validade.
     * @return Uma string com os detalhes completos do reagente.
     */
    @Override
    public String getDescricaoCompleta() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String descricaoPai = super.getDescricaoCompleta(); // Reaproveita o método da classe pai
        String statusValidade = verificarValidade() ? " (VENCIDO!)" : "";

        return String.format(
                "%s\nData de Validade: %s%s",
                descricaoPai, dataValidade.format(formatter), statusValidade
        );
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
