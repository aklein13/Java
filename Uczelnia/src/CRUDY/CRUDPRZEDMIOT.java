package CRUDY;

import Modele.NAUCZYCIEL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import Modele.PRZEDMIOT;

public class CRUDPRZEDMIOT {
	
	  public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:test.db";
	private static Connection conn;
    private static Statement stmt;
	
	
		public void ComboxNauczycielFill(JComboBox<String> Nauczyciele, ArrayList<Integer> teacherkeys){
		teacherkeys.clear();
		 int itemCount =Nauczyciele.getItemCount();

		    for(int i=0;i<itemCount;i++){
		        Nauczyciele.removeItemAt(0);
		    }
	   
		int a=0;

		try {
	            conn = DriverManager.getConnection(DB_URL);
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
     

		
		
			try {

		ResultSet result = stmt.executeQuery("SELECT Id_Nauczyciel,Imie,Nazwisko FROM NAUCZYCIEL");
  
 
 	NAUCZYCIEL FULL1 =new NAUCZYCIEL();
 	
 	
 	while(result.next()) {
        
 		
 		FULL1.setId(result.getInt("Id_Nauczyciel"));
 		FULL1.setImie(result.getString("Imie"));
                FULL1.setNazwisko(result.getString("Nazwisko"));
        
     String nauczyciel;
     if (result.wasNull()) {
			nauczyciel=FULL1.getImie();
	    }
		else{nauczyciel=""+FULL1.getImie()+" "+FULL1.getNazwisko();}
     
       Nauczyciele.addItem(nauczyciel);
       teacherkeys.add(a,FULL1.getId_Nauczyciel());
     
       a++;
       }
 	Nauczyciele.addItem("");
   teacherkeys.add(a,-1);
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

//////////////////////KONIEC ComboxKrajFill///////////////////
	}







public void read(DefaultTableModel model){
		
		   try {
	            conn = DriverManager.getConnection(DB_URL);
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
        
			try {
 
		
			ResultSet result = stmt.executeQuery("SELECT s.Id_Przedmiot, s.Id_Nauczyciel,s.Nazwa,s.Opis,s.Ilosc_Godzin,hp.Imie,hp.Nazwisko FROM PRZEDMIOT s LEFT JOIN NAUCZYCIEL hp on s.Id_Nauczyciel = hp.Id_Nauczyciel ");
        
    	PRZEDMIOT FULL =new PRZEDMIOT();
        NAUCZYCIEL FULL1 =new NAUCZYCIEL();
         while(result.next()) {
           FULL.setId(result.getInt("Id_Przedmiot"));
FULL1.setId(result.getInt("Id_Nauczyciel"));
           FULL.setNazwa(result.getString("Nazwa"));
           FULL.setOpis(result.getString("Opis"));
           FULL.setIlosc_Godzin(result.getInt("Ilosc_Godzin"));
           FULL1.setImie(result.getString("Imie"));
           FULL1.setNazwisko(result.getString("Nazwisko"));
           String nauczyciel=""+FULL1.getImie()+" "+FULL1.getNazwisko();
           
           Object[] full = {FULL.getId(),FULL1.getId_Nauczyciel(),FULL.getNazwa(),FULL.getOpis(),FULL.getIlosc_Godzin(),nauczyciel};
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
	       
		
     
		
		
				PRZEDMIOT DELETE = new PRZEDMIOT();
		DELETE.setId(id);
			 String sql =("DELETE FROM  PRZEDMIOT WHERE Id_Przedmiot = "+DELETE.getId());
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
	
	
	
	

	public int add(String Nazwa, String Opis, String Ilosc_Godzin, int Id_Nauczyciel, JPanel inputPanel){
		
		
		int titlenumber=0;
			int flag=0;
		SimpleDateFormat Dateu;
		 
		
		try {
	            conn = DriverManager.getConnection(DB_URL);
	            
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		

		
		ResultSet rs0 = null;
		
		
	
		
  
		if(flag==0){
		int ilgodz;
                ilgodz=Integer.parseInt(Ilosc_Godzin);
		PRZEDMIOT ADD = new PRZEDMIOT(Nazwa, Opis,ilgodz,Id_Nauczyciel);
		String sql = null;
		
			sql=("INSERT INTO PRZEDMIOT (Nazwa,Opis,Ilosc_Godzin,Id_nauczyciel) VALUES ('"+ADD.getNazwa()+"','"+ADD.getOpis()+"','"+ADD.getIlosc_Godzin()+"', "+ADD.getId_Nauczyciel())+")";
		
			
				
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

	
			
	
	

	public int update(int id, String Nazwa, String Opis, String Ilosc_Godzin, int Id_Nauczyciel, int check, JPanel inputPanel){
		
		
		int titlenumber=0;
			int flag=0;

		
		try {
	            conn = DriverManager.getConnection(DB_URL);
	            
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
		
		
		
		ResultSet rs0 = null;
	


  
		if(flag==0){int ilgodz;
                ilgodz=Integer.parseInt(Ilosc_Godzin);
		
				PRZEDMIOT UPDATE = new PRZEDMIOT(Nazwa, Opis,ilgodz,Id_Nauczyciel);
		String sql = null;
	
			sql=("UPDATE PRZEDMIOT SET Nazwa='"+UPDATE.getNazwa()+"', Opis='"+UPDATE.getOpis()+"',Ilosc_Godzin='"+UPDATE.getIlosc_Godzin()+"',Id_Nauczyciel="+UPDATE.getId_Nauczyciel()+" WHERE Id_Przedmiot="+id+" ");
		
			
				
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



	

