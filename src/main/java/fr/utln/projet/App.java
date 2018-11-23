package fr.utln.projet;


import fr.utln.projet.vue.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws SQLException {

        // ModeleEtudiant modeleEtudiant = new ModeleEtudiant();
        //EtudiantVue etudiantVue = new EtudiantVue(modeleEtudiant);
        //ProfesseurModele professeurModele = new ProfesseurModele();
        //ProfesseurVue professeurVue = new ProfesseurVue(professeurModele);
//         AuthentificationVue authentificationVue = new AuthentificationVue();
//        ModuleModele moduleModele = new ModuleModele();
        MenuProfRefVue a = new MenuProfRefVue("coucou");
        List<Integer> supplierNames = new ArrayList<>();
        int test = 1;
        supplierNames.add(test);
        System.out.println("taille : " + supplierNames.size() + "'\' element(tailel) : " + supplierNames.get(supplierNames.size()-1));

    }}