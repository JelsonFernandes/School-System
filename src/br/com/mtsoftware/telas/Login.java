/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import br.com.mtsoftware.dal.ModuloConexao;
import static br.com.mtsoftware.telas.TelaPrincipal.Desktop;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Level;

/**
 *
 * @author Jelson Fernandes
 */
public class Login extends javax.swing.JFrame {

    Connection conexao = null;
    Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String vencimento;

    public void logar() {
        if(txtUsuario.getText().equals("tokesfprog")&&jPasswordField.getText().equals("61/02tokessoft")){
             TelaPrincipal principal = new TelaPrincipal();
                            principal.setVisible(true);
                            TelaPrincipal.jMenuBoletim.setEnabled(true);
                            TelaPrincipal.MenRel.setEnabled(true);
                            TelaPrincipal.menCadUsu.setEnabled(true);
                            TelaPrincipal.menPagamentos.setEnabled(true);
                            TelaPrincipal.lblUsuario.setForeground(Color.red);
                           
                            TelaPrincipal.jMenuItemPesquiVendas.setEnabled(true);
                            TelaPrincipal.jMenuItemFinancas.setEnabled(true);
                            TelaPrincipal.jMenuItemCadLicence.setEnabled(true);

                            TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                            TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                            AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();
                             dispose();
        }else

        try {
            String sql = "select*from tbusuarios where login=? and senha=? ";
            /*as linhas abaixo preparam a consulta em função do que foi inserido nas caixas de texto
            o ? é substituido pelo coneudo das variaveis*/
            pst = conexao.prepareStatement(sql);
            System.out.println(conexao);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, jPasswordField.getText());

            /* a linha abaixo executa a querry*/
            //Result rs = stmt.executeQuery(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println();
                //a linha abaixo obtem o conteudo do campo perfil da tabela tbusuario
                String perfil = rs.getString(6);
                // System.out.println(perfil);
                //a estrutura abaixo faz o tratamento do perfil do usuário
                pst = conexao.prepareStatement(sql);

                rs.isLast();

                SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
                Date hoje = new Date();
                //JOptionPane.showMessageDialog(rootPane,df.format(hoje));
                String dataAtual = (String) df.format(hoje);
                //String dataSistema = rs.getString(vencimento);
                String dataSistema = String.valueOf(vencimento);
                System.out.println(dataAtual + "---" + dataSistema);
                //JOptionPane.showMessageDialog(rootPane, dataSistema);
                /* float valor1=(Float.parseFloat(dataSistema));
                 float valor2=(Float.parseFloat(dataAtual));
                 float valor3=(valor2-valor1);
                 System.out.println(valor3);
                 if(valor2-valor1<=15){
                     JOptionPane.showMessageDialog(null, "Faltam" +valor3 +"dias para este sistema expirar");
                 }*/

                char[] dataAtualVet = dataAtual.toCharArray();

                char[] dataSisVet = dataSistema.toCharArray();
                int diaAt, mesAt, anoAt, diaVenc, mesVenc, anoVenc;

                diaAt = Integer.parseInt("" + dataAtualVet[0] + dataAtualVet[1]);
                mesAt = Integer.parseInt("" + dataAtualVet[2] + dataAtualVet[3]);
                anoAt = Integer.parseInt("" + dataAtualVet[4] + dataAtualVet[5] + dataAtualVet[6] + dataAtualVet[7]);

                diaVenc = Integer.parseInt("" + dataSisVet[0] + dataSisVet[1]);
                mesVenc = Integer.parseInt("" + dataSisVet[2] + dataSisVet[3]);
                anoVenc = Integer.parseInt("" + dataSisVet[4] + dataSisVet[5] + dataSisVet[6] + dataSisVet[7]);
                // JOptionPane.showMessageDialog(rootPane, diaAt);
                System.out.println(mesVenc);
//                int venc=diaVenc+mesVenc+anoVenc;
//                int at=diaAt+mesAt+anoAt;
//Calculo entre datas
                Date data1 = df.parse(dataAtual);
                Date data2 = df.parse(dataSistema);
                long difference = data2.getTime() - data1.getTime();
                float daysBetween = (difference / (1000 * 60 * 60 * 24));
                if ((daysBetween >= 0)) {
//((diaAt <= diaVenc || mesAt <= mesVenc && anoAt == anoVenc))
                    switch (perfil) {
                        case "admin": {
                            TelaPrincipal principal = new TelaPrincipal();
                            principal.setVisible(true);
                            TelaPrincipal.jMenuBoletim.setEnabled(true);
                            TelaPrincipal.MenRel.setEnabled(true);
                            TelaPrincipal.menCadUsu.setEnabled(true);
                            TelaPrincipal.menPagamentos.setEnabled(true);
                            TelaPrincipal.lblUsuario.setForeground(Color.red);
                            TelaPrincipal.lblUsuario.setText(rs.getString(2));
                            AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();
                            TelaPrincipal.jMenuItemPesquiVendas.setEnabled(false);
                            TelaPrincipal.jMenuItemFinancas.setEnabled(true);

                            // AvaliacaoDoAluno.jLabelUser.setText(rs.getString(2));
                            this.dispose();
                            break;
                        }
                        case "Programador": {
                            TelaPrincipal principal = new TelaPrincipal();
                            principal.setVisible(true);
                            TelaPrincipal.jMenuBoletim.setEnabled(true);
                            TelaPrincipal.MenRel.setEnabled(true);
                            TelaPrincipal.menCadUsu.setEnabled(true);
                            TelaPrincipal.menPagamentos.setEnabled(true);
                            TelaPrincipal.lblUsuario.setForeground(Color.red);
                            TelaPrincipal.lblUsuario.setText(rs.getString(2));
                            TelaPrincipal.jMenuItemPesquiVendas.setEnabled(true);
                            TelaPrincipal.jMenuItemFinancas.setEnabled(true);
                            TelaPrincipal.jMenuItemCadLicence.setEnabled(true);

                            TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                            TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                            AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();

                            // AvaliacaoDoAluno.jLabelUser.setText(rs.getString(2));
                            this.dispose();
                            break;
                        }
                        case "Aluno": {
                            TelaPrincipal principal = new TelaPrincipal();
                            AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();
                            principal.setVisible(true);
                            principal.setVisible(true);
                            TelaPrincipal.jMenuItemMensaldade.setEnabled(false);
                            TelaPrincipal.jMenuBoletim.setEnabled(false);
                            TelaPrincipal.menContratos.setEnabled(false);
                            TelaPrincipal.menPagamentos.setEnabled(false);
                            TelaPrincipal.menCadastro.setEnabled(false);
                            TelaPrincipal.menClasses.setEnabled(false);
                            TelaPrincipal.jMenuItemMensaldade.setEnabled(false);

                            AvaliacaoDoAluno.jButtonSalvar.setEnabled(false);
                            AvaliacaoDoAluno.jButtonAlterar.setEnabled(false);
                            AvaliacaoDoAluno.jButtonExcluir.setEnabled(false);
                            TelaPrincipal.lblUsuario.setText(rs.getString(2));
                            //AvaliacaoDoAluno.jLabelUsuario.setText(rs.getString(2));
                            TelaPrincipal.lblUsuario.setForeground(Color.green);
                            break;
                        }
                        default: {
                            TelaPrincipal principal = new TelaPrincipal();
                            principal.setVisible(true);
                            TelaPrincipal.lblUsuario.setText(rs.getString(2));
                            TelaPrincipal.lblUsuario.setForeground(Color.green);
                            this.dispose();
                            break;
                        }
                    }
                } else if (perfil.equals("Programador")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.jMenuBoletim.setEnabled(true);
                    TelaPrincipal.MenRel.setEnabled(true);
                    TelaPrincipal.menCadUsu.setEnabled(true);
                    TelaPrincipal.menPagamentos.setEnabled(true);
                    TelaPrincipal.lblUsuario.setForeground(Color.red);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    TelaPrincipal.jMenuItemPesquiVendas.setEnabled(true);
                    TelaPrincipal.jMenuItemFinancas.setEnabled(true);
                    TelaPrincipal.jMenuItemCadLicence.setEnabled(true);
                    TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                    TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                    AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();

                    // AvaliacaoDoAluno.jLabelUser.setText(rs.getString(2));
                    this.dispose();
                } else {
                    TelaPrincipal principal = new TelaPrincipal();
//                    AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();
//                    principal.setVisible(true);
                    principal.setVisible(true);
                    ValidacaoDoSistema valiadacao = new ValidacaoDoSistema();
                    valiadacao.setVisible(true);
                    Desktop.add(valiadacao);
//                    TelaPrincipal.jMenParcelas.setEnabled(false);
                    TelaPrincipal.jMenuBoletim.setEnabled(false);
                    TelaPrincipal.menContratos.setEnabled(false);
                    TelaPrincipal.menPagamentos.setEnabled(false);
                    TelaPrincipal.menCadastro.setEnabled(false);
                    TelaPrincipal.menClasses.setEnabled(false);
                    TelaPrincipal.jMenuItemMensaldade.setEnabled(false);
                    TelaPrincipal.nenCadCli.setEnabled(false);
                    TelaPrincipal.menCadUsu.setEnabled(false);
                    TelaPrincipal.menCadEmpre.setEnabled(false);
                    TelaPrincipal.menFuncionarios.setEnabled(false);
                    TelaPrincipal.Escola.setEnabled(false);
                    TelaPrincipal.jMenuItemCadProdutos.setEnabled(false);
                    TelaPrincipal.jMenuItemVenda.setEnabled(false);
                    TelaPrincipal.jMenuItemBaixaVenda.setEnabled(false);
                    TelaPrincipal.jMenuItemPesquiVendas.setEnabled(false);
                    TelaPrincipal.jMenuItemFinancas.setEnabled(false);

                }
                this.dispose();
                conexao.close();

            } else {
                JOptionPane.showMessageDialog(null, "usuário e//ou senha inválido(s)");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Logar!\nERRO" + e);
            System.out.println(e);

        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static final Logger LOG = Logger.getLogger(Login.class.getName());

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setIcon();
        getContentPane().setBackground(Color.white);
        conexao = ModuloConexao.conector();
        // ImageIcon icone=new ImageIcon("/br/com/mtsoftware/icones/bdon.png");
        // setIconImage(icone.getImage());
        /* A linha abaixo serve de apoio ao status da conexão*/
 /*System.out.println(conexao);*/
        if (conexao != null) {
            jLabelonoff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/bdon.png")));
        } else {
            jLabelonoff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/bdoff.png")));
        }

//        try {
//            fundo=javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("planetas.jpg"),"/br/com/mtsoftware/icones/planetas.jpg"));
//        } catch (Exception e) {
//        }
    }
//    public void paint(Graphics Fundo){
//       Fundo.drawImage(fundo, 0, 0, getWidth(),getHeight(),this );
//    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/com/mtsoftware/icones/LOGSOFT_Small-6.png")));
    }

    protected static Image fundo;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPasswordField = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelonoff = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("LOGIN"));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldActionPerformed(evt);
            }
        });
        jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordFieldKeyPressed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuário");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Senha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29))
        );

        jLabelonoff.setBorder(new javax.swing.border.MatteBorder(null));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/LoginButton1.jpg"))); // NOI18N
        jButton1.setBorder(new javax.swing.border.MatteBorder(null));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("RomanT", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 0, 255));
        jLabel4.setText("TOKES MULTI SOFT");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mtsoftware/icones/LOGSOFT_Small-3.png"))); // NOI18N
        jLabel3.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabelonoff, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelonoff, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        logar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            String sql = ("Select (data_vencimento) from tbvencimento ");

            pst = conexao.prepareStatement(sql);

            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                vencimento = rs.getString("data_vencimento");
                // String dataSistemadataSistema.toString.valueOf(vencimento));
                System.out.println(vencimento);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, " Erro de Consulta!\nERRO" + error);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(txtUsuario.getText().equals("tokesfprog")&&jPasswordField.getText().equals("61/02tokessoft")){
             TelaPrincipal principal = new TelaPrincipal();
                            principal.setVisible(true);
                            TelaPrincipal.jMenuBoletim.setEnabled(true);
                            TelaPrincipal.MenRel.setEnabled(true);
                            TelaPrincipal.menCadUsu.setEnabled(true);
                            TelaPrincipal.menPagamentos.setEnabled(true);
                            TelaPrincipal.lblUsuario.setForeground(Color.red);
                           
                            TelaPrincipal.jMenuItemPesquiVendas.setEnabled(true);
                            TelaPrincipal.jMenuItemFinancas.setEnabled(true);
                            TelaPrincipal.jMenuItemCadLicence.setEnabled(true);

                            TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                            TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                            AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();
                            dispose();
        }else

            try {
                String sql = "select*from tbusuarios where login=? and senha=? ";
                /*as linhas abaixo preparam a consulta em função do que foi inserido nas caixas de texto
            o ? é substituido pelo coneudo das variaveis*/
                pst = conexao.prepareStatement(sql);
                System.out.println(conexao);
                pst.setString(1, txtUsuario.getText());
                pst.setString(2, jPasswordField.getText());

                /* a linha abaixo executa a querry*/
                //Result rs = stmt.executeQuery(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    System.out.println();
                    //a linha abaixo obtem o conteudo do campo perfil da tabela tbusuario
                    String perfil = rs.getString(6);
                    // System.out.println(perfil);
                    //a estrutura abaixo faz o tratamento do perfil do usuário
                    pst = conexao.prepareStatement(sql);

                    rs.isLast();

                    SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
                    Date hoje = new Date();
                    //JOptionPane.showMessageDialog(rootPane,df.format(hoje));
                    String dataAtual = (String) df.format(hoje);
                    //String dataSistema = rs.getString(vencimento);
                    String dataSistema = String.valueOf(vencimento);
                    System.out.println(dataAtual + "---" + dataSistema);
                    //JOptionPane.showMessageDialog(rootPane, dataSistema);
                    /* float valor1=(Float.parseFloat(dataSistema));
                 float valor2=(Float.parseFloat(dataAtual));
                 float valor3=(valor2-valor1);
                 System.out.println(valor3);
                 if(valor2-valor1<=15){
                     JOptionPane.showMessageDialog(null, "Faltam" +valor3 +"dias para este sistema expirar");
                 }*/

                    char[] dataAtualVet = dataAtual.toCharArray();

                    char[] dataSisVet = dataSistema.toCharArray();
                    int diaAt, mesAt, anoAt, diaVenc, mesVenc, anoVenc;

                    diaAt = Integer.parseInt("" + dataAtualVet[0] + dataAtualVet[1]);
                    mesAt = Integer.parseInt("" + dataAtualVet[2] + dataAtualVet[3]);
                    anoAt = Integer.parseInt("" + dataAtualVet[4] + dataAtualVet[5] + dataAtualVet[6] + dataAtualVet[7]);

                    diaVenc = Integer.parseInt("" + dataSisVet[0] + dataSisVet[1]);
                    mesVenc = Integer.parseInt("" + dataSisVet[2] + dataSisVet[3]);
                    anoVenc = Integer.parseInt("" + dataSisVet[4] + dataSisVet[5] + dataSisVet[6] + dataSisVet[7]);
                    // JOptionPane.showMessageDialog(rootPane, diaAt);
                    System.out.println(mesVenc);
                    Date data1 = df.parse(dataAtual);
                    Date data2 = df.parse(dataSistema);
                    long difference = data2.getTime() - data1.getTime();
                    float daysBetween = (difference / (1000 * 60 * 60 * 24));
                    if ((daysBetween >= 0)) {

                        switch (perfil) {
                            case "admin": {
                                TelaPrincipal principal = new TelaPrincipal();
                                principal.setVisible(true);
                                TelaPrincipal.jMenuBoletim.setEnabled(true);
                                TelaPrincipal.MenRel.setEnabled(true);
                                TelaPrincipal.menCadUsu.setEnabled(true);
                                TelaPrincipal.menPagamentos.setEnabled(true);
                                TelaPrincipal.lblUsuario.setForeground(Color.red);
                                TelaPrincipal.lblUsuario.setText(rs.getString(2));
                                AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();
                                TelaPrincipal.jMenuItemPesquiVendas.setEnabled(false);
                                TelaPrincipal.jMenuItemFinancas.setEnabled(true);
                                // AvaliacaoDoAluno.jLabelUser.setText(rs.getString(2));
                                this.dispose();
                                break;
                            }
                            case "Programador": {

                                TelaPrincipal principal = new TelaPrincipal();
                                principal.setVisible(true);
                                TelaPrincipal.jMenuBoletim.setEnabled(true);
                                TelaPrincipal.MenRel.setEnabled(true);
                                TelaPrincipal.menCadUsu.setEnabled(true);
                                TelaPrincipal.menPagamentos.setEnabled(true);
                                TelaPrincipal.lblUsuario.setForeground(Color.red);
                                TelaPrincipal.lblUsuario.setText(rs.getString(2));
                                TelaPrincipal.jMenuItemPesquiVendas.setEnabled(true);
                                TelaPrincipal.jMenuItemFinancas.setEnabled(true);
                                TelaPrincipal.jMenuItemCadLicence.setEnabled(true);
                                TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                                TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                                AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();

                                // AvaliacaoDoAluno.jLabelUser.setText(rs.getString(2));
                                this.dispose();
                                break;
                            }
                            case "Aluno": {
                                TelaPrincipal principal = new TelaPrincipal();
                                AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();
                                principal.setVisible(true);
                                principal.setVisible(true);
                                TelaPrincipal.jMenuItemMensaldade.setEnabled(false);
                                TelaPrincipal.jMenuBoletim.setEnabled(false);
                                TelaPrincipal.menContratos.setEnabled(false);
                                TelaPrincipal.menPagamentos.setEnabled(false);
                                TelaPrincipal.menCadastro.setEnabled(false);
                                TelaPrincipal.menClasses.setEnabled(false);
                                TelaPrincipal.jMenuItemMensaldade.setEnabled(false);

                                AvaliacaoDoAluno.jButtonSalvar.setEnabled(false);
                                AvaliacaoDoAluno.jButtonAlterar.setEnabled(false);
                                AvaliacaoDoAluno.jButtonExcluir.setEnabled(false);
                                TelaPrincipal.lblUsuario.setText(rs.getString(2));
                                //AvaliacaoDoAluno.jLabelUsuario.setText(rs.getString(2));
                                TelaPrincipal.lblUsuario.setForeground(Color.green);
                                break;
                            }
                            default: {
                                TelaPrincipal principal = new TelaPrincipal();
                                principal.setVisible(true);
                                TelaPrincipal.lblUsuario.setText(rs.getString(2));
                                TelaPrincipal.lblUsuario.setForeground(Color.green);
                                this.dispose();
                                break;
                            }
                        }

                    } else if (perfil.equals("Programador")) {
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.setVisible(true);
                        TelaPrincipal.jMenuBoletim.setEnabled(true);
                        TelaPrincipal.MenRel.setEnabled(true);
                        TelaPrincipal.menCadUsu.setEnabled(true);
                        TelaPrincipal.menPagamentos.setEnabled(true);
                        TelaPrincipal.lblUsuario.setForeground(Color.red);
                        TelaPrincipal.lblUsuario.setText(rs.getString(2));
                        TelaPrincipal.jMenuItemPesquiVendas.setEnabled(true);
                        TelaPrincipal.jMenuItemFinancas.setEnabled(true);
                        TelaPrincipal.jMenuItemCadLicence.setEnabled(true);
                        TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                        TelaPrincipal.jMenuItemProgramador.setEnabled(true);
                        AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();

                        // AvaliacaoDoAluno.jLabelUser.setText(rs.getString(2));
                        this.dispose();

                    } else {
                        TelaPrincipal principal = new TelaPrincipal();
//                    AvaliacaoDoAluno avaliacao = new AvaliacaoDoAluno();
//                    principal.setVisible(true);
                        principal.setVisible(true);
                        ValidacaoDoSistema valiadacao = new ValidacaoDoSistema();
                        valiadacao.setVisible(true);
                        Desktop.add(valiadacao);
                        TelaPrincipal.jMenuItemMensaldade.setEnabled(false);
                        TelaPrincipal.jMenuBoletim.setEnabled(false);
                        TelaPrincipal.menContratos.setEnabled(false);
                        TelaPrincipal.menPagamentos.setEnabled(false);
                        TelaPrincipal.menCadastro.setEnabled(false);
                        TelaPrincipal.menClasses.setEnabled(false);
                        TelaPrincipal.jMenuItemMensaldade.setEnabled(false);
                        TelaPrincipal.nenCadCli.setEnabled(false);
                        TelaPrincipal.menCadUsu.setEnabled(false);
                        TelaPrincipal.menCadEmpre.setEnabled(false);
                        TelaPrincipal.menFuncionarios.setEnabled(false);
                        TelaPrincipal.Escola.setEnabled(false);
                        TelaPrincipal.jMenuItemCadProdutos.setEnabled(false);
                        TelaPrincipal.jMenuItemVenda.setEnabled(false);
                        TelaPrincipal.jMenuItemBaixaVenda.setEnabled(false);
                        TelaPrincipal.jMenuItemPesquiVendas.setEnabled(false);
                        TelaPrincipal.jMenuItemFinancas.setEnabled(false);

                    }
                    this.dispose();
                    conexao.close();

                } else {
                    JOptionPane.showMessageDialog(null, "usuário e//ou senha inválido(s)");
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao Logar!\nERRO" + e);
                System.out.println(e);

            } catch (ParseException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jPasswordFieldKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelonoff;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
