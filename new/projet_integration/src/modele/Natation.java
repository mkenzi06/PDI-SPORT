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
public class Natation extends Sport {
    private double distanceTotaleNagee;
    private double tempsPourNagerDistance;
    private int nombreBrassesParMinute;

    /**
     * @return the distanceTotaleNagee
     */
    public double getDistanceTotaleNagee() {
        return distanceTotaleNagee;
    }

    /**
     * @param distanceTotaleNagee the distanceTotaleNagee to set
     */
    public void setDistanceTotaleNagee(double distanceTotaleNagee) {
        this.distanceTotaleNagee = distanceTotaleNagee;
    }

    /**
     * @return the tempsPourNagerDistance
     */
    public double getTempsPourNagerDistance() {
        return tempsPourNagerDistance;
    }

    /**
     * @param tempsPourNagerDistance the tempsPourNagerDistance to set
     */
    public void setTempsPourNagerDistance(double tempsPourNagerDistance) {
        this.tempsPourNagerDistance = tempsPourNagerDistance;
    }

    /**
     * @return the nombreBrassesParMinute
     */
    public int getNombreBrassesParMinute() {
        return nombreBrassesParMinute;
    }

    /**
     * @param nombreBrassesParMinute the nombreBrassesParMinute to set
     */
    public void setNombreBrassesParMinute(int nombreBrassesParMinute) {
        this.nombreBrassesParMinute = nombreBrassesParMinute;
    }
    @Override
           public String getNom() {
        return "Natation";
    }

    
}
