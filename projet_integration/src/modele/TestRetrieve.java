/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author HP
 */
public class TestRetrieve {
    public static void main(String[] args) {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Retrieve a specific User by ID (replace 1L with the actual user ID)
            User utilisateur = (User) session.get(User.class, 5L);

            // Check if the user exists
            if (utilisateur != null) {
                // Access user details
                System.out.println("User Details:");
                System.out.println("ID: " + utilisateur.getId());
                System.out.println("Nom: " + utilisateur.getNom());
                System.out.println("Prenom: " + utilisateur.getPrenom());
                System.out.println("Pseudo: " + utilisateur.getPseudo());
                System.out.println("Date de Naissance: " + utilisateur.getDateNaissance());

                // Access associated sports
                List<Sport> sportsPratiques = utilisateur.getSportsPratiques();
                System.out.println("le user "+ utilisateur.getPrenom()+"pratique comme sport");
                for (Sport sport : sportsPratiques){
                    System.out.println(sport.getNom());
                }
                System.out.println("\nSports pratiques:");
                for (Sport sport : sportsPratiques) {
                    if (sport instanceof Cyclisme) {
                        Cyclisme cyclisme = (Cyclisme) sport;
                        System.out.println("modele.TestRetrieve.main()");
//                        System.out.println("Cyclisme - Distance: " + cyclisme.getDistanceTotaleParcourue() +
//                                ", Temps de Performance: " + cyclisme.getTempsPerformance());
                    } if(sport instanceof CourseAPied) {
                        CourseAPied cap = (CourseAPied) sport;
                        System.out.println("modele.TestRetrieve.main()");
                        
//                        System.out.println("Course a pied - Distance: " + cap.getDistanceParcourue() +
//                                ", Temps de Performance: " + cap.getTempsPerformance()+"nbr de seances : "+cap.getNombreSeancesEntrainement());
                        
                    }
                }
            } else {
                System.out.println("User not found!");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
}
