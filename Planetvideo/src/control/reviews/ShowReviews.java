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

@WebServlet("/ShowReviews")
public class ShowReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static ReviewModel<ReviewBean> review = new ReviewModelDM();
    
    public ShowReviews() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codice = request.getParameter("codice");
		if (codice == null) {
			codice = (String) request.getSession().getAttribute("codiceCont");
		}
		request.getSession().setAttribute("codiceCont", codice);
		
		try {
			request.removeAttribute("recensioni");
			request.setAttribute("recensioni", review.doRetrieveByKey(codice));
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviews.jsp");
		rd.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
