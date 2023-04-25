/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.entites;

import com.app.repository.REPOSITORYGestionFacture;
import java.sql.SQLException;

/**
 *
 * @author julie
 */
public class Facture {
    
    private int Id_Facture ;
    private String Date_Facturation ;
    private double Total_HT ;
    private double Total_TVA ;
    private double Total_TTC ;
    private int Id_Commande ;
    private String typePaiement ;

    public Facture() throws SQLException {
        REPOSITORYGestionFacture daoGestionfacture = new REPOSITORYGestionFacture() {};
        int idActuel = daoGestionfacture.lastIdFacure();
        this.Id_Facture = idActuel++;
    }

    public Facture(int Id_Facture, String Date_Facturation, double Total_HT, double Total_TVA, double Total_TTC, int Id_Commande,String typePaiement) throws SQLException {
        REPOSITORYGestionFacture daoGestionfacture = new REPOSITORYGestionFacture();
        int idActuel = daoGestionfacture.lastIdFacure();
        
        this.Id_Facture = idActuel++;
        this.Date_Facturation = Date_Facturation;
        this.Total_HT = Total_HT;
        this.Total_TVA = Total_TVA;
        this.Total_TTC = Total_TTC;
        this.Id_Commande = Id_Commande;
        this.typePaiement = typePaiement;
    }

    public int getId_Facture() {
        return Id_Facture;
    }

    public void setId_Facture(int Id_Facture) {
        this.Id_Facture = Id_Facture;
    }

    public String getDate_Facturation() {
        return Date_Facturation;
    }

    public void setDate_Facturation(String Date_Facturation) {
        this.Date_Facturation = Date_Facturation;
    }

    public double getTotal_HT() {
        return Total_HT;
    }

    public void setTotal_HT(double Total_HT) {
        this.Total_HT = Total_HT;
    }

    public double getTotal_TVA() {
        return Total_TVA;
    }

    public void setTotal_TVA(double Total_TVA) {
        this.Total_TVA = Total_TVA;
    }


    public int getId_Commande() {
        return Id_Commande;
    }

    public void setId_Commande(int Id_Commande) {
        this.Id_Commande = Id_Commande;
    }

    public double getTotal_TTC() {
        return Total_TTC;
    }

    public void setTotal_TTC(double Total_TTC) {
        this.Total_TTC = Total_TTC;
    }

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    @Override
    public String toString() {
        return "Facture{" + "Id_Facture=" + Id_Facture + ", Date_Facturation=" + Date_Facturation + ", Total_HT=" + Total_HT + ", Total_TVA=" + Total_TVA + ", Total_TTC=" + Total_TTC + ", Id_Commande=" + Id_Commande + ", typePaiement=" + typePaiement + '}';
    }
    
    
    
}
