package fr.utln.projet.vue;
import fr.utln.projet.controleur.ControleurEtudiant;
import fr.utln.projet.modele.EtudiantListModel;
import fr.utln.projet.modele.ModeleEtudiant;
import fr.utln.projet.utilisateur.Etudiant;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Nom de classe : EtudiantVue
 *
 * Description   : Partie visible de mon interface permet de voir le formulaire d'ajout d'étudiant
 *
 * Version       : 2
 *
 * Date          : 08/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class EtudiantVue extends JFrame  {


    private final ModeleEtudiant modeleEtudiant;
    private final ControleurEtudiant controleurEtudiant;

    private static EtudiantListModel etudiantListModel;

    private final JPanel etudiantSuppressionPanel = new JPanel(new GridBagLayout());
    private final JPanel etudiantAjoutPanel = new JPanel(new GridBagLayout());

    private final JList<Etudiant> etudiantSuppressionJList;
    private final JButton supprimerEtudiantJButton = new JButton("Supprimer Etudiant");

    private final JButton ajoutOkEtudiantJButton = new JButton(" ok");
    private final JButton ajoutcancelEtudiantJButton = new JButton("cancel Etudiant");

    private final JLabel groupetudlabel = new JLabel(" Groupe :");;
    private final JLabel numetudlabel = new JLabel("Num etud :");
    private final JLabel nometudlabel = new JLabel("Nom :");
    private final JLabel prenometudlabel = new JLabel(" Prénom :");
    private final JLabel mdpEtudiantlabel = new JLabel("mdp");

    private final JTextField numetud;
    private final JTextField nometud;
    private final JTextField prenometud;
    private final JTextField groupetud;
    private final JPasswordField mdpEtudiant;


    public EtudiantVue(ModeleEtudiant modeleEtudiant) throws HeadlessException {

        super("supression etudiants");

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        setSize(width / 2, height / 2);

        this.modeleEtudiant = modeleEtudiant;
        this.controleurEtudiant = new ControleurEtudiant(this,modeleEtudiant);
        this.etudiantListModel = new EtudiantListModel(modeleEtudiant.getEtudiant());

        modeleEtudiant.addObserver(etudiantListModel);

        etudiantSuppressionJList = new JList<>(etudiantListModel);
        etudiantSuppressionJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setSuppressionEtudiant((etudiantSuppressionJList.getSelectedValue() != null));
            }
        });

        supprimerEtudiantJButton.setEnabled(false);
        supprimerEtudiantJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String ID =
                controleurEtudiant.deleteEtudiant(etudiantSuppressionJList.getSelectedValue());
                setSuppressionEtudiant((etudiantSuppressionJList.getSelectedValue() == null));

            }
        });


        ajoutOkEtudiantJButton.setEnabled(false);
        ajoutOkEtudiantJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurEtudiant.ajouterEtudiant();
            }
        });

        ajoutcancelEtudiantJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurEtudiant.cancelEtudiant();
            }
        });

        ajoutcancelEtudiantJButton.setEnabled(false);

        numetud = new JTextField(controleurEtudiant.getNumNouvelEtudiantModel(),"",10);
        nometud = new JTextField(controleurEtudiant.getNomNouvelEtudiantModel(),"",10);
        prenometud = new JTextField(controleurEtudiant.getPrenomNouvelEtudiantModel(),"",10);
        groupetud = new JTextField(controleurEtudiant.getGroupeNouvelEtudiantModel(),"",10);
        mdpEtudiant = new JPasswordField(controleurEtudiant.getMdpNouvelEtudiantModel(),"",10);

        etudiantSuppressionPanel.revalidate();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();

        // Ajout d'un etudiant
        etudiantAjoutPanel.setBorder(BorderFactory.createTitledBorder("Ajout"));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(numetudlabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(numetud, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(nometudlabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(nometud, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(prenometudlabel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(prenometud, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(groupetudlabel, c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(groupetud, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(mdpEtudiantlabel,c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        etudiantAjoutPanel.add(mdpEtudiant,c);

        c.gridx = 0;
        c.gridy = 5;
        etudiantAjoutPanel.add(ajoutOkEtudiantJButton, c);

        c.gridx = 1;
        c.gridy = 5;
        etudiantAjoutPanel.add(ajoutcancelEtudiantJButton, c);

        // Suppression d'un etudiant
        etudiantSuppressionPanel.setBorder(BorderFactory.createTitledBorder("Suppression"));

        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.weighty = 0.8;
        c.gridx = 0;
        c.gridy = 2;
        etudiantSuppressionPanel.add(etudiantSuppressionJList, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 1;
        etudiantSuppressionPanel.add(supprimerEtudiantJButton, c);

        //Ajout des panel de suppression et  d'ajout
        getContentPane().setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(etudiantSuppressionPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(etudiantAjoutPanel, c);

        setVisible(true);
    }

    public void setCreationEtudiant(boolean creationEtudiantok){
        ajoutOkEtudiantJButton.setEnabled(creationEtudiantok);
        ajoutcancelEtudiantJButton.setEnabled(creationEtudiantok);
    }

    public void setSuppressionEtudiant(boolean suppressionEtudiantok){
        supprimerEtudiantJButton.setEnabled(suppressionEtudiantok);

    }

}