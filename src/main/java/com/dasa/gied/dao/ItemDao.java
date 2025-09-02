package com.dasa.gied.dao;

import com.dasa.gied.domain.model.Item;

import java.util.List;

public interface ItemDao {
    Long salvar(Item item);
    void atualizar(Item item);
    void delete(Long id);
    Item getById(Long id);
    List<Item> lisarTodos();
}
