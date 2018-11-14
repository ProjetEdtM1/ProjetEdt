package fr.utln.projet.controleur;

import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.vue.ModuleVUE;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuleControleur{
    private ModuleVUE moduleVUE;
    private ModuleModele moduleModele;

    private Document intituleNouveauModuleModele = new PlainDocument();
    private Document nbHCmNouveauModuleModele = new PlainDocument();
    private Document nbHTdNouveauModuleModele = new PlainDocument();
    private Document nbHTpNouveauModuleModele = new PlainDocument();


    public ModuleControleur(final ModuleVUE moduleVue, ModuleModele moduleModele) {
        this.moduleVUE = moduleVUE;
        this.moduleModele = moduleModele;

        DocumentListener ecouterCHangementTexte = new DocumentListener() {
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
                if(intituleNouveauModuleModele.getLength() == 0) {
                    moduleVUE.setBoutonAjouter(false);
                }
            }
        };

    }

    /* methode pour ajouter un Module */

    public void ajoueterModule() {
        try{
            moduleModele.ajouterModule();
        }

    }
}
