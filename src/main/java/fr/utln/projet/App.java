package fr.utln.projet;


import fr.utln.projet.vue.*;

import java.io.*;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws SQLException, IOException {

        // ModeleEtudiant modeleEtudiant = new ModeleEtudiant();
        //EtudiantVue etudiantVue = new EtudiantVue(modeleEtudiant);
        //ProfesseurModele professeurModele = new ProfesseurModele();
        //ProfesseurVue professeurVue = new ProfesseurVue(professeurModele);
        // AuthentificationVue authentificationVue = new AuthentificationVue();

        // ModuleModele moduleModele = new ModuleModele();
        MenuProfRefVue a = new MenuProfRefVue("coucou");
        //SalleModele salleModele = new SalleModele();
       // SalleVue salleVue = new SalleVue(salleModele);
        //MaterielModele materielModele = new MaterielModele();
        //MaterielVue materielVue = new MaterielVue(materielModele);
       // PlanningEtudiantVue planningEtudiantVue = new PlanningEtudiantVue("SI1A");
//        ReserverSalleVue resa = new ReserverSalleVue();


    }
}