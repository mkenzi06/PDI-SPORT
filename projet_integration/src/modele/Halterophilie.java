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
public class Halterophilie extends Sport {
    private int poidsLeve;
    private int nombreRepetitions;
    private int nombreSeries;

    // Constructeur, getters and setters

    public int getPoidsLeve() {
        return poidsLeve;
    }

    public void setPoidsLeve(int poidsLeve) {
        this.poidsLeve = poidsLeve;
    }

    public int getNombreRepetitions() {
        return nombreRepetitions;
    }

    public void setNombreRepetitions(int nombreRepetitions) {
        this.nombreRepetitions = nombreRepetitions;
    }

    public int getNombreSeries() {
        return nombreSeries;
    }

    public void setNombreSeries(int nombreSeries) {
        this.nombreSeries = nombreSeries;
    }
    
    @Override
    public String getNom() {
        return "Haltérophilie";
    }
}

