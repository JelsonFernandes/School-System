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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jelson Fernandes
 */
public class CadastroNotas extends javax.swing.JInternalFrame {

    Connection conexao = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;
    ModeloProduto mod = new ModeloProduto();
    ControleProduto control = new ControleProduto();
    ConectaBanco conexaopesquisa = new ConectaBanco();

    /**
     * Creates new form CadastroNotas
     */
    public CadastroNotas() {
        initComponents();
        getContentPane().setBackground(Color.white);
        conexao = ModuloConexao.conector();

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
            jComboBoxDisciplina.setModel(defaultComboBox);
            // jComboBox3.setModel(defaultComboBox);

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDoAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pesquisar_alunos() {
        String sql = "select idaluno as ID,nomealuno as Nome,classealuno as Classe from tbalunos where nomealuno like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAluPesquisar.getText() + "%");
            rs = pst.executeQuery();
            jTableSetar.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setar_campos() {
        int setar = jTableSetar.getSelectedRow();
        txtAluId.setText(jTableSetar.getModel().getValueAt(setar, 0).toString());
        jTextFieldNome.setText(jTableSetar.getModel().getValueAt(setar, 1).toString());
        jTextFieldClasse.setText(jTableSetar.getModel().getValueAt(setar, 2).toString());
    }

    private Void adicionar() {
        String sql = "insert into tbmedia_alunos( nome, classe, trimestre, disciplina,media, usuario)value(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, jTextFieldNome.getText());
            pst.setString(2, jTextFieldClasse.getText());
            pst.setString(3, (String) jComboBoxTrimestre.getSelectedItem());
            pst.setString(4, (String) jComboBoxDisciplina.getSelectedItem());
            pst.setString(5, jTextFieldMedia.getText());
            pst.setString(6, jTextFieldUsu.getText());

            //validação dos campos obrigatórios
            if ((jTextFieldUsu.getText().isEmpty()) || (jTextFieldNome.getText().isEmpty()) || (jTextFieldClasse.getText().isEmpty())) {
                JOptionPane.showInternalMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados adicionado com sucesso");
                    jTextFieldNome.setText(null);
                    jTextFieldClasse.setText(null);
                    jComboBoxTrimestre.setSelectedItem(null);
                    jComboBoxDisciplina.setSelectedItem(null);
                    jTextFieldMedia.setText(null);
                    jTextFieldUsu.setText(null);

                }
            }
            //a linha abaixo atualiza a tabela usuário com os dados do formulário
            //a estrutura abaixo é usada para confirmar a alteração dos dados da tabela

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    private void alterar() {
        String sql = "Update tbmedia_alunos set nome=?, classe=?, trimestre=?, disciplina=?,media=?, usuario=? where id_media=?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, jTextFieldNome.getText());
            pst.setString(2, jTextFieldClasse.getText());
            pst.setString(3, (String) jComboBoxTrimestre.getSelectedItem());
            pst.setString(4, (String) jComboBoxDisciplina.getSelectedItem());
            pst.setString(5, jTextFieldMedia.getText());
            pst.setString(6, jTextFieldUsu.getText());
            pst.setString(7, jTextFieldId.getText());
            //validação dos campos obrigatórios
            if ((jTextFieldUsu.getText().isEmpty()) || (jTextFieldNome.getText().isEmpty()) || (jTextFieldClasse.getText().isEmpty())) {
                JOptionPane.showInternalMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterado com sucesso");
                    jTextFieldNome.setText(null);
                    jTextFieldClasse.setText(null);
                    jComboBoxTrimestre.setSelectedItem(null);
                    jComboBoxDisciplina.setSelectedItem(null);
                    jTextFieldMedia.setText(null);
                    jTextFieldUsu.setText(null);
                    jTextFieldId.setText(null);

                }
            }
            //a linha abaixo atualiza a tabela usuário com os dados do formulário
            //a estrutura abaixo é usada para confirmar a alteração dos dados da tabela

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void remover() {
        // a estrutura abaixo confirma a remoção do usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover estes dados?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbmedia_alunos where id_media=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jTextFieldId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, " Dados removido com sucesso");
                    jTextFieldNome.setText(null);
                    jTextFieldClasse.setText(null);
                    jComboBoxTrimestre.setSelectedItem(null);
                    jComboBoxDisciplina.setSelectedItem(null);
                    jTextFieldMedia.setText(null);
                    jTextFieldUsu.setText(null);
                    jTextFieldId.setText("");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }
        }
    }

    public void preencherTabela() {
        conexao = ModuloConexao.conector();
        // conexao = ModuloConexao.conector();
        //outro
        ArrayList dados = new ArrayList();
        //jfAjustaData parcelas=new AjustaData();

        String[] Colunas = new String[]{"ID", "Nome", "Classe", "trimestre", "Disciplina", "Média", "Usuário"};
        String sql = ("select*from tbmedia_alunos where nome like'%" + jTextFieldNome.getText() + "%'");

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            do {
                dados.add(new Object[]{rs.getString("id_media"), rs.getString("nome"), rs.getString("classe"), rs.getString("trimestre"), rs.getString("disciplina"), rs.getString("media"), rs.getString("usuario")});
            } while (rs.next());

            // String foundType = rs.getString(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!|nERRO:" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTablePesquisar.setModel((TableModel) modelo);
        jTablePesquisar.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTablePesquisar.getColumnModel().getColumn(0).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTablePesquisar.getColumnModel().getColumn(1).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTablePesquisar.getColumnModel().getColumn(2).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTablePesquisar.getColumnModel().getColumn(3).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTablePesquisar.getColumnModel().getColumn(4).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTablePesquisar.getColumnModel().getColumn(5).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(6).setPreferredWidth(150);
        jTablePesquisar.getColumnModel().getColumn(6).setResizable(false);

        //Reorganizando o cabeçalho 
        jTablePesquisar.getTableHeader().setReorderingAllowed(false);
        jTablePesquisar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTablePesquisar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldClasse = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxTrimestre = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxDisciplina = new javax.swing.JComboBox<>();
        jButtonPESQ = new javax.swing.JButton();
        jButtonAD = new javax.swing.JButton();
        jButtonALT = new javax.swing.JButton();
        jButtonREMOV = new javax.swing.JButton();
        jButtonUsu = new javax.swing.JButton();
        jTextFieldUsu = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldMedia = new javax.swing.JTextField();
        txtAluPesquisar = new javax.swing.JTextField();
        txtAluId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesquisar = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSetar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
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

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CADASTRO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 51, 204))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("ID");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldId.setEnabled(false);

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Nome");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Classe");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldClasse.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldClasse.setEnabled(false);

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Trimestre");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBoxTrimestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Iº TRIMESTRE", "IIºTRIMESTRE", "IIIºTRIMESTRE" }));
        jComboBoxTrimestre.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Disciplina");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBoxDisciplina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxDisciplina.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonPESQ.setText("PESQUISAR");
        jButtonPESQ.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonPESQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPESQActionPerformed(evt);
            }
        });

        jButtonAD.setText("ADICIONAR");
        jButtonAD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonADActionPerformed(evt);
            }
        });

        jButtonALT.setText("ALTERAR");
        jButtonALT.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonALT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonALTActionPerformed(evt);
            }
        });

        jButtonREMOV.setText("REMOVER");
        jButtonREMOV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonREMOV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonREMOVActionPerformed(evt);
            }
        });

        jButtonUsu.setText("Usuário");
        jButtonUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuActionPerformed(evt);
            }
        });

        jTextFieldUsu.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldUsu.setEnabled(false);
        jTextFieldUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar ");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Média");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAluPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAluPesquisarKeyReleased(evt);
            }
        });

        txtAluId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAluId.setEnabled(false);

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("IDAL");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(jButtonALT, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addGap(406, 406, 406))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonAD, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addGap(286, 286, 286)
                                .addComponent(jButtonPESQ, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addGap(104, 104, 104)
                                .addComponent(jButtonREMOV, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldId, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAluId, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jComboBoxDisciplina, 0, 215, Short.MAX_VALUE)
                                        .addGap(38, 38, 38)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldClasse, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBoxTrimestre, 0, 95, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldMedia, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldUsu, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(txtAluPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                                .addGap(217, 217, 217)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAluPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAluId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxTrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUsu)
                    .addComponent(jTextFieldUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAD)
                    .addComponent(jButtonALT)
                    .addComponent(jButtonREMOV)
                    .addComponent(jButtonPESQ)))
        );

        jTablePesquisar.setBackground(new java.awt.Color(0, 0, 153));
        jTablePesquisar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTablePesquisar.setForeground(new java.awt.Color(255, 255, 0));
        jTablePesquisar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTablePesquisar);

        jTableSetar.setForeground(new java.awt.Color(0, 255, 204));
        jTableSetar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableSetar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSetarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSetar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("                    CADASTRO DE NOTAS");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuActionPerformed
        // TODO add your handling code here:

        conexao = ModuloConexao.conector();
        // a linha abaixo cria uma caixa de entrada do tipo JOption Pane
        String num_usuario = JOptionPane.showInputDialog("Número de usuário");
        String sql = "select*from tbusuarios where senha=" + num_usuario;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.first()) {

                jTextFieldUsu.setText(rs.getString(2));
                // txtDataContrato.setText(rs.getString(2));

                //evitando problemas
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não contratado");
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Nº de Usuário inválido");
            // System.out.println(e);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }
    }//GEN-LAST:event_jButtonUsuActionPerformed

    private void jButtonADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonADActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_jButtonADActionPerformed

    private void jButtonALTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonALTActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_jButtonALTActionPerformed

    private void jButtonPESQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPESQActionPerformed
        // TODO add your handling code here:
        conexao = ModuloConexao.conector();
        // a linha abaixo cria uma caixa de entrada do tipo JOption Pane
        String media = JOptionPane.showInputDialog("Id Média");
        String sql = "select*from tbmedia_alunos where id_media=" + media;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.first()) {

                jTextFieldId.setText(rs.getString(1));
                jTextFieldNome.setText(rs.getString(2));
                jTextFieldClasse.setText(rs.getString(3));
                jComboBoxTrimestre.setSelectedItem(rs.getString(4));
                jComboBoxDisciplina.setSelectedItem(rs.getString(5));
                jTextFieldMedia.setText(rs.getString(6));
                jTextFieldUsu.setText(rs.getString(7));
                // txtDataContrato.setText(rs.getString(2));

                //evitando problemas
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não contratado");
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Nº de Usuário inválido");
            // System.out.println(e);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }

    }//GEN-LAST:event_jButtonPESQActionPerformed

    private void jButtonREMOVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonREMOVActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_jButtonREMOVActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        preencherTabela();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuActionPerformed

    private void txtAluPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAluPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar_alunos();
    }//GEN-LAST:event_txtAluPesquisarKeyReleased

    private void jTableSetarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSetarMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_jTableSetarMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
         BuscrItem();
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAD;
    private javax.swing.JButton jButtonALT;
    private javax.swing.JButton jButtonPESQ;
    private javax.swing.JButton jButtonREMOV;
    private javax.swing.JButton jButtonUsu;
    private javax.swing.JComboBox<String> jComboBoxDisciplina;
    private javax.swing.JComboBox<String> jComboBoxTrimestre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePesquisar;
    private javax.swing.JTable jTableSetar;
    private javax.swing.JTextField jTextFieldClasse;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldMedia;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldUsu;
    private javax.swing.JTextField txtAluId;
    private javax.swing.JTextField txtAluPesquisar;
    // End of variables declaration//GEN-END:variables
}
