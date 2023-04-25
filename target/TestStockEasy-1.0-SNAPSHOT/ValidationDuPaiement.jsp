<%-- 
    Document   : ValidationDuPaiement
    Created on : 23 févr. 2023, 19:11:26
    Author     : julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.app.Beans.ArticlePanier"%>
<%@page import="com.app.Beans.FacturationTVA"%>
<%@page import="java.util.Vector"%>
<jsp:useBean id="listePanier" type="Vector<ArticlePanier>" scope="session"/>
<jsp:useBean id="facturationTVA" type="FacturationTVA" scope="session"/>

<%@ include file="TopMenu.jsp" %>

<br>
<div class="cardProd">
    <img src="/TestStockEasy/Media/logovalidation.png" alt="Logo"/>
    <h1>Paiement validé</h1>
</div>
