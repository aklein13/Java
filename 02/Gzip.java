// Przyklad robienia kompresji pliku
// Program czyta z pliku "Gzip.java" poddaje czytany plik kompresji gzip 
// i zapisuje do pliku "Gzip.java.gz"
import java.util.zip.*;
import java.io.*;

class Gzip
{
	
    public static void main(String[] args) throws IOException
    {
		if (args.length >=1)
{
    BufferedInputStream wej = new BufferedInputStream(new FileInputStream(args[0]));
	BufferedOutputStream wy = 
	new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(args[0]+".gz")));
	int c;
	while ((c = wej.read())!= -1)
	    wy.write(c);
	wej.close();
	wy.close();
}
	else
            System.err.println("Uzycie: java Gzip nazwa.pliku");
    }
}
