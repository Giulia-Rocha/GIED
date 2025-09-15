package com.dasa.gied.view;

import com.dasa.gied.dao.UsuarioDao;
import com.dasa.gied.dao.jdbc.JdbcUsuarioDao;
import com.dasa.gied.domain.enums.TipoUsuario;
import com.dasa.gied.domain.model.Usuario;
import com.dasa.gied.service.UsuarioService;

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

    private final Usuario usuario;

    public TelaMenu(Usuario usuarioAutenticado) {
        this.usuario = usuarioAutenticado;

        setTitle("Menu Principal - GIED");
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        if(usuario.getTipo() != TipoUsuario.ADMIN){
            gerenciarUsuariosButton.setVisible(false);
        }

        //Ação de sair
        sairButton.addActionListener(e -> {
            System.exit(0);
        });

        gerenciarUsuariosButton.addActionListener(e -> {
            // Cria a nova tela
            JFrame frameGerenciarUsuario = new JFrame("Gerenciar Usuários");
            frameGerenciarUsuario.setContentPane(new TelaGerenciarUsuario(this.usuario).getPanel()); // Passa o tipo de usuário
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
            frameMovimentacoes.setContentPane(new TelaMovimentacoes(this.usuario).getPanel());
            frameMovimentacoes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameMovimentacoes.pack();
            frameMovimentacoes.setLocationRelativeTo(null);
            frameMovimentacoes.setVisible(true);

            this.dispose();
        });
        // Ação do botão Consultar Estoque
        consultarEstoqueButton.addActionListener(e -> {
            JFrame frameEstoque = new JFrame("Consultar Estoque");
            frameEstoque.setContentPane(new TelaEstoque(this.usuario).getPanel());
            frameEstoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameEstoque.pack();
            frameEstoque.setLocationRelativeTo(null);
            frameEstoque.setVisible(true);

            this.dispose();
        });
        relatoriosButton.addActionListener(e -> {
            JFrame frameRelatorio = new JFrame("Menu de Relatórios");
            // Chama a nova TelaRelatorio que funciona como um menu
            frameRelatorio.setContentPane(new TelaRelatorio(this.usuario).getPanel());
            frameRelatorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameRelatorio.pack();
            frameRelatorio.setLocationRelativeTo(null);
            frameRelatorio.setVisible(true);
            this.dispose();
        });

        alterarMinhaSenhaButton.addActionListener(e -> {
            // Cria um painel com os campos de senha
            JPanel alterarSenhaPanel = new JPanel();
            alterarSenhaPanel.setLayout(new BoxLayout(alterarSenhaPanel, BoxLayout.Y_AXIS));

            JPasswordField senhaAtualField = new JPasswordField(20);
            JPasswordField novaSenhaField = new JPasswordField(20);
            JPasswordField confirmarNovaSenhaField = new JPasswordField(20);

            alterarSenhaPanel.add(new JLabel("Senha Atual:"));
            alterarSenhaPanel.add(senhaAtualField);
            alterarSenhaPanel.add(Box.createVerticalStrut(10));
            alterarSenhaPanel.add(new JLabel("Nova Senha:"));
            alterarSenhaPanel.add(novaSenhaField);
            alterarSenhaPanel.add(Box.createVerticalStrut(10));
            alterarSenhaPanel.add(new JLabel("Confirme a Nova Senha:"));
            alterarSenhaPanel.add(confirmarNovaSenhaField);

            int result = JOptionPane.showConfirmDialog(this, alterarSenhaPanel, "Alterar Senha", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String senhaAtual = new String(senhaAtualField.getPassword());
                    String novaSenha = new String(novaSenhaField.getPassword());
                    String confirmarNovaSenha = new String(confirmarNovaSenhaField.getPassword());

                    // Validação dos campos
                    if (!novaSenha.equals(confirmarNovaSenha)) {
                        throw new IllegalArgumentException("A nova senha e a confirmação não correspondem.");
                    }

                    // Chama o serviço para alterar a senha
                    // Usa o ID do usuário que está logado
                    UsuarioDao dao = new JdbcUsuarioDao(); // Instancia o DAO e o Service aqui
                    UsuarioService service = new UsuarioService(dao);
                    service.alterarSenha(this.usuario.getId(), senhaAtual, novaSenha);

                    JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (IllegalArgumentException | SecurityException | IllegalStateException  ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao Alterar Senha", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }




    }






