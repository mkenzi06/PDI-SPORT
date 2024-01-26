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
}