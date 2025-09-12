package com.dasa.gied.view;

import com.dasa.gied.Application;
import com.dasa.gied.dao.UsuarioDao;
import com.dasa.gied.dao.jdbc.JdbcUsuarioDao;
import com.dasa.gied.domain.model.Usuario;
import com.dasa.gied.service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TelaLogin {
    public JPanel GIED;
    private JLabel titulo;
    private JButton entrarButton;
    private JTextField loginField;
    private JPasswordField passwordField;

    private final UsuarioService usuarioService;




    public TelaLogin(){
        UsuarioDao usuarioDao = new JdbcUsuarioDao();
        this.usuarioService = new UsuarioService(usuarioDao);

        entrarButton.addActionListener(e -> realizarLogin());
    }



    private void realizarLogin(){
        String login = loginField.getText();
        char[] pass = passwordField.getPassword();
        String senha = new String(pass);

        Arrays.fill(pass, ' ');

        if(login.isEmpty() || senha.isEmpty()){
            JOptionPane.showMessageDialog(GIED, "Preencha todos os campos!");
            return;

        }

        try{
            Usuario usuarioAutenticado = usuarioService.autenticar(login,senha);

            JOptionPane.showMessageDialog(GIED, "Usuario autenticado com sucesso!");
            SwingUtilities.getWindowAncestor(GIED).dispose();

            new TelaMenu(usuarioAutenticado);
        }catch(SecurityException e){
            JOptionPane.showMessageDialog(GIED, e.getMessage(), "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
        }finally {
            passwordField.setText("");
        }

    }

}
