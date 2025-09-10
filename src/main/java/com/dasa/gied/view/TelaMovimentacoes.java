package com.dasa.gied.view;

import com.dasa.gied.dao.ItemDao;
import com.dasa.gied.dao.LoteEstoqueDao;
import com.dasa.gied.dao.jdbc.JdbcItemDao;
import com.dasa.gied.dao.jdbc.JdbcLoteEstoqueDao;
import com.dasa.gied.domain.enums.TipoUsuario;
import com.dasa.gied.domain.model.Usuario;
import com.dasa.gied.service.ItemService;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TelaMovimentacoes  {
    private JPanel panel1;
    private JButton registrarSaidaButton;
    private JButton registrarEntradaButton;
    private JButton voltarButton;

    private ItemService itemService;

    public TelaMovimentacoes(Usuario usuario) {
        // Instancia os DAOs e o Service
        ItemDao itemDao = new JdbcItemDao();
        LoteEstoqueDao loteEstoqueDao = new JdbcLoteEstoqueDao();
        this.itemService = new ItemService(itemDao, loteEstoqueDao);

        //Ação de voltar
        voltarButton.addActionListener(e -> {
            new TelaMenu(usuario);
            SwingUtilities.getWindowAncestor(panel1).dispose();
        });


        // Ação do Botão "Registrar Saída"
        registrarSaidaButton.addActionListener(e -> {
            // Cria um painel com os campos necessários
            JTextField idItemField = new JTextField(10);
            JTextField quantidadeField = new JTextField(10);

            JPanel saidaPanel = new JPanel();
            saidaPanel.setLayout(new BoxLayout(saidaPanel, BoxLayout.Y_AXIS));
            saidaPanel.add(new JLabel("ID do Item:"));
            saidaPanel.add(idItemField);
            saidaPanel.add(Box.createVerticalStrut(10)); // Espaçamento
            saidaPanel.add(new JLabel("Quantidade a ser retirada:"));
            saidaPanel.add(quantidadeField);

            int result = JOptionPane.showConfirmDialog(panel1, saidaPanel,
                    "Registrar Saída de Estoque", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    Long idItem = Long.parseLong(idItemField.getText());
                    int quantidade = Integer.parseInt(quantidadeField.getText());

                    itemService.RegistrarSaida(idItem, quantidade);

                    JOptionPane.showMessageDialog(panel1, "Saída registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel1, "ID e Quantidade devem ser números válidos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException | IllegalStateException ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage(), "Erro ao Registrar Saída", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do Botão "Registrar Entrada"
        registrarEntradaButton.addActionListener(e -> {
            // Cria um painel com os campos necessários
            JTextField idItemField = new JTextField(10);
            JTextField quantidadeField = new JTextField(10);
            JTextField loteField = new JTextField(10);
            JTextField dataValidadeField = new JTextField(10);

            JPanel entradaPanel = new JPanel();
            entradaPanel.setLayout(new BoxLayout(entradaPanel, BoxLayout.Y_AXIS));
            entradaPanel.add(new JLabel("ID do Item:"));
            entradaPanel.add(idItemField);
            entradaPanel.add(Box.createVerticalStrut(10));
            entradaPanel.add(new JLabel("Quantidade:"));
            entradaPanel.add(quantidadeField);
            entradaPanel.add(Box.createVerticalStrut(10));
            entradaPanel.add(new JLabel("Número do Lote:"));
            entradaPanel.add(loteField);
            entradaPanel.add(Box.createVerticalStrut(10));
            entradaPanel.add(new JLabel("Data de Validade (dd/MM/yyyy):"));
            entradaPanel.add(dataValidadeField);

            int result = JOptionPane.showConfirmDialog(panel1, entradaPanel,
                    "Registrar Entrada de Estoque", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    Long idItem = Long.parseLong(idItemField.getText());
                    int quantidade = Integer.parseInt(quantidadeField.getText());
                    String lote = loteField.getText();

                    // Converte a string da data para LocalDate
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataValidade = LocalDate.parse(dataValidadeField.getText(), formatter);

                    itemService.registrarEntrada(idItem, quantidade, lote, dataValidade);

                    JOptionPane.showMessageDialog(panel1, "Entrada registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel1, "ID e Quantidade devem ser números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(panel1, "Formato de data inválido. Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException | IllegalStateException  ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage(), "Erro ao Registrar Entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }
}
