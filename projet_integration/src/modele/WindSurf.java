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
/**
 * Cette classe représente un objet WindSurf, qui est une sous-classe de Sport.
 * 
 * Elle contient des attributs spécifiques au windsurf, tels que la distance parcourue,
 * le temps de performance et le nombre de séances d'entraînement.
 * 
 * Cette classe fournit des méthodes pour accéder et modifier ces attributs, ainsi que
 * des méthodes pour vérifier le type de sport.
 */
@Entity
public class WindSurf extends Sport {
    private double distanceParcourue;
    private double tempsPerformance;
    private int nombreSeancesEntrainement;

    // Getters and setters

    /**
     * Renvoie la distance parcourue en windsurf.
     * 
     * @return La distance parcourue en windsurf.
     */
    public double getDistanceParcourue() {
        return distanceParcourue;
    }

    /**
     * Modifie la distance parcourue en windsurf.
     * 
     * @param distanceParcourue La nouvelle distance parcourue en windsurf.
     */
    public void setDistanceParcourue(double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    /**
     * Renvoie le temps de performance en windsurf.
     * 
     * @return Le temps de performance en windsurf.
     */
    public double getTempsPerformance() {
        return tempsPerformance;
    }

    /**
     * Modifie le temps de performance en windsurf.
     * 
     * @param tempsPerformance Le nouveau temps de performance en windsurf.
     */
    public void setTempsPerformance(double tempsPerformance) {
        this.tempsPerformance = tempsPerformance;
    }

    /**
     * Renvoie le nombre de séances d'entraînement en windsurf.
     * 
     * @return Le nombre de séances d'entraînement en windsurf.
     */
    public int getNombreSeancesEntrainement() {
        return nombreSeancesEntrainement;
    }

    /**
     * Modifie le nombre de séances d'entraînement en windsurf.
     * 
     * @param nombreSeancesEntrainement Le nouveau nombre de séances d'entraînement en windsurf.
     */
    public void setNombreSeancesEntrainement(int nombreSeancesEntrainement) {
        this.nombreSeancesEntrainement = nombreSeancesEntrainement;
    }
    

    /**
     * Renvoie le nom du sport.
     * 
     * @return Le nom du sport.
     */
    @Override
    public String getNom() {
        return "Windsurf";
    }

    // Méthodes de vérification du type de sport

    /**
     * Vérifie si le sport est du cyclisme.
     * 
     * @return true si le sport est du cyclisme, false sinon.
     */
    @Override
    public boolean estCyclisme() {
        return false;
    }

    /**
     * Vérifie si le sport est du tennis.
     * 
     * @return true si le sport est du tennis, false sinon.
     */
    @Override
    public boolean estTennis() {
        return false;
    }

    /**
     * Vérifie si le sport est du windsurf.
     * 
     * @return true si le sport est du windsurf, false sinon.
     */
    @Override
    public boolean estWindsurf() {
       return true;
    }

    /**
     * Vérifie si le sport est de l'haltérophilie.
     * 
     * @return true si le sport est de l'haltérophilie, false sinon.
     */
    @Override
    public boolean estHalterophilie() {
       return false;
    }

    /**
     * Vérifie si le sport est de la natation.
     * 
     * @return true si le sport est de la natation, false sinon.
     */
    @Override
    public boolean estNatation() {
        return false;
    }

    /**
     * Vérifie si le sport est de la course à pied.
     * 
     * @return true si le sport est de la course à pied, false sinon.
     */
    @Override
    public boolean estCap() {
        return false;
    }
}
