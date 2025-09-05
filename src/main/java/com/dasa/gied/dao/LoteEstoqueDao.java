package com.dasa.gied.dao;

import com.dasa.gied.domain.model.LoteEstoque;

import java.util.List;
import java.util.Optional;

public interface LoteEstoqueDao {
    Optional<LoteEstoque> findByItemAndId(Long idItem, String numeroLote);
    void salvar(LoteEstoque loteEstoque);
    void atualizar(LoteEstoque loteEstoque);
    List<LoteEstoque> findByItemOrderByValidadeAsc(Long idItem);

}
