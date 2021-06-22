/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
import static br.com.mtsoftware.telas.BaixaParcelas1.jTextFieldDesconto;
import static br.com.mtsoftware.telas.BaixaParcelas1.jTextFieldMulta;
import static br.com.mtsoftware.telas.BaixaParcelas1.jTextFieldTotal;
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
public class ControleBaixaParcela {

    Connection conexao = null;
    ResultSet rs = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
     ModeloBaixaParcela mod = new ModeloBaixaParcela();

    public ModeloBaixaParcela BuscaParcela(ModeloBaixaParcela mod) {
        conexao = ModuloConexao.conector();
        // String num_os = JOptionPane.showInputDialog("Número de Parcela");
        String sql = "select*from tbparcelas where id_contrato=" + mod.getCodContr();
        try {

            PreparedStatement pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();
            rs.next();
            mod.setCodParc(rs.getInt("id_parcela"));
            mod.setCodContr(rs.getInt("id_contrato"));
            mod.setDatavenc(rs.getString("data_venc"));
            mod.setValor(rs.getFloat("valorparc"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Parcelas!/nERRO: " + ex);
        }

        return mod;
    }

    public void BaixarParcela(ModeloBaixaParcela mod) throws SQLException {

        conexao = ModuloConexao.conector();
     String sql1=("select*from tbparcelas where id_parcela=" + mod.getCodParc() + " and estado='PAGO'");
           pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "Esta Parcela já Está Paga! ");
        } else {
            try {
             
                String sql = "update  tbparcelas set Estado=?,dataat=?,desconto=?,multa=?,totalapag=? where id_parcela=?";
                Date data = new Date();
//               
//                DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //lblData.setText(formatador.format(data));
                pst = conexao.prepareStatement(sql);
                  rs.last();
                pst.setString(1, "PAGO");
                pst.setString(2, (sdf.format(data)));
                pst.setString(3, jTextFieldDesconto.getText());
                pst.setString(4, jTextFieldMulta.getText());
                pst.setString(5, jTextFieldTotal.getText());
                pst.setInt(6, mod.getCodParc());
                pst.execute();

                JOptionPane.showMessageDialog(null, "Parcela Paga Com Sucesso");

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao  Fazer o Pagamento!\nERRO" + e);
            }
}
           
        
    }
    

}
