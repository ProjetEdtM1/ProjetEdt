package fr.utln.projet.modele;


import fr.utln.projet.DAO.DAOMateriel;
import fr.utln.projet.utilisateur.Materiel;
import fr.utln.projet.utilisateur.Salle;

import java.sql.SQLException;
import java.util.*;

/*
 * Nom de classe : MaterielModele
 *
 * Description   : Modele materiel (MVC) contient les méthode a appliquer
 *
 * Version       : 1.0
 *
 * Date          : 24/11/2018
 *
 * Copyright     : CLAIN Cyril
 */
public class MaterielModele  extends Observable  {

    List<Materiel> materiels = new ArrayList();

    private DAOMateriel dao = new DAOMateriel();

    public enum ModeleMaterielEvent {MATERIEL_EVENT}


    /**
     * Methode de récupération de liste des materiel
     *
     * @return list de materiel
     *
     * @author CLAIN Cyril
     */

    public List<Materiel> getMateriel() {
        materiels = dao.creationlistMateriel();
        return materiels;

    }
    /** Methode de récuperationd d'une materiel en fonctiond de son id
     *
     * @param idMateriel
     *
     * @return materiel
     *
     * @author CLAIN Cyril
     */
    public Materiel getMateriel(final Integer idMateriel){

        for (Materiel materiel  : materiels)
            if (materiel.getIdMateriel() == idMateriel) return materiel;
        return null;

    }

    /**
     *  Methode d'ajout des étudiants
     *
     * @param idMateriel
     *
     * @param nomMateriel
     *
     * @author      CLAIN Cyril
     */
    public boolean persisteMateriel(String idMateriel, String nomMateriel) {

        boolean resultat = false;

        int intidMateriel = Integer.parseInt(idMateriel.trim());

        Materiel materiel = new Materiel();
        materiel.setIdMateriel(intidMateriel);

        if (!(materiels.contains(materiel))){
            try {
                if(dao.persisteMateriel(intidMateriel,nomMateriel)){
                    materiels.add(materiel);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            resultat = true;
        }

        //On previent les observateurs du changement

        setChanged();
        notifyObservers(ModeleMaterielEvent.MATERIEL_EVENT);
        return resultat;
    }

    /**
     *
     *  Methode de supression  des étudiants
     *
     * @param materiel
     *
     *  @author      CLAIN Cyril
     */
    public void deleteMateriel(Materiel materiel) throws SQLException {
        dao.deleteMateriel(materiel.getIdMateriel());
        materiels.remove(materiel);

        //On previent les observateurs du changement
        setChanged();
        notifyObservers(ModeleMaterielEvent.MATERIEL_EVENT);
    }

}