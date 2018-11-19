package fr.utln.projet.controleur;

import fr.utln.projet.modele.ProfesseurModeleAuth;
import fr.utln.projet.vue.AuthentificationVue;

public class ProfesseurControleurAuth {
    private AuthentificationVue authentificationVue;
    private ProfesseurModeleAuth professeurModeleAuth;

    public ProfesseurControleurAuth(AuthentificationVue authentificationVue){
        this.authentificationVue = authentificationVue;
        this.professeurModeleAuth = new ProfesseurModeleAuth(this);
    }

    public boolean connecterProfesseur(String login, String mdp){
        if(this.professeurModeleAuth.connecterProfesseur(login,mdp)){
            System.out.println("ok");
            return true;
        }else{
            System.out.println("nok");
            return false;
        }

    }

    public boolean connecterProfesseurRef(String login,String mdp){
        return this.professeurModeleAuth.connecterProfesseurRef(login,mdp);
    }

    public AuthentificationVue getAuthentificationVue() {
        return authentificationVue;
    }
}