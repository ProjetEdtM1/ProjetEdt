package fr.utln.projet.controleur;

import com.sun.deploy.panel.NumberDocument;
import fr.utln.projet.DAO.DAOCours;
import fr.utln.projet.modele.CoursModele;
import fr.utln.projet.utilisateur.Cours;
import fr.utln.projet.vue.PlanningSuperVue;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CoursControleur {
    private PlanningSuperVue planningVue;
    private CoursModele coursModele;


    public CoursControleur(PlanningSuperVue planningVue) {
        this.planningVue = planningVue;
        this.coursModele = new CoursModele();
    }


    public List<Cours> getCoursSemaineGroupe(String intituleGroupe){
        return this.coursModele.getCoursSemaineGroupe(intituleGroupe);
    }

    public List<Cours> getCoursSemaineProf(String login){
        return this.coursModele.getCoursSemaineProf(login);
    }

    public  Object[][] remplireTableCoursProf(String login){
        Object[][] donnes = new Object[11][8];
        List<Cours> listeCours = new ArrayList<>();
        listeCours = getCoursSemaineProf(login);

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

        for(Cours c: listeCours){
            int jour = c.getDateCours().getDay();

            String heureDeb = c.getHeureDebCours().toString();
            String heureFin = c.getHeureFinCours().toString();

            heureDeb = Character.toString(heureDeb.charAt(0)) + Character.toString(heureDeb.charAt(1));
            heureFin = Character.toString(heureFin.charAt(0)) + Character.toString(heureFin.charAt(1));

            int hDebut = Integer.parseInt(heureDeb);
            int hFin = Integer.parseInt(heureFin);

            int iCours = hDebut-8;
            for(int i = 0;i< hFin-hDebut;++i){
                donnes[iCours][jour] = c.getIntituleModule();
                ++iCours;
            }


        }
        return donnes;
    }

    public  Object[][] remplireTableCours(String intituleGroupe){
        Object[][] donnes = new Object[11][8];
        List<Cours> listeCours = new ArrayList<>();
        listeCours = getCoursSemaineGroupe(intituleGroupe);

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

        for(Cours c: listeCours){
            System.out.println(c.getIntituleModule());
            int jour = c.getDateCours().getDay();

            String heureDeb = c.getHeureDebCours().toString();
            String heureFin = c.getHeureFinCours().toString();

            heureDeb = Character.toString(heureDeb.charAt(0)) + Character.toString(heureDeb.charAt(1));
            heureFin = Character.toString(heureFin.charAt(0)) + Character.toString(heureFin.charAt(1));

            int hDebut = Integer.parseInt(heureDeb);
            int hFin = Integer.parseInt(heureFin);
            int iCours = hDebut-8;
            for(int i = 0;i< hFin-hDebut;++i){
                donnes[iCours][jour] = c.getIntituleModule();
                ++iCours;
            }


        }
        return donnes;
    }
}
