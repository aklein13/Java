class Ulamek
{
	int licznik, mianownik;
	
	void mnozPrzez(Ulamek v) 
	{
		licznik=v.licznik*licznik;
		mianownik=v.mianownik*mianownik;
	}
	
	void mnozPrzez(int i) 
	{
		licznik=licznik*i;
	}
	
	static Ulamek razy(Ulamek u, Ulamek v)
	{
		Ulamek w = new Ulamek(1,1);
		w.licznik=u.licznik*v.licznik;
		w.mianownik=u.mianownik*v.mianownik;
		return w;
	}
	
	public String toString()
	{
		return licznik + "%" + mianownik ;
	}
	
	Ulamek(int l, int m) 
	{
			licznik=l;
			mianownik=m;
	}
}


class LU
{
	int cal;
	Ulamek CU;
	int temp;
	
	void mnozPrzez(LU l)
	{
		cal=cal*CU.mianownik;
		CU.licznik=CU.licznik+cal;
		cal=0;
		l.cal=l.cal*l.CU.mianownik;
		l.CU.licznik=l.CU.licznik+l.cal;
		CU.mnozPrzez(l.CU);
		while(CU.mianownik<=CU.licznik)
		{
			CU.licznik=CU.licznik-CU.mianownik;
			cal++;
		}
	}
	
	void mnozPrzez(int i)
	{
		cal=cal*i;
		CU.mnozPrzez(i);
		while(CU.mianownik<=CU.licznik)
		{
			CU.licznik=CU.licznik-CU.mianownik;
			cal++;
		}
	}
	
	void mnozPrzez(Ulamek u)
	{
		cal=cal*CU.mianownik;
		CU.licznik=CU.licznik+cal;
		CU.mnozPrzez(u);
		cal=0;
		while(CU.mianownik<=CU.licznik)
		{
			CU.licznik=CU.licznik-CU.mianownik;
			cal++;
		}
	}
	
	public String toString()
	{
		return cal + " i " + CU.licznik + "/" + CU.mianownik;
	}
	
	LU(int c, Ulamek ul)
	{
		if(ul.licznik < ul.mianownik)
		{
			CU=ul;
			cal=c;
		}
		else
		System.out.println("Blad, podano ulamek niewlasciwy do LU\n");
	}
}

class zad2
{
	public static void main(String[] args)
	{
		Ulamek u = new Ulamek(1,2);
		Ulamek v = new Ulamek(3,4);
		Ulamek z = new Ulamek(2,3);
		LU t = new LU(2,v);
		LU n = new LU (2,z);
		System.out.println(t);//2 i 3/4
		t.mnozPrzez(u);
		System.out.println(t); //1 i 3/8
		t.mnozPrzez(2);
		System.out.println(t); //2 i 6/8
		t.mnozPrzez(n); // 2 i 2/3 
		System.out.println(t); //7 i 1/3 (8/24)
	}
}
