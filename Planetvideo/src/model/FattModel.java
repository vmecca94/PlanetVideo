package model;

import java.sql.SQLException;
import java.util.Collection;

public interface FattModel<T> {

	public Collection<T> doRetrieveByKey(String username) throws SQLException;
	
	public void doSave(T fatt) throws SQLException;
}
