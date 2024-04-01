/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modele.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class Profil extends javax.swing.JFrame {

    private User u;
    DefaultListModel<String> demandesModel = new DefaultListModel<>();
    DefaultListModel<String> demandesEnAttenteModel = new DefaultListModel<>();

    public Profil(User user) {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(this);
        this.u = user;
        afficherDonneesUtilisateur();

    }

    private Profil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void afficherDonneesUtilisateur() {
        // Exemple d'utilisation des données de l'utilisateur
        Session se = DBConnection.getSession();
        Transaction t = se.beginTransaction();
        User ur = (User) se.get(User.class, u.getId());
        nomprenom.setText(ur.getNom() + "\t " + ur.getPrenom());
        t.commit();
        se.close();
        if (u.getPhotoProfil() != null && u.getPhotoProfil().length > 0) {
            // Si l'utilisateur a une photo, afficher cette photo
            ImageIcon img = new ImageIcon(u.getPhotoProfil());
            Image imgrec = img.getImage();
            Image nimage = imgrec.getScaledInstance(lphoto.getWidth(), lphoto.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imgfinal = new ImageIcon(nimage);
            lphoto.setIcon(imgfinal);
        } else {
            // Si l'utilisateur n'a pas de photo, afficher une photo par défaut
            ImageIcon img = new ImageIcon(getClass().getResource("/icon/user (1).png"));
            lphoto.setIcon(img);
        }
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User utilisateur = (User) session.get(User.class, u.getId());
        List<Sport> sportsPratiques = utilisateur.getSportsPratiques();
        HashMap<String, String> sportPhotos = new HashMap<>();
// Ajouter les noms de sports et leurs photos correspondantes à la HashMap
//        sportPhotos.put("Cyclisme", "/icon/cyclisme.jpg");
        sportPhotos.put("Course a pied", "/icon/running.png");
        sportPhotos.put("Natation", "/icon/swimming.png");
        sportPhotos.put("Tennis", "/icon/tennis.png");
        sportPhotos.put("Haltérophilie", "/icon/weightlifter.png");
        sportPhotos.put("Windsurf", "/icon/windsurfing.png");
        sportPhotos.put("Cyclisme", "/icon/cycling.png");
        int i = 0;
//        sportbutton1.hide();
        for (Sport sport : sportsPratiques) {
            if (i > 2) {
                break;
            }

            if (i == 0) {

                sportbutton1.setText(sport.getNom());
                sportbutton1.setVisible(true);
                String photoPath = sportPhotos.get(sport.getNom());
                // Charger et définir la photo pour le premier bouton
                if (photoPath != null) {
                    ImageIcon img = new ImageIcon(getClass().getResource(photoPath));
                    sportbutton1.setIcon(img);
                }
            }
            if (i == 1) {
                sportbutton2.setText(sport.getNom());
                sportbutton2.setVisible(true);
                String photoPath = sportPhotos.get(sport.getNom());
                // Charger et définir la photo pour le premier bouton
                if (photoPath != null) {
                    ImageIcon img = new ImageIcon(getClass().getResource(photoPath));
                    sportbutton2.setIcon(img);
                }

            }
            if (i == 2) {
                sportbutton3.setText(sport.getNom());
                sportbutton3.setVisible(true);
                String photoPath = sportPhotos.get(sport.getNom());
                // Charger et définir la photo pour le premier bouton
                if (photoPath != null) {
                    ImageIcon img = new ImageIcon(getClass().getResource(photoPath));
                    sportbutton3.setIcon(img);
                }

            }
            i++;

//                    System.out.println(sport.getNom());
        }
        if (i == 1) {
            sportbutton2.setVisible(false);
            sportbutton3.setVisible(false);
        } else if (i == 2) {
            sportbutton3.setVisible(false);
        }
        transaction.commit();
        session.close();
        initListeAmi();
        

    }

    public void initListeAmi() {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User utilisateur = (User) session.get(User.class, u.getId());
        List<DemandeAmi> demandesRecues = utilisateur.getDemandesRecues();
        List<DemandeAmi> demandesEnvoy = utilisateur.getDemandesEnvoyees();
        DefaultListModel<String> demandesModel = new DefaultListModel<>();
        DefaultListModel<String> demandesEnAttenteModel = new DefaultListModel<>();

        // Vérifier si la liste des demandes reçues est vide
        if (demandesRecues != null && !demandesRecues.isEmpty()) {
            // Parcourir la liste des demandes reçues et les ajouter au modèle de liste
            for (DemandeAmi demande : demandesRecues) {
                User demandeur = demande.getDemandeur();
                String statut = demande.getStatut().toString();
                if (statut.equals("EN_ATTENTE")) {
                    demandesEnAttenteModel.addElement(demandeur.getPseudo());
                } else if (statut.equals("ACCEPTEE")) {
                    demandesModel.addElement(demandeur.getPseudo());
                    if (!demandesModel.contains(demandeur.getPseudo())) {
                        demandesModel.addElement(demandeur.getPseudo());
                    }
                }
            }
        }
        if (demandesEnvoy != null && !demandesEnvoy.isEmpty()) {
            // Parcourir la liste des demandes envoyées et les ajouter au modèle de liste
            for (DemandeAmi demande : demandesEnvoy) {
                User destinataire = demande.getDestinataire();
                String statut = demande.getStatut().toString();
                if (statut.equals("EN_ATTENTE") && destinataire.equals(utilisateur)) {
                    if (!demandesEnAttenteModel.contains(destinataire.getPseudo())) {
                        demandesEnAttenteModel.addElement(destinataire.getPseudo());
                    }
                } else if (statut.equals("ACCEPTEE")) {
                    if (!demandesModel.contains(destinataire.getPseudo())) {
                        demandesModel.addElement(destinataire.getPseudo());
                    }
                }
            }
        }
        
        listamis.setModel(demandesModel);
        demandeamis.setModel(demandesEnAttenteModel);
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

        backblue = new javax.swing.JPanel();
        nomprenom = new javax.swing.JLabel();
        mesportsbutton = new javax.swing.JButton();
        rechamisbutton = new javax.swing.JButton();
        exitCurseur = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lphoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tpath = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        rechamisbutton1 = new javax.swing.JButton();
        backgris1 = new javax.swing.JPanel();
        listamistext = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listamis = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        demandeamis = new javax.swing.JList<>();
        demandemaistext = new javax.swing.JLabel();
        sportbutton3 = new javax.swing.JButton();
        sportbutton1 = new javax.swing.JButton();
        sportbutton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backblue.setBackground(new java.awt.Color(25, 149, 173));
        backblue.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomprenom.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nomprenom.setForeground(new java.awt.Color(255, 255, 255));
        nomprenom.setText("Nom Prenom");
        backblue.add(nomprenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        mesportsbutton.setBackground(new java.awt.Color(255, 255, 255));
        mesportsbutton.setText("Mes sports");
        mesportsbutton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mesportsbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesportsbuttonActionPerformed(evt);
            }
        });
        backblue.add(mesportsbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 248, 54));

        rechamisbutton.setBackground(new java.awt.Color(255, 255, 255));
        rechamisbutton.setText("Recherche amis");
        rechamisbutton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rechamisbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechamisbuttonActionPerformed(evt);
            }
        });
        backblue.add(rechamisbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 70, 248, 54));

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
        backblue.add(exitCurseur, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 0, 40, 29));

        jPanel3.setBackground(new java.awt.Color(25, 150, 173));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lphoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user (1).png"))); // NOI18N
        jPanel3.add(lphoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -10, 130, 150));

        backblue.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 170, 130));

        jButton1.setText("Charger la photo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        backblue.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        tpath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpathActionPerformed(evt);
            }
        });
        backblue.add(tpath, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 180, -1));

        jButton2.setText("Soumettre");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        backblue.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        rechamisbutton1.setBackground(new java.awt.Color(255, 255, 255));
        rechamisbutton1.setText("Deconnexion");
        rechamisbutton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rechamisbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechamisbutton1ActionPerformed(evt);
            }
        });
        backblue.add(rechamisbutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 70, 248, 54));

        getContentPane().add(backblue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1490, 180));

        backgris1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listamistext.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        listamistext.setText("Liste amis");
        backgris1.add(listamistext, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        listamis.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listamis.setEnabled(false);
        listamis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listamisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listamis);

        backgris1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 230, 240));

        demandeamis.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        demandeamis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                demandeamisMouseClicked(evt);
            }
        });
        demandeamis.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                demandeamisValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(demandeamis);

        backgris1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 230, 240));

        demandemaistext.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        demandemaistext.setText("Demande amis");
        backgris1.add(demandemaistext, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        getContentPane().add(backgris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 300, 610));

        sportbutton3.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        sportbutton3.setForeground(new java.awt.Color(17, 149, 173));
        sportbutton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/padlock (1).png"))); // NOI18N
        sportbutton3.setText("Nom Sport");
        sportbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sportbutton3ActionPerformed(evt);
            }
        });
        getContentPane().add(sportbutton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 380, 280));

        sportbutton1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        sportbutton1.setForeground(new java.awt.Color(25, 149, 173));
        sportbutton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/padlock (1).png"))); // NOI18N
        sportbutton1.setText("Nom Sport");
        sportbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sportbutton1ActionPerformed(evt);
            }
        });
        getContentPane().add(sportbutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 380, 280));

        sportbutton2.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        sportbutton2.setForeground(new java.awt.Color(25, 149, 173));
        sportbutton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/padlock (1).png"))); // NOI18N
        sportbutton2.setText("Nom Sport");
        sportbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sportbutton2ActionPerformed(evt);
            }
        });
        getContentPane().add(sportbutton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 200, 380, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mesportsbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesportsbuttonActionPerformed
        ModifSport m = new ModifSport(this.u);
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mesportsbuttonActionPerformed

    private void rechamisbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechamisbuttonActionPerformed
        AjoutAmi a = new AjoutAmi(this.u);
        a.setVisible(true);
    }//GEN-LAST:event_rechamisbuttonActionPerformed

    private void exitCurseurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitCurseurMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitCurseurMouseClicked
    String str;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        //préciser le dossier ou se trouvent les images
        fileChooser.setCurrentDirectory(new File("C:\\Users\\HP\\Desktop\\RESSOURCES ADMIN\\Table avec images\\PHOTOS STAGIAIRES"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "png", "gif");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fichierselectionne = fileChooser.getSelectedFile();
            String path = fichierselectionne.getAbsolutePath();
            ImageIcon img = new ImageIcon(path);
            Image imgrec = img.getImage();
            Image nimage = imgrec.getScaledInstance(lphoto.getWidth(),
                    lphoto.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imgfinal = new ImageIcon(nimage);
            lphoto.setIcon(imgfinal);
            str = path;
            tpath.setText(str);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Aucun choix effectué");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tpathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpathActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tpath.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir une photo", "ERREUR", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Path path = Paths.get(tpath.getText());
            try {
                byte[] photoData = Files.readAllBytes(path);
                
                
                Session s = DBConnection.getSession();
                Transaction transaction = s.beginTransaction();
                User currentUser = (User) s.get(User.class, u.getId());
                currentUser.setPhotoProfil(photoData);
                s.update(currentUser);

                // Validez et terminez la transaction
                transaction.commit();

                // Fermez la session Hibernate
                s.close();

                // Affichez un message de succès
                JOptionPane.showMessageDialog(this, "Photo de profil mise à jour avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                tpath.setText("");
            } catch (IOException ex) {
                Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void sportbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sportbutton1ActionPerformed
        String buttonText = sportbutton1.getText();

        // Ouvrez l'interface correspondante en fonction du texte du bouton
        openSportInterface(buttonText);
    }//GEN-LAST:event_sportbutton1ActionPerformed

    private void demandeamisValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_demandeamisValueChanged

    }//GEN-LAST:event_demandeamisValueChanged

    private void sportbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sportbutton2ActionPerformed
        String buttonText = sportbutton2.getText();

        // Ouvrez l'interface correspondante en fonction du texte du bouton
        openSportInterface(buttonText);
    }//GEN-LAST:event_sportbutton2ActionPerformed

    private void sportbutton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sportbutton3ActionPerformed
        String buttonText = sportbutton3.getText();

        // Ouvrez l'interface correspondante en fonction du texte du bouton
        openSportInterface(buttonText);
    }//GEN-LAST:event_sportbutton3ActionPerformed

    private void listamisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listamisMouseClicked

    }//GEN-LAST:event_listamisMouseClicked

    private void demandeamisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_demandeamisMouseClicked
        if (evt.getClickCount() == 1) { // Vérifie si l'utilisateur a cliqué une fois
            int[] indicesSelectionnes = demandeamis.getSelectedIndices();
            for (int indice : indicesSelectionnes) {
                String pseudoSelectionne = demandeamis.getModel().getElementAt(indice);
                int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous accepter la demande d'ami de " + pseudoSelectionne + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choix == JOptionPane.YES_OPTION) {

                    Session s = DBConnection.getSession();
                    Transaction t = s.beginTransaction();
                    User d2 = User.getUserByPseudo(pseudoSelectionne);
                    User d = (User) s.get(User.class, u.getId());
                    Query query = s.createQuery("FROM DemandeAmi WHERE demandeur = :demandeur AND destinataire = :destinataire");
                    query.setParameter("demandeur", d2);
                    query.setParameter("destinataire", d);
                    DemandeAmi demandeAmi = (DemandeAmi) query.uniqueResult();
                    if (demandeAmi != null) {
                        // Mettre à jour le statut de la demande d'ami
                        demandeAmi.setStatut(StatutDemandeAmi.StatutDemandeAm.ACCEPTEE);

                        // Enregistrer les modifications dans la base de données
                        s.update(demandeAmi);

                        t.commit();
                        s.close();
                        JOptionPane.showMessageDialog(this, "Demande d'ami acceptee de " + pseudoSelectionne, "PerforMates", JOptionPane.INFORMATION_MESSAGE);
                        initListeAmi();
                        // Traitement si l'utilisateur a accepté la demande d'ami
//                    System.out.println("Demande d'ami acceptée pour " + pseudoSelectionne);
                    }

                    // Traitement si l'utilisateur a accepté la demande d'ami
//                    System.out.println("Demande d'ami acceptée pour " + pseudoSelectionne);
                } else {
                    // Traitement si l'utilisateur a refusé la demande d'amiSession s = DBConnection.getSession();
                    Session s = DBConnection.getSession();
//                    Transaction t = s.beginTransaction();
                    Transaction t = s.beginTransaction();
                    User d2 = User.getUserByPseudo(pseudoSelectionne);
                    User d = (User) s.get(User.class, u.getId());
                    Query query = s.createQuery("FROM DemandeAmi WHERE demandeur = :demandeur AND destinataire = :destinataire");
                    query.setParameter("demandeur", d2);
                    query.setParameter("destinataire", d);
                    DemandeAmi demandeAmi = (DemandeAmi) query.uniqueResult();
                    if (demandeAmi != null) {
                        // Mettre à jour le statut de la demande d'ami
                        demandeAmi.setStatut(StatutDemandeAmi.StatutDemandeAm.REFUSEE);
//                        s.delete(demandeAmi);

                        // Enregistrer les modifications dans la base de données
//                        s.update(demandeAmi);
                        t.commit();
                        s.close();
                        JOptionPane.showMessageDialog(this, "Demande d'ami refusee de " + pseudoSelectionne, "PerforMates", JOptionPane.INFORMATION_MESSAGE);
//                        System.out.println("Demande d'ami refusée pour " + pseudoSelectionne);
                        initListeAmi();
                    }
                }
            }
        }

    }//GEN-LAST:event_demandeamisMouseClicked

    private void rechamisbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechamisbutton1ActionPerformed
        if(JOptionPane.showConfirmDialog(this, "voulez vous vous deconnectez ", "ta", 2, JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION){
        this.dispose();
        Connexion c = new Connexion();
        c.setVisible(true);
        }
        
    }//GEN-LAST:event_rechamisbutton1ActionPerformed
    private void openSportInterface(String sportName) {
        // Ouvrez l'interface correspondante en fonction du nom du sport
        switch (sportName) {
            case "Course a pied":
                // Ouvrez l'interface pour le sport Course a pied
                Session sis = DBConnection.getSession();
                Transaction tttii = sis.beginTransaction();
                User utilisa = (User) sis.get(User.class, u.getId());
                RunningInterf courseAPiedInterface = new RunningInterf(utilisa);
                courseAPiedInterface.setVisible(true);
                tttii.commit();
                sis.close();
                break;
            case "Natation":
                // Ouvrez l'interface pour le sport Natation
                Session siss = DBConnection.getSession();
                Transaction ttti = siss.beginTransaction();
                User utilis = (User) siss.get(User.class, u.getId());
                NatationInterface natationInterface = new NatationInterface(utilis);
                natationInterface.setVisible(true);
                ttti.commit();
                siss.close();
                break;
            case "Cyclisme":
                // Ouvrez l'interface pour le sport Cyclisme
                Session session = DBConnection.getSession();
                Transaction transaction = session.beginTransaction();
                User utilisateur = (User) session.get(User.class, u.getId());
                CyclismeInterf cyclismeInterf = new CyclismeInterf(utilisateur);
                cyclismeInterf.setVisible(true);
                transaction.commit();
                session.close();
                break;

            case "Tennis":
                Session session1 = DBConnection.getSession();
                Transaction transaction1 = session1.beginTransaction();
                User utilisateur1 = (User) session1.get(User.class, u.getId());
                TennisInterface t = new TennisInterface(utilisateur1);
                t.setVisible(true);
                transaction1.commit();
                session1.close();
                break;

            case "Windsurf":

                Session s = DBConnection.getSession();
                Transaction ttt = s.beginTransaction();
                User utili = (User) s.get(User.class, u.getId());
                WindSurfInterface c = new WindSurfInterface(utili);
                c.setVisible(true);
                ttt.commit();
                s.close();
                break;
            case "Haltérophilie":
                Session si = DBConnection.getSession();
                Transaction i = si.beginTransaction();
                User q = (User) si.get(User.class, u.getId());
                HalterophilieInterface tv = new HalterophilieInterface(q);
                tv.setVisible(true);
                break;

            // Ajoutez des cas pour les autres sports si nécessaire
            default:
                // Gérez le cas où le sport n'est pas reconnu
                System.out.println("Sport non reconnu");
                break;
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
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backblue;
    private javax.swing.JPanel backgris1;
    private javax.swing.JList<String> demandeamis;
    private javax.swing.JLabel demandemaistext;
    private javax.swing.JLabel exitCurseur;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listamis;
    private javax.swing.JLabel listamistext;
    private javax.swing.JLabel lphoto;
    private javax.swing.JButton mesportsbutton;
    private javax.swing.JLabel nomprenom;
    private javax.swing.JButton rechamisbutton;
    private javax.swing.JButton rechamisbutton1;
    private javax.swing.JButton sportbutton1;
    private javax.swing.JButton sportbutton2;
    private javax.swing.JButton sportbutton3;
    private javax.swing.JTextField tpath;
    // End of variables declaration//GEN-END:variables
}
