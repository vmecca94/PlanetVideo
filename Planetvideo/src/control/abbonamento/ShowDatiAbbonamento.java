package control.abbonamento;

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

import bean.AbbonBean;
import model.AbbonModel;
import model.AbbonModelDM;

@WebServlet("/ShowDatiAbbonamento")
public class ShowDatiAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowDatiAbbonamento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		AbbonModel<AbbonBean> abbon = new AbbonModelDM();
		String username = request.getParameter("username");
		try{
			out.print(gson.toJson(abbon.doRetrieveByKey(username)));
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
