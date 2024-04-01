/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author lounis
 */
/**
 * Cette classe représente les différents statuts d'une demande d'ami.
 */
public class StatutDemandeAmi {
    /**
     * Enumération des statuts d'une demande d'ami.
     * Les statuts possibles sont:
     * - EN_ATTENTE: la demande est en attente de réponse.
     * - ACCEPTEE: la demande a été acceptée.
     * - REFUSEE: la demande a été refusée.
     * Vous pouvez ajouter d'autres statuts si nécessaire.
     */
    public enum StatutDemandeAm {
     EN_ATTENTE,
     ACCEPTEE,
     REFUSEE,
     // Ajoutez d'autres statuts si nécessaire
    }
}
