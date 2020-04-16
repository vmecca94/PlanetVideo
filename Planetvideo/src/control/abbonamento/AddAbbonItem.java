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


@WebServlet("/AddAbbonItem")
public class AddAbbonItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static AbbonModel<AbbonBean> abbon = new AbbonModelDM();

    public AddAbbonItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LocalDate datainizio = LocalDate.now();
			LocalDate datafine = datainizio.plusMonths(1);
			String usernameUtente = (String) request.getSession().getAttribute("user");
		
			AbbonBean bean = new AbbonBean();
			bean.setDatainizio(datainizio);
			bean.setDatafine(datafine);
			bean.setUsernameUtente(usernameUtente);
		
			abbon.doSave(bean);
			
			request.getSession().setAttribute("user", usernameUtente);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddFattItemZero");
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
