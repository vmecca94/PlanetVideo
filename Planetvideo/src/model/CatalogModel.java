package model;

import java.sql.SQLException;
import java.util.Collection;

public interface CatalogModel<T> {

	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public void doSave(T catalog) throws SQLException;
	
	public T doRetrieveByKey(String codice) throws SQLException;
	
	public boolean doDelete(String codice) throws SQLException;
}
