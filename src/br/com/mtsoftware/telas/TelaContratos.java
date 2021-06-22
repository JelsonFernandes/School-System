/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import java.sql.*;
import br.com.mtsoftware.dal.ModuloConexao;
import static br.com.mtsoftware.telas.TelaPrincipal.Desktop;
import java.awt.Color;
import java.awt.HeadlessException;
import java.beans.PropertyVetoException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
// Alinha abaixo importa recursos da biblioteca rsxml.jar
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jelson Fernandes
 */
public class TelaContratos extends javax.swing.JInternalFrame {

    Connection conexao = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;
    int CodCntrato;

    /**
     * Creates new form TelaContratos
     *
     * @throws java.text.ParseException
     */
    public TelaContratos() throws ParseException {
        initComponents();
        getContentPane().setBackground(Color.white);
        conexao = ModuloConexao.conector();
//        java.util.Date data = new java.util.Date();
//        Locale local = new Locale("pt", "BR");
//        DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
//        txtVecimento.setText(formato.format(data));
       
//        System.out.println(formato.format(data));
       
        txtVecimento.setText(new SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date(System.currentTimeMillis())));
        DecimalFormat df = new DecimalFormat("0.##");
        NumberFormat nf = NumberFormat.getInstance();
    }

    private void pesquisar_alunos() {
        String sql = "select idaluno as ID,nomealuno as Nome,classealuno as Classe from tbalunos where nomealuno like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAluPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblContrato.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setar_campos() {
        int setar = tblContrato.getSelectedRow();
        txtAluId.setText(tblContrato.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblContrato.getModel().getValueAt(setar, 1).toString());
        txtClasse.setText(tblContrato.getModel().getValueAt(setar, 2).toString());
    }
//método para adicionar clientes

    private Void adicionar() {

        String sql = "insert into tbacontratos(nome,classealuno,objectocontrato,nparcelas,valor,vencipriparce,obs,idaluno)values(?,?,?,?,?,?,?,?)";
        try {
            /*DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
 Date dateUser;  
 Calendar c = Calendar.getInstance();  
 dateUser = (Date) df.parse(txtDataContrato.getText());
 c.setTime(dateUser);
 c.add(Calendar.DAY_OF_YEAR, 30);
 txtVecimento.setText(df.format(c.getTime()));*/

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtClasse.getText());
            pst.setString(3, cboOjectoContrato.getSelectedItem().toString());
            pst.setString(4, txtparcelas.getText());
            pst.setString(5, txtValor.getText().replace(",", "."));
            pst.setString(6, txtVecimento.getText());
            pst.setString(7, txtaObs.getText());
            pst.setString(8, txtAluId.getText());
             pst.execute();
           
            //validação dos campos obrigatórios
            /* Data d= new Data();
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada=formatar.format(d);
    
        
        txtVecimento.setText(dataFormatada);*/
            if ((txtAluId.getText().isEmpty()) || (txtparcelas.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Matrícula  Efectuada com sucesso");

                    txtAluId.setText(null);
                    txtNome.setText(null);
                    txtClasse.setText(null);
                    // txtDataContrato.setText(null);
                    txtparcelas.setText(null);
                    txtVecimento.setText(null);
                    txtaObs.setText(null);
                    txtValor.setText(null);
                    //txtTotal.setText(null);
//                    ParcelasCotrato parcelas = new ParcelasCotrato();
//                    parcelas.setVisible(true);

                }

            }
//             String sql1=("select*from tbacontratos");
//            pst = conexao.prepareStatement(sql1);
//           
//            rs = pst.executeQuery();
//            rs.first();
//            CodCntrato = rs.getInt("id_contrato");
 String sql1 = "select*from tbacontratos";
            pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
            rs.last();
             CodCntrato = rs.getInt("id_contrato");
            ParcelasCotrato parcelas = new ParcelasCotrato(CodCntrato);
            parcelas.setVisible(true);
            Desktop.add(parcelas);
             parcelas.setMaximum(true);
            this.dispose();

//            ParcelasCotrato parcelas = new ParcelasCotrato();
//            parcelas.setVisible(true);
//            Desktop.add(parcelas);
//            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;

    }

    // método para pesquisar os
    private void pesquisar_contrato() {
        conexao = ModuloConexao.conector();
        // a linha abaixo cria uma caixa de entrada do tipo JOption Pane
        String num_contrato = JOptionPane.showInputDialog("Número de contrato");
        String sql = "select*from tbacontratos where id_contrato=" + num_contrato;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {

                txtContrato.setText(rs.getString(1));
                // txtDataContrato.setText(rs.getString(2));
                txtNome.setText(rs.getString(3));
                txtClasse.setText(rs.getString(4));
                cboOjectoContrato.setSelectedItem(rs.getString(5));
                txtparcelas.setText(rs.getString(6));
                txtValor.setText(rs.getString(7));
                txtVecimento.setText(rs.getString(8));
                txtaObs.setText(rs.getString(9));
                txtAluId.setText(rs.getString(10));

                //evitando problemas
                btnAdicionar.setEnabled(false);
                txtAluPesquisar.setEnabled(false);
                tblContrato.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não contratado");
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Nº de Contrato Inválido");
            // System.out.println(e);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }

    }

    //método para alterar os
    private void alterar_contrato() {
        String sql = "update  tbacontratos set nome=?,classealuno=?,objectocontrato=?,nparcelas=?,valor=?,vencipriparce=?,obs=? where idaluno=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtClasse.getText());
            pst.setString(3, cboOjectoContrato.getSelectedItem().toString());
            pst.setString(4, txtparcelas.getText());
            pst.setString(5, txtValor.getText().replace(",", "."));
            pst.setString(6, txtVecimento.getText());
            pst.setString(7, txtaObs.getText());
            pst.setString(8, txtAluId.getText());
             CodCntrato = rs.getInt("id_contrato");
            //validação dos campos obrigatórios
            if ((txtAluId.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtparcelas.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Contrato Alterado com sucesso");
                    txtContrato.setText(null);
                    txtAluId.setText(null);
                    txtNome.setText(null);
                    txtClasse.setText(null);
                    //txtDataContrato.setText(null);
                    txtparcelas.setText(null);
                    txtVecimento.setText(null);
                    txtaObs.setText(null);
                    txtValor.setText(null);
                    txtAluId.setText(null);
                    //habilitar contrato objectos
                    btnAdicionar.setEnabled(true);
                    txtAluPesquisar.setEnabled(true);
                    tblContrato.setVisible(true);

                }

            }
//            String sql1=("select*from tbacontratos");
//            pst = conexao.prepareStatement(sql1);
//           
//            rs = pst.executeQuery();
//            rs.next();
//            CodCntrato = rs.getInt("id_contrato");
            ParcelasCotrato parcelas = new ParcelasCotrato(CodCntrato);
            parcelas.setVisible(true);
            Desktop.add(parcelas);
             parcelas.setMaximum(true);
            this.dispose();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaContratos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //método para excluir uma os
    private void excluir_contrato() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este Contrato?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbacontratos where id_contrato=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtContrato.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Contrato excluido com sucesso");
                    txtContrato.setText(null);

                    txtNome.setText(null);
                    txtClasse.setText(null);
                    // txtDataContrato.setText(null);
                    txtparcelas.setText(null);
                    txtVecimento.setText(null);
                    txtAluId.setText(null);
                    txtaObs.setText(null);
                    txtValor.setText(null);
                    //habilitar contrato objectos
                    btnAdicionar.setEnabled(true);
                    txtAluPesquisar.setEnabled(true);
                    tblContrato.setVisible(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    private void imprimir() {

        int comfirma = JOptionPane.showConfirmDialog(null, "Confirma a Impressão desta Matricula?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (comfirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("id_contrato", Integer.parseInt(txtContrato.getText()));
                

                JasperPrint print = JasperFillManager.fillReport("lib/report/Comprovativo.jasper", filtro, conexao);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboOjectoContrato = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtparcelas = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtVecimento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaObs = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtAluPesquisar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtAluId = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblContrato = new javax.swing.JTable();
        txtContrato = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnpesquisar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtClasse = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jButtonImprimir = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Contrato");
        setPreferredSize(new java.awt.Dimension(807, 680));
        setVisible(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 204));
        jLabel4.setText("Classe");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 204));
        jLabel6.setText("Objecto do Contrato");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cboOjectoContrato.setBackground(new java.awt.Color(102, 255, 255));
        cboOjectoContrato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cboOjectoContrato.setForeground(new java.awt.Color(0, 204, 204));
        cboOjectoContrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ensino Geral", "Curso Básico", "Curso de Ferias", "Outros" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 204));
        jLabel7.setText("Nº de Parcelas do Contratos");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtparcelas.setBackground(new java.awt.Color(240, 240, 240));
        txtparcelas.setForeground(new java.awt.Color(51, 0, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 204, 204));
        jLabel8.setText("Data do Contrato");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtVecimento.setBackground(new java.awt.Color(240, 240, 240));
        txtVecimento.setForeground(new java.awt.Color(255, 0, 153));
        txtVecimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVecimentoActionPerformed(evt);
            }
        });

        txtaObs.setColumns(20);
        txtaObs.setForeground(new java.awt.Color(255, 51, 0));
        txtaObs.setRows(5);
        jScrollPane1.setViewportView(txtaObs);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 255));
        jLabel9.setText(" Observação");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/cadastro.png"))); // NOI18N
        btnAdicionar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAdicionar.setPreferredSize(new java.awt.Dimension(83, 67));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Alterar.png"))); // NOI18N
        btnAlterar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAlterar.setPreferredSize(new java.awt.Dimension(83, 67));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/remover.png"))); // NOI18N
        btnRemover.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnRemover.setPreferredSize(new java.awt.Dimension(83, 67));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        txtAluPesquisar.setBackground(new java.awt.Color(204, 204, 255));
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

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Pesquisar (2).png"))); // NOI18N

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAluId.setEnabled(false);

        tblContrato.setBackground(new java.awt.Color(0, 0, 204));
        tblContrato.setForeground(new java.awt.Color(255, 255, 255));
        tblContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblContratoMouseClicked(evt);
            }
        });
        tblContrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblContratoKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tblContrato);

        txtContrato.setEditable(false);
        txtContrato.setEnabled(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nº Contrato");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAluPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtAluId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAluId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAluPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnpesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Pesquisar (2).png"))); // NOI18N
        btnpesquisar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnpesquisar.setPreferredSize(new java.awt.Dimension(83, 67));
        btnpesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpesquisarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 204));
        jLabel2.setText("Nome");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNome.setBackground(new java.awt.Color(240, 240, 240));
        txtNome.setForeground(new java.awt.Color(0, 51, 255));

        txtClasse.setBackground(new java.awt.Color(240, 240, 240));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Valor do Contrato");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtValor.setBackground(new java.awt.Color(240, 240, 240));

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/blueprinter.png"))); // NOI18N
        jButtonImprimir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonImprimir.setPreferredSize(new java.awt.Dimension(83, 67));
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(452, 452, 452)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(186, 186, 186))
                            .addComponent(txtClasse))
                        .addGap(54, 54, 54)))
                .addGap(13, 13, 13))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(567, 567, 567))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(txtparcelas))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(45, 45, 45)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(txtValor)))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(cboOjectoContrato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVecimento)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(635, 635, 635))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(txtNome)
                        .addGap(475, 475, 475))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(btnpesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(txtClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(cboOjectoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtparcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnpesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        setBounds(0, 0, 807, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void txtAluPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAluPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAluPesquisarActionPerformed

    private void txtAluPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAluPesquisarKeyReleased
        // chamando o método pesquiasr alunos
        pesquisar_alunos();
    }//GEN-LAST:event_txtAluPesquisarKeyReleased

    private void tblContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblContratoMouseClicked
        // Chamando o método setar campos
        setar_campos();
    }//GEN-LAST:event_tblContratoMouseClicked

    private void tblContratoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblContratoKeyReleased

    }//GEN-LAST:event_tblContratoKeyReleased

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // chamando o´método adicionar
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnpesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpesquisarActionPerformed
        // chamar o método pesquisar contrato
        pesquisar_contrato();
    }//GEN-LAST:event_btnpesquisarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // chamando o método alterar
        alterar_contrato();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // chamando o método excluir contrato
        excluir_contrato();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void txtVecimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVecimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVecimentoActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
        imprimir();
    }//GEN-LAST:event_jButtonImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnpesquisar;
    private javax.swing.JComboBox<String> cboOjectoContrato;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tblContrato;
    private javax.swing.JTextField txtAluId;
    private javax.swing.JTextField txtAluPesquisar;
    private javax.swing.JTextField txtClasse;
    private javax.swing.JTextField txtContrato;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtVecimento;
    private javax.swing.JTextArea txtaObs;
    private javax.swing.JTextField txtparcelas;
    // End of variables declaration//GEN-END:variables

}
