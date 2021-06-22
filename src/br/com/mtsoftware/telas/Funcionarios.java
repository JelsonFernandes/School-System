    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
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
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jelson Fernandes
 */
public class Funcionarios extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    // a linha abaixo cria uma variavel de acordo com o radiobutton selecionando
    private String Sexo;

    /**
     * Creates new form Funcionarios
     */
    public Funcionarios() {
        initComponents();
         getContentPane().setBackground(Color.white); 
        conexao = ModuloConexao.conector();
    }

    private Void adicionar() {
        FileInputStream archivofoto;

        try {
            String sql = "insert into tbdocentes(sexo,nome,numdocumento, datanasci,nacinalidade,endereco, telefone, email, funcao, salario, iniciofuncao, disp1, disp2,disp3,disp4,disp5, disp6,disp7, disp8,disp9 ,disp10, obs,foto,fotoimagem )value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pst = conexao.prepareStatement(sql);
            PreparedStatement pst = conexao.prepareStatement(sql);
            // pst = conexao.prepareStatement(sql);

            //pst.executeQuery();
            pst = conexao.prepareStatement(sql);

            // rs = pst.executeQuery();
            pst.setString(1, Sexo);
            pst.setString(2, jTextFieldNome.getText());
            pst.setString(3, jTextFieldDocumento.getText());
            pst.setString(4, jTextFieldNascimento.getText());

            pst.setString(5, jTextFieldNacinalidade.getText());
            pst.setString(6, jTextFieldEndereco.getText());
            pst.setString(7, jTextFieldTelefone.getText());
            pst.setString(8, jTextFieldEmail.getText());
            pst.setString(9, jTextFieldFuncao.getText());
            pst.setString(10, jTextFieldSalario.getText());
            pst.setString(11, jTextFieldInicio.getText());
            pst.setString(12, jTextFieldD1.getText());
            pst.setString(13, jTextFieldD2.getText());
            pst.setString(14, jTextFieldD3.getText());
            pst.setString(15, jTextFieldD4.getText());
            pst.setString(16, jTextFieldD5.getText());
            pst.setString(17, jTextFieldD6.getText());
            pst.setString(18, jTextFieldD7.getText());
            pst.setString(19, jTextFieldD8.getText());
            pst.setString(20, jTextFieldD9.getText());
            pst.setString(21, jTextFieldD10.getText());
            pst.setString(22, jTextPaneObs.getText());
            pst.setString(23, jTextFieldImagem.getText());
            archivofoto = new FileInputStream(jTextFieldImagem.getText());
            pst.setBinaryStream(24, archivofoto);
//            if (jRadioButtonM.isSelected()) {
//
//                Sexo = "Masculino";
//
//            } else if (jRadioButtonF.isSelected()) {
//
//                Sexo = "Femenino";
//
//            }
            if ((jTextFieldNome.getText().isEmpty()) || (jTextFieldDocumento.getText().isEmpty()) || (jTextFieldTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Funcionário Adicionados com sucesso");
                    pst.setString(1, jTextFieldNome.getText());
                    jTextFieldDocumento.setText(null);
                    jTextFieldNascimento.setText(null);
                    jTextFieldNacinalidade.setText(null);
                    jTextFieldEndereco.setText(null);
                    jTextFieldTelefone.setText("");
                    jTextFieldEmail.setText(null);
                    jTextFieldFuncao.setText(null);
                    jTextFieldSalario.setText(null);
                    jTextFieldInicio.setText(null);
                    jTextFieldD1.setText(null);
                    jTextFieldD2.setText(null);
                    jTextFieldD3.setText(null);
                    jTextFieldD4.setText(null);
                    jTextFieldD5.setText(null);
                    jTextFieldD6.setText(null);
                    jTextFieldD7.setText(null);
                    jTextFieldD8.setText(null);
                    jTextFieldD9.setText(null);
                    jTextFieldD10.setText(null);
                    jTextPaneObs.setText(null);
                    jTextFieldImagem.setText(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;

    }

    private void pesquisar_alunos() {
        FileInputStream archivofoto;
        String sql = "select id_docente as ID,nome as Nome, numdocumento as Docomento, datanasci as Nascimento, sexo as Sexo, nacinalidade as Nacionalidade, telefone as Fone, funcao as Função, salario as Salário, foto as StockFoto,fotoimagem as Foto from tbdocentes where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteudo da caixa de pesquisa para o interroga
            // atenção a porcentagem que é a continuação da string sql
            pst.setString(1, jTextFieldPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar
            jTableFuncionarios.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
//método para setar campos

    public void setar_campos() {
        try {
            FileInputStream archivofoto = null;
            int setar = jTableFuncionarios.getSelectedRow();
            jTextFieldID.setText(jTableFuncionarios.getModel().getValueAt(setar, 0).toString());
            jTextFieldNome.setText(jTableFuncionarios.getModel().getValueAt(setar, 1).toString());
            jTextFieldNascimento.setText(jTableFuncionarios.getModel().getValueAt(setar, 2).toString());
            jTextFieldDocumento.setText(jTableFuncionarios.getModel().getValueAt(setar, 3).toString());
            jTextFieldNacinalidade.setText(jTableFuncionarios.getModel().getValueAt(setar, 5).toString());
            jTextFieldTelefone.setText(jTableFuncionarios.getModel().getValueAt(setar, 6).toString());
            jTextFieldFuncao.setText(jTableFuncionarios.getModel().getValueAt(setar, 7).toString());
            jTextFieldSalario.setText(jTableFuncionarios.getModel().getValueAt(setar, 8).toString());
            jTextFieldImagem.setText(jTableFuncionarios.getModel().getValueAt(setar, 9).toString());

            archivofoto = new FileInputStream(jTextFieldImagem.getText());
            jLabelFoto.setLabelFor(this);
            Image foto = getToolkit().createImage(jTextFieldImagem.getText());
            foto = foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            jLabelFoto.setIcon(new ImageIcon(foto));
            jLabelFoto.setIcon((Icon) jTableFuncionarios.getModel().getValueAt(10, 100));

            pst.setBinaryStream(11, archivofoto);
            jButtonSalvar.setEnabled(false);
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void pesquisar_aluno() {
        // a linha abaixo cria uma caixa de entrada do tipo JOption Pane
        String num_aluno = JOptionPane.showInputDialog("Número de Funcionário");
        String sql = "select*from tbdocentes where id_docente=" + num_aluno;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                FileInputStream archivofoto = null;
                Object file = null;
                // File file = archivo.getSelectedFile();
/**/
                jTextFieldID.setText(rs.getString(1));
                jTextFieldNome.setText(rs.getString(3));
                jTextFieldDocumento.setText(rs.getString(4));
                jTextFieldNascimento.setText(rs.getString(5));
                jTextFieldNacinalidade.setText(rs.getString(6));
                jTextFieldEndereco.setText(rs.getString(7));
                jTextFieldTelefone.setText(rs.getString(8));
                jTextFieldEmail.setText(rs.getString(9));
                jTextFieldFuncao.setText(rs.getString(10));
                jTextFieldSalario.setText(rs.getString(11));
                jTextFieldInicio.setText(rs.getString(12));
                jTextFieldD1.setText(rs.getString(13));
                jTextFieldD2.setText(rs.getString(14));
                jTextFieldD3.setText(rs.getString(15));
                jTextFieldD4.setText(rs.getString(16));
                jTextFieldD5.setText(rs.getString(17));
                jTextFieldD6.setText(rs.getString(18));
                jTextFieldD7.setText(rs.getString(19));
                jTextFieldD8.setText(rs.getString(20));
                jTextFieldD9.setText(rs.getString(21));
                jTextFieldD10.setText(rs.getString(22));
                jTextPaneObs.setText(rs.getString(23));
                jTextFieldImagem.setText(rs.getString(24));

                //evitando problemas
                jButtonSalvar.setEnabled(false);
                // txtAluPesquisar.setEnabled(false);
                //  tblContrato.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não Cadastrado");
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Nº de Cadastro Inválido");
            // System.out.println(e);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }

    }

//criando o método para alterar dados do usuário
    private void alterar() {
        String sql = "Update tbdocentes set sexo=?,nome=?,numdocumento=?, datanasci=?,nacinalidade=?,endereco=?, telefone=?, email=?, funcao=?, salario=?, iniciofuncao=?, disp1=?, disp2=?,disp3=?,disp4=?,disp5=?, disp6=?,disp7=?, disp8=?,disp9=? ,disp10=?, obs=?,foto=?,fotoimagem=? where id_docente=?";
        try {
            FileInputStream archivofoto;
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Sexo);
            pst.setString(2, jTextFieldNome.getText());
            pst.setString(3, jTextFieldDocumento.getText());
            pst.setString(4, jTextFieldNascimento.getText());

            pst.setString(5, jTextFieldNacinalidade.getText());
            pst.setString(6, jTextFieldEndereco.getText());
            pst.setString(7, jTextFieldTelefone.getText());
            pst.setString(8, jTextFieldEmail.getText());
            pst.setString(9, jTextFieldFuncao.getText());
            pst.setString(10, jTextFieldSalario.getText());
            pst.setString(11, jTextFieldInicio.getText());
            pst.setString(12, jTextFieldD1.getText());
            pst.setString(13, jTextFieldD2.getText());
            pst.setString(14, jTextFieldD3.getText());
            pst.setString(15, jTextFieldD4.getText());
            pst.setString(16, jTextFieldD5.getText());
            pst.setString(17, jTextFieldD6.getText());
            pst.setString(18, jTextFieldD7.getText());
            pst.setString(19, jTextFieldD8.getText());
            pst.setString(20, jTextFieldD9.getText());
            pst.setString(21, jTextFieldD10.getText());
            pst.setString(22, jTextPaneObs.getText());
            pst.setString(23, jTextFieldImagem.getText());
            archivofoto = new FileInputStream(jTextFieldImagem.getText());
            pst.setBinaryStream(24, archivofoto);
            pst.setString(25, jTextFieldID.getText());

            if ((jTextFieldNome.getText().isEmpty()) || (jTextFieldDocumento.getText().isEmpty()) || (jTextFieldTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preecha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Funcionário Adicionados com sucesso");
                    jTextFieldNome.setText(null);
                    jTextFieldDocumento.setText(null);
                    jTextFieldNascimento.setText(null);
                    jTextFieldNacinalidade.setText(null);
                    jTextFieldEndereco.setText(null);
                    jTextFieldTelefone.setText("");
                    jTextFieldEmail.setText(null);
                    jTextFieldFuncao.setText(null);
                    jTextFieldSalario.setText(null);
                    jTextFieldInicio.setText(null);
                    jTextFieldD1.setText(null);
                    jTextFieldD2.setText(null);
                    jTextFieldD3.setText(null);
                    jTextFieldD4.setText(null);
                    jTextFieldD5.setText(null);
                    jTextFieldD6.setText(null);
                    jTextFieldD7.setText(null);
                    jTextFieldD8.setText(null);
                    jTextFieldD9.setText(null);
                    jTextFieldD10.setText(null);
                    jTextPaneObs.setText(null);
                    jTextFieldImagem.setText(null);

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
            String sql = "delete from tbdocentes where id_docente=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jTextFieldID.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, " Aluno removido com sucesso");
                    jTextFieldNome.setText(null);
                    jTextFieldDocumento.setText(null);
                    jTextFieldNascimento.setText(null);
                    jTextFieldNacinalidade.setText(null);
                    jTextFieldEndereco.setText(null);
                    jTextFieldTelefone.setText("");
                    jTextFieldEmail.setText(null);
                    jTextFieldFuncao.setText(null);
                    jTextFieldSalario.setText(null);
                    jTextFieldInicio.setText(null);
                    jTextFieldD1.setText(null);
                    jTextFieldD2.setText(null);
                    jTextFieldD3.setText(null);
                    jTextFieldD4.setText(null);
                    jTextFieldD5.setText(null);
                    jTextFieldD6.setText(null);
                    jTextFieldD7.setText(null);
                    jTextFieldD8.setText(null);
                    jTextFieldD9.setText(null);
                    jTextFieldD10.setText(null);
                    jTextPaneObs.setText(null);
                    jTextFieldImagem.setText(null);

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jLabelFoto = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldD1 = new javax.swing.JTextField();
        jTextFieldD2 = new javax.swing.JTextField();
        jTextFieldD3 = new javax.swing.JTextField();
        jTextFieldD4 = new javax.swing.JTextField();
        jTextFieldD5 = new javax.swing.JTextField();
        jTextFieldD6 = new javax.swing.JTextField();
        jTextFieldD7 = new javax.swing.JTextField();
        jTextFieldD8 = new javax.swing.JTextField();
        jTextFieldD9 = new javax.swing.JTextField();
        jTextFieldD10 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneObs = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableFuncionarios = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldFuncao = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldSalario = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldInicio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldTelefone = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelNacionalidade = new javax.swing.JLabel();
        jTextFieldNacinalidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldEndereco = new javax.swing.JTextField();
        jTextFieldImagem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDocumento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNascimento = new javax.swing.JTextField();
        jButtonSelecionar = new javax.swing.JButton();

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

        jLabel1.setText("  ID");

        jTextFieldID.setEnabled(false);

        jLabelFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "DISCIPLINAS"));

        jLabel16.setText("1");

        jLabel17.setText("2");

        jLabel18.setText("3");

        jLabel19.setText("4");

        jLabel20.setText("5");

        jLabel21.setText("6");

        jLabel22.setText("7");

        jLabel23.setText("8");

        jLabel24.setText("9");

        jLabel25.setText("10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldD3, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldD2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(14, 14, 14)
                        .addComponent(jTextFieldD1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldD4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldD5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldD6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldD7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldD8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldD10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldD9)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextFieldD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jTextFieldD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldD6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextFieldD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextFieldD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldD9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextFieldD10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane2.setViewportView(jTextPaneObs);

        jTableFuncionarios.setBackground(new java.awt.Color(255, 255, 204));
        jTableFuncionarios.setForeground(new java.awt.Color(51, 51, 255));
        jTableFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableFuncionarios);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/search.png"))); // NOI18N

        jTextFieldPesquisar.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarKeyReleased(evt);
            }
        });

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/cadastro.png"))); // NOI18N
        jButtonSalvar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonSalvar.setPreferredSize(new java.awt.Dimension(73, 40));
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Alterar.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.setPreferredSize(new java.awt.Dimension(73, 40));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Pesquisar (2).png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton4.setPreferredSize(new java.awt.Dimension(73, 40));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/remover.png"))); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton5.setPreferredSize(new java.awt.Dimension(73, 40));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DADOS PESSOAIS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 0))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 0));
        jLabel11.setText(" FUNÇÃO");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldFuncao.setBackground(new java.awt.Color(240, 240, 240));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 0));
        jLabel12.setText("SALÁRIO");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldSalario.setBackground(new java.awt.Color(240, 240, 240));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 0));
        jLabel14.setText("INICIO");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldInicio.setBackground(new java.awt.Color(240, 240, 240));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 0));
        jLabel10.setText("   EMAIL");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldTelefone.setBackground(new java.awt.Color(240, 240, 240));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 0));
        jLabel13.setText("TELEFONE");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldEmail.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });

        jLabelNacionalidade.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelNacionalidade.setForeground(new java.awt.Color(255, 255, 0));
        jLabelNacionalidade.setText("NACIONALIDADE");
        jLabelNacionalidade.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldNacinalidade.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldNacinalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNacinalidadeActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText("ENDEREÇO");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldEndereco.setBackground(new java.awt.Color(240, 240, 240));

        jTextFieldImagem.setBackground(new java.awt.Color(240, 240, 240));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("IMAGEM");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("NOME");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldNome.setBackground(new java.awt.Color(240, 240, 240));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Nº de documento");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldDocumento.setBackground(new java.awt.Color(240, 240, 240));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("DATA DE NASCIMENTO");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldNascimento.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldTelefone)
                                    .addComponent(jTextFieldDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldNascimento, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextFieldImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextFieldEmail)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelNacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldNacinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldFuncao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel14)
                        .addGap(96, 96, 96)))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNacionalidade)
                    .addComponent(jTextFieldNacinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldInicio)
                            .addComponent(jLabel11))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldSalario)
                        .addComponent(jLabel14)))
                .addContainerGap())
        );

        jButtonSelecionar.setText("SELECIONAR FOTO");
        jButtonSelecionar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(93, 93, 93)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(78, 78, 78)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(88, 88, 88)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                .addGap(33, 33, 33)
                                .addComponent(jTextFieldID, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSelecionar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addComponent(jLabelFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSelecionar))
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNacinalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNacinalidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNacinalidadeActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarActionPerformed
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
            jTextFieldImagem.setText(String.valueOf(file));
            Image foto = getToolkit().createImage(jTextFieldImagem.getText());
            foto = foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            jLabelFoto.setIcon(new ImageIcon(foto));

        }


    }//GEN-LAST:event_jButtonSelecionarActionPerformed

    private void jTextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar_alunos();
    }//GEN-LAST:event_jTextFieldPesquisarKeyReleased

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        /* jScrollPane1.setPreferredSize(new Dimension(805, 680));
       jScrollPane1.setLayout(new FlowLayout(0));
        jScrollPane1.setBounds(50, 50, 650, 450);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportBorder(BorderFactory.createLoweredSoftBevelBorder());
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setViewportView(jScrollPane1);
        getContentPane().add(jScrollPane1);
         */
    }//GEN-LAST:event_formInternalFrameOpened

    private void jTableFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFuncionariosMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_jTableFuncionariosMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        pesquisar_aluno();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSelecionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFoto;
    private javax.swing.JLabel jLabelNacionalidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableFuncionarios;
    private javax.swing.JTextField jTextFieldD1;
    private javax.swing.JTextField jTextFieldD10;
    private javax.swing.JTextField jTextFieldD2;
    private javax.swing.JTextField jTextFieldD3;
    private javax.swing.JTextField jTextFieldD4;
    private javax.swing.JTextField jTextFieldD5;
    private javax.swing.JTextField jTextFieldD6;
    private javax.swing.JTextField jTextFieldD7;
    private javax.swing.JTextField jTextFieldD8;
    private javax.swing.JTextField jTextFieldD9;
    private javax.swing.JTextField jTextFieldDocumento;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldFuncao;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldImagem;
    private javax.swing.JTextField jTextFieldInicio;
    private javax.swing.JTextField jTextFieldNacinalidade;
    private javax.swing.JTextField jTextFieldNascimento;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPesquisar;
    private javax.swing.JTextField jTextFieldSalario;
    private javax.swing.JTextField jTextFieldTelefone;
    private javax.swing.JTextPane jTextPaneObs;
    // End of variables declaration//GEN-END:variables
}
