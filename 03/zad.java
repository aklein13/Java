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
		return licznik + "/" + mianownik ;
	}
	
	Ulamek(int l, int m) 
	{
			licznik=l;
			mianownik=m;
	}
}

class zad
{
	public static void main(String[] args)
	{
		Ulamek u = new Ulamek(1,2);
		Ulamek v = new Ulamek(2,3);
		u.mnozPrzez(v); //mnozy u przez v
		System.out.println(u); //2/6
		System.out.println(v); //2/3
		Ulamek w = Ulamek.razy(u,v); //mnozy u*v
		System.out.println(w); //4/18
		v.mnozPrzez(2); //mnozy v*2
		System.out.println(v); //4/3
	}
}
