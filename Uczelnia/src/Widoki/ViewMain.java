package Widoki;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import CRUDY.CRUDUCZENPRZEDMIOT;

import baza_danych.BAZA;


import javax.swing.*;



 
public class ViewMain {
	  final static String PANEL0 = "PRZEDMIOTY";
	final static String PANEL1 = "NAUCZYCIELE";
    final static String PANEL2 = "PRZEDMIOTY UCZNIOW";
    final static String PANEL3 = "SPRAWDZIANY";
    final static String PANEL5 = "UCZENIOWIE";
    

 
    public void dodajpanel(JFrame  pane) {
        
    	JTabbedPane Menu = new JTabbedPane();
    	
    	CRUDUCZENPRZEDMIOT a = new CRUDUCZENPRZEDMIOT();
    	
      PrzedmiotWidok fp = new PrzedmiotWidok();
        Menu.add(fp);
        NauczycielWidok fp1 = new NauczycielWidok();
        Menu.add(fp1);
        UczenPrzedmiotWidok fp2 = new UczenPrzedmiotWidok();
        Menu.add(fp2);
        SprawdzianWidok fp3 = new SprawdzianWidok();
        Menu.add(fp3);
        UczenWidok fp5 = new UczenWidok();
        Menu.add(fp5);
        
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
            	int index = Menu.getSelectedIndex();
           	 if(index==0){fp.setmodel();}
           	if(index==1){fp1.setmodel();}
           if(index==2){
          fp2.setmodel();
         a.ComboxUczenFill(fp2.getUczniowie(),fp2.studentkeys);
        a.ComboxPrzedmiotFill(fp2.getNazwy(),fp2.nazwaskeys);}
       if(index==3){fp3.setmodel();}
       if(index==5){}
       fp5.setmodel();}
          };
   
       
          Menu.addChangeListener(changeListener); 
        pane.add(Menu, BorderLayout.CENTER);
        Menu.addTab(PANEL0, fp);
        Menu.addTab(PANEL1, fp1);
        Menu.addTab(PANEL2, fp2);
    
        Menu.addTab(PANEL3, fp3);
       
        Menu.addTab(PANEL5, fp5);

    }
 


    private static void Menu() {
      
        JFrame panel = new JFrame("BAZA UCZELNI");
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        ViewMain view = new ViewMain();
        view.dodajpanel(panel);
      
       panel.pack();
        panel.setVisible(true);
    }
 
    public static void main(String[] args) {
    	BAZA era = new BAZA ();
        
    	  File f = new File("test.db");

    	  if(f.exists()){
    		  System.out.println("Jest juz baza danch test.db");
    	  }else{
    		  era.runz();  
                  System.out.println("Stworzono baze danych test.db");
    	  }
  
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               Menu();
            }
        });
    }
}