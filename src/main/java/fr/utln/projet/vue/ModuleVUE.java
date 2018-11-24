package fr.utln.projet.vue;

import com.sun.deploy.panel.NumberDocument;
import fr.utln.projet.controleur.ModuleControleur;
import fr.utln.projet.modele.ModuleListeModele;
import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.module.Module;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

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

    public static JLabel intituleModule = new JLabel("Intitulé Module :");
    public static JLabel heureCm = new JLabel("Heure(s) CM :");
    public static JLabel heureTd = new JLabel("Heure(s) TD :");
    public static JLabel heureTp = new JLabel("Heure(s) TP :");

    public JList<Module> supprimerModule;

    private static JPanel moduleDetailPanel = new JPanel(new GridBagLayout());
    private final JButton modifierModuleJBouton = new JButton("valider");
    private final JButton cancelModifierModuleJButton = new JButton("annuler");

    private final JComboBox<Module> moduleDetailJComboBox;


    private static JTextField intituleDetailTextField = new JTextField();
    private static JTextField nbHcmDetailTextField = new JFormattedTextField(NumberFormat.getInstance());
    private static JTextField nbHtdDetailTextField = new JTextField();
    private static JTextField nbHtpDetailTextField = new JTextField();

    private final JLabel intituleModuleDetaillabel = new JLabel("Intitulé Module :");
    private final JLabel nbHcmDetaillabel = new JLabel("Heure(s) CM :");
    private final JLabel nbHtdDetaillabel = new JLabel("Heure(s) TD :");
    private final JLabel nbHtpDetaillabel = new JLabel("Heure(s) TP :");

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

//        for (Module m: moduleListeModele.listeModule) {
//            moduleListeModele.addElement(m);
//        }

        ajout.setEnabled(false);
        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduleControleur.ajouterModule();
            }
        });

        annuler.setEnabled(false);
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduleControleur.cancel();
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
               supprimer.setEnabled(false);

            }
        });

        ajoutmodule = new JTextField(moduleControleur.getIntituleNouveauModuleModele(),"",10);
        nbHeureCm = new JTextField(moduleControleur.getNbHCmNouveauModuleModele(),"",10);
        nbHeureTd = new JTextField(moduleControleur.getNbHTdNouveauModuleModele(),"",10);
        nbHeureTp = new JTextField(moduleControleur.getNbHTpNouveauModuleModele(),"",10);

        intituleDetailTextField = new JTextField(moduleControleur.getIntituleModuleModele(),"",10);
        nbHcmDetailTextField = new JTextField(moduleControleur.getNbHCmModuleModele(),"",10);
        nbHtdDetailTextField = new JTextField(moduleControleur.getNbHTdModuleModele(),"",10);
        nbHtpDetailTextField = new JTextField(moduleControleur.getNbHTpModuleModele(),"",10);


        // Liste des modules pouvant être modifiés
        moduleDetailJComboBox = new JComboBox<Module>(moduleListeModele);

        moduleDetailJComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        montrerDetail(null);
                        break;
                    case ItemEvent.SELECTED:
                        montrerDetail(moduleDetailJComboBox.getItemAt(moduleDetailJComboBox.getSelectedIndex()));
                        break;
                }
            }
        });

        modifierModuleJBouton.setEnabled(false);
        modifierModuleJBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduleControleur.modifierEtudiant();
            }
        });

        cancelModifierModuleJButton.setEnabled(false);
        cancelModifierModuleJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                montrerDetail(moduleDetailJComboBox.getItemAt(moduleDetailJComboBox.getSelectedIndex()));
            }
        });

        intituleDetailTextField.setEnabled(false);
        nbHcmDetailTextField.setEnabled(false);
        nbHtdDetailTextField.setEnabled(false);
        nbHtpDetailTextField.setEnabled(false);

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

        contrainte.gridx = 1;
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

        //modification module

        moduleDetailPanel.setBorder(BorderFactory.createTitledBorder("Détails modules"));

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        moduleDetailPanel.add(moduleDetailJComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        moduleDetailPanel.add(intituleModuleDetaillabel,c);

        c.gridx = 1;
        c.gridy = 1;
        moduleDetailPanel.add(intituleDetailTextField,c);

        c.gridx = 0;
        c.gridy = 2;
        moduleDetailPanel.add(nbHcmDetaillabel,c);

        c.gridx = 1;
        c.gridy = 2;
        moduleDetailPanel.add(nbHcmDetailTextField,c);

        c.gridx = 0;
        c.gridy = 3;
        moduleDetailPanel.add(nbHtdDetaillabel,c);

        c.gridx = 1;
        c.gridy = 3;
        moduleDetailPanel.add(nbHtdDetailTextField,c);

        c.gridx = 0;
        c.gridy = 4;
        moduleDetailPanel.add(nbHtpDetaillabel,c);

        c.gridx = 1;
        c.gridy = 4;
        moduleDetailPanel.add(nbHtpDetailTextField,c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        moduleDetailPanel.add(cancelModifierModuleJButton,c);

        c.gridy = 5;
        c.gridx = 0;
        moduleDetailPanel.add(modifierModuleJBouton,c);

        c.gridx = 2;
        c.gridy = 0;
        getContentPane().add(moduleDetailPanel,c);

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

    public void setBoutonAjouter (boolean b) {
        annuler.setEnabled(b);
        ajout.setEnabled(b);
    }

    public void setBoutonSupprimer(boolean gris) {
        supprimer.setEnabled(gris);
    }

    public void setBoutonModifier(boolean gris){
        nbHcmDetailTextField.setEnabled(true);
        nbHtdDetailTextField.setEnabled(true);
        nbHtpDetailTextField.setEnabled(true);
        modifierModuleJBouton.setEnabled(true);
        cancelModifierModuleJButton.setEnabled(true);
    }

    public void montrerDetail(Module module){
        if (module == null){
            intituleDetailTextField.setText("");
            nbHcmDetailTextField.setText("");
            nbHtdDetailTextField.setText("");
            nbHtpDetailTextField.setText("");
        }
        else{
            intituleDetailTextField.setText(module.getIntitule());
            intituleDetailTextField= new JTextField(moduleControleur.getIntituleModuleModele(),module.getIntitule(),10);

            nbHcmDetailTextField.setText(String.valueOf(module.getNbHeureCm()));
            nbHcmDetailTextField= new JTextField(moduleControleur.getNbHCmModuleModele(),String.valueOf(module.getNbHeureCm()),10);

            nbHtdDetailTextField.setText(String.valueOf(module.getNbHeureTd()));
            nbHtdDetailTextField= new JTextField(moduleControleur.getNbHTdModuleModele(),String.valueOf(module.getNbHeureTd()),10);

            nbHtpDetailTextField.setText(String.valueOf(module.getNbHeureTP()));
            nbHtpDetailTextField= new JTextField(moduleControleur.getNbHTpModuleModele(),String.valueOf(module.getNbHeureTP()),10);
        }
    }


}
