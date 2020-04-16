package bean;

import java.io.Serializable;

public class CatalogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String codice;
    String titolo;
    String regista;
    int anno;
    String genere;
    String urlimg;
    
    public CatalogBean() {
    	codice = "";
    	titolo = "";
    	regista = "";
    	anno = 0;
    	genere = "";
    	urlimg = "";
    }

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getRegista() {
		return regista;
	}

	public void setRegista(String regista) {
		this.regista = regista;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getUrlimg() {
		return urlimg;
	}

	public void setUrlimg(String urlimg) {
		this.urlimg = urlimg;
	}
	
	@Override
	public boolean equals(Object other) {
		if(this.getCodice().equals(((CatalogBean)other).getCodice()))
			return true;
		return false;
	}
}
