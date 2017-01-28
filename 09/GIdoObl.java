//Separacja klasy wykonujacej obliczenia (Obliczenia) i klasy
//Graficznego Interfejsu

import java .awt.* ;
import java.awt.event.* ;
import javax.swing.*;

class Obliczenia{
  public int oblicz1(int n) { return n+10 ; }
  public int oblicz2(int n) { return n-1 ; }
}

public class GIdoObl extends JFrame{
  JTextField
    dane  = new JTextField(20),
    wynik = new JTextField(20) ;
  Obliczenia ob = new Obliczenia() ;
  JButton
    obl1 = new JButton("obliczenie 1") ,
    obl2 = new JButton("obliczenie 2") ;
  GIdoObl(){
   setTitle("GI do Obliczen");
   Container cp = getContentPane();
   cp.setLayout(new GridLayout(4,2,10,10)) ;
   cp.add(new JLabel("Argument:")) ;
   cp.add(dane) ;
   obl1.addActionListener(new Obl1L());
   obl2.addActionListener(new Obl2L());
   cp.add(obl1) ;
   cp.add(obl2) ;
   cp.add(new JLabel("")) ; // odstep
   cp.add(new JLabel("")) ;
   cp.add(new JLabel("Wynik:")) ;
   cp.add(wynik) ;
   setDefaultCloseOperation(EXIT_ON_CLOSE);
   setVisible(true) ;   
  }
  int dajLiczbe(JTextField tf){
	try{
          return Integer.parseInt(tf.getText()) ;
        } catch (NumberFormatException e){ return 0 ; }
  }
  class Obl1L implements ActionListener{
   public void actionPerformed(ActionEvent e){
       wynik.setText(Integer.toString(
          ob.oblicz1(dajLiczbe(dane)))) ;
	  }
   }
  class Obl2L implements ActionListener{
   public void actionPerformed(ActionEvent e){
       wynik.setText(Integer.toString(
          ob.oblicz2(dajLiczbe(dane)))) ;
	  }
   }
 public static void main(String[] arg){
  JFrame gi = new GIdoObl() ;
  gi.setSize(200,200) ;
 }
}






