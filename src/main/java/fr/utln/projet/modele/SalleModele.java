package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOSalle;
import fr.utln.projet.utilisateur.Salle;

import java.sql.SQLException;
import java.util.*;

/*
 * Nom de classe : SalleModele
 *
 * Description   : Modele salle (MVC) contient les méthode a appliquer
 *
 * Version       : 1.0
 *
 * Date          : 23/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class SalleModele extends Observable  {

    List<Salle> salles = new ArrayList();

    private DAOSalle dao = new DAOSalle();

    public enum ModeleSalleEvent {Salle}


    /**
     * Methode de récupération de liste des salles
     *
     * @return list de salle
     *
     * @author CLAIN Cyril
     */

    public List<Salle> getSalles() {
        salles = dao.creationlistsalle();
        return salles;

    }
/** Methode de récuperationd d'une salle en fonctiond de son id
 *
 * @param idSalle
 *
 * @return salle
 *
 * @author CLAIN Cyril
 */
public Salle getSalle(final Integer idSalle){

    for (Salle salle  : salles)
        if (salle.getNumerosalle() == idSalle) return salle;
    return null;

}

    /**
     *  Methode d'ajout des étudiants
     *
     * @param idSalle
     *
     * @author      CLAIN Cyril
     */
    public void persisteSalle (String idSalle) {
        int numSalle = Integer.valueOf(idSalle);
        try {
            dao.persisteSalle(numSalle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Salle salle = new Salle();
        salle.setNumerosalle(numSalle);
        salles.add(salle);

        //On previent les observateurs du changement
        setChanged();
        notifyObservers(ModeleSalleEvent.Salle);
    }
    /**
     *
     *  Methode de supression  des étudiants
     *
     * @param salle
     *
     *  @author      CLAIN Cyril
     */
    public void deleteSalle(Salle salle) throws SQLException {
        dao.deleteSalle(salle.getNumerosalle());
        salles.remove(salle);

        //On previent les observateurs du changement
        setChanged();
        notifyObservers(ModeleSalleEvent.Salle);
    }

}