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
    private DAOEtudiant dao = new DAOEtudiant();

    public List<Etudiant> getEtudiant() {
        etudiants = dao.creationListEtudiant();
        return etudiants;
    }

    public enum ModeleEtudiantEvent {ETUDIANT}

    public Etudiant getEtudiant(final String Numetud){
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
    public void ajouterEtudiant(String numetud, String nom, String prenom,String mdp, String groupe) {
        try {

            dao.setEtudiant(numetud,nom, prenom, mdp, groupe);

            Etudiant etudiant = new Etudiant();
            etudiant.setNumetud(numetud);
            etudiant.setNom(nom);
            etudiant.setPrenom(prenom);
            etudiant.setMdp(mdp);
            etudiant.setGroupe(groupe);
            etudiants.add(etudiant);
           ListIterator listIterator = etudiants.listIterator();
            while( listIterator.hasNext()){
                Object o = listIterator.next();
                System.out.println("iterator : "+o);
            }

            //On previent les observateurs du changement
            setChanged();
            notifyObservers(ModeleEtudiantEvent.ETUDIANT);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     *  Methode de supression  des étudiants
     *
     * @param etudiant
     *
     *  @author      CLAIN Cyril
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
        Etudiant etudiant = new Etudiant();
        etudiant.setNumetud(numetud);
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setGroupe(groupe);

        for(Etudiant e : etudiants){

            if ((e.getNumetud().compareTo(numetud))==0) {
                System.out.println("je suis dans if ");
                etudiants.remove(e);
                etudiants.add(etudiant);
            }}

        setChanged();
        notifyObservers(ModeleEtudiantEvent.ETUDIANT);



    }

}