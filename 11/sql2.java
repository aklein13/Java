import java.sql.*;

public class sql2
{
    public static void main(String args[])
    {
	Connection c = null;
	Statement stmt = null;
	int[] dlugosci = new int[99];
	int[] pensje = new int[99];
	int i=0;
	int j,max;
	double suma=0;
	try 
	{
	    Class.forName("org.sqlite.JDBC");
	    c = DriverManager.getConnection("jdbc:sqlite:main");
	    c.setAutoCommit(true);
	    System.out.println("Opened database successfully");
	    stmt = c.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT * FROM pensje;");
	    while ( rs.next() ) 
	    {
			String imie = rs.getString("imie");
			dlugosci[i] = imie.length();
			i++;
			int pensja  = rs.getInt("pensja");
			int rocznik  = rs.getInt("rocznik");
			System.out.println( imie + "  " +pensja + " " + rocznik);
	    }
	    for(j=0;j<i;j++)
	    {
			suma=suma+dlugosci[j];
		}
		suma=suma/i;
	    System.out.println("\nSrednia dlugosc nazwisk to: " + suma);
	    rs = stmt.executeQuery("SELECT * FROM pensje;"); //powtorzyc to, by while od nowa polecial
	    i=0;
	    System.out.println("\nOsoby z imie dluzszym niz srednia:");
	    while ( rs.next() ) 
	    {
			String imie = rs.getString("imie");
			if(imie.length() > suma)
			{
				pensje[i]  = rs.getInt("pensja");
				int rocznik  = rs.getInt("rocznik");
				System.out.println( imie + "  " +pensje[i] + " " + rocznik);
				i++;
			}
			else{}
	    }
	    max=pensje[0];
	    for(j=1;j<i;j++)
			if(max<pensje[j])
				max=pensje[j];
		String sql = "insert into pensje values ('NNNN', '"+max+"' ,1970);";
		stmt.executeUpdate(sql);
		System.out.println("\nDodano NNNN, wypisanie wszystkich:");
	    rs = stmt.executeQuery("SELECT * FROM pensje;");
	    while ( rs.next() ) 
	    {
			String imie = rs.getString("imie");
			int pensja  = rs.getInt("pensja");
			int rocznik  = rs.getInt("rocznik");
			System.out.println( imie + "  " +pensja + " " + rocznik);
	    }
		
	    rs.close();
	    stmt.close();
	    c.close();
	    
	} 
	catch ( Exception e ) 
	{
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    System.exit(0);
	}
	System.out.println("Operation done successfully");
    }
}
