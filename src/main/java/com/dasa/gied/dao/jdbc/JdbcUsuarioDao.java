package com.dasa.gied.dao.jdbc;

import com.dasa.gied.config.OracleConnectionFactory;
import com.dasa.gied.dao.UsuarioDao;
import com.dasa.gied.domain.enums.TipoUsuario;
import com.dasa.gied.domain.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUsuarioDao implements UsuarioDao {
    @Override
    public Optional<Usuario> findByLogin(String login) {
        String sql = "SELECT * FROM T_GIED_USUARIO WHERE login = ?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, login);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    // Se encontrou, mapeia a linha para um objeto e retorna dentro de um Optional
                    return Optional.of(mapRowToUsuario(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por login.", e);
        }
        // Se não encontrou nada, retorna um Optional vazio
        return Optional.empty();
    }

    @Override
    public void criar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, login, senha_hash, tipo) VALUES (?, ?, ?, ?)";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getLogin());
            st.setString(3, usuario.getSenhaHash());
            st.setString(4, usuario.getTipo().name()); // Salva o nome do Enum (ex: "ADMIN")

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar novo usuário.", e);
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, login = ?, senha_hash = ?, tipo = ? WHERE id = ?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getLogin());
            st.setString(3, usuario.getSenhaHash());
            st.setString(4, usuario.getTipo().name());
            st.setLong(5, usuario.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário.", e);
        }

    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> todosOsUsuarios = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                todosOsUsuarios.add(mapRowToUsuario(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os usuários.", e);
        }
        return todosOsUsuarios;
    }

    @Override
    public Usuario findById(Long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID.", e);
        }
        return null; // Retorna null se não encontrar
    }

    private Usuario mapRowToUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setLogin(rs.getString("login"));
        usuario.setSenhaHash(rs.getString("senha_hash"));
        // Converte a String do banco de volta para o Enum
        usuario.setTipo(TipoUsuario.valueOf(rs.getString("tipo")));
        return usuario;
    }
}
