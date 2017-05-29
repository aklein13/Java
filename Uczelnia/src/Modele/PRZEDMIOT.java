package Modele;



public class PRZEDMIOT {

	private Integer Id_Przedmiot; 
	private String Nazwa;
	 private String Opis;
	private  Integer Ilosc_Godzin;
private Integer Id_Nauczyciel; 
	public PRZEDMIOT(Integer Id_Przedmiot, String Nazwa, String Opis, Integer Ilosc_Godzin, Integer Id_Nauczyciel){
		
		this.Nazwa = Nazwa;
		this.Opis = Opis;
        this.Ilosc_Godzin=Ilosc_Godzin;
		
		this.Id_Przedmiot = Id_Przedmiot;

	this.Id_Nauczyciel = Id_Nauczyciel;
	}
	
	public PRZEDMIOT(String Nazwa, String Opis, Integer Ilosc_Godzin, Integer Id_Nauczyciel) {
		this.Nazwa = Nazwa;
		this.Opis = Opis;
        this.Ilosc_Godzin=Ilosc_Godzin;
this.Id_Nauczyciel = Id_Nauczyciel;
	}

	public PRZEDMIOT() {}

	
	public void setId(int Id_Przedmiot) {
		this.Id_Przedmiot = Id_Przedmiot;
	}
	
	public void setNazwa(String Nazwa) {
		this.Nazwa = Nazwa;
	}
	
	public void setOpis(String Opis) {
		this.Opis = Opis;
	}
		public void setId_Kraj(int Id_Nauczyciel) {
		this.Id_Nauczyciel = Id_Nauczyciel;
	}

	
	public Integer getId() {
		return Id_Przedmiot;
	}
	
	public String getNazwa() {
		return Nazwa;
	}
	
	public String getOpis() {
		return Opis;
	}

	public Integer getIlosc_Godzin() {
		return Ilosc_Godzin;
		
	}
	
	public void setIlosc_Godzin(Integer Ilosc_Godzin) {
		this.Ilosc_Godzin=Ilosc_Godzin;	
		
	}
public Integer getId_Nauczyciel() {
		return Id_Nauczyciel;
	}


}
