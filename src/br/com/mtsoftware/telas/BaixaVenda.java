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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jelson Fernandes
 */
public class BaixaVenda extends javax.swing.JInternalFrame {

    ResultSet rs = null;

    PreparedStatement pst = null;
    Connection conexao = null;
    ModeloBaixaVenda mod = new ModeloBaixaVenda();
    ControleBaixaVenda control = new ControleBaixaVenda();
    float valorm, valord, parcelado;
    String multa;
    String desconto;

    /**
     * Creates new form BaixaVenda
     */
    public BaixaVenda() {
        initComponents();
        getContentPane().setBackground(Color.white);
        conexao = ModuloConexao.conector();
        jTextFieldDataPag.setText(new SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date(System.currentTimeMillis())));
    }

    public final class ModeloTabela extends AbstractTableModel {

        private ArrayList linhas = null;
        private String[] colunas = null;

        public ModeloTabela(ArrayList lin, String[] col) {
            setLinhas(lin);
            setColunas(col);

        }

        public ArrayList getLinhas() {
            return linhas;
        }

        public void setLinhas(ArrayList dados) {
            linhas = dados;
        }

        public String[] getColunas() {
            return colunas;
        }

        public void setColunas(String[] nomes) {
            colunas = nomes;
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public int getRowCount() {
            return linhas.size();
        }

        @Override
        public String getColumnName(int numCol) {
            return colunas[numCol];
        }

        @Override
        public Object getValueAt(int numLin, int numCol) {
            Object[] linha = (Object[]) getLinhas().get(numLin);
            return linha[numCol];
        }
    }

    public void preecherTabela(String sql) {

        // conexao = ModuloConexao.conector();
        //outro
        ArrayList dados = new ArrayList();
        //jfAjustaData parcelas=new AjustaData();

        String[] Colunas = new String[]{"Nº da Parcela", "Parcela", "Nº do Contrato", "Valor da Parcela", "Situação", "  Data do Venimento", " Data do Pagamento"};

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            do {
                dados.add(new Object[]{rs.getRow(), rs.getInt("id_parcela"), rs.getString("id_venda"), rs.getString("valorparc"), rs.getString("Estado"), rs.getString("dataparc"), rs.getString("dataat")});
            } while (rs.next());

            // String foundType = rs.getString(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!|nERRO:" + ex);
        }
        BaixaVenda.ModeloTabela modelo = new BaixaVenda.ModeloTabela(dados, Colunas);
        TabelaBaixa.setModel((TableModel) modelo);
        TabelaBaixa.getColumnModel().getColumn(0).setPreferredWidth(100);
        TabelaBaixa.getColumnModel().getColumn(0).setResizable(false);
        TabelaBaixa.getColumnModel().getColumn(1).setPreferredWidth(100);
        TabelaBaixa.getColumnModel().getColumn(1).setResizable(false);
        TabelaBaixa.getColumnModel().getColumn(2).setPreferredWidth(100);
        TabelaBaixa.getColumnModel().getColumn(2).setResizable(false);
        TabelaBaixa.getColumnModel().getColumn(3).setPreferredWidth(120);
        TabelaBaixa.getColumnModel().getColumn(3).setResizable(false);
        TabelaBaixa.getColumnModel().getColumn(4).setPreferredWidth(130);
        TabelaBaixa.getColumnModel().getColumn(4).setResizable(false);
        TabelaBaixa.getColumnModel().getColumn(5).setPreferredWidth(130);
        TabelaBaixa.getColumnModel().getColumn(5).setResizable(false);
        TabelaBaixa.getColumnModel().getColumn(6).setPreferredWidth(130);
        TabelaBaixa.getColumnModel().getColumn(6).setResizable(false);
        //Reorganizando o cabeçalho 
        TabelaBaixa.getTableHeader().setReorderingAllowed(false);
        TabelaBaixa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TabelaBaixa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void setar_campos() {
        int setar = TabelaBaixa.getSelectedRow();

        jTextFieldCod.setText(TabelaBaixa.getModel().getValueAt(setar, 0).toString());
        jTextFieldCodVenda.setText(TabelaBaixa.getModel().getValueAt(setar, 2).toString());
        jTextFieldValorParc.setText(TabelaBaixa.getModel().getValueAt(setar,3 ).toString());
        jTextFieldVencimento.setText(TabelaBaixa.getModel().getValueAt(setar, 5).toString());
        jTextFieldDataPag.setText(TabelaBaixa.getModel().getValueAt(setar, 6).toString());

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
        jTextFieldCodVenda = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldVencimento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCod = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldValorParc = new javax.swing.JTextField();
        jButtonPagar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaBaixa = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldDataPag = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("CÓDIGO DA PARCELA");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldCodVenda.setDisabledTextColor(new java.awt.Color(255, 255, 0));

        jButtonBuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonBuscar.setText("BUSCAR");
        jButtonBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("DATA DO VENCIMENTO");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldVencimento.setDisabledTextColor(new java.awt.Color(255, 255, 0));
        jTextFieldVencimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldVencimentoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("CÓDIGO DA VENDA");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldCod.setDisabledTextColor(new java.awt.Color(255, 255, 0));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("   VALOR DA PARCELA");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldValorParc.setDisabledTextColor(new java.awt.Color(255, 255, 0));

        jButtonPagar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonPagar.setText("Efetuar o Pagamento");
        jButtonPagar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarActionPerformed(evt);
            }
        });

        TabelaBaixa.setBackground(new java.awt.Color(255, 255, 0));
        TabelaBaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TabelaBaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaBaixaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaBaixa);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("     DATA DO PAGAMENTO");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldDataPag.setDisabledTextColor(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldValorParc)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCodVenda))
                                .addGap(29, 29, 29)
                                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel6)
                                        .addGap(73, 73, 73))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldDataPag, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCod))))
                        .addGap(38, 38, 38))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCod, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDataPag, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldValorParc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("              FORMULÁRIO DE BAIXA DE PARCELAS");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        conexao = ModuloConexao.conector();

        int codigo;

        if (!jTextFieldCodVenda.getText().equals("")) {
            codigo = Integer.parseInt(jTextFieldCodVenda.getText());
            mod.setCodContr(codigo);
            mod = control.BuscaParcela(mod);
            jTextFieldCodVenda.setText(String.valueOf(mod.getCodContr()));
            jTextFieldCod.setText(String.valueOf(mod.getCodParc()));
            jTextFieldVencimento.setText(mod.getDatavenc());
            jTextFieldValorParc.setText(String.valueOf(mod.getValor()));
            // jTextFieldPagamento.setText(String.valueOf(mod.getData_pag()));
            preecherTabela("select*from tbparcelaVendas where id_venda='" + jTextFieldCodVenda.getText() + "'order by id_parcela");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Informe um número de Parcela Válida!");
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jTextFieldVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldVencimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldVencimentoActionPerformed

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed
        // TODO add your handling code here:
        mod.setCodParc(Integer.parseInt(jTextFieldCod.getText()));
        try {
            control.BaixaVenda(mod);
        } catch (SQLException ex) {
            Logger.getLogger(BaixaParcelas1.class.getName()).log(Level.SEVERE, null, ex);
        }
        preecherTabela("select*from tbparcelavendas where id_venda='" + jTextFieldCodVenda.getText() + "'order by id_parcela");


    }//GEN-LAST:event_jButtonPagarActionPerformed

    private void TabelaBaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaBaixaMouseClicked
        // TODO add your handling code here:
         setar_campos();
    }//GEN-LAST:event_TabelaBaixaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelaBaixa;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonPagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCod;
    private javax.swing.JTextField jTextFieldCodVenda;
    private javax.swing.JTextField jTextFieldDataPag;
    private javax.swing.JTextField jTextFieldValorParc;
    private javax.swing.JTextField jTextFieldVencimento;
    // End of variables declaration//GEN-END:variables
}
