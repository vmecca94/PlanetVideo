<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Admin Error - PlanetVideo</title>
    <link rel="stylesheet" type="text/css" href="/Planetvideo/css/formstyle.css">
    <link rel="icon" href="/Planetvideo/images/PVicon.ico">
  </head>

  <body>
    <header class="top">
      <img src="/Planetvideo/images/PVlogo.png" alt="PVlogo">
    </header>
    
    <div id="main">
    <h2>Invalid username or password.<br>
        Please, try again!
    </h2>
    <form action="../LoginAdminUserServlet" method="post">
      <fieldset>
      <div class="tableRow">
        <label for="username">Username:</label>
        <p>
        <input type="text" name="username" placeholder="username" required>
        </p> 
      </div>
      <div class="tableRow">   
        <label for="password">Password:</label>
        <p>
        <input type="password" name="userpass" placeholder="********" required>
        </p>
      </div>
      <div class="tableRow">
      <p></p>
      <p>
        <input type="submit" value="Login">&nbsp;
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