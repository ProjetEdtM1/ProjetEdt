package fr.utln.projet.DAO;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.utilisateur.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*
 * Nom de classe : DAOSalle
 *
 * Description   : Gere les requettes sql
 *
 * Version       : 1.0
 *
 * Date          : 23/11/2018
 *
 * Copyright     : CLAIN Cyril
 */
public class DAOSalle {

    private Connexion conn;

    /**
     * Requette SQL d'ajout dans la table Salle
     *
     * @param numeroSalle
     * @throws SQLException
     *
     * @author      CLAIN Cyril
     */
    public boolean persisteSalle(Integer numeroSalle) throws SQLException {
        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        Boolean addlist = false;
        // requete d'ajout d'une salle a la base de donnée écrite en sql
        String sql = "insert into SALLE "+"values (?)";


        try {
            PreparedStatement statementAjoutSalle = conn.getConn().prepareStatement(sql);
            statementAjoutSalle.setObject(1,numeroSalle, Types.INTEGER);
            int resultSet = statementAjoutSalle.executeUpdate();
            System.out.println(resultSet);
            statementAjoutSalle.close();
            conn.close();
            addlist = true;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return addlist;

    }
    /**
     * Methode de supression d'un d'salle ( présent en BD) en fonction de son id
     *
     * @param numeroSalle
     *
     * @author CLAIN Cyril
     */
    public void deleteSalle(Integer numeroSalle){
        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        //requette de supression d'une salle
        String sql = "Delete from SALLE "+" where NUMEROSALLE = ?";

        try{
            PreparedStatement statementSupresisonSalle = conn.getConn().prepareStatement(sql);
            statementSupresisonSalle.setObject(1,numeroSalle, Types.INTEGER);
            int resultSet = statementSupresisonSalle.executeUpdate();
            System.out.println(resultSet);
            statementSupresisonSalle.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode de creation d'une liste de salle ( présent en BD)
     *
     * @return liste de salle
     *
     * @author CLAIN Cyril
     */
    public List<Salle> creationlistsalle(){

        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        List<Salle> sallelist = new ArrayList<Salle>();

        //requette de selection des Salle
        String sql = "SELECT * FROM Salle";
        try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListeDesSalle = statementSelectall.executeQuery(sql);
            System.out.println(resListeDesSalle);
            while(resListeDesSalle.next()){
                Salle salle = new Salle();
                salle.setNumerosalle(resListeDesSalle.getInt(1));
                sallelist.add(salle);
            }

            statementSelectall.close();
            conn.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sallelist;
    }

    /**
     * Methode qui retroune une salle  trouvé en Base de donnée en fonction de son id(clef primaire)
     *
     * @param numerosalle
     * @return instance Salle
     *
     * @author CLAIN Cyril
     */

    public Salle getSalle(Integer numerosalle){

        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        Salle salle = new Salle();


        //requette de selection des Salle
        String sql = "SELECT * FROM SALLE "+"where NUMEROSALLE = ?";
        try {
            PreparedStatement statementSelectSalleFromID = conn.getConn().prepareStatement(sql);
            statementSelectSalleFromID.setObject(1,numerosalle, Types.INTEGER);
            ResultSet resDetailUnSalle = statementSelectSalleFromID.executeQuery();

            // affin d'axeder au 1er elmt de la liste
            resDetailUnSalle.next();

            salle.setNumerosalle(resDetailUnSalle.getInt(1));

        }
        catch (SQLException e1) {
            System.out.println("error in getSalle : "+e1.getMessage());

        }

        return salle;


    }

    /**
     * Methode de creation d'une liste de numero de salle ( présent en BD)
     *
     * @return liste d'entier
     *
     * @author CLAIN Cyril
     */
    public List<Integer> creationListIdSalle(){
        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        List<Integer> ListIdSalle = new ArrayList<>();
        //requette de selection des Etudiant
        String sql = "SELECT NUMEROSALLE FROM SALLE";
        try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListeDesSalles = statementSelectall.executeQuery(sql);
            while(resListeDesSalles.next()){

                ListIdSalle.add(resListeDesSalles.getInt(1));
            }

            statementSelectall.close();
            conn.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListIdSalle;
    }

}
