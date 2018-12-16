package fr.utln.projet;


import fr.utln.projet.modele.CoursModele;
import fr.utln.projet.modele.ReserverSalleModele;
import fr.utln.projet.modele.SalleModele;
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


        //ProfesseurModele professeurModele = new ProfesseurModele();
        //ProfesseurVue professeurVue = new ProfesseurVue(professeurModele);
        // AuthentificationVue authentificationVue = new AuthentificationVue();

        // ModuleModele moduleModele = new ModuleModele();
//        SalleModele salleModele = new SalleModele();
//         SalleVue salleVue = new SalleVue(salleModele);
        //MaterielModele materielModele = new MaterielModele();
        //MaterielVue materielVue = new MaterielVue(materielModele);
        // PlanningEtudiantVue planningEtudiantVue = new PlanningEtudiantVue("SI1A");
//        ReserverSalleVue resa = new ReserverSalleVue();


//        PlanningEtudiantVue planningEtudiantVue = new PlanningEtudiantVue("SI1A");
//        MenuProfVue menuProfVue = new MenuProfVue("coucou");
//        ReserverSalleVue reservationSalleVue = new ReserverSalleVue(new ReserverSalleModele(), menuProfVue);
//        ReserverSalleVue reservationSalleVue = new ReserverSalleVue(new ReserverSalleModele());

        //CoursVue coursVue = new CoursVue(new CoursModele(), new MenuProfRefVue("yo"));

        AuthentificationVue auth = new AuthentificationVue();
        
    }
}
