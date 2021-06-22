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
import javax.swing.JOptionPane;

/**
 *
 * @author Jelson Fernandes
 */
public class ClassesDisciplinas extends javax.swing.JInternalFrame {

    Connection conexao = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form ClassesDisciplinas
     */
    public ClassesDisciplinas() {
        initComponents();
        getContentPane().setBackground(new Color(0,191,255));
        conexao = ModuloConexao.conector();
    }

    private void consultar() {
        String num_classe = JOptionPane.showInputDialog("Número de Classe");
        String sql = "select *from tbdiscipclasses where id_classe=" + num_classe;//Passou para disciplinas
        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

            if (rs.next()) {
                Id.setText(rs.getString(1));
                Classe.setText(rs.getString(2));
                D1.setText(rs.getString(3));
                D2.setText(rs.getString(4));
                D3.setText(rs.getString(5));
                D4.setText(rs.getString(6));
                D5.setText(rs.getString(7));
                D6.setText(rs.getString(8));
                D7.setText(rs.getString(9));
                D8.setText(rs.getString(10));
                D9.setText(rs.getString(11));
                D10.setText(rs.getString(12));
                D11.setText(rs.getString(13));
                D12.setText(rs.getString(14));
                D13.setText(rs.getString(15));
                D14.setText(rs.getString(16));
                D15.setText(rs.getString(17));
                D16.setText(rs.getString(18));
                D17.setText(rs.getString(19));
                D18.setText(rs.getString(20));
                D19.setText(rs.getString(21));
                D20.setText(rs.getString(22));
//                JNotaRep.setText(rs.getString(23));
//                JNotaRecurso.setText(rs.getString(24));
//                JNotaRepr.setText(rs.getString(25));

            } else {
                JOptionPane.showMessageDialog(null, "Disciplina não cadastrada");
                //as linhas abaixo limpam os campos

                D1.setText(null);
                D2.setText(null);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private Void adicionar() {
        String sql = "insert into tbdiscipclasses( classe, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20 )value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, Classe.getText());
            pst.setString(2, D1.getText());
            pst.setString(3, D2.getText());
            pst.setString(4, D3.getText());
            pst.setString(5, D4.getText());
            pst.setString(6, D5.getText());
            pst.setString(7, D6.getText());
            pst.setString(8, D7.getText());
            pst.setString(9, D8.getText());
            pst.setString(10, D9.getText());
            pst.setString(11, D10.getText());
            pst.setString(12, D11.getText());
            pst.setString(13, D12.getText());
            pst.setString(14, D13.getText());
            pst.setString(15, D14.getText());
            pst.setString(16, D15.getText());
            pst.setString(17, D16.getText());
            pst.setString(18, D17.getText());
            pst.setString(19, D18.getText());
            pst.setString(20, D19.getText());
            pst.setString(21, D20.getText());
//            pst.setString(22, JNotaRep.getText());
//            pst.setString(23, JNotaRecurso.getText());
//            pst.setString(24, JNotaRepr.getText());

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Disciplina adicionada com sucesso");
                Id.setText(null);

                Classe.setText(null);
                D1.setText(null);
                D2.setText(null);
                D3.setText(null);
                D4.setText(null);
                D5.setText(null);
                D6.setText(null);
                D7.setText(null);
                D8.setText(null);
                D9.setText(null);
                D10.setText(null);
                D11.setText(null);
                D12.setText(null);
                D13.setText(null);
                D14.setText(null);
                D15.setText(null);
                D16.setText(null);
                D17.setText(null);
                D18.setText(null);
                D19.setText(null);
                D20.setText(null);
//                JNotaRep.setText(null);
//                JNotaRecurso.setText(null);
//                JNotaRepr.setText(null);

            }
            //a linha abaixo atualiza a tabela usuário com os dados do formulário
            //a estrutura abaixo é usada para confirmar a alteração dos dados da tabela

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    private void alterar() {
        String sql = "Update tbdiscipclasses set  classe=?, d1=?, d2=?, d3=?, d4=?, d5=?, d6=?, d7=?, d8=?, d9=?, d10=? ,d11=?, d12=?, d13=?, d14=?, d15=?, d16=?, d17=?, d18=?, d19=?, d20=?  where id_classe=?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, Classe.getText());
            pst.setString(2, D1.getText());
            pst.setString(3, D2.getText());
            pst.setString(4, D3.getText());
            pst.setString(5, D4.getText());
            pst.setString(6, D5.getText());
            pst.setString(7, D6.getText());
            pst.setString(8, D7.getText());
            pst.setString(9, D8.getText());
            pst.setString(10, D9.getText());
            pst.setString(11, D10.getText());
            pst.setString(12, D11.getText());
            pst.setString(13, D12.getText());
            pst.setString(14, D13.getText());
            pst.setString(15, D14.getText());
            pst.setString(16, D15.getText());
            pst.setString(17, D16.getText());
            pst.setString(18, D17.getText());
            pst.setString(19, D18.getText());
            pst.setString(20, D19.getText());
            pst.setString(21, D20.getText());
//            pst.setString(22, JNotaRep.getText());
//            pst.setString(23, JNotaRecurso.getText());
//            pst.setString(24, JNotaRepr.getText());
//            pst.setString(25, Id.getText());

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Disciplina alterada com sucesso");
                Id.setText(null);

                Classe.setText(null);
                D1.setText(null);
                D2.setText(null);
                D3.setText(null);
                D4.setText(null);
                D5.setText(null);
                D6.setText(null);
                D7.setText(null);
                D8.setText(null);
                D9.setText(null);
                D10.setText(null);
                D11.setText(null);
                D12.setText(null);
                D13.setText(null);
                D14.setText(null);
                D15.setText(null);
                D16.setText(null);
                D17.setText(null);
                D18.setText(null);
                D19.setText(null);
                D20.setText(null);
//                JNotaRep.setText(null);
//                JNotaRecurso.setText(null);
//                JNotaRepr.setText(null);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //método responsavel pela remoção de usuários
    private void remover() {
        // a estrutura abaixo confirma a remoção do usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta Classe?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbdiscipclasses where id_classe=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, Id.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, " Classe removida com sucesso");
                    Id.setText(null);

                    Classe.setText(null);
                    D1.setText(null);
                    D2.setText(null);
                    D3.setText(null);
                    D4.setText(null);
                    D5.setText(null);
                    D6.setText(null);
                    D7.setText(null);
                    D8.setText(null);
                    D9.setText(null);
                    D10.setText(null);
                    D11.setText(null);
                    D12.setText(null);
                    D13.setText(null);
                    D14.setText(null);
                    D15.setText(null);
                    D16.setText(null);
                    D17.setText(null);
                    D18.setText(null);
                    D19.setText(null);
                    D20.setText(null);
//                    JNotaRep.setText(null);
//                    JNotaRecurso.setText(null);
//                    JNotaRepr.setText(null);

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

        jLabel3 = new javax.swing.JLabel();
        Id = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Salvar1 = new javax.swing.JButton();
        Consultar1 = new javax.swing.JButton();
        Alterar1 = new javax.swing.JButton();
        Remover1 = new javax.swing.JButton();
        Classe = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        D1 = new javax.swing.JTextField();
        D2 = new javax.swing.JTextField();
        D3 = new javax.swing.JTextField();
        D4 = new javax.swing.JTextField();
        D5 = new javax.swing.JTextField();
        D6 = new javax.swing.JTextField();
        D7 = new javax.swing.JTextField();
        D8 = new javax.swing.JTextField();
        D9 = new javax.swing.JTextField();
        D10 = new javax.swing.JTextField();
        D11 = new javax.swing.JTextField();
        D12 = new javax.swing.JTextField();
        D13 = new javax.swing.JTextField();
        D14 = new javax.swing.JTextField();
        D15 = new javax.swing.JTextField();
        D18 = new javax.swing.JTextField();
        D16 = new javax.swing.JTextField();
        D17 = new javax.swing.JTextField();
        D19 = new javax.swing.JTextField();
        D20 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 204, 255));
        setClosable(true);
        setMaximizable(true);
        setTitle("CADASTR DE  CLASSES & DISCIPLINAS");
        setPreferredSize(new java.awt.Dimension(807, 580));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Classe");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Id.setBackground(new java.awt.Color(204, 204, 255));
        Id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Id.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Salvar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/cadastro.png"))); // NOI18N
        Salvar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Salvar1.setPreferredSize(new java.awt.Dimension(55, 63));
        Salvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Salvar1ActionPerformed(evt);
            }
        });

        Consultar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Pesquisar (2).png"))); // NOI18N
        Consultar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Consultar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Consultar1ActionPerformed(evt);
            }
        });

        Alterar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/Alterar.png"))); // NOI18N
        Alterar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Alterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alterar1ActionPerformed(evt);
            }
        });

        Remover1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/remover.png"))); // NOI18N
        Remover1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Remover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remover1ActionPerformed(evt);
            }
        });

        Classe.setBackground(new java.awt.Color(204, 204, 255));
        Classe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Classe.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 240));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("DISCIPLINAS"));

        D1.setBackground(new java.awt.Color(204, 204, 255));
        D1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D1.setForeground(new java.awt.Color(255, 255, 255));

        D2.setBackground(new java.awt.Color(204, 204, 255));
        D2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D2.setForeground(new java.awt.Color(255, 255, 255));

        D3.setBackground(new java.awt.Color(204, 204, 255));
        D3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D3.setForeground(new java.awt.Color(255, 255, 255));

        D4.setBackground(new java.awt.Color(204, 204, 255));
        D4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D4.setForeground(new java.awt.Color(255, 255, 255));

        D5.setBackground(new java.awt.Color(204, 204, 255));
        D5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D5.setForeground(new java.awt.Color(255, 255, 255));

        D6.setBackground(new java.awt.Color(204, 204, 255));
        D6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D6.setForeground(new java.awt.Color(255, 255, 255));

        D7.setBackground(new java.awt.Color(204, 204, 255));
        D7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D7.setForeground(new java.awt.Color(255, 255, 255));

        D8.setBackground(new java.awt.Color(204, 204, 255));
        D8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D8.setForeground(new java.awt.Color(255, 255, 255));

        D9.setBackground(new java.awt.Color(204, 204, 255));
        D9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D9.setForeground(new java.awt.Color(255, 255, 255));

        D10.setBackground(new java.awt.Color(204, 204, 255));
        D10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D10.setForeground(new java.awt.Color(255, 255, 255));

        D11.setBackground(new java.awt.Color(204, 204, 255));
        D11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D11.setForeground(new java.awt.Color(255, 255, 255));

        D12.setBackground(new java.awt.Color(204, 204, 255));
        D12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D12.setForeground(new java.awt.Color(255, 255, 255));

        D13.setBackground(new java.awt.Color(204, 204, 255));
        D13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D13.setForeground(new java.awt.Color(255, 255, 255));

        D14.setBackground(new java.awt.Color(204, 204, 255));
        D14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D14.setForeground(new java.awt.Color(255, 255, 255));

        D15.setBackground(new java.awt.Color(204, 204, 255));
        D15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D15.setForeground(new java.awt.Color(255, 255, 255));

        D18.setBackground(new java.awt.Color(204, 204, 255));
        D18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D18.setForeground(new java.awt.Color(255, 255, 255));

        D16.setBackground(new java.awt.Color(204, 204, 255));
        D16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D16.setForeground(new java.awt.Color(255, 255, 255));

        D17.setBackground(new java.awt.Color(204, 204, 255));
        D17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D17.setForeground(new java.awt.Color(255, 255, 255));

        D19.setBackground(new java.awt.Color(204, 204, 255));
        D19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D19.setForeground(new java.awt.Color(255, 255, 255));

        D20.setBackground(new java.awt.Color(204, 204, 255));
        D20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D20.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(D16, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(D1)
                    .addComponent(D6)
                    .addComponent(D11))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D17, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(D2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(D7, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(D12, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(D3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(D8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(D13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(D18, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D4, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(D9, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(D14, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(D19, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D5, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(D10, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(D15, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(D20, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(D11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(D16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D20, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(393, 393, 393)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Classe, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Salvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(Consultar1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addGap(87, 87, 87)
                                .addComponent(Alterar1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                .addGap(120, 120, 120)
                                .addComponent(Remover1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Classe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Alterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salvar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Consultar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Remover1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Salvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Salvar1ActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_Salvar1ActionPerformed

    private void Consultar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Consultar1ActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_Consultar1ActionPerformed

    private void Alterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alterar1ActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_Alterar1ActionPerformed

    private void Remover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remover1ActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_Remover1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar1;
    private javax.swing.JTextField Classe;
    private javax.swing.JButton Consultar1;
    private javax.swing.JTextField D1;
    private javax.swing.JTextField D10;
    private javax.swing.JTextField D11;
    private javax.swing.JTextField D12;
    private javax.swing.JTextField D13;
    private javax.swing.JTextField D14;
    private javax.swing.JTextField D15;
    private javax.swing.JTextField D16;
    private javax.swing.JTextField D17;
    private javax.swing.JTextField D18;
    private javax.swing.JTextField D19;
    private javax.swing.JTextField D2;
    private javax.swing.JTextField D20;
    private javax.swing.JTextField D3;
    private javax.swing.JTextField D4;
    private javax.swing.JTextField D5;
    private javax.swing.JTextField D6;
    private javax.swing.JTextField D7;
    private javax.swing.JTextField D8;
    private javax.swing.JTextField D9;
    private javax.swing.JTextField Id;
    private javax.swing.JButton Remover1;
    private javax.swing.JButton Salvar1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
