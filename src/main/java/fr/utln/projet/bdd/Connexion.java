package fr.utln.projet.bdd;

import java.sql.*;

public class Connexion {
    private static Connection conn;
    static final String user = "sa";
    static final String pwd = "";
    static final String host = "10.9.185.1";
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";

    // String Driver = "org.postrgesql.Driver";
    // String url = "jdbc:postgresql://" + host + "/"; // pour postgres


    public void connect() {
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, user, pwd);
            System.out.println("Connexion ok");

            // test de connection a la DB

//            System.out.println("select *");
//            statement = conn.createStatement();
//            String sql = "SELECT * FROM ETUDIANT";
//            ResultSet resultSet = statement.executeQuery(sql);
//            System.out.println(resultSet);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }
}
