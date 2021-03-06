package control.fatture;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.FattBean;
import model.FattModel;
import model.FattModelDM;

@WebServlet("/AddFattItemZero")
public class AddFattItemZero extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static FattModel<FattBean> fatt = new FattModelDM();

    public AddFattItemZero() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			float importo = 0.0f;
			LocalDate dataemissione = LocalDate.now();
			String uname = (String) request.getSession().getAttribute("user");
		
			FattBean bean = new FattBean();
			bean.setDataemissione(dataemissione);
			bean.setImporto(importo);
			bean.setUsername(uname);
		
			fatt.doSave(bean);
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		response.sendRedirect("/Planetvideo/login.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
