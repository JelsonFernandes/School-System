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
public class ModeloBaixaVenda {
    private int codParc;
   private String datavenc;
   private int CodContr;
   private float valor;
   private String data_pag;
   private float vmulta;
   private float vdesconto;
   private float totalpag;
   private int num_parcela;

    /**
     * @return the codParc
     */
    public int getCodParc() {
        return codParc;
    }

    /**
     * @param codParc the codParc to set
     */
    public void setCodParc(int codParc) {
        this.codParc = codParc;
    }

    /**
     * @return the datavenc
     */
    public String getDatavenc() {
        return datavenc;
    }

    /**
     * @param datavenc the datavenc to set
     */
    public void setDatavenc(String datavenc) {
        this.datavenc = datavenc;
    }

    /**
     * @return the CodContr
     */
    public int getCodContr() {
        return CodContr;
    }

    /**
     * @param CodContr the CodContr to set
     */
    public void setCodContr(int CodContr) {
        this.CodContr = CodContr;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * @return the data_pag
     */
    public String getData_pag() {
        return data_pag;
    }

    /**
     * @param data_pag the data_pag to set
     */
    public void setData_pag(String data_pag) {
        this.data_pag = data_pag;
    }

    /**
     * @return the vmulta
     */
    public float getVmulta() {
        return vmulta;
    }

    /**
     * @param vmulta the vmulta to set
     */
    public void setVmulta(float vmulta) {
        this.vmulta = vmulta;
    }

    /**
     * @return the vdesconto
     */
    public float getVdesconto() {
        return vdesconto;
    }

    /**
     * @param vdesconto the vdesconto to set
     */
    public void setVdesconto(float vdesconto) {
        this.vdesconto = vdesconto;
    }

    /**
     * @return the totalpag
     */
    public float getTotalpag() {
        return totalpag;
    }

    /**
     * @param totalpag the totalpag to set
     */
    public void setTotalpag(float totalpag) {
        this.totalpag = totalpag;
    }

    /**
     * @return the num_parcela
     */
    public int getNum_parcela() {
        return num_parcela;
    }

    /**
     * @param num_parcela the num_parcela to set
     */
    public void setNum_parcela(int num_parcela) {
        this.num_parcela = num_parcela;
    }
    
}
