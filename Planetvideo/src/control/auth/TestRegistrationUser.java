package control.auth;

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

import model.UserModelDM;

public class TestRegistrationUser {

	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;

	RegistrationUserServlet servlet = new RegistrationUserServlet();
	UserModelDM element = new UserModelDM();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown() {
		request = null;
		response = null;
	}
	
	//TC 1.3.1
	@Test
	public void registrazione_EmptyNome() throws IOException, ServletException, SQLException {
		when(request.getParameter("nome")).thenReturn(null);
		when(request.getParameter("cognome")).thenReturn("Vitale");
		when(request.getParameter("datanascita")).thenReturn("1994-03-30");
		when(request.getParameter("numcarta")).thenReturn("1234567891234567");
		when(request.getParameter("email")).thenReturn("email@email.com");
		when(request.getParameter("username")).thenReturn("federico");
		when(request.getParameter("userpass")).thenReturn("fede94");
			
		servlet.doPost(request, response);
		assertEquals("", element.doRetrieveByKey("federico").getUsername());
	}
	
	//TC 1.3.2
	@Test
	public void registrazione_EmptyCognome() throws IOException, ServletException, SQLException {
		when(request.getParameter("nome")).thenReturn("Federico");
		when(request.getParameter("cognome")).thenReturn(null);
		when(request.getParameter("datanascita")).thenReturn("1994-03-30");
		when(request.getParameter("numcarta")).thenReturn("1234567891234567");
		when(request.getParameter("email")).thenReturn("email@email.com");
		when(request.getParameter("username")).thenReturn("federico");
		when(request.getParameter("userpass")).thenReturn("fede94");
				
		servlet.doPost(request, response);
		assertEquals("", element.doRetrieveByKey("federico").getUsername());
	}
	
	//TC 1.3.3
	@Test
	public void registrazione_EmptyUsername() throws IOException, ServletException, SQLException {
		when(request.getParameter("nome")).thenReturn("Federico");
		when(request.getParameter("cognome")).thenReturn("Vitale");
		when(request.getParameter("datanascita")).thenReturn("1994-03-30");
		when(request.getParameter("numcarta")).thenReturn("1234567891234567");
		when(request.getParameter("email")).thenReturn("email@email.com");
		when(request.getParameter("username")).thenReturn(null);
		when(request.getParameter("userpass")).thenReturn("fede94");
				
		servlet.doPost(request, response);
		assertEquals("", element.doRetrieveByKey("federico").getUsername());
	}
	
	//TC 1.3.4
	@Test
	public void registrazione_EmptyPassword() throws IOException, ServletException, SQLException {
		when(request.getParameter("nome")).thenReturn("Federico");
		when(request.getParameter("cognome")).thenReturn("Vitale");
		when(request.getParameter("datanascita")).thenReturn("1994-03-30");
		when(request.getParameter("numcarta")).thenReturn("1234567891234567");
		when(request.getParameter("email")).thenReturn("email@email.com");
		when(request.getParameter("username")).thenReturn("federico");
		when(request.getParameter("userpass")).thenReturn(null);
				
		servlet.doPost(request, response);
		assertEquals("", element.doRetrieveByKey("federico").getUsername());
	}
	
	//TC 1.3.6
	@Test
	public void registrazione_EmptyEmail() throws IOException, ServletException, SQLException {
		when(request.getParameter("nome")).thenReturn("Federico");
		when(request.getParameter("cognome")).thenReturn("Vitale");
		when(request.getParameter("datanascita")).thenReturn("1994-03-30");
		when(request.getParameter("numcarta")).thenReturn("1234567891234567");
		when(request.getParameter("email")).thenReturn(null);
		when(request.getParameter("username")).thenReturn("federico");
		when(request.getParameter("userpass")).thenReturn("fede94");
				
		servlet.doPost(request, response);
		assertEquals("", element.doRetrieveByKey("federico").getUsername());
	}
}
