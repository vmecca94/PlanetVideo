package control.fatture;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.FattBean;
import model.FattModel;
import model.FattModelDM;

@WebServlet("/ShowFatture")
public class ShowFatture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static FattModel<FattBean> fatt = new FattModelDM();

    public ShowFatture() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		
		try {
			request.removeAttribute("fatture");
			request.setAttribute("fatture", fatt.doRetrieveByKey(uname));
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
