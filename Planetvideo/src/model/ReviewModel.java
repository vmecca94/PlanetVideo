package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ReviewModel<T> {

	public Collection<T> doRetrieveByKey(String codice) throws SQLException;
	
	public void doSave(T review) throws SQLException;
}
