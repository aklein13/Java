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

class ZP extends Ulamek
{
	int i; // czy mozna cofac
	Ulamek z = new Ulamek(0,0);
	
	ZP(int a, int b)
	{
		super(a,b);
		z.licznik=a;
		z.mianownik=b;
		i=1;
	}
	
	void cofnij(Ulamek v)
	{
		if(sprawdz())
		{
			v.licznik=z.licznik;
			v.mianownik=z.mianownik;
			i=0;
		}
		else
		System.out.println("Nie mozna znowu cofnac!");
	}
	
	void mnozPrzez(Ulamek s)
	{
		super.mnozPrzez(s);
		i=1;
		if (sprawdz())
		{
			z.mianownik=mianownik/s.mianownik;
			z.licznik=licznik/s.licznik;
		}
	}
	
	boolean sprawdz()
	{
		if (i==0) return false;
		else return true;
	}
}

class zadEXT
{
	public static void main(String[] args)
	{
		ZP u = new ZP(4,7);
		Ulamek v = new Ulamek(2,3);
		System.out.println(u); //4/7
		u.mnozPrzez(v); //mnozy u przez v
		System.out.println(u); //8/21
		u.cofnij(u);
		System.out.println(u); //4/7
		//u.mnozPrzez(v);
		u.cofnij(u);
		System.out.println(u);
	}
}
