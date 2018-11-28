package fr.utln.projet.controleur;

import fr.utln.projet.modele.CoursModele;
import fr.utln.projet.utilisateur.Cours;
import fr.utln.projet.vue.PlanningEtudiantVue;

import java.util.ArrayList;
import java.util.List;

public class CoursControleur {
    private PlanningEtudiantVue planningEtudiantVue;
    private CoursModele coursModele;

    public CoursControleur(PlanningEtudiantVue planningEtudiantVue){
        this.planningEtudiantVue = planningEtudiantVue;
        this.coursModele = new CoursModele(this);
    }

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
