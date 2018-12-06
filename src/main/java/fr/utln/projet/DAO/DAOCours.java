package fr.utln.projet.DAO;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.utilisateur.Cours;

import java.sql.*;
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
     *
     * @param groupe
     * @param idProf
     * @param intituleModule
     * @param dateCours
     * @param hDebutCours
     * @param hFinCours
     * @param numSalleCours
     * ajoute un cours dans la BD
     */

    public void ajouterCours(String groupe, String idProf, String intituleModule, Date dateCours, Time hDebutCours, Time hFinCours, int numSalleCours) {

    }



    /**
     * Récupère tous les cours d'un groupe pour la semaine courante
     * @param intituleGroupe
     * @return liste de cours
     */
    public List<Cours> getCoursSemaineGroupe(String intituleGroupe){
        this.conn = new Connexion();
        conn.connect();

        List<Cours> listeCours = new ArrayList<>();

        String sql= "SELECT * FROM SUIVRE WHERE YEAR(dateCours) = YEAR(CURDATE()) AND WEEK(dateCours) = WEEK(CURDATE()) AND intituleGroupe = ? ";
        try{
            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1,intituleGroupe);
            ResultSet resCoursSemaineGroupe = pstmt.executeQuery();
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

    /**
     * Récupère tous les cours d'un prof pour la semaine courante
     * @param login
     * @return liste de cours
     */
    public List<Cours> getCoursSemaineProf(String login){
        this.conn = new Connexion();
        conn.connect();

        List<Cours> listeCours = new ArrayList<>();
        int id;
        String sql = "SELECT IDPROFESSEUR FROM PROFESSEUR WHERE LOGIN = ?";
        try {
            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1,login);
            ResultSet res = pstmt.executeQuery();
            res.next();
            id = res.getInt(1);

            sql= "SELECT * FROM SUIVRE WHERE YEAR(dateCours) = YEAR(CURDATE()) AND WEEK(dateCours) = WEEK(CURDATE()) AND IDPROFESSEUR = ? ";
            PreparedStatement pstmt2 = conn.getConn().prepareStatement(sql);
            pstmt2.setInt(1,id);
            ResultSet resCoursSemaineGroupe = pstmt2.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeCours;
    }
}
