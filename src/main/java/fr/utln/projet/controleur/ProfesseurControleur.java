package fr.utln.projet.controleur;


/*
 * Nom de classe : ProfesseurControleur
 *
 * Description   : Controlleur Professeur (MVC) previens le modele si il y a une action sur la vue
 *
 * Version       : 1.0
 *
 * Date          : 18/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

import fr.utln.projet.modele.ProfesseurModele;
import fr.utln.projet.vue.ProfesseurVue;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class ProfesseurControleur {
    private ProfesseurVue professeurVue;
    private ProfesseurModele professeurModele;

    private Document idNouvelProfesseurModel = new PlainDocument();
    private Document nomNouvelProfesseurModel = new PlainDocument();

    public Document getIdNouvelProfesseurModel() {
        return idNouvelProfesseurModel;
    }

    public void setIdNouvelProfesseurModel(Document idNouvelProfesseurModel) {
        this.idNouvelProfesseurModel = idNouvelProfesseurModel;
    }

    public Document getNomNouvelProfesseurModel() {
        return nomNouvelProfesseurModel;
    }

    public void setNomNouvelProfesseurModel(Document nomNouvelProfesseurModel) {
        this.nomNouvelProfesseurModel = nomNouvelProfesseurModel;
    }

    public Document getPrenomNouvelProfesseurModel() {
        return prenomNouvelProfesseurModel;
    }

    public void setPrenomNouvelProfesseurModel(Document prenomNouvelProfesseurModel) {
        this.prenomNouvelProfesseurModel = prenomNouvelProfesseurModel;
    }

    public Document getLoginNouvelProfesseurModel() {
        return loginNouvelProfesseurModel;
    }

    public void setLoginNouvelProfesseurModel(Document loginNouvelProfesseurModel) {
        this.loginNouvelProfesseurModel = loginNouvelProfesseurModel;
    }

    public Document getMdpNouvelProfesseurModel() {
        return mdpNouvelProfesseurModel;
    }

    public void setMdpNouvelProfesseurModel(Document mdpNouvelProfesseurModel) {
        this.mdpNouvelProfesseurModel = mdpNouvelProfesseurModel;
    }

    private Document prenomNouvelProfesseurModel = new PlainDocument();
    private Document loginNouvelProfesseurModel = new PlainDocument();
    private Document mdpNouvelProfesseurModel = new PlainDocument();

    public ProfesseurControleur(final ProfesseurVue professeurVue, ProfesseurModele professeurModele) {
        this.professeurVue = professeurVue;
        this.professeurModele = professeurModele;

        DocumentListener ecouteurchangementtexProfesseur = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if ((nomNouvelProfesseurModel.getLength() == 0)|| (prenomNouvelProfesseurModel.getLength() == 0) || (loginNouvelProfesseurModel.getLength() == 0) || (mdpNouvelProfesseurModel.getLength() == 0))
                    professeurVue.setCreationProfesseur(false);
                else
                    professeurVue.setCreationProfesseur(true);
            }
        };
        nomNouvelProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
        prenomNouvelProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
        loginNouvelProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
        mdpNouvelProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
    }

    /**
     * Methode d'appel d'ajout etudiant du modele
     *
     * @author      CLAIN Cyril
     */

    public void ajouterProfesseur() {
        try {

            professeurModele.persisteProfesseur(
                    nomNouvelProfesseurModel.getText(0, nomNouvelProfesseurModel.getLength()),
                    prenomNouvelProfesseurModel.getText(0, prenomNouvelProfesseurModel.getLength()),
                    loginNouvelProfesseurModel.getText(0, loginNouvelProfesseurModel.getLength()),
                    mdpNouvelProfesseurModel.getText(0, mdpNouvelProfesseurModel.getLength()));

        }
        catch (BadLocationException e){
            System.out.println("erreur dans controleur");
            e.printStackTrace();
        }
    }

}
