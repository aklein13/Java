package baza_danych;
import java.sql.*;
public class BAZA{

    
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:test.db";
    
    private static Connection conn;
    private static Statement stmt;
    private static Statement stmq;
    
   
   public void runz(){

    	String sql;
 
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
        
      
        try {
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            stmq = conn.createStatement();
           
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
       
        String createUCZEN="CREATE TABLE IF NOT EXISTS UCZEN(Id_Uczen INTEGER PRIMARY KEY AUTOINCREMENT,Imie VARCHAR(30),Nazwisko VARCHAR(30), Wiek INTEGER, Indeks VARCHAR(15) UNIQUE)";
        try {
            stmt.execute(createUCZEN);

        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        } 
        
        
        try {
        	sql="INSERT INTO UCZEN(Imie,Nazwisko,Wiek,Indeks) VALUES ('Stefan','Przykladowy',18,234512);";
            stmt.execute(sql);
        	sql="INSERT INTO UCZEN(Imie,Nazwisko,Wiek,Indeks) VALUES ('Jan','Kowalski',23,21234);";
            stmt.execute(sql);
        	sql="INSERT INTO UCZEN(Imie,Nazwisko,Wiek,Indeks) VALUES ('Patryk','Nowak',29,12345);";
            stmt.execute(sql);
        
       } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu ucznia");
            e.printStackTrace();
        }


 String createNAUCZYCIEL="CREATE TABLE IF NOT EXISTS NAUCZYCIEL(Id_Nauczyciel INTEGER PRIMARY KEY AUTOINCREMENT,Imie VARCHAR(30),Nazwisko Varchar(30), Tytul VARCHAR(40),Data_Zatrudnienia DATE DEFAULT null)";
  try {
      stmt.execute(createNAUCZYCIEL);

  } catch (SQLException e) {
      System.err.println("Blad przy tworzeniu tabeli");
      e.printStackTrace();
  }    
  
  
  try {
      sql="INSERT INTO NAUCZYCIEL(Imie,Nazwisko,Tytul,Data_Zatrudnienia) VALUES ('Marian','Madry','Profesor','2013-01-06');";
      stmt.execute(sql);
      sql="INSERT INTO NAUCZYCIEL(Imie,Nazwisko,Tytul,Data_Zatrudnienia) VALUES ('Igor','Mlody','Inzynier','2017-11-16');";
      stmt.execute(sql);
  } catch (SQLException e) {
      System.err.println("Blad przy dodawaniu nauczyciela");
      e.printStackTrace();
  }
  

        
        
        
        String createPRZEDMIOT="CREATE TABLE IF NOT EXISTS PRZEDMIOT(Id_Przedmiot INTEGER PRIMARY KEY AUTOINCREMENT ,Nazwa VARCHAR(20) NOT NULL,Opis VARCHAR(50),Ilosc_Godzin INTEGER, Id_Nauczyciel INT references NAUCZYCIEL(Id_Nauczyciel))";
        try {
            stmt.execute(createPRZEDMIOT);

        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        } 
        
      
        try {
        	sql="INSERT INTO PRZEDMIOT (Nazwa,Opis,Ilosc_Godzin,Id_Nauczyciel) VALUES ('Fizyka','Fizyka i fizyki',50,1);";
            stmt.execute(sql);
        	sql="INSERT INTO PRZEDMIOT (Nazwa,Opis,Ilosc_Godzin,Id_Nauczyciel) VALUES ('Matematyka','Matematykowanie',20,1);";
            stmt.execute(sql);
        	sql="INSERT INTO PRZEDMIOT (Nazwa,Opis,Ilosc_Godzin,Id_Nauczyciel) VALUES ('Informatyka','Nauka Windosa',30,2);";
            stmt.execute(sql);
        
       } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu przedmiotu");
            e.printStackTrace();
        }
       
      
       
        String createUCZENPRZEDMIOT="CREATE TABLE IF NOT EXISTS UCZEN_PRZEDMIOT(Id_Uczen INT references UCZEN (Id_Uczen),Id_Przedmiot INT references PRZEDMIOT(Id_Przedmiot), Ocena_Koncowa FLOAT, Zaliczenie VARCHAR(30), Uwagi VARCHAR(50) DEFAULT ' ')";
        try {
            stmt.execute(createUCZENPRZEDMIOT);

        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        } 
        
      

        try {
        	sql="INSERT INTO UCZEN_PRZEDMIOT (Id_Uczen,Id_Przedmiot,Ocena_Koncowa,Zaliczenie) VALUES (3,1,4,'Tak');";
            stmt.execute(sql);
            sql="INSERT INTO UCZEN_PRZEDMIOT (Id_Uczen,Id_Przedmiot,Ocena_Koncowa, Zaliczenie,Uwagi) VALUES (1,1,3.5,'Tak','Naciagane');";
            stmt.execute(sql);
        	sql="INSERT INTO UCZEN_PRZEDMIOT (Id_Uczen,Id_Przedmiot,Ocena_Koncowa, Zaliczenie) VALUES (3,2,4,'Tak');";
            stmt.execute(sql);
            sql="INSERT INTO UCZEN_PRZEDMIOT (Id_Uczen,Id_Przedmiot,Ocena_Koncowa, Zaliczenie) VALUES (2,3,5,'Tak');";
            stmt.execute(sql);
        
       } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu uczen_przedmiot");
            e.printStackTrace();
        }
       
        
        
        
        
        
        
        String createSPRAWDZIAN="CREATE TABLE IF NOT EXISTS SPRAWDZIAN(Id_Sprawdzian INTEGER PRIMARY KEY AUTOINCREMENT,Punkty INTEGER,Ocena FLOAT, Id_Nauczyciel INT references NAUCZYCIEL(Id_Nauczyciel),Id_Uczen INT references UCZEN(Id_Uczen))";
        try {
            stmt.execute(createSPRAWDZIAN);

        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        } 
        
        
        try {
        	sql="INSERT INTO Sprawdzian(Punkty,Ocena,Id_Nauczyciel,Id_Uczen) VALUES (44,3.5,2,1);";
            stmt.execute(sql);
        	sql="INSERT INTO Sprawdzian(Punkty,Ocena,Id_Nauczyciel,Id_Uczen) VALUES (23,2,2,2);";
            stmt.execute(sql);
        	sql="INSERT INTO Sprawdzian(Punkty,Ocena,Id_Nauczyciel,Id_Uczen) VALUES (92,5,1,1);";
            stmt.execute(sql);
        
       } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu sprawdzianu");
            e.printStackTrace();
        }

        try {
            
    conn.commit();
    stmt.close();
    stmq.close();
        } catch (SQLException e) {
            System.err.println("SQLException");
            e.printStackTrace();
        }
      
         System.out.println("Stworzono test.db");
        
        }
    }
