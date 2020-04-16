package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AbbonBean;
import it.unisa.DriverManagerConnectionPool;

public class AbbonModelDM implements AbbonModel<AbbonBean> {

	private static final String TABLE_NAME = "abbonamento";
	
	@Override
	public void doSave(AbbonBean abbon) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + AbbonModelDM.TABLE_NAME
				+ " (DATAINIZIO, DATAFINE, USERNAME) VALUES (?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDate(1, java.sql.Date.valueOf(abbon.getDatainizio()));
			preparedStatement.setDate(2, java.sql.Date.valueOf(abbon.getDatafine()));
			preparedStatement.setString(3, abbon.getUsernameUtente());

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
	public void doUpdate(AbbonBean abbon) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + AbbonModelDM.TABLE_NAME
				+ " SET DATAFINE = ? WHERE USERNAME = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDate(1, java.sql.Date.valueOf(abbon.getDatafine()));
			preparedStatement.setString(2, abbon.getUsernameUtente());

			System.out.println("doUpdate: "+ preparedStatement.toString());
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
	public AbbonBean doRetrieveByKey(String usernameUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatament = null;
		
		AbbonBean bean = new AbbonBean();
		
		String selectSQL = "SELECT * FROM " + AbbonModelDM.TABLE_NAME + " WHERE USERNAME = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatament = connection.prepareStatement(selectSQL);
			
			preparedStatament.setString(1, usernameUtente);
			
			System.out.println("doRetrieveByKey:" + preparedStatament.toString());
			
			ResultSet rs = preparedStatament.executeQuery();
			
			while(rs.next()) {				
				bean.setDatainizio(rs.getDate("DATAINIZIO").toLocalDate());
				bean.setDatafine(rs.getDate("DATAFINE").toLocalDate());
				bean.setUsernameUtente(rs.getString("USERNAME"));
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
}
