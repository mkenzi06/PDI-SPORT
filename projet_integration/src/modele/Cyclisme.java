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
public class Cyclisme extends Sport {
    private double distanceTotaleParcourue;
    private double tempsPerformance;

    // Constructeur, getters and setters

    /**
     * @return the distanceTotaleParcourue
     */
    public double getDistanceTotaleParcourue() {
        return distanceTotaleParcourue;
    }

    /**
     * @param distanceTotaleParcourue the distanceTotaleParcourue to set
     */
    public void setDistanceTotaleParcourue(double distanceTotaleParcourue) {
        this.distanceTotaleParcourue = distanceTotaleParcourue;
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
}