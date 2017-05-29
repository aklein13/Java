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


import CRUDY.CRUDSPRAWDZIAN;



public class SprawdzianWidok extends JPanel{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

CRUDSPRAWDZIAN a = new CRUDSPRAWDZIAN();

ArrayList<Integer> teacherkeys = new ArrayList<Integer>();
private JComboBox<String> Nauczyciele= new JComboBox<String>();
ArrayList<Integer> studentkeys = new ArrayList<Integer>();
private JComboBox<String> Uczniowie= new JComboBox<String>();

private DefaultTableModel model;
private  JTable table;


private JTextField  punkty;
private JTextField  ocena;
private JLabel punktyL ;
private JLabel  ocenaL;
private JLabel nauczycielL ;
private JLabel uczenL ;

public SprawdzianWidok() {
	 
	  super();
	
	  JPanel inputPanel = new JPanel();
	  this.model = new DefaultTableModel(){

	        

			
			private static final long serialVersionUID = 1L;

		
			@Override
	        public boolean isCellEditable(int row, int column) {
	           
	           return false;
	        }
	
	    
	    
	    };
    model.addColumn("Id Sprawdzian ");
        model.addColumn("Id Nauczyciel");
 model.addColumn("Id Uczen");
model.addColumn("Punkty ");
    model.addColumn("Ocena  ");
    model.addColumn("Uczen Zdajacy");
    model.addColumn("Nauczyciel Prowadzacy");

    a.read(model);
    


    table = new JTable(model);
    table.removeColumn(table.getColumnModel().getColumn(0));
table.removeColumn(table.getColumnModel().getColumn(0));
table.removeColumn(table.getColumnModel().getColumn(0));
    a.ComboxNauczycielFill(Nauczyciele,teacherkeys);
    a.ComboxUczniowieFill(Uczniowie,studentkeys);

  //////////////////////////////////////////////////////////////  
  punkty = new JTextField(5);
  ocena = new JTextField(5);

//JLabels
  punktyL = new JLabel("Punkty");
  ocenaL = new JLabel("Ocena");
  uczenL= new JLabel("Uczen Zdajacy");
    nauczycielL=new  JLabel("Nauczyciel Prowadzacy");
    
    
    
    
    table.addMouseListener(new MouseAdapter() {
  	  public void mouseClicked(MouseEvent e) {
  	    if (e.getClickCount() == 2) {
  	    
  	    
  	     punkty.setText(Integer.toString((Integer) (table.getModel().getValueAt(table.getSelectedRow(),2))));
  	      ocena.setText(Float.toString((Float) (table.getModel().getValueAt(table.getSelectedRow(),3))));
  	  
  	   Nauczyciele.setSelectedItem((String) (table.getModel().getValueAt(table.getSelectedRow(),4)));
           Uczniowie.setSelectedItem((String) (table.getModel().getValueAt(table.getSelectedRow(),5)));
  	    }
  	  }
  	});

    
    
    
    JButton addButton = new JButton("Dodaj");
    addButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent event) {
       int z=1;
       int y=1;
    	  int id;
    	 try{
    	  z=teacherkeys.get(Nauczyciele.getSelectedIndex());
          y=studentkeys.get(Uczniowie.getSelectedIndex());
    	 }catch(ArrayIndexOutOfBoundsException e){}
    
    	 
    	 
    	 id=a.add(punkty.getText(),ocena.getText(),z, y,inputPanel);
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
  int value2 = 0;
	
  int row = table.getSelectedRow();
  if(row == -1){JOptionPane.showMessageDialog(inputPanel, "Kliknij na rekord ktory chcesz zaktualizowac");}else{
  int value = (int) (table.getModel().getValueAt(table.getSelectedRow(),0));
 try{
  value1 = teacherkeys.get(Nauczyciele.getSelectedIndex());
  value2 = studentkeys.get(Uczniowie.getSelectedIndex());
 }catch(ArrayIndexOutOfBoundsException e){value =-1;}
a.update(value,punkty.getText(),ocena.getText(),value1, value2,inputPanel);
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
    
    subPanel.add(punktyL);
    subPanel.add(punkty);
    subPanel.add(ocenaL);
    subPanel.add(ocena);
        subPanel.add(nauczycielL);
    subPanel.add(Nauczyciele);
    subPanel.add(uczenL);
    subPanel.add(Uczniowie);



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
	    model.addColumn("Id Sprawdzian");
model.addColumn("Id Nauczyciela");

	    model.addColumn("Punkty");
	    model.addColumn("Ocena");
    model.addColumn("Nauczyciel Prowadzacy");
    model.addColumn("Uczen Zdajacy");
	    a.read(model);
	   this.table.setModel(model);
	    table.removeColumn(table.getColumnModel().getColumn(0));
table.removeColumn(table.getColumnModel().getColumn(0));
    a.ComboxNauczycielFill(Nauczyciele,teacherkeys);
    a.ComboxUczniowieFill(Uczniowie,studentkeys);

}


     
  } 

           
