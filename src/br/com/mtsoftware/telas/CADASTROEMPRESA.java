/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jelson Fernandes
 */
public class CADASTROEMPRESA extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form CADASTROEMPRESA
     */
    public CADASTROEMPRESA() {
        initComponents();
        conexao = ModuloConexao.conector();
        getContentPane().setBackground(Color.white);
    }

    private Void adicionar() {
        String sql = "insert into tbempresa(nome,nif,representante,endereco ,telefone, email, numfunciocionarios ,log ,logimagem)value(?,?,?,?,?,?,?,?,?)";
        try {
            FileInputStream archivofoto;
            pst = conexao.prepareStatement(sql);
            pst.setString(1, jTextFieldNome.getText());
            pst.setString(2, jTextFieldNif.getText());
            pst.setString(3, jTextFieldPesp.getText());
            pst.setString(4, jTextFieldEndereco.getText());
            pst.setString(5, jTextPaneFone.getText());
            pst.setString(6, jTextFieldEmail.getText());
            pst.setString(7, jTextFieldFuncionarios.getText());
            pst.setString(8, jTextFieldIMG.getText());
            archivofoto = new FileInputStream(jTextFieldIMG.getText());
            pst.setBinaryStream(9, archivofoto);

            if ((jTextFieldNome.getText().isEmpty()) || (jTextFieldNif.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados da Empresa Adicionados com sucesso");
                    jTextFieldNome.setText(null);
                    jTextFieldNif.setText(null);
                    jTextFieldPesp.setText(null);
                    jTextFieldEndereco.setText(null);
                    jTextPaneFone.setText(null);
                    jTextFieldEmail.setText(null);
                    jTextFieldFuncionarios.setText(null);
                   jTextFieldIMG.setText(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    private void pesquisar_empresa() {
        // a linha abaixo cria uma caixa de entrada do tipo JOption Pane
        String nome = JOptionPane.showInputDialog("Nif da Empresa");
        String sql = "select*from tbempresa where nif=" + nome;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                FileInputStream archivofoto = null;
                Object file = null;
                // File file = archivo.getSelectedFile();

                jTextFieldNome.setText(rs.getString(1));
                jTextFieldPesp.setText(rs.getString(2));
                jTextFieldNif.setText(rs.getString(3));

                jTextFieldEndereco.setText(rs.getString(4));
                jTextFieldFuncionarios.setText(rs.getString(5));
                jTextPaneFone.setText(rs.getString(6));
                jTextFieldEmail.setText(rs.getString(7));
                jTextFieldIMG.setText(rs.getString(8));

                //txtLn.setText(rs.getString(7));
                //evitando problemas
                jButtonSalvar.setEnabled(false);
                // txtAluPesquisar.setEnabled(false);
                //  tblContrato.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Empresa não Cadastrada");
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Nome de Empresa Inválido");
            // System.out.println(e);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }

    }
     private void pesquisar_alunos() {
        FileInputStream archivofoto;
        String sql = "select nome as Nome,nif as NIF,representante as Responsavel,endereco as Endereço ,telefone as Fone, email as E_Mail, numfunciocionarios Funcionários,log as Logo ,logimagem as Foto from tbempresa where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteudo da caixa de pesquisa para o interroga
            // atenção a porcentagem que é a continuação da string sql
            pst.setString(1, jTextFieldPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar
           jTableEmpresa.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
     public void setar_campos() {
        try {
            FileInputStream archivofoto = null;
            int setar = jTableEmpresa.getSelectedRow();
            jTextFieldNome.setText(jTableEmpresa.getModel().getValueAt(setar, 0).toString());
            jTextFieldNif.setText(jTableEmpresa.getModel().getValueAt(setar, 1).toString());
            jTextFieldPesp.setText(jTableEmpresa.getModel().getValueAt(setar, 2).toString());
            jTextFieldEndereco.setText(jTableEmpresa.getModel().getValueAt(setar, 3).toString());
            jTextPaneFone.setText(jTableEmpresa.getModel().getValueAt(setar, 4).toString());
            jTextFieldEmail.setText(jTableEmpresa.getModel().getValueAt(setar, 5).toString());
            jTextFieldFuncionarios.setText(jTableEmpresa.getModel().getValueAt(setar, 6).toString());
            jTextFieldIMG.setText(jTableEmpresa.getModel().getValueAt(setar, 7).toString());
            archivofoto = new FileInputStream(jTextFieldIMG.getText());
            jLabelFoto.setLabelFor(this);
            Image foto = getToolkit().createImage(jTextFieldIMG.getText());
            foto = foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            jLabelFoto.setIcon(new ImageIcon(foto));
            jLabelFoto.setIcon((Icon) jTableEmpresa.getModel().getValueAt(8, 100));
            
//            insert into tbempresa(nome,nif,representante,endereco ,telefone, email, numfunciocionarios ,log ,logimagem

            pst.setBinaryStream(11, archivofoto);
            jButtonSalvar.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void alterar() {
        String sql = "Update tbempresa  set nome=?,nif=?,representante=?,endereco=? ,telefone=?, email=?, numfunciocionarios=?,log=?,logimagem=?  where nome=?";

        try {
            FileInputStream archivofoto;
            pst = conexao.prepareStatement(sql);
            pst.setString(1, jTextFieldNome.getText());
            pst.setString(2, jTextFieldNif.getText());
            pst.setString(3, jTextFieldPesp.getText());
            pst.setString(4, jTextFieldEndereco.getText());
            pst.setString(5, jTextPaneFone.getText());
            pst.setString(6, jTextFieldEmail.getText());
            pst.setString(7, jTextFieldFuncionarios.getText());
            pst.setString(8, jTextFieldIMG.getText());
            archivofoto = new FileInputStream(jTextFieldIMG.getText());
            pst.setBinaryStream(9, archivofoto);
            pst.setString(10, jTextFieldNome.getText());

            if ((jTextFieldNome.getText().isEmpty()) || (jTextFieldNif.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados da Empresa Alterados com sucesso");
                    jTextFieldNome.setText(null);
                    jTextFieldNif.setText(null);
                    jTextFieldPesp.setText(null);
                    jTextFieldEndereco.setText(null);
                    jTextPaneFone.setText(null);
                    jTextFieldEmail.setText(null);
                    jTextFieldFuncionarios.setText(null);
                   jTextFieldIMG.setText(null);

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
            String sql = "delete from tbempresa where nome=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jTextFieldNome.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, " Dados Da Empresa removido com sucesso");
                    jTextFieldNome.setText(null);
                    jTextFieldNif.setText(null);
                    jTextFieldPesp.setText(null);
                    jTextFieldEndereco.setText(null);
                    jTextPaneFone.setText(null);
                    jTextFieldEmail.setText(null);
                    jTextFieldFuncionarios.setText(null);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }
        }
    }

    // TODO add your handling code here:
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelFoto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNif = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPesp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEndereco = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldFuncionarios = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jToggleButtonRemover = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldIMG = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEmpresa = new javax.swing.JTable();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jButtonPesquisar1 = new javax.swing.JButton();
        jTextPaneFone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("             Cadastro Da Empresa");
        setPreferredSize(new java.awt.Dimension(807, 555));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(255, 255, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("  NOME DA EMPRESA");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldNome.setBackground(new java.awt.Color(240, 240, 240));

        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("  NIF");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldNif.setBackground(new java.awt.Color(240, 240, 240));

        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("  RESPONSAVEL");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldPesp.setBackground(new java.awt.Color(240, 240, 240));

        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("  E-MAIL");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldEmail.setBackground(new java.awt.Color(240, 240, 240));

        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("  ENDEREÇO ");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldEndereco.setBackground(new java.awt.Color(240, 240, 240));

        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText(" TELEFONE");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setForeground(new java.awt.Color(255, 255, 0));
        jLabel10.setText("  Nº FUNCIONÁRIOS");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldFuncionarios.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFuncionariosActionPerformed(evt);
            }
        });

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/cadastro.png"))); // NOI18N
        jButtonSalvar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Pesquisar (2).png"))); // NOI18N
        jButtonPesquisar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Alterar.png"))); // NOI18N
        jButtonAlterar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jToggleButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/remover.png"))); // NOI18N
        jToggleButtonRemover.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToggleButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonRemoverActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("IMAGEM");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldIMG.setBackground(new java.awt.Color(240, 240, 240));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableEmpresa.setBackground(new java.awt.Color(0, 0, 51));
        jTableEmpresa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTableEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        jTableEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmpresaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableEmpresa);

        jTextFieldPesquisar.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarKeyReleased(evt);
            }
        });

        jButtonPesquisar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Pesquisar (2).png"))); // NOI18N
        jButtonPesquisar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonPesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisar1ActionPerformed(evt);
            }
        });

        jTextPaneFone.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldPesp, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                            .addComponent(jTextFieldNif, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldFuncionarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)))
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextPaneFone, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(138, 138, 138)
                        .addComponent(jButtonPesquisar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(133, 133, 133)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(jToggleButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldIMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(0, 42, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextPaneFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldPesp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSalvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jToggleButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CADASTRO DE EMPRESA");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        // TODO add your handling code here:
        pesquisar_empresa();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jToggleButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRemoverActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_jToggleButtonRemoverActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG)", "jpg", "jpeg");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Arquivo");
        File rute = new File("D:/");
        archivo.setCurrentDirectory(rute);
        int ventana = archivo.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = archivo.getSelectedFile();
            jTextFieldIMG.setText(String.valueOf(file));
            Image foto = getToolkit().createImage(jTextFieldIMG.getText());
            foto = foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            jLabelFoto.setIcon(new ImageIcon(foto));

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmpresaMouseClicked
        // TODO add your handling code here:
         setar_campos();
    }//GEN-LAST:event_jTableEmpresaMouseClicked

    private void jButtonPesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPesquisar1ActionPerformed

    private void jTextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar_alunos();
    }//GEN-LAST:event_jTextFieldPesquisarKeyReleased

    private void jTextFieldFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFuncionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFuncionariosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonPesquisar1;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFoto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEmpresa;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldFuncionarios;
    private javax.swing.JTextField jTextFieldIMG;
    private javax.swing.JTextField jTextFieldNif;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPesp;
    private javax.swing.JTextField jTextFieldPesquisar;
    private javax.swing.JTextField jTextPaneFone;
    private javax.swing.JToggleButton jToggleButtonRemover;
    // End of variables declaration//GEN-END:variables
}
