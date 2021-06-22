/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.dal;

import java.sql.*;

/**
 *
 * @author Jelson Fernandes
 */
public class ModuloConexao {

    /*Metodo responsavel por estabelecer a conexão com o banco*/
    public static Connection conector() {
        java.sql.Connection conexao = null;
        java.sql.Connection conn = null;

        /* A linha abaixo chama o driver*/
//        String driver = "com.mysql.jdbc.Driver";
//        /*Armazenando informações referente ao banco*/
//        String url = "jdbc:mysql://37.59.55.185:3306/finUHKKtOK";
//        String user = "finUHKKtOK";
//        String password = "CLOtcZatSg";
        String driver = "com.mysql.jdbc.Driver";
        /*Armazenando informações referente ao banco*/
        String url = "jdbc:mysql://localhost:3306/multits";
        String user = "root";
        String password = "";

        /*Estabelecendo a conexão com o banco*/
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            /*A linha abaixo serve de apoio ao erro*/
            System.out.println(e);

            return null;
        }
    }
}
