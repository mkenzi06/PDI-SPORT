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
 * @author HP
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
    
        public void setDemandeur(User demandeur) {
        this.demandeur = demandeur;
        demandeur.getDemandesEnvoyees().add(this);
    }
        
        public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
        destinataire.getDemandesRecues().add(this);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
        public StatutDemandeAmi.StatutDemandeAm getStatut() {
        return statut;
    }
        
        public void setStatut(StatutDemandeAmi.StatutDemandeAm l){
            this.statut=l;
        }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the demandeur
     */
    public User getDemandeur() {
        return demandeur;
    }

    /**
     * @return the destinataire
     */
    public User getDestinataire() {
        return destinataire;
    }

    
}
