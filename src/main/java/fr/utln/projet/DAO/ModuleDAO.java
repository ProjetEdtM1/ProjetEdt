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
        System.out.println("connection a la bd pour la methode ajout");

        String req = "insert into module (INTITULEMODULE, NBHEURECM, NBHEURETD, NBHEURETP)" + "values (?, ?, ?, ?)";

        try {

            PreparedStatement stmt = conn.getConn().prepareStatement(req);
            System.out.println("YO " + stmt);
            System.out.println("int " + intitule);
            System.out.println("CM " + nbHCm);
            System.out.println("TD " + nbHTd);
            System.out.println("TP " + nbHTp);

            stmt.setObject(1, intitule, Types.VARCHAR);
            stmt.setObject(2, nbHCm, Types.INTEGER);
            stmt.setObject(3, nbHTd, Types.INTEGER);
            stmt.setObject(4, nbHTp, Types.INTEGER);

            stmt.executeUpdate();

            // message de confirmation
            System.out.println("J'ai bien ajoute le module " + intitule);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean supprimerModule (String intitule) {
        this.conn = new Connexion();
        conn.connect();
        int res = 0;
        System.out.println("yo " + intitule);

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
        System.out.println("connection a la bd pour la methode lister");

        List<Module> moduleList = new ArrayList<Module>();

        String req = "SELECT * FROM MODULE";

        try{
            Statement stmt = conn.getConn().createStatement();

            ResultSet res = stmt.executeQuery(req);
//            System.out.println(res);

            while(res.next()) {
                Module module = new Module.Builder(res.getString(1)).nbHeureCm(res.getInt(2)).nbHeureTd(res.getInt(3)).nbHeureTp(res.getInt(4)).build();
                moduleList.add(module);
//                System.out.println("LISTE: " + module.getIntitule());
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    return moduleList;
    }
}