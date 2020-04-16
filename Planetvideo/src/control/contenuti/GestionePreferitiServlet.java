package control.contenuti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CatalogBean;
import it.unisa.Cart;
import model.CatalogModel;
import model.CatalogModelDM;

@WebServlet("/GestionePreferitiServlet")
public class GestionePreferitiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static CatalogModel<CatalogBean> catalog = new CatalogModelDM();
	
    public GestionePreferitiServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart<CatalogBean> cart = (Cart<CatalogBean>) request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart<CatalogBean>();
			request.getSession().setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");
		
		try {
			if(action != null) {
				if(action.equalsIgnoreCase("addCart")) {
					String codice = request.getParameter("codice");
					cart.addElement(catalog.doRetrieveByKey(codice));
				} else if(action.equalsIgnoreCase("delCart")) {
					String codice = request.getParameter("codice");
					cart.deleteElement(catalog.doRetrieveByKey(codice));
				}
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		response.sendRedirect("/Planetvideo/catalog.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
