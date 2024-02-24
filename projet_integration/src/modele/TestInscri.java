/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import java.util.*;
import java.awt.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class TestInscri {
    
    public static void main(String[] args) {
        Session session = DBConnection.getSession();
        Transaction persistTransaction = session.beginTransaction();
        User utilisateur = new User("Nom1", "Prenom2", "Pseudo", "MotDePasse");
        Cyclisme cyclisme = new Cyclisme();
        cyclisme.setDistanceTotaleParcourue(100.0);
        cyclisme.setTempsPerformance(10.5);
        session.persist(cyclisme);
        utilisateur.getSportsPratiques().add(cyclisme);

        CourseAPied cap = new CourseAPied();
        cap.setDistanceParcourue(100.0);
        cap.setNombreSeancesEntrainement(3);
        cap.setTempsPerformance(20.5);
        session.persist(cap);
        utilisateur.getSportsPratiques().add(cap);
        session.persist(utilisateur);
        persistTransaction.commit();
        
        session.close();
        
    }
    
}
