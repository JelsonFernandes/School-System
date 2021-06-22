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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelson Fernandes
 */
public class ControleProduto {

    Connection conexao = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;
    int codFornecedor;
    String nomeFornecedor;
    ModeloProduto mod = new ModeloProduto();

    public void inserirProduto(ModeloProduto mod) {
        conexao = ModuloConexao.conector();
        buscarCod(mod.getFornecedor());
        try {
            String sql = ("insert into tbprodutos ( nome_produto, Preco_venda, Preco_compra, quantidade,id_fornecedor)values(?,?,?,?,?)");
            pst = conexao.prepareStatement(sql);
            pst = conexao.prepareStatement(sql);

            // ResultSet rs = pst.executeQuery(sql);
            pst.setString(1, mod.getNomeProduto());
            pst.setFloat(2, mod.getPrecoVenda());
            pst.setFloat(3, mod.getPrecoCompra());
            pst.setInt(4, mod.getQuatProduto());
            pst.setInt(5, codFornecedor);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto Cadastrado Com Sucesso");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o Produto!\nERRO: " + e);
        }

    }
    public void editaProduto(ModeloProduto mod){
         conexao = ModuloConexao.conector();
          buscarCod(mod.getFornecedor());
         String sql="Update tbprodutos set nome_produto=?, Preco_venda=?, Preco_compra=?, quantidade=?,id_fornecedor=? where id_produto=?";
        try {
            pst = conexao.prepareStatement(sql);
             pst.setString(1, mod.getNomeProduto());
            pst.setFloat(2, mod.getPrecoVenda());
            pst.setFloat(3, mod.getPrecoCompra());
            pst.setInt(4, mod.getQuatProduto());
            pst.setInt(5, codFornecedor);
            pst.setInt(6, mod.getIdProduto());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto Alterado Com Sucesso");
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao Alterar o Produto!\nERRO: " + ex);
        }
        
    }
    public void excluirProduto(ModeloProduto mod){
        conexao = ModuloConexao.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este Produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
        String sql=" delete from tbprodutos where id_produto=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, mod.getIdProduto());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto Excluido Com Sucesso");
             
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir o Produto!\nERRO: " + ex);
        }
        }
    }

    public ModeloProduto buscaProduto(ModeloProduto modelo) {
        conexao = ModuloConexao.conector();
        String sql = "select*from tbprodutos where nome_produto like'%" + modelo.getPesquisa() + "%'";
        try {
            pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            rs.first();
            buscaNomeFornecedor(rs.getInt("id_fornecedor"));
            mod.setIdProduto(rs.getInt("id_produto"));
            mod.setNomeProduto(rs.getString("nome_produto"));
            mod.setPrecoVenda(rs.getFloat("Preco_venda"));
            mod.setPrecoCompra(rs.getFloat("Preco_compra"));
            mod.setQuatProduto(rs.getInt("quantidade"));
            mod.setFornecedor(nomeFornecedor);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o Produto!\nERRO: " + e);
        }
        return mod;
    }
    public void buscaNomeFornecedor(int cod){
         conexao = ModuloConexao.conector();
         String sql="select*from tbfornecedores where id_fornecedor="+cod+"";
         try {
              pst = conexao.prepareStatement(sql);
              ResultSet rs = pst.executeQuery(sql);
            rs.first();
            nomeFornecedor=rs.getString("Nome_fornecedor");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o Código!\nERRO: " + e);
        }
    }

    public void buscarCod(String nome) {
        conexao = ModuloConexao.conector();
        String sql = "select*from tbfornecedores where Nome_fornecedor='" + nome + "'";
        try {
            pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            rs.first();
            codFornecedor = rs.getInt("id_fornecedor");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o ID do Fornecedor!\nERRO: " + e);
        }

    }

}
