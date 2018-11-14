package fr.utln.projet.bdd;

import java.sql.*;

/*
 * Nom de classe : Connexion
 *
 * Description   : Connection a la Database sur serveur distant h2 avec url, login, mot de passe
 *
 * Version       : 3.0
 *
 * Date          : 12/11/2018
 *
 * Copyright     : CLAIN Cyril NOCITO Marc
 */

public class Connexion {
    //connection
    private static Connection conn;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    // String Driver = "org.postrgesql.Driver";
    // String url = "jdbc:postgresql://" + host + "/"; // pour postgres


    public void connect() {
        //Statement statement = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("connection ok");


            //STEP 3: test de connection a la DB

//            System.out.println("select *");
//            statement = conn.createStatement();
//            String sql = "SELECT * FROM ETUDIANT";
//            ResultSet resultSet = statement.executeQuery(sql);
//            System.out.println(resultSet);

        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        catch (SQLException e) {
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
