package fr.utln.projet.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private Connection conn;
    public void connect(){
        // String user = "mnocito114";
        // String password = "";
        String Driver = "org.postrgesql.Driver";
        String host = "10.9.185.1";
        String url = "jdbc:postgresql://" + host + "/";


        try{
            conn=DriverManager.getConnection(url);
            System.out.println("Connexion ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
