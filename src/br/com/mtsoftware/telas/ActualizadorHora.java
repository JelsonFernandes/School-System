/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mtsoftware.telas;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Jelson Fernandes
 */
public class ActualizadorHora {
    public static void start(final JLabel lblHorario){
       Thread atualizaHora=new Thread(new Runnable(){
           
           @Override
           public void run(){
               try {
                   
                   while(true){
                       
                       Date date=new Date();
                       StringBuffer data=new StringBuffer();
                       SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
                       lblHorario.setText(data.toString()+sdf.format(date));
                       Thread.sleep(1000);
                       
                       lblHorario.revalidate();
                       
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               
           }
       }
       );
       atualizaHora.start();
    }
    
}
