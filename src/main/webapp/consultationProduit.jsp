<%-- 
    Document   : consultationProduit
    Created on : 20 févr. 2023, 21:58:39
    Author     : julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.app.entites.Produit"%>
<jsp:useBean id="leProduit" type="Produit" scope="session"/>

<%@ include file="TopMenu.jsp" %>
       
        <h1 class="cardProd" >Article(s)</h1>
        <br>

            <form class="cardProd" action="/TestStockEasy/ServletValidationAjoutPanier" method="post"> 
                <strong>Prix</strong>                : <%=leProduit.getPrix()%> €<br>
                <strong>Quantité disponible</strong> : <%=leProduit.getQuantite()%><br>
                <strong>Description</strong>         : <%=leProduit.getDescription()%><br>
                <input type="number" id="qt" value="1" name="quantite_form" min="1" max="<%=leProduit.getQuantite()%>" >
                <input type="number" id="id" name="id_form" value="<%=leProduit.getId_produit()%>" hidden >
                
                <button type="submit">Ajouter au Panier</button>
                <button  class="tablinks" ><a href="/TestStockEasy/afficherProduit.jsp">Retourner a la boutique</a></button>
            </form>
                 
        <br>                
    </body>
</html>
