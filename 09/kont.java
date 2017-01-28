import java .awt.* ;
import java.awt.event.* ;
import javax.swing.*;

class Debet extends Exception
{
	int n;
    Debet(int n){ this.n=n; }
}

class Konto{
  private int stan; 
  Konto() { stan = 0; }
  public void operacja(int ile) throws Debet
  {
    if (stan + ile >= 0 )
       stan += ile;
    else
		throw new Debet(stan+ile);
  }
  public int dajStan() { return stan ; }
  Konto(int n) { stan = n; }
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
  Konto k = new Konto(1000) ;
  JButton
    obl1 = new JButton("wplata") ,
    obl2 = new JButton("wyplata") ,
    obl3 = new JButton("odblokuj") ;
  kont(){
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
   obl3.addActionListener(new odbl());
   setDefaultCloseOperation(EXIT_ON_CLOSE);
   setVisible(true) ;   
  }
  int wartosc(JTextField tf){
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

      } catch (Debet b){
          wynik.setText("NIE");
      }
       dane.setText(String.valueOf(k.dajStan()));
       wynik.setText("OK");
       obl1.setEnabled(false);
       obl2.setEnabled(false);
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
      } 
      catch (Debet b){
          wynik.setText("ZA DUZO O: " + String.valueOf(-(b.n)));
      }
       dane.setText(String.valueOf(k.dajStan()));
       
       obl1.setEnabled(false);
       obl2.setEnabled(false);
	  }
   }
   class odbl implements ActionListener	//odblok
  {
   public void actionPerformed(ActionEvent e)
   {
       obl1.setEnabled(true);
       obl2.setEnabled(true);
       operacja.setText(String.valueOf(0));
	  }
   }
 public static void main(String[] arg)
 {
  JFrame gi = new kont() ;
  gi.setSize(400,200) ;
 }
}
