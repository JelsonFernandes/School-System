/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import static br.com.mtsoftware.telas.TelaPrincipal.lblUsuario;
import java.awt.Color;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jelson Fernandes
 */
public class AvaliacaoDoAluno extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    int valor1, valor2, valor3, mediaFinal;
    int result = 3;
    int valor01, valor02, valor03, mediaFinal01;
    int result01 = 3;
    ///////////////////////////////////////////
    int valor4, valor5, valor6, mediaFinal03;
    int result03 = 3;
    //////////////////////////////////////
    int valor7, valor8, valor9, mediaFinal04;
    int result04 = 3;
    ///////////////////////////////////////
    int valor10, valor11, valor12, mediaFinal5;
    int result05 = 3;
    ////////////////////////////////////////////
    int valor13, valor14, valor15, mediaFinal6;
    int result06 = 3;
    ///////////////////////////////////////////
    int valor16, valor17, valor18, mediaFinal7;
    int result07 = 3;
    //////////////////////////////////////////
    int valor19, valor20, valor21, mediaFinal8;
    int result08 = 3;
    //////////////////////////////////////////////
    int valor22, valor23, valor24, mediaFinal9;
    int result09 = 3;
    ////////////////////////////////////////////
    int valor25, valor26, valor27, mediaFinal10;
    int result10 = 3;
    /////////////////////////////////////////////
    int valor28, valor29, valor30, mediaFinal11;
    int result11 = 3;
    ////////////////////////////////////////////
    int valor31, valor32, valor33, mediaFinal12;
    int result12 = 3;
    //////////////////////////////////////////////
    int valor34, valor35, valor36, mediaFinal13;
    int result13 = 3;
    //////////////////////////////////////////////////
    int valor37, valor38, valor39, mediaFinal14;
    int result14 = 3;
    //////////////////////////////////////////////
    int valor40, valor41, valor42, mediaFinal15;
    int result15 = 3;
////////////////////////////////////////////////
    int valor43, valor44, valor45, mediaFinal16;
    int result16 = 3;
    ///////////////////////////////////////////////
    int valor46, valor47, valor48, mediaFinal17;
    int result17 = 3;
    ////////////////////////////////////////////////
    int valor49, valor50, valor51, mediaFinal18;
    int result18 = 3;
    int valor52, valor53, valor54, mediaFinal19;
    int result19 = 3;
    ///////////////////////////////////////////////////////
    int valor55, valor56, valor57, mediaFinal20;
    int result20 = 3;

    /**
     * Creates new form AvaliaçãoDoAluno
     */
    public AvaliacaoDoAluno() {
        initComponents();
        getContentPane().setBackground(Color.white);
        conexao = ModuloConexao.conector();
    }
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {
        jPanel4.setPreferredSize(new Dimension(805, 680));
        jPanel4.setLayout(new FlowLayout(0));
        jScrollPane1.setBounds(50, 50, 650, 450);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportBorder(BorderFactory.createLoweredSoftBevelBorder());
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setViewportView(jPanel4);
        getContentPane().add(jScrollPane1);
        
    }
    
    private void pesquisar_alunos() {
        String sql = "select idaluno as ID,nomealuno as Nome,classealuno as Classe from tbalunos where nomealuno like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAluPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblAvaliação.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private void setar_campos() {
        int setar = tblAvaliação.getSelectedRow();
        txtAluId.setText(tblAvaliação.getModel().getValueAt(setar, 0).toString());
        Nome.setText(tblAvaliação.getModel().getValueAt(setar, 1).toString());
        Classe.setText(tblAvaliação.getModel().getValueAt(setar, 2).toString());
    }
    
    private void BuscrItem() {
        try {
            List<String> list = new ArrayList<String>();
            String sql = "select abreviatura from tbdisciplina ";
            pst = conexao.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString("abreviatura"));
                
            }
            pst.close();
            DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(list.toArray());
            //  jComboBox4.setModel(defaultComboBox);
            // jComboBox3.setModel(defaultComboBox);

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDoAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consultar() {
        
        String sql = "select *from tbdiscipclasses where classe=?";//Passou para disciplinas
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Classe.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                
                Classe.setText(rs.getString(2));
                jTextField1.setText(rs.getString(3));
                jTextField2.setText(rs.getString(4));
                jTextField3.setText(rs.getString(5));
                jTextField4.setText(rs.getString(6));
                jTextField5.setText(rs.getString(7));
                jTextField6.setText(rs.getString(8));
                jTextField7.setText(rs.getString(9));
                jTextField8.setText(rs.getString(10));
                jTextField9.setText(rs.getString(11));
                jTextField10.setText(rs.getString(12));
                jTextField11.setText(rs.getString(13));
                jTextField12.setText(rs.getString(14));
                jTextField13.setText(rs.getString(15));
                jTextField14.setText(rs.getString(16));
                jTextField15.setText(rs.getString(17));
                jTextField16.setText(rs.getString(18));
                jTextField17.setText(rs.getString(19));
                jTextField18.setText(rs.getString(20));
                jTextField19.setText(rs.getString(21));
                jTextField20.setText(rs.getString(22));
                
            } else {
                JOptionPane.showMessageDialog(null, "Classe não cadastrada");
                //as linhas abaixo limpam os campos

                //D1.setText(null);
                //D2.setText(null);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private void consultarNota() {
        
        String num_aluno = JOptionPane.showInputDialog("Número de Boletim");
        String sql = "select*from avaliacaoaluno where idaluno=" + num_aluno;//Passou para disciplinas
        try {
            pst = conexao.prepareStatement(sql);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                
                idnota.setText(rs.getString(1));
                Nome.setText(rs.getString(2));
                Classe.setText(rs.getString(3));
                jTextField1.setText(rs.getString(4));
                jTextField2.setText(rs.getString(5));
                jTextField3.setText(rs.getString(6));
                jTextField4.setText(rs.getString(7));
                jTextField5.setText(rs.getString(8));
                jTextField6.setText(rs.getString(9));
                jTextField7.setText(rs.getString(10));
                jTextField8.setText(rs.getString(11));
                jTextField9.setText(rs.getString(12));
                jTextField10.setText(rs.getString(13));
                jTextField11.setText(rs.getString(14));
                jTextField12.setText(rs.getString(15));
                jTextField13.setText(rs.getString(16));
                jTextField14.setText(rs.getString(17));
                jTextField15.setText(rs.getString(18));
                jTextField16.setText(rs.getString(19));
                jTextField17.setText(rs.getString(20));
                jTextField18.setText(rs.getString(21));
                jTextField19.setText(rs.getString(22));
                jTextField20.setText(rs.getString(23));
                jTextField21.setText(rs.getString(24));
                jTextField22.setText(rs.getString(25));
                jTextField23.setText(rs.getString(26));
                jTextField024.setText(rs.getString(27));
                jTextField025.setText(rs.getString(28));
                jTextField026.setText(rs.getString(29));
                jTextField27.setText(rs.getString(30));
                jTextField28.setText(rs.getString(31));
                jTextField29.setText(rs.getString(32));
                jTextField30.setText(rs.getString(33));
                jTextField31.setText(rs.getString(34));
                jTextField32.setText(rs.getString(35));
                jTextField33.setText(rs.getString(36));
                jTextField34.setText(rs.getString(37));
                jTextField35.setText(rs.getString(38));
                jTextField36.setText(rs.getString(39));
                jTextField37.setText(rs.getString(40));
                jTextField38.setText(rs.getString(41));
                jTextField39.setText(rs.getString(42));
                jTextField40.setText(rs.getString(43));
                jTextField41.setText(rs.getString(44));
                jTextField42.setText(rs.getString(45));
                jTextField43.setText(rs.getString(46));
                jTextField44.setText(rs.getString(47));
                jTextField45.setText(rs.getString(48));
                jTextField46.setText(rs.getString(49));
                jTextField47.setText(rs.getString(50));
                jTextField48.setText(rs.getString(51));
                jTextField49.setText(rs.getString(52));
                jTextField50.setText(rs.getString(53));
                jTextField51.setText(rs.getString(54));
                jTextField52.setText(rs.getString(55));
                jTextField53.setText(rs.getString(56));
                jTextField54.setText(rs.getString(57));
                jTextField55.setText(rs.getString(58));
                jTextField56.setText(rs.getString(59));
                jTextField57.setText(rs.getString(60));
                jTextField58.setText(rs.getString(61));
                jTextField59.setText(rs.getString(62));
                jTextField60.setText(rs.getString(63));
                jTextField61.setText(rs.getString(64));
                jTextField62.setText(rs.getString(65));
                jTextField63.setText(rs.getString(66));
                jTextField64.setText(rs.getString(67));
                jTextField65.setText(rs.getString(68));
                jTextField66.setText(rs.getString(69));
                jTextField67.setText(rs.getString(70));
                jTextField68.setText(rs.getString(71));
                jTextField69.setText(rs.getString(72));
                jTextField70.setText(rs.getString(73));
                jTextField71.setText(rs.getString(74));
                jTextField72.setText(rs.getString(75));
                jTextField73.setText(rs.getString(76));
                jTextField74.setText(rs.getString(77));
                jTextField75.setText(rs.getString(78));
                jTextField76.setText(rs.getString(79));
                jTextField77.setText(rs.getString(80));
                jTextField78.setText(rs.getString(81));
                jTextField79.setText(rs.getString(82));
                jTextField80.setText(rs.getString(83));
                jTextPaneSituacao.setText(rs.getString(84));
                txtAluId.setText(rs.getString(85));
                jButtonSalvar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi cadastrado nenhum Boletim para Este Aluno");
                //as linhas abaixo limpam os campos

                //D1.setText(null);
                //D2.setText(null);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    //private Void ativar() {

    //}
    private Void adicionar() {
        
        String sql = "insert into avaliacaoaluno( nome, classe, disp1, disp2, disp3, disp4, disp5, disp6, disp7, disp8, disp9, disp10, disp11, disp12, disp13, disp14, disp15, disp16, disp17, disp18, disp19, disp20, n001, n002, n003, n004, n005, n006, n007, n008, n009, n0010, n0011, n0012, n0013, n0014, n0015, n0016, n0017, n0018, n0019, n0020, n01, n02, n03, n04, n05, n06, n07, n08, n09, n010, n011, n012, n013, n014, n015, n016, n017, n018, n019, n020,n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20,situacao,idaluno )value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Nome.getText());
            pst.setString(2, Classe.getText());
            pst.setString(3, jTextField1.getText());
            pst.setString(4, jTextField2.getText());
            pst.setString(5, jTextField3.getText());
            pst.setString(6, jTextField4.getText());
            pst.setString(7, jTextField5.getText());
            pst.setString(8, jTextField6.getText());
            pst.setString(9, jTextField7.getText());
            pst.setString(10, jTextField8.getText());
            pst.setString(11, jTextField9.getText());
            pst.setString(12, jTextField10.getText());
            pst.setString(13, jTextField11.getText());
            pst.setString(14, jTextField12.getText());
            pst.setString(15, jTextField13.getText());
            pst.setString(16, jTextField14.getText());
            pst.setString(17, jTextField15.getText());
            pst.setString(18, jTextField16.getText());
            pst.setString(19, jTextField17.getText());
            pst.setString(20, jTextField18.getText());
            pst.setString(21, jTextField19.getText());
            pst.setString(22, jTextField20.getText());
            pst.setString(23, jTextField21.getText());
            pst.setString(24, jTextField22.getText());
            pst.setString(25, jTextField23.getText());
            pst.setString(26, jTextField024.getText());
            pst.setString(27, jTextField025.getText());
            pst.setString(28, jTextField026.getText());
            pst.setString(29, jTextField27.getText());
            pst.setString(30, jTextField29.getText());
            pst.setString(31, jTextField28.getText());
            pst.setString(32, jTextField30.getText());
            pst.setString(33, jTextField31.getText());
            pst.setString(34, jTextField32.getText());
            pst.setString(35, jTextField33.getText());
            pst.setString(36, jTextField34.getText());
            pst.setString(37, jTextField35.getText());
            pst.setString(38, jTextField36.getText());
            pst.setString(39, jTextField37.getText());
            pst.setString(40, jTextField38.getText());
            pst.setString(41, jTextField39.getText());
            pst.setString(42, jTextField40.getText());
            pst.setString(43, jTextField41.getText());
            pst.setString(44, jTextField42.getText());
            pst.setString(45, jTextField43.getText());
            pst.setString(46, jTextField44.getText());
            pst.setString(47, jTextField45.getText());
            pst.setString(48, jTextField46.getText());
            pst.setString(49, jTextField47.getText());
            pst.setString(50, jTextField48.getText());
            pst.setString(51, jTextField49.getText());
            pst.setString(52, jTextField50.getText());
            pst.setString(53, jTextField51.getText());
            pst.setString(54, jTextField52.getText());
            pst.setString(55, jTextField53.getText());
            pst.setString(56, jTextField54.getText());
            pst.setString(57, jTextField55.getText());
            pst.setString(58, jTextField56.getText());
            pst.setString(59, jTextField57.getText());
            pst.setString(60, jTextField58.getText());
            pst.setString(61, jTextField59.getText());
            pst.setString(62, jTextField60.getText());
            pst.setString(63, jTextField61.getText());
            pst.setString(64, jTextField62.getText());
            pst.setString(65, jTextField63.getText());
            pst.setString(66, jTextField64.getText());
            pst.setString(67, jTextField65.getText());
            pst.setString(68, jTextField66.getText());
            pst.setString(69, jTextField67.getText());
            pst.setString(70, jTextField68.getText());
            pst.setString(71, jTextField69.getText());
            pst.setString(72, jTextField70.getText());
            pst.setString(73, jTextField71.getText());
            pst.setString(74, jTextField72.getText());
            pst.setString(75, jTextField73.getText());
            pst.setString(76, jTextField74.getText());
            pst.setString(77, jTextField75.getText());
            pst.setString(78, jTextField76.getText());
            pst.setString(79, jTextField77.getText());
            pst.setString(80, jTextField78.getText());
            pst.setString(81, jTextField79.getText());
            pst.setString(82, jTextField80.getText());
            pst.setString(83, jTextPaneSituacao.getText());
            pst.setString(84, txtAluId.getText());

            // pst.setString(84, idnota.getText());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Boletim adicionado com sucesso");
                idnota.setText(null);
                
                Classe.setText(null);
                Nome.setText(null);
                jTextField1.setText(null);
                jTextField2.setText(null);
                jTextField3.setText(null);
                jTextField4.setText(null);
                jTextField5.setText(null);
                jTextField6.setText(null);
                jTextField7.setText(null);
                jTextField8.setText(null);
                jTextField9.setText(null);
                jTextField10.setText(null);
                jTextField11.setText(null);
                jTextField12.setText(null);
                jTextField13.setText(null);
                jTextField14.setText(null);
                jTextField15.setText(null);
                jTextField16.setText(null);
                jTextField17.setText(null);
                jTextField18.setText(null);
                jTextField19.setText(null);
                jTextField20.setText(null);
                jTextField21.setText(null);
                jTextField22.setText(null);
                jTextField23.setText(null);
                jTextField024.setText(null);
                jTextField025.setText(null);
                jTextField026.setText(null);
                jTextField27.setText(null);
                jTextField28.setText(null);
                jTextField29.setText(null);
                jTextField30.setText(null);
                jTextField31.setText(null);
                jTextField32.setText(null);
                jTextField33.setText(null);
                jTextField34.setText(null);
                jTextField35.setText(null);
                jTextField36.setText(null);
                jTextField37.setText(null);
                jTextField38.setText(null);
                jTextField39.setText(null);
                jTextField40.setText(null);
                jTextField41.setText(null);
                jTextField42.setText(null);
                jTextField43.setText(null);
                jTextField44.setText(null);
                jTextField45.setText(null);
                jTextField46.setText(null);
                jTextField47.setText(null);
                jTextField48.setText(null);
                jTextField49.setText(null);
                jTextField50.setText(null);
                jTextField51.setText(null);
                jTextField52.setText(null);
                jTextField53.setText(null);
                jTextField54.setText(null);
                jTextField55.setText(null);
                jTextField56.setText(null);
                jTextField57.setText(null);
                jTextField59.setText(null);
                jTextField60.setText(null);
                jTextField61.setText(null);
                jTextField62.setText(null);
                jTextField63.setText(null);
                jTextField64.setText(null);
                jTextField65.setText(null);
                jTextField66.setText(null);
                jTextField67.setText(null);
                jTextField68.setText(null);
                jTextField69.setText(null);
                jTextField70.setText(null);
                jTextField71.setText(null);
                jTextField72.setText(null);
                jTextField73.setText(null);
                jTextField74.setText(null);
                jTextField75.setText(null);;
                jTextField76.setText(null);
                jTextField77.setText(null);
                jTextField78.setText(null);
                jTextField79.setText(null);
                jTextField80.setText(null);
                
            }
            //a linha abaixo atualiza a tabela usuário com os dados do formulário
            //a estrutura abaixo é usada para confirmar a alteração dos dados da tabela

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
        
    }
    
    private void alterar() {
        String sql = "Update avaliacaoaluno set  nome=?, classe=?, disp1=?, disp2=?, disp3=?, disp4=?, disp5=?, disp6=?, disp7=?, disp8=?, disp9=?, disp10=?, disp11=?, disp12=?, disp13=?, disp14=?, disp15=?, disp16=?, disp17=?, disp18=?, disp19=?, disp20=?, n001=?, n002=?, n003=?, n004=?, n005=?, n006=?, n007=?, n008=?, n009=?, n0010=?, n0011=?, n0012=?, n0013=?, n0014=?, n0015=?, n0016=?, n0017=?, n0018=?, n0019=?, n0020=? , n01=?, n02=?, n03=?, n04=?, n05=?, n06=?, n07=?, n08=?, n09=?, n010=?, n011=?, n012=?, n013=?, n014=?, n015=?, n016=?, n017=?, n018=?, n019=?, n020=?,n1=?, n2=?, n3=?, n4=?, n5=?, n6=?, n7=?, n8=?, n9=?, n10=?, n11=?, n12=?, n13=?, n14=?, n15=?, n16=?, n17=?, n18=?, n19=?, n20=?, situacao=?  where id_nota=?";
        try {
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, Nome.getText());
            pst.setString(2, Classe.getText());
            pst.setString(3, jTextField1.getText());
            pst.setString(4, jTextField2.getText());
            pst.setString(5, jTextField3.getText());
            pst.setString(6, jTextField4.getText());
            pst.setString(7, jTextField5.getText());
            pst.setString(8, jTextField6.getText());
            pst.setString(9, jTextField7.getText());
            pst.setString(10, jTextField8.getText());
            pst.setString(11, jTextField9.getText());
            pst.setString(12, jTextField10.getText());
            pst.setString(13, jTextField11.getText());
            pst.setString(14, jTextField12.getText());
            pst.setString(15, jTextField13.getText());
            pst.setString(16, jTextField14.getText());
            pst.setString(17, jTextField15.getText());
            pst.setString(18, jTextField16.getText());
            pst.setString(19, jTextField17.getText());
            pst.setString(20, jTextField18.getText());
            pst.setString(21, jTextField19.getText());
            pst.setString(22, jTextField20.getText());
            pst.setString(23, jTextField21.getText());
            pst.setString(24, jTextField22.getText());
            pst.setString(25, jTextField23.getText());
            pst.setString(26, jTextField024.getText());
            pst.setString(27, jTextField025.getText());
            pst.setString(28, jTextField026.getText());
            pst.setString(29, jTextField27.getText());
            pst.setString(30, jTextField29.getText());
            pst.setString(31, jTextField28.getText());
            pst.setString(32, jTextField30.getText());
            pst.setString(33, jTextField31.getText());
            pst.setString(34, jTextField32.getText());
            pst.setString(35, jTextField33.getText());
            pst.setString(36, jTextField34.getText());
            pst.setString(37, jTextField35.getText());
            pst.setString(38, jTextField36.getText());
            pst.setString(39, jTextField37.getText());
            pst.setString(40, jTextField38.getText());
            pst.setString(41, jTextField39.getText());
            pst.setString(42, jTextField40.getText());
            pst.setString(43, jTextField41.getText());
            pst.setString(44, jTextField42.getText());
            pst.setString(45, jTextField43.getText());
            pst.setString(46, jTextField44.getText());
            pst.setString(47, jTextField45.getText());
            pst.setString(48, jTextField46.getText());
            pst.setString(49, jTextField47.getText());
            pst.setString(50, jTextField48.getText());
            pst.setString(51, jTextField49.getText());
            pst.setString(52, jTextField50.getText());
            pst.setString(53, jTextField51.getText());
            pst.setString(54, jTextField52.getText());
            pst.setString(55, jTextField53.getText());
            pst.setString(56, jTextField54.getText());
            pst.setString(57, jTextField55.getText());
            pst.setString(58, jTextField56.getText());
            pst.setString(59, jTextField57.getText());
            pst.setString(60, jTextField58.getText());
            pst.setString(61, jTextField59.getText());
            pst.setString(62, jTextField60.getText());
            pst.setString(63, jTextField61.getText());
            pst.setString(64, jTextField62.getText());
            pst.setString(65, jTextField63.getText());
            pst.setString(66, jTextField64.getText());
            pst.setString(67, jTextField65.getText());
            pst.setString(68, jTextField66.getText());
            pst.setString(69, jTextField67.getText());
            pst.setString(70, jTextField68.getText());
            pst.setString(71, jTextField69.getText());
            pst.setString(72, jTextField70.getText());
            pst.setString(73, jTextField71.getText());
            pst.setString(74, jTextField72.getText());
            pst.setString(75, jTextField73.getText());
            pst.setString(76, jTextField74.getText());
            pst.setString(77, jTextField75.getText());
            pst.setString(78, jTextField76.getText());
            pst.setString(79, jTextField77.getText());
            pst.setString(80, jTextField78.getText());
            pst.setString(81, jTextField79.getText());
            pst.setString(82, jTextField80.getText());
            pst.setString(83, jTextPaneSituacao.getText());
            pst.setString(84, idnota.getText());
            
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Boletim alterado com sucesso");
                idnota.setText(null);
                
                Classe.setText(null);
                Nome.setText(null);
                jTextField1.setText(null);
                jTextField2.setText(null);
                jTextField3.setText(null);
                jTextField4.setText(null);
                jTextField5.setText(null);
                jTextField6.setText(null);
                jTextField7.setText(null);
                jTextField8.setText(null);
                jTextField9.setText(null);
                jTextField10.setText(null);
                jTextField11.setText(null);
                jTextField12.setText(null);
                jTextField13.setText(null);
                jTextField14.setText(null);
                jTextField15.setText(null);
                jTextField16.setText(null);
                jTextField17.setText(null);
                jTextField18.setText(null);
                jTextField19.setText(null);
                jTextField20.setText(null);
                jTextField21.setText(null);
                jTextField22.setText(null);
                jTextField23.setText(null);
                jTextField024.setText(null);
                jTextField025.setText(null);
                jTextField026.setText(null);
                jTextField27.setText(null);
                jTextField28.setText(null);
                jTextField29.setText(null);
                jTextField30.setText(null);
                jTextField31.setText(null);
                jTextField32.setText(null);
                jTextField33.setText(null);
                jTextField34.setText(null);
                jTextField35.setText(null);
                jTextField36.setText(null);
                jTextField37.setText(null);
                jTextField38.setText(null);
                jTextField39.setText(null);
                jTextField40.setText(null);
                jTextField41.setText(null);
                jTextField42.setText(null);
                jTextField43.setText(null);
                jTextField44.setText(null);
                jTextField45.setText(null);
                jTextField46.setText(null);
                jTextField47.setText(null);
                jTextField48.setText(null);
                jTextField49.setText(null);
                jTextField50.setText(null);
                jTextField51.setText(null);
                jTextField52.setText(null);
                jTextField53.setText(null);
                jTextField54.setText(null);
                jTextField55.setText(null);
                jTextField56.setText(null);
                jTextField57.setText(null);
                jTextField59.setText(null);
                jTextField60.setText(null);
                jTextField61.setText(null);
                jTextField62.setText(null);
                jTextField63.setText(null);
                jTextField64.setText(null);
                jTextField65.setText(null);
                jTextField66.setText(null);
                jTextField67.setText(null);
                jTextField68.setText(null);
                jTextField69.setText(null);
                jTextField70.setText(null);
                jTextField71.setText(null);
                jTextField72.setText(null);
                jTextField73.setText(null);
                jTextField74.setText(null);
                jTextField75.setText(null);;
                jTextField76.setText(null);
                jTextField77.setText(null);
                jTextField78.setText(null);
                jTextField79.setText(null);
                jTextField80.setText(null);
                jTextPaneSituacao.setText(null);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    //método responsavel pela remoção de usuários
    private void remover() {
        // a estrutura abaixo confirma a remoção do usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este Boletim ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from avaliacaoaluno where id_nota=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, idnota.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, " Classe removida com sucesso");
                    idnota.setText(null);
                    
                    Classe.setText(null);
                    Nome.setText(null);
                    jTextField1.setText(null);
                    jTextField2.setText(null);
                    jTextField3.setText(null);
                    jTextField4.setText(null);
                    jTextField5.setText(null);
                    jTextField6.setText(null);
                    jTextField7.setText(null);
                    jTextField8.setText(null);
                    jTextField9.setText(null);
                    jTextField10.setText(null);
                    jTextField11.setText(null);
                    jTextField12.setText(null);
                    jTextField13.setText(null);
                    jTextField14.setText(null);
                    jTextField15.setText(null);
                    jTextField16.setText(null);
                    jTextField17.setText(null);
                    jTextField18.setText(null);
                    jTextField19.setText(null);
                    jTextField20.setText(null);
                    jTextField21.setText(null);
                    jTextField22.setText(null);
                    jTextField23.setText(null);
                    jTextField024.setText(null);
                    jTextField025.setText(null);
                    jTextField026.setText(null);
                    jTextField27.setText(null);
                    jTextField28.setText(null);
                    jTextField29.setText(null);
                    jTextField30.setText(null);
                    jTextField31.setText(null);
                    jTextField32.setText(null);
                    jTextField33.setText(null);
                    jTextField34.setText(null);
                    jTextField35.setText(null);
                    jTextField36.setText(null);
                    jTextField37.setText(null);
                    jTextField38.setText(null);
                    jTextField39.setText(null);
                    jTextField40.setText(null);
                    jTextField41.setText(null);
                    jTextField42.setText(null);
                    jTextField43.setText(null);
                    jTextField44.setText(null);
                    jTextField45.setText(null);
                    jTextField46.setText(null);
                    jTextField47.setText(null);
                    jTextField48.setText(null);
                    jTextField49.setText(null);
                    jTextField50.setText(null);
                    jTextField51.setText(null);
                    jTextField52.setText(null);
                    jTextField53.setText(null);
                    jTextField54.setText(null);
                    jTextField55.setText(null);
                    jTextField56.setText(null);
                    jTextField57.setText(null);
                    jTextField59.setText(null);
                    jTextField60.setText(null);
                    jTextField61.setText(null);
                    jTextField62.setText(null);
                    jTextField63.setText(null);
                    jTextField64.setText(null);
                    jTextField65.setText(null);
                    jTextField66.setText(null);
                    jTextField67.setText(null);
                    jTextField68.setText(null);
                    jTextField69.setText(null);
                    jTextField70.setText(null);
                    jTextField71.setText(null);
                    jTextField72.setText(null);
                    jTextField73.setText(null);
                    jTextField74.setText(null);
                    jTextField75.setText(null);;
                    jTextField76.setText(null);
                    jTextField77.setText(null);
                    jTextField78.setText(null);
                    jTextField79.setText(null);
                    jTextField80.setText(null);
                    jTextPaneSituacao.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                
            }
        }
    }
    
    private void desativarBotao(String user) {
        
    }
    
    private void imprimir() {
        
        int comfirma = JOptionPane.showConfirmDialog(null, "Confirma a Impressão deste Boletim?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (comfirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("idaluno", Integer.parseInt(txtAluId.getText()));
                 filtro.put("id_nota", Integer.parseInt(idnota.getText()));
                
                JasperPrint print = JasperFillManager.fillReport("lib/report/Boletim.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jComboBox55 = new javax.swing.JComboBox<>();
        jComboBox56 = new javax.swing.JComboBox<>();
        jComboBox81 = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        Nome = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        Classe = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTextField01 = new javax.swing.JTextField();
        jTextField02 = new javax.swing.JTextField();
        jTextField03 = new javax.swing.JTextField();
        jTextField04 = new javax.swing.JTextField();
        jTextField05 = new javax.swing.JTextField();
        jTextField06 = new javax.swing.JTextField();
        jTextField07 = new javax.swing.JTextField();
        jTextField08 = new javax.swing.JTextField();
        jTextField09 = new javax.swing.JTextField();
        jTextField010 = new javax.swing.JTextField();
        jTextField011 = new javax.swing.JTextField();
        jTextField012 = new javax.swing.JTextField();
        jTextField013 = new javax.swing.JTextField();
        jTextField014 = new javax.swing.JTextField();
        jTextField015 = new javax.swing.JTextField();
        jTextField016 = new javax.swing.JTextField();
        jTextField017 = new javax.swing.JTextField();
        jTextField018 = new javax.swing.JTextField();
        jTextField019 = new javax.swing.JTextField();
        jTextField020 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTextField61 = new javax.swing.JTextField();
        jTextField62 = new javax.swing.JTextField();
        jTextField63 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        jTextField66 = new javax.swing.JTextField();
        jTextField67 = new javax.swing.JTextField();
        jTextField68 = new javax.swing.JTextField();
        jTextField69 = new javax.swing.JTextField();
        jTextField70 = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        jTextField72 = new javax.swing.JTextField();
        jTextField73 = new javax.swing.JTextField();
        jTextField74 = new javax.swing.JTextField();
        jTextField75 = new javax.swing.JTextField();
        jTextField76 = new javax.swing.JTextField();
        jTextField77 = new javax.swing.JTextField();
        jTextField78 = new javax.swing.JTextField();
        jTextField79 = new javax.swing.JTextField();
        jTextField80 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField024 = new javax.swing.JTextField();
        jTextField025 = new javax.swing.JTextField();
        jTextField026 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        preencher = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneSituacao = new javax.swing.JTextPane();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtAluPesquisar = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblAvaliação = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idnota = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAluId = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(102, 204, 255));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("AVALIAÇÃO DO ALUNO");
        setPreferredSize(new java.awt.Dimension(807, 800));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setForeground(new java.awt.Color(0, 51, 255));
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setEnabled(false);

        jPanel.setBackground(new java.awt.Color(153, 204, 255));
        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("BOLETIM ESCOLAR"));
        jPanel.setForeground(new java.awt.Color(255, 0, 102));
        jPanel.setPreferredSize(new java.awt.Dimension(807, 880));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 102));
        jLabel2.setText("Nº");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 0, 255));
        jLabel25.setText("       DISCIPLINAS");
        jLabel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 0, 255));
        jLabel26.setText("    I TRIMESTRE");
        jLabel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 0, 255));
        jLabel27.setText("     I I TRIMESTRE");
        jLabel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 0, 255));
        jLabel28.setText("I I I TRIMESTRE");
        jLabel28.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 0, 255));
        jLabel29.setText("MÉDIA FINAL");
        jLabel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox55.setBackground(new java.awt.Color(0, 255, 255));
        jComboBox55.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox55.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jComboBox56.setBackground(new java.awt.Color(0, 255, 255));
        jComboBox56.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox56.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jComboBox81.setBackground(new java.awt.Color(0, 255, 255));
        jComboBox81.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox81.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel42.setForeground(new java.awt.Color(51, 51, 255));
        jLabel42.setText("NOME");

        Nome.setBackground(new java.awt.Color(0, 204, 255));

        jLabel43.setForeground(new java.awt.Color(51, 51, 255));
        jLabel43.setText("CLASSE");

        Classe.setBackground(new java.awt.Color(0, 204, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 51));

        jTextField01.setForeground(new java.awt.Color(255, 0, 102));
        jTextField01.setText("0");
        jTextField01.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField01.setEnabled(false);
        jTextField01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField01ActionPerformed(evt);
            }
        });

        jTextField02.setForeground(new java.awt.Color(51, 51, 255));
        jTextField02.setText("0");
        jTextField02.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField02.setEnabled(false);
        jTextField02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField02ActionPerformed(evt);
            }
        });

        jTextField03.setForeground(new java.awt.Color(0, 255, 255));
        jTextField03.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField03.setEnabled(false);
        jTextField03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField03ActionPerformed(evt);
            }
        });

        jTextField04.setForeground(new java.awt.Color(255, 0, 204));
        jTextField04.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField04.setEnabled(false);
        jTextField04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField04ActionPerformed(evt);
            }
        });

        jTextField05.setForeground(new java.awt.Color(255, 0, 0));
        jTextField05.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField05.setEnabled(false);
        jTextField05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField05ActionPerformed(evt);
            }
        });

        jTextField06.setForeground(new java.awt.Color(204, 204, 255));
        jTextField06.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField06.setEnabled(false);
        jTextField06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField06ActionPerformed(evt);
            }
        });

        jTextField07.setForeground(new java.awt.Color(0, 255, 0));
        jTextField07.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField07.setEnabled(false);
        jTextField07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField07ActionPerformed(evt);
            }
        });

        jTextField08.setForeground(new java.awt.Color(51, 0, 255));
        jTextField08.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField08.setEnabled(false);
        jTextField08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField08ActionPerformed(evt);
            }
        });

        jTextField09.setForeground(new java.awt.Color(255, 204, 51));
        jTextField09.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField09.setEnabled(false);
        jTextField09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField09ActionPerformed(evt);
            }
        });

        jTextField010.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField010.setEnabled(false);
        jTextField010.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField010ActionPerformed(evt);
            }
        });

        jTextField011.setForeground(new java.awt.Color(255, 51, 102));
        jTextField011.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField011.setEnabled(false);
        jTextField011.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField011ActionPerformed(evt);
            }
        });

        jTextField012.setForeground(new java.awt.Color(0, 51, 255));
        jTextField012.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField012.setEnabled(false);
        jTextField012.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField012ActionPerformed(evt);
            }
        });

        jTextField013.setForeground(new java.awt.Color(255, 51, 51));
        jTextField013.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField013.setEnabled(false);
        jTextField013.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField013ActionPerformed(evt);
            }
        });

        jTextField014.setForeground(new java.awt.Color(0, 255, 255));
        jTextField014.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField014.setEnabled(false);
        jTextField014.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField014ActionPerformed(evt);
            }
        });

        jTextField015.setForeground(new java.awt.Color(153, 0, 153));
        jTextField015.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField015.setEnabled(false);
        jTextField015.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField015ActionPerformed(evt);
            }
        });

        jTextField016.setForeground(new java.awt.Color(255, 51, 102));
        jTextField016.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField016.setEnabled(false);
        jTextField016.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField016ActionPerformed(evt);
            }
        });

        jTextField017.setForeground(new java.awt.Color(255, 51, 102));
        jTextField017.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField017.setEnabled(false);
        jTextField017.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField017ActionPerformed(evt);
            }
        });

        jTextField018.setForeground(new java.awt.Color(255, 51, 51));
        jTextField018.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField018.setEnabled(false);
        jTextField018.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField018ActionPerformed(evt);
            }
        });

        jTextField019.setForeground(new java.awt.Color(51, 51, 255));
        jTextField019.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField019.setEnabled(false);
        jTextField019.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField019ActionPerformed(evt);
            }
        });

        jTextField020.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField020.setEnabled(false);
        jTextField020.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField020ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField01)
            .addComponent(jTextField02)
            .addComponent(jTextField03)
            .addComponent(jTextField04)
            .addComponent(jTextField05)
            .addComponent(jTextField06)
            .addComponent(jTextField07)
            .addComponent(jTextField08)
            .addComponent(jTextField09)
            .addComponent(jTextField010)
            .addComponent(jTextField011)
            .addComponent(jTextField012)
            .addComponent(jTextField013)
            .addComponent(jTextField014)
            .addComponent(jTextField015)
            .addComponent(jTextField016)
            .addComponent(jTextField017)
            .addComponent(jTextField018)
            .addComponent(jTextField019)
            .addComponent(jTextField020)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTextField01, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField02, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField03, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField04, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField05, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField06, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField07, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField08, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jTextField09, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jTextField010, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField011, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField012, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField013, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField014, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jTextField015, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jTextField016, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField017, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField018, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField019, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField020, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));

        jTextField61.setText("0");

        jTextField62.setText("0");

        jTextField63.setText("0");

        jTextField64.setText("0");

        jTextField65.setText("0");

        jTextField66.setText("0");

        jTextField67.setText("0");
        jTextField67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField67ActionPerformed(evt);
            }
        });

        jTextField68.setText("0");

        jTextField69.setText("0");

        jTextField70.setText("0");

        jTextField71.setText("0");

        jTextField72.setText("0");

        jTextField73.setText("0");
        jTextField73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField73ActionPerformed(evt);
            }
        });

        jTextField74.setText("0");

        jTextField75.setText("0");

        jTextField76.setText("0");

        jTextField77.setText("0");

        jTextField78.setText("0");

        jTextField79.setText("0");

        jTextField80.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField61)
            .addComponent(jTextField62)
            .addComponent(jTextField63)
            .addComponent(jTextField64)
            .addComponent(jTextField65)
            .addComponent(jTextField66)
            .addComponent(jTextField67)
            .addComponent(jTextField69)
            .addComponent(jTextField68)
            .addComponent(jTextField70)
            .addComponent(jTextField71)
            .addComponent(jTextField72)
            .addComponent(jTextField73)
            .addComponent(jTextField74)
            .addComponent(jTextField75)
            .addComponent(jTextField76)
            .addComponent(jTextField77)
            .addComponent(jTextField78)
            .addComponent(jTextField79)
            .addComponent(jTextField80)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField66, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField67, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField68, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField69, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField70, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField71, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField72, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField73, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField74, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField75, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField76, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField77, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField78, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField79, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));

        jTextField41.setText("0");

        jTextField42.setText("0");

        jTextField43.setText("0");
        jTextField43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField43ActionPerformed(evt);
            }
        });

        jTextField44.setText("0");
        jTextField44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField44ActionPerformed(evt);
            }
        });

        jTextField45.setText("0");

        jTextField46.setText("0");

        jTextField47.setText("0");

        jTextField48.setText("0");

        jTextField49.setText("0");

        jTextField50.setText("0");

        jTextField51.setText("0");
        jTextField51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField51ActionPerformed(evt);
            }
        });

        jTextField52.setText("0");
        jTextField52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField52ActionPerformed(evt);
            }
        });

        jTextField53.setText("0");

        jTextField54.setText("0");

        jTextField55.setText("0");
        jTextField55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField55ActionPerformed(evt);
            }
        });

        jTextField56.setText("0");
        jTextField56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField56ActionPerformed(evt);
            }
        });

        jTextField57.setText("0");

        jTextField58.setText("0");

        jTextField59.setText("0");

        jTextField60.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField41)
            .addComponent(jTextField42)
            .addComponent(jTextField43)
            .addComponent(jTextField44)
            .addComponent(jTextField45)
            .addComponent(jTextField46)
            .addComponent(jTextField47)
            .addComponent(jTextField49)
            .addComponent(jTextField48)
            .addComponent(jTextField50)
            .addComponent(jTextField51)
            .addComponent(jTextField52)
            .addComponent(jTextField53)
            .addComponent(jTextField54)
            .addComponent(jTextField55)
            .addComponent(jTextField56)
            .addComponent(jTextField57)
            .addComponent(jTextField58)
            .addComponent(jTextField59)
            .addComponent(jTextField60)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 0));

        jTextField21.setText("0");

        jTextField22.setText("0");

        jTextField23.setText("0");

        jTextField024.setText("0");

        jTextField025.setText("0");

        jTextField026.setText("0");

        jTextField27.setText("0");

        jTextField29.setText("0");

        jTextField28.setText("0");

        jTextField30.setText("0");

        jTextField31.setText("0");
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        jTextField32.setText("0");
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });

        jTextField33.setText("0");
        jTextField33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField33ActionPerformed(evt);
            }
        });

        jTextField34.setText("0");

        jTextField35.setText("0");

        jTextField36.setText("0");

        jTextField37.setText("0");

        jTextField38.setText("0");

        jTextField39.setText("0");

        jTextField40.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField21)
            .addComponent(jTextField22)
            .addComponent(jTextField23)
            .addComponent(jTextField024)
            .addComponent(jTextField025)
            .addComponent(jTextField026)
            .addComponent(jTextField27)
            .addComponent(jTextField29)
            .addComponent(jTextField28)
            .addComponent(jTextField30)
            .addComponent(jTextField31)
            .addComponent(jTextField32)
            .addComponent(jTextField33)
            .addComponent(jTextField34)
            .addComponent(jTextField35)
            .addComponent(jTextField36)
            .addComponent(jTextField37)
            .addComponent(jTextField38)
            .addComponent(jTextField39)
            .addComponent(jTextField40)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jTextField024, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField025, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField026, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setBackground(new java.awt.Color(204, 204, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 102));
        jLabel3.setText("01");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setBackground(new java.awt.Color(204, 204, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("02");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setBackground(new java.awt.Color(204, 204, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 255, 255));
        jLabel5.setText("03");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setBackground(new java.awt.Color(204, 204, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 204));
        jLabel6.setText("04");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setBackground(new java.awt.Color(204, 204, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("05");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setBackground(new java.awt.Color(204, 204, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 255));
        jLabel8.setText("06");
        jLabel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setBackground(new java.awt.Color(204, 204, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 255, 0));
        jLabel9.setText("07");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setBackground(new java.awt.Color(204, 204, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 0, 255));
        jLabel10.setText("08");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setBackground(new java.awt.Color(204, 204, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 204, 51));
        jLabel11.setText("09");
        jLabel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setBackground(new java.awt.Color(204, 204, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 102));
        jLabel14.setText("10");
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setBackground(new java.awt.Color(204, 204, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 102, 102));
        jLabel15.setText("11");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setBackground(new java.awt.Color(204, 204, 255));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 255));
        jLabel16.setText("12");
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setBackground(new java.awt.Color(204, 204, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("13");
        jLabel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel18.setBackground(new java.awt.Color(204, 204, 255));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 255, 255));
        jLabel18.setText("14");
        jLabel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel19.setBackground(new java.awt.Color(204, 204, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 0, 153));
        jLabel19.setText("15");
        jLabel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setBackground(new java.awt.Color(204, 204, 255));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 255, 51));
        jLabel20.setText("16");
        jLabel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel21.setBackground(new java.awt.Color(204, 204, 255));
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 51, 102));
        jLabel21.setText("17");
        jLabel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel22.setBackground(new java.awt.Color(204, 204, 255));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 51, 51));
        jLabel22.setText("18");
        jLabel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel23.setBackground(new java.awt.Color(204, 204, 255));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 255));
        jLabel23.setText("19");
        jLabel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel24.setBackground(new java.awt.Color(204, 204, 255));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("20");
        jLabel24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBackground(new java.awt.Color(153, 153, 153));

        jTextField1.setForeground(new java.awt.Color(255, 0, 102));

        jTextField2.setForeground(new java.awt.Color(51, 51, 255));

        jTextField3.setForeground(new java.awt.Color(102, 255, 255));

        jTextField4.setForeground(new java.awt.Color(255, 51, 255));

        jTextField5.setForeground(new java.awt.Color(255, 51, 51));

        jTextField6.setForeground(new java.awt.Color(204, 204, 255));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.setForeground(new java.awt.Color(102, 255, 0));

        jTextField8.setForeground(new java.awt.Color(51, 51, 255));

        jTextField9.setForeground(new java.awt.Color(255, 204, 0));

        jTextField10.setForeground(new java.awt.Color(255, 204, 0));

        jTextField11.setForeground(new java.awt.Color(255, 51, 51));

        jTextField12.setForeground(new java.awt.Color(51, 0, 255));

        jTextField13.setForeground(new java.awt.Color(255, 0, 0));

        jTextField14.setForeground(new java.awt.Color(51, 255, 255));

        jTextField15.setForeground(new java.awt.Color(204, 51, 255));

        jTextField16.setForeground(new java.awt.Color(0, 255, 102));

        jTextField17.setForeground(new java.awt.Color(255, 0, 51));

        jTextField18.setForeground(new java.awt.Color(255, 51, 0));

        jTextField19.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addComponent(jTextField2)
            .addComponent(jTextField3)
            .addComponent(jTextField4)
            .addComponent(jTextField5)
            .addComponent(jTextField6)
            .addComponent(jTextField7)
            .addComponent(jTextField8)
            .addComponent(jTextField9)
            .addComponent(jTextField10)
            .addComponent(jTextField11)
            .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jTextField13)
            .addComponent(jTextField14)
            .addComponent(jTextField15)
            .addComponent(jTextField16)
            .addComponent(jTextField17)
            .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jTextField19)
            .addComponent(jTextField20)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonSalvar.setText("SALVAR");
        jButtonSalvar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButton3.setText("PESQUISAR");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonExcluir.setText("EXCLUIR");
        jButtonExcluir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("SITUAÇÃO DO ALUNO");

        preencher.setText("Preencher");
        preencher.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        preencher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preencherActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jTextPaneSituacao);

        jButton5.setText("Média");
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                            .addGap(106, 106, 106)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jComboBox81, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox56, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jComboBox55, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(148, 148, 148))))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(Nome)
                                .addGap(407, 407, 407))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(preencher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(86, 86, 86))
                                    .addGroup(jPanelLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(66, 66, 66)))
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLayout.createSequentialGroup()
                                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(66, 66, 66)
                                .addComponent(jButton5))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(429, 429, 429)
                                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelLayout.createSequentialGroup()
                                        .addComponent(Classe)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(186, 186, 186))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)
                            .addComponent(Classe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel29)
                        .addComponent(jLabel28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButton3)
                    .addComponent(jButtonExcluir)
                    .addComponent(jLabel2)
                    .addComponent(jButton5)
                    .addComponent(preencher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(84, 84, 84)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jComboBox56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 208, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 616, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel4);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(51, 0, 255));

        txtAluPesquisar.setBackground(new java.awt.Color(0, 204, 255));
        txtAluPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAluPesquisarActionPerformed(evt);
            }
        });
        txtAluPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAluPesquisarKeyReleased(evt);
            }
        });

        tblAvaliação.setBackground(new java.awt.Color(255, 153, 153));
        tblAvaliação.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblAvaliação.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAvaliaçãoMouseClicked(evt);
            }
        });
        tblAvaliação.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblAvaliaçãoKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tblAvaliação);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("  Pesquiza");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nº Boletim");

        idnota.setEditable(false);
        idnota.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID");

        txtAluId.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtAluPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idnota, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAluId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAluPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAluId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12)
                        .addComponent(idnota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(688, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblAvaliaçãoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAvaliaçãoKeyReleased

    }//GEN-LAST:event_tblAvaliaçãoKeyReleased

    private void tblAvaliaçãoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAvaliaçãoMouseClicked
        // Chamando o método setar campos
        // setar_campos();
        setar_campos();
    }//GEN-LAST:event_tblAvaliaçãoMouseClicked

    private void txtAluPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAluPesquisarKeyReleased
        // chamando o método pesquiasr alunos
        //pesquisar_alunos();
        pesquisar_alunos();
    }//GEN-LAST:event_txtAluPesquisarKeyReleased

    private void txtAluPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAluPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAluPesquisarActionPerformed

    private void jTextField01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField01ActionPerformed

    private void jTextField02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField02ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField02ActionPerformed

    private void jTextField03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField03ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField03ActionPerformed

    private void jTextField04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField04ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField04ActionPerformed

    private void jTextField05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField05ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField05ActionPerformed

    private void jTextField06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField06ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField06ActionPerformed

    private void jTextField07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField07ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField07ActionPerformed

    private void jTextField08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField08ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField08ActionPerformed

    private void jTextField09ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField09ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField09ActionPerformed

    private void jTextField010ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField010ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField010ActionPerformed

    private void jTextField011ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField011ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField011ActionPerformed

    private void jTextField012ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField012ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField012ActionPerformed

    private void jTextField013ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField013ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField013ActionPerformed

    private void jTextField014ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField014ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField014ActionPerformed

    private void jTextField015ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField015ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField015ActionPerformed

    private void jTextField016ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField016ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField016ActionPerformed

    private void jTextField017ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField017ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField017ActionPerformed

    private void jTextField018ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField018ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField018ActionPerformed

    private void jTextField019ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField019ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField019ActionPerformed

    private void jTextField020ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField020ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField020ActionPerformed

    private void preencherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preencherActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_preencherActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:

        String sql = "select(perfil)from tbusuarios where login='" + lblUsuario.getText() + "'";
        try {

            // pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            if (rs.equals("Aluno")) {
                JOptionPane.showMessageDialog(null, "Não Possui permissão para atribuir notas");
            } else {
                adicionar();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        consultarNota();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        // TODO add your handling code here:
        String sql = "select*from tbusuarios where login='" + lblUsuario.getText() + "'";
        try {
            
            pst = conexao.prepareStatement(sql);
            
            rs = pst.executeQuery();
            rs.first();
            if (rs.equals("Aluno")) {
                JOptionPane.showMessageDialog(null, "Não Possui permissão para atribuir notas");
            } else {
                alterar();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        valor1 = Integer.parseInt(jTextField21.getText());
        valor2 = Integer.parseInt(jTextField41.getText());
        valor3 = Integer.parseInt(jTextField61.getText());
        mediaFinal = (valor1 + valor2 + valor3) / result;
        // Tabela.removeAll();
        jTextField01.setText(String.valueOf(mediaFinal));
        //////////////////////////////////////////////////////
        valor01 = Integer.parseInt(jTextField22.getText());
        valor02 = Integer.parseInt(jTextField42.getText());
        valor03 = Integer.parseInt(jTextField62.getText());
        mediaFinal01 = (valor01 + valor02 + valor03) / result01;
        // Tabela.removeAll();
        jTextField02.setText(String.valueOf(mediaFinal01));
        /////////////////////////////////////////////////////////
        valor4 = Integer.parseInt(jTextField23.getText());
        valor5 = Integer.parseInt(jTextField43.getText());
        valor6 = Integer.parseInt(jTextField63.getText());
        mediaFinal03 = (valor4 + valor5 + valor6) / result03;
        // Tabela.removeAll();
        jTextField03.setText(String.valueOf(mediaFinal03));
        /////////////////////////////////////////////////////
        valor7 = Integer.parseInt(jTextField024.getText());
        valor8 = Integer.parseInt(jTextField44.getText());
        valor9 = Integer.parseInt(jTextField64.getText());
        mediaFinal04 = (valor7 + valor8 + valor9) / result04;
        // Tabela.removeAll();
        jTextField04.setText(String.valueOf(mediaFinal04));
        ////////////////////////////////////////////////////
        valor10 = Integer.parseInt(jTextField025.getText());
        valor11 = Integer.parseInt(jTextField45.getText());
        valor12 = Integer.parseInt(jTextField65.getText());
        mediaFinal5 = (valor10 + valor11 + valor12) / result05;
        // Tabela.removeAll();
        jTextField05.setText(String.valueOf(mediaFinal5));
        //////////////////////////////////////////////////
        valor13 = Integer.parseInt(jTextField026.getText());
        valor14 = Integer.parseInt(jTextField46.getText());
        valor15 = Integer.parseInt(jTextField66.getText());
        mediaFinal6 = (valor13 + valor14 + valor15) / result06;
        // Tabela.removeAll();
        jTextField06.setText(String.valueOf(mediaFinal6));
        ///////////////////////////////////////////////////
        valor16 = Integer.parseInt(jTextField27.getText());
        valor17 = Integer.parseInt(jTextField47.getText());
        valor18 = Integer.parseInt(jTextField67.getText());
        mediaFinal7 = (valor16 + valor17 + valor18) / result07;
        // Tabela.removeAll();
        jTextField07.setText(String.valueOf(mediaFinal7));
        ////////////////////////////////////////////////////

        valor19 = Integer.parseInt(jTextField28.getText());
        valor20 = Integer.parseInt(jTextField48.getText());
        valor21 = Integer.parseInt(jTextField68.getText());
        mediaFinal8 = (valor19 + valor20 + valor21) / result08;
        // Tabela.removeAll();
        jTextField08.setText(String.valueOf(mediaFinal8));
        //////////////////////////////////////////////////

        valor22 = Integer.parseInt(jTextField29.getText());
        valor23 = Integer.parseInt(jTextField49.getText());
        valor24 = Integer.parseInt(jTextField69.getText());
        mediaFinal9 = (valor22 + valor23 + valor24) / result09;
        // Tabela.removeAll();
        jTextField09.setText(String.valueOf(mediaFinal9));
        ////////////////////////////////////////////////////

        valor25 = Integer.parseInt(jTextField30.getText());
        valor26 = Integer.parseInt(jTextField50.getText());
        valor27 = Integer.parseInt(jTextField70.getText());
        mediaFinal10 = (valor25 + valor26 + valor27) / result10;
        // Tabela.removeAll();
        jTextField010.setText(String.valueOf(mediaFinal10));
        ///////////////////////////////////////////////////
        valor28 = Integer.parseInt(jTextField31.getText());
        valor29 = Integer.parseInt(jTextField51.getText());
        valor30 = Integer.parseInt(jTextField71.getText());
        mediaFinal11 = (valor28 + valor29 + valor30) / result11;
        // Tabela.removeAll();
        jTextField011.setText(String.valueOf(mediaFinal11));
        ///////////////////////////////////////////////////////
        valor31 = Integer.parseInt(jTextField32.getText());
        valor32 = Integer.parseInt(jTextField52.getText());
        valor33 = Integer.parseInt(jTextField72.getText());
        mediaFinal12 = (valor31 + valor32 + valor33) / result12;
        // Tabela.removeAll();
        jTextField012.setText(String.valueOf(mediaFinal12));
        /////////////////////////////////////////////////////////
        valor34 = Integer.parseInt(jTextField33.getText());
        valor35 = Integer.parseInt(jTextField53.getText());
        valor36 = Integer.parseInt(jTextField73.getText());
        mediaFinal13 = (valor34 + valor35 + valor36) / result13;
        // Tabela.removeAll();
        jTextField013.setText(String.valueOf(mediaFinal13));
        ////////////////////////////////////////////////////
        valor37 = Integer.parseInt(jTextField34.getText());
        valor38 = Integer.parseInt(jTextField54.getText());
        valor39 = Integer.parseInt(jTextField74.getText());
        mediaFinal14 = (valor37 + valor38 + valor39) / result14;
        // Tabela.removeAll();
        jTextField014.setText(String.valueOf(mediaFinal14));
        //////////////////////////////////////////////////////
        valor40 = Integer.parseInt(jTextField35.getText());
        valor41 = Integer.parseInt(jTextField55.getText());
        valor42 = Integer.parseInt(jTextField75.getText());
        mediaFinal15 = (valor40 + valor41 + valor42) / result15;
        // Tabela.removeAll();
        jTextField015.setText(String.valueOf(mediaFinal15));
        /////////////////////////////////////////////////////
        valor43 = Integer.parseInt(jTextField36.getText());
        valor44 = Integer.parseInt(jTextField56.getText());
        valor45 = Integer.parseInt(jTextField76.getText());
        mediaFinal16 = (valor43 + valor44 + valor45) / result16;
        // Tabela.removeAll();
        jTextField016.setText(String.valueOf(mediaFinal16));
        /////////////////////////////////////////////////////
        valor46 = Integer.parseInt(jTextField37.getText());
        valor47 = Integer.parseInt(jTextField57.getText());
        valor48 = Integer.parseInt(jTextField77.getText());
        mediaFinal17 = (valor46 + valor47 + valor48) / result17;
        // Tabela.removeAll();
        jTextField017.setText(String.valueOf(mediaFinal17));
        /////////////////////////////////////////////////////
        valor49 = Integer.parseInt(jTextField38.getText());
        valor50 = Integer.parseInt(jTextField58.getText());
        valor51 = Integer.parseInt(jTextField78.getText());
        mediaFinal18 = (valor49 + valor50 + valor51) / result18;
        // Tabela.removeAll();
        jTextField018.setText(String.valueOf(mediaFinal18));
        /////////////////////////////////////////////////////
        valor52 = Integer.parseInt(jTextField39.getText());
        valor53 = Integer.parseInt(jTextField59.getText());
        valor54 = Integer.parseInt(jTextField79.getText());
        mediaFinal19 = (valor52 + valor53 + valor54) / result19;
        // Tabela.removeAll();
        jTextField019.setText(String.valueOf(mediaFinal19));
        /////////////////////////////////////////////////////
        valor55 = Integer.parseInt(jTextField40.getText());
        valor56 = Integer.parseInt(jTextField60.getText());
        valor57 = Integer.parseInt(jTextField80.getText());
        mediaFinal20 = (valor55 + valor56 + valor57) / result20;
        // Tabela.removeAll();
        jTextField020.setText(String.valueOf(mediaFinal20));
        

    }//GEN-LAST:event_jButton5ActionPerformed
    /* ((Med1)==(NotaA)||(Med2==NotaA)||(Med3==NotaA)||(Med4==NotaA)||(Med5==NotaA)||(Med6==NotaA)||(Med7==NotaA)||(Med8==NotaA)||(Med9==NotaA) ||(Med10==NotaA)||(Med11==NotaA)||(Med12==NotaA)||(Med3|==NotaA)||(Med14==NotaA)||(Med15==NotaA)||(Med16==NotaA)||(Med17==NotaA)||(Med18==NotaA)||(Med19==NotaA)||(Med20==NotaA))*/
    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:

    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
//
//        try {
//            String sql = ("Select (notarep)as NotaRep from tbdiscipclasses");/*notarep ,notarec  ,notaap*/
//
//            pst = conexao.prepareStatement(sql);
//
//            ResultSet rs = pst.executeQuery(sql);
//
//            while (rs.next()) {
//                String Nota = rs.getString("NotaRep");
//                jLabelREP.setText(String.valueOf(Nota));
//            }
//
//        } catch (SQLException error) {
//            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
//        }
//        try {
//            String sql = ("Select (notarec)as NotaReC from tbdiscipclasses");/*notarep ,notarec  ,notaap*/
//
//            pst = conexao.prepareStatement(sql);
//
//            ResultSet rs = pst.executeQuery(sql);
//
//            while (rs.next()) {
//                String Nota = rs.getString("notarec");
//                jLabelRecurso.setText(String.valueOf(Nota));
//            }
//
//        } catch (SQLException error) {
//            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
//        }
//
//        try {
//            String sql = ("Select (notaap)as NotaAp from tbdiscipclasses");/*notarep ,notarec  ,notaap*/
//
//            pst = conexao.prepareStatement(sql);
//
//            ResultSet rs = pst.executeQuery(sql);
//
//            while (rs.next()) {
//                String Nota = rs.getString("NotaAp");
//                jLabelAprovado.setText(String.valueOf(Nota));
//            }
//
//        } catch (SQLException error) {
//            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
//        }
//       
    }//GEN-LAST:event_formInternalFrameOpened

    private void jTextField56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField56ActionPerformed

    private void jTextField55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField55ActionPerformed

    private void jTextField33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField33ActionPerformed

    private void jTextField73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField73ActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void jTextField52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField52ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jTextField51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField51ActionPerformed

    private void jTextField67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField67ActionPerformed

    private void jTextField44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField44ActionPerformed

    private void jTextField43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField43ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        imprimir();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Classe;
    private javax.swing.JTextField Nome;
    private javax.swing.JTextField idnota;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    public static javax.swing.JButton jButtonAlterar;
    public static javax.swing.JButton jButtonExcluir;
    public static javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBox55;
    private javax.swing.JComboBox<String> jComboBox56;
    private javax.swing.JComboBox<String> jComboBox81;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField01;
    private javax.swing.JTextField jTextField010;
    private javax.swing.JTextField jTextField011;
    private javax.swing.JTextField jTextField012;
    private javax.swing.JTextField jTextField013;
    private javax.swing.JTextField jTextField014;
    private javax.swing.JTextField jTextField015;
    private javax.swing.JTextField jTextField016;
    private javax.swing.JTextField jTextField017;
    private javax.swing.JTextField jTextField018;
    private javax.swing.JTextField jTextField019;
    private javax.swing.JTextField jTextField02;
    private javax.swing.JTextField jTextField020;
    private javax.swing.JTextField jTextField024;
    private javax.swing.JTextField jTextField025;
    private javax.swing.JTextField jTextField026;
    private javax.swing.JTextField jTextField03;
    private javax.swing.JTextField jTextField04;
    private javax.swing.JTextField jTextField05;
    private javax.swing.JTextField jTextField06;
    private javax.swing.JTextField jTextField07;
    private javax.swing.JTextField jTextField08;
    private javax.swing.JTextField jTextField09;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField70;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField72;
    private javax.swing.JTextField jTextField73;
    private javax.swing.JTextField jTextField74;
    private javax.swing.JTextField jTextField75;
    private javax.swing.JTextField jTextField76;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField78;
    private javax.swing.JTextField jTextField79;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField80;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextPane jTextPaneSituacao;
    private javax.swing.JButton preencher;
    private javax.swing.JTable tblAvaliação;
    private javax.swing.JTextField txtAluId;
    private javax.swing.JTextField txtAluPesquisar;
    // End of variables declaration//GEN-END:variables
}
