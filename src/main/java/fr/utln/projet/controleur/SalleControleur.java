package fr.utln.projet.controleur;

import com.sun.deploy.panel.NumberDocument;
import fr.utln.projet.modele.SalleModele;
import fr.utln.projet.utilisateur.Salle;
import fr.utln.projet.vue.SalleVue;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.sql.SQLException;
import java.util.List;

/*
 * Nom de classe : SalleControleur
 *
 * Description   : Controlleur Salle (MVC) previens le modele si il y a une action sur la vue
 *
 * Version       : 1.0
 *
 * Date          : 23/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

public class SalleControleur {

    private final SalleVue salleVue;
    private SalleModele SalleModele;

    private Document numNouvelleSalle = new NumberDocument();

    public SalleControleur(final SalleVue salleVue, SalleModele SalleModele) {
        this.salleVue = salleVue;
        this.SalleModele = SalleModele;

        DocumentListener ecouteurchangementtexSalle = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {changedUpdate(e); }

            @Override
            public void removeUpdate(DocumentEvent e) {changedUpdate(e); }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (numNouvelleSalle.getLength() == 0)
                    salleVue.setCreationSalle(false);
                else
                    salleVue.setCreationSalle(true);
            }
        };
        numNouvelleSalle.addDocumentListener(ecouteurchangementtexSalle);

    }

    /**
     * Methode d'appel d'ajout salle du modele
     *
     * @author      CLAIN Cyril
     */

    public boolean persisteSalle() {
        boolean resultat = false;
        try {

            resultat = SalleModele.persisteSalle(numNouvelleSalle.getText(0, numNouvelleSalle.getLength()));
            cancelSalle();
        }
        catch (BadLocationException e){
            System.out.println("erreur dans controleur");
            e.printStackTrace();
        }
        return resultat;
    }

    /**
     * Methode d'appel de cancel  salle du modele permet d'annuler la saisie dans les documents
     * Les remet a zero.
     *
     * @author      CLAIN Cyril
     */
    public void cancelSalle() {

        try {
            numNouvelleSalle.remove(0, numNouvelleSalle.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    public void deleteSalle(Salle salle) {
        try {
            SalleModele.deleteSalle(salle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Document getNumNouvelleSalle() {
        return numNouvelleSalle;
    }

    public void setNumNouvelleSalle(Document numNouvelleSalle) {
        this.numNouvelleSalle = numNouvelleSalle;
    }

    public List<Salle> getListSalle() {
        return(SalleModele.getSalles());
    }
}