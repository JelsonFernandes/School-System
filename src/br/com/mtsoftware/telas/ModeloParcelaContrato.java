package br.com.mtsoftware.telas;

import java.sql.Date;

/**
 *
 * @author Jelson Fernandes
 */
public class ModeloParcelaContrato {

    /**
     * @return the numero_parcela
     */
    public int getNumero_parcela() {
        return Numero_parcela;
    }

    /**
     * @param numero_parcela the numero_parcela to set
     */
    public void setNumero_parcela(int numero_parcela) {
        this.Numero_parcela = numero_parcela;
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

    private int id_contrato;
    private int Numero_parcela;
    private float valor_contrato;
    private float valor_total;
    private float Valorparc;
    private String data_venc;
   

    /**
     * @return the valor_contrato
     */
    public float getValor_contrato() {
        return valor_contrato;
    }

    /**
     * @param valor_contrato the valor_contrato to set
     */
    public void setValor_contrato(float valor_contrato) {
        this.valor_contrato = valor_contrato;
    }

    /**
     * @return the quantidade_parcelas
     */
    public int getQuantidade_parcelas() {
        return getNumero_parcela();
    }

    /**
     * @param quantidade_parcelas the quantidade_parcelas to set
     */
    public void setQuantidade_parcelas(int quantidade_parcelas) {
        this.setNumero_parcela(quantidade_parcelas);
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
     * @return the data_priparce
     */
    public String getData_priparce() {
        return getData_venc();
    }

    /**
     * @param data_priparce the data_priparce to set
     */
    public void setData_priparce(String data_priparce) {
        this.setData_venc(data_priparce);
    }

    /**
     * @return the id_contrato
     */
    public int getId_contrato() {
        return id_contrato;
    }

    /**
     * @param id_contrato the id_contrato to set
     */
    public void setId_contrato(int id_contrato) {
        this.id_contrato = id_contrato;
    }

    Date getData_venc(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
