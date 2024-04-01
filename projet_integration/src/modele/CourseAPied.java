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
 * Cette classe représente un sport de course à pied.
 * Elle hérite de la classe Sport.
 */

@Entity
public class CourseAPied extends Sport {
    private double distanceParcourue;
    private double tempsPerformance;
    private int nombreSeancesEntrainement;

    // Constructeur, getters and setters

    /**
     * Cette classe représente un sport de course à pied.
     * Elle hérite de la classe Sport.
     */
    
    /**
     * Retourne la distance parcourue lors de la course à pied.
     *
     * @return la distance parcourue
     */
    public double getDistanceParcourue() {
        return distanceParcourue;
    }

    /**
     * Définit la distance parcourue lors de la course à pied.
     *
     * @param distanceParcourue la distance parcourue
     */
    public void setDistanceParcourue(double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    /**
     * Retourne le temps de performance lors de la course à pied.
     *
     * @return le temps de performance
     */
    public double getTempsPerformance() {
        return tempsPerformance;
    }

    /**
     * Définit le temps de performance lors de la course à pied.
     *
     * @param tempsPerformance le temps de performance
     */
    public void setTempsPerformance(double tempsPerformance) {
        this.tempsPerformance = tempsPerformance;
    }

    /**
     * Retourne le nombre de séances d'entraînement effectuées pour la course à pied.
     *
     * @return le nombre de séances d'entraînement
     */
    public int getNombreSeancesEntrainement() {
        return nombreSeancesEntrainement;
    }

    /**
     * Définit le nombre de séances d'entraînement effectuées pour la course à pied.
     *
     * @param nombreSeancesEntrainement le nombre de séances d'entraînement
     */
    public void setNombreSeancesEntrainement(int nombreSeancesEntrainement) {
        this.nombreSeancesEntrainement = nombreSeancesEntrainement;
    }
    
    /**
     * Cette méthode retourne le nom du sport.
     *
     * @return le nom du sport
     */
    @Override
    public String getNom() {
        return "Course a pied";
    }

    @Override
    public boolean estCyclisme() {
        return false;
    }

    @Override
    public boolean estTennis() {
        return false;
    }

    @Override
    public boolean estWindsurf() {
       return false;
    }

    @Override
    public boolean estHalterophilie() {
       return false;
    }

    @Override
    public boolean estNatation() {
        return false;
    }

    @Override
    public boolean estCap() {
        return true;
    }
}
