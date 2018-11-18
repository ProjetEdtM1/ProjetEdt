package fr.utln.projet.vue;

import fr.utln.projet.controleur.ProfesseurControleur;
import fr.utln.projet.modele.ProfesseurListModele;
import fr.utln.projet.modele.ProfesseurModele;

public class ProfesseurVue {
    private final ProfesseurModele professeurModele;
    private final ProfesseurControleur professeurControleur;

    private static ProfesseurListModele professeurListModele;

    public ProfesseurVue(ProfesseurModele professeurModele) {
        this.professeurModele = professeurModele;
        this.professeurControleur = new ProfesseurControleur(this, professeurModele);

    }

    public void setCreationProfesseur(boolean b) {
    }
}
