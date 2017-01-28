// modyfikator "private"
// "private" oznacza prywatnosc dla danej klasy a nie pojedynczego 
// obiektu, czyli w obrebie tej samej klasy nie musimy uzywac
// akcesorow. 

class P{
  private int i;
  int  getI(){return i; }   // akcesor, tzw. getter
  void setI(int ii){ i=ii;} // akcesor, tzw. setter
  void zmiana(P p){
      p.i++;  // dozwolone
  }
}

class X{
    void zmiana1(P p){
        p.setI(p.getI()+1); //dobrze
	p.i++; // niedozwolone
    }
}
