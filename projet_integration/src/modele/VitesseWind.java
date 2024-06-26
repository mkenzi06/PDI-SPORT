/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author HP
 */
/**
 * Cette classe représente une fenêtre d'affichage des performances de windsurf.
 * Elle hérite de la classe JFrame.
 */
public class VitesseWind extends JFrame {

    private JList<String> userComboBox;
    private DefaultCategoryDataset dataset;

    private DefaultCategoryDataset createDataset(User selectedUser) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = (User) session.get(User.class, selectedUser.getId());
            if (user != null) {
                List<Performances> performances = user.getPerformances();
                List<Performances> sortedPerformances = new ArrayList<>(performances);

                // Triez les performances par date dans l'ordre chronologique inverse
                Collections.sort(sortedPerformances, new Comparator<Performances>() {
                    @Override
                    public int compare(Performances p1, Performances p2) {
                        return p1.getDate().compareTo(p2.getDate());
                    }
                });

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                for (Performances p : sortedPerformances) {
                    if (p.getSport().estWindsurf()) {
                        // Utilisez isDistance pour décider quel attribut extraire des performances
                        double distance = ((WindSurf) p.getSport()).getDistanceParcourue();
                        double temps = ((WindSurf) p.getSport()).getTempsPerformance();
                        double speed = distance / (temps / 60);
                        Date date = p.getDate();
                        String formattedDate = dateFormat.format(date);
                        dataset.addValue(speed, "Vitesse (km/h)", formattedDate);
                    }
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dataset;
    }

    private void fillWindFriendsList(User user, JList<String> list) {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> windFriends = getWindFriends(user);
        for (String friend : windFriends) {
            model.addElement(friend);
        }
        list.setModel(model);
    }

    private boolean pratiqueWind(User user) {

        for (Sport sport : user.getSportsPratiques()) {
            if (sport.estWindsurf()) {
                return true;
            }
        }
        return false;
    }

    private List<String> getWindFriends(User user) {
        List<String> windFriends = new ArrayList<>();
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User us = (User) session.get(User.class, user.getId());
        try {
            List<DemandeAmi> demandesRecues = us.getDemandesRecues();
            List<DemandeAmi> demandesEnvoyees = us.getDemandesEnvoyees();

            if (demandesRecues != null) {
                for (DemandeAmi demande : demandesRecues) {
                    if (demande != null && demande.getStatut() == StatutDemandeAmi.StatutDemandeAm.ACCEPTEE) {
                        User demandeur = demande.getDemandeur();
                        User u1 = (User) session.get(User.class, demandeur.getId());
                        System.out.println("modele.TestJfreeCh.fillComboBox()" + u1.getPseudo());
                        if (pratiqueWind(u1)) {
                            windFriends.add(u1.getPseudo());
                        }
                    }
                }
            }

            if (demandesEnvoyees != null) {
                for (DemandeAmi demande : demandesEnvoyees) {
                    if (demande != null && demande.getStatut() == StatutDemandeAmi.StatutDemandeAm.ACCEPTEE) {
                        User destinataire = demande.getDestinataire();
                        User u2 = (User) session.get(User.class, destinataire.getId());
                        if (u2 != null && pratiqueWind(u2) && u2.getPseudo() != null) {
                            windFriends.add(destinataire.getPseudo());
                        }
                    }
                }
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return windFriends;
    }

    private DefaultCategoryDataset createDataset2(User user1, User user2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            User loadedUser1 = (User) session.get(User.class, user1.getId());
            User loadedUser2 = (User) session.get(User.class, user2.getId());

            if (loadedUser1 != null && loadedUser2 != null) {
                List<Performances> performancesUser1 = loadedUser1.getPerformances();
                List<Performances> performancesUser2 = loadedUser2.getPerformances();

                List<Performances> mergedPerformances = new ArrayList<>();
                mergedPerformances.addAll(performancesUser1);
                mergedPerformances.addAll(performancesUser2);

                Collections.sort(mergedPerformances, new Comparator<Performances>() {
                    @Override
                    public int compare(Performances p1, Performances p2) {
                        return p1.getDate().compareTo(p2.getDate());
                    }
                });

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                for (Performances p : mergedPerformances) {
                    if (p.getSport().estWindsurf()) {
                        double distance = ((WindSurf) p.getSport()).getDistanceParcourue();
                        double temps = ((WindSurf) p.getSport()).getTempsPerformance();
                        double speed = distance / (temps / 60);
                        Date date = p.getDate();
                        String formattedDate = dateFormat.format(date);
                        if (performancesUser1.contains(p)) {
                            dataset.addValue(speed, user1.getPseudo() + "Vitesse(km/h)", formattedDate);
                        } else if (performancesUser2.contains(p)) {
                            dataset.addValue(speed, user2.getPseudo() + "Vitesse(km/h)", formattedDate);
                        }
                    }
                }
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return dataset;
    }
    private User u;

    public VitesseWind() {

    }

    public VitesseWind(final User u) {
        setTitle("Performances WindSurf");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 600);
        this.u = u;
        JLabel userLabel = new JLabel("Comparez avec votre ami "); // Création du JLabel
        userComboBox = new JList<>();
        fillWindFriendsList(u, userComboBox);

        // Créer un JPanel pour contenir les graphiques
        final JPanel chartPanelContainer = new JPanel(new BorderLayout());

        // Créer le graphique initial
        DefaultCategoryDataset dataset = createDataset(this.u);
        JFreeChart chart = ChartFactory.createBarChart(
                "Performances Windsurf", // Titre du graphique
                "Date", // Libellé de l'axe des catégories (horizontal)
                "Vitesse", // Libellé de l'axe des valeurs (vertical)
                dataset, // Ensemble de données
                PlotOrientation.VERTICAL,// Orientation du graphique (horizontal)
                true, // Inclure la légende (nous ne l'incluons pas pour éviter un warning)
                true, // Inclure les tooltips
                true // Inclure les URLs
        );

        final ChartPanel chartPanel = new ChartPanel(chart);

        // Ajouter le graphique initial au conteneur
        chartPanelContainer.add(chartPanel, BorderLayout.CENTER);

        userComboBox.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    String selectedUser = userComboBox.getSelectedValue();
                    if (selectedUser != null) {
                        User us = User.getUserByPseudo(selectedUser);
                        Session session = DBConnection.getSession();
                        Transaction transaction = session.beginTransaction();
                        User user = (User) session.get(User.class, us.getId());
                        DefaultCategoryDataset dataset2 = createDataset2(u, user); // Mettre à jour le dataset avec les deux utilisateurs
                        JFreeChart chart1 = ChartFactory.createBarChart("Performances WindSurf", // Titre du graphique
                                "Date", // Libellé de l'axe des catégories (horizontal)
                                "Vitesse", // Libellé de l'axe des valeurs (vertical)
                                dataset2, // Ensemble de données
                                PlotOrientation.VERTICAL,// Orientation du graphique (horizontal)
                                true, // Inclure la légende (nous ne l'incluons pas pour éviter un warning)
                                true, // Inclure les tooltips
                                true // Inclure les URLs
                        );

                        ChartPanel chartPan = new ChartPanel(chart1);
                        chartPanelContainer.removeAll();
                        chartPanelContainer.add(chartPan, BorderLayout.CENTER);
                        chartPanelContainer.revalidate();
                        chartPanelContainer.repaint();
                    }
                }
            }
        });

        JButton resetButton = new JButton("Réinitialiser"); // Création du bouton de réinitialisation
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Recréer le graphique initial
                DefaultCategoryDataset initialDataset = createDataset(u);
                JFreeChart initialChart = ChartFactory.createBarChart(
                        "Performances Windsurf", // Titre du graphique
                        "Date", // Libellé de l'axe des catégories (horizontal)
                        "Vitesse", // Libellé de l'axe des valeurs (vertical)
                        initialDataset, // Ensemble de données initial
                        PlotOrientation.VERTICAL,// Orientation du graphique (horizontal)
                        true, // Inclure la légende
                        true, // Inclure les tooltips
                        true // Inclure les URLs
                );
                ChartPanel initialChartPanel = new ChartPanel(initialChart); // Création du panel de chart initial
                chartPanelContainer.removeAll(); // Suppression du graphique actuel du conteneur
                chartPanelContainer.add(initialChartPanel, BorderLayout.CENTER); // Ajout du nouveau graphique au conteneur
                chartPanelContainer.revalidate(); // Actualiser l'affichage
                chartPanelContainer.repaint();
                userComboBox.clearSelection(); // Réinitialiser la sélection de la JList

            }
        });

        JPanel buttonPanel = new JPanel(); // Création du panel pour le bouton de réinitialisation
        buttonPanel.add(resetButton); // Ajout du bouton de réinitialisation au panel

// Ajout du panel de bouton de calcul de vitesse au nord de la fenêtre
//        getContentPane().add(calculateSpeedButtonPanel, BorderLayout.NORTH);
        //add(userComboBox, BorderLayout.EAST);
        // Créer un JPanel pour contenir le JLabel et le JList
        JPanel userListPanel = new JPanel(new BorderLayout());
        userListPanel.add(userLabel, BorderLayout.NORTH); // Ajouter le JLabel au JPanel
        userListPanel.add(userComboBox, BorderLayout.CENTER); // Ajouter le JList au JPanel

// Maintenant, vous pouvez ajouter userListPanel au lieu de userComboBox directement
// add(userComboBox, BorderLayout.EAST);
        add(userListPanel, BorderLayout.EAST); // Ajout du JPanel au conteneur principal

        getContentPane().add(chartPanelContainer, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        // Ajout du panel de bouton au nord de la fenêtre
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VitesseCyclisme histogram = new VitesseCyclisme();
                histogram.setVisible(true);
            }
        });
    }
}
