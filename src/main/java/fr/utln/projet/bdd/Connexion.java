package fr.utln.projet.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private Connection conn;
    private String host = "127.0.0.1";

    public void connect(){
        try{
            conn=DriverManager.getConnection("jdbc:postgresql://sinfo1:5432/nguigou971","nguigou971","");
            System.out.println("Connexion ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
