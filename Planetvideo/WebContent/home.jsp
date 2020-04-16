<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.*"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - PlanetVideo</title>
    <link rel="stylesheet" type="text/css" href="css/homestyle.css">
    <link rel="icon" href="images/PVicon.ico">
  </head>

  <body>
  
    <%
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

    	String username = (String) session.getAttribute("username");
		if(username == null) {
		response.sendRedirect("login.jsp");
		}
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
        <li class="selected"><a href="home.jsp">HOME</a></li>
        <li><a href="catalog.jsp">CATALOGO</a></li>
        <li><a href="myaccount.jsp">ACCOUNT</a></li>
      </ul>
    </nav>
    
    <div id="main">
    <h2>Prossimo film in arrivo su PlanetVideo</h2>
      <div id="film">
        <h3>Titolo: JOBS</h3><br>
        <h3>Genere: Biografico</h3><br>
        <div id="video">
          <iframe width="560" height="315" src="https://www.youtube.com/embed/sLk1g_2acgc" allowfullscreen>
          </iframe>
        </div>
      </div>
      
      <h2>Prossime serie tv in arrivo su PlanetVideo</h2>
      <div id="serie">
        <div>
        <img src="images/catalog/young.jpg" alt="young" class="itemimg">
        <h3>Titolo: Young Sheldon</h3><br>
        <h3>Genere: Commedia</h3>
        </div>
        <div>
        <img src="images/catalog/bad.jpg" alt="bad" class="itemimg">
        <h3>Titolo: Breaking Bad</h3><br>
        <h3>Genere: Commedia nera</h3>
        </div>
      </div>
    </div>
    
    <footer>
      &copy; 2018, PlanetVideo
      <br>
      All trademarks and registered trademarks appearing on 
      this site are the property of their respective owners.
    </footer>
  </body>
</html>