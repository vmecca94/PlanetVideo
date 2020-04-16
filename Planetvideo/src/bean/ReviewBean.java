package bean;

import java.io.Serializable;

public class ReviewBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int id;
	String testo;
	int voto;
	String codice;
	
	public ReviewBean() {
		testo = "";
		voto = 0;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
}
