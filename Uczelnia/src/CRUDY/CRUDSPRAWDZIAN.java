package CRUDY;

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
import Modele.SPRAWDZIAN;
import Modele.UCZEN;
import Modele.NAUCZYCIEL;

public class CRUDSPRAWDZIAN {
	
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
public void ComboxUczniowieFill(JComboBox<String> Uczniowie, ArrayList<Integer> studentkeys){
		studentkeys.clear();
		 int itemCount =Uczniowie.getItemCount();

		    for(int i=0;i<itemCount;i++){
		        Uczniowie.removeItemAt(0);
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

		ResultSet result = stmt.executeQuery("SELECT Id_Uczen,Imie,Nazwisko FROM UCZEN");
  
 
 	UCZEN FULL1 =new UCZEN();
 	
 	
 	while(result.next()) {
        
 		
 		FULL1.setId_Uczen(result.getInt("Id_Uczen"));
 		FULL1.setImie(result.getString("Imie"));
                FULL1.setNazwisko(result.getString("Nazwisko"));
        String Uczen;
     if (result.wasNull()) {
			Uczen=FULL1.getImie();
	    }
		else{Uczen=""+FULL1.getImie()+" "+FULL1.getNazwisko();}
     
       Uczniowie.addItem(Uczen);
       studentkeys.add(a,FULL1.getId_Uczen());
     
       a++;
       }
 	Uczniowie.addItem("");
   studentkeys.add(a,-1);
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
 
		
			ResultSet result = stmt.executeQuery("SELECT s.Id_Sprawdzian, s.Id_Nauczyciel,s.Id_Uczen,s.Punkty,s.Ocena,hp.Imie,hp.Nazwisko,u.Imie as Imiee,u.Nazwisko as Nazwiskoo FROM SPRAWDZIAN s JOIN NAUCZYCIEL hp on s.Id_Nauczyciel = hp.Id_Nauczyciel JOIN UCZEN u on s.Id_Uczen = u.Id_Uczen");
        String uczen;
        String nauczyciel;
    	SPRAWDZIAN FULL =new SPRAWDZIAN();
        NAUCZYCIEL FULL1 =new NAUCZYCIEL();
        UCZEN FULL2 =new UCZEN();
         while(result.next()) {
           FULL.setId_Sprawdzian(result.getInt("Id_Sprawdzian"));
FULL1.setId(result.getInt("Id_Nauczyciel"));
FULL2.setId_Uczen(result.getInt("Id_Uczen"));
           FULL.setPunkty(result.getInt("Punkty"));
           FULL.setOcena(result.getFloat("Ocena"));
           FULL1.setImie(result.getString("Imie"));
           FULL1.setNazwisko(result.getString("Nazwisko"));
           FULL2.setImie(result.getString("Imiee"));
           FULL2.setNazwisko(result.getString("Nazwiskoo"));
           uczen=""+FULL2.getImie()+" "+FULL2.getNazwisko();
           nauczyciel=""+FULL1.getImie()+" "+FULL1.getNazwisko();
           Object[] full = {FULL.getId_Sprawdzian(),FULL1.getId_Nauczyciel(),FULL.getPunkty(),FULL.getOcena(),nauczyciel,uczen,FULL2.getId_Uczen()};
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
	       
		
     
		
		
				SPRAWDZIAN DELETE = new SPRAWDZIAN();
		DELETE.setId_Sprawdzian(id);
			 String sql =("DELETE FROM  PRZEDMIOT WHERE Id_Sprawdzian = "+DELETE.getId_Sprawdzian());
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
	
	
	
	

	public int add(String Punkty, String Ocena, int Id_Nauczyciel, int Id_Uczen, JPanel inputPanel){
		
                   int pkt;
                   float ocen;
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
                pkt=Integer.parseInt(Punkty);
                ocen=Float.parseFloat(Ocena);
		SPRAWDZIAN ADD = new SPRAWDZIAN(pkt, ocen,Id_Nauczyciel,Id_Uczen);
		String sql = null;
		
			sql=("INSERT INTO SPRAWDZIAN (Punkty,Ocena,Id_Nauczyciel,Id_Uczen) VALUES ('"+pkt+"','"+ocen+"','"+ADD.getId_NauczycielS()+"', "+ADD.getId_UczenS())+")";
		
			
				
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

	
			
	
	

	public int update(int id, String Punkty, String Ocena, int Id_Nauczyciel, int Id_Uczen, JPanel inputPanel){
		
		
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
	


  
		if(flag==0){
                    int pkt;
                    float ocen;
                    pkt=Integer.parseInt(Punkty);
                ocen=Float.parseFloat(Ocena);
		
				SPRAWDZIAN UPDATE = new SPRAWDZIAN(pkt,ocen,Id_Nauczyciel,Id_Uczen);
		String sql = null;
	
			sql=("UPDATE SPRAWDZIAN SET Punkty='"+pkt+"', Ocena='"+ocen+"',Id_Nauczyciel='"+UPDATE.getId_NauczycielS()+"',Id_Uczen="+UPDATE.getId_UczenS()+" WHERE Id_Sprawdzian="+id+" ");
		
			
				
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



	

