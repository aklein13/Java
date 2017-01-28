// Elementarny przyklad definicji klasy

class A
{
  int n;   // pole
  void zwieksz() { n++; } // metoda
  static int plus1(int i) { return i+1; } // statyczna metoda
  public String toString()
  {
	return "n: " + n ;
  }
  A(int nn) { n=nn; } // konstruktor
  A() { this(0); } // drugi konstruktor (przeciazenie)
}

class TestA
{
  public static void main(String[] args) //main czyli tak ma sie nazywac plik (TestA)
  {
    System.out.println(A.plus1(6)); //plus1 jest wyzej
    A a1 = new A(5);  // tworzenie obiektu. a1 jest stingiem: "n: %d",n
    A a2 = new A(8);
    a1.zwieksz(); //wieksza zmienna a1
    System.out.println(a1); // automatycznie przeksztalcane na "a1.toString()"
    System.out.println(a2);
  }
}
