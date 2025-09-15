package com.dasa.gied.view;

import com.dasa.gied.dao.ItemDao;
import com.dasa.gied.dao.LoteEstoqueDao;
import com.dasa.gied.dao.MovimentacaoDao;
import com.dasa.gied.dao.jdbc.JdbcItemDao;
import com.dasa.gied.dao.jdbc.JdbcLoteEstoqueDao;
import com.dasa.gied.dao.jdbc.JdbcMovimentacaoDao;
import com.dasa.gied.domain.model.Usuario;
import com.dasa.gied.domain.model.Item;
import com.dasa.gied.domain.model.LoteEstoque;
import com.dasa.gied.service.ItemService;

import javax.swing.*;
import java.util.List;

public class TelaEstoque extends JFrame {
    private JPanel panel1;
    private JButton estoqueTodoButton;
    private JButton estoqueBaixoButton;
    private JButton voltarButton;
    private JButton porIDDoItemButton;

    private ItemService itemService;

    public TelaEstoque(Usuario usuario) {
        // Instancia os DAOs e o Service
        ItemDao itemDao = new JdbcItemDao();
        LoteEstoqueDao loteEstoqueDao = new JdbcLoteEstoqueDao();
        MovimentacaoDao movimentacaoDao = new JdbcMovimentacaoDao();
        this.itemService = new ItemService(itemDao, loteEstoqueDao, movimentacaoDao);

        voltarButton.addActionListener(e -> {
            new TelaMenu(usuario);
            SwingUtilities.getWindowAncestor(panel1).dispose();
        });

        estoqueTodoButton.addActionListener(e -> {
            try {
                List<Item> todosOsItens = itemService.listarEstoque();
                if (todosOsItens.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Nenhum item cadastrado no estoque.", "Estoque Vazio", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Formata a lista de itens para exibição
                StringBuilder sb = new StringBuilder("--- Lista Completa de Itens ---\n\n");
                for (Item item : todosOsItens) {
                    sb.append("ID: ").append(item.getId())
                            .append("\nNome: ").append(item.getNome())
                            .append("\nDescrição: ").append(item.getDescricao())
                            .append("\n");
                }

                // Exibe os resultados em uma área de texto rolável
                JTextArea textArea = new JTextArea(sb.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
                JOptionPane.showMessageDialog(panel1, scrollPane, "Estoque Completo", JOptionPane.INFORMATION_MESSAGE);

            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(panel1, "Erro ao buscar estoque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Ação do Botão "Estoque Baixo"
        estoqueBaixoButton.addActionListener(e -> {
            try {
                List<Item> itensBaixos = itemService.listarEstoqueBaixo();
                if (itensBaixos.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Nenhum item com estoque baixo.", "Estoque Baixo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                StringBuilder sb = new StringBuilder("--- Itens com Estoque Baixo ---\n\n");
                for (Item item : itensBaixos) {
                    sb.append("ID: ").append(item.getId())
                            .append(", Nome: ").append(item.getNome())
                            .append(", Nível Mínimo: ").append(item.getNivelMinEstoque())
                            .append(", Quantidade no estoque: ").append(item.getQuantidadeNoEstoque())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(panel1, sb.toString(), "Itens com Estoque Baixo", JOptionPane.WARNING_MESSAGE);

            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(panel1, "Erro ao buscar estoque baixo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Ação do Botão "Por ID do Item"
        porIDDoItemButton.addActionListener(e -> {
            String idInput = JOptionPane.showInputDialog(panel1, "Digite o ID do item:", "Consultar por ID", JOptionPane.QUESTION_MESSAGE);
            if (idInput != null && !idInput.trim().isEmpty()) {
                try {
                    Long idItem = Long.parseLong(idInput);
                    List<LoteEstoque> lotes = itemService.ConsultarEstoque(idItem);

                    if(lotes.isEmpty()){
                        JOptionPane.showMessageDialog(panel1, "Nenhum lote encontrado para o item com ID: " + idItem, "Consulta por ID", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    StringBuilder sb = new StringBuilder("--- Lotes para o Item ID: " + idItem + " ---\n\n");
                    for(LoteEstoque lote : lotes) {
                        sb.append("Lote: ").append(lote.getLote())
                                .append(", Quantidade: ").append(lote.getQuantidade())
                                .append(", Validade: ").append(lote.getDataValidade().toString())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(panel1, sb.toString(), "Consulta por ID", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel1, "ID inválido. Por favor, digite apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch ( RuntimeException ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage(), "Erro na Consulta", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public JPanel getPanel() {
        return panel1;
    }

}



