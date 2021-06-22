/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
import static br.com.mtsoftware.telas.TelaPrincipal.Desktop;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jelson Fernandes
 */
public class Cadastro extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int codCad;

    /**
     * Creates new form Cadastro
     */
    public Cadastro() {
        initComponents();
        getContentPane().setBackground(Color.white);
        conexao = ModuloConexao.conector();

    }

    private Void adicionar() {
        conexao = ModuloConexao.conector();
        String sql = "insert into tbalunos(nomealuno,classealuno,endealuno,fonealuno,emailaluno,encarregaluno,nascaluno,localnascaluo,Valorinscr,turno,salan,turman)value(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            FileInputStream archivofoto;
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAluNome.getText());
            pst.setString(2, cboClasse.getSelectedItem().toString());
            pst.setString(3, txtAluEndereco.getText());
            pst.setString(4, txtAluFone.getText());
            pst.setString(5, txtEmail.getText());
            pst.setString(6, txtEncarrega.getText());
            pst.setString(7, txtDn.getText());
            pst.setString(8, txtLn.getText());
            pst.setString(9, txtValor.getText());
            pst.setString(10, jComboBoxTurno.getSelectedItem().toString());
            pst.setString(11, jComboBoxSalaN.getSelectedItem().toString());
            pst.setString(12, jComboBoxTurmaN.getSelectedItem().toString());

            if ((txtAluNome.getText().isEmpty()) || (txtAluFone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Aluno Adicionados com sucesso");
                    txtAluNome.setText(null);
                    txtAluEndereco.setText(null);
                    txtAluFone.setText(null);
                    txtEmail.setText(null);
                    txtEncarrega.setText(null);
                    txtDn.setText(null);
                    txtValor.setText(null);
                    txtLn.setText(null);
                    cboClasse.setSelectedItem(null);
                    jComboBoxTurno.setSelectedItem(null);
                    jComboBoxSalaN.setSelectedItem(null);

                    jComboBoxTurmaN.setSelectedItem(null);

                }
            }
            String sql1 = "select*from tbalunos";
            pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
            rs.last();
            codCad = rs.getInt("idaluno");
            int sair = JOptionPane.showConfirmDialog(null, "Dezeja Imprimir um Recibo para Este Aluno? ", "Atenção", JOptionPane.YES_NO_OPTION);
            if (sair == JOptionPane.YES_OPTION) {
                ImprimirCadastro imprimir = new ImprimirCadastro(codCad);
                imprimir.setVisible(true);
                Desktop.add(imprimir);
                try {
                    imprimir.setMaximum(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dispose();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    private void BuscrItem() {
        try {
            List<String> list = new ArrayList<String>();
            String sql = "select classe from tbdiscipclasses ";
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString("classe"));

            }
            pst.close();
            DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(list.toArray());
            cboClasse.setModel(defaultComboBox);
            // jComboBox3.setModel(defaultComboBox);

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDoAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pesquisar_alunos() {
       
        String sql = "select idaluno as ID,nomealuno as Nome,classealuno as Classe,endealuno as Endereço,fonealuno as Fone,emailaluno as Email,encarregaluno as Encarregado,nascaluno as Nascimento,localnascaluo as Local,Valorinscr as Valor from tbalunos where nomealuno like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteudo da caixa de pesquisa para o interroga
            // atenção a porcentagem que é a continuação da string sql
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar
            tblAlunos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
//método para setar campos

    public void setar_campos() {

        int setar = tblAlunos.getSelectedRow();
        txtAlunoId.setText(tblAlunos.getModel().getValueAt(setar, 0).toString());
        txtAluNome.setText(tblAlunos.getModel().getValueAt(setar, 1).toString());
        cboClasse.setSelectedItem(tblAlunos.getModel().getValueAt(setar, 2).toString());
        txtAluEndereco.setText(tblAlunos.getModel().getValueAt(setar, 3).toString());
        txtAluFone.setText(tblAlunos.getModel().getValueAt(setar, 4).toString());
        txtEmail.setText(tblAlunos.getModel().getValueAt(setar, 5).toString());
        txtEncarrega.setText(tblAlunos.getModel().getValueAt(setar, 6).toString());
        txtDn.setText(tblAlunos.getModel().getValueAt(setar, 7).toString());
        txtLn.setText(tblAlunos.getModel().getValueAt(setar, 8).toString());
        txtValor.setText(tblAlunos.getModel().getValueAt(setar, 9).toString());
        jComboBoxTurno.setSelectedItem(tblAlunos.getModel().getValueAt(setar, 10).toString());
        jComboBoxSalaN.setSelectedItem(tblAlunos.getModel().getValueAt(setar, 11).toString());
        jComboBoxTurmaN.setSelectedItem(tblAlunos.getModel().getValueAt(setar, 12).toString());
        btnAdicionar.setEnabled(false);

    }

    private void alterar() {
        String sql = "Update tbalunos set nomealuno=?,classealuno=?,endealuno=?,fonealuno=?,emailaluno=?,encarregaluno=?,nascaluno=?,localnascaluo=?,Valorinscr=?, turno=?,salan=?,turman=? where idaluno=?";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAluNome.getText());
            pst.setString(2, cboClasse.getSelectedItem().toString());
            pst.setString(3, txtAluEndereco.getText());
            pst.setString(4, txtAluFone.getText());
            pst.setString(5, txtEmail.getText());
            pst.setString(6, txtEncarrega.getText());
            pst.setString(7, txtDn.getText());
            pst.setString(8, txtLn.getText());
            pst.setString(9, txtValor.getText());
            pst.setString(10, jComboBoxTurno.getSelectedItem().toString());
            pst.setString(11, jComboBoxSalaN.getSelectedItem().toString());
            pst.setString(12, jComboBoxTurmaN.getSelectedItem().toString());
            pst.setString(13, txtAlunoId.getText());

            if ((txtAluNome.getText().isEmpty()) || (txtAluFone.getText().isEmpty())) {
                JOptionPane.showInternalMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Aluno alterados com sucesso");
                    txtAluNome.setText(null);
                    txtAluEndereco.setText(null);
                    txtAluFone.setText(null);
                    txtEmail.setText(null);
                    txtEncarrega.setText(null);
                    txtDn.setText(null);
                    txtLn.setText(null);
                    txtValor.setText(null);
                    btnAdicionar.setEnabled(true);
                    cboClasse.setSelectedItem(null);
                    jComboBoxTurno.setSelectedItem(null);
                    jComboBoxSalaN.setSelectedItem(null);

                    jComboBoxTurmaN.setSelectedItem(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void remover() {
        // a estrutura abaixo confirma a remoção do aluno
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este Aluno?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbalunos where idaluno=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtAlunoId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, " Aluno removido com sucesso");
                    txtAluNome.setText(null);
                    txtAluEndereco.setText(null);
                    txtAluFone.setText(null);
                    txtEmail.setText(null);
                    txtEncarrega.setText(null);
                    txtDn.setText(null);
                    txtLn.setText(null);
                    txtValor.setText(null);
                    btnAdicionar.setEnabled(true);
                    cboClasse.setSelectedItem(null);
                    jComboBoxTurno.setSelectedItem(null);
                    jComboBoxSalaN.setSelectedItem(null);

                    jComboBoxTurmaN.setSelectedItem(null);

                }
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
        tblAlunos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxTurno = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxTurmaN = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxSalaN = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtDn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEncarrega = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtLn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtAlunoId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtAluNome = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cboClasse = new javax.swing.JComboBox<>();
        txtValor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtAluFone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAluEndereco = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButtonRelTurno = new javax.swing.JButton();
        jButtonRelClasse = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(807, 580));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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

        tblAlunos.setBackground(new java.awt.Color(0, 51, 204));
        tblAlunos.setForeground(new java.awt.Color(255, 255, 255));
        tblAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlunosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAlunos);

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Pesquisar (2).png"))); // NOI18N
        jLabel5.setText("Consulta");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPesquisar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("  TELA DE CADASTRO DE ALUNOS");
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAdicionar.setBackground(new java.awt.Color(102, 102, 102));
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/cadastro.png"))); // NOI18N
        btnAdicionar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAdicionar.setMaximumSize(new java.awt.Dimension(60, 20));
        btnAdicionar.setMinimumSize(new java.awt.Dimension(60, 20));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(83, 67));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setBackground(new java.awt.Color(153, 153, 153));
        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/remover.png"))); // NOI18N
        btnRemover.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnRemover.setMaximumSize(new java.awt.Dimension(60, 20));
        btnRemover.setMinimumSize(new java.awt.Dimension(60, 20));
        btnRemover.setPreferredSize(new java.awt.Dimension(83, 67));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAlterar.setBackground(new java.awt.Color(153, 153, 153));
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Alterar.png"))); // NOI18N
        btnAlterar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAlterar.setMaximumSize(new java.awt.Dimension(60, 20));
        btnAlterar.setMinimumSize(new java.awt.Dimension(60, 20));
        btnAlterar.setPreferredSize(new java.awt.Dimension(83, 67));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("TURNO");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBoxTurno.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manhã", "Tarde", "Noite" }));
        jComboBoxTurno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("SALA Nº");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBoxTurmaN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z 1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "43", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "61", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "76", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));
        jComboBoxTurmaN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("TURMA Nº");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBoxSalaN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "43", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "61", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "76", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));
        jComboBoxSalaN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nascimento");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtDn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Encarregado");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtEncarrega.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Local de Nascimento");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtLn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtLn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLnActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAlunoId.setEnabled(false);
        txtAlunoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlunoIdActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nome");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAluNome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Setar Classes");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("  Classe");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cboClasse.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cboClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboClasseActionPerformed(evt);
            }
        });

        txtValor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Valor da Inscrição");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAluFone.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Telefone");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAluEndereco.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtAluEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAluEnderecoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Endereço");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("BUSCAR TELA DE CARTÕES");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonRelTurno.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRelTurno.setText("Relatório Por Turno");
        jButtonRelTurno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonRelTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRelTurnoActionPerformed(evt);
            }
        });

        jButtonRelClasse.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRelClasse.setText("Relatório Por Classe");
        jButtonRelClasse.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonRelClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRelClasseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtAluNome)
                                        .addGap(1, 1, 1))
                                    .addComponent(txtDn)
                                    .addComponent(txtEmail)
                                    .addComponent(txtAluEndereco))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEncarrega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                    .addComponent(txtValor, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtAluFone, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtLn)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBoxTurno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(20, 20, 20)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButtonRelTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(11, 11, 11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jComboBoxTurmaN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(14, 14, 14))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxSalaN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(txtAlunoId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboClasse, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRelClasse)
                        .addGap(29, 29, 29))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAlunoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRelClasse))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                            .addComponent(txtAluNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(txtAluFone))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAluEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(txtValor)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEncarrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtLn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtDn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jComboBoxTurmaN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBoxSalaN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRelTurno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAluEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAluEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAluEnderecoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        //chamando o método adicionar
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // chamando o método alterar
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // chamando o método remover
        remover();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        BuscrItem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void txtDnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDnActionPerformed
        // método para inserir data  em português
    }//GEN-LAST:event_txtDnActionPerformed

    private void txtLnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLnActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        //chamar o método pesquisar alunos
        pesquisar_alunos();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tblAlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlunosMouseClicked
        //Método para setar campos
        setar_campos();
    }//GEN-LAST:event_tblAlunosMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        try {
            List<String> list = new ArrayList<String>();
            String sql = "select classe from tbdiscipclasses ";
            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString("classe"));

            }
            pst.close();
            DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(list.toArray());
            cboClasse.setModel(defaultComboBox);
            // jComboBox3.setModel(defaultComboBox);

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDoAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void cboClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboClasseActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        try {
            CadastroCartoes cadastroAlunos = new CadastroCartoes();
            cadastroAlunos.setVisible(true);
            Desktop.add(cadastroAlunos);
            cadastroAlunos.setMaximum(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonRelClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRelClasseActionPerformed
        int comfirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste Relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (comfirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("classealuno", (cboClasse.getSelectedItem()));
//                 filtro.put("id_nota", Integer.parseInt(idnota.getText()));

                JasperPrint print = JasperFillManager.fillReport("lib/report/Relatori_Classe.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButtonRelClasseActionPerformed

    private void jButtonRelTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRelTurnoActionPerformed
        // TODO add your handling code here:
        int comfirma = JOptionPane.showConfirmDialog(null, "Confirma a Impressão deste Boletim?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (comfirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("turno", (jComboBoxTurno.getSelectedItem()));
//                 filtro.put("id_nota", Integer.parseInt(idnota.getText()));

                JasperPrint print = JasperFillManager.fillReport("lib/report/Relatorio_Turno.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButtonRelTurnoActionPerformed

    private void txtAlunoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlunoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlunoIdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cboClasse;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonRelClasse;
    private javax.swing.JButton jButtonRelTurno;
    private javax.swing.JComboBox<String> jComboBoxSalaN;
    private javax.swing.JComboBox<String> jComboBoxTurmaN;
    private javax.swing.JComboBox<String> jComboBoxTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTable tblAlunos;
    private javax.swing.JTextField txtAluEndereco;
    private javax.swing.JTextField txtAluFone;
    private javax.swing.JTextField txtAluNome;
    private javax.swing.JTextField txtAlunoId;
    private javax.swing.JTextField txtDn;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEncarrega;
    private javax.swing.JTextField txtLn;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
