/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modele.Cyclisme;
import modele.DBConnection;
import modele.Performances;
import modele.Sport;

import modele.TestJfreeChTime;
import modele.User;
import modele.VitesseCyclisme;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class CyclismeInterf extends javax.swing.JFrame {

    private User u;

    /**
     * Creates new form CyclismeInterf
     */
    public CyclismeInterf(User us) {
        initComponents();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        this.u = us;
        afficheTable();
    }

    private CyclismeInterf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void afficheTable() {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User utilisateur = (User) session.get(User.class, u.getId());
//        List<Sport> sportsPratiques = utilisateur.getSportsPratiques();
        List<Performances> performancesUtilisateur = utilisateur.getPerformances();

// Créer un tableau pour stocker les performances spécifiques au cyclisme
        List<Performances> performancesCyclisme = new ArrayList<>();

// Filtrer les performances pour ne garder que celles liées au cyclisme
        for (Performances performance : performancesUtilisateur) {
            if (performance.getSport().estCyclisme()) {
                performancesCyclisme.add(performance);
            }
        }

        // Create a table model to display the performances
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Distance parcourue");
        model.addColumn("Temps");
        for (Performances performance : performancesCyclisme) {
            Object[] row = new Object[3];
            row[0] = performance.getDate();
            row[1] = ((Cyclisme) performance.getSport()).getDistanceTotaleParcourue();
            row[2] = ((Cyclisme) performance.getSport()).getTempsPerformance();
            model.addRow(row);
        }

        // Set the table model to the JTable
        jTable2.setModel(model);

        transaction.commit();
        session.close();

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dparcour = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("CYCLISME");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 35, -1, 18));

        jLabel2.setText("Distance parcourue:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 115, -1, -1));

        jLabel3.setText("Temps:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        dparcour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dparcourActionPerformed(evt);
            }
        });
        getContentPane().add(dparcour, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 112, 80, -1));

        jButton1.setText("Ajouter perf");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        jButton2.setText("Afficher selon la distance en fonction des jours");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, -1, -1));

        jLabel4.setText("km");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 115, -1, -1));

        jLabel5.setText("min");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));
        getContentPane().add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

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
        jScrollPane1.setViewportView(jTable2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, 280));

        jButton3.setText("Afficher selon les durees de seance en fonction des jours");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 380, -1, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 80, -1));

        jButton5.setText("Vitesse");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 430, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dparcourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dparcourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dparcourActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();

        // Vérifier si une date future est saisie
        if (jCalendar1.getDate().after(new Date())) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une date valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifier si une performance existe déjà à cette date pour l'utilisateur
        User utilisateur = (User) session.get(User.class, u.getId());
        Date selectedDate = jCalendar1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDateString = sdf.format(jCalendar1.getDate());

        // Comparer avec la date existante
        Performances existingPerformance = null;
        for (Performances performance : utilisateur.getPerformances()) {
            String existingDateString = sdf.format(performance.getDate());
            if (existingDateString.equals(selectedDateString) && performance.getSport().estCyclisme()) {
                existingPerformance = performance;
                break;
            }
        }

        if (existingPerformance != null) {
            // Demander à l'utilisateur s'il souhaite modifier la performance existante
            int choice = JOptionPane.showConfirmDialog(this, "Une performance existe déjà à cette date. Voulez-vous la modifier ?", "Performance existante", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // Mettre à jour les valeurs de la performance existante
                Cyclisme c = (Cyclisme) existingPerformance.getSport();
                c.setDistanceTotaleParcourue(Double.parseDouble(dparcour.getText()));
                c.setTempsPerformance(Double.parseDouble(jTextField1.getText()));
                existingPerformance.setDate(selectedDate);
                session.update(c);
                session.update(existingPerformance);
                transaction.commit();
                session.close();
                JOptionPane.showMessageDialog(this, "Performance mise à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                afficheTable();
                return;
            } else {
                return;
            }
        }

        // Ajouter une nouvelle performance si aucune performance existante ou si l'utilisateur ne souhaite pas modifier
        if (dparcour.getText().isEmpty() || jTextField1.getText().isEmpty() || Double.parseDouble(dparcour.getText()) < 0 || Double.parseDouble(jTextField1.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir les champs avec des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            Cyclisme c = new Cyclisme();
            c.setDistanceTotaleParcourue(Double.parseDouble(dparcour.getText()));
            c.setTempsPerformance(Double.parseDouble(jTextField1.getText()));
            session.persist(c);

            Performances p = new Performances();
            p.setUser(utilisateur);
            p.setSport(c);
            p.setDate(selectedDate);
            session.persist(p);

            utilisateur.getPerformances().add(p);
            transaction.commit();
            session.close();
            JOptionPane.showMessageDialog(this, "Performance ajoutée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            afficheTable();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TestJfreeChTime tj = new TestJfreeChTime(this.u, true);
        tj.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here
//        TestJfreeCh tj = new TestJfreeCh(this.u, false);
//        tj.setVisible(true);
        TestJfreeChTime tj = new TestJfreeChTime(this.u, false);
        tj.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        VitesseCyclisme vc = new VitesseCyclisme(this.u);
        vc.setVisible(true);
         }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(CyclismeInterf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CyclismeInterf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CyclismeInterf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CyclismeInterf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CyclismeInterf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dparcour;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
