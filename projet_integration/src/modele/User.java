/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.*;
import javax.swing.JOptionPane;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HP
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String pseudo;
    private String password;
    @Lob
    private byte[] photoProfil;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    //constructeur par defaut
    public User() {

    }

    //constructeur prevu pour l'inscription (normalement)
    public User(String nom, String prenom, String pseudo, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.password = password;

        this.sportsPratiques = sportsPratiques;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_sport",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )

    private List<Sport> sportsPratiques = new ArrayList<>();
    @OneToMany(mappedBy = "destinataire", cascade = CascadeType.ALL)
    @JoinColumn(name = "destinataire_id")
    private List<DemandeAmi> demandesRecues = new ArrayList<>();

    @OneToMany(mappedBy = "demandeur", cascade = CascadeType.ALL)
    @JoinColumn(name = "demandeur_id")
    private List<DemandeAmi> demandesEnvoyees = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Performances> performances = new ArrayList<>();


    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the photoProfil
     */
    public byte[] getPhotoProfil() {
        return photoProfil;
    }

    /**
     * @param photoProfil the photoProfil to set
     */
    public void setPhotoProfil(byte[] photoProfil) {
        this.photoProfil = photoProfil;
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
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the dateNaissance
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return the sportsPratiques
     */
    public List<Sport> getSportsPratiques() {
        return sportsPratiques;
    }

    /**
     * @param sportsPratiques the sportsPratiques to set
     */
    public void setSportsPratiques(List<Sport> sportsPratiques) {
        this.sportsPratiques = sportsPratiques;
    }

    /**
     * @return the demandesRecues
     */
    public List<DemandeAmi> getDemandesRecues() {
        return demandesRecues;
    }

    /**
     * @param demandesRecues the demandesRecues to set
     */
    public void setDemandesRecues(List<DemandeAmi> demandesRecues) {
        this.demandesRecues = demandesRecues;
    }

    /**
     * @return the demandesEnvoyees
     */
    public List<DemandeAmi> getDemandesEnvoyees() {
        return demandesEnvoyees;
    }

    /**
     * @param demandesEnvoyees the demandesEnvoyees to set
     */
    public void setDemandesEnvoyees(List<DemandeAmi> demandesEnvoyees) {
        this.demandesEnvoyees = demandesEnvoyees;
    }



    /**
     * @return the performances
     */
    public List<Performances> getPerformances() {
        return performances;
    }

    /**
     * @param performances the performances to set
     */
    public void setPerformances(List<Performances> performances) {
        this.performances = performances;
    }

    public static User getUserByPseudo(String pseudo) {
        User user = null;
        Session session = DBConnection.getSession();
        
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("pseudo", pseudo));
        user = (User) criteria.uniqueResult();

        return user;
    }
}
