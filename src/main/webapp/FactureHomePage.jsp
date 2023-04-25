<%-- 
    Document   : FactureHomePage
    Created on : 23 févr. 2023, 22:15:53
    Author     : julie
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>     
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<%@page import="com.app.entites.Facture"%>

<jsp:useBean id="listFacture" type="ArrayList<Facture>" scope="session"/>


<%@ include file="TopMenu.jsp" %>


        <h1 class="cardProd">Vos factures</h1><br>
            
            <% for (int i=0; i<listFacture.size(); i++){%>

                <div class="cardProd">
                    <strong>Date Facturation</strong>          : <%=listFacture.get(i).getDate_Facturation()%><br><br>
                    <strong>Total Hors Taxe</strong>          : <%=listFacture.get(i).getTotal_HT()%><br>
                    <strong>Total TVA 19.%</strong>          : <%=listFacture.get(i).getTotal_TVA()%><br>
                    <strong>Total TTC</strong>          : <%=listFacture.get(i).getTotal_TTC()%><br><br>
                    <strong>ID Commande</strong>          : <%=listFacture.get(i).getId_Commande()%><br>
                    <strong>Paiement</strong>          : <%=listFacture.get(i).getTypePaiement()%><br>
                    
                    <button ><a href="/TestStockEasy/sqdf">Facture PDF</a></button>

                </div><br> 
            <% } %>    
                
        <br> 
    </body>
</html>   
