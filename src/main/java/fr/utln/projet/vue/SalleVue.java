package fr.utln.projet.vue;

import fr.utln.projet.controleur.SalleControleur;
import fr.utln.projet.modele.SalleListModele;
import fr.utln.projet.modele.SalleModele;
import fr.utln.projet.utilisateur.Salle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class SalleVue  extends JFrame {

    private final SalleModele salleModele;
    private final SalleControleur salleControleur;

    private static SalleListModele salleListModele;

    private final JPanel salleSuppressionPanel = new JPanel(new GridBagLayout());
    private final JPanel salleAjoutPanel = new JPanel(new GridBagLayout());

    private final JList<Salle> salleJList;

    private final JButton suppressionSalleJButton = new JButton(" Supprimer");
    private final JButton ajoutOkSalleJButton = new JButton(" Ajouter");
    private final JButton ajoutCancelSalleJButton = new JButton("cancel");

    private final JLabel numSallelabel = new JLabel("Numero de salle : ");

    private final JTextField numSalle;


    public SalleVue(SalleModele salleModele) {

        super("CRUD  salle");

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        setSize(width / 2, height / 2);

        this.salleModele = salleModele;
        this.salleControleur = new SalleControleur(this, salleModele);
        this.salleListModele = new SalleListModele(salleControleur.getListSalle());

        salleModele.addObserver(salleListModele);

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
                setSuppressionSalleJButton((salleJList.getSelectedValue() == null));
            }
        });
        numSalle = new JTextField(salleControleur.getNumNouvelleSalle(),"",10);

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
                salleControleur.persisteSalle();
            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(salleSuppressionPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(salleAjoutPanel, c);


        setVisible(true);
    }
    private void setSuppressionSalleJButton(boolean b) {
        suppressionSalleJButton.setEnabled(b);
    }
    public void setCreationSalle(boolean b) {
        ajoutOkSalleJButton.setEnabled(b);
        ajoutCancelSalleJButton.setEnabled(b);

    }
}