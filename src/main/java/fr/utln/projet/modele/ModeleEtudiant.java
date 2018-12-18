package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOEtudiant;
import fr.utln.projet.utilisateur.Etudiant;

import java.sql.SQLException;
import java.util.*;

/*
 * Nom de classe : ModeleEtudiant
 *
 * Description   : Modele Etudiant (MVC) contient les méthode a appliquer
 *
 * Version       : 1.1
 *
 * Date          : 12/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class ModeleEtudiant extends Observable {
    List<Etudiant> etudiants = new ArrayList();
    List<String> groupes = new ArrayList();

    private DAOEtudiant dao = new DAOEtudiant();

    public enum ModeleEtudiantEvent {ETUDIANT}


    public List<Etudiant> getEtudiant() {
        etudiants = dao.creationListEtudiant();
        return etudiants;
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
     * Methode de récuperationd 'un etudiant en fonctiond e son numEtud
     *
     * @param Numetud
     * @return etudiant
     *
     * @author CLAIN Cyril
     */
    public Etudiant getEtudiant(final String Numetud){

        // pour chaque objet de type Etudiant dans la liste etudiants test si numetud = objetcourant.numetud
        //si c'est le cas je retourne l'étudiant en question
        //sinon je retourn null
        // cette fonction regarde si un étudiant de ma liste a le meme numetud que celui saisie

        for (Etudiant etudiant : etudiants)
            if (etudiant.Numetud == Numetud) return etudiant;
        return null;

    }

    /**
     *  Methode d'ajout des étudiants
     *
     * @param numetud
     * @param nom
     * @param prenom
     * @param mdp
     * @param groupe
     *
     * @author      CLAIN Cyril
     */
    public boolean persisteEtudiant(String numetud, String nom, String prenom, String mdp, String groupe) {
        try {
            if (groupes.contains(groupe)){
                if (dao.persisteEtudiant(numetud,nom, prenom, mdp, groupe))
                {
                    Etudiant etudiant = new Etudiant();
                    etudiant.setNumetud(numetud);
                    etudiant.setNom(nom);
                    etudiant.setPrenom(prenom);
                    etudiant.setMdp(mdp);
                    etudiant.setGroupe(groupe);
                    etudiants.add(etudiant);

                    //On previent les observateurs du changement
                    setChanged();
                    notifyObservers(ModeleEtudiantEvent.ETUDIANT);
                }
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return groupes.contains(groupe);
    }

    /**
     *
     *  Methode de supression  des étudiants
     *
     * @param etudiant
     *
     *  @author CLAIN Cyril
     */
    public void deleteEtudiant(Etudiant etudiant) throws SQLException {
        dao.deleteEtudiant(etudiant.getNumetud());
        etudiants.remove(etudiant);

        //On previent les observateurs du changement
        setChanged();
        notifyObservers(ModeleEtudiantEvent.ETUDIANT);
    }

    public void modifiereEtudiant(String numetud, String nom, String prenom, String groupe){

        dao.updateEtudiant(numetud,nom,prenom,groupe);
        for(Etudiant e : etudiants){

            if ((e.getNumetud().compareTo(numetud))==0) {

                e.setPrenom(prenom);
                e.setNom(nom);
                e.setGroupe(groupe);

            }}

        setChanged();
        notifyObservers(ModeleEtudiantEvent.ETUDIANT);



    }

    /**
     *
     * methode de recherche du num étudiant dans la bd afin de ne pas ajouter un Etudiant avec la clef primaire d'un autre
     *
     * @param numetud
     * @return
     *
     * @author CLAIN Cyril
     */
    public boolean numetudDansBase(String numetud) {
        List<String> numEtudlist = new ArrayList();
        for(Etudiant e : etudiants){
            numEtudlist.add(e.getNumetud());
            }
        return numEtudlist.contains(numetud);

    }

}