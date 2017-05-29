package Widoki;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import CRUDY.CRUDPRZEDMIOT;



public class PrzedmiotWidok extends JPanel{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

CRUDPRZEDMIOT a = new CRUDPRZEDMIOT();

ArrayList<Integer> teacherkeys = new ArrayList<Integer>();
private JComboBox<String> Nauczyciele= new JComboBox<String>();


private DefaultTableModel model;
private  JTable table;


private JTextField  nazwa;
private JTextField  opis;
private JTextField  iloscgodzin;
private JLabel nazwaL ;
private JLabel  opisL;
private JLabel  iloscgodzinL ;
private JLabel nauczycielL ;

public PrzedmiotWidok() {
	 
	  super();
	
	  JPanel inputPanel = new JPanel();
	  this.model = new DefaultTableModel(){

	        

			
			private static final long serialVersionUID = 1L;

		
			@Override
	        public boolean isCellEditable(int row, int column) {
	           
	           return false;
	        }
	
	    
	    
	    };
    model.addColumn("Id Przedmiot ");
        model.addColumn("Id Nauczyciel");

model.addColumn("Nazwa ");
    model.addColumn("Opis  ");
    model.addColumn("Ilosc Godzin");
    model.addColumn("Nauczyciel Prowadzacy");

    a.read(model);
    


    table = new JTable(model);
    table.removeColumn(table.getColumnModel().getColumn(0));
table.removeColumn(table.getColumnModel().getColumn(0));
    a.ComboxNauczycielFill(Nauczyciele,teacherkeys);

  //////////////////////////////////////////////////////////////  
  nazwa = new JTextField(15);
  opis = new JTextField(15);
  iloscgodzin = new JTextField(5);

//JLabels
  nazwaL = new JLabel("Nazwa");
  opisL = new JLabel("Opis");
  iloscgodzinL= new JLabel("Ilosc Godzin");
    nauczycielL=new  JLabel("NauczycielProwadzacy");
    
    
    
    
    table.addMouseListener(new MouseAdapter() {
  	  public void mouseClicked(MouseEvent e) {
  	    if (e.getClickCount() == 2) {
  	    
  	    
  	     nazwa.setText((String) (table.getModel().getValueAt(table.getSelectedRow(),2)));
  	     opis.setText( (String) (table.getModel().getValueAt(table.getSelectedRow(),3)));
  	     iloscgodzin.setText(Integer.toString((Integer) (table.getModel().getValueAt(table.getSelectedRow(),4))));
  	   Nauczyciele.setSelectedItem((String) (table.getModel().getValueAt(table.getSelectedRow(),5)));
  	    }
  	  }
  	});

    
    
    
    JButton addButton = new JButton("Dodaj");
    addButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent event) {
       int z=1;
    	  int id;
    	 try{
    	  z=teacherkeys.get(Nauczyciele.getSelectedIndex());
    	 }catch(ArrayIndexOutOfBoundsException e){}
    
    	 
    	 
    	 id=a.add(nazwa.getText(),opis.getText(),iloscgodzin.getText(),z,inputPanel);
   	if(id==0){
    setmodel();  
   	}
     
      }
    });
    
    JButton removeButton = new JButton("Usuń");

    removeButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent event) {
    	 
    	  int row = table.getSelectedRow();
    	  if(row == -1){JOptionPane.showMessageDialog(inputPanel, "Wyberz rekord by go usunac");}else{
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
  int value1 = 0;
	int check=0;
  int row = table.getSelectedRow();
  if(row == -1){JOptionPane.showMessageDialog(inputPanel, "Kliknij na rekord ktory chcesz zaktualizowac");}else{
  int value = (int) (table.getModel().getValueAt(table.getSelectedRow(),0));
 if(nazwa.getText().equals((String)(table.getModel().getValueAt(table.getSelectedRow(),2)))){check=1;}
 if(nazwa.getText().equals((String)(table.getModel().getValueAt(table.getSelectedRow(),2))) && opis.getText().equals((String)(table.getModel().getValueAt(table.getSelectedRow(),3)))){check=1;}
 try{
  value1 = teacherkeys.get(Nauczyciele.getSelectedIndex());
 }catch(ArrayIndexOutOfBoundsException e){value =-1;}
a.update(value,nazwa.getText(),opis.getText(),iloscgodzin.getText(),value1,check,inputPanel);
setmodel();


      }
}       });
    
    

    
    
    
 //   JPanel inputPanel = new JPanel();
    
    inputPanel.setLayout(new BorderLayout());
    JPanel subPanel = new JPanel();

    
  
  // table.setPreferredScrollableViewportSize(table.getPreferredSize());
    JScrollPane tableContainer = new JScrollPane(table);

    inputPanel.add(tableContainer, BorderLayout.NORTH);
    subPanel.add(addButton);
    subPanel.add(removeButton);
 
    subPanel.add(updateButton);
    
    subPanel.add(nazwaL);
    subPanel.add(nazwa);
    subPanel.add(opisL);
    subPanel.add(opis);
    subPanel.add(iloscgodzinL);
    subPanel.add(iloscgodzin);
        subPanel.add(nauczycielL);
    subPanel.add(Nauczyciele);



inputPanel.add(subPanel, BorderLayout.SOUTH);

 


//    inputPanel.setSize(1000, 300);
  
    add(inputPanel);
    
  
    }
  
  
void setmodel(){
		
	  this.model = new DefaultTableModel(){

	        

			
			private static final long serialVersionUID = 1L;

		
			@Override
	        public boolean isCellEditable(int row, int column) {
	           
	           return false;
	        }
	
	    
	    
	    };
	    model.addColumn("Id Przedmiotu");
model.addColumn("Id Nauczyciela");
	    model.addColumn("Nazwa");
	    model.addColumn("Opis");
	    model.addColumn("Ilosc Godzin");
    model.addColumn("Nauczyciel Prowadzacy");
	    a.read(model);
	   this.table.setModel(model);
	    table.removeColumn(table.getColumnModel().getColumn(0));
table.removeColumn(table.getColumnModel().getColumn(0));
    a.ComboxNauczycielFill(Nauczyciele,teacherkeys);

}


     
  } 

           
