package com.dasa.gied.service;

import com.dasa.gied.dao.ItemDao;
import com.dasa.gied.dao.LoteEstoqueDao;
import com.dasa.gied.domain.model.Item;
import com.dasa.gied.domain.model.LoteEstoque;

import java.time.LocalDate;
import java.util.List;

public class ItemService {
    private final ItemDao itemDao ;
    private final LoteEstoqueDao loteEstoqueDao ;

    public ItemService(ItemDao itemDao, LoteEstoqueDao loteEstoqueDao) {
        this.itemDao = itemDao;
        this.loteEstoqueDao = loteEstoqueDao;
    }

    public void registrarEntrada(Long idItem, int quantidade, String numeroLote, LocalDate dataValidade){
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

        loteEstoqueDao.findByItemAndId(idItem,numeroLote)
                .ifPresentOrElse(
                        loteExistente ->{
                            if(!loteExistente.getDataValidade().equals(dataValidade)){
                                throw new IllegalStateException("Inconsistência de dados: a data de validade do lote não corresponde à já cadastrada.")
                            }
                            loteExistente.setQuantidade(loteExistente.getQuantidade()+quantidade);
                            loteEstoqueDao.atualizar(loteExistente);
                        },
                        ()->{
                            LoteEstoque novoLote = new LoteEstoque(null,item, numeroLote, dataValidade, quantidade);
                            loteEstoqueDao.salvar(novoLote);
                        }
                );

    }
    public void RegistrarSaida(Long idItem, int quantidade){


    }
    public Item ConsultarEstoque(Long idItem){}
    public List<Item> listarEstoque(){}
    public List<Item> listarEstoqueBaixo(){}
}
