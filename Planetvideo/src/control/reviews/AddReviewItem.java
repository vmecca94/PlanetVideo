package control.reviews;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ReviewBean;
import model.ReviewModel;
import model.ReviewModelDM;


@WebServlet("/AddReviewItem")
public class AddReviewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static ReviewModel<ReviewBean> review = new ReviewModelDM();
	
    public AddReviewItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String testo = request.getParameter("testo");
			int voto = Integer.parseInt(request.getParameter("voto"));
			String codice = (String) request.getSession().getAttribute("codiceCont");
			
			ReviewBean bean = new ReviewBean();
			bean.setTesto(testo);
			bean.setVoto(voto);
			bean.setCodice(codice);
			
			review.doSave(bean);
			
			request.getSession().setAttribute("codiceCont", codice);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviews.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
