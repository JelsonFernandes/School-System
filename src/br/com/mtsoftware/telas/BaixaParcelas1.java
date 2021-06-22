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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jelson Fernandes
 */
public class BaixaParcelas1 extends javax.swing.JInternalFrame {

    ResultSet rs = null;

    PreparedStatement pst = null;
    Connection conexao = null;
    ModeloBaixaParcela mod = new ModeloBaixaParcela();
    ControleBaixaParcela control = new ControleBaixaParcela();
    float valorm, valord, parcelado;
    String multa;
    String desconto;
    int idPag;

    /**
     * Creates new form BaixaParcelas
     */
    public BaixaParcelas1() {
        initComponents();
        getContentPane().setBackground(Color.white);

        conexao = ModuloConexao.conector();
        jTextFieldPagamento.setText(new SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date(System.currentTimeMillis())));

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

        String[] Colunas = new String[]{"Nº da Parcela", "Parcela", "Nº do Contrato", "Valor da Parcela", "Situação", "Venimento", "Pagamento"};

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            do {
                dados.add(new Object[]{rs.getRow(), rs.getInt("id_parcela"), rs.getString("id_contrato"), rs.getString("valorparc"), rs.getString("Estado"), rs.getString("data_venc"), rs.getString("dataat"),});
            } while (rs.next());

            // String foundType = rs.getString(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!|nERRO:" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
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
        jTextFieldNuparc.setText(TabelaBaixa.getModel().getValueAt(setar, 0).toString());
        txtCodParcela.setText(TabelaBaixa.getModel().getValueAt(setar, 1).toString());
        textCodContrato.setText(TabelaBaixa.getModel().getValueAt(setar, 2).toString());
        ValorParc.setText(TabelaBaixa.getModel().getValueAt(setar, 3).toString());
        txtSituacao.setText(TabelaBaixa.getModel().getValueAt(setar, 4).toString());
        datVenc.setText(TabelaBaixa.getModel().getValueAt(setar, 5).toString());
        jTextFieldPagamento.setText(TabelaBaixa.getModel().getValueAt(setar, 6).toString());

    }

    private void setar_Pagamentos() {
        int setar = tblContrato.getSelectedRow();
        jTextFieldNome.setText(tblContrato.getModel().getValueAt(setar, 1).toString());
        jTextFieldClasse.setText(tblContrato.getModel().getValueAt(setar, 2).toString());
        Contrato.setText(tblContrato.getModel().getValueAt(setar, 0).toString());
    }

    private void adicionar() {
        String sql = "insert into tbpagamentos(nome,classe,tipo,valor,id_contrato)value(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, jTextFieldNome.getText());
            pst.setString(2, jTextFieldClasse.getText());
            pst.setString(3, jComboBoxTipo.getSelectedItem().toString());
            pst.setString(4, jTextFieldValor.getText());
            pst.setString(5, Contrato.getText());

            //validação dos campos obrigatórios
            if ((jTextFieldNome.getText().isEmpty()) || (jTextFieldClasse.getText().isEmpty())) {
                JOptionPane.showInternalMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Pagamento Efectuado com sucesso");
                    jTextFieldNome.setText(null);
                    jComboBoxTipo.setSelectedItem(null);
                    jTextFieldClasse.setText(null);
                    jTextFieldValor.setText(null);

                }
            }
            String sql1 = "select*from tbpagamentos";
            pst = conexao.prepareStatement(sql1);
            rs = pst.executeQuery();
            rs.last();
            idPag = rs.getInt("id_pagamento");
            int sair = JOptionPane.showConfirmDialog(null, "Dezeja Imprimir um Recibo para Este Aluno? ", "Atenção", JOptionPane.YES_NO_OPTION);
            if (sair == JOptionPane.YES_OPTION) {
                ImprimirParcela imprimir = new ImprimirParcela(idPag);
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

    }
    // método para pesquisar clientes pelo nome com filtro

    private void pesquisar_alunos() {
        String sql = "select id_contrato as ID,nome as Nome,classealuno as Classe from tbacontratos where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteudo da caixa de pesquisa para o interroga
            // atenção a porcentagem que é a continuação da string sql
            pst.setString(1, txtAluPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar
            tblContrato.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //método para setar campos
    private void pesquisar_contrato() {
        // a linha abaixo cria uma caixa de entrada do tipo JOption Pane
        String num_contrato = JOptionPane.showInputDialog("Número de Pagamento");
        String sql = "select*from tbpagamentos where id_pagamento=" + num_contrato;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {

                txtPagamento.setText(rs.getString(1));
                jTextFieldNome.setText(rs.getString(2));
                jTextFieldClasse.setText(rs.getString(3));
                jComboBoxTipo.setSelectedItem(rs.getString(4));
                jTextFieldValor.setText(rs.getString(5));
                Contrato.setText(rs.getString(6));

//                //evitando problemas
//                jButton15.setEnabled(false);
//                txtAluPesquisar.setEnabled(false);
//                tblContrato.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Pagamento Não Efetuado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nº de Pagamento Inválido");
            // System.out.println(e);
        }

    }

    //método para alterar dados do cliente
    private void alterar() {
        String sql = "Update tbpagamentos set nome=?,classe=?,tipo=?,valor=? where id_pagamento=?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, jTextFieldNome.getText());
            pst.setString(2, jTextFieldClasse.getText());
            pst.setString(3, jComboBoxTipo.getSelectedItem().toString());
            pst.setString(4, jTextFieldValor.getText());
            pst.setString(5, txtPagamento.getText());
            if ((jTextFieldNome.getText().isEmpty()) || (jTextFieldClasse.getText().isEmpty())) {
                JOptionPane.showInternalMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Pagamento Alterado com sucesso");
                    jTextFieldNome.setText(null);
                    jComboBoxTipo.setSelectedItem(null);
                    jTextFieldClasse.setText(null);
                    jTextFieldValor.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void calcularJuros() {
        try {
            conexao = ModuloConexao.conector();

            //st = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            //jlabelempresa.setText(String.valueOf(nome));
            SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
            // Date hoje = new Date();
            //JOptionPane.showMessageDialog(rootPane,df.format(hoje));
            // String dataAtual = (String) df.format(hoje);
            //String dataSistema = rs.getString(vencimento);
            //SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
//		jf
            String dataVencimento = String.valueOf(datVenc.getText());
            String Pagamento = String.valueOf(jTextFieldPagamento.getText());
            System.out.println(dataVencimento + "---" + Pagamento);
            Date data1 = df.parse(dataVencimento);
            Date data2 = df.parse(Pagamento);
            valorm = (Float.parseFloat(multa));
            valord = (Float.parseFloat(desconto));
//System.out.println("multa");
            parcelado = (Float.parseFloat(ValorParc.getText()));
            float tmulta = (parcelado * valorm / 100);
            float tdesconto = (parcelado * valord / 100);//Valor da parcela*o desconto/100

//   mod.setValor_total(Float.parseFloat(ValorTotal.getText()));
//                    mod.setValor_contrato(Float.parseFloat(ValorContrato.getText()))
            if (data2.compareTo(data1) > 0) {
                // System.out.println(" Este aluno terá que pagar com juros");
                //JOptionPane.showMessageDialog(null,"Este aluno terá que pagar uma multa de:"+multa);

                // jTextFieldMulta.setText(etFloat(ParceMulta));
                // jTextFieldMulta.setText(String.valueOf(parcelado*valorm/100));
                jTextFieldMulta.setText(String.valueOf(tmulta));
                jTextFieldTotal.setText(String.valueOf(parcelado + tmulta));
                jTextFieldDesconto.setText(null);

            } else if (data2.compareTo(data1) < 0) {
                //System.out.println("Este aluno deve sofrer um desconto");
                jTextFieldDesconto.setText(String.valueOf(tdesconto));
                jTextFieldTotal.setText(String.valueOf(parcelado - tdesconto));
                jTextFieldMulta.setText(null);

            } else {
                jTextFieldTotal.setText(String.valueOf(parcelado));
            }
            System.out.println(data1 + "---" + data2);
            btnPagamento.setEnabled(true);
        } catch (ParseException ex) {
            Logger.getLogger(BaixaParcelas1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BaixaParcelas1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public class Impressao {

        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        public void Imprime_Relatorio(List lista) {

//String  caminhoRelJasper="/br/com/mtsoftware/telas/FacturaPagamentos.jasper";
//InputStream relJasper=getClass().getResourceAsStream(caminhoRelJasper);
//JRBeanCollectionDataSource da = new JRBeanCollectionDataSource(lista);
//Map parametros = new HashMap(); 
//JasperPrint impressao=null;
//        try {
//            impressao= JasperFillManager.fillReport(relJasper, parametros,da);
//            JasperViewer viewer = new JasperViewer (impressao,true);
//            viewer.setVisible(true);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());                                        
//                    
//                    
//        }
        }

    }

    private void excluir_Parcelas() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir estas Parcelas?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbparcelas where id_contrato=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCodParcela.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Parcelas excluidas com sucesso");
                    txtCodParcela.setText(null);

                    textCodContrato.setText(null);
                    ValorParc.setText(null);
                    // txtDataContrato.setText(null);
                    txtSituacao.setText(null);
                    datVenc.setText(null);
                    jTextFieldDesconto.setText(null);
                    jTextFieldMulta.setText(null);
                    jTextFieldPagamento.setText(null);
                    jTextFieldTotal.setText(null);
                    //habilitar contrato objectos
//                   jTextFieldNuparc.setEnabled(true);
//                    txtAluPesquisar.setEnabled(true);
//                    tblContrato.setVisible(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    private void imprimir() {

        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste Pagamento?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("pagamento", Integer.parseInt(txtPagamento.getText()));
                net.sf.jasperreports.engine.JasperPrint print = JasperFillManager.fillReport("lib/report/pag.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

    }
     private void pesquisarpagamentos() {

        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste Pagamento?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("Estado", (txtSituacao.getText()));
                 filtro.put("num_parcela", Integer.parseInt(jTextFieldNuparc.getText()));
                net.sf.jasperreports.engine.JasperPrint print = JasperFillManager.fillReport("lib/report/Pagos_Naopagos.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

    }


//    private void imprimir(){
//        
//        
//        int comfirma = JOptionPane.showConfirmDialog(null, "Deseja imprimir uma Factura para este Pagamento ?", "Atenção", JOptionPane.YES_NO_OPTION);
//        if(comfirma==JOptionPane.YES_OPTION){
//            try {
//                HashMap filtro= new HashMap();
//                filtro.put("id_contrato",Integer.parseInt(textCodContrato.getText()));
//                 filtro.put("num_parcela", Integer.parseInt(jTextFieldNuparc.getText()));
//               
//                JasperPrint print=JasperFillManager.fillReport("c:/report/Pagamento.jasper",filtro,conexao);
//                JasperViewer.viewReport(print, false);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//        }
//        
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaBaixa = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblContrato = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        txtPagamento = new javax.swing.JTextField();
        jTextFieldValor = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtAluPesquisar = new javax.swing.JTextField();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldClasse = new javax.swing.JTextField();
        Contrato = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldNuparc = new javax.swing.JTextField();
        btnPagamento = new javax.swing.JButton();
        jTextFieldTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        Multa2 = new javax.swing.JLabel();
        datVenc = new javax.swing.JTextField();
        jTextFieldDesconto = new javax.swing.JTextField();
        jButtonSetarMulta = new javax.swing.JButton();
        jTextFieldMulta = new javax.swing.JTextField();
        jTextFieldPagamento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Multa1 = new javax.swing.JLabel();
        Multa = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textCodContrato = new javax.swing.JTextField();
        txtCodParcela = new javax.swing.JTextField();
        ValorParc = new javax.swing.JTextField();
        txtSituacao = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setForeground(new java.awt.Color(255, 255, 0));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("              Baixa de Parcelas");
        setPreferredSize(new java.awt.Dimension(808, 680));
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

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setEnabled(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(807, 680));

        TabelaBaixa.setBackground(new java.awt.Color(0, 0, 102));
        TabelaBaixa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabelaBaixa.setForeground(new java.awt.Color(255, 255, 255));
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
        TabelaBaixa.setGridColor(new java.awt.Color(255, 102, 0));
        TabelaBaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaBaixaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaBaixa);

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGAMENTOS OUTROS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 204, 0))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 0));

        jButton14.setBackground(new java.awt.Color(102, 102, 102));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("ALTERAR");
        jButton14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        tblContrato.setBackground(new java.awt.Color(0, 0, 153));
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

        jButton15.setBackground(new java.awt.Color(102, 102, 102));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("PAGAR");
        jButton15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(102, 102, 102));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Pesquisar");
        jButton16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        txtPagamento.setEditable(false);
        txtPagamento.setEnabled(false);

        jTextFieldValor.setBackground(new java.awt.Color(204, 204, 255));

        jLabel16.setForeground(new java.awt.Color(255, 255, 0));
        jLabel16.setText("Nº Pagamento");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setForeground(new java.awt.Color(255, 255, 0));
        jLabel20.setText("  VALOR");
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setForeground(new java.awt.Color(255, 255, 0));
        jLabel17.setText("NOME");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldNome.setEnabled(false);

        jLabel18.setForeground(new java.awt.Color(255, 255, 0));
        jLabel18.setText("SERVIÇO");
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jComboBoxTipo.setForeground(new java.awt.Color(255, 255, 0));
        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Uniforme", "Folhas de Provas", "Cartões", "Boletim", "Certificado", "Outros" }));

        jLabel14.setForeground(new java.awt.Color(255, 255, 0));
        jLabel14.setText("  Pesquiza");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setForeground(new java.awt.Color(255, 255, 0));
        jLabel19.setText("CLASSE");
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setForeground(new java.awt.Color(255, 255, 0));
        jLabel15.setText("ID");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldClasse.setEnabled(false);

        Contrato.setEnabled(false);

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Imprimir");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtAluPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(txtPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Contrato, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(54, 54, 54))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAluPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Contrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel14)
                        .addComponent(txtPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 16, Short.MAX_VALUE)
                                .addComponent(jLabel20))
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 57, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PROPINAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 0))); // NOI18N

        jTextFieldNuparc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTextFieldNuparc.setForeground(new java.awt.Color(0, 0, 102));
        jTextFieldNuparc.setDisabledTextColor(new java.awt.Color(0, 0, 153));

        btnPagamento.setBackground(new java.awt.Color(102, 102, 102));
        btnPagamento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPagamento.setForeground(new java.awt.Color(255, 255, 255));
        btnPagamento.setText("Efectuar Pagamento");
        btnPagamento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnPagamento.setEnabled(false);
        btnPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagamentoActionPerformed(evt);
            }
        });

        jTextFieldTotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTextFieldTotal.setForeground(new java.awt.Color(0, 0, 102));
        jTextFieldTotal.setText("                     00");
        jTextFieldTotal.setDisabledTextColor(new java.awt.Color(0, 0, 153));
        jTextFieldTotal.setEnabled(false);

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Remover");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("           Nº Parcela");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Multa2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Multa2.setForeground(new java.awt.Color(255, 255, 0));
        Multa2.setText("       Total a Pagar");
        Multa2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        datVenc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        datVenc.setForeground(new java.awt.Color(255, 0, 0));
        datVenc.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        datVenc.setEnabled(false);

        jTextFieldDesconto.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldDesconto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldDesconto.setForeground(new java.awt.Color(255, 0, 0));
        jTextFieldDesconto.setText("            00");
        jTextFieldDesconto.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        jTextFieldDesconto.setEnabled(false);

        jButtonSetarMulta.setBackground(new java.awt.Color(102, 102, 102));
        jButtonSetarMulta.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSetarMulta.setText("Setar Multa/Desconto");
        jButtonSetarMulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonSetarMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetarMultaActionPerformed(evt);
            }
        });

        jTextFieldMulta.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldMulta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextFieldMulta.setForeground(new java.awt.Color(0, 255, 204));
        jTextFieldMulta.setText("              00");
        jTextFieldMulta.setDisabledTextColor(new java.awt.Color(0, 255, 204));
        jTextFieldMulta.setEnabled(false);

        jTextFieldPagamento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTextFieldPagamento.setForeground(new java.awt.Color(0, 204, 51));
        jTextFieldPagamento.setDisabledTextColor(new java.awt.Color(0, 204, 0));
        jTextFieldPagamento.setEnabled(false);
        jTextFieldPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPagamentoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("      Data  deVencimento");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Multa1.setForeground(new java.awt.Color(255, 255, 0));
        Multa1.setText("    Desconto");

        Multa.setForeground(new java.awt.Color(255, 255, 0));
        Multa.setText("           Multa");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("    Data  de Pagamento");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        textCodContrato.setBackground(new java.awt.Color(204, 204, 255));
        textCodContrato.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textCodContrato.setForeground(new java.awt.Color(255, 0, 51));
        textCodContrato.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        textCodContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCodContratoActionPerformed(evt);
            }
        });

        txtCodParcela.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtCodParcela.setForeground(new java.awt.Color(255, 0, 51));
        txtCodParcela.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        txtCodParcela.setEnabled(false);

        ValorParc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ValorParc.setForeground(new java.awt.Color(0, 0, 102));
        ValorParc.setDisabledTextColor(new java.awt.Color(0, 0, 153));
        ValorParc.setEnabled(false);

        txtSituacao.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtSituacao.setForeground(new java.awt.Color(0, 204, 51));
        txtSituacao.setDisabledTextColor(new java.awt.Color(0, 204, 0));
        txtSituacao.setEnabled(false);

        btnBuscar.setBackground(new java.awt.Color(102, 102, 102));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Código do Contrato");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Código da Parcela");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Valor Da Parcela");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Situação da Parcela");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setText("Relatório");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Relatórios/Pagos/Não Pagos");
        jButton4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datVenc, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSetarMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(Multa1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Multa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(jTextFieldPagamento)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textCodContrato))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtCodParcela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ValorParc, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextFieldNuparc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(btnPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Multa2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textCodContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ValorParc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCodParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Multa1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(Multa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSetarMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(datVenc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Multa2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNuparc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagamentoActionPerformed
        // TODO add your handling code here:
        mod.setCodParc(Integer.parseInt(txtCodParcela.getText()));
        try {
            control.BaixarParcela(mod);
        } catch (SQLException ex) {
            Logger.getLogger(BaixaParcelas1.class.getName()).log(Level.SEVERE, null, ex);
        }
        preecherTabela("select*from tbparcelas where id_contrato='" + textCodContrato.getText() + "'order by id_parcela");
        int comfirma = JOptionPane.showConfirmDialog(null, "Confirma a Impressão desta Matricula?", "Atenção", JOptionPane.YES_NO_OPTION);
        btnPagamento.setEnabled(false);
        if (comfirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("contrato", Integer.parseInt(textCodContrato.getText()));
                filtro.put("Num_Parcela", Integer.parseInt(jTextFieldNuparc.getText()));

                net.sf.jasperreports.engine.JasperPrint print = JasperFillManager.fillReport("lib/report/PagamentoParcela.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
                btnPagamento.setEnabled(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnPagamentoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        // TODO add your handling code here:
        // TODO add your handling code here:
        conexao = ModuloConexao.conector();

        int codigo;

        if (!textCodContrato.getText().equals("")) {
            codigo = Integer.parseInt(textCodContrato.getText());
            mod.setCodContr(codigo);
            mod = control.BuscaParcela(mod);
            textCodContrato.setText(String.valueOf(mod.getCodContr()));
            txtCodParcela.setText(String.valueOf(mod.getCodParc()));
            datVenc.setText(mod.getDatavenc());
            ValorParc.setText(String.valueOf(mod.getValor()));
            // jTextFieldPagamento.setText(String.valueOf(mod.getData_pag()));
            preecherTabela("select*from tbparcelas where id_contrato='" + textCodContrato.getText() + "'order by id_parcela");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Informe um número de Parcela Válida!");
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void TabelaBaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaBaixaMouseClicked
        setar_campos();
    }//GEN-LAST:event_TabelaBaixaMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void tblContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblContratoMouseClicked
        // Chamando o método setar campos
        setar_Pagamentos();
    }//GEN-LAST:event_tblContratoMouseClicked

    private void tblContratoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblContratoKeyReleased

    }//GEN-LAST:event_tblContratoKeyReleased

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        pesquisar_contrato();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void txtAluPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAluPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAluPesquisarActionPerformed

    private void txtAluPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAluPesquisarKeyReleased
        // chamando o método pesquiasr alunos
        pesquisar_alunos();
    }//GEN-LAST:event_txtAluPesquisarKeyReleased

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:

        try {
            String sql = ("Select multa,desconto from tbprecos ");

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                multa = rs.getString("multa");
                desconto = rs.getString("desconto");
                // String dataSistemadataSistema.toString.valueOf(vencimento));
                System.out.println(multa);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro de Consulta!\nERRO" + error);
        }


    }//GEN-LAST:event_formInternalFrameOpened

    private void textCodContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodContratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodContratoActionPerformed

    private void jTextFieldPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPagamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPagamentoActionPerformed

    private void jButtonSetarMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetarMultaActionPerformed
        calcularJuros();

    }//GEN-LAST:event_jButtonSetarMultaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        imprimir();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        excluir_Parcelas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
          int comfirma = JOptionPane.showConfirmDialog(null, "Confirma a Impressão desta Matricula?", "Atenção", JOptionPane.YES_NO_OPTION);
        btnPagamento.setEnabled(false);
        if (comfirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("contrato", Integer.parseInt(textCodContrato.getText()));
//                filtro.put("Num_Parcela", Integer.parseInt(jTextFieldNuparc.getText()));

                net.sf.jasperreports.engine.JasperPrint print = JasperFillManager.fillReport("lib/report/PagamentoParc.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
                btnPagamento.setEnabled(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        pesquisarpagamentos();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Contrato;
    private javax.swing.JLabel Multa;
    private javax.swing.JLabel Multa1;
    private javax.swing.JLabel Multa2;
    private javax.swing.JTable TabelaBaixa;
    private javax.swing.JTextField ValorParc;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnPagamento;
    private javax.swing.JTextField datVenc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonSetarMulta;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextFieldClasse;
    public static javax.swing.JTextField jTextFieldDesconto;
    public static javax.swing.JTextField jTextFieldMulta;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNuparc;
    private javax.swing.JTextField jTextFieldPagamento;
    public static javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldValor;
    private javax.swing.JTable tblContrato;
    private javax.swing.JTextField textCodContrato;
    private javax.swing.JTextField txtAluPesquisar;
    private javax.swing.JTextField txtCodParcela;
    private javax.swing.JTextField txtPagamento;
    private javax.swing.JTextField txtSituacao;
    // End of variables declaration//GEN-END:variables

    public List GetDados() {
        List lista = new ArrayList();
        Reltorio print = new Reltorio();

        print.setCodContr(txtCodParcela.getText());
        print.setCodParc(textCodContrato.getText());
        print.setNum_parcela(jTextFieldNuparc.getText());
        print.setDatavenc(datVenc.getText());
        print.setData_pag(jTextFieldPagamento.getText());
        print.setValor(ValorParc.getText());
        print.setVmulta(jTextFieldMulta.getText());
        print.setVdesconto(jTextFieldDesconto.getText());
        print.setTotalpag(jTextFieldTotal.getText());
        lista.add(print);

        return lista;
    }

}
