// Przyklad czytania pliku tekstowego po linijce

import java.io.*;

class Czytanie
{
    final int ogr = 200 ;            // ograniczenie na ilosc danych
    String[] tab = new String[ogr] ; // tablica napisow
    int ile ;                        // ilosc danych

    // czytanie z pliku do tablicy tab
    void czytaj(String plikWe) throws IOException
    {
		String s;
		int n;
      BufferedReader br = new BufferedReader(new FileReader(plikWe));
      String linia; 
      ile = 0; 
      while ((linia = br.readLine()) != null)  
         if (linia.length() > 0) 
         {
			 n=Integer.parseInt(linia);
			 s=Integer.toOctalString(n);
			tab[ile] = s;
			ile++;
		 }
      br.close() ;
    }
    
    // drukowanie tablicy tab[]
    void pisz()
    {
       int i,l,dl;
       for (i=0; i<ile ; i++)
       {
		   dl=tab[i].length();
		   for(l=4;l>dl;l--)
		   System.out.print(".");
		   //System.out.format("Dlugosc znaku nr %d to %d\n",i,dl);
          System.out.println(tab[i]) ;
         // System.out.print("\n");
	   }
    }

    public static void main(String[] args) throws Exception
    {
        Czytanie cz = new Czytanie() ;
        if (args.length >=1)
        {
              cz.czytaj(args[0]) ;
              cz.pisz();
        }
        else
            System.err.println("Uzycie: java Czytanie dane.txt");
    }
}
