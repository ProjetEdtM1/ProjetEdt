package fr.utln.projet.controleur;

import com.sun.deploy.panel.NumberDocument;
import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.module.Module;
import fr.utln.projet.vue.ModuleVUE;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.util.List;

public class ModuleControleur{
    private ModuleVUE moduleVUE;
    private ModuleModele moduleModele;

    private Document intituleNouveauModuleModele = new PlainDocument();
    private Document nbHCmNouveauModuleModele = new NumberDocument();
    private Document nbHTdNouveauModuleModele = new NumberDocument();
    private Document nbHTpNouveauModuleModele = new NumberDocument();

    private Document intituleModuleModele = new PlainDocument();
    private Document nbHCmModuleModele = new PlainDocument();
    private Document nbHTdModuleModele = new PlainDocument();
    private Document nbHTpModuleModele = new PlainDocument();



    public ModuleControleur(final ModuleVUE moduleVue, ModuleModele moduleModele) {
        this.moduleVUE = moduleVue;
        this.moduleModele = moduleModele;


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

                //todo Modifier les conditions d'ajout
                boolean conditionAjout = ((intituleNouveauModuleModele.getLength()==0) || (nbHCmNouveauModuleModele.getLength()==0) ||
                        (nbHTdNouveauModuleModele.getLength()==0) || (nbHTpNouveauModuleModele.getLength()==0));
                boolean conditionModif = ((nbHCmModuleModele.getLength()==0) ||
                        (nbHTdModuleModele.getLength()==0) || (nbHTpModuleModele.getLength()==0));

                if(conditionAjout) {
                    moduleVUE.setBoutonAjouter(false);
                }
                else if (!conditionAjout) {
                   moduleVUE.setBoutonAjouter(true);
                }
                if(conditionModif){
                    moduleVUE.setBoutonModifier(false);
                }
                else if(!conditionModif){
                    moduleVUE.setBoutonModifier(true);
                }
            }
        };

        intituleNouveauModuleModele.addDocumentListener(ecouterChangementTexte);
        nbHCmNouveauModuleModele.addDocumentListener(ecouterChangementTexte);
        nbHTdNouveauModuleModele.addDocumentListener(ecouterChangementTexte);
        nbHTpNouveauModuleModele.addDocumentListener(ecouterChangementTexte);

        intituleModuleModele.addDocumentListener(ecouterChangementTexte);
        nbHCmModuleModele.addDocumentListener(ecouterChangementTexte);
        nbHTdModuleModele.addDocumentListener(ecouterChangementTexte);
        nbHTpModuleModele.addDocumentListener(ecouterChangementTexte);

    }

    /* methode pour ajouter un Module */

    public void ajouterModule() {

        try {

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

//    Methode pour supprimer un module
//todo Revoir cette fonction
    public boolean supprimerModule(Module module) {
        boolean i = moduleModele.supprimerModule(module);
        return i;
    }

    /**
     * Modifie un étudiant avec les champs saisient par l'utilisateur
     * @author Nicolas Guigou
     */
    public void modifierEtudiant(){
        try {
            moduleModele.modifierModule(intituleModuleModele.getText(0,intituleModuleModele.getLength()),
                    convertionIntStr(nbHCmModuleModele.getText(0,nbHCmModuleModele.getLength())),
                    convertionIntStr(nbHTdModuleModele.getText(0,nbHTdModuleModele.getLength())),
                    convertionIntStr(nbHTpModuleModele.getText(0,nbHTpModuleModele.getLength())));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public int convertionIntStr(String mot) {
        try {
            int res = Integer.parseInt(mot);
            return res;
        } catch (NumberFormatException e) {
            System.out.println("Vous n'avez pas entré un nombre");
        }
        return -1;
    }

    public boolean convertion(String mot) {
        if (convertionIntStr(mot) < 0) {
            return false;
        }
        return true;
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

    public Document getIntituleModuleModele() {
        return intituleModuleModele;
    }

    public Document getNbHCmModuleModele() {
        return nbHCmModuleModele;
    }

    public Document getNbHTdModuleModele() {
        return nbHTdModuleModele;
    }

    public Document getNbHTpModuleModele() {
        return nbHTpModuleModele;
    }

    public List<Module> getListModule() {
        return moduleModele.getListeModule();
    }
}
