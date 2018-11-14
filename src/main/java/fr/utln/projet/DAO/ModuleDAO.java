package fr.utln.projet.DAO;


import fr.utln.projet.bdd.Connexion;
import fr.utln.projet.module.Module;
import fr.utln.projet.bdd.InitStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModuleDAO {
    static Connection conn = Connexion.getConn();

    public void persistModule(Module module) throws SQLException {
        String req1 = "insert into module (intitule, nbHeureCm, nbHeureTd, nbHeureTp) values (?, ?, ?, ?)";

        PreparedStatement insertModule = InitStatement.initPsmt(conn, req1);

        insertModule.setString(1, module.getIntitule());
        insertModule.setInt(2, module.getNbHeureCm());
        insertModule.setInt(3, module.getNbHeureTd());
        insertModule.setInt(4, module.getNbHeureTP());

        insertModule.executeQuery();

        // message de confirmation
        System.out.println("J'ai bien ajoute le module" + module.getIntitule());
    }
}