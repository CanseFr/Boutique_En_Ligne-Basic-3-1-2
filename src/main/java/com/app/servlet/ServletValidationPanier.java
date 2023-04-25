/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.servlet;

import com.app.Beans.ArticlePanier;
import com.app.Beans.FacturationTVA;
import com.app.repository.REPOSITORYGestionCommande;
import com.app.entites.Client;
import com.app.entites.Commande;
import com.app.entites.LigneCommande;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author julie
 */
@WebServlet(name = "ServletValidationPanier", urlPatterns = {"/ServletValidationPanier"})
public class ServletValidationPanier extends HttpServlet {

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
                
        // recuperation Liste Objet
        Vector<ArticlePanier> listePanier = (Vector<ArticlePanier>) maSession.getAttribute("listPanier");
        
        if (listePanier.size() == 0){
            // Redirection
            getServletContext().getRequestDispatcher("/NoPanierHomePage.jsp").forward(request, response);
        }
        Client client = (Client)  maSession.getAttribute("client");
        List<LigneCommande> listLigneCommande = new ArrayList<>();
         
        // Creation de la commande
        String Date_Commande =  String.valueOf( LocalDate.now());
        int Id_Client = client.getId_Client();
        int HT = Integer.parseInt(request.getParameter("ht"));
        double TTC = FacturationTVA.toTTC(HT) ;
        double TVA = FacturationTVA.theTVA(HT) ;

        Commande commande = new Commande(Date_Commande,HT,TTC,TVA,Id_Client);

         // Creation des ligne de commande
         for(int i = 0; i<listePanier.size(); i++){
             
            int Quantite_commande = listePanier.get(i).getQuantiteCommande() ;
            double Prix_total_Ht = Quantite_commande * listePanier.get(i).getPrix() ;
            int Id_Produit = listePanier.get(i).getId_produit()  ;
            int Id_Commande = commande.getId_Commande() ;// getid de la commande crée aud dessus ;
             
            listLigneCommande.add(new LigneCommande(Quantite_commande,Prix_total_Ht,Id_Produit,Id_Commande));
         }
         
        // Affichage Verification
        listLigneCommande.forEach(System.out::println);
        
        // BDD & Ligne Commande
        REPOSITORYGestionCommande commandeEtLigne = new REPOSITORYGestionCommande();
        commandeEtLigne.creerCommandeAndLigneCommande(commande,listLigneCommande);
        
        // Redirection & Set session 
        maSession.setAttribute("commande", commande);
        maSession.setAttribute("HT", HT);
        getServletContext().getRequestDispatcher("/CommandeConfirmation.jsp").forward(request, response);
        
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
            Logger.getLogger(ServletValidationPanier.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletValidationPanier.class.getName()).log(Level.SEVERE, null, ex);
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
