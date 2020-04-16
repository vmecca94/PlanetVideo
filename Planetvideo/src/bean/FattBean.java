package bean;

import java.io.Serializable;
import java.time.LocalDate;

public class FattBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int numfattura;
	LocalDate dataemissione;
    float importo;
    String username;
    
    public FattBean() {
    	dataemissione = LocalDate.of(2000, 1, 1);
    	username = "";
    	importo = 0;
    }
    
    public int getNumfattura() {
    	return numfattura;
    }

	public void setNumfattura(int numfattura) {
		this.numfattura = numfattura;
	}

	public LocalDate getDataemissione() {
		return dataemissione;
	}

	public void setDataemissione(LocalDate dataemissione) {
		this.dataemissione = dataemissione;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
