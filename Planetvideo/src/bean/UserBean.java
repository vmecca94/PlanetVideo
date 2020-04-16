package bean;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String username;
	String userpass;
    String nome;
    String cognome;
    String datanascita;
    String numcarta;
    String email;
    boolean isAdmin;
    
    public UserBean() {
    	username = "";
    	userpass = "";
        nome = "";
        cognome = "";
        datanascita = "";
        numcarta = "";
        email = "";
        isAdmin = false;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDatanascita() {
		return datanascita;
	}

	public void setDatanascita(String datanascita) {
		this.datanascita = datanascita;
	}

	public String getNumcarta() {
		return numcarta;
	}

	public void setNumcarta(String numcarta) {
		this.numcarta = numcarta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
}
