package Modele;


public class UCZEN {

	private Integer Id_Uczen; 
	private String Imie;
	private String Nazwisko;
        private Integer Wiek;
        private String Indeks;
	
	

	
	public UCZEN(Integer Id_Uczen, String Imie, String Nazwisko, Integer Wiek, String Indeks) {
		this.Id_Uczen = Id_Uczen;
		this.Imie = Imie;
		this.Nazwisko = Nazwisko;
                this.Wiek=Wiek;
                this.Indeks=Indeks;
		
	}
	
	public UCZEN(String Imie, String Nazwisko, Integer Wiek, String Indeks) {
		
		this.Imie = Imie;
		this.Nazwisko = Nazwisko;
		this.Wiek=Wiek;
                this.Indeks=Indeks;
	}
	
	
	public UCZEN() {}

	
	public void setId_Uczen(int Id_Kraj) {
		this.Id_Uczen = Id_Kraj;
	}
	
	public void setImie(String Kraj) {
		this.Imie = Kraj;
	}
	
	public void setNazwisko(String Opis) {
		this.Nazwisko = Opis;
	}
        public void setWiek(Integer Wiek) {
		this.Wiek = Wiek;
	}
        public void setIndeks(String Indeks) {
		this.Indeks = Indeks;
	}

	




public Integer getId_Uczen() {
		return Id_Uczen;
	}
	
	public String getImie() {
		return Imie;
	}
	
	public String getNazwisko() {
		return Nazwisko;
	}
	public Integer getWiek() {
		return Wiek;
	}
	public String getIndeks() {
		return Indeks;
	}
}