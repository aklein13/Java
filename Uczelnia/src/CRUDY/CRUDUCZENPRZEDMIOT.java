package CRUDY;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Modele.PRZEDMIOT;
import Modele.UCZEN_PRZEDMIOT;
import Modele.UCZEN;

public class CRUDUCZENPRZEDMIOT {
	
	  public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:test.db";
	private static Connection conn;
    private static Statement stmt;
	
	
	public void ComboxUczenFill(JComboBox<String> Uczniowie, ArrayList<Integer> studentkeys){
		studentkeys.clear();
		 int itemCount = Uczniowie.getItemCount();

		    for(int i=0;i<itemCount;i++){
		        Uczniowie.removeItemAt(0);
		    }
	   
		int a=0;
	      String Uczen;
		try {
	            conn = DriverManager.getConnection(DB_URL);
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
     

		
		
			try {

		ResultSet result = stmt.executeQuery("SELECT Id_Uczen,Imie ,Nazwisko FROM UCZEN");
  
 
 	UCZEN FULL1 =new UCZEN();
 	
 	
 	while(result.next()) {
        
 		
 		FULL1.setId_Uczen(result.getInt("Id_Uczen"));
 		FULL1.setImie(result.getString("Imie"));
        FULL1.setNazwisko(result.getString("Nazwisko"));
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

//read KONIEC		
	}
    
    
	public void ComboxPrzedmiotFill(JComboBox<String> Przedmioty, ArrayList<Integer> przedmiotskeys){
		przedmiotskeys.clear();
		int itemCount = Przedmioty.getItemCount();

		for(int i=0;i<itemCount;i++){
		   Przedmioty.removeItemAt(0);
		    }
		

	    int a = 0; 
		
		String Nazwa;
		try {
	            conn = DriverManager.getConnection(DB_URL);
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
	
			
		
	try {

		
			ResultSet result = stmt.executeQuery("SELECT Id_Przedmiot, Nazwa FROM PRZEDMIOT");
 

	PRZEDMIOT FULL1 =new PRZEDMIOT();
	
	
	while(result.next()) {
       
		
		FULL1.setId(result.getInt("Id_Przedmiot"));
	    
		FULL1.setNazwa(result.getString("Nazwa"));
      
       
      Nazwa=FULL1.getNazwa();
      Przedmioty.addItem(Nazwa);
      przedmiotskeys.add(a,FULL1.getId());
    
      a++;
      }
	 Przedmioty.addItem("");
     przedmiotskeys.add(a,-1);
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

//read KONIEC		
	}
   
    
	public void read(DefaultTableModel model){
		String uczen;
		   try {
	            conn = DriverManager.getConnection(DB_URL);
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
        
			try {
 
				stmt.executeUpdate("DELETE FROM UCZEN_PRZEDMIOT WHERE Id_Uczen=Null OR Id_Przedmiot=Null");
ResultSet result = stmt.executeQuery("SELECT s.Id_Uczen, s.Id_Przedmiot, hp.Imie,hp.Nazwisko, h.Nazwa, s.Ocena_Koncowa,s.Zaliczenie,s.Uwagi FROM UCZEN_PRZEDMIOT s JOIN  UCZEN hp on s.Id_Uczen = hp.Id_Uczen JOIN  PRZEDMIOT h on s.Id_Przedmiot = h.Id_Przedmiot");
     
    	UCZEN FULL1 =new UCZEN();
    	PRZEDMIOT FULL =new PRZEDMIOT();
    	UCZEN_PRZEDMIOT FULL2 =new UCZEN_PRZEDMIOT();
    	
    	while(result.next()) {
           
    		
    		FULL2.setId_UczenP(result.getInt("Id_Uczen"));
    		 FULL2.setId_PrzedmiotP(result.getInt("Id_Przedmiot"));
    		FULL1.setImie(result.getString("Imie"));
    		FULL1.setNazwisko(result.getString("Nazwisko"));
                FULL2.setOcena_Koncowa(result.getFloat("Ocena_Koncowa"));
                FULL2.setZaliczenie(result.getString("Zaliczenie"));
                FULL2.setUwagi(result.getString("Uwagi"));
    		if (result.wasNull()) {
    			uczen=FULL.getNazwa();
    	    }
    		else{uczen=""+FULL1.getImie()+" "+FULL1.getNazwisko();}
    		
    		FULL.setNazwa(result.getString("Nazwa"));
    
   Object[] full = {FULL2.getId_UczenP(),FULL2.getId_PrzedmiotP(),uczen,FULL.getNazwa(),FULL2.getOcena_Koncowa(),FULL2.getZaliczenie(),FULL2.getUwagi()};
           
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



	
	public void delete(int id,int id1,JPanel inputPanel){
		  
		try {
	            conn = DriverManager.getConnection(DB_URL);
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
     
		
		
		   UCZEN_PRZEDMIOT DELETE = new UCZEN_PRZEDMIOT(id,id1, null, null, null);
		 
		String sql =("DELETE FROM  UCZEN_PRZEDMIOT WHERE Id_Uczen="+DELETE.getId_UczenP()+"  AND Id_Przedmiot = "+DELETE.getId_PrzedmiotP());
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
	
	
	
	

	public int add(int id, int id1, String Oczena_Koncowa, String Zaliczenie, String Uwagi, JPanel inputPanel){
		int key = 0;
		int titlenumber=0;
		
		int flag=0;
	
		
		
		try {
	            conn = DriverManager.getConnection(DB_URL);
	            
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }

			
			if(flag==0){
		ResultSet rs0;
		try {
			rs0 = stmt.executeQuery("SELECT * FROM UCZEN_PRZEDMIOT where (Id_Uczen="+id+" AND Id_Przedmiot="+id1+") ");
			while(rs0.next()){
		    	titlenumber =titlenumber+1;
		    }
		
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.err.println(id);
		System.err.println(id1);
		System.err.println(titlenumber);
		

				
		
		if(flag==0){
                    float ocena;
                    ocena=Float.parseFloat(Oczena_Koncowa);
			UCZEN_PRZEDMIOT ADD = new UCZEN_PRZEDMIOT (id,id1, ocena, Zaliczenie, Uwagi);
		String sql;
		sql=("INSERT INTO UCZEN_PRZEDMIOT(Id_Uczen,Id_Przedmiot,Ocena_Koncowa,Zaliczenie,Uwagi) VALUES ('"+ADD.getId_UczenP()+"', '"+ADD.getId_PrzedmiotP()+"', '"+ocena+"', '"+ADD.getZaliczenie()+"', '"+ADD.getUwagi()+"')");
	           
				
				
				 try {
					stmt.executeUpdate(sql);
				
					ResultSet rs = stmt.getGeneratedKeys();
					if ( rs.next() ) {
					    // Retrieve the auto generated key(s).
					     key = rs.getInt(1);
					}
				 
				 
				 } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
		}

			}


     
     try {
         

 stmt.close();

     } catch (SQLException e) {
         System.err.println("SQLException");
         e.printStackTrace();
     }
	if(flag==1){key=-1;}
     return key;

//read KONIEC	
}

	public int update(int id, int id1, int ida, int idb, String Ocena_Koncowa, String Zaliczenie, String Uwagi, JPanel inputPanel){
		String sql;
		int titlenumber=0;
	
		int flag=0;
	
		
		
		try {
	            conn = DriverManager.getConnection(DB_URL);
	            
	            stmt = conn.createStatement();
	        
	         
	           
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	       
		
		ResultSet rs0;
		try {
			rs0 = stmt.executeQuery("SELECT COUNT(*)AS count FROM UCZEN_PRZEDMIOT where Id_Uczen='"+ida+"' AND Id_Przedmiot='"+idb+"' ");
			while(rs0.next()){
		    	titlenumber = rs0.getInt("count");
		    }
		
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		if(flag==0){
		Float ocena;
                ocena=Float.parseFloat(Ocena_Koncowa);
			UCZEN_PRZEDMIOT UPDATE = new UCZEN_PRZEDMIOT(ida,idb, ocena, Zaliczenie, Uwagi);	
	  
		
		
		
sql=("UPDATE UCZEN_PRZEDMIOT SET Id_Uczen ='"+UPDATE.getId_UczenP()+"',Id_Przedmiot='"+UPDATE.getId_PrzedmiotP()+"',Ocena_Koncowa='"+ocena+"',Zaliczenie='"+UPDATE.getZaliczenie()+"',Uwagi='"+UPDATE.getUwagi()+"' WHERE Id_Uczen="+id+" AND Id_Przedmiot="+id1);	
		
		
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

