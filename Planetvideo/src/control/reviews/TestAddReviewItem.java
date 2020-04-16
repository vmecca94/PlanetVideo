package control.reviews;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.ReviewModelDM;

public class TestAddReviewItem {

	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	HttpSession session;

	AddReviewItem servlet = new AddReviewItem();
	ReviewModelDM element = new ReviewModelDM();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown() {
		request = null;
		response = null;
		session = null;
	}
	
	//TC 3.1.1
	@Test
	public void aggiungiRecensione_EmptyTesto() throws IOException, ServletException, SQLException {
		when(request.getParameter("testo")).thenReturn(null);
		when(request.getParameter("voto")).thenReturn("5");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("codiceCont")).thenReturn("F32");
		
		servlet.doGet(request, response);
		assertEquals(0, element.doRetrieveByKey("F32").size());
	}
}
