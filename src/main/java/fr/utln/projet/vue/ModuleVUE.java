package fr.utln.projet.vue;

import fr.utln.projet.controleur.ModuleControleur;
import fr.utln.projet.modele.ModuleListeModele;
import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.module.Module;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModuleVUE extends Fenetre {
    public static ModuleControleur moduleControleur;
    public static ModuleModele moduleModele;
    public static ModuleListeModele moduleListeModele;

    public static JPanel ajoutModulePanel = new JPanel(new GridBagLayout());
    public static JPanel suppressionModulePanel = new JPanel(new GridBagLayout());


    public static JButton ajout = new JButton("Ajouter");
    public static JButton annuler = new JButton("Annuler");
    public static JButton supprimer = new JButton("Supprimer");

    public static JTextField ajoutmodule = new JTextField();
    public static JTextField nbHeureCm = new JTextField();
    public static JTextField nbHeureTd = new JTextField();
    public static JTextField nbHeureTp = new JTextField();

    public static JLabel intituleModule = new JLabel("Intitulé Module ");
    public static JLabel heureCm = new JLabel("nombre d'heures total des CM");
    public static JLabel heureTd = new JLabel("nombre d'heures total des TD");
    public static JLabel heureTp = new JLabel("nombre d'heures total des TP");

    public JList<Module> supprimerModule;

    private MenuProfRefVue menuProfRefVue;

    public ModuleVUE(ModuleModele moduleModele, final MenuProfRefVue menuProfRefVue) {
        super();
        this.setTitle("Gestion des Module");

        this.menuProfRefVue = menuProfRefVue;

//        JPanel global = new JPanel();
//        this.setTitle("Gestion des modules");
//
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        this.moduleModele = moduleModele;
        this.moduleControleur = new ModuleControleur(this, moduleModele);
        this.moduleListeModele = new ModuleListeModele(moduleControleur.getListModule());
        this.supprimerModule = new JList<Module>(moduleListeModele);


        moduleModele.addObserver(moduleListeModele);

        for (Module m: moduleListeModele.listeModule) {
            moduleListeModele.addElement(m);
        }

        ajout.setEnabled(false);
        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduleControleur.ajouterModule();
            }
        });

        supprimer.setEnabled(false);
        supprimerModule.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setBoutonSupprimer(supprimerModule.getSelectedValue() != null);
            }
        });

        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduleControleur.supprimerModule(supprimerModule.getSelectedValue());
                setBoutonSupprimer(supprimerModule.getSelectedValue() == null);
            }
        });

        ajoutmodule = new JTextField(moduleControleur.getIntituleNouveauModuleModele(),"",10);
        nbHeureCm = new JTextField(moduleControleur.getNbHCmNouveauModuleModele(),"",10);
        nbHeureTd = new JTextField(moduleControleur.getNbHTdNouveauModuleModele(),"",10);
        nbHeureTp = new JTextField(moduleControleur.getNbHTpNouveauModuleModele(),"",10);


        // placement des JLabel sur la colonne 1


        contrainte.gridx = 0;
        contrainte.gridy = 1;
        ajoutModulePanel.add(intituleModule, contrainte);

        contrainte.gridy = 2;
        ajoutModulePanel.add(heureCm, contrainte);

        contrainte.gridy = 3;
        ajoutModulePanel.add(heureTd, contrainte);

        contrainte.gridy = 4;
        ajoutModulePanel.add(heureTp, contrainte);

        // placement des JTExtField sur la colonne 2

        contrainte.gridx = 2;
        contrainte.gridy = 1;
        ajoutModulePanel.add(ajoutmodule, contrainte);

        contrainte.gridy = 2;
        ajoutModulePanel.add(nbHeureCm, contrainte);

        contrainte.gridy = 3;
        ajoutModulePanel.add(nbHeureTd, contrainte);

        contrainte.gridy = 4;
        ajoutModulePanel.add(nbHeureTp, contrainte);

        // placement de la JList de modules pour la suppression
        ajoutModulePanel.setBorder(BorderFactory.createTitledBorder("Ajout"));
        suppressionModulePanel.setBorder(BorderFactory.createTitledBorder("Supprimer"));


        contrainte.gridx = 3;
        contrainte.gridy = 1;
        suppressionModulePanel.add(supprimerModule, contrainte);

        // Placement des JButton en bas

        contrainte.gridx = 1;
        contrainte.gridy = 5;
        ajoutModulePanel.add(annuler, contrainte);

        contrainte.gridx = 0;
        ajoutModulePanel.add(ajout, contrainte);

        contrainte.gridx = 3;
        contrainte.gridy = 2;
        suppressionModulePanel.add(supprimer, contrainte);


        contrainte.gridx = 0;
        contrainte.gridy = 0;
        getContentPane().add(ajoutModulePanel, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 0;
        getContentPane().add(suppressionModulePanel, contrainte);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                menuProfRefVue.setTrueBoutonModule();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                menuProfRefVue.setTrueBoutonModule();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


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

    public void setBoutonSupprimer(boolean gris) {
        supprimer.setEnabled(gris);
    }


}
