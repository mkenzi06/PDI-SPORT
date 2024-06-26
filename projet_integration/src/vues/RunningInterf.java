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
import modele.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modele.*;

/**
 *
 * @author HP
 */
/**
 * Cette classe représente l'interface graphique de la fenêtre de la course à pied.
 * Elle affiche les performances de l'utilisateur liées à la course à pied.
 */
public class RunningInterf extends javax.swing.JFrame {

    /**
     * Creates new form RunningInterf
     */
    private User u;

    public RunningInterf(User us) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        this.u = us;

        afficheTable();
    }

    private RunningInterf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void afficheTable() {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User utilisateur = (User) session.get(User.class, u.getId());
//        List<Sport> sportsPratiques = utilisateur.getSportsPratiques();
        List<Performances> performancesUtilisateur = utilisateur.getPerformances();

// Créer un tableau pour stocker les performances spécifiques au cyclisme
        List<Performances> performancesC = new ArrayList<>();

// Filtrer les performances pour ne garder que celles liées au cyclisme
        for (Performances performance : performancesUtilisateur) {
            if (performance.getSport().estCap()) {
                performancesC.add(performance);
            }
        }

        // Create a table model to display the performances
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Distance parcourue");
        model.addColumn("Temps");
        for (Performances performance : performancesC) {
            Object[] row = new Object[3];
            row[0] = performance.getDate();
            row[1] = ((CourseAPied) performance.getSport()).getDistanceParcourue();
            row[2] = ((CourseAPied) performance.getSport()).getTempsPerformance();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dparcour = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 59, -1, 280));

        jPanel1.setBackground(new java.awt.Color(166, 185, 181));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(224, 106, 22));
        jLabel1.setText("Course à pied");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 350, 70));
        jPanel1.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Enregistrer la performance");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("min");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("km");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 80, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Temps:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

        dparcour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dparcourActionPerformed(evt);
            }
        });
        jPanel1.add(dparcour, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 80, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Distance parcourue:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        jButton5.setText("Déduire la vitesse");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton2.setText("Visualiser la distance en fonction des jours.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton3.setText("Visualiser les durées de séance en fonction des jours.\n\n");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dparcourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dparcourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dparcourActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (dparcour.getText().isEmpty() || jTextField1.getText().isEmpty() || Double.parseDouble(dparcour.getText()) < 0 || Double.parseDouble(jTextField1.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Verifier la saisie des donnees", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
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
                if (existingDateString.equals(selectedDateString) && performance.getSport().estCap()) {
                    existingPerformance = performance;
                    break;
                }
            }

            if (existingPerformance != null) {
                // Demander à l'utilisateur s'il souhaite modifier la performance existante
                int choice = JOptionPane.showConfirmDialog(this, "Une performance existe déjà à cette date. Voulez-vous la modifier ?", "Performance existante", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // Mettre à jour les valeurs de la performance existante
                    CourseAPied cap = (CourseAPied) existingPerformance.getSport();
                    cap.setDistanceParcourue(Double.parseDouble(dparcour.getText()));
                    cap.setTempsPerformance(Double.parseDouble(jTextField1.getText()));
                    existingPerformance.setDate(selectedDate);
                    session.update(cap);
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
                CourseAPied c = new CourseAPied();
                c.setDistanceParcourue(Double.parseDouble(dparcour.getText()));
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
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage().toString(), "erreur", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        TestJfreeChTime tj = new TestJfreeChTime(this.u, true);
//        tj.setVisible(true);
        JfreeChCap c = new JfreeChCap(this.u, true);
        c.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here
        //        TestJfreeCh tj = new TestJfreeCh(this.u, false);
        //        tj.setVisible(true);
        JfreeChCap c = new JfreeChCap(this.u, false);
        c.setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        VitesseCap vc = new VitesseCap(this.u);
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
            java.util.logging.Logger.getLogger(RunningInterf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RunningInterf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RunningInterf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RunningInterf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RunningInterf().setVisible(true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
