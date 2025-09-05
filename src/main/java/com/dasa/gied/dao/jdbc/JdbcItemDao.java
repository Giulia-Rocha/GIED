package com.dasa.gied.dao.jdbc;

import com.dasa.gied.config.OracleConnectionFactory;
import com.dasa.gied.dao.ItemDao;

import com.dasa.gied.domain.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcItemDao implements ItemDao {
    @Override
    public Long salvar(Item item) {
        String sql = "INSERT INTO item (nome, descricao, nivel_min_estoque) VALUES (?, ?, ?)";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql, new String[]{"id"})) {

            st.setString(1, item.getNome());
            st.setString(2, item.getDescricao());
            st.setInt(3, item.getNivelMinEstoque());

            st.executeUpdate();

            // Recupera o ID gerado pelo banco de dados.
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
            return 0L; // Retorna 0 se não conseguiu recuperar o ID.

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o item no banco de dados.", e);
        }
    }

    @Override
    public void atualizar(Item item) {
        String sql = "UPDATE item SET nome = ?, descricao = ?, nivel_min_estoque = ? WHERE id = ?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, item.getNome());
            st.setString(2, item.getDescricao());
            st.setInt(3, item.getNivelMinEstoque());
            st.setLong(4, item.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o item no banco de dados.", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM item WHERE id = ?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar o item no banco de dados.", e);
        }
    }

    @Override
    public Item getById(Long id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getLong("id"));
                    item.setNome(rs.getString("nome"));
                    item.setDescricao(rs.getString("descricao"));
                    item.setNivelMinEstoque(rs.getInt("nivel_min_estoque"));
                    return item;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o item por ID.", e);
        }
        return null; // Retorna null se nenhum item for encontrado.
    }

    @Override
    public List<Item> lisarTodos() {
        String sql = "SELECT * FROM T_GIED_ITEM";
        List<Item> todosOsItens = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                todosOsItens.add(mapRowToItem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os itens.", e);
        }
        return todosOsItens;
    }

    @Override
    public List<Item> findByEstoqueBaixo() {
        String sql = """
            SELECT i.id, i.nome, i.descricao, i.nivel_min_estoque
            FROM item i
            JOIN lote l ON i.id = l.id_item
            GROUP BY i.id, i.nome, i.descricao, i.nivel_min_estoque
            HAVING SUM(l.quantidade) <= i.nivel_min_estoque
        """;
        List<Item> itensComEstoqueBaixo = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                itensComEstoqueBaixo.add(mapRowToItem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar itens com estoque baixo.", e);
        }
        return itensComEstoqueBaixo;
    }

    private Item mapRowToItem(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setId(rs.getLong("id"));
        item.setNome(rs.getString("nome"));
        item.setDescricao(rs.getString("descricao"));
        item.setNivelMinEstoque(rs.getInt("nivel_min_estoque"));
        return item;
    }
}
