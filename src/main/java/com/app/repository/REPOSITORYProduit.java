/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.repository;

import com.app.Beans.ArticlePanier;
import com.app.entites.Produit;
import com.app.Beans.ArticlePanier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author julie
 */
public class REPOSITORYProduit  {
        Connection connection;

    public REPOSITORYProduit() throws SQLException {
        connection = MANAGERConnection.getConnection();
    }
    
    
    public Vector<Produit> liste() throws SQLException {
        String query = "Select * from teststockeasy.produit";
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        Vector<Produit> v = new Vector<Produit>();

        while (rs.next()) {
            int id = rs.getInt("Id_Produit");
            int prix = rs.getInt("Prix_Ht");
            int quantite = rs.getInt("Quantite");
            String description = rs.getString("Description");

            Produit p = new Produit(id, prix, quantite, description);
           
            v.add(p);
        }
        return v;
    }

    
    public Produit afficherUnProduit(int id_produit) throws SQLException {
        
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM teststockeasy.Produit WHERE Id_Produit=" + id_produit + "");

        result.next();
        int id = result.getInt("Id_Produit");
        int prix = result.getInt("Prix_Ht");
        int quantite = result.getInt("Quantite");
        String description = result.getString("Description");
        Produit produit = new Produit(id, prix, quantite, description);
           
        return produit;
    }

    
    public ArticlePanier getArticlePanier(int id_produit, int quantite) throws SQLException {
                
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM teststockeasy.Produit WHERE Id_Produit=" + id_produit + "");

        result.next();
        int id_p = result.getInt("Id_Produit");
        int prix_p = result.getInt("Prix_Ht");
        int quantite_p = result.getInt("Quantite");
        String description_p = result.getString("Description");
        
        ArticlePanier articlePanier = new ArticlePanier(quantite,id_p, prix_p, quantite_p, description_p);
        
        return articlePanier;
    }
    
}
