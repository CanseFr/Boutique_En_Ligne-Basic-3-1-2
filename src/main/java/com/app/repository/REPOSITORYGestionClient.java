/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.repository;

import com.app.entites.Client;
import com.app.entites.Produit;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author julie
 */
public class REPOSITORYGestionClient  {

    Connection connection;
    
    public Client chercherIdentifier(String user) throws SQLException {
        connection = MANAGERConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM teststockeasy.Client WHERE User_Name='"+ user +"'");

        int id = 0 ;
        String userName = null;
        String pass = null;
        String mail = null;
        String adresse = null;
        String cp = null;
        String ville = null;

        while(result.next()){
            id = result.getInt("Id_Client");
            userName = result.getString("User_Name");
            pass = result.getString("Password");
            mail = result.getString("Mail");
            adresse = result.getString("Adresse");
            cp = result.getString("CP");
            ville = result.getString("Ville");
        }

        Client client = new Client(id,userName, pass, mail,adresse, cp,ville);
        return client;
    }
    
    
    public void registerUser(Client newClient) throws SQLException {
        connection = MANAGERConnection.getConnection();

        String queryInsertChannel = "INSERT INTO `Client` (`User_Name` , `Password`, `Mail`, `Adresse`, `CP`, `Ville`) VALUES(?,?,?,?,?,?)";

        PreparedStatement preparedStatementChannel = connection.prepareStatement(queryInsertChannel);
        preparedStatementChannel.setString(1, newClient.getUser_Name());
        preparedStatementChannel.setString(2, newClient.getPassword());
        preparedStatementChannel.setString(3, newClient.getMail());
        preparedStatementChannel.setString(4, newClient.getAdresse());
        preparedStatementChannel.setString(5, newClient.getCP());
        preparedStatementChannel.setString(6, newClient.getVille());

        preparedStatementChannel.executeUpdate();

    }
}

 
