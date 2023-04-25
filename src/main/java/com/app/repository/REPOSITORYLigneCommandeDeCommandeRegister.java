/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.repository;

import com.app.entites.Client;
import com.app.entites.Commande;
import com.app.entites.LigneCommandeDeCommandeRegister;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author julie
 */
public class REPOSITORYLigneCommandeDeCommandeRegister  {
    
    Connection connection;


    
    public ArrayList<LigneCommandeDeCommandeRegister> listAllLineCommandeFromClt(Client client) throws SQLException {
        ArrayList<LigneCommandeDeCommandeRegister> listLigneCommande = new ArrayList<>();
        connection = MANAGERConnection.getConnection();
        
        String queryCmdClt =    "SELECT ligne_commande.Id_Ligne_Commande,ligne_commande.Quantite_commande,ligne_commande.Prix_total_Ht,ligne_commande.Id_Produit,ligne_commande.Id_Commande \n" +
                                "FROM `ligne_commande` \n" +
                                "INNER JOIN commande\n" +
                                "ON commande.Id_Commande = ligne_commande.Id_Commande\n" +
                                "INNER JOIN client\n" +
                                "ON client.Id_Client = commande.Id_Client\n" +
                                "WHERE commande.Id_Client = "+client.getId_Client()+" ;";
        
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(queryCmdClt);
        
        while (rs.next()) {      
            int idLigneCommande = rs.getInt("Id_Ligne_Commande");
            String qteCommande = rs.getString("Quantite_commande");
            double pHt = rs.getDouble("Prix_total_Ht");
            int idProduit = rs.getInt("Id_Produit");
            int idCommande = rs.getInt("Id_Commande");
            
            LigneCommandeDeCommandeRegister ligneCommande = new LigneCommandeDeCommandeRegister( idLigneCommande,  qteCommande,  pHt,  idProduit,  idCommande);
            listLigneCommande.add(ligneCommande);
        }
        return listLigneCommande;
    }
}