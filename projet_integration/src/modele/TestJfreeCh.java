package modele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modele.DBConnection;
import modele.User;
import modele.Performances;
import modele.Cyclisme;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.primefaces.component.chart.Chart;

public class TestJfreeCh extends JFrame {

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

                for (Performances p : sortedPerformances) { // Utilisez sortedPerformances au lieu de performances
                    if (p.getSport() instanceof Cyclisme) {
                        Date date = p.getDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = dateFormat.format(date);
                        double distance = ((Cyclisme) p.getSport()).getDistanceTotaleParcourue();
                        dataset.addValue(distance, "Distance Parcourue", formattedDate);
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
private DefaultCategoryDataset createDataset(User user1, User user2) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    Session session = DBConnection.getSession();
    Transaction transaction = session.beginTransaction();

    try {
        User loadedUser1 = (User) session.get(User.class, user1.getId());
        User loadedUser2 = (User) session.get(User.class, user2.getId());

        if (loadedUser1 != null && loadedUser2 != null) {
            List<Performances> performancesUser1 = loadedUser1.getPerformances();
            List<Performances> performancesUser2 = loadedUser2.getPerformances();
            
            // Fusionner les listes de performances
            List<Performances> mergedPerformances = new ArrayList<>();
            mergedPerformances.addAll(performancesUser1);
            mergedPerformances.addAll(performancesUser2);
            
            // Trier les performances fusionnées par date
            Collections.sort(mergedPerformances, new Comparator<Performances>() {
                @Override
                public int compare(Performances p1, Performances p2) {
                    return p1.getDate().compareTo(p2.getDate());
                }
            });

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (Performances p : mergedPerformances) {
                if (p.getSport() instanceof Cyclisme) {
                    Date date = p.getDate();
                    String formattedDate = dateFormat.format(date);
                    double distance = ((Cyclisme) p.getSport()).getDistanceTotaleParcourue();
                    // Utiliser le pseudo de l'utilisateur pour distinguer les données dans le graphique
                    if (performancesUser1.contains(p)) {
                        dataset.addValue(distance, user1.getPseudo() + " - Distance Parcourue", formattedDate);
                    } else if (performancesUser2.contains(p)) {
                        dataset.addValue(distance, user2.getPseudo() + " - Distance Parcourue", formattedDate);
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

    private DefaultCategoryDataset createDataset2(User user1, User user2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            User loadedUser1 = (User) session.get(User.class, user1.getId());
            User loadedUser2 = (User) session.get(User.class, user2.getId());

            if (loadedUser1 != null && loadedUser2 != null) {
                List<Performances> performancesUser1 = loadedUser1.getPerformances();
                List<Performances> sortedPerformancesUser1 = new ArrayList<>(performancesUser1);
                Collections.sort(sortedPerformancesUser1, new Comparator<Performances>() {
                    @Override
                    public int compare(Performances p1, Performances p2) {
                        return p2.getDate().compareTo(p1.getDate());
                    }
                });

                List<Performances> performancesUser2 = loadedUser2.getPerformances();
                List<Performances> sortedPerformancesUser2 = new ArrayList<>(performancesUser2);
                Collections.sort(sortedPerformancesUser2, new Comparator<Performances>() {
                    @Override
                    public int compare(Performances p1, Performances p2) {
                        return p2.getDate().compareTo(p1.getDate());
                    }
                });

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                for (Performances p : sortedPerformancesUser1) {
                    if (p.getSport() instanceof Cyclisme) {
                        Date date = p.getDate();
                        String formattedDate = dateFormat.format(date);
                        double distance = ((Cyclisme) p.getSport()).getDistanceTotaleParcourue();
                        dataset.addValue(distance, user1.getPseudo() + " - Distance Parcourue", formattedDate);
                    }
                }

                for (Performances p : sortedPerformancesUser2) {
                    if (p.getSport() instanceof Cyclisme) {
                        Date date = p.getDate();
                        String formattedDate = dateFormat.format(date);
                        double distance = ((Cyclisme) p.getSport()).getDistanceTotaleParcourue();
                        dataset.addValue(distance, user2.getPseudo() + " - Distance Parcourue", formattedDate);
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
//                        System.out.println("modele.TestJfreeCh.fillComboBox()" + u1.getPseudo());
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
            if (sport instanceof Cyclisme) {
                return true;
            }
        }
        return false;
    }

    private User u;

    public TestJfreeCh(final User u) {
        setTitle("Performances Cyclisme");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 600);
        this.u = u;

        userComboBox = new JList<>();
        fillCyclistFriendsList(u, userComboBox);

        // Créer un JPanel pour contenir les graphiques
        final JPanel chartPanelContainer = new JPanel(new BorderLayout());

        // Créer le graphique initial
        DefaultCategoryDataset dataset = createDataset(this.u);
        JFreeChart chart = ChartFactory.createBarChart(
                "Performances Cyclisme", // Titre du graphique
                "Date", // Libellé de l'axe des catégories (horizontal)
                "Distance Parcourue(km)", // Libellé de l'axe des valeurs (vertical)
                dataset, // Ensemble de données
                PlotOrientation.VERTICAL,// Orientation du graphique (horizontal)
                false, // Inclure la légende (nous ne l'incluons pas pour éviter un warning)
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
                    DefaultCategoryDataset dataset2 = createDataset(u, user); // Mettre à jour le dataset avec les deux utilisateurs
                    JFreeChart chart1 = ChartFactory.createBarChart("Performances Cyclisme", // Titre du graphique
                            "Date", // Libellé de l'axe des catégories (horizontal)
                            "Distance Parcourue(km)", // Libellé de l'axe des valeurs (vertical)
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
                        "Distance Parcourue(km)", // Libellé de l'axe des valeurs (vertical)
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

        add(userComboBox, BorderLayout.EAST);
        getContentPane().add(chartPanelContainer, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.NORTH); // Ajout du panel de bouton au nord de la fenêtre
    }


    private TestJfreeCh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//private void updateChart(User selectedUser) {
////        dataset.clear();
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        Session session = DBConnection.getSession();
//        Transaction transaction = session.beginTransaction();
//
//        try {
//            User user = (User) session.get(User.class, selectedUser.getId());
//            if (user != null) {
//                List<Performances> performances = user.getPerformances();
//                List<Performances> sortedPerformances = new ArrayList<>(performances);
//
//                // Triez les performances par date dans l'ordre chronologique inverse
//                Collections.sort(sortedPerformances, new Comparator<Performances>() {
//                    @Override
//                    public int compare(Performances p1, Performances p2) {
//                        return p1.getDate().compareTo(p2.getDate());
//                    }
//                });
//
//                for (Performances p : sortedPerformances) { // Utilisez sortedPerformances au lieu de performances
//                    if (p.getSport() instanceof Cyclisme) {
//                        Date date = p.getDate();
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                        String formattedDate = dateFormat.format(date);
//                        double distance = ((Cyclisme) p.getSport()).getDistanceTotaleParcourue();
//                        dataset.addValue(distance, "Distance Parcourue", formattedDate);
//                    }
//                }
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            transaction.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        revalidate();
//    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestJfreeCh histogram = new TestJfreeCh();
                histogram.setVisible(true);
            }
        });
    }
}
