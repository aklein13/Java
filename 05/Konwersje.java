/* Konwersje - 
   --------------------------------------------*/
class A{ 
  void m(){System.out.println("Am");}
}
class B extends A{
    void f(){System.out.println("Bf");}
    void m(){System.out.println("Bm");}
}
class TestKonw{
    public static void main(String[]  args){
	A a = new A();
        B b = new B();
        A a1;
        B b1;
        a1=b;   // niejawna konwersja "w gore" hierarchii  klas
	a1.m(); // --> Bm (dynamiczne wiazanie)
    //  a1.f(); // blad kompilacji - klasa A nie ma metody f()
        ((B)a1).f(); // --> Bf  (po jawnej konwesji "w dol")
    //  b1=a; // blad kompilacji - niezgodnosc typow
    //  b1=(B)a; // blad  w czasie wykonania - niewykonalna konwersja
    }
}
