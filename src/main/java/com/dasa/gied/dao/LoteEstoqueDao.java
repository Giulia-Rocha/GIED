package com.dasa.gied.dao;

import com.dasa.gied.domain.model.LoteEstoque;

import java.util.Optional;

public interface LoteEstoqueDao {
    Optional<LoteEstoque> findByItemAndId(Long idItem, Long idLote);
    void salvar(LoteEstoque loteEstoque);
    void atualizar(LoteEstoque loteEstoque);
}
