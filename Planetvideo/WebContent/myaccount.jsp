<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.FattBean, java.util.*"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Il mio account - PlanetVideo</title>
    <link rel="stylesheet" type="text/css" href="css/accountstyle.css">
    <link rel="icon" href="images/PVicon.ico">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
		$(document).ready(function(){
	  		$("#button1").click(function(){
				$.ajax({
    				type: 'GET',
    				url: 'ShowDatiAccount',
    				data: 'username=${username}',
    				headers: {
    					Accept: "application/json; charset=utf-8",
    					"Content-Type": "application/json; charset=utf-8"
    				},
    				success: function(result){
    					var utente = $.parseJSON(result);
    					var result1 = document.getElementById("result1");
    					result1.innerHTML = "<h2>Info Utente</h2>" +
    					                    "<p id='userContainer'>Nome: " + utente.nome + "<br>" + 
    					                       "Cognome: " + utente.cognome + "<br>" +
    					                       "Data di nascita: " + utente.datanascita + "<br>" +
    					                       "Email: " + utente.email + "<br>" +
    					                       "Username: " + utente.username + "</p>";
    				}
    			})
			})
		});
	
		$(document).ready(function(){
			var today = new Date();
			var estendi = document.createElement("a");
			estendi.innerHTML = "Rinnova abbonamento";
			estendi.setAttribute("href", "EstendiAbbonamento");
			estendi.setAttribute("id", "estendi");
			$("#button2").click(function(){
				$.ajax({
    				type: 'GET',
    				url: 'ShowDatiAbbonamento',
    				data: 'username=${username}',
    				headers: {
    					Accept: "application/json; charset=utf-8",
    					"Content-Type": "application/json; charset=utf-8"
    				},
    				success: function(result){
    					var abbonamento = $.parseJSON(result);
    					var result2 = document.getElementById("result2");
    					result2.innerHTML = "<h2>Info Abbonamento</h2>" +
	                                        "<p id='abbContainer'>Costo: 11.99<br>" +
	                                        "Data inizio: " + abbonamento.datainizio.day + "/" + abbonamento.datainizio.month + "/" + abbonamento.datainizio.year + "<br>" +
	                                        "Data fine: " + abbonamento.datafine.day + "/" + abbonamento.datafine.month + "/" + abbonamento.datafine.year + "<br>";
	                    if(abbonamento.datafine.year < today.getFullYear()){
	                    	document.getElementById("abbContainer").appendChild(estendi);
	                    }
	                    else if(abbonamento.datafine.month < today.getMonth() + 1) {
	                    	document.getElementById("abbContainer").appendChild(estendi);
	                    }
	                    else if(abbonamento.datafine.day < today.getDate()) {
	                    	document.getElementById("abbContainer").appendChild(estendi);
	                    }
    				}
    			})
			})
		});
    </script>
  </head>

  <body>
  
    <%
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    
        String username = (String) session.getAttribute("username");
    	if(username == null) {
    		response.sendRedirect("login.jsp");
    		return;
    	}
    	
    	Collection<?> fatture = (Collection<?>) request.getAttribute("fatture");
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
        <li><a href="catalog.jsp">CATALOGO</a></li>
        <li class="selected"><a href="myaccount.jsp">ACCOUNT</a></li>
      </ul>
    </nav>
    
    <div id="tableContainer">
      <div id="actions">
        <div id="userdet">
          <input type="button" value="Mostra dati utente" id="button1">
        </div>
        
        <div id="abbdet">
          <input type="button" value="Mostra dati abbonamento" id="button2">
        </div>
        
        <div id="fattdet">
          <a href=ShowFatture?uname=<%= username %>>Mostra Fatture</a>
        </div>
      </div>
      
      <div id="results">
        <div id="result1"></div>
        <div id="result2"></div>
      </div>
      
      <div id="fatture">
      
    <%
    if (fatture != null) {
    	if(fatture.size() > 0) {
      		Iterator<?> it = fatture.iterator();
    %>
        <h2>Fatture</h2>
        <table>
          <tr>
            <th>Numero</th>
            <th>Data</th>
            <th>Importo</th>
          </tr>
    
    <%  		
      		while(it.hasNext()) {
      			FattBean bean = (FattBean) it.next();
    %>

      <tr>
        <td><%= bean.getNumfattura()%></td>
        <td><%= bean.getDataemissione()%></td>
        <td><%= bean.getImporto()%></td>
      </tr>
      
    <%
      		}
    	}
    }
    %>
        </table>
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