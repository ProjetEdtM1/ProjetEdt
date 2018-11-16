package fr.utln.projet.DAO;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.utilisateur.Etudiant;
import fr.utln.projet.utilisateur.Professeur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
     * @return liste d'etudiant
     *
     * @author CLAIN Cyril
     */
    public List<Professeur> creationListProfesseur(){

        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        List<Professeur> professeurList = new ArrayList<Professeur>();
        //requette de selection des Etudiant
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
}
