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

    private final TipoUsuario tipoUsuario;

    public TelaMenu(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        setTitle("Menu Principal - GIED");
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        if(tipoUsuario != TipoUsuario.ADMIN){
            gerenciarUsuariosButton.setVisible(false);
        }

        //Ação de sair
        sairButton.addActionListener(e -> {
            System.exit(0);
        });

        gerenciarUsuariosButton.addActionListener(e -> {
            // Cria a nova tela
            JFrame frameGerenciarUsuario = new JFrame("Gerenciar Usuários");
            frameGerenciarUsuario.setContentPane(new TelaGerenciarUsuario(this.tipoUsuario).getPanel()); // Passa o tipo de usuário
            frameGerenciarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameGerenciarUsuario.pack();
            frameGerenciarUsuario.setLocationRelativeTo(null);
            frameGerenciarUsuario.setVisible(true);

            // Fecha a tela de menu atual
            this.dispose();
        });
        // Ação do botão Movimentações
        movimentacoesButton.addActionListener(e -> {
            JFrame frameMovimentacoes = new JFrame("Movimentações de Estoque");
            frameMovimentacoes.setContentPane(new TelaMovimentacoes(this.tipoUsuario).getPanel());
            frameMovimentacoes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameMovimentacoes.pack();
            frameMovimentacoes.setLocationRelativeTo(null);
            frameMovimentacoes.setVisible(true);

            this.dispose();
        });
        // Ação do botão Consultar Estoque
        consultarEstoqueButton.addActionListener(e -> {
            JFrame frameEstoque = new JFrame("Consultar Estoque");
            frameEstoque.setContentPane(new TelaEstoque(this.tipoUsuario).getPanel());
            frameEstoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameEstoque.pack();
            frameEstoque.setLocationRelativeTo(null);
            frameEstoque.setVisible(true);

            this.dispose();
        });

    }




    }






