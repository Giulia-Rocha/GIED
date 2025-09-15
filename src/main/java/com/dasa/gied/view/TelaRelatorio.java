package com.dasa.gied.view;

import com.dasa.gied.dao.MovimentacaoDao;
import com.dasa.gied.dao.jdbc.JdbcMovimentacaoDao;
import com.dasa.gied.domain.model.Movimentacao;
import com.dasa.gied.domain.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class TelaRelatorio extends JFrame {
    private JPanel panel1;
    private JButton voltarButton;
    private JButton historicoButton; // Renomeie o botão no .form para 'historicoButton'

    public TelaRelatorio(Usuario usuarioLogado) {
        setTitle("Menu de Relatórios - GIED");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Ação do botão Voltar
        voltarButton.addActionListener(e -> {
            new TelaMenu(usuarioLogado);
            this.dispose();
        });

        // Ação do botão Histórico de Movimentações
        historicoButton.addActionListener(e -> {
            exibirRelatorioHistorico();
        });
    }

    private void exibirRelatorioHistorico() {
        // Busca os dados do banco
        MovimentacaoDao dao = new JdbcMovimentacaoDao();
        List<Movimentacao> movimentacoes = dao.buscarTodas();

        // Verifica se há registros
        if (movimentacoes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma movimentação foi registrada ainda.", "Histórico Vazio", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Monta o texto do relatório
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-25s | %-15s | %s\n", "Data e Hora", "Tipo", "Quantidade"));
        sb.append("------------------------------------------------------------\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for (Movimentacao mov : movimentacoes) {
            sb.append(String.format("%-25s | %-15s | %d\n",
                    mov.getDataHora().format(formatter),
                    mov.getTipoMovimentacao(),
                    mov.getQuantidade()));
        }

        // Cria os componentes visuais para exibir o relatório
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Define um tamanho bom para a janela

        // Exibe o relatório em uma janela de diálogo
        JOptionPane.showMessageDialog(this, scrollPane, "Histórico de Movimentações", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getPanel() {
        return panel1;
    }
}
