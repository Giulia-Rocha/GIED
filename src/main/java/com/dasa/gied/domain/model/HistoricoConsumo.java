package com.dasa.gied.domain.model;

import java.time.LocalDate;

/**
 * Classe para armazenar um registro de consumo de um material em uma data.
 */
public class HistoricoConsumo {

    private Material material;
    private int quantidadeConsumida;
    private LocalDate dataConsumo;

    public HistoricoConsumo(Material material, int quantidadeConsumida, LocalDate dataConsumo) {
        this.material = material;
        this.quantidadeConsumida = quantidadeConsumida;
        this.dataConsumo = dataConsumo;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getQuantidadeConsumida() {
        return quantidadeConsumida;
    }

    public void setQuantidadeConsumida(int quantidadeConsumida) {
        this.quantidadeConsumida = quantidadeConsumida;
    }

    public LocalDate getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(LocalDate dataConsumo) {
        this.dataConsumo = dataConsumo;
    }
}
