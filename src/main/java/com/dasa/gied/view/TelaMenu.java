package com.dasa.gied.view;

import com.dasa.gied.domain.enums.TipoUsuario;

import javax.swing.*;


public class TelaMenu extends JFrame {
    private JButton consultarEstoqueButton;
    private JButton gerenciarUsuariosButton;
    private JButton movimentacoesButton;
    private JButton relatoriosButton;
    private JButton sairButton;
    private JButton alterarMinhaSenhaButton;
    private JPanel PainelOp;
    private JPanel PainelSair;
    private JLabel tituloMenu;
    private JPanel painelPrincipal;


    public TelaMenu(TipoUsuario tipo) {

        getComponents();

        if (tipo != TipoUsuario.ADMIN) {
            gerenciarUsuariosButton.setVisible(false);
        }

        sairButton.addActionListener(e -> {
            System.exit(0);
        });
    }




}

