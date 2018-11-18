package fr.utln.projet;

import fr.utln.projet.DAO.DAOEtudiant;
import fr.utln.projet.DAO.DAOProfesseur;
import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.modele.ProfesseurModele;
import fr.utln.projet.module.Module;
import fr.utln.projet.utilisateur.Professeur;
import fr.utln.projet.utilisateur.Utilisateur;
import fr.utln.projet.vue.AuthentificationVue;
import fr.utln.projet.vue.EtudiantVue;
import fr.utln.projet.modele.ModeleEtudiant;
import fr.utln.projet.vue.ModuleVUE;
import fr.utln.projet.vue.ProfesseurVue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws SQLException {

        //ModeleEtudiant modeleEtudiant = new ModeleEtudiant();
        //EtudiantVue etudiantVue = new EtudiantVue(modeleEtudiant);
        ProfesseurModele professeurModele = new ProfesseurModele();
        ProfesseurVue professeurVue = new ProfesseurVue(professeurModele);

    }}
