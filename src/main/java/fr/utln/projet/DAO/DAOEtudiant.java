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
 * Version       : 1.0
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
    public void setEtudiant(String numetud,String nom, String prenom, String mdp, String groupe) throws SQLException {
        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        System.out.println("DAO");


        // requete d'ajout d'un etudiant a la base de donnée écrite en sql
        String sql = "insert into ETUDIANT "+"values (?,?,?,?,?)";


        try {
            PreparedStatement statementAjoutEtudiant = conn.getConn().prepareStatement(sql);
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
}