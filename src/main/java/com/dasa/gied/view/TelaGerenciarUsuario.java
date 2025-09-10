package com.dasa.gied.view;

import com.dasa.gied.dao.UsuarioDao;
import com.dasa.gied.dao.jdbc.JdbcUsuarioDao;
import com.dasa.gied.domain.enums.TipoUsuario;
import com.dasa.gied.domain.model.Usuario;
import com.dasa.gied.service.UsuarioService;

import javax.swing.*;

public class TelaGerenciarUsuario extends JFrame {
    private JPanel panel1;
    private JButton registrarUsuarioButton;
    private JButton deletarUsuarioButton;
    private JButton buscarPorIDButton;
    private JButton voltarButton;

    private final UsuarioService usuarioService;

    public TelaGerenciarUsuario(Usuario usuarioLogado) {
        UsuarioDao usuarioDao = new JdbcUsuarioDao();
        this.usuarioService = new UsuarioService(usuarioDao);

        voltarButton.addActionListener(e -> {
            new TelaMenu(usuarioLogado);
            SwingUtilities.getWindowAncestor(panel1).dispose();
        });
        // Ação do Botão "Registrar Usuário"
        registrarUsuarioButton.addActionListener(e -> {
            JTextField nomeField = new JTextField(20);
            JTextField loginField = new JTextField(20);
            JPasswordField senhaField = new JPasswordField(20);
            JComboBox<TipoUsuario> tipoComboBox = new JComboBox<>(TipoUsuario.values());

            JPanel registroPanel = new JPanel();
            registroPanel.setLayout(new BoxLayout(registroPanel, BoxLayout.Y_AXIS));
            registroPanel.add(new JLabel("Nome:"));
            registroPanel.add(nomeField);
            registroPanel.add(new JLabel("Login:"));
            registroPanel.add(loginField);
            registroPanel.add(new JLabel("Senha:"));
            registroPanel.add(senhaField);
            registroPanel.add(new JLabel("Tipo de Usuário:"));
            registroPanel.add(tipoComboBox);

            int result = JOptionPane.showConfirmDialog(panel1, registroPanel, "Registrar Novo Usuário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    Usuario novoUsuario = new Usuario();
                    novoUsuario.setNome(nomeField.getText());
                    novoUsuario.setLogin(loginField.getText());
                    novoUsuario.setTipo((TipoUsuario) tipoComboBox.getSelectedItem());
                    String senha = new String(senhaField.getPassword());

                    if (senha.isEmpty() || novoUsuario.getNome().isEmpty() || novoUsuario.getLogin().isEmpty()) {
                        throw new IllegalArgumentException("Todos os campos são obrigatórios.");
                    }

                    usuarioService.criarUsuario(novoUsuario, senha);
                    JOptionPane.showMessageDialog(panel1, "Usuário registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (IllegalArgumentException | IllegalStateException ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage(), "Erro ao Registrar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do Botão "Deletar Usuário"
        deletarUsuarioButton.addActionListener(e -> {
            String idInput = JOptionPane.showInputDialog(panel1, "Digite o ID do usuário a ser deletado:", "Deletar Usuário", JOptionPane.WARNING_MESSAGE);
            if (idInput != null && !idInput.trim().isEmpty()) {
                try {
                    Long id = Long.parseLong(idInput);
                    int confirm = JOptionPane.showConfirmDialog(panel1, "Tem certeza que deseja deletar o usuário com ID " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        usuarioService.deletarUsuario(id);
                        JOptionPane.showMessageDialog(panel1, "Usuário deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel1, "ID inválido. Digite apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException  ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage(), "Erro ao Deletar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do Botão "Buscar por ID"
        buscarPorIDButton.addActionListener(e -> {
            String idInput = JOptionPane.showInputDialog(panel1, "Digite o ID do usuário:", "Buscar Usuário", JOptionPane.QUESTION_MESSAGE);
            if (idInput != null && !idInput.trim().isEmpty()) {
                try {
                    Long id = Long.parseLong(idInput);
                    Usuario usuario = usuarioService.buscarPorId(id);
                    String info = String.format("ID: %d\nNome: %s\nLogin: %s\nTipo: %s",
                            usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getTipo());
                    JOptionPane.showMessageDialog(panel1, info, "Dados do Usuário", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel1, "ID inválido. Digite apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException  ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage(), "Erro na Busca", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }

}
