package fr.utln.projet.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    public static Connection conn;

    public void connect() {
        String user = "sa";
        String password = "";
        // String Driver = "org.postrgesql.Driver";
        String host = "10.9.185.1";
        // String url = "jdbc:postgresql://" + host + "/"; // pour postgres
        String url = "jdbc:h2:tcp://localhost/~/test";


        try {
            conn = DriverManager.getConnection(url + user + password);
            System.out.println("Connexion ok");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }
}
