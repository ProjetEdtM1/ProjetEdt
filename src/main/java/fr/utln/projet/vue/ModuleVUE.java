package fr.utln.projet.vue;

import fr.utln.projet.controleur.ModuleControleur;
//import fr.utln.projet.modele.ModuleListeModele;
import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.module.Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuleVUE extends Fenetre {
    public static ModuleControleur moduleControleur;
    public static ModuleModele moduleModele;
//    public static ModuleListeModele moduleListeModele;

    public static JButton ajout = new JButton("Ajouter");
    public static JButton annuler = new JButton("Annuler");
    public static JButton supprimer = new JButton("Supprimer");

    public static JTextField ajoutmodule = new JTextField();
    public static JTextField nbHeureCm = new JTextField();
    public static JTextField nbHeureTd = new JTextField();
    public static JTextField nbHeureTp = new JTextField();

    public static JLabel intituleModule = new JLabel("Intitulé Module ");
    public static JLabel heureCm = new JLabel("nombre d'heures total des cours magistraux");
    public static JLabel heureTd = new JLabel("nombre d'heures total des travaux dirigés");
    public static JLabel heureTp = new JLabel("nombre d'heures total des travaux pratiques");

    public static JList<Module> supprimerModule;

    public ModuleVUE(ModuleModele moduleModele) {
        super();
        JPanel global = new JPanel();
        this.setTitle("Ajouter un module");

        global.setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        this.moduleModele = moduleModele;
        this.moduleControleur = new ModuleControleur(this, moduleModele);
//        this.moduleListeModele = new ModuleListeModele(moduleModele.getModule());

//        moduleModele.addObserver(moduleListeModele);

        // supprimerModule = new JList<>(moduleListeModele);

        ajout.setEnabled(false);
        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduleControleur.ajouterModule();
            }
        });

        ajoutmodule = new JTextField(moduleControleur.getIntituleNouveauModuleModele(),"",10);
        nbHeureCm = new JTextField(moduleControleur.getNbHCmNouveauModuleModele(),"",10);
        nbHeureTd = new JTextField(moduleControleur.getNbHTdNouveauModuleModele(),"",10);
        nbHeureTp = new JTextField(moduleControleur.getNbHTpNouveauModuleModele(),"",10);


        // placement des JLabel sur la colonne 1

        contrainte.gridx = 1;
        contrainte.gridy = 1;
        global.add(intituleModule, contrainte);

        contrainte.gridy = 2;
        global.add(heureCm, contrainte);

        contrainte.gridy = 3;
        global.add(heureTd, contrainte);

        contrainte.gridy = 4;
        global.add(heureTp, contrainte);


        // placement des JTExtField sur la colonne 2

        contrainte.gridx = 2;
        contrainte.gridy = 1;
        global.add(ajoutmodule, contrainte);

        contrainte.gridy = 2;
        global.add(nbHeureCm, contrainte);

        contrainte.gridy = 3;
        global.add(nbHeureTd, contrainte);

        contrainte.gridy = 4;
        global.add(nbHeureTp, contrainte);

        // Placement des JButton en bas

        contrainte.gridy = 5;
        global.add(annuler, contrainte);

        contrainte.gridx = 1;
        global.add(ajout, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 10;
        global.add(supprimer, contrainte);



        getContentPane().add(global);


//        ajout.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controleurModule.ajoutermodule();
//                String intit = ajoutmodule.getText();
//                String hCm = nbHeureCm.getText();
//                String hTd = nbHeureTd.getText();
//                String hTp = nbHeureTp.getText();
//
//                try {
//                    int aux = Integer.parseInt(hCm);
//                } catch (NumberFormatException nfe){
//                    int valmin = 0;
//
//                }
//                Module module = new Module.Builder(intit).nbHeureCm(hCm).nbHeureTd(hTd).nbHeureTp(hTp).build();
//                ModuleDAO moduleDAO = new ModuleDAO();
//            }
//        });


    }

    public void setBoutonAjouter (boolean gris) {
        ajout.setEnabled(gris);
    }


    public static void main(String[] args) {
        ModuleModele moduleModele = new ModuleModele();
        new ModuleVUE(moduleModele);
    }
}
