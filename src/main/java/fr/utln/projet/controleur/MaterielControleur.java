package fr.utln.projet.controleur;

import com.sun.deploy.panel.NumberDocument;
import fr.utln.projet.modele.MaterielModele;
import fr.utln.projet.utilisateur.Materiel;
import fr.utln.projet.vue.MaterielVue;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.sql.SQLException;
import java.util.List;

/*
 * Nom de classe : MaterielControleur
 *
 * Description   : Controlleur materiel (MVC) previens le modele si il y a une action sur la vue
 *
 * Version       : 1.0
 *
 * Date          : 24/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class MaterielControleur {


    private MaterielVue materielVue;
    private MaterielModele materielModele;

    private Document numNouveauMateriel = new NumberDocument();
    private Document nomNouveauMateriel = new PlainDocument();

    private Document IdMateriel = new NumberDocument();
    private Document nomMateriel = new PlainDocument();


    public MaterielControleur(final MaterielVue materielVue, MaterielModele materielModele) {
        this.materielVue = materielVue;
        this.materielModele = materielModele;

        DocumentListener ecouteurchangementtexMateriel = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {changedUpdate(e); }

            @Override
            public void removeUpdate(DocumentEvent e) {changedUpdate(e); }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if ((numNouveauMateriel.getLength() == 0) || (nomNouveauMateriel.getLength() == 0))
                    materielVue.setCreationMateriel(false);
                else
                    materielVue.setCreationMateriel(true);
            }
        };
        numNouveauMateriel.addDocumentListener(ecouteurchangementtexMateriel);
        nomNouveauMateriel.addDocumentListener(ecouteurchangementtexMateriel);
        nomMateriel.addDocumentListener(ecouteurchangementtexMateriel);
        IdMateriel.addDocumentListener(ecouteurchangementtexMateriel);

    }

    /**
     * Methode d'appel d'ajout materiel du modele
     *
     * @author      CLAIN Cyril
     */

    public boolean persisteMateriel() {
        boolean resultat = false;
        try {

            resultat = materielModele.persisteMateriel(
                    numNouveauMateriel.getText(0, numNouveauMateriel.getLength()),
                    nomNouveauMateriel.getText(0,nomNouveauMateriel.getLength())
                    );
            cancelMateriel();
        }
        catch (BadLocationException e){
            System.out.println("erreur dans controleur");
            e.printStackTrace();
        }
        return resultat;
    }

    /**
     * Methode d'appel de cancel  materiel du modele permet d'annuler la saisie dans les documents
     * Les remet a zero.
     *
     * @author      CLAIN Cyril
     */
    public void cancelMateriel() {

        try {
            numNouveauMateriel.remove(0, numNouveauMateriel.getLength());
            nomNouveauMateriel.remove(0,nomNouveauMateriel.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    public void deleteMateriel(Materiel materiel) {
        try {
            materielModele.deleteMateriel(materiel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Document getNumNouveauMateriel() {
        return numNouveauMateriel;
    }

    public void setNumNouveauMateriel(Document numNouveauMateriel) {
        this.numNouveauMateriel = numNouveauMateriel;
    }

    public List<Materiel> getListMateriel() {
        return(materielModele.getMateriel());
    }

    public Document getNomNouveauMateriel() {
        return nomNouveauMateriel;
    }

    public void setNomNouveauMateriel(Document nomNouveauMateriel) {
        nomNouveauMateriel = nomNouveauMateriel;
    }

    public Document getIdMateriel() {
        return IdMateriel;
    }

    public void setIdMateriel(Document idMateriel) {
        IdMateriel = idMateriel;
    }

    public Document getNomMateriel() {
        return nomMateriel;
    }

    public void setNomMateriel(Document nomMateriel) {
        this.nomMateriel = nomMateriel;
    }
}