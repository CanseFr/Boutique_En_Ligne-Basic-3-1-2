/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.servlet;

import com.app.Beans.ArticlePanier;
import com.app.repository.REPOSITORYGestionClient;
import com.app.entites.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;


/**
 *
 * @author julie
 */
@WebServlet(name = "ServletAuthentification", urlPatterns = {"/ServletAuthentification"})
public class ServletAuthentification extends HttpServlet {

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
            
            String userName = request.getParameter("user_name");
            String password = request.getParameter("user_pass");
            REPOSITORYGestionClient daoClient = new REPOSITORYGestionClient();
            Client client =  daoClient.chercherIdentifier(userName);// <-------
            System.out.println(client.getId_Client());

            if (client != null){   
                
                if (password.equals(client.getPassword())){
                    
                    HttpSession maSession = request.getSession(true);
                    Vector<ArticlePanier> listPanier = new Vector<>();
                    
                    maSession.setAttribute("client", client);
                    maSession.setAttribute("listPanier", listPanier);

                    getServletContext().getRequestDispatcher("/HomeUser.jsp").forward(request, response);
                    
                } else {
                // Code erreur Mot de passe incorect
                getServletContext().getRequestDispatcher("/ErrorPage.jsp?error=4").forward(request, response);
                }                   
            } else {
            // Code erreur Utilisateur inexistant
            getServletContext().getRequestDispatcher("/ErrorPage.jsp?error=3").forward(request, response);
            }
            
        } catch (Exception e){
            // Code erreur Databse
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
