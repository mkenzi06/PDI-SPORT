/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modele.Cyclisme;
import modele.DBConnection;
import modele.Sport;
import modele.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.security.*;
import modele.CourseAPied;
import modele.Halterophilie;
import modele.Natation;
import modele.Tennis;
import modele.WindSurf;
import org.jfree.chart.needle.WindNeedle;

/**
 *
 * @author Sabine
 */
public class Inscription extends javax.swing.JFrame {

    private int nombreCasesCochees = 0;

    /**
     * Creates new form login
     */
    public Inscription() {
        initComponents();
        cyclisme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionNombreCasesCochées();
            }

        });

        course.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionNombreCasesCochées();
            }
        });

        tennis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionNombreCasesCochées();
            }
        });

        natation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionNombreCasesCochées();
            }
        });

        plancheavoile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionNombreCasesCochées();
            }
        });

        halterophilie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionNombreCasesCochées();
            }
        });

    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void gestionNombreCasesCochées() {
        int nombreCasesActuelles = (cyclisme.isSelected() ? 1 : 0)
                + (course.isSelected() ? 1 : 0)
                + (tennis.isSelected() ? 1 : 0)
                + (natation.isSelected() ? 1 : 0)
                + (plancheavoile.isSelected() ? 1 : 0)
                + (halterophilie.isSelected() ? 1 : 0);

        if (nombreCasesActuelles > 3) {
            JOptionPane.showMessageDialog(this, "Vous ne pouvez sélectionner que trois sports au maximum.", "Erreur", JOptionPane.ERROR_MESSAGE);
            // Annuler la sélection de la dernière case cochée
            if (cyclisme.isSelected()) {
                cyclisme.setSelected(false);
            } else if (course.isSelected()) {
                course.setSelected(false);
            } else if (tennis.isSelected()) {
                tennis.setSelected(false);
            } else if (natation.isSelected()) {
                natation.setSelected(false);
            } else if (plancheavoile.isSelected()) {
                plancheavoile.setSelected(false);
            } else if (halterophilie.isSelected()) {
                halterophilie.setSelected(false);
            }
        }
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
        connexionText = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        formSection = new javax.swing.JPanel();
        exitCurseur = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        inscriptionbutton = new javax.swing.JButton();
        inscription = new javax.swing.JLabel();
        inscritText = new javax.swing.JLabel();
        mdp = new javax.swing.JPasswordField();
        pseudo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pseudo1 = new javax.swing.JTextField();
        selectsport = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cyclisme = new javax.swing.JCheckBox();
        course = new javax.swing.JCheckBox();
        tennis = new javax.swing.JCheckBox();
        natation = new javax.swing.JCheckBox();
        plancheavoile = new javax.swing.JCheckBox();
        halterophilie = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        mdp3 = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(166, 185, 181));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        connexionText.setFont(new java.awt.Font("Georgia", 1, 30)); // NOI18N
        connexionText.setForeground(new java.awt.Color(224, 106, 22));
        connexionText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connexionText.setText("Suivez, comparez, excellez!");
        jPanel1.add(connexionText, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 420, 41));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Mask_group.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 440));

        formSection.setBackground(new java.awt.Color(25, 149, 173));
        formSection.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitCurseur.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        exitCurseur.setForeground(new java.awt.Color(255, 255, 255));
        exitCurseur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitCurseur.setText("x");
        exitCurseur.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exitCurseur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitCurseurMouseClicked(evt);
            }
        });
        formSection.add(exitCurseur, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 40, 29));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(199, 226, 255));
        jLabel5.setText("Nom");
        formSection.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 120, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(199, 226, 255));
        jLabel8.setText("Confirmer votre mot de passe");
        formSection.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 341, -1));

        inscriptionbutton.setBackground(new java.awt.Color(255, 255, 255));
        inscriptionbutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        inscriptionbutton.setForeground(new java.awt.Color(25, 149, 173));
        inscriptionbutton.setText("Inscription");
        inscriptionbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        inscriptionbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inscriptionbuttonActionPerformed(evt);
            }
        });
        formSection.add(inscriptionbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 341, 40));

        inscription.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        inscription.setForeground(new java.awt.Color(255, 255, 255));
        inscription.setText("Se connecter");
        inscription.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        inscription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inscriptionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inscriptionMouseEntered(evt);
            }
        });
        formSection.add(inscription, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 80, -1));

        inscritText.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        inscritText.setForeground(new java.awt.Color(199, 226, 255));
        inscritText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        inscritText.setText("Déja membre?");
        formSection.add(inscritText, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 400, 213, -1));

        mdp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdpActionPerformed(evt);
            }
        });
        formSection.add(mdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 340, 30));

        pseudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pseudoActionPerformed(evt);
            }
        });
        pseudo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pseudoFocusLost(evt);
            }
        });
        formSection.add(pseudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 160, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(199, 226, 255));
        jLabel6.setText("Prénom");
        formSection.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 120, -1));

        pseudo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pseudo1ActionPerformed(evt);
            }
        });
        pseudo1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pseudo1FocusLost(evt);
            }
        });
        formSection.add(pseudo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 160, 30));

        selectsport.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        selectsport.setForeground(new java.awt.Color(199, 226, 255));
        selectsport.setText("Selectionner les sports");
        formSection.add(selectsport, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 341, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(199, 226, 255));
        jLabel11.setText("Pseudo");
        formSection.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 341, -1));

        cyclisme.setForeground(new java.awt.Color(255, 255, 255));
        cyclisme.setText("Cyclisme");
        cyclisme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cyclismeActionPerformed(evt);
            }
        });
        formSection.add(cyclisme, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        course.setBackground(new java.awt.Color(0, 51, 255));
        course.setForeground(new java.awt.Color(255, 255, 255));
        course.setText("Course à pied");
        course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseActionPerformed(evt);
            }
        });
        formSection.add(course, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        tennis.setForeground(new java.awt.Color(255, 255, 255));
        tennis.setText("Tennis");
        tennis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tennisActionPerformed(evt);
            }
        });
        formSection.add(tennis, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, -1, -1));

        natation.setForeground(new java.awt.Color(255, 255, 255));
        natation.setText("Natation");
        natation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                natationActionPerformed(evt);
            }
        });
        formSection.add(natation, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, -1, -1));

        plancheavoile.setForeground(new java.awt.Color(255, 255, 255));
        plancheavoile.setText("Planche à voile");
        plancheavoile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plancheavoileActionPerformed(evt);
            }
        });
        formSection.add(plancheavoile, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, -1, -1));

        halterophilie.setForeground(new java.awt.Color(255, 255, 255));
        halterophilie.setText("Haltérophilie");
        halterophilie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                halterophilieActionPerformed(evt);
            }
        });
        formSection.add(halterophilie, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(199, 226, 255));
        jLabel10.setText("Mot de passe");
        formSection.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 341, -1));

        mdp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdp3ActionPerformed(evt);
            }
        });
        mdp3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                mdp3FocusLost(evt);
            }
        });
        formSection.add(mdp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 340, 30));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        formSection.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 116, 340, 30));

        getContentPane().add(formSection, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 420, 440));

        setSize(new java.awt.Dimension(916, 438));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitCurseurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitCurseurMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitCurseurMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        for (double i = 0.0; i <= 1.0; i = i + 0.1) {
            String val = i + "";
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try {
                Thread.sleep(50);
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void inscriptionbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inscriptionbuttonActionPerformed
        if (pseudo.getText().isEmpty() || mdp.getText().isEmpty() || pseudo1.getText().isEmpty() || jTextField1.getText().isEmpty() || mdp3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Erreur : Champ Obligatoire", "", JOptionPane.ERROR_MESSAGE);
        } else if (!mdp3.getText().trim().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            JOptionPane.showMessageDialog(this, "Erreur : Le mot de passe doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre et être d'au moins 8 caractères.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Arrêter le processus d'inscription si la validation échoue
        } else if (!pseudo.getText().trim().matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(this, "Erreur : seulement des lettres pour votre nom ", "Erreur", JOptionPane.ERROR_MESSAGE);
            pseudo.setText("");
        } else if (!jTextField1.getText().trim().matches("^[a-zA-Z0-9-_]{4,20}$")) {
            JOptionPane.showMessageDialog(this, "Erreur : Le pseudo ne doit contenir que des lettres, chiffres, et doit etre sup a 4", "Erreur", JOptionPane.ERROR_MESSAGE);
            jTextField1.setText("");
        } else if (!pseudo1.getText().trim().matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(this, "Erreur : seulement des lettres pour votre prenom ", "Erreur", JOptionPane.ERROR_MESSAGE);
            pseudo1.setText("");
        } else if (nombreCasesCochees == 0) {
            JOptionPane.showMessageDialog(this, "Veuillez selectionner au moins un sport.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (!mdp.getText().trim().equals(mdp3.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Les mots de passe ne concordent pas.", "Erreur Formulaire", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!cyclisme.isSelected() && !course.isSelected() && !tennis.isSelected() && !natation.isSelected() && !plancheavoile.isSelected() && !halterophilie.isSelected()) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner au moins un sport.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return; // Empêcher l'insertion dans la base de données si aucun sport n'est sélectionné
            }
            //insertion dans la BD avec hibernate
            Session session = DBConnection.getSession();
            Transaction transaction = session.beginTransaction();
            String hashedPassword = hashPassword(mdp3.getText().trim());

            if (hashedPassword != null) {
                long count = (long) session.createQuery("SELECT COUNT(*) FROM User WHERE pseudo = :pseudo")
                        .setParameter("pseudo", jTextField1.getText().trim())
                        .uniqueResult();

                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Erreur : Ce pseudo est déjà utilisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                User utilisateur = new User(pseudo.getText().trim(), pseudo1.getText().trim(), jTextField1.getText().trim(), hashedPassword);

                // Associer les sports sélectionnés à l'utilisateur
                if (cyclisme.isSelected()) {
                    Cyclisme s = new Cyclisme();
                    session.persist(s);
                    utilisateur.getSportsPratiques().add(s);
                }
                if (natation.isSelected()) {
                    Natation s = new Natation();
                    session.persist(s);
                    utilisateur.getSportsPratiques().add(s);
                }
                if (plancheavoile.isSelected()) {
                    WindSurf s = new WindSurf();

                    session.persist(s);
                    utilisateur.getSportsPratiques().add(s);
                }
                if (tennis.isSelected()) {
                    Tennis s = new Tennis();

                    session.persist(s);
                    utilisateur.getSportsPratiques().add(s);
                }
                if (halterophilie.isSelected()) {
                    Halterophilie s = new Halterophilie();

                    session.persist(s);
                    utilisateur.getSportsPratiques().add(s);
                }
                if (course.isSelected()) {
                    CourseAPied s = new CourseAPied();

                    session.persist(s);
                    utilisateur.getSportsPratiques().add(s);
                }

                // Insérer l'utilisateur dans la base de données
                session.persist(utilisateur);
                transaction.commit();
                JOptionPane.showMessageDialog(this, "Inscription reussie !", "INSCRIPTION", JOptionPane.INFORMATION_MESSAGE);
                Connexion c = new Connexion();
                c.setVisible(true);
                this.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors du hachage du mot de passe", "erreur", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_inscriptionbuttonActionPerformed

    private void mdpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mdpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mdpActionPerformed

    private void pseudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pseudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pseudoActionPerformed

    private void pseudo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pseudo1ActionPerformed

    }//GEN-LAST:event_pseudo1ActionPerformed

    private void cyclismeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cyclismeActionPerformed
        if (cyclisme.isSelected()) {
            nombreCasesCochees++;
        } else {
            nombreCasesCochees--;
        }
        gestionNombreCasesCochées();
    }//GEN-LAST:event_cyclismeActionPerformed

    private void courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseActionPerformed
        if (course.isSelected()) {
            nombreCasesCochees++;
        } else {
            nombreCasesCochees--;
        }
        gestionNombreCasesCochées();
    }//GEN-LAST:event_courseActionPerformed

    private void tennisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tennisActionPerformed
        if (tennis.isSelected()) {
            nombreCasesCochees++;
        } else {
            nombreCasesCochees--;
        }
        gestionNombreCasesCochées();
    }//GEN-LAST:event_tennisActionPerformed

    private void natationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_natationActionPerformed
        if (natation.isSelected()) {
            nombreCasesCochees++;
        } else {
            nombreCasesCochees--;
        }
        gestionNombreCasesCochées();
    }//GEN-LAST:event_natationActionPerformed

    private void plancheavoileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plancheavoileActionPerformed
        if (plancheavoile.isSelected()) {
            nombreCasesCochees++;
        } else {
            nombreCasesCochees--;
        }
        gestionNombreCasesCochées();
    }//GEN-LAST:event_plancheavoileActionPerformed

    private void halterophilieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_halterophilieActionPerformed
        if (halterophilie.isSelected()) {
            nombreCasesCochees++;
        } else {
            nombreCasesCochees--;
        }
        gestionNombreCasesCochées();
    }//GEN-LAST:event_halterophilieActionPerformed

    private void mdp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mdp3ActionPerformed

    }//GEN-LAST:event_mdp3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost

    }//GEN-LAST:event_jTextField1FocusLost

    private void pseudoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pseudoFocusLost

    }//GEN-LAST:event_pseudoFocusLost

    private void pseudo1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pseudo1FocusLost

    }//GEN-LAST:event_pseudo1FocusLost

    private void mdp3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdp3FocusLost

    }//GEN-LAST:event_mdp3FocusLost

    private void inscriptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inscriptionMouseClicked
        Connexion c = new Connexion();
        c.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_inscriptionMouseClicked

    private void inscriptionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inscriptionMouseEntered
        Component component = evt.getComponent();
        component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_inscriptionMouseEntered

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
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Inscription().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel connexionText;
    private javax.swing.JCheckBox course;
    private javax.swing.JCheckBox cyclisme;
    private javax.swing.JLabel exitCurseur;
    private javax.swing.JPanel formSection;
    private javax.swing.JCheckBox halterophilie;
    private javax.swing.JLabel inscription;
    private javax.swing.JButton inscriptionbutton;
    private javax.swing.JLabel inscritText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPasswordField mdp;
    private javax.swing.JPasswordField mdp3;
    private javax.swing.JCheckBox natation;
    private javax.swing.JCheckBox plancheavoile;
    private javax.swing.JTextField pseudo;
    private javax.swing.JTextField pseudo1;
    private javax.swing.JLabel selectsport;
    private javax.swing.JCheckBox tennis;
    // End of variables declaration//GEN-END:variables
}
