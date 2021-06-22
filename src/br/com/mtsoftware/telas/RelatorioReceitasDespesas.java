/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
import static br.com.mtsoftware.telas.TelaPrincipal.Desktop;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author Jelson Fernandes
 */
public class RelatorioReceitasDespesas extends javax.swing.JInternalFrame {
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
     * Creates new form RelatorioReceitasDespesas
     */
    public RelatorioReceitasDespesas() {
        initComponents();
        getContentPane().setBackground(Color.white);
    }
     public void preencherTabela() {
        conexao = ModuloConexao.conector();
        // conexao = ModuloConexao.conector();
        //outro
        ArrayList dados = new ArrayList();
        //jfAjustaData parcelas=new AjustaData();

        String[] Colunas = new String[]{"ID", "Valor","Tipo","Descrição","Usuário","Data"};
        String sql = ("select*from tbrece_despes");

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            do {
                dados.add(new Object[]{rs.getString("id_recedesp"), rs.getString("valor"), rs.getString("tipo"), rs.getString("descricao"), rs.getString("usuario"), rs.getString("data_recdesp")});
            } while (rs.next());
            

            // String foundType = rs.getString(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!|nERRO:" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTablePesquisar.setModel((TableModel) modelo);
        jTablePesquisar.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTablePesquisar.getColumnModel().getColumn(0).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTablePesquisar.getColumnModel().getColumn(1).setResizable(false);
         jTablePesquisar.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTablePesquisar.getColumnModel().getColumn(2).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTablePesquisar.getColumnModel().getColumn(3).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(4).setPreferredWidth(250);
        jTablePesquisar.getColumnModel().getColumn(4).setResizable(false);
        jTablePesquisar.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTablePesquisar.getColumnModel().getColumn(5).setResizable(false);

        //Reorganizando o cabeçalho 
        jTablePesquisar.getTableHeader().setReorderingAllowed(false);
        jTablePesquisar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTablePesquisar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
 
      
      
    }
     public void pintrLinha(){
         int linhas = 5;
int colunas = 2;
JTable table = new JTable(linhas, colunas) {
    public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
         
        DefaultTableModel m = (DefaultTableModel) getModel();
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
         
        // altera a cor de background da linha para vermelho e foreground para branco
        // quando o valor da coluna 3 for igual a fechado 
        if (m.getValueAt(rowIndex, 2).toString().toLowerCase().equals("Receita")) {
            c.setBackground(new Color(192, 0, 0));
            c.setForeground(Color.white);
        } else {
            // mantem a cor padrão de foreground 
            c.setForeground(getForeground());
             
            // determina a cor de background da linha selecionada 
            if(isCellSelected(rowIndex, vColIndex)) {
                c.setBackground(new Color(184, 207, 229));
            } else {
                // linhas não selecionadas, manter cor de background padrão 
                c.setBackground(getBackground());
            }
 
        }           
        return c;
    }
};
     }
     
      public void ValorRecetas() {

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbrece_despes where (tipo='Receita')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jLabelReceitas.setText(String.valueOf(soma));
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro ao Consultar Totais!\nERRO" + error);
        }

    }
      public void ValorDespesas() {

        conexao = ModuloConexao.conector();

        try {

            String sql = "Select tipo, sum(valor)as soma from tbrece_despes where (tipo='Despesa')";

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                float soma = rs.getFloat("soma");
                jLabelDespesas.setText(String.valueOf(soma));
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesquisar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabelReceitas = new javax.swing.JLabel();
        jLabelDespesas = new javax.swing.JLabel();
        jLabelFundo = new javax.swing.JLabel();
        jButtonMensal = new javax.swing.JButton();

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 0));
        jLabel1.setText("     RELATÓRIO DE RECEITAS & DESPESAS");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTablePesquisar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Receitas                                                         Despesas                                                   Fundo Restante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 0))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 255, 255));

        jLabelReceitas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelReceitas.setForeground(new java.awt.Color(0, 204, 0));
        jLabelReceitas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelDespesas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelDespesas.setForeground(new java.awt.Color(255, 51, 0));
        jLabelDespesas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelFundo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelFundo.setForeground(new java.awt.Color(0, 204, 102));
        jLabelFundo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonMensal.setText("Buscar o Relatório Mensal");
        jButtonMensal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMensalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelReceitas, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addGap(95, 95, 95)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonMensal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDespesas, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addGap(81, 81, 81)
                .addComponent(jLabelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButtonMensal)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDespesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelReceitas, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        preencherTabela();
        pintrLinha();
        ValorRecetas();
        ValorDespesas();
         Color c=new java.awt.Color(0,123,0);
         int row = 0;
        Object texto= jTablePesquisar.getValueAt(row, 2);
        if(texto=="Receita" && "*".equals(texto.toString().substring(0,2))){
            
        
            c=Color.RED;
        
          c=Color.green;  
          jTablePesquisar.setForeground(c);
        
        }

        
       
        float fundo=Float.parseFloat(jLabelReceitas.getText())-Float.parseFloat(jLabelDespesas.getText());
        jLabelFundo.setText(String.valueOf(fundo) + " KZ");
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButtonMensalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMensalActionPerformed
        // TODO add your handling code here:
        
        
       PesquisarReceitas receitas = new PesquisarReceitas();
        receitas.setVisible(true);
        Desktop.add(receitas);
        try {
           receitas.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jButtonMensalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonMensal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelDespesas;
    private javax.swing.JLabel jLabelFundo;
    private javax.swing.JLabel jLabelReceitas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePesquisar;
    // End of variables declaration//GEN-END:variables
}
