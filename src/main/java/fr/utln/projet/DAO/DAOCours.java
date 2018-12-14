package fr.utln.projet.DAO;

import com.sun.deploy.security.ValidationState;
import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.utilisateur.Cours;
import fr.utln.projet.utilisateur.Professeur;
import org.h2.jdbc.JdbcPreparedStatement;

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
     * @param groupe
     * @param idProf
     * @param intituleModule
     * @param dateCours
     * @param hDebutCours
     * @param hFinCours
     * @param numSalleCours  ajoute un cours dans la BD
     */

    public void ajouterCours(String groupe, String idProf, String intituleModule, Date dateCours, Time hDebutCours, Time hFinCours, String numSalleCours) {
        this.conn = new Connexion();
        conn.connect();

        String req = "INSERT INTO SUIVRE (Intitulegroupe, idprofesseur, intitulemodule, datecours, heuredebcours, heurefincours, numerosalle)" +
                "values(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement psmt = conn.getConn().prepareStatement(req);

            psmt.setObject(1, groupe, Types.VARCHAR);
            psmt.setObject(2, idProf, Types.VARCHAR);
            psmt.setObject(3, intituleModule, Types.VARCHAR);
            psmt.setObject(4, dateCours, Types.DATE);
            psmt.setObject(5, hDebutCours, Types.TIME);
            psmt.setObject(6, hFinCours, Types.TIME);
            psmt.setObject(7, numSalleCours, Types.VARCHAR);

            psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        conn.close();
    }


    public void testAjoutCours(String groupe, String idProf, String intituleModule, Date dateCours, Time hDebutCours, Time hFinCours, String numSalleCours) {
//        System.out.println(chevauchementcoursetudiant(groupe, dateCours, hDebutCours, hFinCours));
        boolean testprofesseur = chevauchementCoursProfesseur(idProf,dateCours,hDebutCours, hFinCours);
        boolean testEtudiant = chevauchementcoursetudiant(groupe, dateCours, hDebutCours, hFinCours);
        boolean testSalle = chevauchementCoursSalle(dateCours, hDebutCours, hFinCours, numSalleCours);
        if (!testEtudiant && !testprofesseur && !testSalle) {
            System.out.println("Le cours peut etre ajoute ");
            ajouterCours(groupe, idProf, intituleModule, dateCours, hDebutCours, hFinCours, numSalleCours);
        }
        else {
            System.out.println("Le cours n'a pas pu etre ajoute ");

        }
    }


    /**
     * Récupère tous les cours d'un groupe pour la semaine courante
     *
     * @param intituleGroupe
     * @return liste de cours
     */
    public List<Cours> getCoursSemaineGroupe(String intituleGroupe) {
        this.conn = new Connexion();
        conn.connect();

        List<Cours> listeCours = new ArrayList<>();

        String sql = "SELECT * FROM SUIVRE WHERE YEAR(dateCours) = YEAR(CURDATE()) AND WEEK(dateCours) = WEEK(CURDATE()) AND intituleGroupe = ? ";
        try {
            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, intituleGroupe);
            ResultSet resCoursSemaineGroupe = pstmt.executeQuery();
            while (resCoursSemaineGroupe.next()) {
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

    /**
     * Récupère tous les cours d'un prof pour la semaine courante
     *
     * @param login
     * @return liste de cours
     */
    public List<Cours> getCoursSemaineProf(String login) {
        this.conn = new Connexion();
        conn.connect();

        List<Cours> listeCours = new ArrayList<>();
        int id;
        String sql = "SELECT IDPROFESSEUR FROM PROFESSEUR WHERE LOGIN = ?";
        try {
            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, login);
            ResultSet res = pstmt.executeQuery();
            res.next();
            id = res.getInt(1);

            sql = "SELECT * FROM SUIVRE WHERE YEAR(dateCours) = YEAR(CURDATE()) AND WEEK(dateCours) = WEEK(CURDATE()) AND IDPROFESSEUR = ? ";
            PreparedStatement pstmt2 = conn.getConn().prepareStatement(sql);
            pstmt2.setInt(1, id);
            ResultSet resCoursSemaineGroupe = pstmt2.executeQuery();
            while (resCoursSemaineGroupe.next()) {
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

    /**
     * Methode de creation d'une liste de groupe  ( présent en BD)
     *
     * @return liste de groupe
     * @author CLAIN Cyril
     */
    public List<String> creationListGroupe() {

        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        List<String> groupeList = new ArrayList<String>();

        //requette de selection des Etudiant
        String sql = "SELECT INTITULEGROUPE FROM Groupe";
        try {
            Statement statementSelectIntitulegroupe = conn.getConn().createStatement();
            ResultSet resListeDesEtudiant = statementSelectIntitulegroupe.executeQuery(sql);

            while (resListeDesEtudiant.next()) {

                groupeList.add(resListeDesEtudiant.getString(1));
            }

            statementSelectIntitulegroupe.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupeList;
    }

    /**
     * Methode de creation d'une liste de professeur ( présent en BD)
     *
     * @return liste d'professeur
     * @author CLAIN Cyril
     */

    public List<Professeur> creationListProfesseur() {

        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        List<Professeur> professeurList = new ArrayList<Professeur>();
        //requette de selection des Professeur
        String sql = "SELECT * FROM PROFESSEUR";
        try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListeDesProfesseur = statementSelectall.executeQuery(sql);
            while (resListeDesProfesseur.next()) {
                Professeur professeur = new Professeur();
                professeur.setIdprofesseur(resListeDesProfesseur.getString(1));
                professeur.setNom(resListeDesProfesseur.getString(2));
                professeur.setPrenom(resListeDesProfesseur.getString(3));
                professeur.setLogin(resListeDesProfesseur.getString(4));
                professeur.setMdp(resListeDesProfesseur.getString(5));
                professeurList.add(professeur);
            }

            statementSelectall.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurList;
    }


    public boolean chevauchementcoursetudiant(String grp, Date date, Time hdc, Time hfc) {
        this.conn = new Connexion();
        conn.connect();
        boolean retour = false;

        String req = "select heuredebcours, heurefincours from suivre where intitulegroupe = ? and datecours = ?";
        try {

            PreparedStatement stmt = conn.getConn().prepareStatement(req);
            stmt.setString(1, grp);
            stmt.setDate(2, date);
            ResultSet res = stmt.executeQuery();

//                l'heure de debut doit etre inferieure a l'heure de fin
            if (hdc.compareTo(hfc) >= 0){
                retour = true;
            }

            while (res.next() && !retour) {
                Time hDebCours = res.getTime("heuredebcours");
                Time hFinCours = res.getTime("heurefincours");

                System.out.println(hdc.compareTo(hDebCours));
                System.out.println(hFinCours.compareTo(hfc));

//                chevauchement sur l'heure de debut (commence avant le debut, mais fini apres le debut
                if ((hdc.compareTo(hDebCours) <= 0) && (hfc.compareTo(hDebCours) > 0)) {
                    System.out.println("chevauchement inferieur ");
                    retour = true;
                }

//                chevauchelent interne (commence apres le debut, mais avant la fin, et fini avant la fin)
                if ((hdc.compareTo(hDebCours) >= 0) && (hdc.compareTo(hFinCours) <= 0) && (hfc.compareTo(hDebCours) > 0)
                        && (hfc.compareTo(hFinCours) <= 0) && (!retour)) {
                    System.out.println("chevauchement interieur ");
                    retour = true;
                }

//                chevauchement sur l'heure de fin (commence apres le debut, mais avant la fin, et fini plus tard)
                if ((hdc.compareTo(hDebCours) > 0) && (hdc.compareTo(hFinCours) < 0) && (hfc.compareTo(hFinCours) >= 0) && (!retour)) {
                    System.out.println("chevauchement superieur ");
                    retour = true;
                }

//                chevauchement total (commence avant le debut et fini apres la fin)
//                securite, mais deja pris en compte par le chevauchement inferieur (le 1)
                if ((hdc.compareTo(hDebCours) <= 0) && (hfc.compareTo(hFinCours) >= 0) && (!retour)) {
                    System.out.println("chevauchement total ");
                    retour = true;
                }
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retour;
    }

    public boolean chevauchementCoursProfesseur(String idProf, Date date, Time hdc, Time hfc) {
        this.conn = new Connexion();
        conn.connect();
        boolean retour = false;

        String req = "select heuredebcours, heurefincours from suivre where idprofesseur = ? and datecours = ?";

        try {

            PreparedStatement stmt = conn.getConn().prepareStatement(req);
            stmt.setString(1, idProf);
            stmt.setDate(2, date);
//            stmt.executeUpdate();
            ResultSet res = stmt.executeQuery();

//            l'heure de debut doit etre inferieure a l'heure de fin
            if (hdc.compareTo(hfc) >= 0){
                retour = true;
            }

            while (res.next() && !retour) {
                Time hDebCours = res.getTime("heuredebcours");
                Time hFinCours = res.getTime("heurefincours");

                System.out.println(hdc.compareTo(hDebCours));
                System.out.println(hFinCours.compareTo(hfc));

//                chevauchement sur l'heure de debut (commence avant le debut, mais fini apres le debut
                if ((hdc.compareTo(hDebCours) <= 0) && (hfc.compareTo(hDebCours) > 0)) {
                    System.out.println("chevauchement inferieur ");
                    retour = true;
                }

//                chevauchelent interne (commence apres le debut, mais avant la fin, et fini avant la fin)
                if ((hdc.compareTo(hDebCours) >= 0) && (hdc.compareTo(hFinCours) <= 0) && (hfc.compareTo(hDebCours) > 0)
                        && (hfc.compareTo(hFinCours) <= 0) && (!retour)) {
                    System.out.println("chevauchement interieur ");
                    retour = true;
                }

//                chevauchement sur l'heure de fin (commence apres le debut, mais avant la fin, et fini plus tard)
                if ((hdc.compareTo(hDebCours) > 0) && (hdc.compareTo(hFinCours) < 0) && (hfc.compareTo(hFinCours) >= 0) && (!retour)) {
                    System.out.println("chevauchement superieur ");
                    retour = true;
                }

//                chevauchement total (commence avant le debut et fini apres la fin)
//                securite, mais deja pris en compte par le chevauchement inferieur (le 1)
                if ((hdc.compareTo(hDebCours) <= 0) && (hfc.compareTo(hFinCours) >= 0) && (!retour)) {
                    System.out.println("chevauchement total ");
                    retour = true;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retour;
    }



    public boolean chevauchementCoursSalle(Date date, Time hdc, Time hfc, String salle) {
        this.conn = new Connexion();
        conn.connect();
        boolean retour = false;

        String req = "select heuredebcours, heurefincours from suivre where numerosalle = ? and datecours = ?";
        try {

            PreparedStatement stmt = conn.getConn().prepareStatement(req);
            stmt.setString(1, salle);
            stmt.setDate(2, date);
            ResultSet res = stmt.executeQuery();

//                l'heure de debut doit etre inferieure a l'heure de fin
            if (hdc.compareTo(hfc) >= 0){
                retour = true;
            }

            while (res.next() && !retour) {
                Time hDebCours = res.getTime("heuredebcours");
                Time hFinCours = res.getTime("heurefincours");

                System.out.println(hdc.compareTo(hDebCours));
                System.out.println(hFinCours.compareTo(hfc));

//                chevauchement sur l'heure de debut (commence avant le debut, mais fini apres le debut
                if ((hdc.compareTo(hDebCours) <= 0) && (hfc.compareTo(hDebCours) > 0)) {
                    System.out.println("chevauchement inferieur ");
                    retour = true;
                }

//                chevauchelent interne (commence apres le debut, mais avant la fin, et fini avant la fin)
                if ((hdc.compareTo(hDebCours) >= 0) && (hdc.compareTo(hFinCours) <= 0) && (hfc.compareTo(hDebCours) > 0)
                        && (hfc.compareTo(hFinCours) <= 0) && (!retour)) {
                    System.out.println("chevauchement interieur ");
                    retour = true;
                }

//                chevauchement sur l'heure de fin (commence apres le debut, mais avant la fin, et fini plus tard)
                if ((hdc.compareTo(hDebCours) > 0) && (hdc.compareTo(hFinCours) < 0) && (hfc.compareTo(hFinCours) >= 0) && (!retour)) {
                    System.out.println("chevauchement superieur ");
                    retour = true;
                }

//                chevauchement total (commence avant le debut et fini apres la fin)
//                securite, mais deja pris en compte par le chevauchement inferieur (le 1)
                if ((hdc.compareTo(hDebCours) <= 0) && (hfc.compareTo(hFinCours) >= 0) && (!retour)) {
                    System.out.println("chevauchement total ");
                    retour = true;
                }
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retour;
    }
}