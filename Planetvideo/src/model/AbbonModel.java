package model;

import java.sql.SQLException;

public interface AbbonModel<T> {

	public T doRetrieveByKey(String usernameUtente) throws SQLException;
	
	public void doSave(T abbon) throws SQLException;
	
	public void doUpdate(T abbon) throws SQLException;
}
