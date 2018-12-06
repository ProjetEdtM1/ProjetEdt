package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOCours;
import fr.utln.projet.DAO.DAOProfesseur;
import fr.utln.projet.controleur.CoursControleur;
import fr.utln.projet.controleur.ProfesseurControleurAuth;
import fr.utln.projet.utilisateur.Cours;

import java.util.List;

public class CoursModele {
    private DAOCours daoCours;
    private CoursControleur coursControleur;

    public CoursModele(CoursControleur coursControleur){
        this.coursControleur = coursControleur;
        this.daoCours = new DAOCours();
    }

    public List<Cours> getCoursSemaineGroupe(String intituleGroupe){
        return this.daoCours.getCoursSemaineGroupe(intituleGroupe);
    }

    public List<Cours> getCoursSemaineProf(String login){
        return this.daoCours.getCoursSemaineProf(login);
    }
}
