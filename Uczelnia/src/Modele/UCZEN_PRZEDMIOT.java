package Modele;





public class UCZEN_PRZEDMIOT {

	private Integer Id_Uczen; 
	private Integer Id_Przedmiot; 
        private Float Ocena_Koncowa; 
        private String Zaliczenie; 
         private String Uwagi; 
        
	
	
	public UCZEN_PRZEDMIOT(){}
	
	public UCZEN_PRZEDMIOT(Integer Id_Uczen, Integer Id_Przedmiot, Float Ocena_Koncowa, String Zaliczenie, String Uwagi) {
		this.Id_Uczen = Id_Uczen;
		this.Id_Przedmiot = Id_Przedmiot;
                this.Ocena_Koncowa = Ocena_Koncowa;
                this.Zaliczenie = Zaliczenie;
                this.Uwagi = Uwagi;
	}
	

	
	public void setId_UczenP(int Id_Uczen) {
		this.Id_Uczen = Id_Uczen;
	}
	
	public void setId_PrzedmiotP(int Id_Przedmiot) {
		this.Id_Przedmiot = Id_Przedmiot;
	}
	public void setZaliczenie(String Zaliczenie) {
		this.Zaliczenie = Zaliczenie;
	}
        public void setOcena_Koncowa(Float Ocena_Koncowa) {
		this.Ocena_Koncowa = Ocena_Koncowa;
	}
        public void setUwagi(String Uwagi) {
		this.Uwagi = Uwagi;
	}
	
	
	
	
	public Integer getId_UczenP() {
		return Id_Uczen;
	}
	public Integer getId_PrzedmiotP() {
		return Id_Przedmiot;
	}	
public Float getOcena_Koncowa() {
		return Ocena_Koncowa;
	}
	public String getZaliczenie() {
		return Zaliczenie;
	}	
public String getUwagi() {
		return Uwagi;
	}
	

}
