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
    private double pourcentagePremiersServicesReussis;
    private int nombreMoyenVoleesParMatch;

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
    public double getPourcentagePremiersServicesReussis() {
        return pourcentagePremiersServicesReussis;
    }

    /**
     * @param pourcentagePremiersServicesReussis the
     * pourcentagePremiersServicesReussis to set
     */
    public void setPourcentagePremiersServicesReussis(double pourcentagePremiersServicesReussis) {
        this.pourcentagePremiersServicesReussis = pourcentagePremiersServicesReussis;
    }

    /**
     * @return the nombreMoyenVoleesParMatch
     */
    public int getNombreMoyenVoleesParMatch() {
        return nombreMoyenVoleesParMatch;
    }

    /**
     * @param nombreMoyenVoleesParMatch the nombreMoyenVoleesParMatch to set
     */
    public void setNombreMoyenVoleesParMatch(int nombreMoyenVoleesParMatch) {
        this.nombreMoyenVoleesParMatch = nombreMoyenVoleesParMatch;
    }
}
