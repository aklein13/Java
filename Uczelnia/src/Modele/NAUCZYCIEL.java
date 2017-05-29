package Modele;

public class NAUCZYCIEL {

	private Integer Id_Nauczyciel; 
        private String Imie ;
	private String Nazwisko;
	private String Tytul;
	private String Data_Zatrudnienia;
	
	
	public NAUCZYCIEL(Integer Id_Nauczyciel, String Imie, String Nazwisko, String Tytul, String Data_Zatrudnienia) {
		this(Imie,Nazwisko, Tytul,Data_Zatrudnienia);
		this.Id_Nauczyciel = Id_Nauczyciel;
	}
	
	public NAUCZYCIEL(String Imie, String Nazwisko, String Tytul, String Data_Zatrudnienia) {
		this.Imie = Imie;
		this.Nazwisko = Nazwisko;
		this.Tytul = Tytul;
		this.Data_Zatrudnienia=Data_Zatrudnienia;
	}

	public NAUCZYCIEL() {}

	
	public void setId(int Id_Nauczyciel) {
		this.Id_Nauczyciel = Id_Nauczyciel;
	}
	
	public void setTytul(String Tytul) {
		this.Tytul = Tytul;
	}
	
	public void setData_Zatrudnienia(String Data_Zatrudnienia) {
		this.Data_Zatrudnienia = Data_Zatrudnienia;
	}

	public void setImie(String Imie) {
		this.Imie = Imie;
	}


	public void setNazwisko(String d) {

		this.Nazwisko=d;
	}


public Integer getId_Nauczyciel() {
		return Id_Nauczyciel;
	}
	
	public String getTytul() {
		return Tytul;
	}
	
	public String getData_Zatrudnienia() {
		return Data_Zatrudnienia;
	}
	
	public String getImie() {
		return Imie;
	}

	public String getNazwisko() {

		return Nazwisko;
	}



}
