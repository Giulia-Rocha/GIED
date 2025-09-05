package com.dasa.gied.dao.jdbc;

import com.dasa.gied.config.OracleConnectionFactory;
import com.dasa.gied.dao.LoteEstoqueDao;
import com.dasa.gied.domain.model.Item;
import com.dasa.gied.domain.model.LoteEstoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcLoteEstoqueDao implements LoteEstoqueDao {

    @Override
    public Optional<LoteEstoque> findByItemAndId(Long idItem, String numeroLote) {
        String sql = "SELECT * FROM lote WHERE id_item = ? AND numero_lote = ?";

        try(Connection con = OracleConnectionFactory.getConnection();
            PreparedStatement st = con.prepareStatement(sql)){

            st.setLong(1,idItem);
            st.setString(2,numeroLote);

            try(ResultSet rs = st.executeQuery()){
                if(rs.next()){
                    Item itemProxy = new Item();
                    itemProxy.setId(idItem);

                    LoteEstoque loteEstoque = new LoteEstoque();
                    loteEstoque.setId(rs.getLong("id"));
                    loteEstoque.setQuantidade(rs.getInt("quantidade"));
                    loteEstoque.setLote(rs.getString("numero_lote"));
                    loteEstoque.setDataValidade(rs.getDate("data_validade").toLocalDate());
                    loteEstoque.setItem(itemProxy);

                    return Optional.of(loteEstoque);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Erro ao buscar lote por item e número");
        }
        return Optional.empty();
    }

    @Override
    public void salvar(LoteEstoque loteEstoque) {
        String sql = "INSERT INTO lote (id_item, numero_lote, data_validade, quantidade) VALUES ( ?, ?, ? , ?)";
        try(Connection con = OracleConnectionFactory.getConnection();
            PreparedStatement st = con.prepareStatement(sql)){
            st.setLong(1, loteEstoque.getItem().getId());
            st.setString(2,loteEstoque.getLote());
            st.setDate(3, java.sql.Date.valueOf(loteEstoque.getDataValidade()));
            st.setInt(4,loteEstoque.getQuantidade());
            st.execute();


        }catch(SQLException e){
            throw new RuntimeException("Erro ao salvar no BD "+e.getMessage());
        }
    }

    @Override
    public int atualizar(LoteEstoque loteEstoque) {
        String sql = "UPDATE lote SET quantidade = ? WHERE id= ?";
        try(Connection con = OracleConnectionFactory.getConnection();
            PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, loteEstoque.getQuantidade());
            st.setLong(2, loteEstoque.getId());

            return  st.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar no BD "+e.getMessage());
        }
    }

    @Override
    public List<LoteEstoque> findByItemOrderByValidadeAsc(Long idItem) {
        String sql = "SELECT * FROM lote WHERE id_item = ? ORDER BY data_validade ASC";
        List<LoteEstoque> lotesOrdenados = new ArrayList<>();
        try(Connection con = OracleConnectionFactory.getConnection();
            PreparedStatement st = con.prepareStatement(sql)){
            st.setLong(1, idItem);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Item itemProxy = new Item();
                itemProxy.setId(idItem);

                // Agora, cria o objeto LoteEstoque completo.
                LoteEstoque loteEstoque = new LoteEstoque();
                loteEstoque.setId(rs.getLong("id"));
                loteEstoque.setQuantidade(rs.getInt("quantidade"));
                loteEstoque.setLote(rs.getString("numero_lote")); // Ajuste o nome da coluna se necessário

                // Converte a data do banco (java.sql.Date) para LocalDate.
                loteEstoque.setDataValidade(rs.getDate("data_validade").toLocalDate());

                // Associa o Item proxy ao Lote. Agora o link está feito!
                loteEstoque.setItem(itemProxy);

                // Adiciona o lote totalmente montado à lista de retorno.
                lotesOrdenados.add(loteEstoque);

            }


        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar lotes "+e.getMessage());
        }
        return lotesOrdenados;
    }
}
