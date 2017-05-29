package Widoki;


import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;


import CRUDY.CRUDUCZEN;
import java.awt.Component;


public class UczenWidok extends JPanel{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

CRUDUCZEN a = new CRUDUCZEN();
private DefaultTableModel model;
private	JPanel inputPanel = new JPanel()	;	
private JTable table;
private JLabel ImieL; 
private JLabel NazwiskoL; 
private JLabel WiekL; 
private JLabel IndeksL; 
private JTextField  Nazwisko;
private JTextField  Imie;
private JTextField  Wiek;
private JTextField  Indeks;



public UczenWidok() {
    super();
    model = new DefaultTableModel(){

       

	
		private static final long serialVersionUID = 1L;

		@Override
        public boolean isCellEditable(int row, int column) {
           
           return false;
        }
    };
    model.addColumn("Id Uczen");
    model.addColumn("Imie");
    model.addColumn("Nazwisko");
    model.addColumn("Wiek");
model.addColumn("Indeks");
    a.read(model);
    


    
    
    
    table = new JTable(model);
    table.removeColumn(table.getColumnModel().getColumn(0));

    
 //JTextfields   
Imie= new JTextField(15);
Nazwisko= new JTextField(15);
Wiek= new JTextField(5);
Indeks= new JTextField(15);
//JLabels
ImieL = new JLabel("Imie");
NazwiskoL = new JLabel("Nazwisko");
WiekL = new JLabel("Wiek");
IndeksL = new JLabel("Indeks");
table.addMouseListener(new MouseAdapter() {
	  public void mouseClicked(MouseEvent e) {
	    if (e.getClickCount() == 2) {
	    
	    
	     Imie.setText((String) (table.getModel().getValueAt(table.getSelectedRow(),1)));
	     Nazwisko.setText( (String) (table.getModel().getValueAt(table.getSelectedRow(),2)));
             Wiek.setText( (String) (table.getModel().getValueAt(table.getSelectedRow(),3)));
             Indeks.setText( (String) (table.getModel().getValueAt(table.getSelectedRow(),4)));
	    }
	  }
	});  
    
 
    
    



JButton addButton = new JButton("Dodaj");
    addButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent event) {
        int id;
    id=a.add(Imie.getText(),Nazwisko.getText(),Wiek.getText(), Indeks.getText(),inputPanel);
    	  
    if(id!=-1){	
    
       setmodel();
   
    }
      }
    });

   
    
    JButton removeButton = new JButton("Usuń");

    removeButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent event) {
    	 
    	  int row = table.getSelectedRow();
    	  if(row == -1){JOptionPane.showMessageDialog(inputPanel, "Kliknij na rekord ktory chcesz usunac");}else{
    	  int value = (int) (table.getModel().getValueAt(table.getSelectedRow(),0));
    	
    	 
    	  
    	  int answer = JOptionPane.showConfirmDialog(
    			  inputPanel,
    			    "Jestes pewien ze chcesz usunac ten rekord",
    			    "WARNING",
    			    JOptionPane.YES_NO_OPTION);
    		
    	  
    	  if(answer == JOptionPane.YES_OPTION){
    		 
    		
    
    	  
    	  a.delete(value);
    	  
    	 
    	  model.removeRow(table.getSelectedRow());
    	  }
      
      
    	  }
      }
    });
    
    
    
    
   
    
    
    
    JButton updateButton = new JButton("Zmień");
    updateButton.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent event) {
        	 int row = table.getSelectedRow();  
         	if(row == -1){JOptionPane.showMessageDialog(inputPanel, "Kliknij na rekord ktory chcesz zaktualizowac");}else{   
     int id=0;
        	
        	  int value = (int) (table.getModel().getValueAt(row,0));
        	id=a.update(value,Imie.getText(),Nazwisko.getText(), Wiek.getText(), Indeks.getText(),inputPanel);
      	  
    	
        	 if(id!=-1){	
         setmodel();
        	 }
         	}
        }
      });
    
    
    
 
    
    inputPanel.setLayout(new BorderLayout());
    JPanel subPanel = new JPanel();

    
  
  
    JScrollPane tableContainer = new JScrollPane(table);

    inputPanel.add(tableContainer, BorderLayout.CENTER);
    subPanel.add(addButton);
    subPanel.add(removeButton);


    subPanel.add(updateButton);
    
    subPanel.add(ImieL);
    subPanel.add(Imie);
    subPanel.add(NazwiskoL);
    subPanel.add(Nazwisko);
    subPanel.add(WiekL);
    subPanel.add(Wiek);
    subPanel.add(IndeksL);
    subPanel.add(Indeks);
    
    inputPanel.add(subPanel, BorderLayout.SOUTH);

 


//    inputPanel.setSize(1000, 300);
  
    add(inputPanel);
  
  } 

 void  setmodel(){
		
 this.model = new DefaultTableModel(){
 private static final long serialVersionUID = 1L;
@Override
public boolean isCellEditable(int row, int column) {
 return false;
	        }
	
	  
	    
	    };
	    
	    model.addColumn("Id Uczen");
	    model.addColumn("Imie");
	    model.addColumn("Nazwisko");
             model.addColumn("Wiek");
              model.addColumn("Indeks");
	    


	    a.read(model);
	   this.table.setModel(model);
	    table.removeColumn(table.getColumnModel().getColumn(0));

}
}
















   
