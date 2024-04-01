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
 * @author kenzi
 */
/**
 * Cette classe représente une connexion aux données utilisateur.
 * Elle permet de récupérer un utilisateur en fonction de son nom d'utilisateur et de son mot de passe,
 * ainsi que de sauvegarder un utilisateur dans la base de données.
 */
public class UserDataConnect {

    DBConnection dbc = new DBConnection();

    /**
     * Récupère un utilisateur en fonction de son nom d'utilisateur et de son mot de passe.
     *
     * @param username Le nom d'utilisateur de l'utilisateur recherché.
     * @param password Le mot de passe de l'utilisateur recherché.
     * @return L'utilisateur correspondant aux informations fournies, ou null si aucun utilisateur n'est trouvé.
     */
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
        } else {
            session.close();
        }

        return null;
    }

    /**
     * Sauvegarde un utilisateur dans la base de données.
     *
     * @param user L'utilisateur à sauvegarder.
     */
    public static void saveUser(User user) {
        Session session = DBConnection.getSession();
        Transaction read = session.beginTransaction();

        try {
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
