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
import javax.swing.JOptionPane;

/**
 *
 * @author Jelson Fernandes
 */
public class ControleDataVencimento {
     Connection conexao = null;
    ResultSet rs = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null; 
    public void salvar_data(String data){
        conexao = ModuloConexao.conector();
         try {
             
             String sql="insert into tbvencimento(data_vencimento)value(?)";
             PreparedStatement pst = conexao.prepareStatement(sql);
             pst = conexao.prepareStatement(sql);
             pst.setString(1, data);
             pst.execute();
             JOptionPane.showMessageDialog(null, "Data Cadastrada Com Sucesso!");
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Na inserção da data!\nERRO:"+ex);
         }
        
    }
    
}
