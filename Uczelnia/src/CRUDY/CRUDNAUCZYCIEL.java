package CRUDY;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Modele.NAUCZYCIEL;

public class CRUDNAUCZYCIEL {
	
	
	
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
 
		
			ResultSet result = stmt.executeQuery("SELECT * FROM NAUCZYCIEL");
        
    	NAUCZYCIEL FULL =new NAUCZYCIEL();
         while(result.next()) {
           FULL.setId(result.getInt("Id_Nauczyciel"));
           FULL.setImie(result.getString("Imie"));
           FULL.setNazwisko(result.getString("Nazwisko"));
           FULL.setTytul(result.getString("Tytul"));
           FULL.setData_Zatrudnienia(result.getString("Data_Zatrudnienia"));
           
           Object[] full = {FULL.getId_Nauczyciel(),FULL.getImie(),FULL.getNazwisko(),FULL.getTytul(),FULL.getData_Zatrudnienia()};
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
	       
		
     
		
		
				NAUCZYCIEL DELETE = new NAUCZYCIEL();
		DELETE.setId(id);
			 String sql =("DELETE FROM  NAUCZYCIEL WHERE Id_Nauczyciel = "+DELETE.getId_Nauczyciel());
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

//read KONIEC	
}
	
	
	
	

	public int add(String Imie, String Nazwisko, String Tytul, String Data_Zatrudnienia,  JPanel inputPanel){
		
		int titlenumber=0;
		int pagenumber=0;
		int flag=0;
		SimpleDateFormat Dateu;
		 java.sql.Date dataf = null;
		float score = 0;
		BigDecimal price = null ;
		try {
	            conn = DriverManager.getConnection(DB_URL);
	            
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
		ResultSet rs0;

		
		
		flag=0;
		
		if(flag==0){
			
			
			NAUCZYCIEL ADD = new NAUCZYCIEL(Imie,Nazwisko,Tytul,Data_Zatrudnienia);
		String sql = null;
		
		
		sql=("INSERT INTO NAUCZYCIEL (Imie,Nazwisko,Tytul,Data_Zatrudnienia) VALUES ('"+ADD.getImie()+"','"+ADD.getNazwisko()+"','"+ADD.getTytul()+"','"+ADD.getData_Zatrudnienia()+"')");  
				try {
		stmt.executeUpdate(sql);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
				
	}
	
	
	try {
         

 stmt.close();

     } catch (SQLException e) {
         System.err.println("SQLException");
         e.printStackTrace();
     }

return flag;
}

	
	
	
	
	
	
	
	
	
	
public int update(int id, String Imie, String Nazwisko, String Tytul, String Data_Zatrudnienia, JPanel inputPanel){
	
	int titlenumber=0;
	int pagenumber=0;
	int flag=0;
	SimpleDateFormat Dateu;
	 java.sql.Date dataf = null;
	float score = 0;
	BigDecimal price = null ;
	try {
            conn = DriverManager.getConnection(DB_URL);
            
            stmt = conn.createStatement();
        
         
           
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
       
	
	ResultSet rs0;
	try {
		rs0 = stmt.executeQuery("SELECT COUNT(*)AS count FROM NAUCZYCIEL where Imie='"+Imie+"'");
		while(rs0.next()){
	    	titlenumber = rs0.getInt("count");
	    }
	
	} catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}


	
	


	
	try{
		
	}catch(NullPointerException e){flag=1;}		
	
	

	
	 
	if(flag==0){
	
		NAUCZYCIEL UPDATE = new NAUCZYCIEL(id,Imie,Nazwisko,Tytul,Data_Zatrudnienia);
  
	
	
	
String sql=("UPDATE NAUCZYCIEL SET Imie ='"+UPDATE.getImie()+"',Nazwisko='"+UPDATE.getNazwisko()+"',Tytul='"+UPDATE.getTytul()+"',Data_Zatrudnienia='"+UPDATE.getData_Zatrudnienia()+"' WHERE Id_Nauczyciel="+UPDATE.getId_Nauczyciel());	
	
	
	try {
		stmt.executeUpdate(sql);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
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

    




	
	}

