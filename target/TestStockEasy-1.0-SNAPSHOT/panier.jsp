<%-- 
    Document   : panier
    Created on : 21 févr. 2023, 21:26:07
    Author     : julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.app.Beans.ArticlePanier"%>
<%@page import="com.app.Beans.FacturationTVA"%>
<%@page import="java.util.Vector"%>
<jsp:useBean id="listePanier" type="Vector<ArticlePanier>" scope="session"/>
<jsp:useBean id="facturationTVA" type="FacturationTVA" scope="session"/>

<%@ include file="TopMenu.jsp" %>
        


    <%int somme = 0;%>
    
        <h1 class="cardProd">Votre panier</h1>
        <br>

            <% for (int i=0; i<listePanier.size(); i++){%>

                <div class="cardProd">

                <strong>Produit : </strong>          : <%=listePanier.elementAt(i).getDescription()%><br>
                <strong>Quantite</strong>         : <%=listePanier.elementAt(i).getQuantiteCommande()%><br><br>

                <strong>Prix Total Hors Taxe</strong>       : <%=listePanier.elementAt(i).getPrix() * listePanier.elementAt(i).getQuantiteCommande()%> €<br>
                <strong>Tva 19.6 %</strong>       : <%= (facturationTVA.theTVA( listePanier.elementAt(i).getPrix() * listePanier.elementAt(i).getQuantiteCommande()) ) %> €<br>
                <strong>Total TTC</strong>       : <%= (facturationTVA.toTTC(listePanier.elementAt(i).getPrix() * listePanier.elementAt(i).getQuantiteCommande()) )   %> €<br>
                
                    
                    <a href="/TestStockEasy/ServletSupprimerElementPanier?id=<%=listePanier.elementAt(i).getId_produit()%>">
                        <img style="width: 30px;" class="imgTrash" src="/TestStockEasy/Media/trash.png" alt="Supprimer"/>  
                    </a>

                <% somme += listePanier.elementAt(i).getPrix() * listePanier.elementAt(i).getQuantiteCommande() ;%>

                </div><br>

            <% } %>    

            <br>
            <div class="cardProd">
                <strong>Total Hors Taxe : </strong>       : <% out.print(somme); %> €<br>
                <strong>Total TVA : </strong>       : <% out.print(facturationTVA.theTVA(somme) ); %> €<br><br>
                <strong>Total TTC : </strong>       : <% out.print( facturationTVA.toTTC(somme)  ); %> €<br>
                <button  class="tablinks" ><a href="/TestStockEasy/ServletValidationPanier?ht=<%=somme %>" >Valider Panier</a></button>
                <button  class="tablinks" ><a href="/TestStockEasy/afficherProduit.jsp">Continuer le Shopping</a></button>
            </div>
                
    </body>
</html>