package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;
import it.unisa.DriverManagerConnectionPool;

public class UserModelDM implements UserModel<UserBean> {

	private static final String TABLE_NAME = "utente";
	
	@Override
	public void doSave(UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UserModelDM.TABLE_NAME
				+ " (USERNAME, USERPASS, NOME, COGNOME, DATANASCITA, NUMCARTA, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getUserpass());
			preparedStatement.setString(3, user.getNome());
			preparedStatement.setString(4, user.getCognome());
			preparedStatement.setString(5, user.getDatanascita());
			preparedStatement.setString(6, user.getNumcarta());
			preparedStatement.setString(7, user.getEmail());

			System.out.println("doSave: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}
	
	@Override
	public UserBean doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatament = null;
		
		UserBean bean = new UserBean();
		
		String selectSQL = "SELECT * FROM " + UserModelDM.TABLE_NAME + " WHERE USERNAME = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatament = connection.prepareStatement(selectSQL);
			
			preparedStatament.setString(1, username);
			
			System.out.println("doRetrieveByKey:" + preparedStatament.toString());
			
			ResultSet rs = preparedStatament.executeQuery();
			
			while(rs.next()) {				
				bean.setUsername(rs.getString("USERNAME"));
				bean.setUserpass(rs.getString("USERPASS"));
				bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				bean.setDatanascita(rs.getString("DATANASCITA"));
				bean.setNumcarta(rs.getString("NUMCARTA"));
				bean.setEmail(rs.getString("EMAIL"));
			}
		} finally {
			try {
				if(preparedStatament != null)
					preparedStatament.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		
		return bean;
	}
	
	@Override
	public boolean checkUser(String username, String userpass) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatament = null;
		
		String selectSQL = "SELECT username, userpass FROM " + UserModelDM.TABLE_NAME + " WHERE USERNAME = ? AND USERPASS = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatament = connection.prepareStatement(selectSQL);
			
			preparedStatament.setString(1, username);
			preparedStatament.setString(2, userpass);
			
			ResultSet rs = preparedStatament.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} finally {
			try {
				if(preparedStatament != null)
					preparedStatament.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		
		return false;
	}
	
	@Override
	public boolean checkAdminUser(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatament = null;
		
		String selectSQL = "SELECT username FROM " + UserModelDM.TABLE_NAME + " WHERE USERNAME = ? AND ISADMIN = 1";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatament = connection.prepareStatement(selectSQL);
			
			preparedStatament.setString(1, username);
			
			ResultSet rs = preparedStatament.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} finally {
			try {
				if(preparedStatament != null)
					preparedStatament.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		
		return false;
	}
}
