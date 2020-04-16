<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Abbonamenti - PlanetVideo</title>
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
        <li><a href="admincatalog.jsp">GESTIONE CATALOGO</a></li>
        <li class="selected"><a href="adminabb.jsp">GESTIONE ACCOUNT</a></li>
      </ul>
    </nav>
  
    <div id="main">
    <h3>Compila il form per aggiungere un abbonamento.</h3>
    <form action="../AddAbbonItem" method="get" onsubmit="alert('Form submitted')">
      <fieldset>
      <div class="tableRow">
        <label for="numeroabb">Numero Abbonamento:</label>
        <p>
        <input type="number" name="numeroabb" placeholder="numero abbonamento" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="costo">Costo:</label>
        <p>
        <input type="text" name="costo" placeholder="costo abbonamento" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="datainizio">Data inizio:</label>
        <p>
        <input type="date" name="datainizio" required>
        <p>
      </div>
      <div class="tableRow">
        <label for="datafine">Data fine:</label>
        <p>
        <input type="date" name="datafine" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="usernameUtente">Username utente:</label>
        <p>
        <input type="text" name="usernameUtente" placeholder="username utente" required>
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
    <h3>Compila il form per aggiungere una fattura.</h3>
    <form action="../AddFattItem" method="get" onsubmit="alert('Form submitted')">
      <fieldset>
      <div class="tableRow">
        <label for="numfattura">Numero Fattura:</label>
        <p>
        <input type="number" name="numfattura" placeholder="numero fattura" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="importo">Importo:</label>
        <p>
        <input type="text" name="importo" placeholder="importo fattura" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="dataemissione">Data Emissione:</label>
        <p>
        <input type="date" name="dataemissione" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="numeroAbbonamento">Numero Abbonamento:</label>
        <p>
        <input type="number" name="numeroAbbonamento" required>
        </p>
      </div>
      <div class="tableRow">
        <label for="uname">Username utente:</label>
        <p>
        <input type="text" name="uname" placeholder="username utente" required>
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
    </div>
    
    <footer>
      &copy; 2018, PlanetVideo
      <br>
      All trademarks and registered trademarks appearing on 
      this site are the property of their respective owners.
    </footer>
  </body>
</html>