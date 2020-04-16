package model;

import java.sql.SQLException;

public interface UserModel<T> {

	public void doSave(T user) throws SQLException;
	
	public T doRetrieveByKey(String username) throws SQLException;
	
	public boolean checkUser(String username, String userpass) throws SQLException;
	
	public boolean checkAdminUser(String username) throws SQLException;
}
