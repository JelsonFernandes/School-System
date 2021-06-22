/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelson Fernandes
 */
public class ControleBaixaVenda {
      Connection conexao = null;
    ResultSet rs = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
     ModeloBaixaVenda mod = new ModeloBaixaVenda();
     
     public ModeloBaixaVenda BuscaParcela(ModeloBaixaVenda mod) {
        conexao = ModuloConexao.conector();
        // String num_os = JOptionPane.showInputDialog("Número de Parcela");
        String sql = "select*from tbparcelavendas where id_venda=" + mod.getCodContr();
        try {

            PreparedStatement pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();
            rs.next();
            mod.setCodParc(rs.getInt("id_parcela"));
            mod.setCodContr(rs.getInt("id_venda"));
            mod.setDatavenc(rs.getString("dataparc"));
            mod.setValor(rs.getFloat("valorparc"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Parcelas!/nERRO: " + ex);
        }

        return mod;
    }
     public void BaixaVenda(ModeloBaixaVenda mod) throws SQLException {

        conexao = ModuloConexao.conector();
     String sql1=("select*from tbparcelaVendas where id_parcela=" + mod.getCodParc() + " and Estado='PAGO'");
           pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "Esta Parcela já Está Paga! ");
        } else {
            try {
             
                String sql = "update  tbparcelavendas set Estado=?,dataat=? where id_parcela=?";
                Date data = new Date();
//               
//                DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //lblData.setText(formatador.format(data));
                pst = conexao.prepareStatement(sql);
                  rs.last();
                pst.setString(1, "PAGO");
                pst.setString(2, (sdf.format(data)));
                pst.setInt(3, mod.getCodParc());
                
                pst.execute();

                JOptionPane.showMessageDialog(null, "Parcela Paga Com Sucesso");

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao  Fazer o Pagamento!\nERRO" + e);
            }
}
           
        
    }
    

    
}
