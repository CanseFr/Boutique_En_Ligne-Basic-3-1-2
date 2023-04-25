/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.entites;

import com.app.repository.REPOSITORYGestionCommande;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julie
 */
public class Commande {
    
    private int id_Commande;
    private String Date_Commande ;
    private double HT;
    private double TTC;
    private double TVA;
    private int Id_Client ;
    // Creer une list de ligne commande pour afficher la la liste de ligne commande dans consultation des commande 
    private ArrayList<LigneCommandeDeCommandeRegister> listLigneCommande ;

    public Commande(int id_Commande, String Date_Commande, double HT, double TTC, double TVA, int Id_Client) {
        this.id_Commande = id_Commande;
        this.Date_Commande = Date_Commande;
        this.HT = HT;
        this.TTC = TTC;
        this.TVA = TVA;
        this.Id_Client = Id_Client;
        this.listLigneCommande = new ArrayList<>();
    }

    public List<LigneCommandeDeCommandeRegister> getListLigneCommande() {
        return listLigneCommande;
    }

    public void setLigneCommande(LigneCommandeDeCommandeRegister listLigneCommande) {
        this.listLigneCommande.add(  listLigneCommande) ;
    }
    
    // ATTENTION CONSTRUCTEUR !!! ID etc .. 

    public Commande() throws SQLException {
        REPOSITORYGestionCommande daoGestionCommande = new REPOSITORYGestionCommande();
        int idActuel = daoGestionCommande.lastIdCommande();
        this.id_Commande = idActuel++;
    }

    public Commande(String Date_Commande, double HT, double TTC, double TVA, int Id_Client) throws SQLException {
        REPOSITORYGestionCommande daoGestionCommande = new REPOSITORYGestionCommande();
        int idActuel = daoGestionCommande.lastIdCommande();
        
        this.id_Commande = idActuel++;
        this.Date_Commande = Date_Commande;
        this.HT = HT;
        this.TTC = TTC;
        this.TVA = TVA;
        this.Id_Client = Id_Client;
    }

    public int getId_Commande() {
        return id_Commande;
    }

    public void setId_Commande(int id_Commande) {
        this.id_Commande = id_Commande;
    }

    public String getDate_Commande() {
        return Date_Commande;
    }

    public void setDate_Commande(String Date_Commande) {
        this.Date_Commande = Date_Commande;
    }

    public double getHT() {
        return HT;
    }

    public void setHT(double HT) {
        this.HT = HT;
    }

    public double getTTC() {
        return TTC;
    }

    public void setTTC(double TTC) {
        this.TTC = TTC;
    }

    public double getTVA() {
        return TVA;
    }

    public void setTVA(double TVA) {
        this.TVA = TVA;
    }

    public int getId_Client() {
        return Id_Client;
    }

    public void setId_Client(int Id_Client) {
        this.Id_Client = Id_Client;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_Commande=" + id_Commande + ", Date_Commande=" + Date_Commande + ", HT=" + HT + ", TTC=" + TTC + ", TVA=" + TVA + ", Id_Client=" + Id_Client + ", listLigneCommande=" + listLigneCommande + '}';
    }


    
    
}
