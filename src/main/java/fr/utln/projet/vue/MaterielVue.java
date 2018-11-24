package fr.utln.projet.vue;

import fr.utln.projet.controleur.MaterielControleur;
import fr.utln.projet.modele.MaterielListModel;
import fr.utln.projet.modele.MaterielModele;
import fr.utln.projet.utilisateur.Materiel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;


/*
 * Nom de classe : MaterielVue
 *
 * Description   : Partie visible de mon interface permet de voir les formulaire CRUD d'un materiel
 *
 * Version       : 1.0
 *
 * Date          : 24/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class MaterielVue extends JFrame {

    private final MaterielModele materielModele;
    private final MaterielControleur materielControleur;

    private static MaterielListModel materielListModel;

    private final JPanel materielSuppressionPanel = new JPanel(new GridBagLayout());
    private final JPanel materielAjoutPanel = new JPanel(new GridBagLayout());

    private final JList<Materiel> materielJList;

    private final JButton suppressionMaterielJButton = new JButton(" Supprimer");
    private final JButton ajoutOkMaterielJButton = new JButton(" Ajouter");
    private final JButton ajoutCancelMaterielJButton = new JButton("cancel");

    private final JLabel numMateriellabel = new JLabel("Numero de materiel : ");

    private final JTextField numMateriel;


    public MaterielVue(MaterielModele materielModele) {

        super("CRUD  materiel");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        setSize(width / 2, height / 2);

        this.materielModele = materielModele;
        this.materielControleur = new MaterielControleur(this, materielModele);
        this.materielListModel = new MaterielListModel(materielControleur.getListMateriel());

        materielModele.addObserver(materielListModel);

        materielJList = new JList<>(materielListModel);
        materielJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setSuppressionMaterielJButton((materielJList.getSelectedValue() != null));
            }
        });
        suppressionMaterielJButton.setEnabled(false);
        suppressionMaterielJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materielControleur.deleteMateriel(materielJList.getSelectedValue());
                suppressionMaterielJButton.setEnabled(false);
            }
        });
        numMateriel = new JTextField(materielControleur.getNumNouvelleMateriel(),"",10);

        ajoutCancelMaterielJButton.setEnabled(false);
        ajoutCancelMaterielJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materielControleur.cancelMateriel();
            }
        });
        ajoutOkMaterielJButton.setEnabled(false);
        ajoutOkMaterielJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(materielControleur.persisteMateriel()))
                    JOptionPane.showMessageDialog(materielAjoutPanel, "Le numero de materiel est deja pris ");

            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();


        // Ajout d'un materiel
        materielAjoutPanel.setBorder(BorderFactory.createTitledBorder("Ajout"));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        materielAjoutPanel.add(numMateriellabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        materielAjoutPanel.add(numMateriel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        materielAjoutPanel.add(ajoutOkMaterielJButton, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        materielAjoutPanel.add(ajoutCancelMaterielJButton, c);

        //Suppression materiel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 1;
        materielSuppressionPanel.add(suppressionMaterielJButton, c);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.weighty = 0.8;
        c.gridx = 0;
        c.gridy = 2;
        materielSuppressionPanel.add(materielJList, c);

        //Ajout des panel de suppression et  d'ajout consultation
        getContentPane().setLayout(new GridBagLayout());


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(materielSuppressionPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(materielAjoutPanel, c);


        setVisible(true);
    }
    private void setSuppressionMaterielJButton(boolean b) {
        suppressionMaterielJButton.setEnabled(b);
    }
    public void setCreationSalle(boolean b) {
        ajoutOkMaterielJButton.setEnabled(b);
        ajoutCancelMaterielJButton.setEnabled(b);

    }
}