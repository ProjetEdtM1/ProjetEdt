package fr.utln.projet;


import fr.utln.projet.controleur.ControleurEtudiant;
import fr.utln.projet.controleur.CoursControleur;
import fr.utln.projet.controleur.ProfesseurControleur;
import fr.utln.projet.modele.ModeleEtudiant;
import fr.utln.projet.modele.ProfesseurModele;
import fr.utln.projet.modele.ReserverSalleModele;
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
//        MenuProfRefVue a = new MenuProfRefVue("coucou");
        //SalleModele salleModele = new SalleModele();
        // SalleVue salleVue = new SalleVue(salleModele);
        //MaterielModele materielModele = new MaterielModele();
        //MaterielVue materielVue = new MaterielVue(materielModele);
        // PlanningEtudiantVue planningEtudiantVue = new PlanningEtudiantVue("SI1A");
//        ReserverSalleVue resa = new ReserverSalleVue();


//        PlanningEtudiantVue planningEtudiantVue = new PlanningEtudiantVue("SI1A");
//        MenuProfVue menuProfVue = new MenuProfVue("coucou");
//        ReserverSalleVue reservationSalleVue = new ReserverSalleVue(new ReserverSalleModele(), menuProfVue);
        PlanningEtudiantVue planningEtudiantVue = new PlanningEtudiantVue("SI1A");
//        ReserverSalleVue reservationSalleVue = new ReserverSalleVue(new ReserverSalleModele());
//
        //AuthentificationVue auth = new AuthentificationVue();

        ModeleEtudiant modeleEtudiant = new ModeleEtudiant();
        ProfesseurModele professeurModele = new ProfesseurModele();
        CoursVue coursVue = new CoursVue(new CoursControleur(planningEtudiantVue),new ControleurEtudiant(new EtudiantVue(modeleEtudiant,new MenuProfRefVue("cc")),modeleEtudiant), new ProfesseurControleur(new ProfesseurVue(professeurModele, new MenuProfRefVue("ccc")), professeurModele));
    }
}