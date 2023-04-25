<%-- 
    Document   : ErrorPage
    Created on : 9 mars 2023, 16:01:30
    Author     : julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="/TestStockEasy/CSS/style.css">

        <title>JSP Page</title>
    </head>
    <body>
        
        <div class="cardProd">
            <%  String name = request.getParameter("error"); 
                int errorCode = Integer.parseInt(name);

                if ( errorCode == 0){
                    out.print("Error Database !");
                } else if ( errorCode == 1){
                    out.print("Username deja existant, veuillez en choisir un autre !");
                } else if ( errorCode == 2){
                    out.print("Inscription validé !");
                } else if ( errorCode == 3){
                    out.print("Compte inexistant !");
                }else if ( errorCode == 4){
                    out.print("Mot de passe incorrect !");
                }

            %>
                <button ><a href="/TestStockEasy/Home.html">Back</a></button>

        </div>
                
    </body>
</html>

