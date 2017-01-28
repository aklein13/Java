import java.sql.*;
// przyklad z http://www.tutorialspoint.com/sqlite/sqlite_java.htm

public class sql1
{
    public static void main(String args[])
    {
	Connection c = null;
	Statement stmt = null;
	int max = 1988;
	System.out.println( "Max rocznik to: " + max);
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
			String  imie = rs.getString("imie");
			int pensja  = rs.getInt("pensja");
			int rocznik = rs.getInt("rocznik");
			if(rocznik > max)
			{
				stmt = c.createStatement();
				String sql = "UPDATE pensje SET pensja = pensja*1.1 WHERE rocznik > 1988;";
				stmt.executeUpdate(sql);
				System.out.println( imie + "  " +pensja + " " + rocznik);
			}
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
