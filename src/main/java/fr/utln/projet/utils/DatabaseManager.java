package fr.utln.projet.utils;

/**
 * Created by Cyril on 13/12/18.
 * Copyright emmanuelbruno cours-java-librarymanager-jdbcgui
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class DatabaseManager {

    /**
     * la liste de connection de base du pull de connections.
     */
    private static final Queue<Connection> freeConnections = new LinkedList<Connection>();

    /**
     * Nombre initial de connections disponible en meme temps
     */
    private static final int numberOfInitialConnections = 10;

    // les trois prochain sont constant
    /**
     * mot de passe du .properties
     */
    private static final String password = System
            .getProperty("database.password");

    /**
     * url du .properties
     */
    private static final String url = System
            .getProperty("database.url");

    /**
     * utilisateur du .properties
     */
    private static final String user = System
            .getProperty("database.user");

    // pour le nombre de connection définis essaie d'en créer une ou catch l'exeption
    static {
        for (int i = 0; i < numberOfInitialConnections; i++) {
            try {
                freeConnections.add(DriverManager.getConnection(url, user,
                        password));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Donne une connection.
     *
     * @return the connection
     * @throws SQLException the SQL exception
     */
    public static synchronized Connection getConnection() throws SQLException {
        Connection connection = null;
        // si toute les connection sont utilisé en recrér une
        if (freeConnections.isEmpty()) {
            connection = DriverManager.getConnection(url, user, password);
        }

        else {
            connection = freeConnections.remove();
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public static synchronized void releaseConnection(Connection connection) {
        // regarde la taille du pull de connexion
        // si il y en a pas assez en recréé une

        if (freeConnections.size() < numberOfInitialConnections) {
            freeConnections.add(connection);
        } else {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

