package com.dasa.gied.service.dto;

import com.dasa.gied.domain.model.Item;
import com.dasa.gied.domain.model.LoteEstoque;

import java.util.List;

public record ConsultaEstoqueDTO(Item item, List<LoteEstoque> lotes) {
}
