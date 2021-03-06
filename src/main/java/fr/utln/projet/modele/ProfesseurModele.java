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

    public void updateProfesseur() {
    }

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
     * Methode de récuperationd 'un professeur en fonctiond e son idprofesseur
     *
     * @param idprofesseur
     *
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
     *  Methode d'ajout des Professeur
     *
     * @param nom
     * @param prenom
     * @param login
     * @param mdp
     *
     * @author      CLAIN Cyril
     */
    public void persisteProfesseur (String nom, String prenom, String login, String mdp) {

        dao.persisteProfesseur(nom, prenom,login,mdp);

        Professeur professeur = new Professeur();
        String idprofesseur = Integer.toString(professeurs.size() + 1);
        professeur.setIdprofesseur(idprofesseur);
        professeur.setNom(nom);
        professeur.setPrenom(prenom);
        professeur.setMdp(mdp);
        professeur.setLogin(login);
        professeurs.add(professeur);


        //On previent les observateurs du changement
        setChanged();
        notifyObservers(ModeleProfesseurEvent.PROFESSEUR);
    }


    /**
     *
     *  Methode de supression  des professeur
     *
     * @param professeur
     *
     * @return boolean
     *
     *  @author      CLAIN Cyril
     */
    public boolean deleteProfesseur(Professeur professeur) throws SQLException {
        if (dao.deleteProfesseur(professeur.getIdprofesseur())) {
            professeurs.remove(professeur);

            //On previent les observateurs du changement
            setChanged();
            notifyObservers(ModeleProfesseurEvent.PROFESSEUR);

            // je ne retourne pas dao.deleteProfesseur(professeur.getIdprofesseur() afin de ne pas faire 2 acces en bd
            return true;
        }
        return false;
    }

    public void modifierProfesseur(String id, String nom, String prenom){

        dao.updateProfesseur(id,nom,prenom);

        for(Professeur p : professeurs){

            if ((p.getIdprofesseur().compareTo(id))==0) {

                p.setPrenom(prenom);
                p.setNom(nom);

            }}


        setChanged();
        notifyObservers(ModeleProfesseurEvent.PROFESSEUR);

    }

}
