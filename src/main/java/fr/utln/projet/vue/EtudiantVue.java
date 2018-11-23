package fr.utln.projet.vue;
import fr.utln.projet.controleur.ControleurEtudiant;
import fr.utln.projet.modele.EtudiantListModel;
import fr.utln.projet.modele.GroupeListModele;
import fr.utln.projet.modele.ModeleEtudiant;
import fr.utln.projet.utilisateur.Etudiant;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

/*
 * Nom de classe : EtudiantVue
 *
 * Description   : Partie visible de mon interface permet de voir les formulaire CRUD d'étudiant
 *
 * Version       : 2.3
 *
 * Date          : 21/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class EtudiantVue extends JFrame  {

    private final MenuProfRefVue menuProfRefVue;
    private final ModeleEtudiant modeleEtudiant;
    private final ControleurEtudiant controleurEtudiant;

    private static EtudiantListModel etudiantListModel;
    private static GroupeListModele groupeListModele;

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
    private final JTextField mdpEtudiant;

    private static JPanel etudiantDetailPanel = new JPanel(new GridBagLayout());
    private final JButton modifierEtudiantJBouton = new JButton("valider");
    private final JButton cancelModifierEtudiantJButton = new JButton("annuler ");

    private final JComboBox<Etudiant> etudiantDetailJComboBox;
    private final JComboBox<String> groupeEtudiantJcomboBox;

    private static JTextField numEtudDetailTextField;
    private static JTextField nomEtudDetailTextField;
    private static JTextField prenomEtudDetailTextField;
    private static JTextField groupeEtudDetailTextField;

    private final JLabel numEtudDetaillabel = new JLabel(" num etud :");;
    private final JLabel nomEtudDetaillabel = new JLabel("Nom :");
    private final JLabel prenomEtudDetaillabel = new JLabel("prenom :");
    private final JLabel groupeEtudDetaillabel = new JLabel(" groupe :");



    public EtudiantVue(ModeleEtudiant modeleEtudiant, final MenuProfRefVue menuProfRefVue) throws HeadlessException {

        super("CRUD  etudiants");

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        setSize(3 * width / 4, 3 * height / 4);

        this.modeleEtudiant = modeleEtudiant;
        this.controleurEtudiant = new ControleurEtudiant(this,modeleEtudiant);
        this.etudiantListModel = new EtudiantListModel(controleurEtudiant.getListEtudiant());
        this.groupeListModele = new GroupeListModele(controleurEtudiant.getListGroupe());
        this.menuProfRefVue = menuProfRefVue;


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
                controleurEtudiant.deleteEtudiant(etudiantSuppressionJList.getSelectedValue());
                setSuppressionEtudiant((etudiantSuppressionJList.getSelectedValue() == null));

            }
        });


        ajoutOkEtudiantJButton.setEnabled(false);
        ajoutOkEtudiantJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!controleurEtudiant.ajouterEtudiant()){
                    JOptionPane.showMessageDialog(etudiantAjoutPanel, "Choisir un groupe existant(ex : SI1A)");
                }
            }
        });

        ajoutcancelEtudiantJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurEtudiant.cancelEtudiant();
            }
        });

        ajoutcancelEtudiantJButton.setEnabled(false);

        // Champ d'ajout d'étudiant
        numetud = new JTextField(controleurEtudiant.getNumNouvelEtudiantModel(),"",10);
        nometud = new JTextField(controleurEtudiant.getNomNouvelEtudiantModel(),"",10);
        prenometud = new JTextField(controleurEtudiant.getPrenomNouvelEtudiantModel(),"",10);
        groupetud = new JTextField(controleurEtudiant.getGroupeNouvelEtudiantModel(),"",10);
        mdpEtudiant = new JTextField(controleurEtudiant.getMdpNouvelEtudiantModel(),"",10);


        // Champ de modification  d'étudiant
        numEtudDetailTextField = new JTextField(controleurEtudiant.getNumEtudiantModel(),"",10);
        nomEtudDetailTextField = new JTextField(controleurEtudiant.getNomEtudiantModel(),"",10);
        prenomEtudDetailTextField = new JTextField(controleurEtudiant.getPrenomEtudiantModel(),"",10);
        groupeEtudDetailTextField= new JTextField(controleurEtudiant.getGroupeEtudiantModel(),"",10);

        groupeEtudiantJcomboBox = new JComboBox<String>(groupeListModele);
        groupeEtudiantJcomboBox.setEnabled(false);
        groupeEtudiantJcomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        modifierEtudiantJBouton.setEnabled(false);
                        break;
                    case ItemEvent.SELECTED:
                        modifierEtudiantJBouton.setEnabled(true);
                        modifierGroupe(groupeEtudiantJcomboBox.getItemAt(groupeEtudiantJcomboBox.getSelectedIndex()));
                        break;
                }

            }
        });
        etudiantDetailJComboBox = new JComboBox<Etudiant>(etudiantListModel);

        etudiantDetailJComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        montrerDetail(null);
                        break;
                    case ItemEvent.SELECTED:
                        montrerDetail(etudiantDetailJComboBox.getItemAt(etudiantDetailJComboBox.getSelectedIndex()));
                        break;
                }

            }
        });


        modifierEtudiantJBouton.setEnabled(false);
        modifierEtudiantJBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurEtudiant.modifierEtudiant();
                etudiantDetailJComboBox.setSelectedIndex(-1);// unselect value in JCOMBOBOX
                groupeEtudiantJcomboBox.setSelectedIndex(-1);


            }
        });

        cancelModifierEtudiantJButton.setEnabled(false);
        cancelModifierEtudiantJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                montrerDetail(etudiantDetailJComboBox.getItemAt(etudiantDetailJComboBox.getSelectedIndex()));
            }
        });

        /* bloque l'écriture dans les champs text me servira a afficher suelement */
        numEtudDetailTextField.setEnabled(false);
        nomEtudDetailTextField.setEnabled(false);
        prenomEtudDetailTextField.setEnabled(false);
        groupeEtudDetailTextField.setEnabled(false);



        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                menuProfRefVue.setTrueBoutonEtudiant();
            }

            @Override
            public void windowClosed(WindowEvent e) {
               menuProfRefVue.setTrueBoutonEtudiant();
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

        // placement des widget
        GridBagConstraints c = new GridBagConstraints();

        //Le détail d'un Etudiant

        etudiantDetailPanel.setBorder(BorderFactory.createTitledBorder("Détails etudiant"));

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        etudiantDetailPanel.add(etudiantDetailJComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        etudiantDetailPanel.add(numEtudDetaillabel, c);

        c.gridx = 1;
        c.gridy = 1;
        etudiantDetailPanel.add(numEtudDetailTextField, c);

        c.gridx = 0;
        c.gridy = 2;
        etudiantDetailPanel.add(nomEtudDetaillabel, c);

        c.gridx = 1;
        c.gridy = 2;
        etudiantDetailPanel.add(nomEtudDetailTextField, c);

        c.gridx = 0;
        c.gridy = 3;
        etudiantDetailPanel.add(prenomEtudDetaillabel, c);

        c.gridx = 1;
        c.gridy = 3;
        etudiantDetailPanel.add(prenomEtudDetailTextField, c);

        c.gridx = 0;
        c.gridy = 4;
        etudiantDetailPanel.add(groupeEtudDetaillabel, c);

        c.gridx = 1;
        c.gridy = 4;
        etudiantDetailPanel.add(groupeEtudiantJcomboBox,c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        etudiantDetailPanel.add(cancelModifierEtudiantJButton,c);

        c.gridy = 5;
        c.gridx = 0;
        etudiantDetailPanel.add(modifierEtudiantJBouton,c);




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

        //Ajout des panel de suppression et  d'ajout consultation
        getContentPane().setLayout(new GridBagLayout());


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(etudiantSuppressionPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(etudiantAjoutPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        getContentPane().add(etudiantDetailPanel, c);


        setVisible(true);

    }

    /**
     * Methode modifiant le groupe en fonction de groupe existant ( foreign key)
     *
     * @param itemAt
     *
     * @author CLAIN CYRIL
     */
    private void modifierGroupe(String itemAt) {
        groupeEtudDetailTextField.setText(itemAt);
        groupeEtudDetailTextField= new JTextField(controleurEtudiant.getGroupeEtudiantModel(),itemAt,10);
    }

    public void setCreationEtudiant(boolean creationEtudiantok){
        ajoutOkEtudiantJButton.setEnabled(creationEtudiantok);
        ajoutcancelEtudiantJButton.setEnabled(creationEtudiantok);
    }

    public void setSuppressionEtudiant(boolean suppressionEtudiantok){
        supprimerEtudiantJButton.setEnabled(suppressionEtudiantok);

    }

    /**
     * methode qui permet la saisie de text dans le JTextfield pour modifier un étudiant
     *
     * @param modificationEtudiantok
     *
     * @autor CLAIN CYRIL
     */
    public void setModificationEtudiant(boolean modificationEtudiantok) {
        prenomEtudDetailTextField.setEnabled(modificationEtudiantok);
        nomEtudDetailTextField.setEnabled(true);
        cancelModifierEtudiantJButton.setEnabled(modificationEtudiantok);
        groupeEtudiantJcomboBox.setEnabled(modificationEtudiantok);

    }

    public void montrerDetail(Etudiant etudiant){
        if (etudiant == null){
            numEtudDetailTextField.setText("");
            nomEtudDetailTextField.setText("");
            prenomEtudDetailTextField.setText("");
            groupeEtudDetailTextField.setText("");
        }
        else{
            numEtudDetailTextField.setText(etudiant.getNumetud());
            numEtudDetailTextField= new JTextField(controleurEtudiant.getNumEtudiantModel(),etudiant.getNumetud(),10);

            nomEtudDetailTextField.setText(etudiant.getNom());
            nomEtudDetailTextField= new JTextField(controleurEtudiant.getNomEtudiantModel(),etudiant.getNom(),10);

            prenomEtudDetailTextField.setText(etudiant.getPrenom());
            prenomEtudDetailTextField= new JTextField(controleurEtudiant.getPrenomEtudiantModel(),etudiant.getPrenom(),10);

            groupeEtudDetailTextField.setText(etudiant.getGroupe());
            groupeEtudDetailTextField= new JTextField(controleurEtudiant.getGroupeEtudiantModel(),etudiant.getGroupe(),10);
        }
    }
}