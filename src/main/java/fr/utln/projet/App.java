package fr.utln.projet;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.module.Module;
import fr.utln.projet.utilisateur.Utilisateur;

import java.sql.ResultSet;
import java.sql.Statement;

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
        Module module = new Module.Builder("math").build();
        //Modulevue modulevue = new Modulevue(module);
//        Utilisateur utilisateur = new Utilisateur.Builder("toto", "Jean").telephone("0605020301").build();
//        System.out.println(utilisateur.nom + " " + utilisateur.prenom + " " + utilisateur.telephone);
    }
}
