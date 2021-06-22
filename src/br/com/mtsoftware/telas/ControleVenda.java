/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelson Fernandes
 */
public class ControleVenda {

    int codProduto, codCliente;

    Connection conexao = null;
    ResultSet rs = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;

    public void adicionarItem(ModeloVenda mod) {
        conexao = ModuloConexao.conector();
        achaCodProduto(mod.getNomeProduto());
//        String sql = ("insert into tbitemvendas(idvenda,idproduto,quant_produto)values(?,?,?)");
        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setInt(1, mod.getId_Venda());
//            pst.setInt(2, codProduto);
//            pst.setInt(3, mod.getQuantItem());
//            pst.execute();
            //Baixa de estoque
            int quant = 0, resto = 0;
            String sql1 = ("select*from tbprodutos where nome_produto='" + mod.getNomeProduto() + "'");
            pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
            rs.first();
            quant = rs.getInt("quantidade");
            resto = quant - mod.getQuantItem();
            String sql2 = ("update tbprodutos set quantidade=? where nome_produto=?");
            pst = conexao.prepareStatement(sql2);
            pst.setInt(1, resto);
            pst.setString(2, mod.getNomeProduto());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Prduto adicionado com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Realizar a Venda!|nERRO:" + ex);
        }
    }

    public void achaCodProduto(String nome) {
        conexao = ModuloConexao.conector();
        String sql = ("select*from tbprodutos where nome_produto='" + nome + "'");
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            codProduto = rs.getInt("Id_produto");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o Código!|nERRO:" + ex);
        }

    }

    public void fechaVenda(ModeloVenda mod) {
        conexao = ModuloConexao.conector();
//        achaCliente(mod.getNomeCliente());
        String sql = ("insert into tbitemvendas( data_venda,total_venda,tipo_pag) values(?,?,?)");
        try {
//            java.util.Date data = new java.util.Date();

            pst = conexao.prepareStatement(sql);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//            pst.setString(1, (sdf.format(data)));
//            pst.setDate(1, (Date) mod.getData_Venda());
            pst.setString(1, mod.getData_Venda());
            pst.setFloat(2, mod.getValor_Venda());
            pst.setString(3, mod.getPagamento());
//            pst.setInt(5, mod.getId_Venda());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Venda Finalizada Com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a venda!|nERRO:" + ex);
        }
    }

    public void achaCliente(String nome) {
        try {
            conexao = ModuloConexao.conector();
            String sql = ("select*from tbclientes where nomecli='" + nome + "'");
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            codCliente = rs.getInt("idcli");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o Cliente!|nERRO:" + ex);
        }
    }

    void cancelaVenda() {
        conexao = ModuloConexao.conector();
        try {
            String sql1 = ("select*from tbvendas inner join tbitemvendas on tbvendas.id_venda=tbitemvendas.idvenda inner join tbprodutos on tbitemvendas.id_produto=tbprodutos.id_produto where valor_venda=0");
            pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
            rs.first();
            do {
                int qtdProd = rs.getInt("quantidade");
                int qtdVenda = rs.getInt("quant_produto");
                int soma = qtdProd + qtdVenda;
                String sql2 = ("update tbprodutos set quantidade=? where id_produto=?");
                pst = conexao.prepareStatement(sql2);
                pst.setInt(1, soma);
                pst.setInt(2, rs.getInt("id_produto"));
                pst.execute();
                int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este item da venda?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (confirma == JOptionPane.YES_OPTION) {
                    String sql3 = "delete from tbitemvendas where idvenda=?";

                    pst = conexao.prepareStatement(sql3);
                    pst.setInt(1, rs.getInt("id_venda"));
                    pst.execute();
                }

            } while (rs.next());

            int confirma1 = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta venda?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma1 == JOptionPane.YES_OPTION) {

            }
            String sql = "delete from tbvendas where valor_venda=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, 0);
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, " Venda removida com sucesso");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cancelar a venda!|nERRO:" + ex);
        }

    }

}
