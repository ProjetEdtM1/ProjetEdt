package fr.utln.projet.DAO;

import fr.utln.projet.bdd.Connexion;

import java.sql.*;

public class ReserverSalleDAO {
    private Connexion conn;

    /**
     *
     * @param numeroSalle
     * @param idProf
     * @param dateReservation
     * @param heureDebRes
     * @param heureFinRes
     * @param etat
     */

    public void ajoutReservation(String numeroSalle, int idProf, Date dateReservation, Time heureDebRes, Time heureFinRes, int etat) {
        this.conn = new Connexion();
        conn.connect();

        String req = "insert into RESERVER (NUMEROSALLE, IDPROFESSEUR, DATERESERVATION, HEUREDEBRES, HEUREFINRES, ETAT)" +
                "values (?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement stmt = new Connexion().getConn().prepareStatement(req);

            stmt.setObject(1, numeroSalle, Types.VARCHAR);
            stmt.setObject(2, idProf, Types.INTEGER);
            stmt.setObject(3, dateReservation, Types.DATE);
            stmt.setObject(4, heureDebRes, Types.TIME);
            stmt.setObject(5, heureFinRes, Types.TIME);
            stmt.setObject(6, etat, Types.INTEGER);

            stmt.executeQuery();

            stmt.close();
            conn.close();


        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
