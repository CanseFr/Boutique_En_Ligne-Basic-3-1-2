<%-- 
    Document   : afficherProduit
    Created on : 20 févr. 2023, 21:04:12
    Author     : julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.app.entites.Produit"%>
<%@page import="java.util.Vector"%>

<jsp:useBean id="listeProduit" type="Vector<Produit>" scope="session"/>

<%@ include file="TopMenu.jsp" %>
       
<h1 class="cardProd">Voici les produits disponible</h1>
       <br>
            
            <% for (int i=0; i<listeProduit.size(); i++){%>

                <div class="cardProd">
                    <strong>ID Produit</strong>          : <%=listeProduit.elementAt(i).getId_produit()%><br>
                    <strong>Prix</strong>                : <%=listeProduit.elementAt(i).getPrix()%> €<br>
                    <strong>Quantité disponible</strong> : <%=listeProduit.elementAt(i).getQuantite()%><br>
                    <strong>Description</strong>         : <%=listeProduit.elementAt(i).getDescription()%>

                    <button  class="buttonCard" ><a href="/TestStockEasy/ServletConsulterProduit?id=<%=listeProduit.elementAt(i).getId_produit()%>">Decouvrir</a></button>
                </div>
                <br>                
            <% } %>    
                
        <br> 
    </body>
</html>

