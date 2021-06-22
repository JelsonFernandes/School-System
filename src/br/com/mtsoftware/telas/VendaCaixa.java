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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jelson Fernandes
 */
public class VendaCaixa extends javax.swing.JInternalFrame {

    Connection conexao = null;
    ResultSet rs = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    ModeloVenda mod = new ModeloVenda();
    ControleVenda control = new ControleVenda();

    /**
     * Creates new form VendaCaixa
     */
    public VendaCaixa() {
        initComponents();
        getContentPane().setBackground(Color.white);
        jTextFieldDataVenda.setText(new SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date(System.currentTimeMillis())));
    }

    public void preencherTabela() {
        conexao = ModuloConexao.conector();
        // conexao = ModuloConexao.conector();
        //outro
        ArrayList dados = new ArrayList();
        //jfAjustaData parcelas=new AjustaData();

        String[] Colunas = new String[]{"PRODUTO", "PREÇO DA VENDA","QUANTIDADE"};
        String sql = ("select*from tbprodutos where nome_produto like'%" + jTextFieldProduto.getText() + "%'");

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            do {
                dados.add(new Object[]{rs.getString("nome_produto"), rs.getString("Preco_venda"),rs.getString("quantidade")});
            } while (rs.next());

            // String foundType = rs.getString(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!|nERRO:" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTablePesquisar.setModel((TableModel) modelo);
        jTablePesquisar.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTablePesquisar.getColumnModel().getColumn(0).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTablePesquisar.getColumnModel().getColumn(1).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTablePesquisar.getColumnModel().getColumn(2).setResizable(false);

        //Reorganizando o cabeçalho 
        jTablePesquisar.getTableHeader().setReorderingAllowed(false);
        jTablePesquisar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTablePesquisar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void setar_campos() {
        int setar = jTablePesquisar.getSelectedRow();
        jTextFieldProduto.setText(jTablePesquisar.getModel().getValueAt(setar, 0).toString());
        jTextFieldValorVenda.setText(jTablePesquisar.getModel().getValueAt(setar, 1).toString());

    }

    private void pesquisar_cliente() {
        conexao = ModuloConexao.conector();
        // a linha abaixo cria uma caixa de entrada do tipo JOption Pane
        String nome_cli = JOptionPane.showInputDialog("Cliente");
        String sql = "select*from tbclientes where idcli=" + nome_cli;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {

                jTextFieldCliente.setText(rs.getString(2));
//                jTextFieldValorVenda.setText(rs.getString("Preco_venda"));
//                
//                // txtDataContrato.setText(rs.getString(2));
//               

                //evitando problemas
//               JOptionPane.showMessageDialog(null, "nomecli");
//                System.out.println("nomecli");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não cadastratado");
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Nome Inválido");
            // System.out.println(e);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }

    }

    public void adicionarItem() {
        conexao = ModuloConexao.conector();
//        achaCodProduto(mod.getNomeProduto());
//        String sql = ("insert into tbitemvendas(idvenda,idproduto,quant_produto)values(?,?,?)");
        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setInt(1, mod.getId_Venda());
//            pst.setInt(2, codProduto);
//            pst.setInt(3, mod.getQuantItem());
//            pst.execute();
            //Baixa de estoque
            int quant = 0, resto = 0;
            String sql1 = ("select*from tbprodutos where nome_produto='" + jTextFieldProduto.getText() + "'");
            pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
            rs.first();
            quant = rs.getInt("quantidade");
            resto = quant - Integer.parseInt(jTextFieldValorQuantidade.getText());
            String sql2 = ("update tbprodutos set quantidade=? where nome_produto=?");
            pst = conexao.prepareStatement(sql2);
            pst.setInt(1, resto);
            pst.setString(2, jTextFieldProduto.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Prduto adicionado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar o Produto!|nERRO:" + ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldProduto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldValorVenda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDataVenda = new javax.swing.JTextField();
        jButtonAdicionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCadastro = new javax.swing.JTable();
        jTextFieldValorTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldEntregue = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldTrouco = new javax.swing.JTextField();
        jButtonFinalizarVenda = new javax.swing.JButton();
        jButtonGerarTotal = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldValorQuantidade = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePesquisar = new javax.swing.JTable();
        jButtonAbrirVenda = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setForeground(new java.awt.Color(255, 255, 0));
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Cliente");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldCliente.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldCliente.setEnabled(false);
        jTextFieldCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldClienteKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText(" Produto");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldProduto.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldProduto.setEnabled(false);
        jTextFieldProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldProdutoKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Valor Venda");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldValorVenda.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldValorVenda.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Data  Venda");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldDataVenda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldDataVenda.setForeground(new java.awt.Color(0, 0, 153));
        jTextFieldDataVenda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldDataVenda.setDisabledTextColor(new java.awt.Color(0, 0, 153));
        jTextFieldDataVenda.setEnabled(false);

        jButtonAdicionar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAdicionar.setText("Adicionar Item");
        jButtonAdicionar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAdicionar.setEnabled(false);
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jTableCadastro.setBackground(new java.awt.Color(0, 0, 153));
        jTableCadastro.setForeground(new java.awt.Color(255, 255, 255));
        jTableCadastro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLIENTE", "PRODUTO", "VALOR", "QUANTIDADE", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(jTableCadastro);

        jTextFieldValorTotal.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldValorTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextFieldValorTotal.setForeground(new java.awt.Color(0, 0, 153));
        jTextFieldValorTotal.setDisabledTextColor(new java.awt.Color(51, 0, 102));
        jTextFieldValorTotal.setEnabled(false);
        jTextFieldValorTotal.setSelectedTextColor(new java.awt.Color(0, 0, 153));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("   TOTAL A PAGAR");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("TROUCO");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldEntregue.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldEntregue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldEntregue.setForeground(new java.awt.Color(0, 0, 153));
        jTextFieldEntregue.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("VALOR ENTREGUE");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldTrouco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTrouco.setForeground(new java.awt.Color(0, 0, 153));
        jTextFieldTrouco.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldTrouco.setDisabledTextColor(new java.awt.Color(0, 0, 153));
        jTextFieldTrouco.setEnabled(false);

        jButtonFinalizarVenda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonFinalizarVenda.setText("Finalizar Venda");
        jButtonFinalizarVenda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonFinalizarVenda.setEnabled(false);
        jButtonFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarVendaActionPerformed(evt);
            }
        });

        jButtonGerarTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonGerarTotal.setText("Gerar Trouco");
        jButtonGerarTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonGerarTotal.setEnabled(false);
        jButtonGerarTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarTotalActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText("Quantidade");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldValorQuantidade.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldValorQuantidade.setEnabled(false);

        jLabel10.setForeground(new java.awt.Color(255, 255, 0));
        jLabel10.setText("TOTAL");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldTotal.setBackground(new java.awt.Color(255, 255, 0));
        jTextFieldTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTotal.setForeground(new java.awt.Color(0, 0, 153));
        jTextFieldTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldTotal.setDisabledTextColor(new java.awt.Color(0, 0, 153));
        jTextFieldTotal.setEnabled(false);

        jTablePesquisar.setBackground(new java.awt.Color(0, 0, 0));
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
        jTablePesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePesquisarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablePesquisar);

        jButtonAbrirVenda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAbrirVenda.setText("Abrir Venda");
        jButtonAbrirVenda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAbrirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirVendaActionPerformed(evt);
            }
        });

        jButtonSalvar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonSalvar.setText("Salvar Venda");
        jButtonSalvar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonSalvar.setEnabled(false);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldValorVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonAbrirVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                        .addGap(153, 153, 153)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldDataVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                        .addGap(140, 140, 140)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(jTextFieldValorQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jButtonFinalizarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                            .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonGerarTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                            .addComponent(jTextFieldEntregue)
                                            .addComponent(jTextFieldTrouco))
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(515, 515, 515)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAbrirVenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldValorQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEntregue)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonGerarTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldTrouco, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("       CAIXA");
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
                .addGap(305, 305, 305)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addGap(310, 310, 310))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        try {
            int idVenda, Quantity = 0;
            //            // TODO add your handling code here:
            conexao = ModuloConexao.conector();
            String sql = ("select*from tbprodutos where nome_produto='" + jTextFieldProduto.getText() + "'");
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            Quantity = rs.getInt("quantidade");
            if (Quantity >= Integer.parseInt(jTextFieldValorQuantidade.getText())) {
                int resto = Quantity - Integer.parseInt(jTextFieldValorQuantidade.getText());

//         qtdParcela = Integer.parseInt(QuantidadesDeParcelas.getText());
//        valor = Float.parseFloat(ValorContrato.getText());
//        porc = Float.parseFloat(Juro.getText());
//        valorParcela = valor / qtdParcela;
                String cliente = jTextFieldCliente.getText().trim();
                String produto = jTextFieldProduto.getText().trim();
                String valor = jTextFieldValorVenda.getText();
                String quant = jTextFieldValorQuantidade.getText();
                String total = null;
//        float soma = 0;
                float vendval = Float.valueOf(valor);

                int quantidad = Integer.valueOf(quant);
                total = (String.valueOf(quantidad * vendval));
//       float total=Float.valueOf(valortot);

                DefaultTableModel val = (DefaultTableModel) jTableCadastro.getModel();
                val.addRow(new String[]{cliente, produto, valor, quant, total});
                adicionarItem();

                jTextFieldCliente.setText("");
                jTextFieldProduto.setText("");
                jTextFieldValorVenda.setText("");
                jTextFieldValorQuantidade.setText("");
//        Double soma=0.0;
//         for (int i=0;i<jTableCadastro.getColumnCount();i++){
//             if(jTableCadastro.getValueAt(i, 0).equals(true)){
//                Double valorAux=(Double)jTableCadastro.getValueAt(i, 4);
//                 soma+=valorAux;
//             }
//             jTextFieldValorTotal.setText(String.valueOf(soma));
//         }
//////////////////////////////////////////////////////////////////////////////////////
//         float teste=Float.parseFloat(total);
//        soma += Float.parseFloat(total);

//for (int i=0; i<jTableCadastro.getColumnCount(); i++) {
//float valor1 =Float.parseFloat((String) jTableCadastro.getValueAt(1, 4));
//soma+=valor1;
//}
//        jTextFieldValorTotal.setText(String.valueOf(soma));
////////////////////////////////////////////////////////////////////////////////////////
                Double orcamento = 0.0;
                for (int i = 0; i < jTableCadastro.getRowCount(); i++) {
                    orcamento += Double.parseDouble((String) jTableCadastro.getValueAt(i, 4));
//   float convert=(float) Double.parseDouble(jTextFieldValorTotal.getText());
                    jTextFieldValorTotal.setText(String.valueOf(orcamento) + "AKZ");
                    jTextFieldTotal.setText(String.valueOf(orcamento));
                    jButtonGerarTotal.setEnabled(true);
                     jTextFieldEntregue.setEnabled(true);

///////////////////////////calcular o trouco
                }
            } else {
                JOptionPane.showMessageDialog(null, "A quantidade dezejada não está disponivel no Estoque");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void FinalizarAvenda() {
        conexao = ModuloConexao.conector();

        try {
            String sql = "Insert into tbcaixavendafinal( totalvenda, data_venda) values (?,?)";
            pst = conexao.prepareStatement(sql);

            pst.setString(1, jTextFieldTotal.getText());
            pst.setString(2, jTextFieldDataVenda.getText());
            pst.execute();
            jTextFieldProduto.setEnabled(false);
            jTextFieldCliente.setEnabled(false);
            jTextFieldValorVenda.setEnabled(false);
            jTextFieldValorQuantidade.setEnabled(false);
            jButtonAdicionar.setEnabled(false);
            jTextFieldEntregue.setEnabled(false);
            jButtonFinalizarVenda.setEnabled(false);
            jButtonGerarTotal.setEnabled(false);
//            jButtonProcurar.setEnabled(false);
            jButtonAbrirVenda.setEnabled(true);
            jTextFieldEntregue.setText("");
            jTextFieldTrouco.setText("");
            jTextFieldValorTotal.setText("");
           jTextFieldTotal.setText("");
            JOptionPane.showMessageDialog(null, "Venda Finalizada Com Sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao Finalizar!\nERRO:" + ex);
        }
    }
    private void jButtonGerarTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarTotalActionPerformed
        // TODO add your handling code here:
        float entregue = Float.parseFloat(jTextFieldEntregue.getText());
        float valtot = Float.parseFloat(jTextFieldTotal.getText());
//        porc = Float.parseFloat(jTextFieldJuro.getText());
        float valorParcela = entregue - valtot;
        jTextFieldTrouco.setText(String.valueOf(valorParcela) + "AKZ");
         jButtonSalvar.setEnabled(true);

    }//GEN-LAST:event_jButtonGerarTotalActionPerformed

    private void jButtonFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarVendaActionPerformed
        // TODO add your handling code here:
        FinalizarAvenda();
    }//GEN-LAST:event_jButtonFinalizarVendaActionPerformed

    private void jTextFieldClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteKeyReleased

    private void jTextFieldProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldProdutoKeyReleased
        // TODO add your handling code here:

        preencherTabela();
    }//GEN-LAST:event_jTextFieldProdutoKeyReleased

    private void jTablePesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePesquisarMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_jTablePesquisarMouseClicked

    private void jButtonAbrirVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirVendaActionPerformed
        // TODO add your handling code here:
        conexao = ModuloConexao.conector();
        String sql = "insert into tbcaixavendafinal(totalvenda )values(?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setFloat(1, 0);
            pst.execute();
            jTextFieldProduto.setEnabled(true);
            jTextFieldCliente.setEnabled(true);
            jTextFieldValorVenda.setEnabled(true);
            jTextFieldValorQuantidade.setEnabled(true);
            jButtonAdicionar.setEnabled(true);
//            jTextFieldEntregue.setEnabled(true);
//            jButtonFinalizarVenda.setEnabled(true);
//            jButtonGerarTotal.setEnabled(true);
//            jButtonProcurar.setEnabled(true);
//            jButtonSalvar.setEnabled(true);
            jButtonAbrirVenda.setEnabled(false);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Abrir a venda!|nERRO:" + ex);
        }
    }//GEN-LAST:event_jButtonAbrirVendaActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:

        conexao = ModuloConexao.conector();
        int rows = jTableCadastro.getRowCount();
        try {
            String sql = "Insert into tbcaixavendas(nome_cli,nome_produto ,valor_venda,quant_produto,total,data_venda) values (?,?,?,?,?,?)";
            pst = conexao.prepareStatement(sql);
            for (int row = 0; row < rows; row++) {
                String coitemname = (String) jTableCadastro.getValueAt(row, 0);
                String cocateg = (String) jTableCadastro.getValueAt(row, 1);
                String codesc = (String) jTableCadastro.getValueAt(row, 2);
                String coloc = (String) jTableCadastro.getValueAt(row, 3);
                String coitemtagno = (String) jTableCadastro.getValueAt(row, 4);
                pst.setString(1, coitemname);
                pst.setString(2, cocateg);
                pst.setString(3, codesc);
                pst.setString(4, coloc);
                pst.setString(5, coitemtagno);
                pst.setString(6, jTextFieldDataVenda.getText());
                pst.execute();
//                JOptionPane.showMessageDialog(null, "Venda Finalizada Com Sucesso");
                jButtonFinalizarVenda.setEnabled(true);
                jButtonGerarTotal.setEnabled(true);
                jButtonAdicionar.setEnabled(false);
                 jButtonSalvar.setEnabled(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao Finalizar!\nERRO:" + ex);
        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbrirVenda;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonFinalizarVenda;
    private javax.swing.JButton jButtonGerarTotal;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCadastro;
    private javax.swing.JTable jTablePesquisar;
    private javax.swing.JTextField jTextFieldCliente;
    private javax.swing.JTextField jTextFieldDataVenda;
    private javax.swing.JTextField jTextFieldEntregue;
    private javax.swing.JTextField jTextFieldProduto;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldTrouco;
    private javax.swing.JTextField jTextFieldValorQuantidade;
    private javax.swing.JTextField jTextFieldValorTotal;
    private javax.swing.JTextField jTextFieldValorVenda;
    // End of variables declaration//GEN-END:variables
}
