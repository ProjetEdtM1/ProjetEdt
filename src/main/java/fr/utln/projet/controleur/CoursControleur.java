package fr.utln.projet.controleur;

import fr.utln.projet.modele.CoursModele;
import fr.utln.projet.utilisateur.Cours;
import fr.utln.projet.vue.PlanningEtudiantVue;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.util.ArrayList;
import java.util.List;

public class CoursControleur {
    private PlanningEtudiantVue planningEtudiantVue;
    private CoursModele coursModele;

    private String nouveauGroupeCours = new String();
    private Document nouveauIntituleModule = new PlainDocument();
    private Document nouveauDateCours = new PlainDocument();
    private Document nouveauHDebutCours = new PlainDocument();
    private Document nouveauHFinCours = new PlainDocument();
    private Document nouveauNumSalleCours = new PlainDocument();

    public CoursControleur(PlanningEtudiantVue planningEtudiantVue){
        this.planningEtudiantVue = planningEtudiantVue;
        this.coursModele = new CoursModele(this);

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

            }
        };

        nouveauIntituleModule.addDocumentListener(ecouterChangementTexte);
        nouveauDateCours.addDocumentListener(ecouterChangementTexte);
        nouveauHDebutCours.addDocumentListener(ecouterChangementTexte);
        nouveauHFinCours.addDocumentListener(ecouterChangementTexte);
        nouveauNumSalleCours.addDocumentListener(ecouterChangementTexte);
    }



    public void ajoutCours() {
        try {

            System.out.println(nouveauGroupeCours);
            System.out.println(nouveauIntituleModule.getText(0, nouveauIntituleModule.getLength()));
            System.out.println(nouveauDateCours.getText(0, nouveauDateCours.getLength()));
            System.out.println(nouveauHDebutCours.getText(0, nouveauHDebutCours.getLength()));
            System.out.println(nouveauHFinCours.getText(0, nouveauHFinCours.getLength()));
            System.out.println(nouveauNumSalleCours.getText(0, nouveauNumSalleCours.getLength()));


        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }


    public String getNouveauGroupeCours() {
        return nouveauGroupeCours;
    }

    public Document getNouveauIntituleModule() {
        return nouveauIntituleModule;
    }

    public Document getNouveauDateCours() {
        return nouveauDateCours;
    }

    public Document getNouveauHDebutCours() {
        return nouveauHDebutCours;
    }

    public Document getNouveauHFinCours() {
        return nouveauHFinCours;
    }

    public Document getNouveauNumSalleCours() {
        return nouveauNumSalleCours;
    }


    public void setTest(String nouveauGroupeCours) {
        this.nouveauGroupeCours = nouveauGroupeCours;
    }

    // Pas toucher a ca !!
    public List<Cours> getCoursSemaineGroupe(String intituleGroupe){
        return this.coursModele.getCoursSemaineGroupe(intituleGroupe);
    }

    public Object[][] remplireTableCours(String intituleGroupe){
        Object[][] donnes = new Object[11][8];
        List<Cours> listeCours = new ArrayList<>();
        listeCours = getCoursSemaineGroupe(intituleGroupe);

        for (Cours c: listeCours) {
            int jour = c.getDateCours().getDay();
            String heureDeb = c.getHeureDebCours().toString();
            String heureFin = c.getHeureFinCours().toString();

            donnes[0][0]="8h";
            donnes[1][0]="9h";
            donnes[2][0]="10h";
            donnes[3][0]="11h";
            donnes[4][0]="12h";
            donnes[5][0]="13h";
            donnes[6][0]="14h";
            donnes[7][0]="15h";
            donnes[8][0]="16h";
            donnes[9][0]="17h";
            donnes[10][0]="18h";

            if(heureDeb.equals("08:00:00")){
                donnes[0][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("09:00:00")){
                donnes[1][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("10:00:00")){
                donnes[2][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("11:00:00")){
                donnes[3][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("12:00:00")){
                donnes[4][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("13:00:00")){
                donnes[5][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("14:00:00")){
                donnes[6][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("15:00:00")){
                donnes[7][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("16:00:00")){
                donnes[8][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("17:00:00")){
                donnes[9][jour] = c.getIntituleModule();
            }
            if(heureDeb.equals("18:00:00")){
                donnes[10][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("09:00:00")){
                donnes[0][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("10:00:00")){
                donnes[1][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("11:00:00")){
                donnes[2][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("12:00:00")){
                donnes[3][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("13:00:00")){
                donnes[4][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("14:00:00")){
                donnes[5][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("15:00:00")){
                donnes[6][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("16:00:00")){
                donnes[7][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("17:00:00")){
                donnes[8][jour] = c.getIntituleModule();
            }
            if(heureFin.equals("18:00:00")){
                donnes[9][jour] = c.getIntituleModule();
            }


        }
        return donnes;
    }


}
