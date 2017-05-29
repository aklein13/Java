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


import CRUDY.CRUDNAUCZYCIEL;


public class NauczycielWidok extends JPanel{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

CRUDNAUCZYCIEL a = new CRUDNAUCZYCIEL();
private DefaultTableModel model;
private	JPanel inputPanel = new JPanel();
private JTable table;
private JLabel ImieL;
private JLabel NazwiskoL;
private JLabel TytulL; 
private JLabel DataZatrudnieniaL; 
private JTextField  Imie ;
private JTextField Nazwisko; 
private JTextField  Tytul ;
private JTextField  Data_Zatrudnienia;




public NauczycielWidok() {
    super();
    model = new DefaultTableModel(){

       

	
		private static final long serialVersionUID = 1L;

		@Override
        public boolean isCellEditable(int row, int column) {
           
           return false;
        }
    };
    model.addColumn("Id Nauczyciel");
    model.addColumn("Imie");
    model.addColumn("Nazwisko");
    model.addColumn("Tytul");
    model.addColumn("Data Zatrudnienia");
    
    
    a.read(model);
    


    
    
    
    table = new JTable(model);
    table.removeColumn(table.getColumnModel().getColumn(0));

    
 //JTextfields 
 Imie = new JTextField(7);
Nazwisko= new JTextField(7);
Tytul = new JTextField(20);
Data_Zatrudnienia = new JTextField(10);

//JLabels
ImieL= new JLabel("Imie");
NazwiskoL= new JLabel("Nazwisko");
TytulL = new JLabel("Tytul");
DataZatrudnieniaL = new JLabel("Data Zatrudnienia");


table.addMouseListener(new MouseAdapter() {
	  public void mouseClicked(MouseEvent e) {
	    if (e.getClickCount() == 2) {
	    
	    Imie.setText((String) (table.getModel().getValueAt(table.getSelectedRow(),1)));
	     Nazwisko.setText((String) (table.getModel().getValueAt(table.getSelectedRow(),2)));
	     Tytul.setText((String) (table.getModel().getValueAt(table.getSelectedRow(),3)));
	     Data_Zatrudnienia.setText( (String) (table.getModel().getValueAt(table.getSelectedRow(),4)));
	     
	    
	    }
	  }
	});  
    
 
    
    



JButton addButton = new JButton("Dodaj");
    addButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent event) {
        int id;
   int test=0;
  
        id=a.add(Imie.getText(),Nazwisko.getText(),Tytul.getText(),Data_Zatrudnienia.getText(),inputPanel);
    	  
    if(id!=-1){	
    
       setmodel();
   
    }
      }
    });

   
    
    JButton removeButton = new JButton("Usuń");


 removeButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent event) {
    	 
    	  int row = table.getSelectedRow();
    	  if(row == -1){JOptionPane.showMessageDialog(inputPanel, "kliknij na rekord ktory chcesz usunac");}else{
    	  int value = (int) (table.getModel().getValueAt(table.getSelectedRow(),0));
    	
    	 
    	  
    	  int answer = JOptionPane.showConfirmDialog(
    			  inputPanel,
    			    "Jestes pewny ze chcesz usunac ten rekord",
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
     int test=0;
     if(Tytul.getText().equals((String)(table.getModel().getValueAt(table.getSelectedRow(),1)))){test=1;}   	
     
        	  int value = (int) (table.getModel().getValueAt(row,0));
        	id=a.update(value,Imie.getText(),Nazwisko.getText(),Tytul.getText(),Data_Zatrudnienia.getText(),inputPanel);
      	  
    	
        	 if(id!=-1){	
         setmodel();
        	 }
  
        }
        }});
    
    
    
   // JPanel inputPanel = new JPanel();
    
    inputPanel.setLayout(new BorderLayout());
    JPanel subPanel = new JPanel();

    
  
  // table.setPreferredScrollableViewportSize(table.getPreferredSize());
    JScrollPane tableContainer = new JScrollPane(table);

    inputPanel.add(tableContainer, BorderLayout.CENTER);
    subPanel.add(addButton);
    subPanel.add(removeButton);
    subPanel.add(updateButton);
       subPanel.add(ImieL);
    subPanel.add(Imie);
    subPanel.add(NazwiskoL);
    subPanel.add(Nazwisko);
    subPanel.add(TytulL);
    subPanel.add(Tytul);
    subPanel.add(DataZatrudnieniaL);
    subPanel.add(Data_Zatrudnienia);

    
    
    inputPanel.add(subPanel, BorderLayout.SOUTH);

 


//    inputPanel.setSize(1000, 300);
  
    add(inputPanel);
    ;
  
  } 
 void  addmod(){
	
	 model.setRowCount(0); 
	    model.addColumn("Id Nauczyciel");
            model.addColumn("Imie");
	    model.addColumn("Nazwisko");
	    model.addColumn("Tytul");
	    model.addColumn("Data Zatrudnienia");
	    
	    a.read(model);
	   this.table.setModel(model);

}
 void  setmodel(){
		
	    this.model = new DefaultTableModel(){

	        

		
			private static final long serialVersionUID = 1L;

		
			@Override
	        public boolean isCellEditable(int row, int column) {
	           
	           return false;
	        }
	
	    
	    
	    };
	    
	    model.addColumn("Id Nauczyciel");
             model.addColumn("Imie");
	    model.addColumn("Nazwisko");
	    model.addColumn("Tytul");
	    model.addColumn("Data Zatrudnienia");
	   

	    a.read(model);
	   this.table.setModel(model);
	    table.removeColumn(table.getColumnModel().getColumn(0));

}
}

















   
