package control.contenuti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CatalogBean;
import it.unisa.Cart;
import model.CatalogModel;
import model.CatalogModelDM;


@WebServlet("/ShowCatalog")
public class ShowCatalog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static CatalogModel<CatalogBean> catalog = new CatalogModelDM();

    public ShowCatalog() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sort = request.getParameter("sort");
		
		try {
			request.removeAttribute("products");
			request.setAttribute("products", catalog.doRetrieveAll(sort));
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		Cart<CatalogBean> cart = (Cart<CatalogBean>) request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart<CatalogBean>();
			request.getSession().setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");
		
		try {
			if(action != null) {
				if(action.equalsIgnoreCase("details")) {
					String codice = request.getParameter("codice");
					request.removeAttribute("product");
					request.setAttribute("product", catalog.doRetrieveByKey(codice));
				}
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalog.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
