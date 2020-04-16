package bean;

import java.io.Serializable;
import java.time.LocalDate;

public class AbbonBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int numeroabb;
    LocalDate datainizio;
    LocalDate datafine;
    String username;
    
    public AbbonBean() {
    	datainizio = LocalDate.of(2000, 1, 1);
    	datafine = LocalDate.of(2000, 2, 1);
    }

	public int getNumeroabb() {
		return numeroabb;
	}

	public LocalDate getDatainizio() {
		return datainizio;
	}

	public void setDatainizio(LocalDate datainizio) {
		this.datainizio = datainizio;
	}

	public LocalDate getDatafine() {
		return datafine;
	}

	public void setDatafine(LocalDate datafine) {
		this.datafine = datafine;
	}

	public String getUsernameUtente() {
		return username;
	}

	public void setUsernameUtente(String usernameUtente) {
		this.username = usernameUtente;
	}
}
