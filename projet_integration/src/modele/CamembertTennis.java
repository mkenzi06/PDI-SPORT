/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author HP
 */
public class CamembertTennis extends JFrame{

    private JList<String> userComboBox;
    private DefaultCategoryDataset dataset;

    private DefaultPieDataset createDataset(User selectedUser, Date d) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Récupérer l'utilisateur de la session
            User user = (User) session.get(User.class, selectedUser.getId());
            if (user != null) {
                // Effectuer une requête pour récupérer la performance de natation pour la date spécifiée
                Query query = session.createQuery("FROM Performances WHERE user = :user AND date = :date");
                query.setParameter("user", user);
                query.setParameter("date", d);
                List<Performances> performances = query.list();

                // Si une performance de natation est trouvée, ajoutez-la au dataset
                int totalsets = 0;

                // Variables pour stocker les temps passés pour chaque style de nage
                int Sets = 0;
                

                // Calculer les temps passés pour chaque style de nage
                for (Performances performanc : performances) {
                    if (performanc.getSport().estTennis()) {
                        Tennis swimming = (Tennis) performanc.getSport();
                        totalsets += swimming.getNombresetsmatch();
                        
                        Sets += swimming.getNombreSetsGagnes();
                    }
                }
                double crawlPercentage = (double) Sets / totalsets * 100;
                
                double freeSwimmingPercentage = 100 - crawlPercentage;

                // Ajouter les valeurs au dataset
                dataset.setValue("sets gagnes", crawlPercentage);
                
                dataset.setValue("sets perdues", freeSwimmingPercentage);

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

    private void fillCyclistFriendsList(User user, JList<String> list, Date d) {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> cyclistFriends = getCyclistFriends(user, d);
        for (String friend : cyclistFriends) {
            model.addElement(friend);
        }
        list.setModel(model);
    }

    private boolean pratiqueNatation(User user) {

        for (Sport sport : user.getSportsPratiques()) {
            if (sport.estTennis()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPerformanceOnDate(User user, Date date) {
        Session session = DBConnection.getSession();
        try {
            Query query = session.createQuery("FROM Performances WHERE user = :user AND date = :date");
            query.setParameter("user", user);
            query.setParameter("date", date);
            List<Performances> performances = query.list();

            for (Performances performance : performances) {
                if (performance.getSport().estTennis()) {
                    return true; // Si au moins une performance est de type Natation, retourne true
                }
            }

            return false; // Si aucune performance de natation n'est trouvée, retourne false
        } finally {
            session.close();
        }
    }

    private List<String> getCyclistFriends(User user, Date date) {
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
                        if (pratiqueNatation(u1) && hasPerformanceOnDate(u1, date)) {
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
                        if (u2 != null && pratiqueNatation(u2) && u2.getPseudo() != null && hasPerformanceOnDate(u2, date)) {
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

    private DefaultPieDataset createDataset2(User user1, User user2, Date d) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Récupérer les utilisateurs de la session
            User dbUser1 = (User) session.get(User.class, user1.getId());
            User dbUser2 = (User) session.get(User.class, user2.getId());
            if (dbUser1 != null && dbUser2 != null) {
                // Effectuer une requête pour récupérer les performances de natation pour la date spécifiée
                Query query = session.createQuery("FROM Performances WHERE (user = :user1 OR user = :user2) AND date = :date");
                query.setParameter("user1", dbUser1);
                query.setParameter("user2", dbUser2);
                query.setParameter("date", d);
                List<Performances> performances = query.list();

                // Variables pour stocker les temps passés pour chaque style de nage pour les deux utilisateurs
                double user1CrawlTime = 0;
                
                double user2CrawlTime = 0;
                
                int totalTimeUser1 = 0;
                int totalTimeUser2 = 0;

                // Calculer les temps passés pour chaque style de nage pour les deux utilisateurs
                for (Performances performance : performances) {
                    if (performance.getSport().estTennis()) {
                        Tennis swimming = (Tennis) performance.getSport();
                        if (performance.getUser().equals(dbUser1)) {
                            user1CrawlTime += swimming.getNombreSetsGagnes();
//                            user1ButterflyTime += swimming.getPapillonTimePercentage();
                            totalTimeUser1 += swimming.getNombresetsmatch();
                        } else if (performance.getUser().equals(dbUser2)) {
                            user2CrawlTime += swimming.getNombreSetsGagnes();
                            
                            totalTimeUser2 += swimming.getNombresetsmatch();
                        }

                    }
                }
                double crawlPercentageUser1 = (double) user1CrawlTime / totalTimeUser1 * 100;
                
                double freeSwimmingPercentageUser1 = 100 - crawlPercentageUser1 ;
                double crawlPercentageUser2 = (double) user2CrawlTime / totalTimeUser2 * 100;
               
                double freeSwimmingPercentageUser2 = 100 - crawlPercentageUser2 ;
                // Ajouter les valeurs au dataset en les différenciant pour les deux utilisateurs
                dataset.setValue(user1.getPseudo() + " - Sets Gagne", crawlPercentageUser1);
                
                dataset.setValue(this.u.getPseudo() + " - Sets perdues", freeSwimmingPercentageUser1);
                dataset.setValue(user2.getPseudo() + " - Sets gagne", crawlPercentageUser2);
               
                dataset.setValue(user2.getPseudo() + " -  Sets perdues", freeSwimmingPercentageUser2);

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
    private Date d;

    public CamembertTennis() {

    }

    public CamembertTennis(final User u, final Date d) {
        setTitle("Performances Natation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(800, 600);
        this.u = u;
        this.d = d;

        userComboBox = new JList<>();
        fillCyclistFriendsList(u, userComboBox, d);

        // Créer un JPanel pour contenir les graphiques
        final JPanel chartPanelContainer = new JPanel(new BorderLayout());

        // Créer le graphique initial
        DefaultPieDataset dataset = createDataset(this.u, this.d);
        JFreeChart chart = ChartFactory.createPieChart(
                "Performances Tennis du " + d + "\t pour  " + u.getPseudo(), // Titre du graphique
                dataset, // Ensemble de données
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
                        DefaultPieDataset dataset2 = createDataset2(u, user, d); // Mettre à jour le dataset avec les deux utilisateurs
                        JFreeChart chart1 = ChartFactory.createPieChart(
                                "Performances Tennis\t " + u.getPseudo() + " vs\t " + user.getPseudo(), // Titre du graphique

                                dataset2, // Ensemble de données
                                // Orientation du graphique (horizontal)
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
                DefaultPieDataset dataset = createDataset(u, d);
                JFreeChart chart = ChartFactory.createPieChart(
                        "Performances Tennis du " + d + "\t pour  " + u.getPseudo(), // Titre du graphique
                        dataset, // Ensemble de données
                        true, // Inclure la légende (nous ne l'incluons pas pour éviter un warning)
                        true, // Inclure les tooltips
                        true // Inclure les URLs
                );
                ChartPanel initialChartPanel = new ChartPanel(chart); // Création du panel de chart initial
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
        add(userComboBox, BorderLayout.EAST);
        getContentPane().add(chartPanelContainer, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        setLocationRelativeTo(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CamembertTennis h = new CamembertTennis();
                h.setVisible(true);
            }
        });
    }
}
