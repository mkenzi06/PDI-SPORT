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
 * Cette classe représente une fenêtre d'application pour afficher les performances de cyclisme.
 * Elle hérite de la classe JFrame.
 */
public class TestJfreeChTime extends JFrame {

    private JList<String> userComboBox;
    private DefaultCategoryDataset dataset;
    private boolean isDistance;

    private TestJfreeChTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
                    // Utilisez isDistance pour décider quel attribut extraire des performances
                    if (p.getSport().estCyclisme()) {
                        double value = isDistance ? ((Cyclisme) p.getSport()).getDistanceTotaleParcourue() : ((Cyclisme) p.getSport()).getTempsPerformance();
                        Date date = p.getDate();
                        String formattedDate = dateFormat.format(date);
                        dataset.addValue(value, isDistance ? "Distance Parcourue" : "Temps Parcouru", formattedDate);
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

    private void fillCyclistFriendsList(User user, JList<String> list) {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> cyclistFriends = getCyclistFriends(user);
        for (String friend : cyclistFriends) {
            model.addElement(friend);
        }
        list.setModel(model);
    }

    private List<String> getCyclistFriends(User user) {
        List<String> cyclistFriends = new ArrayList<>();
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
                        if (pratiqueCyclisme(u1)) {
                            cyclistFriends.add(u1.getPseudo());
                        }
                    }
                }
            }

            if (demandesEnvoyees != null) {
                for (DemandeAmi demande : demandesEnvoyees) {
                    if (demande != null && demande.getStatut() == StatutDemandeAmi.StatutDemandeAm.ACCEPTEE) {
                        User destinataire = demande.getDestinataire();
                        User u2 = (User) session.get(User.class, destinataire.getId());
                        if (u2 != null && pratiqueCyclisme(u2) && u2.getPseudo() != null) {
                            cyclistFriends.add(destinataire.getPseudo());
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

        return cyclistFriends;
    }

    /**
     * Un ensemble de données par défaut pour les graphiques de catégories.
     * Cette classe implémente l'interface CategoryDataset et fournit une implémentation de base pour stocker et accéder aux données des graphiques de catégories.
     * Les données sont organisées en catégories et en séries, où chaque série représente une catégorie et chaque catégorie contient des valeurs.
     * Les valeurs peuvent être des nombres ou des objets.
     * 
     * Cette classe est utilisée pour créer des ensembles de données pour les graphiques de catégories utilisés dans les bibliothèques de graphiques, telles que JFreeChart.
     * 
     * Pour créer un ensemble de données de catégories, vous pouvez utiliser le constructeur par défaut ou spécifier les catégories et les séries lors de la création de l'objet.
     * Vous pouvez ensuite ajouter des valeurs aux catégories en utilisant les méthodes addValue() ou setValue().
     * 
     * Cette classe est généralement utilisée en conjonction avec des graphiques de catégories pour afficher des données sous forme de graphiques à barres, de graphiques à lignes, de graphiques à aires, etc.
     * 
     * Le dataset sera affiché dans un graphique qui contient les performances de deux utilisateurs.
     * 
     * @param user1 L'utilisateur 1 pour lequel les performances doivent être affichées  
     * @param user2 L'utilisateur 2 pour lequel les performances doivent être affichées 
     */
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
                    if (p.getSport().estCyclisme()) {
                        double value = isDistance ? ((Cyclisme) p.getSport()).getDistanceTotaleParcourue() : ((Cyclisme) p.getSport()).getTempsPerformance();
                        Date date = p.getDate();
                        String formattedDate = dateFormat.format(date);
                        if (performancesUser1.contains(p)) {
                            dataset.addValue(value, user1.getPseudo() + (isDistance ? " - Distance Parcourue" : " - Temps Parcouru"), formattedDate);
                        } else if (performancesUser2.contains(p)) {
                            dataset.addValue(value, user2.getPseudo() + (isDistance ? " - Distance Parcourue" : " - Temps Parcouru"), formattedDate);
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

    private void fillComboBox(User user, JList<String> userComboBox1) {
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
                        if (pratiqueCyclisme(u1)) {
//                        userComboBox.addItem(u1.getPseudo());
                        }
                    }
                }
            }

            if (demandesEnvoyees != null) {
                for (DemandeAmi demande : demandesEnvoyees) {
                    if (demande != null && demande.getStatut() == StatutDemandeAmi.StatutDemandeAm.ACCEPTEE) {

                        User destinataire = demande.getDestinataire();
                        User u2 = (User) session.get(User.class, destinataire.getId());
                        if (u2 != null && pratiqueCyclisme(u2) && u2.getPseudo() != null) {
//                        userComboBox.addItem(destinataire.getPseudo());
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
    }

    private boolean pratiqueCyclisme(User user) {

        for (Sport sport : user.getSportsPratiques()) {
            if (sport.estCyclisme()) {
                return true;
            }
        }
        return false;
    }

    private User u;

    public TestJfreeChTime(final User u, final boolean isDistance) {
        setTitle("Performances Cyclisme");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 600);
        this.u = u;
        this.isDistance = isDistance;
        JLabel userLabel = new JLabel("Comparez avec votre ami "); // Création du JLabel
        userComboBox = new JList<>();
        fillCyclistFriendsList(u, userComboBox);

        // Créer un JPanel pour contenir les graphiques
        final JPanel chartPanelContainer = new JPanel(new BorderLayout());

        // Créer le graphique initial
        DefaultCategoryDataset dataset = createDataset(this.u);
        JFreeChart chart = ChartFactory.createBarChart(
                "Performances Cyclisme", // Titre du graphique
                "Date", // Libellé de l'axe des catégories (horizontal)
                isDistance ? "Distance Parcourue(km)" : "Temps Performance(min)", // Libellé de l'axe des valeurs (vertical)
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
                        JFreeChart chart1 = ChartFactory.createBarChart("Performances Cyclisme", // Titre du graphique
                                "Date", // Libellé de l'axe des catégories (horizontal)
                                isDistance ? "Distance Parcourue(km)" : "Temps Performance(min)", // Libellé de l'axe des valeurs (vertical)
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
                        "Performances Cyclisme", // Titre du graphique
                        "Date", // Libellé de l'axe des catégories (horizontal)
                        isDistance ? "Distance Parcourue(km)" : "Temps Performance(min)", // Libellé de l'axe des valeurs (vertical)
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
        // Création du bouton pour calculer la vitesse

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
                TestJfreeChTime histogram = new TestJfreeChTime();
                histogram.setVisible(true);
            }
        });
    }

}
