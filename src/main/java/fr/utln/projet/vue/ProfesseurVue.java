package fr.utln.projet.vue;

import fr.utln.projet.controleur.ProfesseurControleur;
import fr.utln.projet.modele.ProfesseurListModele;
import fr.utln.projet.modele.ProfesseurModele;
import fr.utln.projet.utilisateur.Professeur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;


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

public class ProfesseurVue extends Fenetre{
    private final ProfesseurModele professeurModele;
    private final ProfesseurControleur professeurControleur;
    private MenuProfRefVue menuProfRefVue;

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

    private static JPanel professeurDetailPanel = new JPanel(new GridBagLayout());
    private final JButton modifierProfesseurJBouton = new JButton("valider");
    private final JButton cancelModifierProfesseurJButton = new JButton("annuler ");

    private final JComboBox<Professeur> profeseurDetailJComboBox;

    private static JTextField numProfDetailTextField;
    private static JTextField nomProfDetailTextField;
    private static JTextField prenomProfDetailTextField;

    private final JLabel numProfDetaillabel = new JLabel(" id Professeur :");;
    private final JLabel nomProfDetaillabel = new JLabel("Nom :");
    private final JLabel prenomProfDetaillabel = new JLabel("prenom :");




    public ProfesseurVue(ProfesseurModele professeurModele,final MenuProfRefVue menuProfRefVue) {

        super();
        setTitle("CRUD  professeur");

        this.professeurModele = professeurModele;
        this.professeurControleur = new ProfesseurControleur(this, professeurModele);
        this.professeurListModele = new ProfesseurListModele(professeurControleur.getListProfesseur());
        this.menuProfRefVue = menuProfRefVue;

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
                if (!(professeurControleur.deleteProfesseur(professeurtSuppressionJList.getSelectedValue()))){
                    JOptionPane.showMessageDialog(professeurAjoutPanel, "Le professeur donne un cours, remplacez le avant de le suprimer  ");
                }
                suppressionProfesseurJButton.setEnabled(false);
            }
        });

        // variable ajout professeur
        nomProfesseur = new JTextField(professeurControleur.getNomNouvelProfesseurModel(),"",10);
        prenomprofesseur = new JTextField(professeurControleur.getPrenomNouvelProfesseurModel(),"",10);
        loginProfesseur = new JTextField(professeurControleur.getLoginNouvelProfesseurModel(),"",10);
        mdpProfesseur = new JTextField(professeurControleur.getMdpNouvelProfesseurModel(),"",10);

        //variable modification professeur
        numProfDetailTextField = new JTextField(professeurControleur.getIdProfesseurModel(),"",10);
        nomProfDetailTextField = new JTextField(professeurControleur.getNomProfesseurModel(),"",10);
        prenomProfDetailTextField = new JTextField(professeurControleur.getPrenomProfesseurModel(),"",10);

        numProfDetailTextField.setEnabled(false);
        nomProfDetailTextField.setEnabled(false);
        prenomProfDetailTextField.setEnabled(false);

        cancelModifierProfesseurJButton.setEnabled(false);
        cancelModifierProfesseurJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                montrerDetail(profeseurDetailJComboBox.getItemAt(profeseurDetailJComboBox.getSelectedIndex()));
            }
        });

        modifierProfesseurJBouton.setEnabled(false);
        modifierProfesseurJBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professeurControleur.modifierProfesseur();
            }
        });

        profeseurDetailJComboBox = new JComboBox<Professeur>(professeurListModele);

        profeseurDetailJComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        montrerDetail(null);
                        break;
                    case ItemEvent.SELECTED:
                        montrerDetail(profeseurDetailJComboBox.getItemAt(profeseurDetailJComboBox.getSelectedIndex()));
                        break;
                }

            }
        });

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
                if (loginProfesseur.getText().charAt(0) == 'p')
                {
                    professeurControleur.persisteProfesseur();
                }
                else{
                    JOptionPane.showMessageDialog(professeurAjoutPanel, "Le login doit commencer par p ");
                }

            }
        });


        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                menuProfRefVue.setTrueBoutonProf();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                menuProfRefVue.setTrueBoutonProf();
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

        professeurDetailPanel.setBorder(BorderFactory.createTitledBorder("Détails professeur"));

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        professeurDetailPanel.add(profeseurDetailJComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        professeurDetailPanel.add(numProfDetaillabel, c);

        c.gridx = 1;
        c.gridy = 1;
        professeurDetailPanel.add(numProfDetailTextField, c);

        c.gridx = 0;
        c.gridy = 2;
        professeurDetailPanel.add(nomProfDetaillabel, c);

        c.gridx = 1;
        c.gridy = 2;
        professeurDetailPanel.add(nomProfDetailTextField, c);

        c.gridx = 0;
        c.gridy = 3;
        professeurDetailPanel.add(prenomProfDetaillabel, c);

        c.gridx = 1;
        c.gridy = 3;
        professeurDetailPanel.add(prenomProfDetailTextField, c);

        c.gridx = 0;
        c.gridy = 4;
        professeurDetailPanel.add(modifierProfesseurJBouton,c);

        c.gridy = 4;
        c.gridx = 1;
        professeurDetailPanel.add(cancelModifierProfesseurJButton,c);

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

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        getContentPane().add(professeurDetailPanel, c);


        setVisible(true);
    }

    /**
     * Methode rendant cliquable ou non le bouton de supression de professeur
     *
     * @param b
     * @autor CLAIN CYRIL
     */
    private void setSuppressionProfesseurJButton(boolean b) {
        suppressionProfesseurJButton.setEnabled(b);

    }
    /**
     * Methode rendant cliquable ou non les boutons d'ajout de professeur
     *
     * @param b
     * @autor CLAIN CYRIL
     */
    public void setCreationProfesseur(boolean b) {
        ajoutOkProfesseurJButton.setEnabled(b);
        ajoutCancelProfesseurJButton.setEnabled(b);

    }

    /**
     * Methode affichant le detail d'un professeur dans la jcombobox
     *
     * @param professeur
     * @autor CLAIN CYRIL
     */
    public void montrerDetail(Professeur professeur){
        if (professeur == null){
            numProfDetailTextField.setText("");
            nomProfDetailTextField.setText("");
            prenomProfDetailTextField.setText("");
        }
        else {
            System.out.println("prof : "+professeur.getIdprofesseur());
            numProfDetailTextField.setText(professeur.getIdprofesseur());
            numProfDetailTextField = new JTextField(professeurControleur.getIdProfesseurModel(), professeur.getIdprofesseur(), 10);

            nomProfDetailTextField.setText(professeur.getNom());
            nomProfDetailTextField = new JTextField(professeurControleur.getNomProfesseurModel(), professeur.getNom(), 10);

            prenomProfDetailTextField.setText(professeur.getPrenom());
            prenomProfDetailTextField = new JTextField(professeurControleur.getPrenomProfesseurModel(), professeur.getPrenom(), 10);
        }
    }

    /**
     * Methode rendant cliquable ou non les champs de professeur lie a la modification
     *
     * @param b
     * @autor CLAIN CYRIL
     */
    public void setModification(boolean b) {
        nomProfDetailTextField.setEnabled(true);
        prenomProfDetailTextField.setEnabled(b);
        modifierProfesseurJBouton.setEnabled(b);
        cancelModifierProfesseurJButton.setEnabled(b);
    }
}
