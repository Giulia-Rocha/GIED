package com.dasa.gied.dao;

import com.dasa.gied.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDao {
     Optional<Usuario> findByLogin(String login);
     void criar(Usuario usuario);
     void atualizar(Usuario usuario);
     List<Usuario> findAll();
     Usuario findById(Long id);
     void delete(Long id);

}
