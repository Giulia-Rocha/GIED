package com.dasa.gied;

import com.dasa.gied.dao.UsuarioDao;
import com.dasa.gied.domain.model.Usuario;
import com.dasa.gied.view.TelaLogin;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tela de Login");
        frame.setContentPane(new TelaLogin().GIED);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



public static class GeradorDeHash {
    public static void main(String[] args) {
        // A senha que vamos usar para o nosso usuário de teste
        String senha = "123";

        // Gera o hash
        String senhaHasheada = BCrypt.hashpw(senha, BCrypt.gensalt());

        // Imprime o hash no console. Copie este valor!
        System.out.println(senhaHasheada);
        // Exemplo de saída: $2a$10$7b.bW0N.xL4u.2PzJ1E7oO23nSOgWjgaJ86K8N7/8.aR1jJ2.5zS
    }
}



    // Esta classe simula o acesso ao banco de dados.
public static class UsuarioDaoFake implements UsuarioDao { // Se UsuarioDao for uma classe, podemos estendê-la.
    // Se for interface, seria 'implements UsuarioDao'

    @Override
    public Optional<Usuario> findByLogin(String login) {
        // Vamos fingir que temos um único usuário no nosso "banco de dados falso".
        // O login dele é "admin".

        if ("admin".equalsIgnoreCase(login)) {
            // Se o login for "admin", criamos e retornamos o objeto Usuario.
            Usuario usuarioFalso = new Usuario();
            usuarioFalso.setId(1L); // ID Fixo
            usuarioFalso.setNome("Administrador do Sistema");
            usuarioFalso.setLogin("admin");

            // IMPORTANTE: Cole aqui o hash que você gerou no Passo 1!
            String hashDaSenha = "$2a$10$sY3x2DVEmHpazRAvUMjxDORz3GYSb4kHPH5ObFpmbkh.013alupda"; // Exemplo! Use o seu.
            usuarioFalso.setSenhaHash(hashDaSenha);

            // Retornamos o usuário dentro de um Optional, como o original faria.
            return Optional.of(usuarioFalso);
        }

        // Se o login não for "admin", retornamos um Optional vazio, simulando "usuário não encontrado".
        return Optional.empty();
    }

    @Override
    public void criar(Usuario usuario) {

    }

    @Override
    public void atualizar(Usuario usuario) {

    }

    @Override
    public List<Usuario> findAll() {
        return List.of();
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }
}

}
