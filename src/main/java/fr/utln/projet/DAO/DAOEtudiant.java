package fr.utln.projet.DAO;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.utilisateur.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Nom de classe : DaoEtudiant
 *
 * Description   : Gere les requettes sql
 *
 * Version       : 1.1
 *
 * Date          : 12/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class DAOEtudiant {

    private Connexion conn;

    /**
     * Requette SQL d'ajout dans la table ETUDIANT
     *
     * @param numetud
     * @param nom
     * @param prenom
     * @param mdp
     * @param groupe
     * @throws SQLException
     *
     * @author      CLAIN Cyril
     */
    public void persisteEtudiant(String numetud, String nom, String prenom, String mdp, String groupe) throws SQLException {
        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        System.out.println("DAO");


        // requete d'ajout d'un etudiant a la base de donnée écrite en sql
        String sql = "insert into ETUDIANT "+"values (?,?,?,?,?)";


        try {
            PreparedStatement statementAjoutEtudiant = conn.getConn().prepareStatement(sql);
            // on ajoute au statement au neme ? l'objet x (numetud par exemple) de type Types.type 
            statementAjoutEtudiant.setObject(1,numetud, Types.VARCHAR);
            statementAjoutEtudiant.setObject(2,nom, Types.VARCHAR);
            statementAjoutEtudiant.setObject(3,prenom, Types.VARCHAR);
            statementAjoutEtudiant.setObject(4, mdp, Types.VARCHAR);
            statementAjoutEtudiant.setObject(5,groupe, Types.VARCHAR);
            int resultSet = statementAjoutEtudiant.executeUpdate();
            System.out.println(resultSet);
            statementAjoutEtudiant.close();
            conn.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    /**
     * Methode de supression d'un d'étudient ( présent en BD) en fonction de son id
     *
     * @param numetud
     *
     * @author      CLAIN Cyril
     */
    public void deleteEtudiant(String numetud){
        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        //requette de supression d'un Etudiant
        String sql = "Delete from Etudiant "+" where NUMETUDIANT = ?";

        try{
            PreparedStatement statementSupresisonEtudiant = conn.getConn().prepareStatement(sql);
            statementSupresisonEtudiant.setObject(1,numetud, Types.VARCHAR);
            int resultSet = statementSupresisonEtudiant.executeUpdate();
            System.out.println(resultSet);
            statementSupresisonEtudiant.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode de creation d'une liste d'étudient ( présent en BD)
     *
     * @return liste d'etudiant
     *
     * @author CLAIN Cyril
     */
    public List<Etudiant> creationListEtudiant(){
        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        List<Etudiant> etudiantList = new ArrayList<Etudiant>();
        //requette de selection des Etudiant
        String sql = "SELECT * FROM ETUDIANT";
        try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListeDesEtudiant = statementSelectall.executeQuery(sql);
            System.out.println(resListeDesEtudiant);
            while(resListeDesEtudiant.next()){
                Etudiant etudiant = new Etudiant();
                etudiant.setNumetud(resListeDesEtudiant.getString(1));
                etudiant.setNom(resListeDesEtudiant.getString(2));
                etudiant.setPrenom(resListeDesEtudiant.getString(3));
                etudiant.setMdp(resListeDesEtudiant.getString(4));
                etudiant.setGroupe(resListeDesEtudiant.getString(5));
                etudiantList.add(etudiant);
            }

            statementSelectall.close();
            conn.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiantList;
    }

    /**
     * Methode qui retroune un Etudiant trouvé en Base de donnée en fonction de son numetud(clef primaire)
     *
     * @param numetud
     * @return instance Etudiant
     *
     * @author CLAIN Cyril
     */

    public Etudiant getEtudiant(String numetud){
        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        Etudiant etudiant = new Etudiant();


        //requette de selection des Etudiant
        String sql = "SELECT * FROM ETUDIANT "+"where NUMETUDIANT = ?";
        System.out.println("num etud : "+numetud);
        try {
            PreparedStatement statementSelectEtudiantFromID = conn.getConn().prepareStatement(sql);
            System.out.println("statement pret");
            statementSelectEtudiantFromID.setObject(1,numetud, Types.VARCHAR);
            System.out.println("param ajouter ");
            ResultSet resDetailUnEtudiant = statementSelectEtudiantFromID.executeQuery();
           // affin d'axeder au 1er elmt de la liste
            resDetailUnEtudiant.next();

            etudiant.setNumetud(resDetailUnEtudiant.getString(1));
            System.out.println(resDetailUnEtudiant.getString(1));
            etudiant.setNom(resDetailUnEtudiant.getString(2));
            etudiant.setPrenom(resDetailUnEtudiant.getString(3));
            System.out.println(resDetailUnEtudiant.getString(3));
            etudiant.setMdp(resDetailUnEtudiant.getString(4));
            etudiant.setGroupe(resDetailUnEtudiant.getString(5));
        }
            catch (SQLException e1) {
                System.out.println("error in getEtudiant : "+e1.getMessage());

        }

        return etudiant;


        }



    /**
     *
     *
     * @param login
     * @param mdp
     * @return vrai si l'identifiant et le mot de passe sont correctes
     *
     * @author Nicolas Guigou
     */
    public Boolean connexionEtudiant(String login,String mdp){
        this.conn = new Connexion();
        conn.connect();
        try {


            PreparedStatement pstmt = conn.getConn().prepareStatement("SELECT *" +
                    " FROM ETUDIANT WHERE numEtudiant = ?");
            pstmt.setString(1,login);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                pstmt = conn.getConn().prepareStatement("SELECT motDePasse FROM ETUDIANT " +
                        "WHERE numEtudiant = ?");
                pstmt.setString(1,login);
                res = pstmt.executeQuery();
                if(res.next()){
                    if(res.getString("motDePasse").equals(mdp)){
                        return true;
                    }
                }else
                    return false;
            }else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


    /**
     * Methode de modification d'un d'étudient ( présent en BD) en fonction de son numetud(clef primaire)
     *
     * @param numetud
     * @param nom
     * @param prenom
     * @param groupe
     *
     * @author CLAIN Cyril
     */
    public void updateEtudiant(String numetud,String nom, String prenom, String groupe)  {
        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        String sqlNom = "UPDATE Etudiant " + "SET NOMETUDIANT = ? WHERE NUMETUDIANT = ?";
        String sqlPrenom = "UPDATE Etudiant " + "SET PRENOMETUDIANT = ? WHERE NUMETUDIANT = ?";
        String sqlGroupe = "UPDATE Etudiant " + "SET INTITULEGROUPE = ? WHERE NUMETUDIANT = ?";

        Etudiant etudiant = getEtudiant(numetud);

        try {


            if (etudiant.getNom() != nom) {
                PreparedStatement statementSelectEtudiantFromID = conn.getConn().prepareStatement(sqlNom);
                statementSelectEtudiantFromID.setObject(1, nom, Types.VARCHAR);
                statementSelectEtudiantFromID.setObject(2, numetud, Types.VARCHAR);
                int resDetailUnEtudiant = statementSelectEtudiantFromID.executeUpdate();

            }
            if (etudiant.getGroupe() != groupe) {
                PreparedStatement statementSelectEtudiantFromID = conn.getConn().prepareStatement(sqlGroupe);
                statementSelectEtudiantFromID.setObject(1, groupe, Types.VARCHAR);
                statementSelectEtudiantFromID.setObject(2, numetud, Types.VARCHAR);
                int resDetailUnEtudiant = statementSelectEtudiantFromID.executeUpdate();

            }
            if (etudiant.getPrenom() != prenom) {
                System.out.println(etudiant.getPrenom());
                System.out.println(prenom);
                PreparedStatement statementSelectEtudiantFromID = conn.getConn().prepareStatement(sqlPrenom);
                statementSelectEtudiantFromID.setObject(1, prenom, Types.VARCHAR);
                statementSelectEtudiantFromID.setObject(2, numetud, Types.VARCHAR);
                int resDetailUnEtudiant = statementSelectEtudiantFromID.executeUpdate();

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }

    /**
     * Methode de creation d'une liste de groupe  ( présent en BD)
     *
     * @return liste degroupe
     *
     * @author CLAIN Cyril
     */
    public List<String> creationListGroupe(){

        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        List<String> groupeList = new ArrayList<String>();

        //requette de selection des Etudiant
        String sql = "SELECT INTITULEGROUPE FROM Groupe";
        try {
            Statement statementSelectIntitulegroupe = conn.getConn().createStatement();
            ResultSet resListeDesEtudiant = statementSelectIntitulegroupe.executeQuery(sql);

            while(resListeDesEtudiant.next()){

                groupeList.add(resListeDesEtudiant.getString(1));
            }

            statementSelectIntitulegroupe.close();
            conn.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupeList;
    }
}
