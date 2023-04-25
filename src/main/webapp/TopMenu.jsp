<%-- 
    Document   : TopMenu
    Created on : 22 févr. 2023, 18:24:04
    Author     : julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/TestStockEasy/CSS/style.css">

    </head>
    <body>
        
    <h3 style="color:green;">Connecté</h3>
    <div class="topbar">
        <button ><a href="/TestStockEasy/ServletAfficherProduitEnStock">Produits</a></button>
        <button ><a href="/TestStockEasy/ServletValidationAjoutPanier">Panier</a></button> <!-- Verifier ce lien regler probleme lors d'un panier deja rempli ...-->
        <button ><a href="/TestStockEasy/ServletCommandeHomePage">Commande</a></button>
        <button ><a href="/TestStockEasy/ServletHistoriqueFacture">Facture</a></button>
        <button ><a href="/TestStockEasy/Parametre.jsp">Parametre</a></button>
        <button class="deco"  ><a href="/TestStockEasy/SerlvetDeconnexion">Deconnexion</a></button>
    </div><br><br><br><br>

        
        

