/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.entites;

/**
 *
 * @author julie
 */
public class LigneCommandeDeCommandeRegister {
    private int Id_Ligne_Commande ;
    private String Quantite_commande ;
    private double Prix_total_Ht;
    private int Id_Produit ;
    private int Id_Commande;

    public LigneCommandeDeCommandeRegister(int Id_Ligne_Commande, String Quantite_commande, double Prix_total_Ht, int Id_Produit, int Id_Commande) {
        this.Id_Ligne_Commande = Id_Ligne_Commande;
        this.Quantite_commande = Quantite_commande;
        this.Prix_total_Ht = Prix_total_Ht;
        this.Id_Produit = Id_Produit;
        this.Id_Commande = Id_Commande;
    }

    public int getId_Ligne_Commande() {
        return Id_Ligne_Commande;
    }

    public void setId_Ligne_Commande(int Id_Ligne_Commande) {
        this.Id_Ligne_Commande = Id_Ligne_Commande;
    }

    public String getQuantite_commande() {
        return Quantite_commande;
    }

    public void setQuantite_commande(String Quantite_commande) {
        this.Quantite_commande = Quantite_commande;
    }

    public double getPrix_total_Ht() {
        return Prix_total_Ht;
    }

    public void setPrix_total_Ht(double Prix_total_Ht) {
        this.Prix_total_Ht = Prix_total_Ht;
    }

    public int getId_Produit() {
        return Id_Produit;
    }

    public void setId_Produit(int Id_Produit) {
        this.Id_Produit = Id_Produit;
    }

    public int getId_Commande() {
        return Id_Commande;
    }

    public void setId_Commande(int Id_Commande) {
        this.Id_Commande = Id_Commande;
    }

    @Override
    public String toString() {
        return "ID Produit -> " + Id_Produit + " , Description -> Fair requete !  , Quantité -> " + Quantite_commande + " ,Prix HT -> " + Prix_total_Ht +" €" + ", Id Cmd ->" +Id_Commande ;
    }

}
