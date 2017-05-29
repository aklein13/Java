package CRUDY;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


import Modele.UCZEN;


public class CRUDUCZEN{
	
	  public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:test.db";
	private static Connection conn;
    private static Statement stmt;
	
	
	public void read(DefaultTableModel model){
		
		   try {
	            conn = DriverManager.getConnection(DB_URL);
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
        
			try {
 
		
			ResultSet result = stmt.executeQuery("SELECT * FROM UCZEN");
        
    	UCZEN FULL =new UCZEN();
         while(result.next()) {
           FULL.setId_Uczen(result.getInt("Id_Uczen"));
           FULL.setImie(result.getString("Imie"));
           FULL.setNazwisko(result.getString("Nazwisko"));
           FULL.setWiek(result.getInt("Wiek"));
           FULL.setIndeks(result.getString("Indeks"));
           
           Object[] full = {FULL.getId_Uczen(),FULL.getImie(),FULL.getNazwisko(),String.valueOf(FULL.getWiek()),FULL.getIndeks()};
           model.addRow(full); 
         }
         } catch (SQLException e) {
             System.err.println("Blad przy wykonywaniu SELECT");
             e.printStackTrace();
         }

      
  
   
           
           try {
               
      
       stmt.close();
     
           } catch (SQLException e) {
               System.err.println("SQLException");
               e.printStackTrace();
           }
   
// read KONIEC	
}



	
	public void delete(int id){
		   try {
	            conn = DriverManager.getConnection(DB_URL);
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
     
		
		
				UCZEN DELETE = new UCZEN();
		DELETE.setId_Uczen(id);
			 String sql =("DELETE FROM UCZEN WHERE Id_Uczen = "+DELETE.getId_Uczen());
				 try {
					stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 	
    
  
   


        
        try {
            
   
    stmt.close();
  
        } catch (SQLException e) {
            System.err.println("SQLException");
            e.printStackTrace();
        }

//read KONIEC	 DELETE
}
	
	
	
	

	public int add(String Imie, String Nazwisko, String Wiek, String Indeks, JPanel inputPanel){
		
		
		int flag=0;
		
		
		try {
	            conn = DriverManager.getConnection(DB_URL);
	            
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
		
		if(flag==0){
			
			int wiekk;
	wiekk=Integer.parseInt(Wiek);
			UCZEN ADD = new UCZEN(Imie,Nazwisko, wiekk, Indeks);
		String sql = null;
		
		sql=("INSERT INTO UCZEN (Imie,Nazwisko,Wiek,Indeks) VALUES ('"+ADD.getImie()+"','"+ADD.getNazwisko()+"','"+ADD.getWiek()+"','"+ADD.getIndeks()+"')");
 
				
				
				 try {
					stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					flag=1;JOptionPane.showMessageDialog(inputPanel, "Taki uczen juz istnieje!"); 
				}
	
		}
 try {
         

 stmt.close();

     } catch (SQLException e) {
         System.err.println("SQLException");
         e.printStackTrace();
     }
	
     return flag;

//read KONIEC	
}

	
	
	
	
	
	
	
	
	
	
public int update(int id, String Imie, String Nazwisko, String Wiek, String Indeks, JPanel inputPanel){
	String sql;
	
	int flag=0;
	
	
	
	try {
            conn = DriverManager.getConnection(DB_URL);
            
            stmt = conn.createStatement();
        
         
           
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
       int wiekk;
	wiekk=Integer.parseInt(Wiek);
if(flag==0){
	
	UCZEN UPDATE = new UCZEN(id,Imie,Nazwisko, wiekk, Indeks);	
  
	
	
	
	sql=("UPDATE UCZEN SET Imie ='"+UPDATE.getImie()+"',Nazwisko='"+UPDATE.getNazwisko()+"',Wiek='"+UPDATE.getWiek()+"',Indeks='"+UPDATE.getIndeks()+"' WHERE Id_Uczen="+UPDATE.getId_Uczen());	
	
	
	try {
		stmt.executeUpdate(sql);
	} catch (SQLException e1) {
		         flag=1;JOptionPane.showMessageDialog(inputPanel, "Taki uczen juz istnieje"); 

	}
				
	}
	
	
	try {
         

 stmt.close();

     } catch (SQLException e) {
         System.err.println("SQLException");

     }

return flag;
//read KONIEC	 UPDATE
}


}


	