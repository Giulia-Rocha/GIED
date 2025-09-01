package com.dasa.gied.dao.jdbc;

import com.dasa.gied.dao.MaterialDao;
import com.dasa.gied.domain.model.Material;

import java.util.List;

public class JdbcMaterialDao implements MaterialDao {
    @Override
    public Long salvar(Material material) {
        return 0L;
    }

    @Override
    public void atualizar(Material material) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Material getById(Long id) {
        return null;
    }

    @Override
    public List<Material> lisarTodos() {
        return List.of();
    }
}
