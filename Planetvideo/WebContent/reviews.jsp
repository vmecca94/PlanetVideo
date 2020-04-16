<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.ReviewBean, java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>Recensioni - PlanetVideo</title>
    	<link rel="stylesheet" type="text/css" href="css/reviewsstyle.css">
    	<link rel="stylesheet" type="text/css" href="/Planetvideo/css/formstyle.css">
    	<link rel="icon" href="/Planetvideo/images/PVicon.ico">
	</head>
	<body>
	
	<%
		Collection<?> recensioni = (Collection<?>) request.getAttribute("recensioni");
		if (recensioni == null) {
			response.sendRedirect("./ShowReviews");
			return;
		}
	%>
	
		<header class="top">
			<a href="home.jsp">
      			<img src="/Planetvideo/images/PVlogo.png" alt="PVlogo">
      		</a>
    	</header>
    	
    	<div id="main">
    		<div id="recensioni">
    		<%
    			if (recensioni.size() > 0) {
    				Iterator<?> it = recensioni.iterator();
    		%>
    			<h2>Recensioni</h2>
		        <table>
		          <tr>
		            <th>Voto</th>
		            <th>Recensione</th>
		          </tr>
		    <%
		    	while (it.hasNext()) {
		    		ReviewBean bean = (ReviewBean) it.next();
		    		String codice = bean.getCodice();
		    %>
		    		<tr>
		    			<td><%= bean.getVoto() %></td>
		    			<td><%= bean.getTesto() %></td>
		    		</tr>
		    <%
		    	}
    		}
		    %>
		    	</table>
    		</div>
    		<a id="back" href="catalog.jsp">Torna al catalogo</a>
    		<hr>
    		<h3>Compila il form per aggiungere una recensione</h3>
		    <form action="AddReviewItem" method="get">
		      <fieldset>
		      <div class="tableRow">
		        <label for="voto">Voto:</label>
		        <p>
		        <input type="number" name="voto" value="1" min="1" max="5" required>
		        </p>
		      </div>
		      <div class="tableRow">
		        <label for="testo"></label>
		        <p>
		        <textarea name="testo" placeholder="Inserisci la tua recensione." rows="4" cols="50" required></textarea>
		        </p>
		      </div>
		      <div class="tableRow">
		      <p></p>
		        <p>
		        <input type="submit" value="Aggiungi">&nbsp;
          		<input type="reset" value="Reset">
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