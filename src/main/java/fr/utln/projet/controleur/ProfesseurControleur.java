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
import fr.utln.projet.utilisateur.Professeur;
import fr.utln.projet.vue.ProfesseurVue;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.sql.SQLException;
import java.util.List;

public class ProfesseurControleur {
    private ProfesseurVue professeurVue;
    private ProfesseurModele professeurModele;

    // Vairable utilisé a l'ajout d'un nouveau professeur en bd
    private Document idNouvelProfesseurModel = new PlainDocument();
    private Document nomNouvelProfesseurModel = new PlainDocument();
    private Document prenomNouvelProfesseurModel = new PlainDocument();
    private Document loginNouvelProfesseurModel = new PlainDocument();
    private Document mdpNouvelProfesseurModel = new PlainDocument();

    // Vairable utilisé a la modification d'un professeur en bd
    private Document idProfesseurModel = new PlainDocument();
    private Document nomProfesseurModel = new PlainDocument();
    private Document prenomProfesseurModel = new PlainDocument();
    private Document loginProfesseurModel = new PlainDocument();
    private Document mdpProfesseurModel = new PlainDocument();


    public ProfesseurControleur(final ProfesseurVue professeurVue, ProfesseurModele professeurModele) {
        this.professeurVue = professeurVue;
        this.professeurModele = professeurModele;

        DocumentListener ecouteurchangementtexProfesseur = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {changedUpdate(e); }

            @Override
            public void removeUpdate(DocumentEvent e) {changedUpdate(e); }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Boolean conditionDAjout = (nomNouvelProfesseurModel.getLength() == 0)|| (prenomNouvelProfesseurModel.getLength() == 0) ||(loginNouvelProfesseurModel.getLength() == 0) || (mdpNouvelProfesseurModel.getLength() == 0);
                Boolean conditionModification = ((prenomNouvelProfesseurModel.getLength() == 0)|| (nomNouvelProfesseurModel.getLength() == 0) || (loginProfesseurModel.getLength() == 0) || mdpProfesseurModel.getLength() == 0);
                if (conditionDAjout)
                    professeurVue.setCreationProfesseur(false);
                else if (!conditionDAjout)
                    professeurVue.setCreationProfesseur(true);

                if (conditionModification)
                {
                    professeurVue.setModificationProfesseur(false);
                }
                else if (!conditionModification){
                    professeurVue.setModificationProfesseur(true);

                }
            }
        };
        idNouvelProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
        nomNouvelProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
        prenomNouvelProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
        loginNouvelProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
        mdpNouvelProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);

       idProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
       nomProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
       prenomProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
       loginProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
       mdpProfesseurModel.addDocumentListener(ecouteurchangementtexProfesseur);
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

            cancelProfesseur();
        }
        catch (BadLocationException e){
            System.out.println("erreur dans controleur");
            e.printStackTrace();
        }
    }

    /**
     * Methode d'appel de cancel  etudiant du modele permet d'annuler la saisie dans les documents
     * Les remet a zero.
     *
     * @author      CLAIN Cyril
     */
    public void cancelProfesseur() {

        try {
            nomNouvelProfesseurModel.remove(0, nomNouvelProfesseurModel.getLength());
            prenomNouvelProfesseurModel.remove(0, prenomNouvelProfesseurModel.getLength());
            loginNouvelProfesseurModel.remove(0, loginNouvelProfesseurModel.getLength());
            mdpNouvelProfesseurModel.remove(0, mdpNouvelProfesseurModel.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

    }

    public void deleteProfesseur(Professeur professeur) {
        try {
            professeurModele.deleteProfesseur(professeur);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ModificationProfesseur(){
        professeurModele.updateProfesseur();
    }

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

    public List<Professeur> getListProfesseur() {
        return(professeurModele.getProfesseurs());
    }

}
