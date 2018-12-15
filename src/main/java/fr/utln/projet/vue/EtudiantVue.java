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
import java.util.ResourceBundle;

/*
 * Nom de classe : EtudiantVue
 *
 * Description   : Partie visible de mon interface permet de voir les formulaire CRUD d'étudiant
 *
 * Version       : 2.4
 *
 * Date          : 15/12/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class EtudiantVue extends Fenetre  {

    private final MenuProfRefVue menuProfRefVue;
    private final ModeleEtudiant modeleEtudiant;
    private final ControleurEtudiant controleurEtudiant;

    private static EtudiantListModel etudiantListModel;
    private static GroupeListModele groupeListModele;

    private final JPanel etudiantSuppressionPanel = new JPanel(new GridBagLayout());
    private final JPanel etudiantAjoutPanel = new JPanel(new GridBagLayout());

    private ResourceBundle rbBouton = ResourceBundle.getBundle("textBouton");
    private ResourceBundle rbLabel = ResourceBundle.getBundle("textLabel");

    private final JList<Etudiant> etudiantSuppressionJList;

    private final JButton supprimerEtudiantJButton = new JButton(rbBouton.getString("Supprimer Etudiant"));

    private final JButton ajoutOkEtudiantJButton = new JButton(rbBouton.getString("Ajouter"));
    private final JButton ajoutcancelEtudiantJButton = new JButton(rbBouton.getString("Annuler"));

    private final JLabel groupetudlabel = new JLabel(rbLabel.getString("Groupe")+" :");
    private final JLabel numetudlabel = new JLabel(rbLabel.getString("Numero")+" :");
    private final JLabel nometudlabel = new JLabel(rbLabel.getString("Nom")+" :");
    private final JLabel prenometudlabel = new JLabel(rbLabel.getString("Prenom")+" :");
    private final JLabel mdpEtudiantlabel = new JLabel(rbLabel.getString("mdp")+" :");

    private final JTextField numetud;
    private final JTextField nometud;
    private final JTextField prenometud;
    private final JTextField groupetud;
    private final JTextField mdpEtudiant;

    private static JPanel etudiantDetailPanel = new JPanel(new GridBagLayout());
    private final JButton modifierEtudiantJBouton = new JButton(rbBouton.getString("modifier"));
    private final JButton cancelModifierEtudiantJButton = new JButton( rbBouton.getString("Annuler"));

    private final JComboBox<Etudiant> etudiantDetailJComboBox;
    private final JComboBox<String> groupeEtudiantJcomboBox;

    private static JTextField numEtudDetailTextField;
    private static JTextField nomEtudDetailTextField;
    private static JTextField prenomEtudDetailTextField;
    private static JTextField groupeEtudDetailTextField;

    private final JLabel numEtudDetaillabel = new JLabel(rbLabel.getString("Numero")+" :");
    private final JLabel nomEtudDetaillabel = new JLabel(rbLabel.getString("Nom")+" :");
    private final JLabel prenomEtudDetaillabel = new JLabel(rbLabel.getString("Prenom")+" :");
    private final JLabel groupeEtudDetaillabel = new JLabel(rbLabel.getString("Groupe")+" :");



    public EtudiantVue(ModeleEtudiant modeleEtudiant, final MenuProfRefVue menuProfRefVue) throws HeadlessException {

        super();
        setTitle("CRUD  etudiants");

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
                supprimerEtudiantJButton.setEnabled(false);

            }
        });


        ajoutOkEtudiantJButton.setEnabled(false);
        ajoutOkEtudiantJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (testNumEtud()){
                    controleurEtudiant.persisteEtudiant();
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

        /* bloque l'écriture dans les champs text tant qu'il n'y as pas d'étudiant choisit */
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

        etudiantDetailPanel.setBorder(BorderFactory.createTitledBorder("Modifier etudiant"));

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

        c.gridy = 06;
        etudiantDetailPanel.add(new JLabel(rbLabel.getString("taille maximum sauvegarder 15")),c);



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


    /**
     * Methode qui affiche le detail d'un étudiant dans la jcombobox
     *
     * @param etudiant
     * @autor CLAIN CYRIL
     */
    public void montrerDetail(Etudiant etudiant){
        if (etudiant == null){
            numEtudDetailTextField.setText("");
            nomEtudDetailTextField.setText("");
            prenomEtudDetailTextField.setText("");
            groupeEtudDetailTextField.setText("");
        }
        else{
            numEtudDetailTextField.setText(etudiant.getNumetud());
            numEtudDetailTextField = new JTextField(controleurEtudiant.getNumEtudiantModel(),etudiant.getNumetud(),15);

            nomEtudDetailTextField.setText(etudiant.getNom());
            nomEtudDetailTextField = new JTextField(controleurEtudiant.getNomEtudiantModel(),etudiant.getNom(),15);

            prenomEtudDetailTextField.setText(etudiant.getPrenom());
            prenomEtudDetailTextField = new JTextField(controleurEtudiant.getPrenomEtudiantModel(),etudiant.getPrenom(),15);

            groupeEtudDetailTextField.setText(etudiant.getGroupe());
            groupeEtudDetailTextField = new JTextField(controleurEtudiant.getGroupeEtudiantModel(),etudiant.getGroupe(),15);
        }
    }

    /**
     * Methode de vérification des règles de gestion
     *
     * @return boolean
     * @autor CLAIN CYRIL
     */
    public boolean testNumEtud(){
        boolean test = true;
        if (numetud.getText().charAt(0) != 'e'){
            JOptionPane.showMessageDialog(etudiantAjoutPanel, "Le numero étudiant doit commencé par e (ex: e123)");
            test = false;
        }

        if (!groupeListModele.contains(groupetud.getText())){
            JOptionPane.showMessageDialog(etudiantAjoutPanel, "Choisir un groupe existant(ex : SI1A)");
            test = false;
        }

        if (controleurEtudiant.numetudDansBase()){
            JOptionPane.showMessageDialog(etudiantAjoutPanel, "Ce numero etudiant est deja pris");
            test = false;
        }
        System.out.println("test : "+test);
        System.out.println("id : "+controleurEtudiant.numetudDansBase());

        return test;

    }

}