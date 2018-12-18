package fr.utln.projet.bdd;

import fr.utln.projet.utils.ConfigReader;
import fr.utln.projet.utils.DatabaseManager;

import java.sql.*;

/*
 * Nom de classe : Connexion
 *
 * Description   : Connection a la Database sur serveur distant h2 avec fichier de conf
 *
 * Version       : 4.0
 *
 * Date          : 13/12/2018
 *
 * Copyright     : CLAIN Cyril NOCITO Marc
 */

public class Connexion {
    //connection
    private static Connection conn;


    public void connect() {

        //Importation des paramètres de configuration (cf. src/main/resources/config.xml)
        ConfigReader.importConfig();

        try {
            // STEP 1: Register JDBC driver
            //Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DatabaseManager.getConnection();
            System.out.println("connection ok");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
