/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.repository;

import com.app.entites.Client;
import com.app.entites.Commande;
import com.app.entites.LigneCommande;
import com.app.entites.LigneCommandeDeCommandeRegister;
import com.app.entites.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author julie
 */
public class REPOSITORYGestionCommande  {
    
    Connection connection;

    
    public void creerCommandeAndLigneCommande(Commande cmd, List<LigneCommande> LignCmd) throws SQLException {
        connection = MANAGERConnection.getConnection();
        
        String insertCommande = "INSERT INTO commande (Date_Commande,Ht, TTC, TVA,Id_Client) VALUES(?,?,?,?,?)";  

        try {  
            connection.setAutoCommit(false);  

            // Commande     
            PreparedStatement preparedStatementCommande = connection.prepareStatement(insertCommande);  
            preparedStatementCommande.setString(1, cmd.getDate_Commande());  
            preparedStatementCommande.setDouble(2, cmd.getHT());  
            preparedStatementCommande.setDouble(3, cmd.getTTC());  
            preparedStatementCommande.setDouble(4, cmd.getTVA());  
            preparedStatementCommande.setInt(5, cmd.getId_Client());  
            
            // Update
            preparedStatementCommande.executeUpdate(); 
            
            connection.commit();  

        } catch (Exception e) {     
            e.printStackTrace();  
            connection.rollback();   
        }
        // Repo Ligne commande
        for (int i = 0; i<LignCmd.size(); i++){
            connection = MANAGERConnection.getConnection();
            String query = "INSERT INTO ligne_Commande (Quantite_commande,Prix_total_Ht, Id_Produit, Id_Commande) VALUES("+ LignCmd.get(i).getQuantite_commande()+","+LignCmd.get(i).getPrix_total_Ht()+","+ LignCmd.get(i).getId_Produit()+","+cmd.getId_Commande()+");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
    }
    
    
    public int lastIdCommande() throws SQLException {
        connection = MANAGERConnection.getConnection();
        
        String query = "SELECT MAX(Id_Commande) AS Id_Commande FROM teststockeasy.Commande;";
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        
        rs.next();
        int lastIdCommande = rs.getInt("Id_Commande");
        return lastIdCommande;
    }

    
    public ArrayList<Commande> listerCommandeUtilisateur(Client c) throws SQLException {
        ArrayList<Commande> listCommande = new ArrayList<>();
        
        connection = MANAGERConnection.getConnection();
        String queryCmdClt = "SELECT * FROM Commande WHERE Id_Client="+ c.getId_Client() +" ;";
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(queryCmdClt);
        
        
        while (rs.next()) { 
            int id_commande = rs.getInt("Id_Commande");
            String Date_Commande = rs.getString("Date_Commande");
            double HT = rs.getDouble("HT");
            double TTC = rs.getDouble("TTC");
            double TVA = rs.getDouble("TVA");
            int Id_Client = rs.getInt("Id_Client");
            Commande commande = new Commande(id_commande, Date_Commande,  HT,  TTC,  TVA,  Id_Client);
            listCommande.add(commande);
        }
        return listCommande;
    }
}

