import java.util.*;
class Student implements Comparable <Student>
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
    public int compareTo(Student s) //porownanie
	{
		int d;
		d = imie.compareTo(s.imie); //porowanie stringow imion
		/*if(s.nr == nr) //porownanie wg nr ideksu
			d=0;
		else if(s.nr < nr)
			d=1;
		else
			d=-1;*/
		return (d>0 ? 1 : (d==0 ? 0 : -1));
	}
}

class CompX implements Comparator<Student>
{
   public int compare(Student s, Student l)
   {
		int i;
		int d=0;
		double s1, s2;
		for(i=0; i<s.oceny.size(); i++) 
			d=d+s.oceny.get(i);
		s1=d;
		s1=s1/s.oceny.size(); // srednia studenta s
		d=0;
		for(i=0; i<l.oceny.size(); i++)
			d=d+l.oceny.get(i);
		s2=d;
		s2=s2/l.oceny.size(); // srednia studenta l
		if(s1 == s2)
			d=0;
		else if(s1 < s2)
			d=1;
		else
			d=-1;
		//System.out.println(s.imie + " " + s1 + " " + l.imie + " " + s2);
		return (d>0 ? 1 : (d==0 ? 0 : -1));
   }
}

class WykazS
{
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
	void sortujIM()
	{
		Collections.sort(wykaz); //posortuje po imionach uzywajac cmp to w Student
	}
	void sortujOC()
	{
		Collections.sort(wykaz, new CompX());
	}
	void wyswietl(int n)
	{
		System.out.println("Szukam conajmniej " + n + " u studentow");
		int j;
		for (Iterator<Student> i = wykaz.iterator(); i.hasNext();)
		{
			Student q;
			q = i.next();
			for (j=0; j<q.oceny.size(); j++)
			{
				if(n <= q.oceny.get(j))
					System.out.println(q.imie + " " + "Ma " + q.oceny.get(j));
				else{}
			}
		}
	}
}

class stud
{
	public static void main(String[] a)
	{
		int i;
		WykazS ws = new WykazS();
		ws.wstawStudenta(199201,"Stefan"); // srednia 3.66
		ws.wstawStudenta(199205,"Marian"); // srednia 3
		ws.wstawStudenta(199200,"Zygmunt"); // zrednia 3.5
		ws.wstawStudenta(199204,"Janusz"); // srednia 2.cos
		ws.wstawStudenta(189557,"Wiesiek"); // srednia 5
		ws.wstawOcene(199204,2);
		ws.wstawOcene(199200,4);
		ws.wstawOcene(199200,3);
		ws.wstawOcene(199200,2);
		ws.wstawOcene(199200,5);
		ws.wstawOcene(199201,2);
		ws.wstawOcene(199201,4);
		ws.wstawOcene(199204,2);
		ws.wstawOcene(199204,3);
		ws.wstawOcene(199201,5);
		ws.wstawOcene(199205,3);
		ws.wstawOcene(189557,5);
		//ws.wypisz(199205);
		ws.wypisz();
		ws.sortujIM();
		ws.wypisz();
		ws.sortujOC();
		ws.wypisz();
		ws.wyswietl(4);
	}
}


