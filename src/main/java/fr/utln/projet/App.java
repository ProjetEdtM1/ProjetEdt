package fr.utln.projet;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.utilisateur.Utilisateur;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Connexion con = new Connexion();
        con.connect();

//        Utilisateur utilisateur = new Utilisateur.Builder("toto", "Jean").telephone("0605020301").build();
//        System.out.println(utilisateur.nom + " " + utilisateur.prenom + " " + utilisateur.telephone);
    }
}
