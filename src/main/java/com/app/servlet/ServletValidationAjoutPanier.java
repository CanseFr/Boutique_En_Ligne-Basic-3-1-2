/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.servlet;

import com.app.Beans.ArticlePanier;
import com.app.Beans.FacturationTVA;
import com.app.repository.REPOSITORYProduit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author julie
 */
@WebServlet(name = "ServletValidationAjoutPanier", urlPatterns = {"/ServletValidationAjoutPanier"})
public class ServletValidationAjoutPanier extends HttpServlet {

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

        HttpSession maSession = request.getSession(false);
        
        Integer id_produit = Integer.parseInt(request.getParameter("id_form")) ;
        Integer qt_produit = Integer.parseInt(request.getParameter("quantite_form"));
        

        Vector<ArticlePanier> listePanier = (Vector<ArticlePanier>) maSession.getAttribute("listPanier");
        REPOSITORYProduit daoGestionProduit = new REPOSITORYProduit();
        ArticlePanier leProduit =  daoGestionProduit.getArticlePanier(id_produit,qt_produit);
        
        // Regle Panier
        listePanier.add(leProduit);
        
        maSession.setAttribute("listePanier", listePanier);
        FacturationTVA facturationTVA = new FacturationTVA();
        maSession.setAttribute("facturationTVA", facturationTVA);
        
        getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response); 
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
            Logger.getLogger(ServletValidationAjoutPanier.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletValidationAjoutPanier.class.getName()).log(Level.SEVERE, null, ex);
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
