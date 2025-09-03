package com.dasa.gied.dao.jdbc;

import com.dasa.gied.dao.ItemDao;

import com.dasa.gied.domain.model.Item;

import java.util.List;

public class JdbcItemDao implements ItemDao {
    @Override
    public Long salvar(Item item) {
        return 0L;
    }

    @Override
    public void atualizar(Item item) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Item getById(Long id) {
        return null;
    }

    @Override
    public List<Item> lisarTodos() {
        return List.of();
    }

    @Override
    public List<Item> findByEstoqueBaixo() {
        return List.of();
    }
}
