/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HP
 */
public class Main {

    public static boolean checkFriendReques(User demandeur, User destinataire) {
        Session session = DBConnection.getSession();
        Transaction t = session.beginTransaction();
        boolean isPending = false;
        String hql = "SELECT d FROM DemandeAmi d WHERE d.demandeur = :demandeur AND d.destinataire = :destinataire AND d.statut = :statut";
        Query query = session.createQuery(hql);
        query.setParameter("demandeur", demandeur);
        query.setParameter("destinataire", destinataire);
        query.setParameter("statut", StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE);
        List resultList = query.list();
        isPending = resultList.isEmpty();
//        try {
//            Criteria criteria = session.createCriteria(DemandeAmi.class);
//            criteria.add(Restrictions.eq("demandeur", demandeur));
//            criteria.add(Restrictions.eq("destinataire", destinataire));
//            criteria.add(Restrictions.eq("statut", StatutDemandeAmi.StatutDemandeAm.EN_ATTENTE));
//
//            return criteria.list().isEmpty();
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
        return isPending;
    }

    public static void main(String[] args) {
        Session s = DBConnection.getSession();
        Transaction transaction = s.beginTransaction();
        User utilisateur = (User) s.get(User.class, 6L);
        User utilisateur2 = (User) s.get(User.class, 2L);
        if (checkFriendReques(utilisateur, utilisateur2)) {
            System.out.println("vrai");
            DemandeAmi d = new DemandeAmi();
            d.setDemandeur(utilisateur);
            d.setDestinataire(utilisateur2);
            d.setStatut(StatutDemandeAmi.StatutDemandeAm.ACCEPTEE);
            utilisateur.getDemandesEnvoyees().add(d);
            s.persist(d);
            transaction.commit();
            s.close();

        } else {
            System.out.println("invitation exsiste deja !");
        }

    }

}
