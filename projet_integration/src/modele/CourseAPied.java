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
public class CourseAPied extends Sport {
    private double distanceParcourue;
    private double tempsPerformance;
    private int nombreSeancesEntrainement;

    // Constructeur, getters and setters

    /**
     * @return the distanceParcourue
     */
    public double getDistanceParcourue() {
        return distanceParcourue;
    }

    /**
     * @param distanceParcourue the distanceParcourue to set
     */
    public void setDistanceParcourue(double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    /**
     * @return the tempsPerformance
     */
    public double getTempsPerformance() {
        return tempsPerformance;
    }

    /**
     * @param tempsPerformance the tempsPerformance to set
     */
    public void setTempsPerformance(double tempsPerformance) {
        this.tempsPerformance = tempsPerformance;
    }

    /**
     * @return the nombreSeancesEntrainement
     */
    public int getNombreSeancesEntrainement() {
        return nombreSeancesEntrainement;
    }

    /**
     * @param nombreSeancesEntrainement the nombreSeancesEntrainement to set
     */
    public void setNombreSeancesEntrainement(int nombreSeancesEntrainement) {
        this.nombreSeancesEntrainement = nombreSeancesEntrainement;
    }
}
