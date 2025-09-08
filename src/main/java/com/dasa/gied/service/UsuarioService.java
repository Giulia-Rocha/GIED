package com.dasa.gied.service;

import com.dasa.gied.dao.UsuarioDao;
import com.dasa.gied.domain.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

public class UsuarioService {
    private  final UsuarioDao usuarioDao;

    public UsuarioService(UsuarioDao usuarioDao){
        this.usuarioDao = usuarioDao;
    }

    public Usuario autenticar(String login, String senha){
        Optional<Usuario> user = usuarioDao.findByLogin(login);
        if(user.isPresent() && BCrypt.checkpw(senha,user.get().getSenhaHash())){
            return user.get();
        }
        else{
            throw new SecurityException("Login ou Senha Inválidos");
        }
    }
}
