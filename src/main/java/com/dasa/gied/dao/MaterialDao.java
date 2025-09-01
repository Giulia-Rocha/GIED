package com.dasa.gied.dao;

import com.dasa.gied.domain.model.Material;

import java.util.List;

public interface MaterialDao {
    Long salvar(Material material);
    void atualizar(Material material);
    void delete(Long id);
    Material getById(Long id);
    List<Material> lisarTodos();
}
