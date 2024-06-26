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
import modele.CamembertNatation;
import modele.CamembertTennis;
import modele.Cyclisme;
import modele.DBConnection;
import modele.Natation;
import modele.Performances;
import modele.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modele.Tennis;

/**
 *
 * @author HP
 */
/**
 * Cette classe représente l'interface graphique du Tennis.
 * Elle étend la classe javax.swing.JFrame et affiche les performances de l'utilisateur dans un tableau.
 */
public class TennisInterface extends javax.swing.JFrame {

    /**
     * Creates new form Tennis
     */
    private User u;

    public TennisInterface(User u) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        this.u = u;
        afficheTable();
    }

    private TennisInterface() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            if (performance.getSport().estTennis()) {
                performances.add(performance);
            }
        }

        // Create a table model to display the performances
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Sets gagnes");
        model.addColumn("Sets totale dans le match");

        for (Performances performance : performances) {
            Object[] row = new Object[3];
            row[0] = performance.getDate();
            row[1] = ((Tennis) performance.getSport()).getNombreSetsGagnes();
            row[2] = ((Tennis) performance.getSport()).getNombresetsmatch();
            model.addRow(row);
        }

        jTable1.setModel(model);

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
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(224, 106, 22));
        jLabel1.setText("Tennis");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jPanel1.setBackground(new java.awt.Color(166, 185, 181));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 110, -1));

        jTextField2.setText("6");
        jTextField2.setEnabled(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 110, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sets defini : ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre de sets gagnés : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Enregistrer la performance");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, -1, -1));
        jPanel1.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, -1, 210));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton2.setText("Visualiser le nombre de sets remportés par jour en fonction du total.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        try {
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
                if (existingDateString.equals(selectedDateString) && performance.getSport().estTennis()) {
                    existingPerformance = performance;
                    break;
                }
            }

            if (existingPerformance != null) {
                // Demander à l'utilisateur s'il souhaite modifier la performance existante
                int choice = JOptionPane.showConfirmDialog(this, "Une performance existe déjà à cette date. Voulez-vous la modifier ?", "Performance existante", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // Mettre à jour les valeurs de la performance existante
                    if (Integer.parseInt(jTextField1.getText()) <= Integer.parseInt(jTextField2.getText())) {
                        Tennis c = (Tennis) existingPerformance.getSport();
                        c.setNombreSetsGagnes(Integer.parseInt(jTextField1.getText()));
                        c.setNombresetsmatch(Integer.parseInt(jTextField2.getText()));
                        existingPerformance.setDate(selectedDate);
                        session.update(c);
                        session.update(existingPerformance);
                        transaction.commit();
                        session.close();
                        JOptionPane.showMessageDialog(this, "Performance mise à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        afficheTable();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur avec la saisie des donnees");
                    }
                } else {
                    return;
                }
            }
//        System.out.println("vues.TennisInterface.jButton1ActionPerformed()"+jTextField2.getText());
            // Ajouter une nouvelle performance si aucune performance existante ou si l'utilisateur ne souhaite pas modifier
            if (jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty() || Integer.parseInt(jTextField1.getText()) < 0 || Integer.parseInt(jTextField2.getText()) < 0 || Integer.parseInt(jTextField1.getText()) > Integer.parseInt(jTextField2.getText())) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir les champs avec des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                Tennis c = new Tennis();
                c.setNombreSetsGagnes(Integer.parseInt(jTextField1.getText()));
                c.setNombresetsmatch(Integer.parseInt(jTextField2.getText()));
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
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne dans la table.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Date selectedDate = (Date) jTable1.getValueAt(selectedRow, 0);
        CamembertTennis c = new CamembertTennis(u, selectedDate);
        c.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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
            java.util.logging.Logger.getLogger(TennisInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TennisInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TennisInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TennisInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TennisInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
