package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOCours;
import fr.utln.projet.DAO.DAOSalle;
import fr.utln.projet.DAO.ModuleDAO;
import fr.utln.projet.utilisateur.Cours;
import fr.utln.projet.utilisateur.Professeur;
import fr.utln.projet.utilisateur.Salle;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CoursModele {
    List<String> groupes = new ArrayList();
    List<Salle> salles = new ArrayList();
    ArrayList<String> modules= new ArrayList();
    List<Professeur> professeurs = new ArrayList();


    private DAOCours dao = new DAOCours();
    private DAOSalle daoSalle = new DAOSalle();
    private ModuleDAO daoModule = new ModuleDAO();

    /**
     * Methode pour ajouter un cours en BDD
     *
     * @param groupe
     * @param idProf
     * @param module
     * @param date
     * @param hDebCours
     * @param hFinCours
     * @param numeroSalle
     * @return
     */

    public boolean ajouterCours(String groupe, String idProf, String module, Date date, Time hDebCours, Time hFinCours, String numeroSalle) {

        return dao.testAjoutCours(groupe, idProf, module, date, hDebCours, hFinCours, numeroSalle);
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

    public List<Salle> getListSalle() {
        salles = daoSalle.creationlistsalle();
        return salles;
    }

    public ArrayList<String> getListeModule() {
        modules = daoModule.listeIntituleModule();
        return modules;
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
