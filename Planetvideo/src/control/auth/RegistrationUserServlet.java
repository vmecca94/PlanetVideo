package control.auth;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import model.UserModel;
import model.UserModelDM;

@WebServlet("/RegistrationUserServlet")
public class RegistrationUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UserModel<UserBean> user = new UserModelDM();

    public RegistrationUserServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String datanascita = request.getParameter("datanascita");
			String numcarta = request.getParameter("numcarta");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String userpass = request.getParameter("userpass");
			
			UserBean bean = new UserBean();
			bean.setNome(nome);
			bean.setCognome(cognome);
			bean.setDatanascita(datanascita);
			bean.setNumcarta(numcarta);
			bean.setEmail(email);
			bean.setUsername(username);
			bean.setUserpass(userpass);
			
			user.doSave(bean);

			request.getSession().setAttribute("user", username);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddAbbonItem");
			rd.forward(request, response);
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
	}

}
