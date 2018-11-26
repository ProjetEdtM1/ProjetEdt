package fr.utln.projet.DAO;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.utilisateur.Cours;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DAOCours {
    private Connexion conn;

    public DAOCours() {
        this.conn = new Connexion();
    }


    /**
     * Récupère tous les cours d'un groupe pour la semaine courante
     * @param intituleGroupe
     * @return liste de cours
     */
    public List<Cours> getCoursSemaineGroupe(String intituleGroupe){
        this.conn.connect();

        List<Cours> listeCours = new ArrayList<>();

        String sql= "SELECT * FROM SUIVRE WHERE YEAR(dateCours) = YEAR(CURDATE()) AND WEEK(dateCours) = WEEK(CURDATE())";
        try{
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resCoursSemaineGroupe = statementSelectall.executeQuery(sql);
            while(resCoursSemaineGroupe.next()){
                Cours cours = new Cours();
                cours.setIntituleGroupe(resCoursSemaineGroupe.getString(1));
                cours.setIdProfesseur(resCoursSemaineGroupe.getInt(2));
                cours.setIntituleModule(resCoursSemaineGroupe.getString(3));
                cours.setDateCours(resCoursSemaineGroupe.getDate(4));
                cours.setHeureDebCours(resCoursSemaineGroupe.getTime(5));
                cours.setHeureFinCours(resCoursSemaineGroupe.getTime(6));
                cours.setNumeroSalle(resCoursSemaineGroupe.getInt(7));

                listeCours.add(cours);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listeCours;
    }
}
