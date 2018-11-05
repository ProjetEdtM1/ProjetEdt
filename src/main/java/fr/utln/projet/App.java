package fr.utln.projet;

import fr.utln.projet.bdd.Connexion;

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
    }
}
