// Arkadiusz Klein
// Plansza do gry A, B, C
// archiwum.wiz.pl/2001/01124800.asp
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.io.*;

class Model implements Serializable
{
	char  tab[][] = new char[5][4];
	int wynik=0;
	int takiesame=0;
	String log;
	int[][] historia = new int[999][999];
	int h=0;
}

class Plansza extends JFrame 
{
	Model model = new Model();
	JButton tab[][] = new JButton[5][4];
	JPanel plansza = new JPanel();
	JPanel sterowanie = new JPanel();
	JTextField t = new JTextField(30);
	JFrame frame;
	JButton cof = new JButton("");
	JButton next = new JButton("");
	JButton save = new JButton("Zapisz");
	JButton load = new JButton("Wczytaj");
	JButton help = new JButton("Pomoc");
	JTextField login = new JTextField(20);
	JButton ok = new JButton("OK");
	JLabel podaj = new JLabel("Podaj swoj login, zeby moc zapisac");
	int juz=0; //czyszczenie pola login
	ImageIcon aa = new ImageIcon("a.png");
	ImageIcon bb = new ImageIcon("b.png");
	ImageIcon cc = new ImageIcon("c.png");
	void stan_pocz()
	{
		tab[1][3].setIcon(aa);
		tab[1][2].setIcon(bb);
		tab[2][0].setIcon(bb);
		tab[2][3].setIcon(cc);
		tab[3][0].setIcon(cc);
		tab[4][1].setIcon(aa);
	}
	public Plansza() 
	{
		setTitle("A, B, C");
		int i,j;
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(1,2,5,5));
		cp.add(plansza); 
		cp.add(sterowanie);
		sterowanie.setLayout(new GridLayout(5,1,5,5)); //to 5 to wielkosc przyciskow
		sterowanie.add(t);
		sterowanie.add(help);
		help.addActionListener(new pomoc());
		sterowanie.add(cof);
		cof.addActionListener(new cofnij());
		sterowanie.add(next);
		next.addActionListener(new doprzodu());
		sterowanie.add(save);
		save.addActionListener(new zapisz());
		sterowanie.add(load);
		load.addActionListener(new wczytaj());
		save.setEnabled(false);
		load.setEnabled(false);
		sterowanie.add(login);
		login.setText("Login");
		sterowanie.add(ok);
		ok.addActionListener(new podane());
		sterowanie.add(podaj);
		t.setFont(t.getFont().deriveFont(25.0f));
		plansza.setLayout(new GridLayout(5,4));
		i=0;
		for(j=0;j<4;j++)
		{
			tab[i][j]=new JButton("");
			plansza.add(tab[i][j]);
			(tab[i][j]).addActionListener(new C(j));
		}
		for(i=1;i<5;i++)
			for (j=0;j<4;j++)
			{
				tab[i][j]=new JButton("");
				plansza.add(tab[i][j]);
				(tab[i][j]).addActionListener(new B(i,j));
			}
		
		ImageIcon check = new ImageIcon("check.png");
		ImageIcon forw = new ImageIcon("forw.png");
		ImageIcon back = new ImageIcon("back.png");
		cof.setIcon(back);
		next.setIcon(forw);
		for(i=0;i<4;i++) //gorny pasek do sprawdzania
			tab[0][i].setIcon(check);
		stan_pocz(); //ustawianie stan abc wg stanu pocz
		tab[1][3].setBackground(Color.LIGHT_GRAY);
		tab[1][2].setBackground(Color.LIGHT_GRAY);
		tab[2][0].setBackground(Color.LIGHT_GRAY);
		tab[2][3].setBackground(Color.LIGHT_GRAY);
		tab[3][0].setBackground(Color.LIGHT_GRAY);
		tab[4][1].setBackground(Color.LIGHT_GRAY);
		//wpisanie do modelu stanu pocz (nie w funkcji bo przy wczytaj nie potrzeba)
		model.tab[1][2]='B';
		model.tab[1][3]='A';
		model.tab[2][0]='B';
		model.tab[2][3]='C';
		model.tab[3][0]='C';
		model.tab[4][1]='A';
		login.addMouseListener(new MouseAdapter()//czyszczenie pola login
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(juz==0)
				{
					login.setText("");
					juz++;
				}
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
		
	class B implements ActionListener //zmienia literki kliknieciem
	{
		int i,j;
		B(int i,int j)
		{
			this.i=i;this.j=j;
		}
		public void actionPerformed(ActionEvent e) 
		{
			if( (i==1 && j==2) || (i==1 && j==3) || (i==2 && j==0) || (i==2 && j==3) || (i==3 && j==0) || (i==4 && j==1) )
				JOptionPane.showMessageDialog(frame, "Nie mozesz zmieniac liter startowych (na szaro)","Blad",JOptionPane.WARNING_MESSAGE);
			else
			{
				model.historia[model.h][0]=i;
				model.historia[model.h][1]=j;
				model.historia[model.h][2]=Character.getNumericValue(model.tab[i][j]);
				model.h++;
				if(model.tab[i][j] == 'A')
				{
					model.tab[i][j]='B';
					tab[i][j].setIcon(bb);
				}
				else if(model.tab[i][j] == 'B')
				{
					model.tab[i][j]='C';
					tab[i][j].setIcon(cc);
				}
				else if(model.tab[i][j] == 'C')
				{
					model.tab[i][j]='A';
					tab[i][j].setIcon(aa);
				}
				else if(model.tab[i][j] == 0)
				{
					model.tab[i][j]='A';
					tab[i][j].setIcon(aa);
				}
			}
		}
	}
	
	class C implements ActionListener //check czyli sprawdza czy zgodnie z regulami
	{
		int i,a,b,c,j,suma;
		C(int i)
		{
			this.i=i;
		}  
		public void actionPerformed(ActionEvent e) 
		{
			a=0;
			b=0;
			c=0;
			for(j=1;j<5;j++)
			{
				if(model.tab[j][i] == 'A')
					a++;
				else if(model.tab[j][i] == 'B')
					b++;
				else if(model.tab[j][i] == 'C')
					c++;
			}
			suma=a+b+c;
			if(suma==4)
			{
				if(a==4 || b==4 || c==4)
				{
					model.historia[model.h][0]=i;
					model.historia[model.h][1]=1;
					model.historia[model.h][2]=1;
					model.h++;
					model.wynik++; //zgadza sie, wynik musi byc 4 by zakonczyc
					model.takiesame++; //jesli jest np AAAA
					t.setText("Kolumna " + (i+1) + " OK");
					tab[0][i].setEnabled(false);
					for(j=1;j<5;j++)
						tab[j][i].setEnabled(false);
				}
				else if((a==2 && b==2) || (a==2 && c==2) || (b==2 && c==2))
				{
					if(model.takiesame<1 && model.wynik==3)
						JOptionPane.showMessageDialog(frame, "BRAK kolumny 4 tych samych liter (np AAAA), COFNIJ","Blad",JOptionPane.WARNING_MESSAGE);
					else
					{	
						model.historia[model.h][0]=i;
						model.historia[model.h][1]=1;
						model.historia[model.h][2]=2;
						model.h++;
						model.wynik++; //zgadza sie, wynik musi byc 4 by zakonczyc
						t.setText("Kolumna " + (i+1) + " OK");
						tab[0][i].setEnabled(false);
						for(j=1;j<5;j++)
							tab[j][i].setEnabled(false);
					}
				}
				else
				{
					t.setText("Kolumna " + (i+1) + " ZLE");
				}
			}
			else
			{
				t.setText("ZLE");
			}
			if(model.wynik==4)
			{
				int win=0;
				if(model.tab[1][0] == 'B')
					win++;
				if(model.tab[1][3] == 'B')
					win++;
				if(model.tab[2][1] == 'B')
					win++;
				if(model.tab[2][2] == 'B')
					win++;
				if(model.tab[3][1] == 'B')
					win++;
				if(model.tab[3][2] == 'B')
					win++;
				if(model.tab[4][0] == 'B')
					win++;
				if(model.tab[4][3] == 'B')
					win++;
				JOptionPane.showMessageDialog(frame, "Wygrales \nLiczba B na przekatnych to: " + win,"Wygrana",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	class cofnij implements ActionListener //cofnij
	{
		public void actionPerformed(ActionEvent e)
		{
			if(model.h > 0)
			{
				model.h--;
				int i = model.historia[model.h][0];
				int j = model.historia[model.h][1];
				if(model.historia[model.h][2] == (-1))
				{
					model.tab[i][j]=0;
					tab[i][j].setIcon(null);
				}
				else if(model.historia[model.h][2] == 10)
				{
					model.tab[i][j]='A';
					tab[i][j].setIcon(aa);
				}
				else if(model.historia[model.h][2] == 11)
				{
					model.tab[i][j]='B';
					tab[i][j].setIcon(bb);
				}
				else if(model.historia[model.h][2] == 12)
				{
					model.tab[i][j]='C';
					tab[i][j].setIcon(cc);
				}
				else if(model.historia[model.h][2] == 1)//jesli ostatnie bylo sprawdzeniem z tymi samymi
				{
					model.wynik--;
					model.takiesame--;
					tab[0][i].setEnabled(true);
					for(j=1;j<5;j++)
						tab[j][i].setEnabled(true);
				}
				else if(model.historia[model.h][2] == 2)//jesli ostatnie bylo sprawdzeniem
				{
					model.wynik--;
					tab[0][i].setEnabled(true);
					for(j=1;j<5;j++)
						tab[j][i].setEnabled(true);
				}
				t.setText("Cofnieto");
			}
			else
				JOptionPane.showMessageDialog(frame, "Nie mozna wiecej cofnac","Blad",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	class doprzodu implements ActionListener //next
	{
		public void actionPerformed(ActionEvent e)
		{
			if(model.historia[model.h][2] != 0)
			{
				int i = model.historia[model.h][0];
				int j = model.historia[model.h][1];
				if(model.historia[model.h][2] == (-1))
				{
					model.tab[i][j]='A';
					tab[i][j].setIcon(aa);
				}
				else if(model.historia[model.h][2] == 10)
				{
					model.tab[i][j]='B';
					tab[i][j].setIcon(bb);
				}
				else if(model.historia[model.h][2] == 11)
				{
					model.tab[i][j]='C';
					tab[i][j].setIcon(cc);
				}
				else if(model.historia[model.h][2] == 12)
				{
					model.tab[i][j]='A';
					tab[i][j].setIcon(aa);
				}
				else if(model.historia[model.h][2] == 1)//jesli ostatnie bylo sprawdzeniem z tymi samymi
				{
					model.wynik++;
					model.takiesame++;
					tab[0][i].setEnabled(false);
					for(j=1;j<5;j++)
						tab[j][i].setEnabled(false);
				}
				else if(model.historia[model.h][2] == 2)//jesli ostatnie bylo sprawdzeniem
				{
					model.wynik++;
					tab[0][i].setEnabled(false);
					for(j=1;j<5;j++)
						tab[j][i].setEnabled(false);
				}
				model.h++;
				if(model.wynik==4)
				{
					int win=0;
					if(model.tab[1][0] == 'B')
						win++;
					if(model.tab[1][3] == 'B')
						win++;
					if(model.tab[2][1] == 'B')
						win++;
					if(model.tab[2][2] == 'B')
						win++;
					if(model.tab[3][1] == 'B')
						win++;
					if(model.tab[3][2] == 'B')
						win++;
					if(model.tab[4][0] == 'B')
						win++;
					if(model.tab[4][3] == 'B')
						win++;
					t.setText("Do przodu");
					JOptionPane.showMessageDialog(frame, "Znowu wygrales \nLiczba B na przekatnych to: " + win,"Wygrana",JOptionPane.INFORMATION_MESSAGE);
				}
				else
					t.setText("Do przodu");
			}
			else
				JOptionPane.showMessageDialog(frame, "Nie mozna wiecej isc do przodu","Blad",JOptionPane.WARNING_MESSAGE);
		}
	}

	class podane implements ActionListener //podanie loginu
	{
		public void actionPerformed(ActionEvent e)
		{
			model.log = login.getText();
			login.setEnabled(false);
			ok.setEnabled(false);
			save.setEnabled(true);
			load.setEnabled(true);
			podaj.setVisible(false);
		}
	}
	
	class pomoc implements ActionListener //help
	{
		public void actionPerformed(ActionEvent e)
		{
			JLabel halp = new JLabel(new ImageIcon("help.jpg"));
			JOptionPane.showMessageDialog(null,halp,"Pomoc",JOptionPane.PLAIN_MESSAGE,null);
		}
	}

	class zapisz implements ActionListener //save
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				FileOutputStream f = new FileOutputStream("save_" + model.log);
				ObjectOutputStream os = new ObjectOutputStream(f);
				os.writeObject(model);
				f.close();
				t.setText("Zapisano dla " + model.log);
			} catch (IOException b){}
		}
	}
	
	class wczytaj implements ActionListener //load
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				int i;
				int j;
				t.setText("Wczytano dla " + model.log);
				for(i=0; i<4; i++) //zeby wlaczyc przycisko
					if(!(tab[0][i].isEnabled()))
					{
						tab[0][i].setEnabled(true);
						for(j=1;j<5;j++)
							tab[j][i].setEnabled(true);
					}
				for(i=1;i<5;i++) //czyszczenie tego co jest
					for (j=0;j<4;j++)
						if(model.tab[i][j] != 0)
						{
							model.tab[i][j]=0;
							tab[i][j].setIcon(null);
						}
				stan_pocz(); //przywrocenie stan pocz po czyszczeniu
				ObjectInputStream is = new ObjectInputStream(new FileInputStream("save_" + model.log));
				model = (Model)is.readObject();
				is.close();
				int p;
				for(p=0; p<model.h; p++) //odtworzenie historii wczytanej
				{
					i = model.historia[p][0];
					j = model.historia[p][1];
					if(model.historia[p][2] == (-1))
					{
						tab[i][j].setIcon(aa);
					}
					else if(model.historia[p][2] == 10)
					{
						tab[i][j].setIcon(bb);
					}
					else if(model.historia[p][2] == 11)
					{
						tab[i][j].setIcon(cc);
					}
					else if(model.historia[p][2] == 12)
					{
						tab[i][j].setIcon(null);
					}
					else if(model.historia[p][2] == 1 || model.historia[p][2] == 2)//jesli ostatnie bylo sprawdzeniem z tymi samymi
					{
						tab[0][i].setEnabled(false);
						for(j=1;j<5;j++)
							tab[j][i].setEnabled(false);
					}
					//System.out.println(p + " " + model.historia[p][2] + "\n");
				}
			} 
			catch (IOException g)
			{
				t.setText("Brak zapisu " + model.log);
			}
			catch (ClassNotFoundException g) {}
		}
	}
	
	
	
	public static void main(String[] args)
	{
		JFrame f=new Plansza();
		f.setSize(1000,400);
		f.setLocation(100,100);
		f.setVisible(true);
		
	}
}
