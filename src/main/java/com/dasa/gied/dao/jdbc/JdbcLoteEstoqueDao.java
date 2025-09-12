package com.dasa.gied.dao.jdbc;

import com.dasa.gied.config.OracleConnectionFactory;
import com.dasa.gied.dao.LoteEstoqueDao;
import com.dasa.gied.domain.enums.TipoUsuario;
import com.dasa.gied.domain.model.Item;
import com.dasa.gied.domain.model.LoteEstoque;
import com.dasa.gied.domain.model.Usuario;

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
        String sql = "SELECT * FROM LOTE WHERE ID_ITEM = ? AND NR_LOTE = ?";

        try(Connection con = OracleConnectionFactory.getConnection();
            PreparedStatement st = con.prepareStatement(sql)){

            st.setLong(1,idItem);
            st.setString(2,numeroLote);

            try(ResultSet rs = st.executeQuery()){
                if(rs.next()){
                    return Optional.of(mapRowToLoteEstoque(rs));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Erro ao buscar lote por item e número");
        }
        return Optional.empty();
    }

    @Override
    public void salvar(LoteEstoque loteEstoque) {
        String sql = "INSERT INTO LOTE (ID_ITEM, NR_LOTE, DT_VALIDADE, NR_QUANTIDADE) VALUES ( ?, ?, ? , ?)";
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
        String sql = "UPDATE LOTE SET NR_QUANTIDADE = ? WHERE ID_LOTE= ?";
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
        String sql = "SELECT * FROM LOTE WHERE ID_ITEM = ? ORDER BY DT_VALIDADE ASC";
        List<LoteEstoque> lotesOrdenados = new ArrayList<>();
        try(Connection con = OracleConnectionFactory.getConnection();
            PreparedStatement st = con.prepareStatement(sql)){
            st.setLong(1, idItem);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                // Adiciona o lote totalmente montado à lista de retorno.
                lotesOrdenados.add(mapRowToLoteEstoque(rs));
            }


        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar lotes "+e.getMessage());
        }
        return lotesOrdenados;
    }

    private LoteEstoque mapRowToLoteEstoque(ResultSet rs) throws SQLException {
        LoteEstoque loteEstoque = new LoteEstoque();
        loteEstoque.setId(rs.getLong("ID_LOTE"));
        loteEstoque.setQuantidade(rs.getInt("NR_QUANTIDADE"));
        loteEstoque.setLote(rs.getString("NR_LOTE"));
        loteEstoque.setDataValidade(rs.getDate("DT_VALIDADE").toLocalDate());
        return loteEstoque;
    }
}
