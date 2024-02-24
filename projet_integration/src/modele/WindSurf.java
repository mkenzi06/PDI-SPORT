/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Entity;

/**
 *
 * @author HP
 */
@Entity
public class WindSurf extends Sport {
    private double distanceParcourue;
    private double tempsPerformance;
    private int nombreSeancesEntrainement;

    // Constructeur, getters and setters

    public double getDistanceParcourue() {
        return distanceParcourue;
    }

    public void setDistanceParcourue(double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    public double getTempsPerformance() {
        return tempsPerformance;
    }

    public void setTempsPerformance(double tempsPerformance) {
        this.tempsPerformance = tempsPerformance;
    }

    public int getNombreSeancesEntrainement() {
        return nombreSeancesEntrainement;
    }

    public void setNombreSeancesEntrainement(int nombreSeancesEntrainement) {
        this.nombreSeancesEntrainement = nombreSeancesEntrainement;
    }
    
    @Override
    public String getNom() {
        return "Windsurf";
    }
}
