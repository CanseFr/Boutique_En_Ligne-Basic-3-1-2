<%-- 
    Document   : CommandeHomePage
    Created on : 23 févr. 2023, 20:36:03
    Author     : julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>     
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<%@page import="com.app.entites.Commande"%>
<%@page import="com.app.entites.LigneCommandeDeCommandeRegister"%>

<jsp:useBean id="listCommande" type="ArrayList<Commande>" scope="session"/>
<jsp:useBean id="listLigneCommandeRegister" type="ArrayList<LigneCommandeDeCommandeRegister>" scope="session"/>


<%@ include file="TopMenu.jsp" %>


        <h1 class="cardProd">Vos Commandes</h1><br>
            
            <% for (Commande x : listCommande){%>

                <div class="cardProd">
                    <strong>ID Commande</strong>       : <%=x.getId_Commande()%><br>

                    <% for (LigneCommandeDeCommandeRegister y : listLigneCommandeRegister ){%>
                        <%    if (y.getId_Commande() == x.getId_Commande()){%>
                            <div class="cardProd">

                                <strong>Artciles : </strong> <%=y.toString()%><br>

                            </div>
                        <% } %> 
                    <% } %> 
                    <br>
                    <strong>Date Commande</strong>     : <%=x.getDate_Commande()%><br><br>
                    
                    <strong>Total Hors Taxe</strong>   : <%=x.getHT()%><br>
                    <strong>Total TVA 19.%</strong>    : <%=x.getTVA()%><br>
                    <strong>Total TTC</strong>         : <%=x.getTTC()%><br>
                    
                </div><br>                
            <% } %> 
            

                
        <br> 
    </body>
</html>            

