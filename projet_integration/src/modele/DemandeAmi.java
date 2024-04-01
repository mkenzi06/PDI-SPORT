/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author kenzi
 */

@Entity
public class DemandeAmi {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "demandeur_id")
    private User demandeur;
    @Enumerated(EnumType.STRING)
    private StatutDemandeAmi.StatutDemandeAm statut;
    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private User destinataire;
    
    /**
     * Définit le demandeur de la demande d'ami.
     * 
     * @param demandeur le demandeur de la demande d'ami
     */
    public void setDemandeur(User demandeur) {
        this.demandeur = demandeur;
        demandeur.getDemandesEnvoyees().add(this);
    }
        
    /**
     * Définit le destinataire de la demande d'ami.
     * 
     * @param destinataire le destinataire de la demande d'ami
     */
    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
        destinataire.getDemandesRecues().add(this);
    }

    /**
     * Récupère l'identifiant de la demande d'ami.
     * 
     * @return l'identifiant de la demande d'ami
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Récupère le statut de la demande d'ami.
     * 
     * @return le statut de la demande d'ami
     */
    public StatutDemandeAmi.StatutDemandeAm getStatut() {
        return statut;
    }
        
    /**
     * Définit le statut de la demande d'ami.
     * 
     * @param l le statut de la demande d'ami
     */
    public void setStatut(StatutDemandeAmi.StatutDemandeAm l){
        this.statut=l;
    }

    /**
     * Définit l'identifiant de la demande d'ami.
     * 
     * @param id l'identifiant de la demande d'ami
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le demandeur de la demande d'ami.
     * 
     * @return le demandeur de la demande d'ami
     */
    public User getDemandeur() {
        return demandeur;
    }

    /**
     * Récupère le destinataire de la demande d'ami.
     * 
     * @return le destinataire de la demande d'ami
     */
    public User getDestinataire() {
        return destinataire;
    }
}
