package fr.utln.projet;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.module.Module;
import fr.utln.projet.utilisateur.Utilisateur;
import fr.utln.projet.vue.AuthentificationVue;
import fr.utln.projet.vue.EtudiantVue;
import fr.utln.projet.modele.ModeleEtudiant;
import fr.utln.projet.vue.ModuleVUE;

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

        ModeleEtudiant modeleEtudiant = new ModeleEtudiant();
        System.out.println(modeleEtudiant.getEtudiant());
        EtudiantVue etudiantVue = new EtudiantVue(modeleEtudiant);
        AuthentificationVue authentificationVue = new AuthentificationVue();

        ModuleModele moduleModele = new ModuleModele();
        new ModuleVUE(moduleModele);
//        Utilisateur utilisateur = new Utilisateur.Builder("toto", "Jean").telephone("0605020301").build();
//        System.out.println(utilisateur.nom + " " + utilisateur.prenom + " " + utilisateur.telephone);
    }
}
