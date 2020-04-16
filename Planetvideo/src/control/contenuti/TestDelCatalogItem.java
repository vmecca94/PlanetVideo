package control.contenuti;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.CatalogModelDM;

public class TestDelCatalogItem {

	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	DelCatalogItem servlet = new DelCatalogItem();
	
	CatalogModelDM element = new CatalogModelDM();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown() {
		request = null;
		response = null;
	}
	
	//TC 2.5.1
	@Test
	public void rimuoviContenuto_EmptyTitolo() throws IOException, ServletException, SQLException {
		when(request.getParameter("codice")).thenReturn("S11");
		
		servlet.doGet(request, response);
		assertEquals("", element.doRetrieveByKey("S11").getCodice());
	}
}
