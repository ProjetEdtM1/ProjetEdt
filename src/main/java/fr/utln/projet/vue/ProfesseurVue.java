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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ProfesseurVue extends JFrame{
    private final ProfesseurModele professeurModele;
    private final ProfesseurControleur professeurControleur;

    private static ProfesseurListModele professeurListModele;


    private final JPanel professeurAjoutPanel = new JPanel(new GridBagLayout());

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

       // professeurModele.addObserver(professeurListModele);

        nomProfesseur = new JTextField(professeurControleur.getNomNouvelProfesseurModel(),"",10);
        prenomprofesseur = new JTextField(professeurControleur.getPrenomNouvelProfesseurModel(),"",10);
        loginProfesseur = new JTextField(professeurControleur.getLoginNouvelProfesseurModel(),"",10);
        mdpProfesseur = new JTextField(professeurControleur.getMdpNouvelProfesseurModel(),"",10);


        //ajoutOkProfesseurJButton.setEnabled(false);
        ajoutOkProfesseurJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professeurControleur.ajouterProfesseur();
            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();


        // Ajout d'un etudiant
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

        //Ajout des panel de suppression et  d'ajout consultation
        getContentPane().setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(professeurAjoutPanel, c);


        setVisible(true);
    }

    public void setCreationProfesseur(boolean b) {
        ajoutOkProfesseurJButton.setEnabled(b);
    }
}
