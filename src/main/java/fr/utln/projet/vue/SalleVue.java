package fr.utln.projet.vue;

import fr.utln.projet.controleur.SalleControleur;
import fr.utln.projet.modele.LangueListModele;
import fr.utln.projet.modele.SalleListModele;
import fr.utln.projet.modele.SalleModele;
import fr.utln.projet.utilisateur.Salle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
/*
 * Nom de classe : SalleVue
 *
 * Description   : Partie visible de mon interface permet de voir les formulaire CRUD d'un Salle
 *
 * Version       : 1.0
 *
 * Date          : 23/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class SalleVue  extends Fenetre {

    private final SalleModele salleModele;
    private final SalleControleur salleControleur;

    private static SalleListModele salleListModele;

    private final JPanel salleSuppressionPanel = new JPanel(new GridBagLayout());
    private final JPanel salleAjoutPanel = new JPanel(new GridBagLayout());

    private final JList<Salle> salleJList;

    private final JList<String> langueJlist;
    private ResourceBundle rbBouton = ResourceBundle.getBundle("textBouton");
    private ResourceBundle rbLabel = ResourceBundle.getBundle("textLabel");
    public static LangueListModele langueListModele;


    private final JButton suppressionSalleJButton;// = new JButton("Supprimer");
    private final JButton ajoutOkSalleJButton;// = new JButton("Ajouter");
    private final JButton ajoutCancelSalleJButton;// = new JButton("Annuler");

    private final JLabel numSallelabel;// = new JLabel("Numero de salle"+" : ");

    private final JTextField numSalle;


    public SalleVue(SalleModele salleModele, final MenuProfRefVue menuProfRefVue) {

        super();
        setTitle("CRUD  salle");

        this.salleModele = salleModele;
        this.salleControleur = new SalleControleur(this, salleModele);
        this.salleListModele = new SalleListModele(salleControleur.getListSalle());

        salleModele.addObserver(salleListModele);

        String[] listlangue = {"Francais", "Anglais"};
        langueListModele = new LangueListModele(Arrays.asList(listlangue));
        langueJlist = new JList<>(listlangue);
        langueJlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                changeBundle(langueJlist.getSelectedValue());
            }
        });

        suppressionSalleJButton = new JButton(rbBouton.getString("Supprimer"));
        ajoutOkSalleJButton = new JButton(rbBouton.getString("Ajouter"));
        ajoutCancelSalleJButton = new JButton(rbBouton.getString("Annuler"));
        numSallelabel = new JLabel(rbLabel.getString("Numero de salle"));


        salleJList = new JList<>(salleListModele);
        salleJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setSuppressionSalleJButton((salleJList.getSelectedValue() != null));
            }
        });
        suppressionSalleJButton.setEnabled(false);
        suppressionSalleJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salleControleur.deleteSalle(salleJList.getSelectedValue());
                suppressionSalleJButton.setEnabled(false);
            }
        });
        numSalle = new JTextField(salleControleur.getNumNouvelleSalle(), "", 10);

        ajoutCancelSalleJButton.setEnabled(false);
        ajoutCancelSalleJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salleControleur.cancelSalle();
            }
        });
        ajoutOkSalleJButton.setEnabled(false);
        ajoutOkSalleJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(salleControleur.persisteSalle()))
                    JOptionPane.showMessageDialog(salleAjoutPanel, "Le numero de salle est deja pris ");

            }
        });

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                menuProfRefVue.setTrueBoutonMa();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                menuProfRefVue.setTrueBoutonMa();
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

        GridBagConstraints c = new GridBagConstraints();


        // Ajout d'un salle
        salleAjoutPanel.setBorder(BorderFactory.createTitledBorder("Ajout"));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        salleAjoutPanel.add(numSallelabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        salleAjoutPanel.add(numSalle, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        salleAjoutPanel.add(ajoutOkSalleJButton, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        salleAjoutPanel.add(ajoutCancelSalleJButton, c);

        //Suppression salle
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 1;
        salleSuppressionPanel.add(suppressionSalleJButton, c);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.weighty = 0.8;
        c.gridx = 0;
        c.gridy = 2;
        salleSuppressionPanel.add(salleJList, c);

        //Ajout des panel de suppression et  d'ajout consultation
        getContentPane().setLayout(new GridBagLayout());


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(salleSuppressionPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        getContentPane().add(salleAjoutPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        getContentPane().add(langueJlist,c);

        setVisible(true);
    }

    private void setSuppressionSalleJButton(boolean b) {
        suppressionSalleJButton.setEnabled(b);
    }

    public void setCreationSalle(boolean b) {
        ajoutOkSalleJButton.setEnabled(b);
        ajoutCancelSalleJButton.setEnabled(b);

    }

    private void changeBundle(String selectedValue) {
        if (selectedValue.compareTo("Anglais") == 0) {
            rbBouton = ResourceBundle.getBundle("textBouton", Locale.ENGLISH);
            rbLabel = ResourceBundle.getBundle("textLabel", Locale.ENGLISH);

        } else {
            rbBouton = ResourceBundle.getBundle("textBouton", Locale.FRANCE);
            rbLabel = ResourceBundle.getBundle("textLabel", Locale.FRANCE);
        }
        setTextBouton(rbBouton);
        setTextLabel(rbLabel);

    }

    private void setTextLabel(ResourceBundle rbLabel) {
        numSallelabel.setText(rbLabel.getString("Numero de salle"));
    }

    private void setTextBouton(ResourceBundle rbBouton) {
        suppressionSalleJButton.setText(rbBouton.getString("Supprimer"));
        ajoutOkSalleJButton.setText(rbBouton.getString("Ajouter"));
        ajoutCancelSalleJButton.setText(rbBouton.getString("Annuler"));

    }
}