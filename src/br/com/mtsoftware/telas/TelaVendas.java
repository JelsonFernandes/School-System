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
public class TelaVendas extends javax.swing.JInternalFrame {

    Connection conexao = null;
    ResultSet rs = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    ModeloVenda mod = new ModeloVenda();
    ControleVenda control = new ControleVenda();
    int flag = 1, codVenda;
    float precoProduto, Total;

    /**
     * Creates new form TelaVendas
     */
    public TelaVendas() {
        initComponents();
         getContentPane().setBackground(Color.white);
        jTextFieldData.setText(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(System.currentTimeMillis())));

    }

    public void preencherTabela() {
        conexao = ModuloConexao.conector();
        // conexao = ModuloConexao.conector();
        //outro
        ArrayList dados = new ArrayList();
        //jfAjustaData parcelas=new AjustaData();

        String[] Colunas = new String[]{"CÓDIGO", "NOME"};
        String sql = ("select*from tbclientes where nomecli like'%" + jTextFieldNomeCli.getText() + "%'");

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            do {
                dados.add(new Object[]{rs.getString("idcli"), rs.getString("nomecli")});
            } while (rs.next());

            // String foundType = rs.getString(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!|nERRO:" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTablePesquisa.setModel((TableModel) modelo);
        jTablePesquisa.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTablePesquisa.getColumnModel().getColumn(0).setResizable(false);
        jTablePesquisa.getColumnModel().getColumn(1).setPreferredWidth(510);
        jTablePesquisa.getColumnModel().getColumn(1).setResizable(false);

        //Reorganizando o cabeçalho 
        jTablePesquisa.getTableHeader().setReorderingAllowed(false);
        jTablePesquisa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTablePesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void preencherTabelaProduto() {
        conexao = ModuloConexao.conector();
        // conexao = ModuloConexao.conector();
        //outro
        ArrayList dados = new ArrayList();
        //jfAjustaData parcelas=new AjustaData();

        String[] Colunas = new String[]{"CÓDIGO", "PRODUTO", "QUANTIDADE"};
        String sql = ("select*from tbprodutos where nome_produto like'%" + jTextFieldProduto.getText() + "%'");

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            do {
                dados.add(new Object[]{rs.getString("id_produto"), rs.getString("nome_produto"), rs.getString("quantidade")});
            } while (rs.next());

            // String foundType = rs.getString(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!|nERRO:" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTablePesquisa.setModel((TableModel) modelo);
        jTablePesquisa.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTablePesquisa.getColumnModel().getColumn(0).setResizable(false);
        jTablePesquisa.getColumnModel().getColumn(1).setPreferredWidth(350);
        jTablePesquisa.getColumnModel().getColumn(1).setResizable(false);
        jTablePesquisa.getColumnModel().getColumn(2).setPreferredWidth(350);
        jTablePesquisa.getColumnModel().getColumn(2).setResizable(false);

        //Reorganizando o cabeçalho 
        jTablePesquisa.getTableHeader().setReorderingAllowed(false);
        jTablePesquisa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTablePesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
            String sql1 = ("select*from tbprodutos where nome_produto='" +jTextFieldProduto.getText() + "'");
            pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
            rs.first();
            quant = rs.getInt("quantidade");
            resto = quant - Integer.parseInt(jTextFieldQuantidade.getText());
            String sql2 = ("update tbprodutos set quantidade=? where nome_produto=?");
            pst = conexao.prepareStatement(sql2);
            pst.setInt(1, resto);
            pst.setString(2, jTextFieldProduto.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Prduto adicionado com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Realizar a Venda!|nERRO:" + ex);
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNomeCli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldData = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldProduto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldValorItem = new javax.swing.JTextField();
        jButtonBuscarProduto = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldValorTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItemVendas = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePesquisa = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jButtonFinalizarVenda = new javax.swing.JButton();
        jButtonCancelarVenda = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jButtonSoma = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxPagamento = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("FORMULÁRIO DE VENDA");

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel2.setText("Nome do Cliente");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldNomeCli.setBackground(new java.awt.Color(0, 0, 51));
        jTextFieldNomeCli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldNomeCli.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setText(" Data");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldData.setBackground(new java.awt.Color(0, 0, 51));
        jTextFieldData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldData.setForeground(new java.awt.Color(255, 255, 255));

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel4.setText("Produto");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldProduto.setBackground(new java.awt.Color(0, 0, 51));
        jTextFieldProduto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldProduto.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Quantidade");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldQuantidade.setBackground(new java.awt.Color(0, 0, 51));
        jTextFieldQuantidade.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldQuantidade.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldQuantidadeFocusGained(evt);
            }
        });

        jLabel6.setText("Valor Por Item");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldValorItem.setBackground(new java.awt.Color(0, 0, 51));
        jTextFieldValorItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldValorItem.setForeground(new java.awt.Color(255, 255, 255));

        jButtonBuscarProduto.setText("Buscar");
        jButtonBuscarProduto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonBuscarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarProdutoActionPerformed(evt);
            }
        });

        jLabel7.setText("Valor Total");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldValorTotal.setBackground(new java.awt.Color(0, 0, 51));
        jTextFieldValorTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldValorTotal.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorTotalActionPerformed(evt);
            }
        });

        jTableItemVendas.setBackground(new java.awt.Color(51, 51, 255));
        jTableItemVendas.setForeground(new java.awt.Color(255, 255, 255));
        jTableItemVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLIENTE", "PRODUTO", "VALOR", "QUANTIDADE", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(jTableItemVendas);

        jLabel8.setText("Pesquisar");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTablePesquisa.setBackground(new java.awt.Color(0, 0, 51));
        jTablePesquisa.setForeground(new java.awt.Color(255, 255, 255));
        jTablePesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTablePesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePesquisaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablePesquisa);

        jLabel9.setText("Itens Venda");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonFinalizarVenda.setText("Finalizar a Venda");
        jButtonFinalizarVenda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarVendaActionPerformed(evt);
            }
        });

        jButtonCancelarVenda.setText("Cancelar a Venda");
        jButtonCancelarVenda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonCancelarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarVendaActionPerformed(evt);
            }
        });

        jButtonAdd.setText("Add");
        jButtonAdd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jLabel10.setText("ID");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldId.setBackground(new java.awt.Color(0, 0, 51));
        jTextFieldId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldId.setForeground(new java.awt.Color(255, 255, 255));

        jButtonSoma.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonSoma.setText("       ABRIR    O   CAIXA             ");
        jButtonSoma.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonSoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSomaActionPerformed(evt);
            }
        });

        jLabel11.setText("Pagamento:");

        jComboBoxPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Vista", "Parcelado" }));
        jComboBoxPagamento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Salvar as Vendas");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(639, 639, 639))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxPagamento, 0, 129, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonFinalizarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldNomeCli, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(155, 155, 155)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonBuscarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(102, 102, 102)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldId, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                                        .addGap(53, 53, 53))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                        .addGap(161, 161, 161)
                                        .addComponent(jButtonSoma, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                        .addGap(8, 8, 8))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                                .addGap(143, 143, 143))
                                            .addComponent(jTextFieldData, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                                        .addGap(37, 37, 37)
                                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(93, 93, 93))
                                            .addComponent(jTextFieldQuantidade))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(81, 81, 81))
                                    .addComponent(jTextFieldValorItem)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarProduto)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAdd)
                            .addComponent(jTextFieldValorItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jButtonSoma, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonFinalizarVenda)
                        .addComponent(jButtonCancelarVenda)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        flag = 1;
        preencherTabela();
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonBuscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarProdutoActionPerformed
        try {
            // TODO add your handling code here:
            conexao = ModuloConexao.conector();
            String sql = "insert into tbitemvendas(total_venda)values(?)";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setFloat(1, 0);
                pst.execute();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Abrir a venda!|nERRO:" + ex);
            }
            String sql1 = "select*from tbvendas";
            pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
            rs.last();
            codVenda = rs.getInt("id_venda");
            flag = 2;
            preencherTabelaProduto();
        } catch (SQLException ex) {
            Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonBuscarProdutoActionPerformed

    private void jTablePesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePesquisaMouseClicked
        // TODO add your handling code here:
        if (flag == 1) {
            String id_cliente = "" + jTablePesquisa.getValueAt(jTablePesquisa.getSelectedRow(), 0);
            String nome_cliente = "" + jTablePesquisa.getValueAt(jTablePesquisa.getSelectedRow(), 1);
            jTextFieldId.setText(id_cliente);
            jTextFieldNomeCli.setText(nome_cliente);
        } else {
            try {
                String id_produto = "" + jTablePesquisa.getValueAt(jTablePesquisa.getSelectedRow(), 0);
                String nome_produto = "" + jTablePesquisa.getValueAt(jTablePesquisa.getSelectedRow(), 1);
                conexao = ModuloConexao.conector();

                conexao = ModuloConexao.conector();
                String sql = "select*from tbprodutos where nome_produto='" + nome_produto + "'";

                pst = conexao.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();

                precoProduto = rs.getFloat("Preco_venda");
                jTextFieldId.setText(id_produto);
                jTextFieldProduto.setText(nome_produto);
                jTextFieldValorItem.setText(String.valueOf(precoProduto));
                jTextFieldQuantidade.setText("01");
                jTextFieldValorTotal.setText("00");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Setar os Campos!/nERRO: " + ex);
            }
        }
    }//GEN-LAST:event_jTablePesquisaMouseClicked

    private void jTextFieldQuantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeFocusGained
        // TODO add your handling code here:
//        float valorTotal;
//        valorTotal = Float.parseFloat(jTextFieldValorItem.getText()) * Integer.parseInt(jTextFieldQuantidade.getText());
//        jTextFieldValorTotal.setText(String.valueOf(valorTotal));
        jTextFieldValorTotal.setText(String.valueOf(Total));
    }//GEN-LAST:event_jTextFieldQuantidadeFocusGained

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened


    }//GEN-LAST:event_formInternalFrameOpened

    private void jButtonSomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSomaActionPerformed
        VendaCaixa caixa = new VendaCaixa();
        caixa.setVisible(true);
        Desktop.add(caixa);
        try {
            caixa.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();

    }//GEN-LAST:event_jButtonSomaActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        try {
            int idVenda, Quantity = 0;
            //            // TODO add your handling code here:
            conexao = ModuloConexao.conector();
            String sql = ("select*from tbprodutos where nome_produto='" + jTextFieldProduto.getText() + "'");
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            Quantity = rs.getInt("quantidade");
            if (Quantity >= Integer.parseInt(jTextFieldQuantidade.getText())) {
                int resto = Quantity - Integer.parseInt(jTextFieldQuantidade.getText());

//                mod.setNomeProduto(jTextFieldProduto.getText());
//                mod.setQuantItem(Integer.parseInt(jTextFieldQuantidade.getText()));
//                mod.setId_Venda(codVenda);
//               
//                preencherTabelaItensVenda();
//                pst = conexao.prepareStatement(sql);
//                rs = pst.executeQuery();
//                JOptionPane.showMessageDialog(null, "Preco_venda");
////                Total = Total + Float.parseFloat(jTextFieldValorItem.getText()) * Integer.parseInt(jTextFieldQuantidade.getText());
//                jTextFieldValorTotal.setText(String.valueOf(Total));
//            } else {
//                JOptionPane.showMessageDialog(null, "A quantidade dezejada não está disponivel no Estoque");
//            }
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro !|nERRO:" + ex);
//        }

//         qtdParcela = Integer.parseInt(QuantidadesDeParcelas.getText());
//        valor = Float.parseFloat(ValorContrato.getText());
//        porc = Float.parseFloat(Juro.getText());
//        valorParcela = valor / qtdParcela;
String cliente = jTextFieldNomeCli.getText().trim();
String produto = jTextFieldProduto.getText().trim();
String valor = jTextFieldValorItem.getText();
String quant = jTextFieldQuantidade.getText();
String total = null;
//        float soma = 0;
float vendval = Float.valueOf(valor);

int quantidad = Integer.valueOf(quant);
total = (String.valueOf(quantidad * vendval));
//       float total=Float.valueOf(valortot);

DefaultTableModel val = (DefaultTableModel) jTableItemVendas.getModel();
val.addRow(new String[]{cliente, produto, valor, quant, total});
 adicionarItem();


jTextFieldNomeCli.setText("");
jTextFieldProduto.setText("");
jTextFieldValorItem.setText("");
jTextFieldQuantidade.setText("");


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
for (int i = 0; i < jTableItemVendas.getRowCount(); i++) {
    orcamento += Double.parseDouble((String) jTableItemVendas.getValueAt(i, 4));
//   float convert=(float) Double.parseDouble(jTextFieldValorTotal.getText());
jTextFieldValorTotal.setText(String.valueOf(orcamento) + "AKZ");
jTextFieldValorTotal.setText(String.valueOf(orcamento));

///////////////////////////calcular o trouco
}
            } else {
                JOptionPane.showMessageDialog(null, "A quantidade dezejada não está disponivel no Estoque");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarVendaActionPerformed

        // TODO add your handling code here:
        if (jComboBoxPagamento.getSelectedItem().equals("A Vista")) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            conexao = ModuloConexao.conector();
//        mod.setId_Venda(codVenda);
//        mod.setNomeCliente(jTextFieldNomeCli.getText());
            mod.setValor_Venda(Float.parseFloat(jTextFieldValorTotal.getText()));
            mod.setData_Venda((jTextFieldData.getText()));
            mod.setPagamento((String) jComboBoxPagamento.getSelectedItem());
            control.fechaVenda(mod);
//         this.dispose();
        } else {
            conexao = ModuloConexao.conector();
//        mod.setId_Venda(codVenda);
//        mod.setNomeCliente(jTextFieldNomeCli.getText());
            mod.setData_Venda((jTextFieldData.getText()));
            mod.setValor_Venda(Float.parseFloat(jTextFieldValorTotal.getText()));
            mod.setPagamento((String) jComboBoxPagamento.getSelectedItem());
            control.fechaVenda(mod);
            ParcelaVendas parcevendas = new ParcelaVendas(codVenda);
            parcevendas.setVisible(true);
            Desktop.add(parcevendas);
            try {
                parcevendas.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
        }

    }//GEN-LAST:event_jButtonFinalizarVendaActionPerformed

    private void jButtonCancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarVendaActionPerformed
        // TODO add your handling code here:
        control.cancelaVenda();
        dispose();
    }//GEN-LAST:event_jButtonCancelarVendaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        conexao = ModuloConexao.conector();
        int rows = jTableItemVendas.getRowCount();
        try {
            String sql = "Insert into tbvendas(data_venda,valor_venda,nomecli,nome_produto ,quant_produto,total) values (?,?,?,?,?,?)";
            pst = conexao.prepareStatement(sql);
            for (int row = 0; row < rows; row++) {
                String cli = (String) jTableItemVendas.getValueAt(row, 0);
                String prod = (String) jTableItemVendas.getValueAt(row, 1);
                String valor = (String) jTableItemVendas.getValueAt(row, 2);
                String quant = (String) jTableItemVendas.getValueAt(row, 3);
                String total = (String) jTableItemVendas.getValueAt(row, 4);
                pst.setString(1, jTextFieldData.getText());
                pst.setString(2, valor);
                pst.setString(3, cli);
                pst.setString(4, prod);
                pst.setString(5, quant);
                pst.setString(6, total);

                pst.execute();
               
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao Finalizar!\nERRO:" + ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorTotalActionPerformed
//    public void preencherTabelaItensVenda() {
//        conexao = ModuloConexao.conector();
//        String sql = ("select*from tbprodutos inner join tbitemvendas \n" +
//"on tbprodutos.id_produto=tbitemvendas.idproduto inner join tbvendas \n" +
//"on tbvendas.id_venda=tbitemvendas.idvenda where tbvendas.id_venda=" + codVenda);
//
//        // conexao = ModuloConexao.conector();
//        //outro
//        ArrayList dados = new ArrayList();
//
//        //jfAjustaData parcelas=new AjustaData();
//        String[] Colunas = new String[]{"DESCRIÇÃO", "QUANTIDADE", "VALOR TOTAL"};
//        try {
//            pst = conexao.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            rs.first();
//            do {
//
//                float ValorProduto = rs.getFloat("Preco_venda");
//                int QuantVendid = rs.getInt("quant_produto");
//
//                dados.add(new Object[]{rs.getString("nome_produto"), rs.getString("quant_produto"), ValorProduto * QuantVendid});
////                ValorProduto * QuantVendid});
//
//            } while (rs.next());
//
//            // String foundType = rs.getString(1);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!|nERRO:" + ex);
//        }
//        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
//        jTableItemVendas.setModel((TableModel) modelo);
////        modelo.addTableModelListener(jTablePesquisa);
//        jTableItemVendas.getColumnModel().getColumn(0).setPreferredWidth(250);
//        jTableItemVendas.getColumnModel().getColumn(0).setResizable(false);
//        jTableItemVendas.getColumnModel().getColumn(1).setPreferredWidth(350);
//        jTableItemVendas.getColumnModel().getColumn(1).setResizable(false);
//        jTableItemVendas.getColumnModel().getColumn(2).setPreferredWidth(350);
//        jTableItemVendas.getColumnModel().getColumn(2).setResizable(false);
//        //Reorganizando o cabeçalho
//        jTableItemVendas.getTableHeader().setReorderingAllowed(false);
//        jTableItemVendas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        jTableItemVendas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        somaProduto();
//    }
//
//    public void somaProduto() {
//        Total = 0;
//        int Quant = 0;
//        float valor = 0;
//        try {
//            conexao = ModuloConexao.conector();
//            String sql = ("select*from tbitemvendas inner join tbprodutos on tbitemvendas.idproduto=tbprodutos.id_produto  where idvenda=" + codVenda);
//            pst = conexao.prepareStatement(sql);
//            rs = pst.executeQuery();
////            rs.first();
//            while (rs.next()) {
////                Quant = rs.getInt("quantidade");
////                valor = rs.getFloat("Preco_venda");
//                Total = Total + rs.getFloat("Preco_venda") * rs.getInt("quant_produto");
////                JOptionPane.showMessageDialog(null, "qtd" + Quant + "pv=" + valor);
//
//                jTextFieldValorTotal.setText(String.valueOf(Total));
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao fazer a soma !|nERRO:" + ex);
//        }
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonBuscarProduto;
    private javax.swing.JButton jButtonCancelarVenda;
    private javax.swing.JButton jButtonFinalizarVenda;
    private javax.swing.JButton jButtonSoma;
    private javax.swing.JComboBox<String> jComboBoxPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTable jTableItemVendas;
    private javax.swing.JTable jTablePesquisa;
    private javax.swing.JTextField jTextFieldData;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldNomeCli;
    private javax.swing.JTextField jTextFieldProduto;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldValorItem;
    private javax.swing.JTextField jTextFieldValorTotal;
    // End of variables declaration//GEN-END:variables
}
