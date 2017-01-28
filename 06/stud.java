// ilustracja ArrayList i iteratora w Javie 
import java.util.*;
class Student
{
	int nr;
	String imie;
	ArrayList<Integer> oceny = new ArrayList<Integer>();
	public String toString() 
	{ 
		return "Index: " + nr  + " " + imie + " Oceny: " + oceny + "\n"; 
	}
	Student(int nr, String imie) 
	{ 
	   this.nr=nr; this.imie=imie; 
	}
	public boolean equals(Object o) //szukaj
    {
       if (!(o instanceof Student)) return false;
       Student p = (Student)o;
       return (p.nr==nr);
    }
	
}

class WykazS
{
	//lp.add(new Punkt(0,0)) ;  // wstaw
    //lp.set(0,new Punkt(0,3)); // zastap
    ArrayList<Student> wykaz = new ArrayList<Student>();
    void wstawStudenta(int i, String name)
    {
		wykaz.add(new Student(i,name));
	}
	void wstawOcene(int nr, int ocena)
	{
		Student p,q;
		q = new Student(nr,"");
		int k = wykaz.indexOf(q);   // szukaj (uzywa equals() !)
		//System.out.println(q + " na pozycji " + k);
		wykaz.get(k).oceny.add(new Integer(ocena));
	}
	void wypisz(int nr)
	{
		Student p,q;
		q = new Student(nr,"");
		int k = wykaz.indexOf(q);   // szukaj (uzywa equals() !)
		System.out.println(wykaz.get(k));
	}
	void wypisz()
	{
		System.out.println(wykaz);
	}
	
}

class stud
{
	public static void main(String[] a)
	{
		WykazS ws = new WykazS();
		ws.wstawStudenta(199200,"Stefan");
		ws.wstawStudenta(199201,"Marian");
		ws.wstawStudenta(199204,"Zygmunt");
		ws.wstawStudenta(199205,"Janusz");
		ws.wstawStudenta(189557,"Wiesiek");
		ws.wstawOcene(199204,2);
		ws.wstawOcene(199200,2);
		ws.wstawOcene(199200,2);
		ws.wstawOcene(199200,2);
		ws.wstawOcene(199200,2);
		ws.wstawOcene(199201,2);
		ws.wstawOcene(199201,2);
		ws.wstawOcene(199204,2);
		ws.wstawOcene(199204,2);
		ws.wstawOcene(199201,2);
		ws.wstawOcene(199205,3);
		ws.wstawOcene(189557,2);
		ws.wypisz();
		ws.wypisz(199205);
	}
}


