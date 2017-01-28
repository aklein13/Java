// serializacja obiektu, modyfikator transient

import java.io.*;

class A implements Serializable{
    private   int p;
    private	 int x;
    A(int pp, int xx){ p=pp; x=xx; }
    public String toString(){
	return "A: p = "+p+ " x = " + x;
    }
}

class Zapis{
    public static void main(String[] args){
	A a = new A(1,2);
	A b = new A(3,4);
        try{
	    FileOutputStream f = new FileOutputStream("obiektA");
	    ObjectOutputStream os = new ObjectOutputStream(f);
	    os.writeObject(a);
	    os.writeObject(b);
            f.close();
	} catch (IOException e){}

	A a1 = new A(0,223); //powinnno to nizej nadpisac
	A b1 = new A(99,24);
        try{
	    ObjectInputStream is = new ObjectInputStream(
                                     new FileInputStream("obiektA"));
	    a1 = (A)is.readObject();
	    b1 = (A)is.readObject();
            is.close();
	} catch (IOException e){System.out.println("--wyjatek!");}
  	  catch (ClassNotFoundException e){}

        System.out.println(a1);
        System.out.println(b1);
    }
}
