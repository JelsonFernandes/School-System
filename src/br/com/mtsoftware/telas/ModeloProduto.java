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
public class ModeloProduto {
   private int IdProduto;
   private String NomeProduto;
   private float PrecoVenda;
   private float PrecoCompra;
   private String Fornecedor;
   private int QuatProduto;
   private String Pesquisa;

    /**
     * @return the IdProduto
     */
    public int getIdProduto() {
        return IdProduto;
    }

    /**
     * @param IdProduto the IdProduto to set
     */
    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
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
     * @return the PrecoVenda
     */
    public float getPrecoVenda() {
        return PrecoVenda;
    }

    /**
     * @param PrecoVenda the PrecoVenda to set
     */
    public void setPrecoVenda(float PrecoVenda) {
        this.PrecoVenda = PrecoVenda;
    }

    /**
     * @return the PrecoCompra
     */
    public float getPrecoCompra() {
        return PrecoCompra;
    }

    /**
     * @param PrecoCompra the PrecoCompra to set
     */
    public void setPrecoCompra(float PrecoCompra) {
        this.PrecoCompra = PrecoCompra;
    }

    /**
     * @return the Fornecedor
     */
    public String getFornecedor() {
        return Fornecedor;
    }

    /**
     * @param Fornecedor the Fornecedor to set
     */
    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    /**
     * @return the QuatProduto
     */
    public int getQuatProduto() {
        return QuatProduto;
    }

    /**
     * @param QuatProduto the QuatProduto to set
     */
    public void setQuatProduto(int QuatProduto) {
        this.QuatProduto = QuatProduto;
    }

    /**
     * @return the Pesquisa
     */
    public String getPesquisa() {
        return Pesquisa;
    }

    /**
     * @param Pesquisa the Pesquisa to set
     */
    public void setPesquisa(String Pesquisa) {
        this.Pesquisa = Pesquisa;
    }
    
}
