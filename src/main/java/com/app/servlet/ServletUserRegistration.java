/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.servlet;

import com.app.repository.REPOSITORYGestionClient;
import com.app.entites.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 *
 * @author julie
 */
@WebServlet(name = "ServletUserRegistration", urlPatterns = {"/ServletUserRegistration"})
public class ServletUserRegistration extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String userName = request.getParameter("user_name").trim();
            String password = request.getParameter("user_pass").trim();
            String mail = request.getParameter("user_mail").trim();
            String adresse = request.getParameter("user_adresse").trim();
            String cp = request.getParameter("user_cp").trim();
            String ville = request.getParameter("user_ville").trim();

            REPOSITORYGestionClient daoClient = new REPOSITORYGestionClient();
            Client client =  daoClient.chercherIdentifier(userName);

            // verif user existant
            if (client.getUser_Name() == null){   
                Client newClient = new Client(userName,password,mail,adresse,cp,ville);
                daoClient.registerUser(newClient);

                // Code Inscription validé
                getServletContext().getRequestDispatcher("/ErrorPage.jsp?error=2").forward(request, response);
                                
            } else {
                // Code erreur User deja existant
                getServletContext().getRequestDispatcher("/ErrorPage.jsp?error=1").forward(request, response);                 
            }
            
        } catch (Exception e){
            // Code erreur Interne DB
            getServletContext().getRequestDispatcher("/ErrorPage.jsp?error=0").forward(request, response);            
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
