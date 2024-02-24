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

    @ManyToMany
    @JoinTable(
            name = "user_sport",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )

    private List<Sport> sportsPratiques = new ArrayList<>();
    @OneToMany(mappedBy = "destinataire", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "destinataire_id")
    private List<DemandeAmi> demandesRecues = new ArrayList<>();

    @OneToMany(mappedBy = "demandeur", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "demandeur_id")
    private List<DemandeAmi> demandesEnvoyees = new ArrayList<>();

    public void envoyerDemandeAmi(User destinataire) {
        DemandeAmi demande = new DemandeAmi();
        demande.setDemandeur(this);
        demande.setDestinataire(destinataire);
        getDemandesEnvoyees().add(demande);
        destinataire.getDemandesRecues().add(demande);
    }

    public void accepterDemandeAmi(DemandeAmi demande) {
        if (demandesRecues.contains(demande)) {
            int choix = JOptionPane.showConfirmDialog(
                    null,
                    "Accepter la demande d'ami de " + demande.getDemandeur().getPseudo() + "?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (choix == JOptionPane.YES_OPTION) {
                // Ajoutez ici la logique pour accepter la demande d'ami
                // Par exemple, créer une relation d'amitié, etc.

                // Supprimer la demande des listes
                demandesRecues.remove(demande);
                demande.getDemandeur().getDemandesEnvoyees().remove(demande);
                JOptionPane.showMessageDialog(null, "Demande d'ami acceptée !");
            } else {
                // Refuser la demande (éventuellement ajouter une logique spécifique)
                demandesRecues.remove(demande);
                demande.getDemandeur().getDemandesEnvoyees().remove(demande);
                JOptionPane.showMessageDialog(null, "Demande d'ami refusée !");
            }
        }
    }

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<DataSport> dataSports = new ArrayList<>();
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

    public List<User> getAmis() {
        List<User> amis = new ArrayList<>();

        // Parcourir les demandes acceptées
        for (DemandeAmi demande : demandesRecues) {
            if (demande.getStatut() == StatutDemandeAmi.StatutDemandeAm.ACCEPTEE) {
                amis.add(demande.getDemandeur());
            }
        }

        for (DemandeAmi demande : demandesEnvoyees) {
            if (demande.getStatut() == StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE) {
                amis.add(demande.getDestinataire());
            }
        }

        return amis;
    }
}
