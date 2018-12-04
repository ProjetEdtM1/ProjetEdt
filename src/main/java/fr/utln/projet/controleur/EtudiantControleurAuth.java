package fr.utln.projet.controleur;

import fr.utln.projet.modele.EtudiantModeleAuth;
import fr.utln.projet.vue.AuthentificationVue;

public class EtudiantControleurAuth {
    private AuthentificationVue authentificationVue;
    private EtudiantModeleAuth etudiantModeleAuth;


    public EtudiantControleurAuth(AuthentificationVue authentificationVue){
        this.authentificationVue = authentificationVue;
        this.etudiantModeleAuth = new EtudiantModeleAuth(this);

    }

    public boolean connecterEtudiant(String login, String mdp){
        if(this.etudiantModeleAuth.connecterEtudiant(login,mdp)){
            System.out.println("ok");
            return true;
        }else{
            System.out.println("nok");
            return false;
        }

    }

    public String getGroup(String numEtudiant){
        return etudiantModeleAuth.getGroup(numEtudiant);
    }

    public AuthentificationVue getAuthentificationVue() {
        return authentificationVue;
    }
}
