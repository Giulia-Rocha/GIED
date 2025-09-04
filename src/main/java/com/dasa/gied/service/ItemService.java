package com.dasa.gied.service;

import com.dasa.gied.dao.ItemDao;
import com.dasa.gied.dao.LoteEstoqueDao;
import com.dasa.gied.domain.model.Item;

import java.util.Date;
import java.util.List;

public class ItemService {
    private final ItemDao itemDao ;
    private final LoteEstoqueDao loteEstoqueDao ;

    public ItemService(ItemDao itemDao, LoteEstoqueDao loteEstoqueDao) {
        this.itemDao = itemDao;
        this.loteEstoqueDao = loteEstoqueDao;
    }

    public void registrarEntrada(Long idItem, int quantidade, LoteEstoqueDao loteEstoqueDao, Date dataValidade){

    }
    public void RegistrarSaida(Long idItem, int quantidade){}
    public Item ConsultarEstoque(Long idItem){}
    public List<Item> listarEstoque(){}
    public List<Item> listarEstoqueBaixo(){}
}
