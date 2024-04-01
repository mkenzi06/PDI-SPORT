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
 * Cette classe représente le sport de l'haltérophilie.
 * Elle hérite de la classe Sport.
 */
@Entity
public class Halterophilie extends Sport {
    private int poidsLeve;
    private int nombreRepetitions;
    private int nombreSeries;

    // Constructeur, getters and setters

    /**
     * Retourne le poids levé lors de la pratique de l'haltérophilie.
     * @return le poids levé
     */
    public int getPoidsLeve() {
        return poidsLeve;
    }

    /**
     * Définit le poids levé lors de la pratique de l'haltérophilie.
     * @param poidsLeve le poids levé
     */
    public void setPoidsLeve(int poidsLeve) {
        this.poidsLeve = poidsLeve;
    }

    /**
     * Retourne le nombre de répétitions effectuées lors de la pratique de l'haltérophilie.
     * @return le nombre de répétitions
     */
    public int getNombreRepetitions() {
        return nombreRepetitions;
    }

    /**
     * Définit le nombre de répétitions effectuées lors de la pratique de l'haltérophilie.
     * @param nombreRepetitions le nombre de répétitions
     */
    public void setNombreRepetitions(int nombreRepetitions) {
        this.nombreRepetitions = nombreRepetitions;
    }

    /**
     * Retourne le nombre de séries effectuées lors de la pratique de l'haltérophilie.
     * @return le nombre de séries
     */
    public int getNombreSeries() {
        return nombreSeries;
    }

    /**
     * Définit le nombre de séries effectuées lors de la pratique de l'haltérophilie.
     * @param nombreSeries le nombre de séries
     */
    public void setNombreSeries(int nombreSeries) {
        this.nombreSeries = nombreSeries;
    }
    
    @Override
    public String getNom() {
        return "Haltérophilie";
    }

    // Les méthodes suivantes sont héritées de la classe Sport et sont redéfinies ici pour retourner false

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
       return true;
    }

    @Override
    public boolean estNatation() {
        return false;
    }

    @Override
    public boolean estCap() {
        return false;
    }
}

