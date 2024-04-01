/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author HP
 */
/**
 * Cette classe abstraite représente un sport.
 */
@Entity
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @ManyToMany(mappedBy = "sportsPratiques")
    private List<User> users = new ArrayList<>();

    // Getters et setters pour `users`
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Renvoie l'identifiant du sport.
     * 
     * @return l'identifiant du sport
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du sport.
     * 
     * @param id l'identifiant du sport
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoie le nom du sport.
     * 
     * @return le nom du sport
     */
    public abstract String getNom();
//

    /**
     * Vérifie si le sport est du cyclisme.
     * 
     * @return true si le sport est du cyclisme, sinon false
     */
    public abstract boolean estCyclisme();

    /**
     * Vérifie si le sport est du tennis.
     * 
     * @return true si le sport est du tennis, sinon false
     */
    public abstract boolean estTennis();

    /**
     * Vérifie si le sport est du windsurf.
     * 
     * @return true si le sport est du windsurf, sinon false
     */
    public abstract boolean estWindsurf();
//

    /**
     * Vérifie si le sport est de l'haltérophilie.
     * 
     * @return true si le sport est de l'haltérophilie, sinon false
     */
    public abstract boolean estHalterophilie();

    /**
     * Vérifie si le sport est de la natation.
     * 
     * @return true si le sport est de la natation, sinon false
     */
    public abstract boolean estNatation();

    /**
     * Vérifie si le sport est de la course à pied.
     * 
     * @return true si le sport est de la course à pied, sinon false
     */
    public abstract boolean estCap();

    /**
     * Définit le nom du sport.
     * 
     * @param nom le nom du sport
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
