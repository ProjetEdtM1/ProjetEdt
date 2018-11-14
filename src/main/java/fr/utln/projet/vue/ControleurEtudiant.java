package fr.utln.projet.vue;

import fr.utln.projet.utilisateur.Etudiant;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.sql.SQLException;

/*
 * Nom de classe : ControleurEtudiant
 *
 * Description   : Controlleur Etudiant (MVC) previens le modele si il y a une action sur la vue
 *
 * Version       : 1.1
 *
 * Date          : 12/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class ControleurEtudiant {
    private EtudiantVue etudiantVue;
    private ModeleEtudiant modeleEtudiant;

    private Document numNouvelEtudiantModel = new PlainDocument();
    private Document nomNouvelEtudiantModel = new PlainDocument();
    private Document prenomNouvelEtudiantModel = new PlainDocument();
    private Document groupeNouvelEtudiantModel = new PlainDocument();
    private Document mdpNouvelEtudiantModel = new PlainDocument();

    public Document getMdpNouvelEtudiantModel() {
        return mdpNouvelEtudiantModel;
    }


    public ControleurEtudiant(final EtudiantVue ajoutetudiantVue, ModeleEtudiant modeleEtudiant) {
        this.etudiantVue = ajoutetudiantVue;
        this.modeleEtudiant = modeleEtudiant;

        DocumentListener ecouteurChangementTexte = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if ((nomNouvelEtudiantModel.getLength() == 0)|| (prenomNouvelEtudiantModel.getLength() == 0) || (groupeNouvelEtudiantModel.getLength() == 0) || (numNouvelEtudiantModel.getLength() == 0) || (mdpNouvelEtudiantModel.getLength() == 0))
                    etudiantVue.setCreationEtudiant(false);
                else
                    etudiantVue.setCreationEtudiant(true);

            }
        };
        numNouvelEtudiantModel.addDocumentListener(ecouteurChangementTexte);
        nomNouvelEtudiantModel.addDocumentListener(ecouteurChangementTexte);
        prenomNouvelEtudiantModel.addDocumentListener(ecouteurChangementTexte);
        groupeNouvelEtudiantModel.addDocumentListener(ecouteurChangementTexte);
        mdpNouvelEtudiantModel.addDocumentListener(ecouteurChangementTexte);


    }

    /**
     * Methode d'appel d'ajout etudiant du modele
     *
     * @author      CLAIN Cyril
     */

    public void ajouterEtudiant() {
        try {
            System.out.println(numNouvelEtudiantModel.getText(0, numNouvelEtudiantModel.getLength()));

            modeleEtudiant.ajouterEtudiant(
                    numNouvelEtudiantModel.getText(0, numNouvelEtudiantModel.getLength()),
                    nomNouvelEtudiantModel.getText(0, nomNouvelEtudiantModel.getLength()),
                    prenomNouvelEtudiantModel.getText(0, prenomNouvelEtudiantModel.getLength()),
                    mdpNouvelEtudiantModel.getText(0, mdpNouvelEtudiantModel.getLength()),
                    groupeNouvelEtudiantModel.getText(0, groupeNouvelEtudiantModel.getLength())

            );
            numNouvelEtudiantModel.remove(0, numNouvelEtudiantModel.getLength());
            nomNouvelEtudiantModel.remove(0, nomNouvelEtudiantModel.getLength());
            prenomNouvelEtudiantModel.remove(0, prenomNouvelEtudiantModel.getLength());
            mdpNouvelEtudiantModel.remove(0, mdpNouvelEtudiantModel.getLength());
            groupeNouvelEtudiantModel.remove(0, groupeNouvelEtudiantModel.getLength());
        }
        catch (BadLocationException e){
            System.out.println("erreur dans controleur");
            e.printStackTrace();
        }
    }


    /**
     * Methode d'appel de suppression etudiant du modele
     *
     * @author      CLAIN Cyril
     */

    public void deleteEtudiant(Etudiant etudiant){
        try{
            modeleEtudiant.deleteEtudiant(etudiant);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode qui annule la saisie de text
     *
     * @author      CLAIN Cyril
     */

    public void cancelEtudiant() {
        try {
            numNouvelEtudiantModel.remove(0, numNouvelEtudiantModel.getLength());
            nomNouvelEtudiantModel.remove(0, nomNouvelEtudiantModel.getLength());
            prenomNouvelEtudiantModel.remove(0, prenomNouvelEtudiantModel.getLength());
            mdpNouvelEtudiantModel.remove(0, mdpNouvelEtudiantModel.getLength());
            groupeNouvelEtudiantModel.remove(0, groupeNouvelEtudiantModel.getLength());

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public Document getNumNouvelEtudiantModel() {
        return numNouvelEtudiantModel;
    }

    public Document getNomNouvelEtudiantModel() {
        return nomNouvelEtudiantModel;
    }

    public Document getPrenomNouvelEtudiantModel() {
        return prenomNouvelEtudiantModel;
    }

    public Document getGroupeNouvelEtudiantModel() {
        return groupeNouvelEtudiantModel;
    }

}