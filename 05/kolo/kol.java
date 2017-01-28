class Ulamek
{
	int licznik, mianownik;
	
	void naPol()
	{
		mianownik=mianownik*2;
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
		return licznik + "/" + mianownik;
	}
	
	Ulamek(int l, int m) 
	{
		if (m == 0)
			System.out.println("Blad, podano mianownik = 0\n");
		else
		{
			licznik=l;
			mianownik=m;
		}
	}
	
	Ulamek() 
	{
		this(0,1);
	}
}

class DwaUlamki
{
	Ulamek u1, u2;
	
	void pomniejsz()
	{
		u1.naPol();
		u2.naPol();
	}
	
	public String toString()
	{
		return u1.licznik + "/" + u1.mianownik + " oraz " + u2.licznik + "/" + u2.mianownik;
	}
	
	DwaUlamki(Ulamek a, Ulamek b)
	{
		u1=a;
		u2=b;
	}
}

class kol
{
	public static void main(String[] args)
	{
		Ulamek u = new Ulamek(1,2);
		Ulamek v = new Ulamek(2,3);
		System.out.println(u); // 1/2
		u.naPol();
		System.out.println(u); // 1/4
		DwaUlamki z = new DwaUlamki(u,v);
		System.out.println(z); // 1/4 oraz 2/3
		z.pomniejsz();
		System.out.println(z); // 1/8 oraz 2/6
	}
}
