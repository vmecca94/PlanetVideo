package control.abbonamento;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AbbonBean;
import model.AbbonModel;
import model.AbbonModelDM;

@WebServlet("/EstendiAbbonamento")
public class EstendiAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static AbbonModel<AbbonBean> abbon = new AbbonModelDM();

    public EstendiAbbonamento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LocalDate datafine = LocalDate.now().plusMonths(1);
			String usernameUtente = (String) request.getSession().getAttribute("username");
		
			AbbonBean bean = new AbbonBean();
			bean.setDatafine(datafine);
			bean.setUsernameUtente(usernameUtente);
		
			abbon.doUpdate(bean);
			
			request.getSession().setAttribute("username", usernameUtente);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddFattItem");
			rd.forward(request, response);
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
