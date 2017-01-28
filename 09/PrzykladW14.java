// podstawowe operacje zwiazane z wyjatkami
import java.io.* ;

// definicja wyjatku
class MojWyjatek extends Exception{}

class A{
    void m(int i) throws  MojWyjatek{
        System.out.println(" m-poczatek");
        // zgloszenie wyjatku przez metode
        if (i<0) throw new MojWyjatek();
        System.out.println(" m-koniec");
    }
    void f(int i) throws  MojWyjatek{
        System.out.println("f-poczatek");
        m(i); // brak obslugi wyjatku
        System.out.println("f-koniec");
    }
    void d1(int n) throws  DrugiWyjatek{
        System.out.println("d1-poczatek");
        // zgloszenie wyjatku z przekazaniem wartosci
        if (n>0) throw new DrugiWyjatek(n);
        System.out.println("d1-koniec");
    }
    void d2(int n) throws  DrugiWyjatek{
        System.out.println("d2-poczatek");
        // zgloszenie wyjatku z przekazaniem wartosci
        if (n>0) throw new DrugiWyjatek("argument "+n);
        System.out.println("d2-koniec");
    }
}

class DrugiWyjatek extends Exception{
    int i;
    DrugiWyjatek(){ super();}
    DrugiWyjatek(String msg){ super(msg);}
    DrugiWyjatek(int i){ this.i=i; }
}

class PrzykladW14{
  public static void main(String[] args)throws IOException{
      A a = new A();
      // przechwycenie i obsluga wyjatku
      try{
          a.f(1);
          a.f(-1);
      } catch (MojWyjatek e){
          System.out.println("  main-obsluga wyjatku MojWyjatek\n");
          // ....
      }
      try{
          a.d1(3);

      } catch (DrugiWyjatek e){
          System.out.println("  main - Drugi Wyjatek przekazal wartosc "+ e.i);
      }
      try{
          a.d2(4);
      } catch (Exception e){
          System.out.println("  main - Drugi Wyjatek przekazal: "+ e.getMessage());
      }

      // dalszy ciag programu ....
  }
}









