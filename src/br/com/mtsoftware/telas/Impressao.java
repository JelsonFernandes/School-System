/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jelson Fernandes
 */
public class Impressao {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     public void Imprime_Relatoio(List lista){
         
String  caminhoRelJasper="/br/com/mtsoftware/telas/FacturaPagamentos.jasper";
InputStream relJasper=getClass().getResourceAsStream(caminhoRelJasper);
JRBeanCollectionDataSource da = new JRBeanCollectionDataSource(lista);
Map parametros = new HashMap(); 
JasperPrint impressao=null;
        try {
            impressao= JasperFillManager.fillReport(relJasper, parametros,da);
            JasperViewer viewer = new JasperViewer (impressao,true);
            viewer.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());                                        
                    
                    
        }
}
    
}
