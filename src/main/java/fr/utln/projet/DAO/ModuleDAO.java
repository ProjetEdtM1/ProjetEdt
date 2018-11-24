package fr.utln.projet.DAO;


import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.module.Module;
import fr.utln.projet.bdd.InitStatement;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleDAO {
    private Connexion conn;

    public void persistModule(String intitule, int nbHCm, int nbHTd, int nbHTp) throws SQLException {
        this.conn = new Connexion();
        conn.connect();

        String req = "insert into module (INTITULEMODULE, NBHEURECM, NBHEURETD, NBHEURETP)" + "values (?, ?, ?, ?)";

        try {

            PreparedStatement stmt = conn.getConn().prepareStatement(req);


            stmt.setObject(1, intitule, Types.VARCHAR);
            stmt.setObject(2, nbHCm, Types.INTEGER);
            stmt.setObject(3, nbHTd, Types.INTEGER);
            stmt.setObject(4, nbHTp, Types.INTEGER);

            stmt.executeUpdate();

            // message de confirmation
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Methode pour supprimer un module
     * @param intitule
     * @return
     */


    public boolean supprimerModule (String intitule) {
        this.conn = new Connexion();
        conn.connect();
        int res = 0;

        String req = "DELETE FROM module WHERE INTITULEMODULE = ?";

        try {

            PreparedStatement stmt = conn.getConn().prepareStatement(req);

            stmt.setObject(1, intitule, Types.VARCHAR);

            res = stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res == 1)
            return true;
        else
            return false;
    }

    public List<Module> creationListModule() {
        this.conn = new Connexion();
        conn.connect();

        List<Module> moduleList = new ArrayList<Module>();

        String req = "SELECT * FROM MODULE";

        try{
            Statement stmt = conn.getConn().createStatement();

            ResultSet res = stmt.executeQuery(req);

            while(res.next()) {
                Module module = new Module.Builder(res.getString(1)).nbHeureCm(res.getInt(2)).nbHeureTd(res.getInt(3)).nbHeureTp(res.getInt(4)).build();
                moduleList.add(module);
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    return moduleList;
    }

    /**
     * Récupération d'un module dans la base de données en fonction de l'intitulé passé en paramètre
     * @param intituleModule
     * @return Un module avec les bons attributs
     */
    public Module getModule(String intituleModule){
        this.conn = new Connexion();
        conn.connect();

        String sql = "SELECT * FROM MODULE "+"where INTITULEMODULE = ?";
        Module module;
        try{
            PreparedStatement statementSelectModuleFromID = conn.getConn().prepareStatement(sql);
            statementSelectModuleFromID.setObject(1,intituleModule, Types.VARCHAR);
            ResultSet resDetailsModule = statementSelectModuleFromID.executeQuery();
            resDetailsModule.next();
            module = new Module.Builder(intituleModule)
                    .nbHeureCm(resDetailsModule.getInt(2))
                    .nbHeureTd(resDetailsModule.getInt(3))
                    .nbHeureTp(resDetailsModule.getInt(4)).build();
            return module;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        module = null;
        return module;
    }

    /**
     * Modification d'un module dans la base de données
     * @param intituleModule
     * @param nbHeureCm
     * @param nbHeureTd
     * @param nbHeureTp
     * @author Nicolas Guigou
     */
    public void updateModule(String intituleModule, int nbHeureCm, int nbHeureTd, int nbHeureTp ){
        String sqlCM = "UPDATE MODULE" +" SET NBHEURECM = ? WHERE INTITULEMODULE = ?";
        String sqlTD = "UPDATE MODULE SET NBHEURETD = ? WHERE INTITULEMODULE = ?";
        String sqlTP = "UPDATE MODULE SET NBHEURETP = ? WHERE INTITULEMODULE = ?";

        Module module = getModule(intituleModule);
        try {
            if (module.getIntitule() != intituleModule) {
                PreparedStatement statementSelectModuleFromID = conn.getConn().prepareStatement(sqlCM);
                statementSelectModuleFromID.setObject(1, nbHeureCm, Types.INTEGER);
                statementSelectModuleFromID.setObject(2, intituleModule, Types.VARCHAR);
                int resDetailUnModule = statementSelectModuleFromID.executeUpdate();
            }
            if (module.getIntitule() != intituleModule) {
                PreparedStatement statementSelectModuleFromID = conn.getConn().prepareStatement(sqlTD);
                statementSelectModuleFromID.setObject(1, nbHeureTd, Types.INTEGER);
                statementSelectModuleFromID.setObject(2, intituleModule, Types.VARCHAR);
                int resDetailUnModule = statementSelectModuleFromID.executeUpdate();
            }
            if (module.getIntitule() != intituleModule) {
                PreparedStatement statementSelectModuleFromID = conn.getConn().prepareStatement(sqlTP);
                statementSelectModuleFromID.setObject(1, nbHeureTp, Types.INTEGER);
                statementSelectModuleFromID.setObject(2, intituleModule, Types.VARCHAR);
                int resDetailUnModule = statementSelectModuleFromID.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}