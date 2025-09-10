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
}

