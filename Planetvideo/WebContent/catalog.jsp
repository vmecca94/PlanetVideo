<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.*, bean.CatalogBean, bean.AbbonBean, java.time.*"%>
<!DOCTYPE html PUBLIC>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalogo - PlanetVideo</title>
    <link rel="stylesheet" type="text/css" href="css/catalogstyle.css">
    <link rel="icon" href="images/PVicon.ico">
  </head>

  <body>

    <%
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		String username = (String) session.getAttribute("username");
		if(username == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		AbbonBean abb = (AbbonBean) session.getAttribute("abbonamento");
    	
    	Collection<?> products = (Collection<?>) request.getAttribute("products");
    	if(products == null) {
    		response.sendRedirect("./ShowCatalog");
    		return;
    	}
    	
    	CatalogBean product = (CatalogBean) request.getAttribute("product");
    	
    	Cart cart = (Cart) request.getAttribute("cart");
    %>
    
    <header class="top">
      <a href="home.jsp">
      <img src="images/PVlogo.png" alt="PVlogo" id="himg">
      </a>
      <form action="LogoutUserServlet" method="get">
        <input type="submit" value="Logout">
      </form>
      <div id="welcome">
        <h4>Welcome <%=username %></h4>
      </div>
    </header>
	
	<nav>
      <ul>
        <li><a href="home.jsp">HOME</a></li>
        <li class="selected"><a href="catalog.jsp">CATALOGO</a></li>
        <li><a href="myaccount.jsp">ACCOUNT</a></li>
      </ul>
    </nav>
    
    <div id=tableContainer>
    
    <%
    	if(abb.getDatafine().compareTo(LocalDate.now()) >= 0) {
    %>
      <aside>
          <%
    	if(product != null) {
    %>
        
          <div id="dettagli">
            <h2>Dettagli</h2>
              <p>Titolo: <%=product.getTitolo() %><br>
                 Regista: <%=product.getRegista() %><br>
                 Anno: <%=product.getAnno() %><br>
                 Genere: <%=product.getGenere() %><br>
              </p>
          </div>
    <%
    	}
    %>
          
          <div id="preferiti">
            <h2>Lista preferiti</h2>
            <table>
              <tr>
                <th>Codice</th>
                <th>Titolo</th>
              </tr>
          
    <%
    	List<CatalogBean> catalcart = cart.getList();
        for(CatalogBean beancart : catalcart) {
    %>
              <tr>
                <td><%=beancart.getCodice() %></td>
                <td><%=beancart.getTitolo() %></td>
                <td><a href="GestionePreferitiServlet?action=delCart&codice=<%=beancart.getCodice() %>">Rimuovi dai Preferiti</a></td>
              </tr>
    <%
        }
    %>
            </table>
          </div>
        </aside>
    
        <div id="catalogo">
    
    <%
    	if(products.size() > 0) {
      		Iterator<?> it = products.iterator();
      		
      		while(it.hasNext()) {
      			CatalogBean bean = (CatalogBean) it.next();
    %>
          <div id="item">
            <img src=<%= bean.getUrlimg()%> alt="urlimg" id="itemimg">
            <h4>Titolo: <%= bean.getTitolo()%></h4><br>
            <a href="ShowCatalog?action=details&codice=<%=bean.getCodice()%>">Dettagli</a><br>
            <a href="GestionePreferitiServlet?action=addCart&codice=<%=bean.getCodice()%>">Aggiungi ai Preferiti</a><br>
            <a href="ShowReviews?codice=<%=bean.getCodice()%>">Vai a Recensioni</a>
          </div>
    <%
    		}
      	}
    %>
        </div>
        
        <%
        	} else {
        %>
        <div id="main">
        <h1>Il tuo abbonamento &#232; scaduto!</h1>
        <h2>Per continuare ad usufruire delle funzionalit&#224; del sito e della visione dei contenuti, devi rinnovare il tuo abbonamento.<br></h2>
        <h2>Nella sezione Account, alla voce "Mostra dati abbonamento", potrai rinnovare la tua iscrizione al costo di 11.99 euro.</h2>
        </div>
        <%
        	}
        %>
      </div>
      
    <footer>
      &copy; 2018, PlanetVideo
      <br>
      All trademarks and registered trademarks appearing on 
      this site are the property of their respective owners.
    </footer>
  </body>
</html>