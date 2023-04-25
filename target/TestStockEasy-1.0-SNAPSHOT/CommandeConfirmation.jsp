<%-- 
    Document   : CommandeConfirmation
    Created on : 23 févr. 2023, 10:35:01
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
    
        <h1 class="cardProd">Page de Paiement</h1>
        <br>

        <div class="cardProd">
            <% for (int i=0; i<listePanier.size(); i++){%>
                <strong>Produit </strong>          : <%=listePanier.elementAt(i).getDescription()%>
                <strong>Quantite</strong>         : <%=listePanier.elementAt(i).getQuantiteCommande()%><br><br>

                <strong>Prix Total Hors Taxe</strong>       : <%=listePanier.elementAt(i).getPrix() * listePanier.elementAt(i).getQuantiteCommande()%> €<br>
                <strong>Tva 19.6 %</strong>       : <%= (facturationTVA.theTVA( listePanier.elementAt(i).getPrix() * listePanier.elementAt(i).getQuantiteCommande()) ) %> €<br>
                <strong>Total TTC</strong>       : <%= (facturationTVA.toTTC(listePanier.elementAt(i).getPrix() * listePanier.elementAt(i).getQuantiteCommande()) )   %> €<br>
                    <p>______________________________________________________________________________</p>
                <% somme += listePanier.elementAt(i).getPrix() * listePanier.elementAt(i).getQuantiteCommande() ;%>
                <br><br>
            <% } %>    
        </div><br><br>
        
        <div class="cardProd">
            <h3 class="cardProd">Facturation</h3>
            <form action="/TestStockEasy/ServletValidationPaiement" method="post" >
                    <strong>Total Hors Taxe : </strong> : <% out.print(somme); %> €<br>
                    <strong>Total TVA : </strong>       : <% out.print(facturationTVA.theTVA(somme) ); %> €<br><br>
                    <strong>Total TTC : </strong>       : <% out.print( facturationTVA.toTTC(somme)  ); %> €<br>
                        <label  for="paie"> Type de paiement :</label>
                        <select name="paie" id="paie">
                            <optgroup label="Type">
                                <option value="CB">CB</option>
                                <option value="PayPal">PayPal</option>
                                <option value="En_Nature">En Nature</option>
                            </optgroup>
                        </select>
                    <br><br>
                    <button type="submit"  class="tablinks" > Paiement </button>
                </form>
            <button  class="tablinks" ><a href="/TestStockEasy/CommandeConfirmation.jsp">Retour au Panier</a></button>
        </div>
    </body>
</html>