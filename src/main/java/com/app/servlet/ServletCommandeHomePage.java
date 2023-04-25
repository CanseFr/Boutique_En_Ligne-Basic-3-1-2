/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.servlet;

import com.app.repository.REPOSITORYGestionCommande;
import com.app.entites.Client;
import com.app.entites.Commande;
import com.app.entites.LigneCommandeDeCommandeRegister;
import com.app.repository.REPOSITORYLigneCommandeDeCommandeRegister;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author julie
 */
@WebServlet(name = "ServletCommandeHomePage", urlPatterns = {"/ServletCommandeHomePage"})
public class ServletCommandeHomePage extends HttpServlet {

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
        
        // Recuperation session client, nom
        HttpSession maSession = request.getSession(false);
        Client client = (Client)  maSession.getAttribute("client");
        
        // Requete Sql depuis id client -> Commande
        REPOSITORYGestionCommande daoGestionCommande = new REPOSITORYGestionCommande();
        ArrayList<Commande> listCommande = daoGestionCommande.listerCommandeUtilisateur(client);
        
        //*********
        // Requete Sql depuis id client -> Ligne Commande
        REPOSITORYLigneCommandeDeCommandeRegister GestionLigneRegisterCommande = new REPOSITORYLigneCommandeDeCommandeRegister();
        ArrayList<LigneCommandeDeCommandeRegister> listLigneCommandeRegister = GestionLigneRegisterCommande.listAllLineCommandeFromClt(client);
        
        
        // !!! FAIR UNE BOUCLE POUR AJOUTER A CHAQQUE Liste de commande de  COMMANDE les ligne commande correspondate par id commande  !!!
        // Rassemblement ligne commande -> par commande

        for(int i = 0; i< listCommande.size(); i++){
            for (int j = 0; j<listLigneCommandeRegister.size(); j++){
                if (listCommande.get(i).getId_Commande() == listLigneCommandeRegister.get(j).getId_Commande()){
                    listCommande.get(i).setLigneCommande(listLigneCommandeRegister.get(j));
                }
            }
        }
        
        //System.out.println("---->START LigneCMD<----");
        //for (LigneCommandeDeCommandeRegister x : listLigneCommandeRegister ){
        //    System.out.println(x.toString());
        //}
        //System.out.println("---->END LigneCMD<----");
        
        System.out.println("---->START CMD<----");
        for (Commande x : listCommande ){
            System.out.println("Commande ->" + x.getId_Commande());

            for (LigneCommandeDeCommandeRegister y : listLigneCommandeRegister ){
                if (y.getId_Commande() == x.getId_Commande()){
                    System.out.println("Line Commande ->" + y.toString());
                }
            }
        }
        System.out.println("---->END CMD<----");
        
        // Set Objet de type liste de commande a la page 
        if (listCommande.size() == 0){
            String MessagePaDeCommande = "Aucune commande presente";
            maSession.setAttribute("MessagePaDeCommande", MessagePaDeCommande);
            // Redirection
            getServletContext().getRequestDispatcher("/NoCommandeHomePage.jsp").forward(request, response);
            
        } else {
            maSession.setAttribute("listCommande", listCommande);
            maSession.setAttribute("listLigneCommandeRegister", listLigneCommandeRegister);
        }
      
        // Redirection
        getServletContext().getRequestDispatcher("/CommandeHomePage.jsp").forward(request, response);
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
            Logger.getLogger(ServletCommandeHomePage.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletCommandeHomePage.class.getName()).log(Level.SEVERE, null, ex);
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
