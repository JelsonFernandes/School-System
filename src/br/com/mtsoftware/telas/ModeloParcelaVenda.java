/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

/**
 *
 * @author Jelson Fernandes
 */
class ModeloParcelaVenda {
    
    
    private int id_Venda;
    private int Numero_parcela;
    private float valor_Venda;
    private float valor_total;
    private float Valorparc;
    private String data_venc;

    /**
     * @return the id_Venda
     */
    public int getId_Venda() {
        return id_Venda;
    }

    /**
     * @param id_Venda the id_Venda to set
     */
    public void setId_Venda(int id_Venda) {
        this.id_Venda = id_Venda;
    }

    /**
     * @return the Numero_parcela
     */
    public int getNumero_parcela() {
        return Numero_parcela;
    }

    /**
     * @param Numero_parcela the Numero_parcela to set
     */
    public void setNumero_parcela(int Numero_parcela) {
        this.Numero_parcela = Numero_parcela;
    }

    /**
     * @return the valor_Venda
     */
    public float getValor_Venda() {
        return valor_Venda;
    }

    /**
     * @param valor_Venda the valor_Venda to set
     */
    public void setValor_Venda(float valor_Venda) {
        this.valor_Venda = valor_Venda;
    }

    /**
     * @return the valor_total
     */
    public float getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * @return the Valorparc
     */
    public float getValorparc() {
        return Valorparc;
    }

    /**
     * @param Valorparc the Valorparc to set
     */
    public void setValorparc(float Valorparc) {
        this.Valorparc = Valorparc;
    }

    /**
     * @return the data_venc
     */
    public String getData_venc() {
        return data_venc;
    }

    /**
     * @param data_venc the data_venc to set
     */
    public void setData_venc(String data_venc) {
        this.data_venc = data_venc;
    }
    
}
