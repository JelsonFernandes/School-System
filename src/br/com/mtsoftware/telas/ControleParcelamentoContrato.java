
/* * To change this license header, choose License Headers in Project Properties.
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
public class ControleParcelamentoContrato {
    
   Connection conexao ;
   // criando variaveis especiais para conexão com o banco
   // prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst= null;
    ResultSet rs = null; 
     String sql=null;
    public void SalvarParcela(ModeloParcelaContrato mod) throws SQLException{
      conexao = ModuloConexao.conector();
       
        try {
         
            String sql = "insert into tbparcelas(id_contrato,valor_contrato,valor_total,Valorparc,num_parcela,data_venc)value(?,?,?,?,?,?)";
            pst = conexao.prepareStatement(sql);
             pst.setInt(1,mod.getId_contrato());
            pst.setFloat(2,mod.getValor_contrato());
             pst.setFloat(3,mod.getValor_total());
              pst.setFloat(4,mod.getValorparc());
            pst.setInt(5,mod.getNumero_parcela());
            pst.setString(6,mod.getData_venc());
            
             pst.execute();

           
           
                       
           
            
            
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"Erro ao Salvar as Parcelas\nErro:"+ e);
       }
        String sql = "update  tbparcelas set Estado=? where id_contrato=?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, "Não Pago");
                pst.setInt(2,mod.getId_contrato());
                pst.execute();
    }
    
}
