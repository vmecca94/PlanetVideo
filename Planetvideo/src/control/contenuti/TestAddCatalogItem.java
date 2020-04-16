package control.contenuti;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestAddCatalogItem {
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;

	AddCatalogItem servlet = new AddCatalogItem();
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
	
	//TC 2.4.1
	@Test
	public void aggiungiContenuto_EmptyTitolo() throws IOException, ServletException, SQLException {
		when(request.getParameter("codice")).thenReturn("F100");
		when(request.getParameter("titolo")).thenReturn(null);
		when(request.getParameter("regista")).thenReturn("Paolo Sorrentino");
		when(request.getParameter("anno")).thenReturn("2013");
		when(request.getParameter("genere")).thenReturn("Drammatico");
		when(request.getParameter("urlimg")).thenReturn("catalog/img.jpg");
		
		servlet.doGet(request, response);
		assertEquals("", element.doRetrieveByKey("F100").getCodice());
	}
	
	//TC 2.4.2
	@Test
	public void aggiungiContenuto() throws IOException, ServletException, SQLException {
		when(request.getParameter("codice")).thenReturn("F51");
		when(request.getParameter("titolo")).thenReturn("La grande bellezza");
		when(request.getParameter("regista")).thenReturn("Paolo Sorrentino");
		when(request.getParameter("anno")).thenReturn("2013");
		when(request.getParameter("genere")).thenReturn("Drammatico");
		when(request.getParameter("urlimg")).thenReturn("catalog/img.jpg");
		
		servlet.doGet(request, response);
		assertEquals("F51", element.doRetrieveByKey("F51").getCodice());
		assertEquals("La grande bellezza", element.doRetrieveByKey("F51").getTitolo());
		assertEquals("Paolo Sorrentino", element.doRetrieveByKey("F51").getRegista());
		assertEquals(2013, element.doRetrieveByKey("F51").getAnno());
		assertEquals("Drammatico", element.doRetrieveByKey("F51").getGenere());
		assertEquals("catalog/img.jpg", element.doRetrieveByKey("F51").getUrlimg());
	}
}
