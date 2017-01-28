class Ulamek
{
	private int licznik, mianownik;
	
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
	public int getmian()
	{
		return mianownik;
	}
	void setmian(int i) 
	{
		mianownik = i;
    }
    public int getlicz()
	{
		return licznik;
	}
	void setlicz(int i) 
	{
		licznik = i;
    }
}


class LU
{
	private int cal;
	Ulamek CU;
	public int temp;
	
	void mnozPrzez(LU l)
	{
		setcal(getcal()*CU.getmian());
		CU.setlicz(CU.getlicz()+getcal());
		setcal(0);
		l.setcal(l.getcal()*CU.getmian());
		l.CU.setlicz(l.CU.getlicz()+l.getcal());
		CU.mnozPrzez(l.CU);
		while(CU.getmian()<=CU.getlicz())
		{
			CU.setlicz(CU.getlicz()-CU.getmian());
			setcal(getcal()+1);
		}
	}
	
	void mnozPrzez(int i)
	{
		setcal(getcal()*i);
		CU.mnozPrzez(i);
		while(CU.getmian()<=CU.getlicz())
		{
			CU.setlicz(CU.getlicz()-CU.getmian());
			setcal(getcal()+1);
		}
	}
	
	void mnozPrzez(Ulamek u)
	{
		setcal(getcal()*CU.getmian());
		CU.setlicz(CU.getlicz()+getcal());
		CU.mnozPrzez(u);
		cal=0;
		while(CU.getmian()<=CU.getlicz())
		{
			CU.setlicz(CU.getlicz()-CU.getmian());
			setcal(getcal()+1);
		}
	}
	
	public String toString()
	{
		return cal + " i " + CU.getlicz() + "/" + CU.getmian();
	}
	
	LU(int c, Ulamek ul)
	{
		if(ul.getlicz() < ul.getmian())
		{
			CU=ul;
			setcal(c);
		}
		else
		System.out.println("Blad, podano ulamek niewlasciwy do LU\n");
	}
	
	public int getcal()
	{
		return cal;
	}
	void setcal(int i) 
	{
		cal = i;
    }
}

class zad2priv
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
		System.out.println(t); //16 i 1/3 (12/24)
	}
}
