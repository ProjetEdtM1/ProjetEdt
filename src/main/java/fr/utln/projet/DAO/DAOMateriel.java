package fr.utln.projet.DAO;

import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.utilisateur.Materiel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*
 * Nom de classe : DAOMateriel
 *
 * Description   : Gere les requettes sql
 *
 * Version       : 1.0
 *
 * Date          : 24/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class DAOMateriel {

    private Connexion conn;

    /**
     * Requette SQL d'ajout dans la table Materiel
     *
     * @param idMateriel
     * @param nomMateriel
     * @throws SQLException
     *
     * @author      CLAIN Cyril
     */
    public boolean persisteMateriel(Integer idMateriel, String nomMateriel) throws SQLException {

        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        Boolean addlist = false;
        String sql = "insert into MATERIEL "+"values (?,?)";


        try {
            PreparedStatement statementAjoutMAteriel = conn.getConn().prepareStatement(sql);
            statementAjoutMAteriel.setObject(1,idMateriel, Types.INTEGER);
            statementAjoutMAteriel.setObject(2,nomMateriel, Types.VARCHAR);
            int resultSet = statementAjoutMAteriel.executeUpdate();
            System.out.println(resultSet);
            statementAjoutMAteriel.close();
            conn.close();
            addlist = true;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return addlist;

    }
    /**
     * Methode de supression d'un d'materiel ( présent en BD) en fonction de son id
     *
     * @param idMateriel
     *
     * @author CLAIN Cyril
     */
    public void deleteMateriel(Integer idMateriel){
        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        //requette de supression d'un materiel
        String sql = "Delete from MATERIEL "+" where IDMATERIEL = ?";

        try{
            PreparedStatement statementSupresisonMateriel = conn.getConn().prepareStatement(sql);
            statementSupresisonMateriel.setObject(1,idMateriel, Types.INTEGER);
            int resultSet = statementSupresisonMateriel.executeUpdate();
            System.out.println(resultSet);
            statementSupresisonMateriel.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode de creation d'une liste de materiel ( présent en BD)
     *
     * @return liste de materiel
     *
     * @author CLAIN Cyril
     */
    public List<Materiel> creationlistMateriel(){

        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        List<Materiel> materielList = new ArrayList<Materiel>();

        //requette de selection des Materiel
        String sql = "SELECT * FROM MATERIEL";
        try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListeDesMateriel = statementSelectall.executeQuery(sql);
            System.out.println(resListeDesMateriel);
            while(resListeDesMateriel.next()){
                Materiel materiel = new Materiel();
                materiel.setIdMateriel(resListeDesMateriel.getInt(1));
                materielList.add(materiel);
            }

            statementSelectall.close();
            conn.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materielList;
    }

    /**
     * Methode qui retroune une materiel  trouvé en Base de donnée en fonction de son id(clef primaire)
     *
     * @param idMateriel
     * @return instance materiel
     *
     * @author CLAIN Cyril
     */

    public Materiel getMateriel(Integer idMateriel){

        // debut de connection
        this.conn = new Connexion();
        conn.connect();
        Materiel materiel = new Materiel();


        String sql = "SELECT * FROM MATERIEL "+"where IDMATERIEL = ?";

        try {
            PreparedStatement statementSelectMaterielFromID = conn.getConn().prepareStatement(sql);
            statementSelectMaterielFromID.setObject(1,idMateriel, Types.INTEGER);
            ResultSet resDetailUnMateriel = statementSelectMaterielFromID.executeQuery();

            // affin d'axeder au 1er elmt de la liste
            resDetailUnMateriel.next();

            materiel.setIdMateriel(resDetailUnMateriel.getInt(1));

        }
        catch (SQLException e1) {
            System.out.println("error in getMateriel : "+e1.getMessage());

        }

        return materiel;


    }

    /**
     * Methode de creation d'une liste de numero de materiel ( présent en BD)
     *
     * @return liste d'entier
     *
     * @author CLAIN Cyril
     */
    public List<Integer> creationListIdMateriel(){

        // debut de connection
        this.conn = new Connexion();
        conn.connect();

        List<Integer> ListIdMateriel = new ArrayList<>();
        //requette de selection des Etudiant
        String sql = "SELECT IDMATERIEL FROM MATERIEL";
        try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListeDesMateriels = statementSelectall.executeQuery(sql);
            while(resListeDesMateriels.next()){

                ListIdMateriel.add(resListeDesMateriels.getInt(1));
            }

            statementSelectall.close();
            conn.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListIdMateriel;
    }

}
