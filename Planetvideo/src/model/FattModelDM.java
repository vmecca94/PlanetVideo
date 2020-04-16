package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.FattBean;
import it.unisa.DriverManagerConnectionPool;

public class FattModelDM implements FattModel<FattBean> {

	private static final String TABLE_NAME = "fattura";
	
	@Override
	public Collection<FattBean> doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatament = null;
		
		Collection<FattBean> fattItems = new LinkedList<FattBean>();
		
		String selectSQL = "SELECT * FROM " + FattModelDM.TABLE_NAME + " WHERE USERNAME = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatament = connection.prepareStatement(selectSQL);
			
			preparedStatament.setString(1, username);
			
			System.out.println("doRetrieveByKey:" + preparedStatament.toString());
			
			ResultSet rs = preparedStatament.executeQuery();
			
			while(rs.next()) {	
				FattBean bean = new FattBean();
				bean.setNumfattura(rs.getInt("NUMFATTURA"));
				bean.setUsername(rs.getString("USERNAME"));
				bean.setDataemissione(rs.getDate("DATAEMISSIONE").toLocalDate());
				bean.setImporto(rs.getFloat("IMPORTO"));
				
				fattItems.add(bean);
			}
		} finally {
			try {
				if(preparedStatament != null)
					preparedStatament.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return fattItems;
	}
	
	@Override
	public void doSave(FattBean fatt) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + FattModelDM.TABLE_NAME
				+ " (USERNAME, DATAEMISSIONE, IMPORTO) VALUES (?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, fatt.getUsername());
			preparedStatement.setDate(2, java.sql.Date.valueOf(fatt.getDataemissione()));
			preparedStatement.setFloat(3, fatt.getImporto());

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
}
