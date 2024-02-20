/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;
import java.awt.*;


/**
 *
 * @author HP
 */
public class TestAmis {

    public static void main(String Args[]) {
        Session session = DBConnection.getSession();
        Transaction transaction = session.beginTransaction();
        User utilisateur = (User) session.get(User.class, 1L);
        ArrayList<DemandeAmi> u1;
//        u1 = (ArrayList<DemandeAmi>) utilisateur.getDemandesEnvoyees();
        u1 = new ArrayList<>(utilisateur.getDemandesEnvoyees());
        if(u1.isEmpty()){
            System.out.println("Tu as pas d'amis wassim tu es nulle");
            
        }else{
            
            for (int i = 0; i < u1.size(); i++) {
                if(u1.get(i).getStatut()==StatutDemandeAmi.StatutDemandeAm.ACCEPTEE){
                    System.out.println("AMIS "+u1.get(i).getDestinataire().getNom());
                }else{
                    System.out.println("ta pas d'amis");
                }

            }
        }


    }

}
