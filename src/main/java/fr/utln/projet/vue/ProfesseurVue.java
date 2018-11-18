package fr.utln.projet.vue;

import fr.utln.projet.controleur.ProfesseurControleur;
import fr.utln.projet.modele.ProfesseurListModele;
import fr.utln.projet.modele.ProfesseurModele;
import fr.utln.projet.utilisateur.Professeur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
 * Nom de classe : ProfesseurVue
 *
 * Description   : Partie visible de mon interface permet de voir les formulaire CRUD d'un professeur
 *
 * Version       : 1.2
 *
 * Date          : 18/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class ProfesseurVue extends JFrame{
    private final ProfesseurModele professeurModele;
    private final ProfesseurControleur professeurControleur;

    private static ProfesseurListModele professeurListModele;

    private final JPanel professeurSuppressionPanel = new JPanel(new GridBagLayout());
    private final JPanel professeurAjoutPanel = new JPanel(new GridBagLayout());

    private final JList<Professeur> professeurtSuppressionJList;
    private final JButton suppressionProfesseurJButton = new JButton(" Supprimer");

    private final JButton ajoutOkProfesseurJButton = new JButton(" Ajouter");
    private final JButton ajoutCancelProfesseurJButton = new JButton("cancel");

    private final JLabel nomProfesseurlabel = new JLabel("nom : ");
    private final JLabel prenomProfesseurlabel = new JLabel("prenom : ");
    private final JLabel loginProfesseurlabel = new JLabel("login : ");
    private final JLabel mdpProfesseurlabel = new JLabel("mdp :  ");

    private final JTextField nomProfesseur;
    private final JTextField prenomprofesseur;
    private final JTextField loginProfesseur;
    private final JTextField mdpProfesseur;

    public ProfesseurVue(ProfesseurModele professeurModele) {

        super("CRUD  professeur");

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        setSize(width / 2, height / 2);
        this.professeurModele = professeurModele;
        this.professeurControleur = new ProfesseurControleur(this, professeurModele);
        this.professeurListModele = new ProfesseurListModele(professeurControleur.getListProfesseur());

        professeurModele.addObserver(professeurListModele);

        professeurtSuppressionJList = new JList<>(professeurListModele);
        professeurtSuppressionJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setSuppressionProfesseurJButton((professeurtSuppressionJList.getSelectedValue() != null));
            }
        });

        suppressionProfesseurJButton.setEnabled(false);
        suppressionProfesseurJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professeurControleur.deleteProfesseur(professeurtSuppressionJList.getSelectedValue());
                setSuppressionProfesseurJButton((professeurtSuppressionJList.getSelectedValue() == null));
            }
        });

        nomProfesseur = new JTextField(professeurControleur.getNomNouvelProfesseurModel(),"",10);
        prenomprofesseur = new JTextField(professeurControleur.getPrenomNouvelProfesseurModel(),"",10);
        loginProfesseur = new JTextField(professeurControleur.getLoginNouvelProfesseurModel(),"",10);
        mdpProfesseur = new JTextField(professeurControleur.getMdpNouvelProfesseurModel(),"",10);

        ajoutCancelProfesseurJButton.setEnabled(false);
        ajoutCancelProfesseurJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professeurControleur.cancelProfesseur();
            }
        });
        ajoutOkProfesseurJButton.setEnabled(false);
        ajoutOkProfesseurJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professeurControleur.ajouterProfesseur();
            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();


        // Ajout d'un professeur
        professeurAjoutPanel.setBorder(BorderFactory.createTitledBorder("Ajout"));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        professeurAjoutPanel.add(nomProfesseurlabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        professeurAjoutPanel.add(nomProfesseur, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        professeurAjoutPanel.add(prenomProfesseurlabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        professeurAjoutPanel.add(prenomprofesseur, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        professeurAjoutPanel.add(loginProfesseurlabel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        professeurAjoutPanel.add(loginProfesseur, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        professeurAjoutPanel.add(mdpProfesseurlabel, c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        professeurAjoutPanel.add(mdpProfesseur, c);

        c.gridx = 0;
        c.gridy = 5;
        professeurAjoutPanel.add(ajoutOkProfesseurJButton, c);

        c.gridx = 1;
        c.gridy = 5;
        professeurAjoutPanel.add(ajoutCancelProfesseurJButton, c);

        //Suppression professeur

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 1;
        professeurSuppressionPanel.add(suppressionProfesseurJButton, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.weighty = 0.8;
        c.gridx = 0;
        c.gridy = 2;
        professeurSuppressionPanel.add(professeurtSuppressionJList, c);

        //Ajout des panel de suppression et  d'ajout consultation
        getContentPane().setLayout(new GridBagLayout());


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(professeurSuppressionPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(professeurAjoutPanel, c);


        setVisible(true);
    }

    private void setSuppressionProfesseurJButton(boolean b) {
        suppressionProfesseurJButton.setEnabled(b);

    }

    public void setCreationProfesseur(boolean b) {
        ajoutOkProfesseurJButton.setEnabled(b);
        ajoutCancelProfesseurJButton.setEnabled(b);

    }
}
