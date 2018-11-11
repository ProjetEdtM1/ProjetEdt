package fr.utln.projet.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InitStatement {

    public static PreparedStatement initPsmt(Connection connection, String requete) throws SQLException {
        PreparedStatement psmt = connection.prepareStatement(requete);
        return psmt;
    }

    public static Statement intitStmt(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt;
    }
}
