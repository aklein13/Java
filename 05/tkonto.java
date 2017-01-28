class Konto 
{
	private String wlasciciel;
	private float stan;
  
	void operacja(float ile)
	{ 
	  stan+=ile; 
	}
	void przelej(float ile, Konto dokad)
	{
		operacja(-ile); 
		dokad.operacja(ile); 
	}
	void przelej(Konto skad, Konto dokad, float ile)
	{
		dokad.operacja(ile); skad.operacja(-ile);
	}
	public String toString()
	{ 	
		return wlasciciel + " " + stan; 
	}
	Konto(String w, float stan)
	{ 	
		wlasciciel=w; 
		this.stan=stan; 
	}
	Konto()
	{ 	
		wlasciciel=" "; 
		this.stan=0; 
	}
}

class KontoH extends Konto
{
	float[] tab = new float[5];
	int i;
	Konto kont = new Konto();
	KontoH(String w, float stanPocz)
	{
		super(w,stanPocz);
	}
	void operacja(float ile)
	{
		tab[0]=tab[1];
		tab[1]=tab[2];
		tab[2]=tab[3];
		tab[3]=tab[4];
		tab[4]=ile;
		super.operacja(ile);
	}
	void historia(Konto h)
	{
		for(i=0;i<5;i++)
			System.out.println("Operacja nr " + i + ": " + tab[i]);
	}
}

class tkonto
{
	public static void main(String[] a)
	{
		Konto k = new Konto("Stefan",100);
		KontoH h = new KontoH("Marian",2000);
		KontoH h1 = new KontoH("Janusz",50);
		System.out.println(k);
		System.out.println(h);
		System.out.println(h1);
		h.przelej(5,k);// Stefan dostal 5
		System.out.println(k);  
		h1.przelej(10,h); //Marian dostal 10
		System.out.println(h1); //Janusz=40
		System.out.println(h); // 2100
		h.przelej(h1,h,30);
		h.przelej(k,h,30);
		h.przelej(k,h,20);
		h.przelej(k,h,5);
		System.out.println(h1); //Janusz=10
		System.out.println(h); // 2130
		h.historia(h1);
		System.out.println(h);
  }
}

