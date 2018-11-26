package fr.utln.projet.controleur;

import fr.utln.projet.modele.CoursModele;
import fr.utln.projet.vue.PlanningEtudiantVue;

public class CoursControleur {
    private PlanningEtudiantVue planningEtudiantVue;
    private CoursModele coursModele;

    public CoursControleur(PlanningEtudiantVue planningEtudiantVue){
        this.planningEtudiantVue = planningEtudiantVue;
        this.coursModele = new CoursModele(this);
    }
}
