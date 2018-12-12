package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOCours;
import fr.utln.projet.controleur.AjoutCoursControleur;
import fr.utln.projet.utilisateur.Cours;
import fr.utln.projet.utilisateur.Professeur;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CoursModele {
    // private AjoutCoursControleur ajoutCoursControleur;


    List<String> groupes = new ArrayList();
    List<Professeur> professeurs = new ArrayList();


    private DAOCours dao = new DAOCours();

    /*public CoursModele(AjoutCoursControleur ajoutCoursControleur){
        this.ajoutCoursControleur = ajoutCoursControleur;
    }*/

    public void ajouterCours(String groupe, String idProf, String module, Date date, Time hDebCours, Time hFinCours, String numeroSalle) {
        dao.ajouterCours(groupe, idProf, module, date, hDebCours, hFinCours, numeroSalle);
    }

    public List<Cours> getCoursSemaineGroupe(String intituleGroupe){
        return this.dao.getCoursSemaineGroupe(intituleGroupe);
    }

    public List<Cours> getCoursSemaineProf(String login){
        return this.dao.getCoursSemaineProf(login);
    }

    /**
     * Methode qui retourne la list des groupe possible a donner a un étudiant
     *
     * @return list de gourpe
     *
     * @author CLAIN Cyril
     */
    public List<String> getListGroupe() {
        groupes = dao.creationListGroupe();
        return groupes;
    }

    /**
     * Methode de récupération de liste des professeur
     *
     * @return list de professeur
     *
     * @author CLAIN Cyril
     */
    public List<Professeur> getProfesseurs() {
        professeurs = dao.creationListProfesseur();
        return professeurs;

    }
}
