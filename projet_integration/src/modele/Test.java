/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author HP
 */
public class Test {

    public static boolean checkFriendRequest(User demandeur, User destinataire) {
        Session session = DBConnection.getSession();
        try {
            Criteria criteria = session.createCriteria(DemandeAmi.class);
            criteria.add(Restrictions.eq("demandeur", demandeur));
            criteria.add(Restrictions.eq("destinataire", destinataire));
            criteria.add(Restrictions.eq("statut", StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE));

            return criteria.list().isEmpty();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void main(String[] args) {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        String username = "wassim123";
        String password = "A123456*";
        User userExists = UserDataConnect.getUserByUsernameAndPassword(username, password);
        try {
            if (userExists != null) {

                System.out.println("Bienvenue\n" + userExists.getNom());
//           User newUser = new User("Nom1", "Prenom2", "Pseudo", "password", new Date());
                User utilisateur = (User) session.get(User.class, 2L);
                System.out.println("modele.Test.main()" + utilisateur.getId());
//            userExists.envoyerDemandeAmi(utilisateur);
//            userExists.getAmis().add(utilisateur);
                try {
                    if (checkFriendRequest(userExists, utilisateur)) {
                        DemandeAmi e = new DemandeAmi();
                        e.setDemandeur(userExists);
                        e.setDestinataire(utilisateur);
                        e.setStatut(StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE);
                        
                        session.persist(e);
                        JOptionPane.showMessageDialog(null, "demande envoye avec succees");
                        transaction.commit();
                        session.close();
                    } else {
                        System.out.println("Une demande d'ami similaire existe déjà.");

                    }
                }catch(Exception e){
                    if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
                    
                }
                

                }else {
                System.out.println("IDENTIFIANT(S) INCORRECT ! ");

            }
            }catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        }
    }
