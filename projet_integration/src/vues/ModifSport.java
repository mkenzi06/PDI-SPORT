/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modele.CourseAPied;
import modele.Cyclisme;
import modele.DBConnection;
import modele.Halterophilie;
import modele.Natation;
import modele.Performances;
import modele.Sport;
import modele.Tennis;
import modele.User;
import modele.WindSurf;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class ModifSport extends javax.swing.JFrame {

    /**
     * Creates new form ModifSport
     */
    private User u;
    private final List<String> sportsDisponibles = Arrays.asList("Cyclisme", "Tennis", "Natation", "Course a pied", "Windsurf", "Haltérophilie");

    public ModifSport(User u) {
        initComponents();
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.u = u;
        initData(u);
        afficheSport();
        chargerSportsDisponibles();
        updateBtnAjouterState();

    }

    private void initData(User u) {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();
        User u1 = (User) s.get(User.class, u.getId());
        tnom.setText(u1.getNom());
        tprenom.setText(u1.getPrenom());
        tpseudo.setText(u1.getPseudo());
        t.commit();
        s.close();
    }

    private boolean aTropDeSports() {
        return jList1.getModel().getSize() >= 3;
    }

// Méthode pour activer ou désactiver le bouton "Ajouter" en fonction du nombre de sports choisis dans la jList1
    private void updateBtnAjouterState() {
        btnAjouter.setEnabled(!aTropDeSports());
    }

// Méthode pour activer ou désactiver le bouton "Ajouter" en fonction de la sélection d'un sport dans la jList2
    private void updateBtnAjouterStateJList2() {
        btnAjouter.setEnabled(jList2.getSelectedIndex() != -1 && !aTropDeSports());
    }

    private void chargerSportsDisponibles() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String sport : sportsDisponibles) {
            model.addElement(sport);
        }
        jList2.setModel(model);
    }

    private void saveUser(User user) {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();
        s.saveOrUpdate(user);
        t.commit();
        s.close();
    }

    private void afficheSport() {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();
        User u1 = (User) s.get(User.class, u.getId());
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Sport sport : u1.getSportsPratiques()) {
            model.addElement(sport.getNom());
        }
        jList1.setModel(model);
        t.commit();
        s.close();

    }

    private ModifSport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        btnAjouter = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tnom = new javax.swing.JTextField();
        tprenom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tpseudo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("Mes information");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 160, -1));

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 230, -1));

        btnAjouter.setText("Ajouter");
        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });
        getContentPane().add(btnAjouter, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 90, -1));

        jButton2.setText("Supprimer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setText("Mes sports");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));
        getContentPane().add(tnom, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 80, -1));
        getContentPane().add(tprenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 70, -1));

        jLabel3.setText("Prenom :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel4.setText("Nom :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jButton1.setText("Modifier");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, -1, -1));

        tpseudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpseudoActionPerformed(evt);
            }
        });
        getContentPane().add(tpseudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 80, -1));

        jLabel5.setText("Pseudo :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jButton3.setText("Profil");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 100, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed

        String sportChoisi = jList2.getSelectedValue();
        if (sportChoisi != null && !sportChoisi.isEmpty()) {
            DefaultListModel<String> model = (DefaultListModel<String>) jList1.getModel();
            if (!model.contains(sportChoisi)) {
                if (!aTropDeSports()) {
                    model.addElement(sportChoisi);
                    ajouterSportUtilisateur(sportChoisi);
                    updateBtnAjouterState(); // Mettre à jour l'état du bouton "Ajouter" en fonction de jList1
                } else {
                    JOptionPane.showMessageDialog(this, "Vous avez déjà choisi trois sports.", "Limite atteinte", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ce sport est déjà dans votre liste.", "Sport déjà ajouté", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAjouterActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String sportASupprimer = jList1.getSelectedValue();
        if (sportASupprimer != null && !sportASupprimer.isEmpty()) {
            DefaultListModel<String> model = (DefaultListModel<String>) jList1.getModel();
            int selectedIndex = jList1.getSelectedIndex();
            model.remove(selectedIndex); // Supprimer le sport de la JList

            supprimerSportUtilisateur(sportASupprimer); // Supprimer le sport de la base de données

            updateBtnAjouterState(); // Mettre à jour l'état du bouton "Ajouter"
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void tpseudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpseudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpseudoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User utilisateur = (User) session.get(User.class, u.getId());
        if (!tnom.getText().isEmpty() || !tprenom.getText().isEmpty() || !tpseudo.getText().isEmpty()) {
            String pseudoUtilisateurCourant = utilisateur.getPseudo();

            if (!tpseudo.getText().equals(pseudoUtilisateurCourant)) {
                // Vérifier si le nouveau pseudo existe déjà dans la base de données
                long count = (long) session.createQuery("SELECT COUNT(*) FROM User WHERE pseudo = :pseudo")
                        .setParameter("pseudo", tpseudo.getText())
                        .uniqueResult();

                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Erreur : Ce pseudo est déjà utilisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            utilisateur.setNom(tnom.getText());
            utilisateur.setPrenom(tprenom.getText());
            utilisateur.setPseudo(tpseudo.getText());
            session.update(utilisateur);
            transaction.commit();
            JOptionPane.showMessageDialog(this, "Données de l'utilisateur mises à jour avec succès !");
            initData(utilisateur);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User utilisateur = (User) session.get(User.class, u.getId());
        Profil p = new Profil(utilisateur);
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
//    private void supprimerSportUtilisateur(String sportASupprimer) {
//        Session session = null;
//        try {
//            session = DBConnection.getSession();
//            Transaction transaction = session.beginTransaction();
//
//            // Récupérer l'utilisateur actuel
//            User utilisateur = (User) session.get(User.class, u.getId());
//
//            // Rechercher le sport à supprimer dans la liste des sports de l'utilisateur
//            Sport sport = null;
//            for (Sport s : utilisateur.getSportsPratiques()) {
//                if (s.getNom().equals(sportASupprimer)) {
//                    sport = s;
//                    break;
//                }
//            }
//
//            if (sport != null) {
//                utilisateur.getSportsPratiques().remove(sport); // Supprimer le sport de la liste de l'utilisateur
//                session.delete(sport); // Supprimer le sport de la base de données
//                JOptionPane.showMessageDialog(this, "Sport supprimé avec succès !");
//            } else {
//                JOptionPane.showMessageDialog(this, "Le sport sélectionné n'existe pas dans la liste de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
//            }
//
//            // Mettre à jour l'utilisateur dans la base de données
//            session.update(utilisateur);
//
//            // Valider la transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (session != null) {
//                session.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }

    private void supprimerSportUtilisateur(String sportASupprimer) {
        Session session = null;
        try {
            session = DBConnection.getSession();
            Transaction transaction = session.beginTransaction();

            // Récupérer l'utilisateur actuel
            User utilisateur = (User) session.get(User.class, u.getId());

            // Rechercher le sport à supprimer dans la liste des sports de l'utilisateur
            Sport sport = null;
            for (Sport s : utilisateur.getSportsPratiques()) {
                if (s.getNom().equals(sportASupprimer)) {
                    sport = s;
                    break;
                }
            }

            if (sport != null) {
                // Supprimer les performances associées au sport
                System.out.println("vues.ModifSport.supprimerSportUtilisateur() debbug ");
                Iterator<Performances> iterator = utilisateur.getPerformances().iterator();
                while (iterator.hasNext()) {
                    Performances performance = iterator.next();
                    if (performance.getSport().getNom().equals(sportASupprimer)) {
                        iterator.remove(); // Use iterator's remove method
                        session.delete(performance);
                    }
                }
//                if (sport.estCyclisme()) {
//                    // Supprimer toutes les instances de Cyclisme une par une
//                    List<Cyclisme> cyclismes = session.createQuery("FROM Cyclisme WHERE id = :sportId")
//                            .setParameter("sportId", sport.getId())
//                            .list();
//                    for (Cyclisme cyclisme : cyclismes) {
//                        session.delete(cyclisme);
//                    }
//                }
                utilisateur.getSportsPratiques().remove(sport); // Supprimer le sport de la liste de l'utilisateur
                session.delete(sport); // Supprimer le sport de la base de données
                JOptionPane.showMessageDialog(this, "Sport supprimé avec succès !");
            } else {
                JOptionPane.showMessageDialog(this, "Le sport sélectionné n'existe pas dans la liste de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            // Mettre à jour l'utilisateur dans la base de données
            session.update(utilisateur);

            // Valider la transaction
            transaction.commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private void ajouterSportUtilisateur(String sportChoisi) {
        Session session = null;
        try {
            session = DBConnection.getSession();
            Transaction transaction = session.beginTransaction();

            // Récupérer l'utilisateur actuel
            User utilisateur = (User) session.get(User.class, u.getId());

            // Vérifier le type de sport choisi
            switch (sportChoisi) {
                case "Cyclisme":
                    Cyclisme s = new Cyclisme();
                    session.persist(s);
                    utilisateur.getSportsPratiques().add(s);
                    break;
                case "Tennis":
                    Tennis t = new Tennis();

                    session.persist(t);
                    utilisateur.getSportsPratiques().add(t);
                    break;
                case "Haltérophilie":
                    Halterophilie h = new Halterophilie();
                    utilisateur.getSportsPratiques().add(h);
                    session.persist(h);
                    break;
                case "Course a pied":
                    CourseAPied cap = new CourseAPied();
                    utilisateur.getSportsPratiques().add(cap);
                    session.persist(cap);
                    break;
                case "Natation":
                    Natation n = new Natation();
                    utilisateur.getSportsPratiques().add(n);
                    session.persist(n);
                    break;
                case "Windsurf":
                    WindSurf w = new WindSurf();
                    utilisateur.getSportsPratiques().add(w);
                    session.persist(w);
                    break;
                default:
                    System.err.println("Erreur : Sport non reconnu");
                    break;
            }

            // Mettre à jour l'utilisateur dans la base de données
            session.update(utilisateur);

            JOptionPane.showMessageDialog(this, "nouveau sport !!");
            // Valider la transaction
            transaction.commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ModifSport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifSport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifSport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifSport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifSport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tnom;
    private javax.swing.JTextField tprenom;
    private javax.swing.JTextField tpseudo;
    // End of variables declaration//GEN-END:variables
}
