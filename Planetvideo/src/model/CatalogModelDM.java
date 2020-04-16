package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.CatalogBean;
import it.unisa.DriverManagerConnectionPool;

public class CatalogModelDM implements CatalogModel<CatalogBean> {

	private static final String TABLE_NAME = "contenuti";
	
	@Override
	public Collection<CatalogBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatament = null;
		
		Collection<CatalogBean> catalogItems = new LinkedList<CatalogBean>();
		
		String selectSQL = "SELECT * FROM " + CatalogModelDM.TABLE_NAME;
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatament = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatament.toString());
			
			ResultSet rs = preparedStatament.executeQuery();
			
			while(rs.next()) {
				CatalogBean bean = new CatalogBean();
				
				bean.setCodice(rs.getString("CODICE"));
				bean.setTitolo(rs.getString("TITOLO"));
				bean.setRegista(rs.getString("REGISTA"));
				bean.setAnno(rs.getInt("ANNO"));
				bean.setGenere(rs.getString("GENERE"));
				bean.setUrlimg(rs.getString("URLIMG"));
				
				catalogItems.add(bean);
			}
		} finally {
			try {
				if(preparedStatament != null)
					preparedStatament.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return catalogItems;
	}
	
	@Override
	public void doSave(CatalogBean catalog) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + CatalogModelDM.TABLE_NAME
				+ " (CODICE, TITOLO, REGISTA, ANNO, GENERE, URLIMG) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, catalog.getCodice());
			preparedStatement.setString(2, catalog.getTitolo());
			preparedStatement.setString(3, catalog.getRegista());
			preparedStatement.setInt(4, catalog.getAnno());
			preparedStatement.setString(5, catalog.getGenere());
			preparedStatement.setString(6, catalog.getUrlimg());

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
	public CatalogBean doRetrieveByKey(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatament = null;
		
		CatalogBean bean = new CatalogBean();
		
		String selectSQL = "SELECT * FROM " + CatalogModelDM.TABLE_NAME + " WHERE CODICE = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatament = connection.prepareStatement(selectSQL);
			
			preparedStatament.setString(1, codice);
			
			System.out.println("doRetrieveByKey:" + preparedStatament.toString());
			
			ResultSet rs = preparedStatament.executeQuery();
			
			while(rs.next()) {				
				bean.setCodice(rs.getString("CODICE"));
				bean.setTitolo(rs.getString("TITOLO"));
				bean.setRegista(rs.getString("REGISTA"));
				bean.setAnno(rs.getInt("ANNO"));
				bean.setGenere(rs.getString("GENERE"));
				bean.setUrlimg(rs.getString("URLIMG"));
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
	public boolean doDelete(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + CatalogModelDM.TABLE_NAME + " WHERE CODICE = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, codice);

			System.out.println("doDelete: "+ preparedStatement.toString());
			result = preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}
}
