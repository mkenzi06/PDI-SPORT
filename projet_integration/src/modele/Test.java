/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author HP
 */
public class Test {
    public static void main(String[] args){
                
        String username = "wassim123";
        String password = "A123456*";
        User userExists = UserDataConnect.getUserByUsernameAndPassword(username, password);
        if(userExists != null){
            System.out.println("Bienvenue\n"+ userExists.getNom());
        }else{
            System.out.println("IDENTIFIANT(S) INCORRECT ! ");
            
        }
     

    }
}
