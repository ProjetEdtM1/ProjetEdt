package fr.utln.projet.modele;

import fr.utln.projet.DAO.ReserverSalleDAO;

import java.sql.Date;
import java.sql.Time;

public class ReserverSalleModele {
    private ReserverSalleDAO reserverSalleDAO = new ReserverSalleDAO();

    public void ajouterReservationSalle(String numeroSalle, int idProf, Date dateReservation, Time heureDebRes, Time heureFinRes, int etat) {


            reserverSalleDAO.ajoutReservation(numeroSalle, idProf, dateReservation, heureDebRes, heureFinRes, etat);


    }
}
