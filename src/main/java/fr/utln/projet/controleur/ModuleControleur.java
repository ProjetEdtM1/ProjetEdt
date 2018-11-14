package fr.utln.projet.controleur;

import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.vue.ModuleVUE;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModuleControleur{
    private ModuleVUE moduleVUE;
    private ModuleModele moduleModele;

    private Document intituleNouveauModuleModele = new PlainDocument();
    private Document nbHCmNouveauModuleModele = new PlainDocument();
    private Document nbHTdNouveauModuleModele = new PlainDocument();
    private Document nbHTpNouveauModuleModele = new PlainDocument();


    public ModuleControleur(final ModuleVUE moduleVue, ModuleModele moduleModele) {
        this.moduleVUE = moduleVue;
        this.moduleModele = moduleModele;
        System.out.println("aa " + moduleModele);


        DocumentListener ecouterChangementTexte = new DocumentListener() {
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
                // System.out.println("YOOO");
                if(intituleNouveauModuleModele.getLength() == 0) {
                    moduleVUE.setBoutonAjouter(false);
                }
                else {
                    moduleVUE.setBoutonAjouter(true);
                }
            }
        };

        intituleNouveauModuleModele.addDocumentListener(ecouterChangementTexte);
        nbHCmNouveauModuleModele.addDocumentListener(ecouterChangementTexte);
        nbHTdNouveauModuleModele.addDocumentListener(ecouterChangementTexte);
        nbHTpNouveauModuleModele.addDocumentListener(ecouterChangementTexte);

    }

    /* methode pour ajouter un Module */

    public void ajouterModule() {
//        String a;

        try {
//            a = intituleNouveauModuleModele.getText(0, intituleNouveauModuleModele.getLength());
//
//            System.out.println("fef" + intituleNouveauModuleModele.getLength());
//
//
//            System.out.println("ff" + a);

//            System.out.println("intitule " + intituleNouveauModuleModele.getText(0, intituleNouveauModuleModele.getLength()));
//            System.out.println("nbHCm " + nbHCmNouveauModuleModele.getText(0, nbHCmNouveauModuleModele.getLength()));
//            System.out.println("nbHTd " + nbHTdNouveauModuleModele.getText(0, nbHTdNouveauModuleModele.getLength()));
//            System.out.println("nbHTp " + nbHTpNouveauModuleModele.getText(0, nbHTpNouveauModuleModele.getLength()));

            System.out.println("ff " + moduleModele);

            moduleModele.ajouterModule(
                    intituleNouveauModuleModele.getText(0, intituleNouveauModuleModele.getLength()),
                    nbHCmNouveauModuleModele.getText(0, nbHCmNouveauModuleModele.getLength()),
                    nbHTdNouveauModuleModele.getText(0, nbHTdNouveauModuleModele.getLength()),
                    nbHTpNouveauModuleModele.getText(0, nbHTpNouveauModuleModele.getLength())
            );

            intituleNouveauModuleModele.remove(0, intituleNouveauModuleModele.getLength());
            nbHCmNouveauModuleModele.remove(0, nbHCmNouveauModuleModele.getLength());
            nbHTdNouveauModuleModele.remove(0, nbHTdNouveauModuleModele.getLength());
            nbHTpNouveauModuleModele.remove(0, nbHTpNouveauModuleModele.getLength());
        } catch (BadLocationException e){
            System.out.println("erreur");
            e.printStackTrace();

        }
    }

    public Document getIntituleNouveauModuleModele() {
        return intituleNouveauModuleModele;
    }

    public Document getNbHCmNouveauModuleModele() {
        return nbHCmNouveauModuleModele;
    }

    public Document getNbHTdNouveauModuleModele() {
        return nbHTdNouveauModuleModele;
    }

    public Document getNbHTpNouveauModuleModele() {
        return nbHTpNouveauModuleModele;
    }
}
