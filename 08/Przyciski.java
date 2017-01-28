import java.awt.* ;
import java.awt.event.* ;
import javax.swing.*;
// przyciski :D
class Przyciski extends JFrame
{
	JTextField t = new JTextField(25);
	JTextField t2 = new JTextField(10);
	JButton b1 = new JButton("przycisk 1");
	JButton b2 = new JButton("przycisk 2");
	JButton b3 = new JButton("przycisk 3");
	JButton b4 = new JButton("przycisk 4");
	JButton zero = new JButton("zeruj");
	int[] tab={1,1,1,1};
	int n=4; // licznik zielonych
	int a=0; // kliki
	Przyciski()
	{
		setTitle("Przyciski");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout()); //od lewej do prawej
		cp.add(b1);	//dodaje przycisk
		cp.add(b2);
		cp.add(b3);
		cp.add(b4);
		cp.add(t);
		cp.add(t2);
		cp.add(zero);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,400);
		setVisible(true);
		b1.addActionListener(new B1());
		b2.addActionListener(new B2());
		b3.addActionListener(new B3());
		b4.addActionListener(new B4());
		zero.addActionListener(new ZERO());
		b1.setBackground(Color.green);
		b2.setBackground(Color.green);
		b3.setBackground(Color.green);
		b4.setBackground(Color.green);
	}
	class B1 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(tab[0]==1 && n>2)
			{
				b1.setBackground(Color.yellow);
				tab[0]=0;
				n--;
				a++;
				t2.setText(String.valueOf(a));
			}
			else if(tab[0]==0)
			{
				b1.setBackground(Color.green);
				tab[0]=1;
				n++;
				a++;
				t2.setText(String.valueOf(a));
			}
			else if(tab[0]==1 && n<=2)
				t.setText("Nie mozna zmienic P1 - za malo zielonych");
		}
	}
	class B2 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(tab[1]==1 && n>2)
			{
				b2.setBackground(Color.yellow);
				tab[1]=0;
				n--;
				a++;
				t2.setText(String.valueOf(a));
			}
			else if(tab[1]==0)
			{
				b2.setBackground(Color.green);
				tab[1]=1;
				n++;
				a++;
				t2.setText(String.valueOf(a));
			}
			else if(tab[1]==1 && n<=2)
				t.setText("Nie mozna zmienic P2 - za malo zielonych");
		}
	}
	class B3 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(tab[2]==1 && n>2)
			{
				b3.setBackground(Color.yellow);
				tab[2]=0;
				n--;
				a++;
				t2.setText(String.valueOf(a));
			}
			else if(tab[2]==0)
			{
				b3.setBackground(Color.green);
				tab[2]=1;
				n++;
				a++;
				t2.setText(String.valueOf(a));
			}
			else if(tab[2]==1 && n<=2)
				t.setText("Nie mozna zmienic P3 - za malo zielonych");
		}
	}
	class B4 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(tab[3]==1 && n>2)
			{
				b4.setBackground(Color.yellow);
				tab[3]=0;
				n--;
				a++;
				t2.setText(String.valueOf(a));
			}
			else if(tab[3]==0)
			{
				b4.setBackground(Color.green);
				tab[3]=1;
				n++;
				a++;
				t2.setText(String.valueOf(a));
			}
			else if(tab[3]==1 && n<=2)
				t.setText("Nie mozna zmienic P4 - za malo zielonych");
		}
	}
	
	class ZERO implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			a=0;
			t2.setText(String.valueOf(a));
		}
	}

	public static void main(String[] arg)
	{
		JFrame f = new Przyciski();
	}
}






