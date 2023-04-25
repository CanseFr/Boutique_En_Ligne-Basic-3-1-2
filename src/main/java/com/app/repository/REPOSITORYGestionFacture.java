/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.repository;

import com.app.entites.Client;
import com.app.entites.Commande;
import com.app.entites.Facture;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author julie
 */
public class REPOSITORYGestionFacture  {

    Connection connection;
    
    
    public void creerFacture(Facture f) throws SQLException {
        connection = MANAGERConnection.getConnection();
        Statement s = connection.createStatement();
        String query = "INSERT INTO facture (Date_Facturation, Total_HT, Total_TVA,Total_TTC, Id_Commande) VALUES ('" + f.getDate_Facturation() + "'," + f.getTotal_HT() + "," + f.getTotal_TVA() + "," + f.getTotal_TTC() + "," + f.getId_Commande() + ");";
        s.executeUpdate(query);
    }

    
    public int lastIdFacure() throws SQLException {
        connection = MANAGERConnection.getConnection();
        
        String query = "SELECT MAX(Id_Facture) AS Id_Facture FROM teststockeasy.Facture;";
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        
        rs.next();
        int lastIdFacture = rs.getInt("Id_Facture");
        return lastIdFacture;
    }

    
    public ArrayList<Facture> listerFactureUtilisateur(Client c) throws SQLException {
        ArrayList<Facture> listFacture = new ArrayList<>();

        connection = MANAGERConnection.getConnection();
        String queryFctClt = "SELECT  Id_Facture ,Date_Facturation, Total_HT , Total_TVA ,Total_TTC  , facture.Id_Commande\n" +
                            "FROM facture\n" +
                            "INNER JOIN commande\n" +
                            "ON commande.Id_Commande = facture.Id_Commande\n" +
                            "INNER JOIN client\n" +
                            "ON client.Id_Client = commande.Id_Client\n" +
                            "WHERE client.Id_Client = "+c.getId_Client()+";";

        Statement s = connection.createStatement();

        ResultSet rs = s.executeQuery(queryFctClt);


        while (rs.next()) {   

            int Id_Facture = rs.getInt("Id_Facture");
            String Date_Facturation = rs.getString("Date_Facturation");
            double Total_HT = rs.getDouble("Total_HT");
            double Total_TVA = rs.getDouble("Total_TVA");
            double Total_TTC = rs.getDouble("Total_TTC");
            int Id_Commande = rs.getInt("Id_Commande");
            String typePaiement = "Paiement recus";
            
            Facture facture = new Facture( Id_Facture,  Date_Facturation,  Total_HT,  Total_TVA,  Total_TTC,  Id_Commande,typePaiement);
            listFacture.add(facture);
        }
        return listFacture;
    }
    
}
