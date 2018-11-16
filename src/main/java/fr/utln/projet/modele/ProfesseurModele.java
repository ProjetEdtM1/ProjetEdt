package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOProfesseur;
import fr.utln.projet.utilisateur.Professeur;

import java.sql.SQLException;
import java.util.*;

/*
 * Nom de classe : ProfesseurModele
 *
 * Description   : Modele professeur (MVC) contient les méthode a appliquer
 *
 * Version       : 1.0
 *
 * Date          : 16/11/2018
 *
 * Copyright     : CLAIN Cyril
 */


public class ProfesseurModele extends Observable {

    List<Professeur> professeurs = new ArrayList();

    private DAOProfesseur dao = new DAOProfesseur();

    public enum ModeleProfesseurEvent {PROFESSEUR}


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

    /**
     * Methode de récuperationd 'un etudiant en fonctiond e son numEtud
     *
     * @param idprofesseur
     * @return idprofesseur
     *
     * @author CLAIN Cyril
     */
    public Professeur getProfesseur(final String idprofesseur){


        for (Professeur professeur  : professeurs)
            if (professeur.idprofesseur == idprofesseur) return professeur;
        return null;

    }

    /**
     *  Methode d'ajout des étudiants
     *
     * @param idprofesseur
     * @param nom
     * @param prenom
     * @param login
     * @param mdp
     *
     * @author      CLAIN Cyril
     */
    public void persisteProfesseur (String idprofesseur, String nom, String prenom, String login, String mdp) {

        dao.persisteProfesseur(nom, prenom, login, mdp);

        Professeur etudiant = new Professeur();
        etudiant.setIdprofesseur(idprofesseur);
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setMdp(mdp);
        etudiant.setLogin(login);
        professeurs.add(etudiant);
        ListIterator listIterator = professeurs.listIterator();
        while( listIterator.hasNext()){
            Object o = listIterator.next();
            System.out.println("iterator : "+o);
        }

        //On previent les observateurs du changement
        setChanged();
        notifyObservers(ModeleProfesseurEvent.PROFESSEUR);
    }

}
