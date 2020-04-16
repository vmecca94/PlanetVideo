package control.contenuti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CatalogBean;
import model.CatalogModel;
import model.CatalogModelDM;


@WebServlet("/AddCatalogItem")
public class AddCatalogItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static CatalogModel<CatalogBean> catalog = new CatalogModelDM();

    public AddCatalogItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String codice = request.getParameter("codice");
			String titolo = request.getParameter("titolo");
			String regista = request.getParameter("regista");
			int anno = Integer.parseInt(request.getParameter("anno"));
			String genere = request.getParameter("genere");
			String urlimg = request.getParameter("urlimg");
					
			CatalogBean bean = new CatalogBean();
			bean.setCodice(codice);
			bean.setTitolo(titolo);
			bean.setRegista(regista);
			bean.setAnno(anno);
			bean.setGenere(genere);
			bean.setUrlimg(urlimg);
				
			catalog.doSave(bean);
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		response.sendRedirect("/Planetvideo/admin/admincatalog.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
