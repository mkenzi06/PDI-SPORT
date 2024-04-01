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
 * Cette classe représente le sport de la natation.
 * Elle hérite de la classe Sport.
 */
@Entity
public class Natation extends Sport {
      
    private double crawlTimePercentage;
    
    private double papillonTimePercentage;

    @Column(name = "total_seance_time")
    private int totalSessionTime; 

    /**
     * Retourne le nom du sport.
     * @return le nom du sport
     */
    @Override
    public String getNom() {
        return "Natation";
    }

    /**
     * Retourne le pourcentage de temps consacré au crawl.
     * @return le pourcentage de temps consacré au crawl
     */
    public double getCrawlTimePercentage() {
        return crawlTimePercentage;
    }

    /**
     * Définit le pourcentage de temps consacré au crawl.
     * @param crawlTimePercentage le pourcentage de temps consacré au crawl
     */
    public void setCrawlTimePercentage(double crawlTimePercentage) {
        this.crawlTimePercentage = crawlTimePercentage;
    }

    /**
     * Retourne le pourcentage de temps consacré au papillon.
     * @return le pourcentage de temps consacré au papillon
     */
    public double getPapillonTimePercentage() {
        return papillonTimePercentage;
    }

    /**
     * Définit le pourcentage de temps consacré au papillon.
     * @param papillonTimePercentage le pourcentage de temps consacré au papillon
     */
    public void setPapillonTimePercentage(double papillonTimePercentage) {
        this.papillonTimePercentage = papillonTimePercentage;
    }

    /**
     * Retourne la durée totale de la séance.
     * @return la durée totale de la séance
     */
    public int getTotalSessionTime() {
        return totalSessionTime;
    }

    /**
     * Définit la durée totale de la séance.
     * @param totalSessionTime la durée totale de la séance
     */
    public void setTotalSessionTime(int totalSessionTime) {
        this.totalSessionTime = totalSessionTime;
    }

    /**
     * Vérifie si le sport est du cyclisme.
     * @return true si le sport est du cyclisme, sinon false
     */
    @Override
    public boolean estCyclisme() {
        return false;
    }

    /**
     * Vérifie si le sport est du tennis.
     * @return true si le sport est du tennis, sinon false
     */
    @Override
    public boolean estTennis() {
        return false;
    }

    /**
     * Vérifie si le sport est du windsurf.
     * @return true si le sport est du windsurf, sinon false
     */
    @Override
    public boolean estWindsurf() {
       return false;
    }

    /**
     * Vérifie si le sport est de l'haltérophilie.
     * @return true si le sport est de l'haltérophilie, sinon false
     */
    @Override
    public boolean estHalterophilie() {
       return false;
    }

    /**
     * Vérifie si le sport est de la natation.
     * @return true si le sport est de la natation, sinon false
     */
    @Override
    public boolean estNatation() {
        return true;
    }

    /**
     * Vérifie si le sport est de la course à pied.
     * @return true si le sport est de la course à pied, sinon false
     */
    @Override
    public boolean estCap() {
        return false;
    }
    
}
