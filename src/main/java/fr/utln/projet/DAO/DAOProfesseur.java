package fr.utln.projet.DAO;

/*
 * Nom de classe : DaoProfessuer
 *
 * Description   : Gere les requettes sql
 *
 * Version       : 1.2
 *
 * Date          : 16/11/2018
 *
 * Copyright     : CLAIN Cyril, GUIGOU Nicolas
 */

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.utilisateur.Professeur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOProfesseur {
    private Connexion conn;

    public DAOProfesseur() {
        this.conn = new Connexion();
    }

    public Boolean connexionProfesseur(String login, String mdp) {
        conn.connect();
        try {
            PreparedStatement pstmt = conn.getConn().prepareStatement("SELECT *" +
                    " FROM PROFESSEUR WHERE LOGIN = ?");
            pstmt.setString(1, login);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                pstmt = conn.getConn().prepareStatement("SELECT MOTDEPASSE FROM PROFESSEUR " +
                        "WHERE LOGIN = ?");
                pstmt.setString(1, login);
                res = pstmt.executeQuery();
                if (res.next()) {
                    if (res.getString("motDePasse").equals(mdp)) {
                        return true;
                    }
                } else
                    return false;
            } else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

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


    public void persisteProfesseur(String nom, String prenom, String login, String mdp) {
        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        // requete d'ajout d'un professeur a la base de donnée écrite en sql
        String sql = "insert into PROFESSEUR (NOMPROFESSEUR, PRENOMPROFESSEUR, LOGIN, MOTDEPASSE) " + "values (?,?,?,?)";


        try {
            PreparedStatement statementAjoutProfesseur = conn.getConn().prepareStatement(sql);

            // on ajoute au statement au neme ? l'objet x (numetud par exemple) de type Types.type

            statementAjoutProfesseur.setObject(1, nom, Types.VARCHAR);
            statementAjoutProfesseur.setObject(2, prenom, Types.VARCHAR);
            statementAjoutProfesseur.setObject(3, login, Types.VARCHAR);
            statementAjoutProfesseur.setObject(4, mdp, Types.VARCHAR);
            int resultSet = statementAjoutProfesseur.executeUpdate();
            System.out.println(resultSet);
            statementAjoutProfesseur.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Methode de supression d'un Professeur ( présent en BD) en fonction de son id
     *
     * @param idprofesseur
     *
     * @author CLAIN Cyril
     */
    public void deleteProfesseur(String idprofesseur) {

            // debut de connection
            this.conn = new Connexion();
            conn.connect();

            //requette de supression d'un Professeur
            String sql = "Delete from PROFESSEUR " + " where IDPROFESSEUR = ?";

            try {
                PreparedStatement statementSupresisonProfesseur = conn.getConn().prepareStatement(sql);
                statementSupresisonProfesseur.setObject(1, idprofesseur, Types.VARCHAR);
                int resultSet = statementSupresisonProfesseur.executeUpdate();
                System.out.println(resultSet);
                statementSupresisonProfesseur.close();
                conn.close();

            }
            catch (SQLException e) {
                e.printStackTrace();
            }


    }


    /**
     * Methode de modification d'un d'un professeur ( présent en BD) en fonction de son idprofesseur(clef primaire)
     *
     * @param IdPRofesseur
     * @param nom
     * @param prenom
     *
     * @author CLAIN Cyril
     */
    public void updateProfesseur(String IdPRofesseur, String nom, String prenom) {

        // connection
        this.conn = new Connexion();
        conn.connect();

        String sqlNom = "UPDATE PROFESSEUR " + "SET NOMPROFESSEUR = ? WHERE IDPROFESSEUR = ?";
        String sqlPrenom = "UPDATE PROFESSEUR " + "SET PRENOMPROFESSEUR = ? WHERE IDPROFESSEUR  = ?";

        Professeur professeur = getProfesseur(IdPRofesseur);
        try {

            if (professeur.getNom() != nom) {
                PreparedStatement statementSelectProfesseurFromID = conn.getConn().prepareStatement(sqlNom);
                statementSelectProfesseurFromID.setObject(1, IdPRofesseur, Types.VARCHAR);
                statementSelectProfesseurFromID.setObject(2, nom, Types.VARCHAR);
                int resDetailUnProfesseur = statementSelectProfesseurFromID.executeUpdate();


            }
            if (professeur.getPrenom() != prenom) {
                PreparedStatement statementSelectProfesseurFromID = conn.getConn().prepareStatement(sqlPrenom);
                statementSelectProfesseurFromID.setObject(1, IdPRofesseur, Types.VARCHAR);
                statementSelectProfesseurFromID.setObject(2, prenom, Types.VARCHAR);
                int resDetailUnProfesseur = statementSelectProfesseurFromID.executeUpdate();

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

        /**
         * Methode qui retroune un Etudiant trouvé en Base de donnée en fonction de son numetud(clef primaire)
         *
         * @param IdPRofesseur
         * @return instance Etudiant
         *
         * @author CLAIN Cyril
         */

        public Professeur getProfesseur(String IdPRofesseur){
            // debut de connection
            this.conn = new Connexion();
            conn.connect();
            Professeur professeur = new Professeur();


            //requette de selection des Etudiant
            String sql = "SELECT * FROM PROFESSEUR "+"where IDPROFESSEUR = ?";
            System.out.println("num etud : "+IdPRofesseur);
            try {
                PreparedStatement statementSelectProfesseurFromID = conn.getConn().prepareStatement(sql);
                statementSelectProfesseurFromID.setObject(1,IdPRofesseur, Types.VARCHAR);

                ResultSet resDetailUnProfesseur = statementSelectProfesseurFromID.executeQuery();
                // affin d'acceder au 1er elmt de la liste
                resDetailUnProfesseur.next();

                professeur.setIdprofesseur(resDetailUnProfesseur.getString(1));
                professeur.setNom(resDetailUnProfesseur.getString(2));
                professeur.setPrenom(resDetailUnProfesseur.getString(3));
                professeur.setLogin(resDetailUnProfesseur.getString(4));
                professeur.setMdp(resDetailUnProfesseur.getString(5));
            }

            catch (SQLException e1) {
                System.out.println("error in getEtudiant : "+e1.getMessage());

            }

            return professeur;


        }

    /**
     * Connexion d'un professeur référent
     * @param login
     * @param mdp
     * @return vrai si le login et le mot de passe correspondent bien à un professeur référent
     */
    public Boolean connexionProfesseurRef(String login,String mdp){
        conn.connect();
        if(connexionProfesseur(login,mdp)){
            try {
                PreparedStatement pstmt = conn.getConn().prepareStatement("SELECT *" +
                        " FROM PROMOTION,PROFESSEUR WHERE PROFESSEUR.login = ? AND PROFESSEUR.idProfesseur = PROMOTION.idProfesseur");
                pstmt.setString(1,login);
                ResultSet res = pstmt.executeQuery();
                if(res.next()) return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }else
            return false;

    }

    }



