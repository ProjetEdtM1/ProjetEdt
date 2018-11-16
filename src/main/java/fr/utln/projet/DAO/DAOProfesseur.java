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

    public DAOProfesseur(){
        this.conn = new Connexion();
    }

    public Boolean connexionProfesseur(String login,String mdp){
        conn.connect();
        try {
            PreparedStatement pstmt = conn.getConn().prepareStatement("SELECT *" +
                    " FROM PROFESSEUR WHERE LOGIN = ?");
            pstmt.setString(1,login);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                pstmt = conn.getConn().prepareStatement("SELECT MOTDEPASSE FROM PROFESSEUR " +
                        "WHERE LOGIN = ?");
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
     * Methode de creation d'une liste de professeur ( présent en BD)
     *
     * @return liste d'professeur
     *
     * @author CLAIN Cyril
     */

    public List<Professeur> creationListProfesseur(){

        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        List<Professeur> professeurList = new ArrayList<Professeur>();
        //requette de selection des Professeur
        String sql = "SELECT * FROM PROFESSEUR";
        try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListeDesProfesseur = statementSelectall.executeQuery(sql);
            while(resListeDesProfesseur.next()){
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
        String sql = "insert into PROFESSEUR (NOMPROFESSEUR, PRENOMPROFESSEUR, LOGIN, MOTDEPASSE) "+"values (?,?,?,?)";


        try {
            PreparedStatement statementAjoutProfesseur = conn.getConn().prepareStatement(sql);

            // on ajoute au statement au neme ? l'objet x (numetud par exemple) de type Types.type

            statementAjoutProfesseur.setObject(1,nom, Types.VARCHAR);
            statementAjoutProfesseur.setObject(2,prenom, Types.VARCHAR);
            statementAjoutProfesseur.setObject(3, login, Types.VARCHAR);
            statementAjoutProfesseur.setObject(4,mdp, Types.VARCHAR);
            int resultSet = statementAjoutProfesseur.executeUpdate();
            System.out.println(resultSet);
            statementAjoutProfesseur.close();
            conn.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
