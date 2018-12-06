package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOCours;
import fr.utln.projet.controleur.CoursControleur;
import fr.utln.projet.utilisateur.Cours;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CoursModele {
    private DAOCours daoCours;
    private CoursControleur coursControleur;

    public CoursModele(CoursControleur coursControleur){
        this.coursControleur = coursControleur;
        this.daoCours = new DAOCours();
    }

    public void ajouterCours(String groupe, String idProf, String module, Date date, Time hDebCours, Time hFinCours, String numeroSalle) {
        daoCours.ajouterCours(groupe, idProf, module, date, hDebCours, hFinCours, numeroSalle);
    }

    public List<Cours> getCoursSemaineGroupe(String intituleGroupe){
        return this.daoCours.getCoursSemaineGroupe(intituleGroupe);
    }

    public List<Cours> getCoursSemaineProf(String login){
        return this.daoCours.getCoursSemaineProf(login);
    }
}
