/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.*;

/**
 *
 * @author HP
 */
@Entity
public class Tennis extends Sport {

    private int nombreSetsGagnes;
    private int nombresetsmatch;


    // Constructeur, getters and setters
    /**
     * @return the nombreSetsGagnes
     */
    @Override
    public String getNom() {
        return "Tennis";
    }

    public int getNombreSetsGagnes() {
        return nombreSetsGagnes;
    }

    /**
     * @param nombreSetsGagnes the nombreSetsGagnes to set
     */
    public void setNombreSetsGagnes(int nombreSetsGagnes) {
        this.nombreSetsGagnes = nombreSetsGagnes;
    }

    /**
     * @return the pourcentagePremiersServicesReussis
     */
   

    /**
     * @param pourcentagePremiersServicesReussis the
     * pourcentagePremiersServicesReussis to set
     */
  

    /**
     * @return the nombreMoyenVoleesParMatch
     */
  

    /**
     * @param nombreMoyenVoleesParMatch the nombreMoyenVoleesParMatch to set
     */
   
        @Override
    public boolean estCyclisme() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estTennis() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estWindsurf() {
       return false;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estHalterophilie() {
       return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estNatation() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estCap() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the nombresetsmatch
     */
    public int getNombresetsmatch() {
        return nombresetsmatch;
    }

    /**
     * @param nombresetsmatch the nombresetsmatch to set
     */
    public void setNombresetsmatch(int nombresetsmatch) {
        this.nombresetsmatch = nombresetsmatch;
    }
}
