package control.auth;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AbbonBean;
import bean.UserBean;
import model.AbbonModel;
import model.AbbonModelDM;
import model.UserModel;
import model.UserModelDM;

@WebServlet("/LoginAdminUserServlet")
public class LoginAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UserModel<UserBean> user = new UserModelDM();
	static AbbonModel<AbbonBean> abb = new AbbonModelDM();
	
    public LoginAdminUserServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		
		try {
			if(user.checkUser(username, userpass) && user.checkAdminUser(username)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("abbonamento", abb.doRetrieveByKey(username));
				response.sendRedirect("/Planetvideo/admin/admincatalog.jsp");
			} else {
				response.sendRedirect("/Planetvideo/admin/adminloginerror.jsp");
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
	}

}
