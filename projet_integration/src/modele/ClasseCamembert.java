/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.hibernate.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author HP
 */
public class ClasseCamembert extends JFrame {

    private User u;
    private Date d;
    private JList<String> userComboBox;

    private void fillCyclistFriendsList(User user, JList<String> list) {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> cyclistFriends = getSwimmerFriends(user, d);
        for (String friend : cyclistFriends) {
            model.addElement(friend);
        }
        list.setModel(model);
    }

    public ClasseCamembert(final User user, final Date selectedDate) {
        this.u = user;
        this.d = selectedDate;
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(this);
        // Création du panel pour le graphique en camembert
        JPanel chartPanel = createChartPanel(selectedDate);
        chartPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Ajouter de l'espacement autour du graphique en camembert
        add(chartPanel, BorderLayout.CENTER);

        // Création de la liste des utilisateurs de natation
//        JList<String> userList = createUserList(selectedDate);
        userComboBox = new JList<>();
        fillCyclistFriendsList(this.u, userComboBox);
        JScrollPane scrollPane = new JScrollPane(userComboBox);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Ajouter de l'espacement autour de la liste des utilisateurs de natation
        add(scrollPane, BorderLayout.EAST);

        JButton resetButton = new JButton("Réinitialiser");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        userComboBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Vérifie si l'événement est en train de se terminer
                    String selectedUser = userComboBox.getSelectedValue();
                    JPanel chartPanel = createChartPanel1(d, user, User.getUserByPseudo(selectedUser));
                    chartPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Ajouter de l'espacement autour du graphique en camembert

                    // Supprimer tous les composants du JFrame sauf le premier
                    for (Component component : getContentPane().getComponents()) {
                        if (component instanceof JPanel && component != userComboBox) {
                            remove(component);
                            break; // Sortir de la boucle après la suppression du graphique
                        }
                    }
                    add(chartPanel, BorderLayout.WEST); // Ajouter le nouveau graphique
                    setSize(800, 400);
                    revalidate(); // Rafraîchir le JFrame pour afficher les modifications
                    repaint();
                }
            }
        });

    }

    private void reset() {
        // Réinitialiser la fenêtre en enlevant tous les composants sauf le graphique initial et la liste
        Component chartPanel = getContentPane().getComponent(0);
        Component userListPanel = getContentPane().getComponent(1);
//        Component l = getContentPane().getComponent(2);
        getContentPane().removeAll();
        JPanel chartPanel2 = createChartPanel(this.d);
        chartPanel2.setBorder(new EmptyBorder(10, 10, 10, 10)); // Ajouter de l'espacement autour du graphique en camembert
        add(chartPanel2, BorderLayout.CENTER);
        add(chartPanel, BorderLayout.WEST);
        add(userListPanel, BorderLayout.EAST);
        revalidate();
        repaint();
    }

    private void afficherCamembert(String selectedUser, Date date) {
        // Récupérer les performances de l'utilisateur sélectionné pour la date spécifiée
        Session session = DBConnection.getSession();
        try {
            Query query = session.createQuery("FROM Performances WHERE user.pseudo = :pseudo AND date = :date");
            query.setParameter("pseudo", selectedUser);
            query.setParameter("date", date);
            List<Performances> performances = query.list();

            // Créer un ensemble de données pour le graphique en camembert
            DefaultPieDataset dataset = new DefaultPieDataset();

            // Récupérer le temps total passé par l'utilisateur
            int totalTime = 0;

            // Variables pour stocker les temps passés pour chaque style de nage
            int crawlTime = 0;
            int butterflyTime = 0;

            // Calculer les temps passés pour chaque style de nage
            for (Performances performance : performances) {
                Natation swimming = (Natation) performance.getSport();
                crawlTime += swimming.getCrawlTimePercentage();
                butterflyTime += swimming.getPapillonTimePercentage();
                totalTime += swimming.getTotalSessionTime();
            }

            // Calculer les pourcentages pour chaque style de nage
            double crawlPercentage = (double) crawlTime / totalTime * 100;
            double butterflyPercentage = (double) butterflyTime / totalTime * 100;
            double freeSwimmingPercentage = 100 - crawlPercentage - butterflyPercentage;

            // Ajouter les valeurs au dataset
            dataset.setValue("Crawl", crawlPercentage);
            dataset.setValue("Butterfly", butterflyPercentage);
            dataset.setValue("Free Swimming", freeSwimmingPercentage);

            // Créer le graphique en camembert
            JFreeChart chart = ChartFactory.createPieChart(
                    "Performance Camembert for " + selectedUser + " on " + date.toString(), // Titre du graphique
                    dataset, // Ensemble de données
                    true, // Légende
                    true, // Info-bulle
                    false // URLs
            );

            // Mettre à jour le contenu du graphique dans le panel existant
            ChartPanel chartPanel = new ChartPanel(chart);
            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.add(chartPanel, BorderLayout.CENTER);
            this.setContentPane(chartPanel); // Mettre à jour le contenu du panel du graphique
            this.setTitle("Graphique en camembert pour " + selectedUser + " on " + date.toString());

            this.revalidate(); // Rafraîchir le JFrame pour afficher les modifications
        } finally {
            session.close(); // Fermer la session Hibernate après utilisation
        }
    }

    private JPanel createChartPanel(Date date) {
        // Créer une requête pour récupérer les performances de l'utilisateur pour la date spécifique
        Session session = DBConnection.getSession();
        try {
            Query query = session.createQuery("FROM Performances WHERE user = :user AND date = :date");
            query.setParameter("user", u);
            query.setParameter("date", date);
            List<Performances> performances = query.list();

            // Créer un ensemble de données pour le graphique en camembert
            DefaultPieDataset dataset = new DefaultPieDataset();

            // Récupérer le temps total passé par l'utilisateur
            int totalTime = 0;

            // Variables pour stocker les temps passés pour chaque style de nage
            int crawlTime = 0;
            int butterflyTime = 0;

            // Calculer les temps passés pour chaque style de nage
            for (Performances performance : performances) {
                if (performance.getSport() instanceof Natation) {
                    Natation swimming = (Natation) performance.getSport();
                    crawlTime += swimming.getCrawlTimePercentage();
                    butterflyTime += swimming.getPapillonTimePercentage();
                    totalTime += swimming.getTotalSessionTime();
                }
            }

            // Calculer les pourcentages pour chaque style de nage
            double crawlPercentage = (double) crawlTime / totalTime * 100;
            double butterflyPercentage = (double) butterflyTime / totalTime * 100;
            double freeSwimmingPercentage = 100 - crawlPercentage - butterflyPercentage;

            // Ajouter les valeurs au dataset
            dataset.setValue("Crawl", crawlPercentage);
            dataset.setValue("Butterfly", butterflyPercentage);
            dataset.setValue("Free Swimming", freeSwimmingPercentage);

            // Créer le graphique en camembert
            JFreeChart chart = ChartFactory.createPieChart(
                    "Performance Camembert for " + date.toString(), // Titre du graphique
                    dataset, // Ensemble de données
                    true, // Légende
                    true, // Info-bulle
                    false // URLs
            );

            // Afficher le graphique dans un panel
            ChartPanel chartPanel = new ChartPanel(chart);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(chartPanel, BorderLayout.CENTER);
            return panel;
        } finally {
            session.close();
        }
    }

    private JPanel createChartPanel1(Date date, User user1, User user2) {
        // Créer une requête pour récupérer les performances de natation des deux utilisateurs pour la date spécifique
        Session session = DBConnection.getSession();
        try {
            // Récupérer les performances de natation de l'utilisateur 1
            Query queryUser1 = session.createQuery("FROM Performances WHERE user = :user AND date = :date");
            queryUser1.setParameter("user", user1);
            queryUser1.setParameter("date", date);

            List<Performances> performancesUser1 = queryUser1.list();

            // Récupérer les performances de natation de l'utilisateur 2
            Query queryUser2 = session.createQuery("FROM Performances WHERE user = :user AND date = :date");
            queryUser2.setParameter("user", user2);
            queryUser2.setParameter("date", date);

            List<Performances> performancesUser2 = queryUser2.list();
            double crawlTimeUser1 = 0.0;
            double butterflyTimeUser1 = 0.0;
            int totalTimeUser1 = 0;

            double crawlTimeUser2 = 0.0;
            double butterflyTimeUser2 = 0.0;
            int totalTimeUser2 = 0;

            // Créer un ensemble de données pour le graphique en camembert
            DefaultPieDataset dataset = new DefaultPieDataset();

            // Calculer les pourcentages pour chaque utilisateur
            for (Performances performance : performancesUser1) {
                if (performance.getSport() instanceof Natation) {
                    Natation swimming = (Natation) performance.getSport();
                    crawlTimeUser1 += swimming.getCrawlTimePercentage();
                    butterflyTimeUser1 += swimming.getPapillonTimePercentage();
                    totalTimeUser1 += swimming.getTotalSessionTime();
                }
            }
            double crawlPercentageUser1 = (double) crawlTimeUser1 / totalTimeUser1 * 100;
            double butterflyPercentageUser1 = (double) butterflyTimeUser1 / totalTimeUser1 * 100;
            double freeSwimmingPercentageUser1 = 100 - crawlPercentageUser1 - butterflyPercentageUser1;

            // Parcourir les performances de l'utilisateur 2
            for (Performances performance : performancesUser2) {
                if (performance.getSport() instanceof Natation) {
                    Natation swimming = (Natation) performance.getSport();
                    crawlTimeUser2 += swimming.getCrawlTimePercentage();
                    butterflyTimeUser2 += swimming.getPapillonTimePercentage();
                    totalTimeUser2 += swimming.getTotalSessionTime();
                }
            }
            double crawlPercentageUser2 = (double) crawlTimeUser2 / totalTimeUser2 * 100;
            double butterflyPercentageUser2 = (double) butterflyTimeUser2 / totalTimeUser1 * 100;
            double freeSwimmingPercentageUser2 = 100 - crawlPercentageUser2 - butterflyPercentageUser2;

            // Ajouter les valeurs au dataset avec distinction entre les utilisateurs
            dataset.setValue(user1.getPseudo() + " - Crawl", crawlPercentageUser1);
            dataset.setValue(user1.getPseudo() + " - Butterfly", butterflyPercentageUser1);
            dataset.setValue(user1.getPseudo() + " - Free Swimming", freeSwimmingPercentageUser1);
            dataset.setValue(user2.getPseudo() + " - Crawl", crawlPercentageUser2);
            dataset.setValue(user2.getPseudo() + " - Butterfly", butterflyPercentageUser2);
            dataset.setValue(user2.getPseudo() + " - Free Swimming", freeSwimmingPercentageUser2);

            // Créer le graphique en camembert
            JFreeChart chart = ChartFactory.createPieChart(
                    "Performances Natation for " + user1.getPseudo() + " & " + user2.getPseudo() + " on " + date.toString(), // Titre du graphique
                    dataset, // Ensemble de données
                    true, // Légende
                    true, // Info-bulle
                    false // URLs
            );

            // Afficher le graphique dans un panel
            ChartPanel chartPanel = new ChartPanel(chart);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(chartPanel, BorderLayout.CENTER);
            return panel;
        } finally {
            session.close();
        }
    }

    private List<String> getSwimmerFriends(User user, Date date) {
        List<String> swimmerFriends = new ArrayList<>();
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
                        if (pratiqueNatation(u1) && hasPerformanceOnDate(u1, date)) {
                            swimmerFriends.add(u1.getPseudo());
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
                            swimmerFriends.add(destinataire.getPseudo());
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

        return swimmerFriends;
    }

    private boolean pratiqueNatation(User user) {
        for (Sport sport : user.getSportsPratiques()) {
            if (sport instanceof Natation) {
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
            return !performances.isEmpty();
        } finally {
            session.close();
        }
    }

}
