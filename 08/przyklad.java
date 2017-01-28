import java.awt.* ;
import java.awt.event.* ;
import javax.swing.*;

class przyklad extends JFrame
{
	JTextField t = new JTextField(20);
	JButton b1 = new JButton("przycisk 1");
	JButton b2 = new JButton("przycisk 2");
	przyklad()
	{
		setTitle("Przyciski przyklad");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(b1);
		cp.add(b2);
		cp.add(t);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,200);
		setVisible(true); 
		b1.addActionListener( new B1());
		b2.addActionListener( new B2());
		b1.setBackground(Color.yellow);
	}

	class B1 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			t.setText("P1 - nieaktywny");
			b1.setEnabled(false);
			b2.setEnabled(true);
		}
	}
	class B2 implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			t.setText("P2 - nieaktywny");
			b1.setEnabled(true);
			b2.setEnabled(false);
		}
	}

	public static void main(String[] arg)
	{
		JFrame f = new przyklad();
		//JFrame f1 = new Przyciski();
	}
}






