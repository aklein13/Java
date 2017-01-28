import java .awt.* ;
import java.awt.event.* ;
import javax.swing.*;
import java.util.*;
import java.io.*;

class Debet extends Exception
{
	int n;
    Debet(int n){ this.n=n; }
}

class Konto implements Serializable
{
  private int stan; 
  int i;
	int[] historia = new int[999];
  Konto() { stan = 0; i=0;}
  public void operacja(int ile) throws Debet
  {
    if (stan + ile >= 0 )
    {
		historia[i]=ile;
       stan += ile;
   }
    else
		throw new Debet(stan+ile);
  }
  public int dajStan() { return stan ; }
  Konto(int n, int ii) { stan = n; i=ii;}
  public String toString()
  {
	return stan + "\n";
    }
}

class DrugiWyjatek extends Exception{
    int i;
    DrugiWyjatek(){ super();}
    DrugiWyjatek(String msg){ super(msg);}
    DrugiWyjatek(int i){ this.i=i; }
}

public class kont extends JFrame
{
  JTextField
    dane  = new JTextField(20),
    wynik = new JTextField(20),
    operacja = new JTextField(20) ;
  	   Konto k = new Konto(1000,0) ;
  JButton
    obl1 = new JButton("wplata") ,
    obl2 = new JButton("wyplata") ,
    obl3 = new JButton("odblokuj") ,
    obl4 = new JButton("cofnij") ,
    obl5 = new JButton("zapisz") ;
  kont()
  {
	  try{//ZAPIS STANU I HISTORI (no dobra, odczyt xD)
	    ObjectInputStream is = new ObjectInputStream(new FileInputStream("save"));
	    k = (Konto)is.readObject();
            is.close();
	} catch (IOException e){System.out.println("Brak savu do wczytania");}
  	  catch (ClassNotFoundException e){}
  	  
   setTitle("Konto");
   Container cp = getContentPane();
   cp.setLayout(new GridLayout(4,3,10,10)) ; //wiersze, kolumny, 10 to odstepy
   cp.add(new JLabel("stan")) ;
   dane.setText(String.valueOf(k.dajStan()));
   cp.add(dane) ;
   dane.setEnabled(false);
   cp.add(new JLabel("\n")) ;
   obl1.addActionListener(new wpl());
   obl2.addActionListener(new wypl());
   cp.add(obl1);
   cp.add(obl2);
   cp.add(operacja) ;
   cp.add(new JLabel("\n")) ;
   cp.add(new JLabel("rezultat:")) ;
   cp.add(wynik) ;
   cp.add(obl3);
   cp.add(obl4);
   cp.add(obl5);
   obl3.addActionListener(new odbl());
   obl4.addActionListener(new cofnij());
   obl5.addActionListener(new zapisz());
   setDefaultCloseOperation(EXIT_ON_CLOSE);
   setVisible(true) ;   
  }
  int wartosc(JTextField tf)
  {
	try{
          return Integer.parseInt(tf.getText()) ;
        } catch (NumberFormatException e){ return 0 ; }
  }
  class wpl implements ActionListener	//wplata
  {
   public void actionPerformed(ActionEvent e)
   {
       
       try
	   {
          k.operacja(wartosc(operacja));

      } catch (Debet b){}
       dane.setText(String.valueOf(k.dajStan()));
       k.i++;
       wynik.setText("OK");
       obl1.setEnabled(false);
       obl2.setEnabled(false);
       obl4.setEnabled(false);
       obl5.setEnabled(false);
	 }
   }
  class wypl implements ActionListener	//wyplata
  {
   public void actionPerformed(ActionEvent e)
   {
	   try
	   {
          k.operacja(-wartosc(operacja));
          wynik.setText("OK");
          k.i++;
      } 
      catch (Debet b){
          wynik.setText("ZA DUZO O: " + String.valueOf(-(b.n)));
      }
       dane.setText(String.valueOf(k.dajStan()));
       obl1.setEnabled(false);
       obl2.setEnabled(false);
       obl4.setEnabled(false);
       obl5.setEnabled(false);
	  }
   }
   class odbl implements ActionListener	//odblok
  {
   public void actionPerformed(ActionEvent e)
   {
       obl1.setEnabled(true);
       obl2.setEnabled(true);
       obl4.setEnabled(true);
       obl5.setEnabled(true);
       operacja.setText(null);
       wynik.setText(null);
	  }
   }
   class cofnij implements ActionListener	//cofnij
  {
   public void actionPerformed(ActionEvent e)
   {
       try
	   {
		   if(k.i > 0)
		   {
		   k.i--;
          k.operacja(-k.historia[k.i]); 
          wynik.setText("Cofnieto");
		}
		else
			wynik.setText("Nie mozna wicej cofnac");
   
	} catch (Debet b){}
       dane.setText(String.valueOf(k.dajStan()));
       obl1.setEnabled(false);
       obl2.setEnabled(false);
       obl4.setEnabled(false);
       obl5.setEnabled(false);
	  }
	  
   }
   class zapisz implements ActionListener
  {
   public void actionPerformed(ActionEvent e)
   {
       try
       {
	    FileOutputStream f = new FileOutputStream("save");
	    ObjectOutputStream os = new ObjectOutputStream(f);
	    os.writeObject(k);
            f.close();
            wynik.setText("Zapisano");
	} catch (IOException b){}
	}
   }
 public static void main(String[] arg)
 {
  JFrame gi = new kont() ;
  gi.setSize(400,200) ;
 }
}
