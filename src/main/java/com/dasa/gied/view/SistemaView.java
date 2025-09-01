package com.dasa.gied.view;

import com.dasa.gied.domain.model.Material;

import javax.swing.*;
import java.util.List;

/**
 * Classe responsável por toda a interação com o usuário (interface).
 * Utiliza JOptionPane para exibir menus, solicitar dados e mostrar mensagens.
 */

public class SistemaView {

    public int exibirMenuPrincipal() {
        String menu = "===== G.I.E.D. - Gestão de Estoque Dasa =====\n\n"
                + "1. Consultar Material por ID\n"
                + "2. Registrar Consumo de Material\n"
                + "3. Calcular Previsão de Duração do Estoque\n"
                + "4. Listar Materiais com Estoque Baixo\n"
                + "5. Listar Todos os Materiais\n"
                + "0. Sair\n\n"
                + "Escolha uma opção:";
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(menu));
        } catch (NumberFormatException e) {
            return -1; // Opção inválida
        }
    }

    public String solicitarIdMaterial(String acao) {
        return JOptionPane.showInputDialog("Digite o ID (QR Code) do material para " + acao + ":");
    }

    public int solicitarQuantidade(String acao) {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade para " + acao + ":"));
        } catch (NumberFormatException e) {
            mostrarMensagem("Erro: Quantidade inválida. A operação foi cancelada.");
            return -1;
        }
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void mostrarDetalhesMaterial(String detalhes) {
        JOptionPane.showMessageDialog(null, detalhes, "Detalhes do Material", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarListaMateriais(List<Material> materiais, String titulo) {
        if (materiais.isEmpty()) {
            mostrarMensagem("Nenhum material encontrado.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("--- ").append(titulo).append(" ---\n\n");

        for (Material m : materiais) {
            lista.append(m.getDescricaoCompleta()).append("\n-------------------------\n");
        }

        mostrarDetalhesMaterial(lista.toString());
    }
}
