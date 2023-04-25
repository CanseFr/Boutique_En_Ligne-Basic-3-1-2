/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.servlet;

import com.app.Beans.ArticlePanier;
import com.app.Beans.FacturationTVA;
import com.app.repository.REPOSITORYGestionFacture;
import com.app.entites.Client;
import com.app.entites.Commande;
import com.app.entites.Facture;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author julie
 */
@WebServlet(name = "ServletValidationPaiement", urlPatterns = {"/ServletValidationPaiement"})
public class ServletValidationPaiement extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        // recuperation Session
        HttpSession maSession = request.getSession(false);
                
        // Recuperation des Objets
        Vector<ArticlePanier> listePanier = (Vector<ArticlePanier>) maSession.getAttribute("listPanier");
        Client client = (Client)  maSession.getAttribute("client");
        Commande commande = (Commande)  maSession.getAttribute("commande");
         
        // Creation de la Facture
        String Date_Commande =  String.valueOf( LocalDate.now());
        int Id_Client = client.getId_Client();
        int HT = (int)  maSession.getAttribute("HT");
        double TVA = FacturationTVA.theTVA(HT) ;
        double TTC = FacturationTVA.toTTC(HT) ;
        int Id_Commande = commande.getId_Commande() ;
        String typePaiement = request.getParameter("paie");
        System.out.println(HT+"<-------------**--HT");
        System.out.println(TVA+"<-------------**--TVA");
        System.out.println(TTC+"<-------------**--TTC");
        
        Facture facture = new Facture(Id_Client,Date_Commande,HT,TVA,TTC,Id_Commande,typePaiement);
        System.out.println("------>Facture"+facture.toString());
        
        // BDD & Ligne Commande
        REPOSITORYGestionFacture facturebdd = new REPOSITORYGestionFacture();
        facturebdd.creerFacture(facture);
        
        // Destruction
        //Vector<ArticlePanier> listePanier = (Vector<ArticlePanier>) maSession.getAttribute("listPanier");
        //Client client = (Client)  maSession.getAttribute("client");
        String id_produitStr = request.getParameter("id");
        listePanier.clear();
        HT = 0;
        
        // Set Initialisation
        maSession.setAttribute("HT", HT);
        maSession.setAttribute("listePanier", listePanier);
                
        // Redirection 
        getServletContext().getRequestDispatcher("/ValidationDuPaiement.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletValidationPaiement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletValidationPaiement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
