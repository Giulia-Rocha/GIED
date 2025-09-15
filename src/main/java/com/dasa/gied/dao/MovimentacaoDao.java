package com.dasa.gied.dao;

import com.dasa.gied.domain.model.Movimentacao;

import java.util.List;

public interface MovimentacaoDao {
    void salvar(Movimentacao movimentacao);
    List<Movimentacao> buscarTodas();
}
