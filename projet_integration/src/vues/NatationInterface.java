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
import modele.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class NatationInterface extends javax.swing.JFrame {

    private User u;

    /**
     * Creates new form NatationInterface
     */
    public NatationInterface(User us) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        this.u = us;
        afficheTable();

    }

    private void afficheTable() {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User utilisateur = (User) session.get(User.class, u.getId());
//        List<Sport> sportsPratiques = utilisateur.getSportsPratiques();
        List<Performances> performancesUtilisateur = utilisateur.getPerformances();

// Créer un tableau pour stocker les performances spécifiques au cyclisme
        List<Performances> performances = new ArrayList<>();

// Filtrer les performances pour ne garder que celles liées au cyclisme
        for (Performances performance : performancesUtilisateur) {
            if (performance.getSport().estNatation()) {
                performances.add(performance);
            }
        }

        // Create a table model to display the performances
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Crawl minute");
        model.addColumn("Papillon minute");
        model.addColumn("total minute");
        for (Performances performance : performances) {
            Object[] row = new Object[4];
            row[0] = performance.getDate();
            row[1] = ((Natation) performance.getSport()).getCrawlTimePercentage();
            row[2] = ((Natation) performance.getSport()).getPapillonTimePercentage();
            row[3] = ((Natation) performance.getSport()).getTotalSessionTime();
            model.addRow(row);
        }

        jTable2.setModel(model);

        transaction.commit();
        session.close();

    }

    private NatationInterface() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        dparcour = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jCalendar1 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(166, 185, 181));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(224, 106, 22));
        jLabel1.setText("Natation");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 250, 60));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, -1, 280));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Durée séance totale :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        dparcour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dparcourActionPerformed(evt);
            }
        });
        jPanel1.add(dparcour, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 80, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("min");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Temps passé crawl:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Temps passé papillon :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("min");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, -1, -1));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 80, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("min");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 80, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Enregistrer la performance\n\n");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton3.setText("Visualiser les types de nages pour une journée sélectionnée dans la table.\n");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, -1, -1));
        jPanel1.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 520));

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

        // Récupérer l'utilisateur
        User utilisateur = (User) session.get(User.class, u.getId());
        Date selectedDate = jCalendar1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDateString = sdf.format(jCalendar1.getDate());

        // Vérifier si une performance existe déjà à cette date pour l'utilisateur
//    Date selectedDate = jCalendar1.getDate();
        Performances existingPerformance = null;
        for (Performances performance : utilisateur.getPerformances()) {
            String existingDateString = sdf.format(performance.getDate());
            if (existingDateString.equals(selectedDateString) && performance.getSport().estNatation()) {
                existingPerformance = performance;
                break;
            }
        }

        if (existingPerformance != null) {
            // Demander à l'utilisateur s'il souhaite modifier la performance existante
            int choice = JOptionPane.showConfirmDialog(this, "Une performance existe déjà à cette date. Voulez-vous la modifier ?", "Performance existante", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // Mettre à jour les valeurs de la performance existante
                Natation natation = (Natation) existingPerformance.getSport();
                natation.setCrawlTimePercentage(Double.parseDouble(jTextField1.getText()));
                natation.setPapillonTimePercentage(Double.parseDouble(jTextField2.getText()));
                natation.setTotalSessionTime(Integer.parseInt(dparcour.getText()));
                existingPerformance.setDate(selectedDate);
                session.update(existingPerformance);
                transaction.commit();
                session.close();
                JOptionPane.showMessageDialog(this, "Performance mise à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                afficheTable();
                return;
            }else{
                return;
            }
        }

        // Ajouter une nouvelle performance si aucune performance existante ou si l'utilisateur ne souhaite pas modifier
        if (dparcour.getText().isEmpty() || jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty() || Double.parseDouble(dparcour.getText()) < 0 || Double.parseDouble(jTextField1.getText()) < 0 || Double.parseDouble(jTextField2.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir les champs avec des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            Natation natation = new Natation();
            natation.setCrawlTimePercentage(Double.parseDouble(jTextField1.getText()));
            natation.setPapillonTimePercentage(Double.parseDouble(jTextField2.getText()));
            natation.setTotalSessionTime(Integer.parseInt(dparcour.getText()));
            session.persist(natation);

            Performances p = new Performances();
            p.setUser(utilisateur);
            p.setSport(natation);
            p.setDate(selectedDate);
            session.persist(p);

            utilisateur.getPerformances().add(p);
            transaction.commit();
            session.close();
            JOptionPane.showMessageDialog(this, "Performance ajoutée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            afficheTable();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne dans la table.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Date selectedDate = (Date) jTable2.getValueAt(selectedRow, 0);
        CamembertNatation c = new CamembertNatation(u, selectedDate);
        c.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(NatationInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NatationInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NatationInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NatationInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NatationInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dparcour;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
