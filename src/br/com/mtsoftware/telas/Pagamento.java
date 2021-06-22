/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelson Fernandes
 */
public class Pagamento extends javax.swing.JInternalFrame {

    Connection conexao = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;
    String vencimento;
    int diaVenc, mesVenc, anoVenc;
    int valida;

    /**
     * Creates new form Pagamento
     */
    public Pagamento() {
        initComponents();
         getContentPane().setBackground(Color.white);
        conexao = ModuloConexao.conector();
    }

    public void ValorTotalAluno() {

        conexao = ModuloConexao.conector();

        try {
            String num_contrato = JOptionPane.showInputDialog("Número de contrato");
            String sql = "Select  sum(valorparc)as soma from tbparcelas where id_contrato=" + num_contrato;

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField6.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorNaoPago() {

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select Estado, sum(valorparc)as soma from tbparcelas where (Estado='Não Pago')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField4.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorInscricoes() {

        conexao = ModuloConexao.conector();

        try {
            String sql = ("Select sum(Valorinscr)as valor from  tbalunos ");

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float valor = rs.getFloat("valor");
                jTextField5.setText(String.valueOf(valor) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void TotalAluno() {

        conexao = ModuloConexao.conector();

        try {
            String sql = ("Select count(idaluno)as conta from tbalunos ");

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float conta = rs.getFloat("conta");
                jTextField1.setText(String.valueOf(conta));
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorPagTotais() {
        conexao = ModuloConexao.conector();

        try {
            String sql = ("select Estado, sum(valorparc) as pago from tbparcelas where (Estado='PAGO')");
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                float pago = rs.getFloat("pago");
                jTextField3.setText(String.valueOf(pago) + " KZ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }

    }

    public void ValorUnifomes() {

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Uniforme')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField9.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorCartões() {

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Cartões')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField10.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorFolhas() {

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Folhas de Provas')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField11.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorBoletim() {

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Boletim')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField12.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorCertificados() {

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Certificado')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField13.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorOutros() {

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Outros')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField14.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorTotais() {

        conexao = ModuloConexao.conector();

        try {
            String sql = ("Select sum(valorparc)as soma from tbparcelas ");

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField2.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    
    public void ValorAluno() {

        conexao = ModuloConexao.conector();

        try {
            String num_contrato = JOptionPane.showInputDialog("Número de contrato");
            String sql = "Select Estado, sum(valorparc)as soma from tbparcelas where (Estado='PAGO') and id_contrato=" + num_contrato;

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField7.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }

    public void ValorAlunoDivida() {

        conexao = ModuloConexao.conector();

        try {
            String num_contrato = JOptionPane.showInputDialog("Número de contrato");
            String sql = "Select Estado, sum(valorparc)as soma from tbparcelas where (Estado='Não Pago') and id_contrato=" + num_contrato;

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField8.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        Uniformes = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        Cartoes = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Folhas = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Boletim = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Certificado = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        Outros = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldJaneiro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldFevereiro = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldMarço = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldAbril = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldMaio = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldJunho = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldJulho = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldAgosto = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldSetembro = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldOutubro = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldNovembro = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldDezembro = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 204, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(807, 680));
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

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CONSULTAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 255, 0))); // NOI18N
        jPanel2.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("  NÚMERO DE ALUNOS INSCRITOS");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 0, 0));
        jTextField1.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField1.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TOTAL DE VALORES POR RECEBER");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 0, 0));
        jTextField2.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField2.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("  TOTAL DE VALORES COBRADOS");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 0, 0));
        jTextField3.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField3.setEnabled(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("  DÍVIDA POR RECEBER");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 0, 0));
        jTextField4.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField4.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("  VALOR DAS INSCRIÇÕES");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(255, 0, 0));
        jTextField5.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField5.setEnabled(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(" TOTAL DO ALUNO ALUNO POR PAGAR");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(255, 0, 0));
        jTextField6.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField6.setEnabled(false);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TOTAL PAGO");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(255, 0, 0));
        jTextField7.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField7.setEnabled(false);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DÍVIDA À PAGAR");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(255, 0, 0));
        jTextField8.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField8.setEnabled(false);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OUTROS VALORES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 0))); // NOI18N

        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(255, 0, 0));
        jTextField9.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField9.setEnabled(false);

        Uniformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        Uniformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UniformesActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 204, 0));
        jLabel9.setText("UNIFORMES");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Cartoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        Cartoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CartoesActionPerformed(evt);
            }
        });

        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(255, 0, 0));
        jTextField10.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField10.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 204, 0));
        jLabel10.setText("CARTÕES");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Folhas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        Folhas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FolhasActionPerformed(evt);
            }
        });

        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(255, 0, 0));
        jTextField11.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField11.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 204, 0));
        jLabel11.setText(" FOLHAS DE PROVAS");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Boletim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        Boletim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoletimActionPerformed(evt);
            }
        });

        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(255, 0, 0));
        jTextField12.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField12.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 204, 0));
        jLabel12.setText("BOLETIM");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Certificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        Certificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CertificadoActionPerformed(evt);
            }
        });

        jTextField13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(255, 0, 0));
        jTextField13.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField13.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 204, 0));
        jLabel13.setText("CERTIFICADOS");
        jLabel13.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 204, 0));
        jLabel21.setText("OUTROS");
        jLabel21.setBorder(new javax.swing.border.MatteBorder(null));

        jTextField14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField14.setForeground(new java.awt.Color(255, 0, 0));
        jTextField14.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextField14.setEnabled(false);

        Outros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        Outros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OutrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Uniformes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Certificado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Folhas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Boletim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Outros, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cartoes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Certificado, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Uniformes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Cartoes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Folhas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField11)
                    .addComponent(jTextField12)
                    .addComponent(Boletim, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Outros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Cogrados Por Cada Més", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 0, 51))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jTextFieldJaneiro.setEnabled(false);

        jLabel14.setBackground(new java.awt.Color(204, 204, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("                   Janeiro");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setBackground(new java.awt.Color(204, 204, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("                Fevereiro");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldFevereiro.setEnabled(false);

        jLabel16.setBackground(new java.awt.Color(204, 204, 255));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("                  Março");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldMarço.setEnabled(false);

        jLabel17.setBackground(new java.awt.Color(204, 204, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("                     Abril");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldAbril.setEnabled(false);

        jLabel18.setBackground(new java.awt.Color(204, 204, 255));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("                    Maio");
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldMaio.setEnabled(false);

        jLabel19.setBackground(new java.awt.Color(204, 204, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("                  Junho");
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldJunho.setEnabled(false);

        jLabel20.setBackground(new java.awt.Color(204, 204, 255));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("                     Julho");
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldJulho.setEnabled(false);

        jLabel22.setBackground(new java.awt.Color(204, 204, 255));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("                 Agosto");
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldAgosto.setEnabled(false);

        jLabel23.setBackground(new java.awt.Color(204, 204, 255));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("               Setembro");
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldSetembro.setEnabled(false);

        jLabel24.setBackground(new java.awt.Color(204, 204, 255));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("                  Outubro");
        jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldOutubro.setEnabled(false);

        jLabel25.setBackground(new java.awt.Color(204, 204, 255));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("               Novembro");
        jLabel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldNovembro.setEnabled(false);

        jLabel26.setBackground(new java.awt.Color(204, 204, 255));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("               Dezembro");
        jLabel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldDezembro.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldJaneiro)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldAbril)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldFevereiro)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldMarço)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldMaio)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldJunho)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldJulho)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldOutubro)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldNovembro)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldDezembro)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldAgosto)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldSetembro)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMarço, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFevereiro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldJaneiro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAbril, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMaio, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldJunho, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldJulho, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAgosto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSetembro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldOutubro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNovembro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDezembro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextField3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel6)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(37, 37, 37))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel6))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(jTextField6)
                                    .addComponent(jTextField7)))
                            .addComponent(jTextField1))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(jTextField8))
                        .addGap(35, 35, 35)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField2)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        ValorTotalAluno();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        ValorAluno();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        ValorAlunoDivida();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void UniformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UniformesActionPerformed
        // TODO add your handling code here:
        ValorUnifomes();
    }//GEN-LAST:event_UniformesActionPerformed

    private void CartoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CartoesActionPerformed
        // TODO add your handling code here:
        ValorCartões();
    }//GEN-LAST:event_CartoesActionPerformed

    private void FolhasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FolhasActionPerformed
        // TODO add your handling code here:
        ValorFolhas();
    }//GEN-LAST:event_FolhasActionPerformed

    private void BoletimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoletimActionPerformed
        // TODO add your handling code here:
        ValorBoletim();
    }//GEN-LAST:event_BoletimActionPerformed

    private void CertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CertificadoActionPerformed
        // TODO add your handling code here:
        ValorCertificados();
    }//GEN-LAST:event_CertificadoActionPerformed

    private void OutrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OutrosActionPerformed
        // TODO add your handling code here:
        ValorOutros();
    }//GEN-LAST:event_OutrosActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:

    }//GEN-LAST:event_formInternalFrameActivated
    //Método executado ao startar o form
    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        conexao = ModuloConexao.conector();
        Date data = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.get(Calendar.MONTH);
        System.out.println(mesVenc);

        try {
            String sql = ("Select sum(Valorinscr)as valor from  tbalunos ");

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float valor = rs.getFloat("valor");
                jTextField5.setText(String.valueOf(valor) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }
        conexao = ModuloConexao.conector();

        try {
            String sql = ("Select sum(valorparc)as soma from tbparcelas ");

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField2.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }
        conexao = ModuloConexao.conector();

        try {

            String sql = "Select Estado, sum(valorparc)as soma from tbparcelas where (Estado='Não Pago')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField4.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }
        conexao = ModuloConexao.conector();

        try {
            String sql = ("select Estado, sum(totalapag) as pago from tbparcelas where (Estado='PAGO')");
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                float pago = rs.getFloat("pago");
                jTextField3.setText(String.valueOf(pago) + " KZ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
        conexao = ModuloConexao.conector();

        try {
            String sql = ("Select count(idaluno)as conta from tbalunos ");

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float conta = rs.getFloat("conta");
                jTextField1.setText(String.valueOf(conta));
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }
        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Uniforme')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField9.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }
        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Cartões')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField10.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Folhas de Provas')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField11.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }
        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Boletim')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField12.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }
        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Certificado')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField13.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbpagamentos where (tipo='Outros')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jTextField14.setText(String.valueOf(soma) + " KZ");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }
//         SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");

        try {
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=01 and Estado='PAGO'";
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldJaneiro.setText(String.valueOf(soma) + " KZ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
        conexao = ModuloConexao.conector();
        try {
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=02 and Estado='PAGO'";
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldFevereiro.setText(String.valueOf(soma) + " KZ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
        try {
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=03 and Estado='PAGO'";
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldMarço.setText(String.valueOf(soma) + " KZ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
        try {
            String sql = "select month(dataat) ,sum(totalapag) as pago from tbparcelas where month(dataat)=04 and Estado='PAGO'";
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pago");
                jTextFieldAbril.setText(String.valueOf(soma) + " KZ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
        try {
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=05 and Estado='PAGO'";
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldMaio
                        .setText(String.valueOf(soma) + " KZ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
        try {

//              
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=06 and Estado='PAGO'";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldJunho.setText(String.valueOf(soma) + " KZ");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
         

         try {

//              
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=07 and Estado='PAGO'";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldJulho.setText(String.valueOf(soma) + " KZ");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
 try {

//              
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=08 and Estado='PAGO'";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldAgosto.setText(String.valueOf(soma) + " KZ");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
 try {

//              
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=09 and Estado='PAGO'";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldSetembro.setText(String.valueOf(soma) + " KZ");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
 try {

//              
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=10 and Estado='PAGO'";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldOutubro.setText(String.valueOf(soma) + " KZ");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
  try {

//              
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=11 and Estado='PAGO'";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldNovembro.setText(String.valueOf(soma) + " KZ");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }
   try {

//              
            String sql = "select month(dataat) ,sum(totalapag) as pagamento from tbparcelas where month(dataat)=12 and Estado='PAGO'";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {

                float soma = rs.getFloat("pagamento");
                jTextFieldDezembro.setText(String.valueOf(soma) + " KZ");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + ex);
        }

    }//GEN-LAST:event_formInternalFrameOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        TotalAluno();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ValorInscricoes();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //  ValorTotais();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ValorPagTotais();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ValorNaoPago();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boletim;
    private javax.swing.JButton Cartoes;
    private javax.swing.JButton Certificado;
    private javax.swing.JButton Folhas;
    private javax.swing.JButton Outros;
    private javax.swing.JButton Uniformes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldAbril;
    private javax.swing.JTextField jTextFieldAgosto;
    private javax.swing.JTextField jTextFieldDezembro;
    private javax.swing.JTextField jTextFieldFevereiro;
    private javax.swing.JTextField jTextFieldJaneiro;
    private javax.swing.JTextField jTextFieldJulho;
    private javax.swing.JTextField jTextFieldJunho;
    private javax.swing.JTextField jTextFieldMaio;
    private javax.swing.JTextField jTextFieldMarço;
    private javax.swing.JTextField jTextFieldNovembro;
    private javax.swing.JTextField jTextFieldOutubro;
    private javax.swing.JTextField jTextFieldSetembro;
    // End of variables declaration//GEN-END:variables
}
