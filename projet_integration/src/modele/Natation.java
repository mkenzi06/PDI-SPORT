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
      
    private double crawlTimePercentage;

    
    private double papillonTimePercentage;

    @Column(name = "total_seance_time")
    private int totalSessionTime; 
    


    @Override
           public String getNom() {
        return "Natation";
    }

    /**
     * @return the crawlTimePercentage
     */
    public double getCrawlTimePercentage() {
        return crawlTimePercentage;
    }

    /**
     * @param crawlTimePercentage the crawlTimePercentage to set
     */
    public void setCrawlTimePercentage(double crawlTimePercentage) {
        this.crawlTimePercentage = crawlTimePercentage;
    }

    /**
     * @return the papillonTimePercentage
     */
    public double getPapillonTimePercentage() {
        return papillonTimePercentage;
    }

    /**
     * @param papillonTimePercentage the papillonTimePercentage to set
     */
    public void setPapillonTimePercentage(double papillonTimePercentage) {
        this.papillonTimePercentage = papillonTimePercentage;
    }

    /**
     * @return the totalSessionTime
     */
    public int getTotalSessionTime() {
        return totalSessionTime;
    }

    /**
     * @param totalSessionTime the totalSessionTime to set
     */
    public void setTotalSessionTime(int totalSessionTime) {
        this.totalSessionTime = totalSessionTime;
    }

    
}
