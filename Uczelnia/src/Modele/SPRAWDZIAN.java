package Modele;



public class SPRAWDZIAN {

	private Integer Id_Sprawdzian; 
	private Integer Punkty;
	 private Float Ocena;
	private  Integer Id_Nauczyciel;
private Integer Id_Uczen; 
	public SPRAWDZIAN(Integer Id_Sprawdzian, Integer Punkty, Float Ocena, Integer Id_Nauczyciel, Integer Id_Uczen){
		this.Id_Sprawdzian = Id_Sprawdzian;
		this.Punkty = Punkty;
		this.Ocena = Ocena;
                this.Id_Nauczyciel = Id_Nauczyciel;
                this.Id_Uczen=Id_Uczen;
		

	}
	
	public SPRAWDZIAN(Integer Punkty, Float Ocena, Integer Id_Nauczyciel, Integer Id_Uczen) {
		this.Punkty = Punkty;
		this.Ocena = Ocena;
                this.Id_Nauczyciel = Id_Nauczyciel;
                this.Id_Uczen=Id_Uczen;
	}

	public SPRAWDZIAN() {}

	
	public void setId_Sprawdzian(int Id_Sprawdzian) {
		this.Id_Sprawdzian = Id_Sprawdzian;
	}
	
	public void setPunkty(Integer Punkty) {
		this.Punkty = Punkty;
	}
	
	public void setOcena(Float Ocena) {
		this.Ocena = Ocena;
	}
		public void setId_NauczycielS(int Id_Nauczyciel) {
		this.Id_Nauczyciel = Id_Nauczyciel;
	}
public void setId_UczenS(int Id_Uczen) {
		this.Id_Uczen = Id_Uczen;
	}
	
	public Integer getId_Sprawdzian() {
		return Id_Sprawdzian;
	}
	
	public Integer getPunkty() {
		return Punkty;
	}
	
	public Float getOcena() {
		return Ocena;
	}

	public Integer getId_NauczycielS() {
		return Id_Nauczyciel;
		
	}
        public Integer getId_UczenS() {
		return Id_Uczen;
		
	}
}
