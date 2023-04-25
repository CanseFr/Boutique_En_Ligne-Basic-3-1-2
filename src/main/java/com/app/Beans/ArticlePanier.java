/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.Beans;

import com.app.entites.Produit;

/**
 *
 * @author julie
 */
public class ArticlePanier extends Produit{
    
    private int quantiteCommande;

    public ArticlePanier(int quantiteCommande, int id_produit, int prix, int quantite, String description) {
        super(id_produit, prix, quantite, description);
        this.quantiteCommande = quantiteCommande;
    }

    public ArticlePanier() {
    }

    public int getQuantiteCommande() {
        return quantiteCommande;
    }

    public void setQuantiteCommande(int quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArticlePanier ){
            if (this.getId_produit() == ((ArticlePanier) obj).getId_produit()){
                return true;
            }
        }
        
        return false;
    }

    @Override
    public String toString() {
        return super.toString()+ " Quantité commande " + quantiteCommande + ""; 
        
    } 
}
