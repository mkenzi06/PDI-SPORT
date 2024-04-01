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
/**
 * Cette classe représente un objet Tennis qui hérite de la classe Sport.
 * 
 * Elle contient les informations spécifiques au tennis, telles que le nombre de sets gagnés et le nombre de sets par match.
 */
@Entity
public class Tennis extends Sport {

    private int nombreSetsGagnes;
    private int nombresetsmatch;

   
    @Override
    public String getNom() {
        return "Tennis";
    }

 // Getters and setters
    /**
     * @return the nombreSetsGagnes
     */
    public int getNombreSetsGagnes() {
        return nombreSetsGagnes;
    }

    /**
     * @param nombreSetsGagnes the nombreSetsGagnes to set
     */
    public void setNombreSetsGagnes(int nombreSetsGagnes) {
        this.nombreSetsGagnes = nombreSetsGagnes;
    }
  
   
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

    public void setNombresetsmatch(int nombresetsmatch) {
        this.nombresetsmatch = nombresetsmatch;
    }
}
