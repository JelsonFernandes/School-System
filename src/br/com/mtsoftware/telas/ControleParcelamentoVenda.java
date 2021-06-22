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
public class ControleParcelamentoVenda {

    Connection conexao;
    // criando variaveis especiais para conexão com o banco
    // prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql = null;

    public void SalvarParcela(ModeloParcelaVenda mod) throws SQLException {
        conexao = ModuloConexao.conector();

        try {

            String sql = "insert into tbparcelaVendas(id_venda,valor_venda,quantidade_parcelas,valorparc,valor_total,dataparc)value(?,?,?,?,?,?)";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, mod.getId_Venda());
            pst.setFloat(2, mod.getValor_Venda());
            pst.setInt(3, mod.getNumero_parcela());
            pst.setFloat(4, mod.getValorparc());

            pst.setFloat(5, mod.getValor_total());

            pst.setString(6, mod.getData_venc());

            pst.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar as Parcelas\nErro:" + e);
        }
        String sql = "update  tbparcelavendas set estado=? where id_venda=?";
        pst = conexao.prepareStatement(sql);
        pst.setString(1, "Não Pago");
        pst.setInt(2, mod.getId_Venda());
        pst.execute();
    }

}
