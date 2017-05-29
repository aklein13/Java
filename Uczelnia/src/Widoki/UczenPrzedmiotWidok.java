package Widoki;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;


import CRUDY.CRUDUCZENPRZEDMIOT;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class UczenPrzedmiotWidok extends JPanel{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

ArrayList<Integer> studentkeys = new ArrayList<Integer>();
ArrayList<Integer> nazwaskeys = new ArrayList<Integer>();


CRUDUCZENPRZEDMIOT a = new CRUDUCZENPRZEDMIOT();
private DefaultTableModel model;
private JTable table;
private JComboBox<String> Uczniowie = new JComboBox<String>();
private JComboBox<String> Nazwy = new JComboBox<String>();
private JPanel inputPanel ; 

public JComboBox<String> getUczniowie(){return Uczniowie;}
public JComboBox<String> getNazwy(){return Nazwy;}
private JTextField  Ocena_Koncowa;
private JLabel Ocena_KoncowaL ;
private JTextField  Zaliczenie;
private JLabel ZaliczenieL ;
private JTextField  Uwagi;
private JLabel UwagiL ;
public UczenPrzedmiotWidok() {
 super();

 
inputPanel = new JPanel();
inputPanel.setLayout(new BorderLayout());


model = new DefaultTableModel(){
private static final long serialVersionUID = 1L;
@Override
public boolean isCellEditable(int row, int column) {
return false;
	        }
};
    
model.addColumn("Id Uczen ");
model.addColumn("Id Przedmiot");
model.addColumn("Uczen");
model.addColumn("Przedmiot");
model.addColumn("Ocena Koncowa");
    model.addColumn("Zaliczenie");
    model.addColumn("Uwagi");
a.read(model);
    
table = new JTable(model);
table.removeColumn(table.getColumnModel().getColumn(0));
table.removeColumn(table.getColumnModel().getColumn(0));
 a.ComboxUczenFill(Uczniowie,studentkeys);
 a.ComboxPrzedmiotFill(Nazwy,nazwaskeys);
  Ocena_Koncowa = new JTextField(7);

//JLabels
  Ocena_KoncowaL = new JLabel("Ocena Koncowa");
    Zaliczenie = new JTextField(10);

//JLabels
  ZaliczenieL = new JLabel("Zaliczenie");

  Uwagi = new JTextField(15);

//JLabels
  UwagiL = new JLabel("Uwagi");
   table.addMouseListener(new MouseAdapter() {
  	  public void mouseClicked(MouseEvent e) {
  	    if (e.getClickCount() == 2) {
 Uczniowie.setSelectedItem((String) (table.getModel().getValueAt(table.getSelectedRow(),2)));
 Nazwy.setSelectedItem((String) (table.getModel().getValueAt(table.getSelectedRow(),3)));
 Ocena_Koncowa.setText(Float.toString((Float) (table.getModel().getValueAt(table.getSelectedRow(),4))));
  	    Zaliczenie.setText((String) (table.getModel().getValueAt(table.getSelectedRow(),5)));
           Uwagi.setText((String) (table.getModel().getValueAt(table.getSelectedRow(),6)));
  	    }
  	  }
  	});

    
    
    
JButton addButton = new JButton("Dodaj");
 
addButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent event) {
 int id;
 id=a.add(studentkeys.get(Uczniowie.getSelectedIndex()),nazwaskeys.get(Nazwy.getSelectedIndex()), Ocena_Koncowa.getText(), Zaliczenie.getText(), Uwagi.getText(),inputPanel);
 
 if(id!=-1){	
  setmodel();
 } 
 }
        });
    
   
JButton removeButton = new JButton("Usuń");

removeButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent event) {
  int row = table.getSelectedRow();
  if(row == -1){JOptionPane.showMessageDialog(inputPanel, "Kliknij na record ktory chcesz usunac");}else{
  int value = (int) (table.getModel().getValueAt(table.getSelectedRow(),0));
  int value1 = (int) (table.getModel().getValueAt(table.getSelectedRow(),1));
   int answer = JOptionPane.showConfirmDialog(
   inputPanel,
    "Jestes pewny ze chcesz usunac ten rekord",
    "WARNING",
   JOptionPane.YES_NO_OPTION);
    		
 if(answer == JOptionPane.YES_OPTION){
   a.delete(value,value1,inputPanel);
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
  int value1 = (int) (table.getModel().getValueAt(row,1));
  id=a.update(value,value1,studentkeys.get(Uczniowie.getSelectedIndex()),nazwaskeys.get(Nazwy.getSelectedIndex()), Ocena_Koncowa.getText(), Zaliczenie.getText(), Uwagi.getText(),inputPanel);
      	  
    if(id!=-1){	
         setmodel();
   }
 }
}
        });
    
    

    

 JPanel subPanel = new JPanel();

    JScrollPane tableContainer = new JScrollPane(table);

    inputPanel.add(tableContainer, BorderLayout.NORTH);
    subPanel.add(addButton);
    subPanel.add(removeButton);
    subPanel.add(updateButton);
    subPanel.add(Uczniowie);
    subPanel.add(Nazwy);
    subPanel.add(Ocena_KoncowaL);
    subPanel.add(Ocena_Koncowa);
    subPanel.add(ZaliczenieL);
    subPanel.add(Zaliczenie);
    subPanel.add(UwagiL);
    subPanel.add(Uwagi);
    inputPanel.add(subPanel, BorderLayout.SOUTH);

 


//    inputPanel.setSize(1000, 300);
  
    add(inputPanel);
    ;
  
  } 
  
  
  void  setmodel(){
this.model = new DefaultTableModel(){
private static final long serialVersionUID = 1L;
@Override 
public boolean isCellEditable(int row, int column) {
return false;
	        }
	};;
	 model.addColumn("Id Uczen ");
	model.addColumn("Id Przedmiot");
	    model.addColumn("Uczen");
	    model.addColumn("Przedmiot");
            model.addColumn("Ocena Koncowa");
            model.addColumn("Zaliczenie");
            model.addColumn("Uwagi");

	    a.read(model);
	   this.table.setModel(model);
	   table.removeColumn(table.getColumnModel().getColumn(0));
	    table.removeColumn(table.getColumnModel().getColumn(0));

}
}
