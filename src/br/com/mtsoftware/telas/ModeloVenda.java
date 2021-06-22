/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Jelson Fernandes
 */
public class ModeloVenda {

    Connection conexao = null;
    ResultSet rs = null;
    //criando variaveis especiais para conexão com o banco
    //prepared statement e resultSet são frameworks do pacote java.sql
    //e servem para preparar eexecutar as instruções SQL
    PreparedStatement pst = null;

    private int Id_Venda;
    private String Data_Venda;
    private float Valor_Venda;
    private String NomeCliente;
    private String NomeProduto;
    private int QuantItem;
    private String Pagamento;

    /**
     * @return the Id_Venda
     */
    public int getId_Venda() {
        return Id_Venda;
    }

    /**
     * @param Id_Venda the Id_Venda to set
     */
    public void setId_Venda(int Id_Venda) {
        this.Id_Venda = Id_Venda;
    }

    /**
     * @return the Data_Venda
     */
    public String getData_Venda() {
        return Data_Venda;
    }

    /**
     * @param Data_Venda the Data_Venda to set
     */
    public void setData_Venda(Date Data_Venda) {
        this.setData_Venda(Data_Venda);
    }

    /**
     * @return the Valor_Venda
     */
    public float getValor_Venda() {
        return Valor_Venda;
    }

    /**
     * @param Valor_Venda the Valor_Venda to set
     */
    public void setValor_Venda(float Valor_Venda) {
        this.Valor_Venda = Valor_Venda;
    }

    /**
     * @return the NomeCliente
     */
    public String getNomeCliente() {
        return NomeCliente;
    }

    /**
     * @param NomeCliente the NomeCliente to set
     */
    public void setNomeCliente(String NomeCliente) {
        this.NomeCliente = NomeCliente;
    }

    /**
     * @return the NomeProduto
     */
    public String getNomeProduto() {
        return NomeProduto;
    }

    /**
     * @param NomeProduto the NomeProduto to set
     */
    public void setNomeProduto(String NomeProduto) {
        this.NomeProduto = NomeProduto;
    }

    /**
     * @return the QuantItem
     */
    public int getQuantItem() {
        return QuantItem;
    }

    /**
     * @param QuantItem the QuantItem to set
     */
    public void setQuantItem(int QuantItem) {
        this.QuantItem = QuantItem;
    }

    /**
     * @param Data_Venda the Data_Venda to set
     */
    public void setData_Venda(String Data_Venda) {
        this.Data_Venda = Data_Venda;
    }

    /**
     * @return the Pagamento
     */
    public String getPagamento() {
        return Pagamento;
    }

    /**
     * @param Pagamento the Pagamento to set
     */
    public void setPagamento(String Pagamento) {
        this.Pagamento = Pagamento;
    }

}
