// ilustracja sortowania przy pomocy Collections.sort()
// i interfejsow Comparable i Comparator (JDK 5)

import java.util.*;

class Punkt implements Comparable<Punkt>
{
   int x,y ;
   public boolean equals(Object o) 
   {
       if (!(o instanceof Punkt)) return false;
       Punkt p = (Punkt)o;
       return (p.x==x) && (p.y==y);
   }
   public int compareTo(Punkt p)
   {
   // porownanie ze wzgledu na odleglosc od (0,0)
      int d = (x*x+y*y) - (p.x * p.x + p.y * p.y);
      return (d>0 ? 1 : (d==0 ? 0 : -1));
   }
   public String toString() { return "(" + x  + "," + y +")"; }
   Punkt(int x,int y) { this.x=x; this.y=y; }
}

class CompX implements Comparator<Punkt>
{
   public int compare(Punkt p, Punkt q)
   {
   // porownanie ze wzgledu na wspolrzedna x
     return (p.x>q.x ? 1 : (p.x==q.x ? 0 : -1));
   }
}

class ListSort{
  public static void main(String[] args)
  {
      ArrayList<Punkt> lp = new ArrayList<Punkt>();
      lp.add(new Punkt(1,1)) ;
      lp.add(new Punkt(0,0)) ;
      lp.add(new Punkt(0,3));
      System.out.println(lp);
      Collections.sort(lp);  // sortowanie wg compareTo()
      System.out.println(lp);
      Collections.sort(lp, new CompX());  // sortowanie wg compare()
      System.out.println(lp);
  }
}

