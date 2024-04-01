/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.*;
import modele.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HP
 */
/**
 * Cette classe représente la fenêtre d'ajout d'amis.
 * Elle affiche une liste d'utilisateurs ayant des intérêts sportifs similaires à l'utilisateur connecté.
 * L'utilisateur peut sélectionner un utilisateur de la liste pour l'ajouter en tant qu'ami.
 */
public class AjoutAmi extends javax.swing.JFrame {

    User u;

    private AjoutAmi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Récupère la liste des identifiants des utilisateurs ayant une demande d'ami en attente pour un utilisateur donné.
     *
     * @param utilisateur L'utilisateur pour lequel on souhaite récupérer les identifiants des utilisateurs ayant une demande d'ami en attente.
     * @return La liste des identifiants des utilisateurs ayant une demande d'ami en attente pour l'utilisateur donné.
     */
    private List<Long> getUtilisateurIdsAvecDemandeAmiEnAttente(User utilisateur) {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(DemandeAmi.class)
                .createAlias("destinataire", "d") // Créer une jointure avec la classe User et l'alias "d"
                .add(Restrictions.eq("d.demandeur", utilisateur)) // Accéder à la propriété demandeur via l'alias "d"
                .add(Restrictions.eq("statut", StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE))
                .setProjection(Projections.property("d.destinataire.id")); // Accéder à l'ID du destinataire via l'alias "d"
        return criteria.list();
    }

// Méthode pour récupérer les IDs des utilisateurs avec demande d'ami acceptée
    /**
     * Cette méthode retourne une liste d'identifiants d'utilisateurs ayant une demande d'ami acceptée.
     * 
     * @param utilisateur L'utilisateur pour lequel on souhaite obtenir les identifiants des utilisateurs ayant accepté sa demande d'ami.
     * @return Une liste d'identifiants d'utilisateurs ayant accepté la demande d'ami de l'utilisateur spécifié.
     */
    private List<Long> getUtilisateurIdsAvecDemandeAmiAcceptee(User utilisateur) {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(DemandeAmi.class)
                .createAlias("destinataire", "d") // Créer une jointure avec la classe User et l'alias "d"
                .add(Restrictions.eq("d.demandeur", utilisateur)) // Accéder à la propriété demandeur via l'alias "d"
                .add(Restrictions.eq("statut", StatutDemandeAmi.StatutDemandeAm.ACCEPTEE))
                .setProjection(Projections.property("d.destinataire.id")); // Accéder à l'ID du destinataire via l'alias "d"
        return criteria.list();
    }

    /**
     * Cette méthode retourne une liste d'identifiants d'utilisateurs ayant une demande d'ami en attente
     * pour un utilisateur donné.
     *
     * @param utilisateur L'utilisateur pour lequel on souhaite récupérer les identifiants des utilisateurs avec une demande d'ami en attente.
     * @return Une liste d'identifiants d'utilisateurs ayant une demande d'ami en attente.
     */
    private List<Long> getUtilisateurIdsAvecDemandeAmiEnAttente1(User utilisateur) {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT da.destinataire.id "
                + "FROM DemandeAmi da "
                + "WHERE da.demandeur = :utilisateur "
                + "AND da.statut = :statut";

        List<Long> ids = session.createQuery(hql)
                .setParameter("utilisateur", utilisateur)
                .setParameter("statut", StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE)
                .list();

        transaction.commit();
        session.close();

        return ids;
    }

    /**
     * Affiche les utilisateurs qui pratiquent le même sport que l'utilisateur actuel.
     * Les utilisateurs sont affichés dans un tableau avec leurs pseudonymes et les sports en commun.
     * Les utilisateurs avec une demande d'ami en attente ne sont pas inclus dans les résultats.
     */
    private void afficherUtilisateursMemeSport() {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User utilisateur = (User) session.get(User.class, u.getId());
        List<Sport> sportsUtilisateur = utilisateur.getSportsPratiques();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Pseudo");
        for (Sport sport : sportsUtilisateur) {
            model.addColumn(sport.getNom());
        }

        Set<User> utilisateursAjoutes = new HashSet<>(); // Pour suivre les utilisateurs déjà ajoutés
        List<DemandeAmi> d = utilisateur.getDemandesEnvoyees();
        List<DemandeAmi> d2 = utilisateur.getDemandesRecues();
        
        List<Long> utilisateursIdsAvecDemandeAmiEnAttente = getUtilisateurIdsAvecDemandeAmiEnAttente1(utilisateur);
        List<Long> utilisateursIdsAvecDemandeAmiEnvoyee = new ArrayList<Long>();
        for (DemandeAmi demande : utilisateur.getDemandesRecues()) {
            if (demande.getStatut().equals(StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE)|| demande.getStatut().equals(StatutDemandeAmi.StatutDemandeAm.ACCEPTEE)) {
                utilisateursIdsAvecDemandeAmiEnvoyee.add(demande.getDemandeur().getId());
            }
        }
          for (DemandeAmi demande : utilisateur.getDemandesEnvoyees()) {
            if (demande.getStatut().equals(StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE)|| demande.getStatut().equals(StatutDemandeAmi.StatutDemandeAm.ACCEPTEE)) {
                utilisateursIdsAvecDemandeAmiEnvoyee.add(demande.getDestinataire().getId());
            }
        }

        // Ajouter les utilisateurs qui ont envoyé une demande d'ami à la liste des utilisateurs avec demande en attente
        utilisateursIdsAvecDemandeAmiEnAttente.addAll(utilisateursIdsAvecDemandeAmiEnvoyee);
//
//        for (DemandeAmi demande : d) {
//            if (demande.getStatut().equals(StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE)) {
//                utilisateursIdsAvecDemandeAmiEnAttente.add(demande.getDestinataire().getId());
//            }
//        }
        // Parcourir tous les sports de l'utilisateur
        for (Sport sport : sportsUtilisateur) {
            // Récupérer tous les utilisateurs pratiquant le sport
            Criteria criteria = session.createCriteria(User.class)
                    .createAlias("sportsPratiques", "s")
                    .add(Restrictions.eq("s.class", sport.getClass()))
                    .add(Restrictions.ne("id", utilisateur.getId()));

            if (!utilisateursIdsAvecDemandeAmiEnAttente.isEmpty()) {
                criteria.add(Restrictions.not(Restrictions.in("id", utilisateursIdsAvecDemandeAmiEnAttente))); // Exclure les utilisateurs avec demande en attente
            }

            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            List<User> utilisateursPratiquantSport = criteria.list();

            // Ajouter les utilisateurs et les sports en commun à chaque itération
            for (User utilisateurSport : utilisateursPratiquantSport) {
                // Si l'utilisateur n'a pas déjà été ajouté, ajoutez-le à la table
                
                if (!utilisateursAjoutes.contains(utilisateurSport)) {
                    Object[] row = new Object[model.getColumnCount()];
                    row[0] = utilisateurSport.getPseudo();

                    int columnCount = 1; // Commencer à partir de la deuxième colonne
                    for (Sport userSport : sportsUtilisateur) {
                        boolean sportEnCommun = false;
                        // Vérifier si l'utilisateur a en commun le sport
                        for (Sport sportPratique : utilisateurSport.getSportsPratiques()) {
                            if (sportPratique.getNom().equals(userSport.getNom())) {
                                sportEnCommun = true;
                                break;
                            }
                        }
                        row[columnCount++] = sportEnCommun ? "Oui" : "Non";
                    }

                    // Ajouter la nouvelle ligne au modèle de la table
                    model.addRow(row);
                    utilisateursAjoutes.add(utilisateurSport); // Ajouter l'utilisateur à l'ensemble des utilisateurs ajoutés
                }
            }
        }

        jTable2.setModel(model);

        transaction.commit();
        session.close();
    }

    /**
     * Creates new form AjoutAmi
     */
    public AjoutAmi(User u) {

        initComponents();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        this.u = u;
        afficherUtilisateursMemeSport();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(25, 148, 173));
        jLabel1.setText("Suggestions d'amis");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 620, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Méthode appelée lorsqu'un clic de souris est effectué sur la jTable2.
     * Elle permet d'envoyer une invitation à un utilisateur sélectionné dans la table.
     * 
     * @param evt L'événement de la souris qui a déclenché l'appel à la méthode.
     */
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int ro = jTable2.getSelectedRow();
        if (ro >= 0) {
            // Get the pseudo of the selected user
            String pseudo = (String) jTable2.getValueAt(ro, 0);
            User u2 = User.getUserByPseudo(pseudo);

            int option = JOptionPane.showConfirmDialog(null, "Voulez-vous envoyer une invitation à " + pseudo + "?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                Session s = DBConnection.getSession();
                Transaction t = s.beginTransaction();
                User d = (User) s.get(User.class, u.getId());

                DemandeAmi a = new DemandeAmi();
                a.setDemandeur(d);
                a.setDestinataire(u2);
                a.setStatut(StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE);
                s.persist(a);
                d.getDemandesEnvoyees().add(a);
                t.commit();
                s.close();
                JOptionPane.showMessageDialog(this, "Demande d'ami", "PerforMates", JOptionPane.INFORMATION_MESSAGE);
                afficherUtilisateursMemeSport();
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws java.lang.InstantiationException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AjoutAmi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjoutAmi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjoutAmi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjoutAmi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
