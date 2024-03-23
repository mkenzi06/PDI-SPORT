package modele;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import javax.swing.*;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;

public class Test {

    public static void main(String[] args) {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();
        User u1 = (User) s.get(User.class, 1L);
        List<DemandeAmi> d = u1.getDemandesEnvoyees();
        
        String hql = "SELECT d.demandeur.id FROM DemandeAmi d WHERE d.destinataire = :user AND d.statut = :statut";
        Query query = s.createQuery(hql);
        query.setParameter("user", u1);
        query.setParameter("statut", StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE);

        List<Long> userIds = query.list();
        for (Long userId : userIds) {
            System.out.println("User ID: " + userId);
        }
//        String sportName = "Tennis";
//        Criteria criteria = s.createCriteria(User.class)
//                .createAlias("sportsPratiques", "s")
//                .add(Restrictions.eq("s.class", Cyclisme.class));
//        Criteria criteria = s.createCriteria(DemandeAmi.class)
//                .createAlias("destinataire", "d") // Créer une jointure avec la classe User et l'alias "d" pour accéder au destinataire
//                .add(Restrictions.eq("d", u1)) // Ajouter une restriction pour que le destinataire soit égal à l'utilisateur u1
//                .add(Restrictions.eq("statut", StatutDemandeAmi.StatutDemandeAm.ACCEPTEE));  // Accéder à l'ID du destinataire via l'alias "d"
//        List<DemandeAmi> users = criteria.list();
//        for (DemandeAmi user : users) {
//            System.out.println("Nom: " + user.getDemandeur());
//            System.out.println("Prénom: " + user.getDestinataire());
//            // Ajoutez d'autres informations de l'utilisateur que vous souhaitez afficher
//        }

//        String hql = "SELECT u FROM User u JOIN u.sportsPratiques s WHERE s.class = :sportClassName";
//
//        Query query = s.createQuery(hql);
//        query.setParameter("sportClassName", Cyclisme.class.getName());
//        List<User> users = query.list();
//        String hql = "SELECT u FROM User u JOIN u.sportsPratiques s WHERE TYPE(s) = Cyclisme";
//
//        Query query = s.createQuery(hql);
//        List<User> users = query.getResultList();
//
//        User u3 = (User) usersPratiquantCyclisme.get(0);
//        System.out.println("miaou" + users.size());
//       Cyclisme c = new Cyclisme();
//       c.setDistanceTotaleParcourue(1200);
//       s.persist(c);
//       
//       List<Sport> sportsPratiques = u1.getSportsPratiques();
//       sportsPratiques.add(c);
//        for (int i = 0; i < 3; i++) {
//       Performances p = new Performances();
//       p.setUser(u1);
//       p.setSport(c);
//       // Créer une date différente pour chaque performance
//       Date datePerformance = new Date(System.currentTimeMillis() + i * 24 * 60 * 60 * 1000); // Ajoute un jour à chaque itération
//       p.setDate(datePerformance);
//       s.persist(p);
//       u1.getPerformances().add(p);
////       c.getPerformances().add(p);
//   }
        t.commit();
        s.close();

    }
}
