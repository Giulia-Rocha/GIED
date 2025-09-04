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

    public void registrarEntrada(Long idItem, int quantidade, String numeroLote,Date dataValidade){
        //validações de entrada
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (numeroLote == null || numeroLote.trim().isEmpty()) {
            throw new IllegalArgumentException("O número do lote é obrigatório.");
        }

        // Regra de Negócio
        Item item = itemDao.getById(idItem);
        if(item==null){
            throw new IllegalStateException("Produto com ID " + idItem + " não encontrado no catálogo. Cadastre o produto primeiro.");
        }

        loteEstoqueDao.findByItemAndId(idItem,)

    }
    public void RegistrarSaida(Long idItem, int quantidade){}
    public Item ConsultarEstoque(Long idItem){}
    public List<Item> listarEstoque(){}
    public List<Item> listarEstoqueBaixo(){}
}
