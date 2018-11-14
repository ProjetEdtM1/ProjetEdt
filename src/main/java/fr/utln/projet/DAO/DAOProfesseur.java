package fr.utln.projet.DAO;

import fr.utln.projet.bdd.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
