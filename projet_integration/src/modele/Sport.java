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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public abstract String getNom();
//

    public abstract boolean estCyclisme();

    public abstract boolean estTennis();

    public abstract boolean estWindsurf();
//

    public abstract boolean estHalterophilie();

    public abstract boolean estNatation();

    public abstract boolean estCap();

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the performances
     */
}
