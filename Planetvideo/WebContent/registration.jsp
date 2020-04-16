<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration - PlanetVideo</title>
    <link rel="stylesheet" type="text/css" href="css/formstyle.css">
    <link rel="icon" href="images/PVicon.ico">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/form-validation.js"></script>
  </head>

  <body>
    <header class="top">
      <a href="index.jsp">
      <img src="images/PVlogo.png" alt="PVlogo" id="himg">
      </a>
    </header>
  
    <div id="main">
    <h2>Inserisci qui i dati per la registrazione.</h2>
    
    <form name="myForm" action="RegistrationUserServlet" onsubmit="return validateForm(this)" method="post">
      <fieldset>
        <div class="tableRow">
          <label for="nome">Nome:</label>
          <p>
          <input type="text" name="nome" placeholder="nome" required>
          </p>
        </div>
        <div class="tableRow">
          <label for="cognome">Cognome:</label>
          <p>
          <input type="text" name="cognome" placeholder="cognome" required>
          </p>
        </div>
        <div class="tableRow">
          <label for="datanascita">Data di nascita:</label>
          <p>
          <input type="date" name="datanascita">
          </p>
        </div>
        <div class="tableRow">
          <label for="numcarta">Carta di credito:</label>
          <p>
          <input type="text" name="numcarta" placeholder="0000000000000000" required>
          </p>
          <p class="hidden" id="errorecarta">You have entered an invalid credit card! You must enter 16 digits.</p>
        </div>
        <div class="tableRow">
          <label for="email">E-mail:</label>
          <p>
          <input type="email" name="email" placeholder="test@example.com" required>
          </p>
          <p class="hidden" id="erroremail">You have entered an invalid email address!</p>
        </div>
        <div class="tableRow">
          <label for="username">Username:</label>
          <p>
          <input type="text" name="username" placeholder="username" required>
          </p>
        </div>
        <div class="tableRow">
          <label for="password">Password:</label>
          <p>
          <input type="password" name="userpass" required>
          </p>
        </div>
        <div class="tableRow">
          <p></p>
          <p>
          <input type="submit" value="Registrati">&nbsp;
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