<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.AbbonBean, java.time.*"%>
<!DOCTYPE html PUBLIC>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Catalogo - PlanetVideo</title>
    <link rel="stylesheet" type="text/css" href="/Planetvideo/css/adminstyle.css">
    <link rel="icon" href="/Planetvideo/images/PVicon.ico">
  </head>

  <body>
  
  	<%
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

    	String username = (String) session.getAttribute("username");
		if(username == null) {
			response.sendRedirect("/adminlogin.jsp");
		}
		
		AbbonBean abb = (AbbonBean) session.getAttribute("abbonamento");
  	%>
    
    <header class="top">
      <a href="admincatalog.jsp">
      	<img src="/Planetvideo/images/PVlogo.png" alt="PVlogo" id="himg">
      </a>
      <form id="logout" action="../LogoutAdminUserServlet" method="get">
        <input type="submit" value="Logout">
      </form>
      <div id="welcome">
        <h4>Welcome <%=username %></h4>
      </div>
    </header>
    
    <nav>
      <ul>
        <li class="selected"><a href="admincatalog.jsp">GESTIONE CATALOGO</a></li>
      </ul>
    </nav>
    
    <div id="main">
    <%
    	if(abb.getDatafine().compareTo(LocalDate.now()) >= 0) {
    %>
    <h3>Compila il form per aggiungere film e serie tv.</h3>
    <form action="../AddCatalogItem" method="get" onsubmit="alert('Contenuto correttamente aggiunto al catalogo.')">
      <fieldset>
      <div class="tableRow">
        <label for="codice">Codice:</label>
        <p>
        <input type="text" name="codice" placeholder="codice" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="titolo">Titolo:</label>
        <p>
        <input type="text" name="titolo" placeholder="titolo" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="regista">Regista:</label>
        <p>
        <input type="text" name="regista" placeholder="regista" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="anno">Anno:</label>
        <p>
        <input type="number" name="anno" min="1950" max="2020" placeholder="1950" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="genere">Genere:</label>
        <p>
        <input type="text" name="genere" placeholder="genere" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="urlimg">Url Immagine:</label>
        <p>
        <input type="text" name="urlimg" placeholder="images/catalog/file.jpg" required>
        </p>
      </div>
      <div class="tableRow">
      <p></p>
        <p>
        <input type="submit" value="Aggiungi">
        </p>
      </div>
      </fieldset>
    </form>
    
    <hr>
    
    <h3>Compila il form per rimuovere film e serie tv.</h3>
    <form action="../DelCatalogItem" method="get" onsubmit="alert('Contenuto correttamente rimosso dal catalogo')">
      <fieldset>
      <div class="tableRow">
        <label for="codice">Codice:</label>
        <p>
        <input type="text" name="codice" placeholder="codice" required>
        </p>
      </div>
      <div class="tableRow">
      <p></p>
        <p>
        <input type="submit" value="Rimuovi">
        </p>
      </div>
      </fieldset>
    </form>
    <%
        	} else {
        %>
        <h1>Il tuo abbonamento è scaduto!</h1>
        <h2>Per continuare ad usufruire delle funzionalità del sito e della visione dei contenuti, devi rinnovare il tuo abbonamento.<br></h2>
        <h2>Nella sezione Account, alla voce "Mostra dati abbonamento", potrai rinnovare la tua iscrizione al costo di 11.99 euro.</h2>
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