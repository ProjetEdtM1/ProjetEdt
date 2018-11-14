package fr.utln.projet;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.module.Module;
import fr.utln.projet.utilisateur.Utilisateur;
import fr.utln.projet.vue.AuthentificationVue;
import fr.utln.projet.vue.EtudiantVue;
import fr.utln.projet.modele.ModeleEtudiant;

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

        ModeleEtudiant modeleEtudiant = new ModeleEtudiant();
        EtudiantVue etudiantVue = new EtudiantVue(modeleEtudiant);
        //AuthentificationVue authentificationVue = new AuthentificationVue();

    }
}
