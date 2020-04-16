package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.ReviewBean;
import it.unisa.DriverManagerConnectionPool;

public class ReviewModelDM implements ReviewModel<ReviewBean> {

	private static final String TABLE_NAME = "recensioni";
	
	@Override
	public Collection<ReviewBean> doRetrieveByKey(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatament = null;
		
		Collection<ReviewBean> reviewItems = new LinkedList<ReviewBean>();
		
		String selectSQL = "SELECT * FROM " + ReviewModelDM.TABLE_NAME + " WHERE CODICE = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatament = connection.prepareStatement(selectSQL);
			
			preparedStatament.setString(1, codice);
			
			System.out.println("doRetrieveByKey:" + preparedStatament.toString());
			
			ResultSet rs = preparedStatament.executeQuery();
			
			while(rs.next()) {	
				ReviewBean bean = new ReviewBean();
				bean.setTesto(rs.getString("TESTO"));
				bean.setVoto(rs.getInt("VOTO"));
				bean.setCodice(rs.getString("CODICE"));
				
				reviewItems.add(bean);
			}
		} finally {
			try {
				if(preparedStatament != null)
					preparedStatament.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return reviewItems;
	}
	
	@Override
	public void doSave(ReviewBean review) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ReviewModelDM.TABLE_NAME
				+ " (TESTO, VOTO, CODICE) VALUES (?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, review.getTesto());
			preparedStatement.setInt(2, review.getVoto());
			preparedStatement.setString(3, review.getCodice());

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
