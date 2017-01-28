import java.io.*;

class Zamiana {
    public static void main(String[] args) throws Exception
    {
        if (args.length < 2)
            System.out.println("Poprawne Uzycie: Kopiuj PlikWejsciowy PlikWyjsciowy");
        else
        {
            FileReader wej = new FileReader(args[0]);
            FileWriter wy  = new FileWriter(args[1]);
                                
			int c;
			int l=0;
			while ((c = wej.read()) != -1)  
			{
				if (c=='\n') l++;
				else if (Character.isUpperCase((char) c))
				{
				c=Character.toLowerCase((char)c);
				}
				else if (Character.isLowerCase((char) c))
				{
				c=Character.toUpperCase((char)c);
				}
				wy.write(c);
			}

			wej.close();
			wy.close();
			System.out.println("skopiowano " + l + " wierszy");
        }
    }
}
