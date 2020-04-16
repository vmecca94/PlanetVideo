package control.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.UserBean;
import model.UserModel;
import model.UserModelDM;

@WebServlet("/ShowDatiAccount")
public class ShowDatiAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ShowDatiAccount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		UserModel<UserBean> user = new UserModelDM();
		String username = request.getParameter("username");
		
		try {
			out.print(gson.toJson(user.doRetrieveByKey(username)));
			out.flush();
			out.close();
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/myaccount.jsp");
		rd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
