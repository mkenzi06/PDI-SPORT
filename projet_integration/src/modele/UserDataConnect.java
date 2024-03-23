/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import org.hibernate.*;
import javax.persistence.*;
import org.hibernate.Session;
import modele.DBConnection;
import org.hibernate.Query;
import java.util.*;
import org.hibernate.cfg.annotations.Version;
import static vues.Inscription.hashPassword;
//import org.hibernate.query.Query;

/**
 *
 * @author HP
 */
public class UserDataConnect {

    DBConnection dbc = new DBConnection();
//    @SuppressWarnings("rawtypes")

    public static User getUserByUsernameAndPassword(String username, String password) {

        Session session = DBConnection.getSession();
        Transaction read = session.beginTransaction();
        String hashedPassword = hashPassword(password);

        Query readq = session.createQuery("from User where pseudo = :username and password = :password");
        readq.setParameter("username", username);
        readq.setParameter("password", hashedPassword);
        List res = readq.list();
        User u1;

        if (!res.isEmpty()) {
            u1 = (User) res.get(0);
            read.commit();
            session.close();
            return u1;
        }else{
            session.close();
        }
            
        
        return null;
    }

    public static void saveUser(User user) {
        Session session = DBConnection.getSession();
        Transaction read = session.beginTransaction();

        try {
//            session.save(user);
            session.persist(user);
            read.commit();
        } catch (Exception e) {
            if (read != null) {
                read.rollback();
            }
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

}
