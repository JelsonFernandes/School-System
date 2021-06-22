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
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelson Fernandes
 */
class ControleValidacao {

    Connection conexao = null;
    ResultSet rs = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    int valida;

    public void valida(String senha) {

        conexao = ModuloConexao.conector();

        try {

            String sql = ("Select (data_vencimento)as vencimento from tbvencimento ");
            // conexao.prepareStatement("Select (data_vencimento)as vencimento from tbvencimento ");
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            // pst.executeQuery();
            rs.last();
            valida = Integer.parseInt(rs.getString("vencimento"));
            int operacao = (valida + 132) / 4;//(data)+232/7
            int senhaValidacao = Integer.parseInt(senha);
            System.out.println("Operação" + operacao);
            System.out.println("Senha" + senha);
            if (operacao == senhaValidacao) {
                int dia, mes, ano;
                String AcertaMes = null, AcertaDia, ProxSenha;

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date hoje = new Date();
                String data = df.format(hoje);
                char[] senhachar = data.toCharArray();
                dia = Integer.parseInt("" + senhachar[0] + senhachar[1]);
                mes = Integer.parseInt("" + senhachar[3] + senhachar[4]);
                ano = Integer.parseInt("" + senhachar[6] + senhachar[7] + senhachar[8] + senhachar[9]);
                //JOptionPane.showMessageDialog(null, data); 
                mes+=6;
//                dia=15;
            //    mes+=1;
                if (mes > 12) {
//                   dia+=15;
                    mes=1;
                     ano++;
                    //ano++;

                    if (mes < 10) {
                        AcertaMes = "0" + mes;
                    } else {
                        AcertaMes = "" + mes;
                    }

                } else {
                    mes+=6;
//                    dia=15;
                  
                    AcertaMes = "0" + mes;
                }
                if (dia < 10) {
                    AcertaDia = "0" + dia;
                } else {
                    AcertaDia = "" + dia;
                }
                ProxSenha = AcertaDia + AcertaMes + ano;

                //String sql="insert into tbvencimento(data_vencime nto)value(?)";
                //PreparedStatement pst = conexao.prepareStatement(sql);
                pst = conexao.prepareStatement("insert into tbvencimento(data_vencimento)value(?)");
                pst.setString(1, ProxSenha);
                pst.execute();
                // JOptionPane.showMessageDialog(null, ""+ProxSenha);
            } else {
                JOptionPane.showMessageDialog(null, "Senha Invalida. Verifique e Tente Novamente");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao validar!\nERRO:" + ex);
        }

    }

}
