package com.dasa.gied.dao.jdbc;

import com.dasa.gied.config.OracleConnectionFactory;
import com.dasa.gied.dao.LoteEstoqueDao;
import com.dasa.gied.domain.model.LoteEstoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class JdbcLoteEstoqueDao implements LoteEstoqueDao {

    @Override
    public Optional<LoteEstoque> findByItemAndId(Long idItem, String numeroLote) {
        return Optional.empty();
    }

    @Override
    public void salvar(LoteEstoque loteEstoque) {
        String sql = "INSERT INTO lote (id_item, numero_lote, data_validade, quantidade) VALUES ( ?, ?, ? , ?)";                                  )"
        try(Connection con = OracleConnectionFactory.getConnection();
            PreparedStatement st = con.prepareStatement(sql)){
            st.setLong(1, loteEstoque.getItem().getId());
            st.setString(2,loteEstoque.getLote());
            st.setDate(3, java.sql.Date.valueOf(loteEstoque.getDataValidade().toLocalDate()));
            st.setInt(4,loteEstoque.getQuantidade());
            st.execute();


        }catch(SQLException e){
            throw new RuntimeException("Erro ao salvar no BD "+e.getMessage());
        }
    }

    @Override
    public void atualizar(LoteEstoque loteEstoque) {

    }

    @Override
    public List<LoteEstoque> findByItemOrderByValidadeAsc(Long idItem) {
        return List.of();
    }
}
